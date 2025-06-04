package academy.nhn.task_api.controller;

import academy.nhn.task_api.entity.Task;
import academy.nhn.task_api.entity.dto.TaskCreationDto;
import academy.nhn.task_api.repository.TaskRepository;
import academy.nhn.task_api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @GetMapping("/{userId}/projects/{projectId}/tasks/{taskId}")
    public Task getTaskByTaskId(@PathVariable String userId, @PathVariable String projectId, @PathVariable int taskId) {
        Task task = taskService.findTaskById(taskId);
        return task;
    }

    @PostMapping("/{userId}/projects/{projectId}/tasks/new")
    public void createTask(@PathVariable String userId, @PathVariable String projectId, @RequestBody TaskCreationDto taskCreationDto) {
        Task task = taskService.createTask(taskCreationDto);
        return;
    }

    @PutMapping("/{userId}/projects/{projectId}/tasks/{taskId}")
    public void editTask(@PathVariable String userId, @PathVariable int projectId, @PathVariable int taskId, @RequestBody Task updatedTask) {
        Task task = taskService.findTaskById(taskId);
        taskService.saveTask(updatedTask);
    }


    @DeleteMapping("/{userId}/projects/{projectId}/tasks/{taskId}")
    public void deleteTask(@PathVariable String userId, @PathVariable int projectId, @PathVariable int taskId) {
        taskService.deleteTaskById(taskId);
    }
}
