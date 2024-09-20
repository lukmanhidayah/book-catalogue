package com.lukmanhidayah.catalog.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {

  // random Long generated serial version UID by current timestamp
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "code", nullable = false, unique = true)
  private String code;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  @ManyToMany(mappedBy = "categories")
  private List<Book> books;

}
