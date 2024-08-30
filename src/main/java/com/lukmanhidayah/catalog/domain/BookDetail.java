package com.lukmanhidayah.catalog.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "book_detail")
public class BookDetail implements Serializable {

  /**
   * random generated serial version UID
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long id;

  @Column(name = "settings")
  private String settings;

  @Column(name = "thumbnail")
  private String thumbnail;

  @OneToOne
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;
}
