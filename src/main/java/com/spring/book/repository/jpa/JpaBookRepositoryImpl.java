package com.spring.book.repository.jpa;

import com.spring.book.model.Book;
import com.spring.book.repository.BookRepository;
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
public class JpaBookRepositoryImpl implements BookRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Book> findAll() {
		return this.em.createQuery("select distinct book FROM Book book left join book.authors order by book.title").getResultList();
	}

	@Transactional
	public void save(Book book) {
		if (book.getId() == null) {
			em.persist(book);
		} else {
			em.merge(book);
		}
	}

	public Book findById(Long id) {
		Query query = this.em.createQuery("select book from Book book left join book.authors where UPPER(book.id) =:id");
		query.setParameter("id", id);
		return (Book) query.getSingleResult();
	}
	@Transactional
	public void deleteBook(Long id) {
		Book book = findById(id);
		System.out.println("book id in delete book = "+book.getId());
		if (book.getId() != null) {
			System.out.println("book id in delete book = "+book.getId());
			em.remove(book);
		}
	}

	public List<Book> findByName(String name) {
		Query query = this.em.createQuery("select distinct book from Book book left join book.authors where UPPER(book.title) like :name");
		query.setParameter("name", name.toUpperCase() + "%");
		return query.getResultList();
	}

	public List<Book> findByAuthorName(String authorName) {
		Query query = this.em.createQuery("select distinct book from Book book left join book.authors author where author.name like :authorName");
		query.setParameter("authorName", authorName.toUpperCase() + "%");
		return query.getResultList();
	}
}
