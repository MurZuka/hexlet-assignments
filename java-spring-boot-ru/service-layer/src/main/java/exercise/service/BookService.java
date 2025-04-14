package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Author;
import exercise.model.Book;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AuthorRepository authorRepository;

    public List<BookDTO> getAll() {
        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(bookMapper::map)
                .toList();
    }

    public BookDTO create(BookCreateDTO bookData) {
        Book book = bookMapper.map(bookData);
        Author author = authorRepository.findById(bookData.getAuthorId())
                .orElse(null);

        book.setAuthor(author);

        bookRepository.save(book);

        BookDTO result = bookMapper.map(book);
        return result;
    }

    public BookDTO findById(Long id) {
        Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));

        BookDTO result = bookMapper.map(book);
        return result;
    }

    public BookDTO update(BookUpdateDTO bookData, Long id) {
        Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        bookMapper.update(bookData, book);

        bookRepository.save(book);
        BookDTO result = bookMapper.map(book);

        return result;
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
