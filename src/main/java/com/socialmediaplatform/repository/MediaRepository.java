package com.socialmediaplatform.repository;

import com.socialmediaplatform.domain.Media;
import com.socialmediaplatform.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Media} entity.
 */
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {}
