package academy.nhn.task_api.service;

import academy.nhn.task_api.entity.Comment;
import academy.nhn.task_api.entity.Task;
import academy.nhn.task_api.entity.dto.CommentAuthorContentDto;
import academy.nhn.task_api.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final TaskService taskService;
    private final CommentRepository commentRepository;

    public Comment createComment(int taskId, CommentAuthorContentDto dto) {
        Task task = taskService.findTaskById(taskId);
        Comment comment = new Comment(task, dto);
        return comment;
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void editComment(Comment comment) {
        Comment cmt = findById(comment.getId());
        cmt.setContent(comment.getContent());
    }
    public Comment findById(int id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        return commentOptional.orElseThrow(() -> new EntityNotFoundException());
    }

    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }
}
