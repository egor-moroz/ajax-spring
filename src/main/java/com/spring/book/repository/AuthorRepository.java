package com.spring.book.repository;


import com.spring.book.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ???? on 01.07.2016.
 */

public interface AuthorRepository {

	List<Author> findAll();

	void save(Author author);

	Author findById(Long id);

	void delete(Author author);


}
