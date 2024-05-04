package ru.edu.springdata.rest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.repository.JPARepositoryImpl;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
@Transactional
public class CRUDController {

    private final JPARepositoryImpl jpaRepository;

    @GetMapping(value = "/book/{id}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBook(@PathVariable("id") Long id) {
        return jpaRepository.findById(id).orElse(null);
    }

    @GetMapping(value = "/book/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getBooks() {
        return jpaRepository.findAll();
    }

    @PostMapping(value = "/book/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book addBook(@RequestBody Book inputBook) {
        return jpaRepository.save(inputBook);
    }

    @PutMapping(value = "/book/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book updateBook(@RequestBody Book inputBook) {
        return jpaRepository.save(inputBook);
    }

    @DeleteMapping(value = "/book/{id}/delete")
    public Long deleteBook(@PathVariable("id") Long id) {
        jpaRepository.deleteById(id);
        return id;
    }
}
