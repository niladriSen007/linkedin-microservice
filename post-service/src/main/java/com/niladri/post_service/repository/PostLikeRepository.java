package com.niladri.post_service.repository;

import com.niladri.post_service.model.PostLike;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);

    //Custom delete methods in Spring Data JPA need to be marked with @Transactional to ensure proper transaction management and data consistency.
    //Here's why:
    //Ensuring Atomicity: Database operations, especially those that modify data like deletions, should ideally be atomic.
    // This means they either fully succeed and all changes are committed, or they entirely fail and all changes are rolled back,
    // leaving the database in its original state.
    // @Transactional guarantees this atomicity by creating a transaction boundary around the method's execution.
    @Transactional
    void deleteByUserIdAndPostId(Long userId, Long postId);
}
