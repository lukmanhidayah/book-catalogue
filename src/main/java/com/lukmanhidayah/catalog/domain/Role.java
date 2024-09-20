package com.lukmanhidayah.catalog.domain;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "role")
@Entity
public class Role implements GrantedAuthority {

  private static final long serialVersionUID = 123543209L;

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Override
  public String getAuthority() {
    return "ROLE_" + name;
  }

}
