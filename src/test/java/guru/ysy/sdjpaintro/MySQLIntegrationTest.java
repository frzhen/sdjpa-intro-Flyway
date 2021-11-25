package guru.ysy.sdjpaintro;

import guru.ysy.sdjpaintro.domain.Author;
import guru.ysy.sdjpaintro.domain.Book;
import guru.ysy.sdjpaintro.repositories.AuthorRepository;
import guru.ysy.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by zhenrui on 2021/11/21 12:26
 */
@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"guru.ysy.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void testBookUuid() {
        Book book = bookRepository.save(new Book());
        assertThat(book).isNotNull();
        assertThat(book.getId()).isNotNull();

        Book fetchedBook = bookRepository.getById(book.getId());
        assertThat(fetchedBook).isNotNull();
    }
    @Test
    void testAuthorId() {
        Author author = authorRepository.save(new Author());
        assertThat(author).isNotNull();
        assertThat(author.getId()).isNotNull();

        Author fetchedAuthor = authorRepository.getById(author.getId());
        assertThat(fetchedAuthor).isNotNull();
    }
    @Test
    void testMySQL() {
        long count1 = bookRepository.count();
        assertThat(count1).isEqualTo(2);
        long count2 = authorRepository.count();
        assertThat(count2).isEqualTo(2);
    }
}
