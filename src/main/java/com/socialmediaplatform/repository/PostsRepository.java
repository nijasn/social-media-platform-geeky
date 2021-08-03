package com.socialmediaplatform.repository;

import com.socialmediaplatform.domain.Posts;
import com.socialmediaplatform.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Posts} entity.
 */
@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findPostsByUser(User user);
}
