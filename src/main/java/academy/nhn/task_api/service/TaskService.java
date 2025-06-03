package academy.nhn.task_api.service;

import academy.nhn.task_api.entity.Project;
import academy.nhn.task_api.entity.Task;
import academy.nhn.task_api.entity.dto.TaskCreationDto;
import academy.nhn.task_api.repository.ProjectRepository;
import academy.nhn.task_api.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public Task createTask(TaskCreationDto dto) {
        Project project = projectRepository.findById(dto.getProjectId()).orElseThrow(() -> new EntityNotFoundException("project not found"));

        Task task = new Task(dto, project);
        return taskRepository.save(task);
    }

    public Task findTaskById(int taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException());
        return task;
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }
}
