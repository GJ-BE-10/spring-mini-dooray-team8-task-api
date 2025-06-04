package academy.nhn.task_api.entity.dto;

import academy.nhn.task_api.entity.MileStone;
import academy.nhn.task_api.entity.Tag;
import academy.nhn.task_api.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDto {
    private int taskId;
    private int projectId;
    private String authorId;
    private String title;
    private String content;
    private List<Tag> tags;
    private MileStone mileStone;


    public TaskDto(Task task, List<Tag> tags) {
        this.taskId = task.getId();
        this.projectId = task.getProject().getId();
        this.authorId = task.getAuthorId();
        this.title = task.getTitle();
        this.content = task.getContent();
        this.mileStone = task.getMileStone();
        this.tags = tags;

    }
}
