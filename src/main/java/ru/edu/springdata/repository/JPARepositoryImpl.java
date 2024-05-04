package ru.edu.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.springdata.model.Book;

@Repository
public interface JPARepositoryImpl extends JpaRepository<Book, Long> {
}
