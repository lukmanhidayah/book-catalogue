package com.lukmanhidayah.catalog.domain;

import lombok.Data;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Data
@DynamicUpdate
@Entity
@Table(name = "publisher", indexes = {
    @Index(name = "idx_secure_id", columnList = "secure_id")
})
public class Publisher extends AbstractBestEntity {

  /**
   * random generated serial version UID
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "publisher_generator")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "company_name", nullable = false)
  private String companyName;

  @Column(name = "address", nullable = false)
  private String address;

  @OneToMany(mappedBy = "publisher")
  private List<Book> books;

}
