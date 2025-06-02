package academy.nhn.task_api.controller;

import academy.nhn.task_api.entity.Task;
import academy.nhn.task_api.exception.InvalidAccessException;
import academy.nhn.task_api.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

    @GetMapping("/{userId}/projects/{projectId}/tasks/{taskId}")
    public Task getTaskByTaskId(@PathVariable String userId, @PathVariable String projectId, @PathVariable int taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isEmpty()) {
            throw new InvalidAccessException();
        }
        return taskOptional.get();
    }
}
