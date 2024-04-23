package ru.edu.springdata.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.edu.springdata.model.Book;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CRUDRepository {

    private final JdbcTemplate jdbcTemplate;

    public Book getBookById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Book WHERE id = ?", ((rs, rowNum) -> {
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setCategory(rs.getString("category"));
                book.setLanguage(rs.getString("language"));
                return book;
            }), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", ((rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setName(rs.getString("name"));
            book.setCategory(rs.getString("category"));
            book.setLanguage(rs.getString("language"));
            return book;
        }));
    }

    public int addBook(Book book) {
        return jdbcTemplate.update("INSERT INTO book (name, category, language) VALUES (?, ?, ?);",
                book.getName(), book.getCategory(), book.getLanguage());
    }

    public int updateBook(Book book) {
        return jdbcTemplate.update("UPDATE book SET name = ?, category = ?, language = ? WHERE id = ?;",
                book.getName(), book.getCategory(), book.getLanguage(), book.getId());
    }

    public int deleteBook(Long id) {
        return jdbcTemplate.update("DELETE FROM Book WHERE id = ?", id);
    }
}
