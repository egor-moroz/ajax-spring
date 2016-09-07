package com.spring.book.repository.jpa;

import com.spring.book.model.Author;
import com.spring.book.repository.AuthorRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ???? on 01.07.2016.
 */
@Repository
public class JpaAuthorRepositoryImpl implements AuthorRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Author> findAll() {
		return this.entityManager.createQuery("FROM Author author ORDER BY author.name", Author.class).getResultList();
	}

	@Transactional
	public void save(Author author) {
		if (author.getId() == null) {
			this.entityManager.persist(author);
			System.out.println("I am in author persist");
		} else {
			this.entityManager.merge(author);
			System.out.println("I am in author merge");
		}
	}

	public Author findById(Long id) {
		Query query = this.entityManager.createQuery("select distinct author FROM Author author where author.id =:id");
		query.setParameter("id", id);
		return (Author) query.getSingleResult();
	}

	public void delete(Author author) {
		entityManager.remove(author);
	}
}
