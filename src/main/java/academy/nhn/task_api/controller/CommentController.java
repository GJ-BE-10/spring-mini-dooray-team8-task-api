package academy.nhn.task_api.controller;

import academy.nhn.task_api.entity.Comment;
import academy.nhn.task_api.entity.dto.CommentAuthorContentDto;
import academy.nhn.task_api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{userId}/projects/{projectId}/tasks/{taskId}/comments/new")
    public void addComment(@PathVariable String userId, @PathVariable int projectId, @PathVariable int taskId, @RequestBody CommentAuthorContentDto dto) {
        Comment comment = commentService.createComment(taskId, dto);
        commentService.saveComment(comment);
    }

    @PutMapping("/{userId}/projects/{projectId}/tasks/{taskId}/comments/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable String userId, @PathVariable int projectId, @PathVariable int taskId, @PathVariable int commentId, @RequestBody Comment comment) {
        Comment oldComment = commentService.findById(comment.getId());
        commentService.saveComment(comment);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/projects/{projectId}/tasks/{taskId}/comments/{commentId}")
    public void deleteComment(@PathVariable String userId, @PathVariable int projectId, @PathVariable int taskId, @PathVariable int commentId) {
        commentService.deleteById(commentId);

    }
}
