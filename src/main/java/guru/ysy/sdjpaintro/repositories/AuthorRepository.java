package guru.ysy.sdjpaintro.repositories;

import guru.ysy.sdjpaintro.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by zhenrui on 2021/11/24 17:06
 */
public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
