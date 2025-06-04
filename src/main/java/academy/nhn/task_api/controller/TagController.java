package academy.nhn.task_api.controller;

import academy.nhn.task_api.entity.Tag;
import academy.nhn.task_api.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class TagController {
    private final TagService tagService;

    @GetMapping("/{userId}/projects/{projectId}/tags/{tagId}")
    public Tag getTag(@PathVariable String userId, @PathVariable int projectId, @PathVariable int tagId) {
        return tagService.findById(tagId);
    }

    @PutMapping("/{userId}/projects/{projectId}/tags/{tagId}")
    public HttpEntity<Void> updateTag(@PathVariable String userId, @PathVariable int projectId, @PathVariable int tagId, Tag tag) {
        Tag oldTag = tagService.findById(tagId);
        tagService.saveTag(tag);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/projects/{projectId}/tags/{tagId}")
    public void deleteTag(@PathVariable String userId, @PathVariable int projectId, @PathVariable int tagId) {
        tagService.deleteTag(tagId);
    }
}
