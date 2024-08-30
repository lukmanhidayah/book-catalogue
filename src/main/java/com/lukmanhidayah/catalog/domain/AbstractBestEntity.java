package com.lukmanhidayah.catalog.domain;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractBestEntity implements Serializable {

  /**
   * Generated serial version UID
   */
  private static final long serialVersionUID = 1L;

  @Column(name = "secure_id", nullable = false, unique = true)
  private String secureId = UUID.randomUUID().toString();

  @Column(name = "deleted", columnDefinition = "boolean default false")
  private Boolean deleted;

}
