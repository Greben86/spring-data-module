package ru.edu.springdata.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.repository.CRUDRepository;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class CRUDController {

    private final CRUDRepository crudRepository;

    @GetMapping(value = "/book/{id}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBook(@PathVariable("id") Long id) {
        return crudRepository.getBookById(id);
    }

    @GetMapping(value = "/book/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getBooks() {
        return crudRepository.getAllBooks();
    }

    @PostMapping(value = "/book/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book addBook(@RequestBody Book inputBook) {
        return crudRepository.addBook(inputBook);
    }

    @PutMapping(value = "/book/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book updateBook(@RequestBody Book inputBook) {
        return crudRepository.updateBook(inputBook);
    }

    @DeleteMapping(value = "/book/{id}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book deleteBook(@PathVariable("id") Long id) {
        return crudRepository.deleteBook(id);
    }
}
