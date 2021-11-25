package guru.ysy.sdjpaintro.repositories;

import guru.ysy.sdjpaintro.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by zhenrui on 2021/11/15 21:27
 */
public interface BookRepository extends JpaRepository<Book, UUID> {

}
