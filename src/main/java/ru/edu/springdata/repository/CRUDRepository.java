package ru.edu.springdata.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.edu.springdata.model.Book;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CRUDRepository {
    // CREATE TABLE book(id SERIAL, name VARCHAR(255), category VARCHAR(255), language VARCHAR(255));
    // insert into book (name, category, language) values ('Молот ведьм', 'Религиозные книги', 'Английсткий');

    private final JdbcTemplate jdbcTemplate;

    public Book getBookById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Book WHERE id = ?", ((rs, rowNum) -> {
            Book book = new Book();
            book.setId(id);
            book.setName(rs.getString("name"));
            book.setCategory(rs.getString("category"));
            book.setLanguage(rs.getString("language"));
            return book;
        }), id);
    }

    public List<Book> getAllBooks() {
        return Collections.emptyList();
    }

    public Book addBook(Book book) {
        book.setId(new Date().getTime());
        return book;
    }

    public Book updateBook(Book book) {
        return book;
    }

    public Book deleteBook(Long id) {
        return new Book();
    }
}
