package academy.nhn.task_api.controller;

import academy.nhn.task_api.entity.Task;
import academy.nhn.task_api.entity.dto.TaskDto;
import academy.nhn.task_api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @GetMapping("/{userId}/projects/{projectId}/tasks/{taskId}")
    public TaskDto getTaskByTaskId(@PathVariable String userId, @PathVariable int projectId, @PathVariable int taskId) {
        TaskDto taskDto = taskService.findTaskDtoById(taskId);
        return taskDto;
    }

    @PostMapping("/{userId}/projects/{projectId}/tasks/new")
    public Task createTask(@PathVariable String userId, @PathVariable int projectId, @RequestBody TaskDto dto) {
        Task task = taskService.createTask(dto);
        return task;
    }

    @PutMapping("/{userId}/projects/{projectId}/tasks/{taskId}")
    public void editTask(@PathVariable String userId, @PathVariable int projectId, @PathVariable int taskId, @RequestBody TaskDto updatedTask) {
        Task task = taskService.findTaskById(taskId);
        taskService.editTask(updatedTask);
    }


    @DeleteMapping("/{userId}/projects/{projectId}/tasks/{taskId}")
    public void deleteTask(@PathVariable String userId, @PathVariable int projectId, @PathVariable int taskId) {
        taskService.deleteTaskById(taskId);
    }
}
