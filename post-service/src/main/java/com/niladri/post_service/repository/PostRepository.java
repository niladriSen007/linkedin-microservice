package com.niladri.post_service.repository;

import com.niladri.post_service.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts,Long> {
}
