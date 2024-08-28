package com.lukmanhidayah.catalog.service.impl;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import com.lukmanhidayah.catalog.domain.Database;
import com.lukmanhidayah.catalog.dto.database.DatabaseConResponseDto;
import com.lukmanhidayah.catalog.dto.database.DatabaseRequestDto;
import com.lukmanhidayah.catalog.dto.database.DatabaseResponseDto;
import com.lukmanhidayah.catalog.exception.BadRequestException;
import com.lukmanhidayah.catalog.repository.DatabaseRepository;
import com.lukmanhidayah.catalog.service.DatabaseService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class DatabaseServiceImpl implements DatabaseService {

  private JdbcTemplate jdbcTemplate;

  private DatabaseRepository databaseRepository;

  @Override
  public DatabaseConResponseDto connectToDatabase(DatabaseRequestDto request) {
    Map<String, List<String>> result = connectToDatabase(request.getUsername(), request.getIp(),
        request.getDatabaseName(),
        request.getPassword(),
        request.getDbType());

    return new DatabaseConResponseDto("Connected to database successfully", result);

  }

  @Override
  public Database saveDatabaseDetails(DatabaseRequestDto request) {
    Database database = new Database();
    database.setUsername(request.getUsername());
    database.setIp(request.getIp());
    database.setDatabaseName(request.getDatabaseName());
    database.setPassword(request.getPassword());
    database.setDbType(request.getDbType());
    database.setPort(request.getPort());

    return databaseRepository.save(database);

  }

  @Override
  public DatabaseConResponseDto getTableDDL(String tableName, String dbType) {
    Map<String, List<String>> ddl = listAllTablesAndColumns(dbType);
    return new DatabaseConResponseDto("DDL fetched successfully", ddl);
  }

  private Map<String, List<String>> connectToDatabase(String username, String ip, String databaseName, String password,
      String dbType) {
    try {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      String url;
      switch (dbType.toLowerCase()) {
        case "mysql":
          dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
          url = "jdbc:mysql://" + ip + ":3306/" + databaseName;
          dataSource.setUrl(url);
          break;
        case "postgres":
          dataSource.setDriverClassName("org.postgresql.Driver");
          url = "jdbc:postgresql://" + ip + ":5432/" + databaseName;
          dataSource.setUrl(url);
          break;
        case "bigquery":
          dataSource.setDriverClassName("com.simba.googlebigquery.jdbc.Driver");
          url = "jdbc:bigquery://https://www.googleapis.com/bigquery/v2:443;ProjectId=" + databaseName;
          dataSource.setUrl(url);
          break;
        case "oracle":
          dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
          url = "jdbc:oracle:thin:@" + ip + ":1521:" + databaseName;
          dataSource.setUrl(url);
          break;
        default:
          throw new BadRequestException("Unsupported database type: " + dbType);
      }

      dataSource.setUsername(username);
      dataSource.setPassword(password);
      jdbcTemplate.setDataSource(dataSource);
      return listAllTablesAndColumns(dbType);
    } catch (Exception e) {
      throw new BadRequestException("Failed to connect: " + e.getMessage());
    }
  }

  public List<String> listAllTables(String dbType) {
    String query;
    switch (dbType.toLowerCase()) {
      case "mysql":
        query = "SHOW TABLES";
        break;
      case "postgres":
        query = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'";
        break;
      case "bigquery":
        query = "SELECT table_name FROM `project-id.dataset.INFORMATION_SCHEMA.TABLES`";
        break;
      case "oracle":
        query = "SELECT table_name FROM user_tables";
        break;
      default:
        throw new BadRequestException("Unsupported database type: " + dbType);
    }
    return jdbcTemplate.queryForList(query, String.class);
  }

  public Map<String, List<String>> listAllTablesAndColumns(String dbType) {
    String query;
    switch (dbType.toLowerCase()) {
      case "mysql":
        query = "SELECT table_name, column_name FROM information_schema.columns WHERE table_schema = DATABASE()";
        break;
      case "postgres":
        query = "SELECT table_name, column_name FROM information_schema.columns WHERE table_schema = 'public'";
        break;
      case "bigquery":
        query = "SELECT table_name, column_name FROM `project-id.dataset.INFORMATION_SCHEMA.COLUMNS`";
        break;
      case "oracle":
        query = "SELECT table_name, column_name FROM user_tab_columns";
        break;
      default:
        throw new BadRequestException("Unsupported database type: " + dbType);
    }

    List<Map<String, Object>> results = jdbcTemplate.queryForList(query);
    Map<String, List<String>> tableColumnsMap = new HashMap<>();

    for (Map<String, Object> row : results) {
      String tableName = (String) row.get("table_name");
      String columnName = (String) row.get("column_name");
      tableColumnsMap.computeIfAbsent(tableName, k -> new ArrayList<>()).add(columnName);
    }

    return tableColumnsMap;
  }

  @Override
  public DatabaseResponseDto findById(Long id) {
    Database database = databaseRepository.findById(id)
        .orElseThrow(() -> new BadRequestException("Database not found"));

    DatabaseResponseDto response = new DatabaseResponseDto();
    response.setUsername(database.getUsername());
    response.setIp(database.getIp());
    response.setPort(database.getPort());
    response.setDatabaseName(database.getDatabaseName());
    response.setPassword(database.getPassword());
    response.setDbType(database.getDbType());
    return response;
  }

}
