package com.lukmanhidayah.catalog.domain;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "author")
@SQLDelete(sql = "UPDATE author SET deleted=true WHERE id=?")

// Where(clause = "deleted=false") adalah
// sebuah anotasi yang digunakan untuk menentukan kondisi
// dimana data yang diambil dari database adalah data yang
// memiliki kondisi deleted=false.
@Where(clause = "deleted=false")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
	@SequenceGenerator(name = "author_generator", sequenceName = "author_id_seq")
	private Long id;

	@Column(name = "author_name", nullable = false, columnDefinition = "varchar(300)")
	private String name;

	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;

	@Column(name = "deleted", columnDefinition = "boolean default false")
	private Boolean deleted;

}
