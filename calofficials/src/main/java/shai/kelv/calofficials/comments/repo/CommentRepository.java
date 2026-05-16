/**
 * Repository for accessing Comment data from the database.
 * This interface provides methods to find comments and replies.
 * @author Shaila Lewis, Kelvin Myat
 * @since 05.15.26
 */
package shai.kelv.calofficials.comments.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shai.kelv.calofficials.comments.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Finds comments that are replies to a specific parent comment.
     * @param parentId the id of the parent comment
     * @return list of comments with the matching parent id
     */
    List<Comment> findByParentId(Long parentId);

    /**
     * Finds all main comments that do not have a parent comment.
     * @return list of top-level comments
     */
    List<Comment> findByParentIdIsNull();
}