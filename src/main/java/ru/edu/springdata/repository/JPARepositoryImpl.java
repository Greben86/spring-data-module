package ru.edu.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.edu.springdata.model.Book;

import java.util.List;

@Repository
public interface JPARepositoryImpl extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.category = ?1")
    List<Book> getAllBooksByCategory(String category);
}
