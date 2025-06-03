package academy.nhn.task_api.controller;

import academy.nhn.task_api.entity.Task;
import academy.nhn.task_api.entity.dto.TaskCreationDto;
import academy.nhn.task_api.exception.InvalidAccessException;
import academy.nhn.task_api.repository.TaskRepository;
import academy.nhn.task_api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;
    private final TaskService taskService;


    @GetMapping("/{userId}/projects/{projectId}/tasks/{taskId}")
    public Task getTaskByTaskId(@PathVariable String userId, @PathVariable String projectId, @PathVariable int taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isEmpty()) {
            throw new InvalidAccessException();
        }
        return taskOptional.get();
    }

    @PostMapping("/{userId}/projects/{projectId}/tasks/new")
    public void createTask(@PathVariable String userId, @PathVariable String projectId, @RequestBody TaskCreationDto taskCreationDto) {
        Task task = taskService.createTask(taskCreationDto);
        return;
    }
}
