package shai.kelv.calofficials.comments.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import shai.kelv.calofficials.comments.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByParentId(Long parentId);
}
