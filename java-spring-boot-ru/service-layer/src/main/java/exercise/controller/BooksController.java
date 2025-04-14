package exercise.controller;

import java.util.List;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    // BEGIN
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDTO>> index() {
        List<BookDTO> books = bookService.getAll();

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(books.size()))
                .body(books);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BookDTO> show(@PathVariable Long id) {
        BookDTO bookDTO = bookService.findById(id);

        return ResponseEntity.ok()
                .body(bookDTO);
    }

    @PostMapping
    public ResponseEntity<BookDTO> addNew(@Valid @RequestBody BookCreateDTO bookData) {
        BookDTO bookDTO = bookService.create(bookData);

        return ResponseEntity.status(201)
                .body(bookDTO);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BookDTO> edit(@PathVariable Long id,
                                        @Valid @RequestBody BookUpdateDTO bookData) {
        BookDTO bookDTO = bookService.update(bookData, id);

        return ResponseEntity.ok()
                .body(bookDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
    // END
}
