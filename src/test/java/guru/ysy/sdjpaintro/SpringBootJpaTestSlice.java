package guru.ysy.sdjpaintro;

import guru.ysy.sdjpaintro.domain.Author;
import guru.ysy.sdjpaintro.domain.Book;
import guru.ysy.sdjpaintro.repositories.AuthorRepository;
import guru.ysy.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by zhenrui on 2021/11/20 18:41
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = {"guru.ysy.sdjpaintro.bootstrap"})//bring in the bootstrap class for test
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringBootJpaTestSlice {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;


//    @Rollback(value = false)//stop rollback so that the state can be passed on to the next test
    @Commit//This is cleaner than @Rollback
    @Order(1)
    @Test
    void testJpaTestSplice() {
        long countAuthorBefore = authorRepository.count();
        assertThat(countAuthorBefore).isEqualTo(2);
        long countBookBefore = bookRepository.count();
        assertThat(countBookBefore).isEqualTo(2);

        Author newAuthor = authorRepository.save(new Author("Noah", "Gordon"));
        bookRepository.save(new Book("The Physician", newAuthor.getId(), "Packt", "789"));

        long countAuthorAfter = authorRepository.count();
        long countBookAfter = bookRepository.count();

        assertThat(countAuthorBefore).isLessThan(countAuthorAfter);
        assertThat(countBookBefore).isLessThan(countBookAfter);
    }

    @Order(2)
    @Test
    void testJpaTestSpliceTransaction() {
        long countAuthor = authorRepository.count();
        long countBook = bookRepository.count();
        assertThat(countAuthor).isEqualTo(3);
        assertThat(countBook).isEqualTo(3);
    }
}
