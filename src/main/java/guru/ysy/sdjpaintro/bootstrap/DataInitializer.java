package guru.ysy.sdjpaintro.bootstrap;

import guru.ysy.sdjpaintro.domain.Author;
import guru.ysy.sdjpaintro.domain.AuthorUuid;
import guru.ysy.sdjpaintro.domain.Book;
import guru.ysy.sdjpaintro.repositories.AuthorRepository;
import guru.ysy.sdjpaintro.repositories.AuthorUuidRepository;
import guru.ysy.sdjpaintro.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by zhenrui on 2021/11/15 21:29
 */
@Profile({"local","default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorUuidRepository authorUuidRepository;

    public DataInitializer(BookRepository bookRepository, AuthorRepository authorRepository, AuthorUuidRepository authorUuidRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.authorUuidRepository = authorUuidRepository;
    }

    @Override
    public void run(String... args) {
        try {
            System.out.println("********** Books initializing ************");
            bookRepository.deleteAll();

            Book bookDDD = new Book(
                    "Domain Driven Design",
                    "123",
                    "RandomHouse",
                    null);
            bookRepository.save(bookDDD);

            Book bookSIA = new Book(
                    "Spring In Action",
                    "234",
                    "Orielly",
                    null);
            bookRepository.save(bookSIA);

            bookRepository.findAll().forEach(book -> {
                System.out.println("Book Id: " + book.getId());
                System.out.println("Book Title: " + book.getTitle());
            });

            System.out.println("******** author initializing **********");
            authorRepository.deleteAll();
            Author author1 = new Author("Eric","Evans");
            System.out.println("Id " + author1.getId());
            authorRepository.save(author1);

            Author author2 = new Author("Craig","Walls");
            authorRepository.save(author2);

            authorRepository.findAll().forEach(author -> {
                System.out.println("Author Id: " + author.getId());
                System.out.println("Author Name: " + author.getFirstName() + " " + author.getLastName());
            });

            System.out.println("******** author uuid initializing **********");
            authorUuidRepository.deleteAll();
            AuthorUuid authorUuid1 = new AuthorUuid();
            authorUuid1.setFirstName("Joe");
            authorUuid1.setLastName("Black");
            authorUuidRepository.save(authorUuid1);
            authorUuidRepository.findAll().forEach(authorUuid -> {
                System.out.println("Author UUID: " + authorUuid.getId());
                System.out.println("Author Name: " + authorUuid.getFirstName() + " " + authorUuid.getLastName());
            });

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Finally");
        }

    }
}
