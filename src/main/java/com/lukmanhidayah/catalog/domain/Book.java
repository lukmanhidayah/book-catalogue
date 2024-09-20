package com.lukmanhidayah.catalog.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "book", indexes = {
		@Index(name = "idx_secure_id", columnList = "secure_id")
})
public class Book extends AbstractBestEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -493967282312085855L;

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description", nullable = false, columnDefinition = "TEXT")
	private String description;

	@ManyToOne
	@JoinColumn(name = "publisher_id", nullable = false)
	private Publisher publisher;

	@ManyToMany
	@JoinTable(name = "book_author", joinColumns = {
			@JoinColumn(name = "book_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "author_id", referencedColumnName = "id")
			})
	private List<Author> authors;

	@ManyToMany
	@JoinTable(name = "book_category", joinColumns = {
			@JoinColumn(name = "book_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "category_code", referencedColumnName = "code")
			})
	private List<Category> categories;

}
