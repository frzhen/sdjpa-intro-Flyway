package guru.ysy.sdjpaintro.repositories;

import guru.ysy.sdjpaintro.domain.AuthorUuid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by zhenrui on 2021/11/25 16:46
 */
public interface AuthorUuidRepository extends JpaRepository<AuthorUuid, UUID> {
}
