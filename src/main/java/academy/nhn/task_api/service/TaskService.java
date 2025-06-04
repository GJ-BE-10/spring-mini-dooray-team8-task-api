package academy.nhn.task_api.service;

import academy.nhn.task_api.entity.Project;
import academy.nhn.task_api.entity.Tag;
import academy.nhn.task_api.entity.Task;
import academy.nhn.task_api.entity.TaskTag;
import academy.nhn.task_api.entity.dto.TaskDto;
import academy.nhn.task_api.repository.TagRepository;
import academy.nhn.task_api.repository.TaskRepository;
import academy.nhn.task_api.repository.TaskTagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectService projectService;
    private final TaskTagRepository taskTagRepository;
    private final TagRepository tagRepository;

    public Task createTask(TaskDto dto) {
        Project project = projectService.findById(dto.getProjectId());

        Task task = new Task(dto, project);
        Task save = taskRepository.save(task);
        for (Tag tag : dto.getTags()) {
            taskTagRepository.save(new TaskTag(save, tag));
        }
        return save;
    }

    public Task findTaskById(int taskId) {
        Task task = taskRepository.findByIdWithTags(taskId).orElseThrow(() -> new EntityNotFoundException());
        return task;
    }
    public TaskDto findTaskDtoById(int taskId) {
        Task task = findTaskById(taskId);
        Set<TaskTag> taskTags = task.getTaskTags();
        List<Tag> tags = taskTags.stream().map(TaskTag::getTag).collect(Collectors.toList());
        TaskDto taskDto = new TaskDto(task, tags);
        return taskDto;
    }

    public void deleteTaskById(int taskId) {
        taskRepository.deleteById(taskId);
    }

    public void editTask(TaskDto newTask) {
        Task existingTask = findTaskById(newTask.getTaskId());
        Set<TaskTag> oldTaskTags = existingTask.getTaskTags();
        List<Tag> oldTags = oldTaskTags.stream().map(TaskTag::getTag).collect(Collectors.toList()); // 기존 tags
        List<Tag> newTags = newTask.getTags();
        for (Tag tag : oldTags) {
            if (newTags.remove(tag)) { // 여기서 지워졌다면, tasktag레파지토리에서도 지워져야함
                taskTagRepository.deleteByTaskAndTag(existingTask, tag);
            }
        }
        for (Tag tag : newTags) {
            taskTagRepository.save(new TaskTag(existingTask, tag));
        }
        existingTask.updateFromDto(newTask);
    }
}
