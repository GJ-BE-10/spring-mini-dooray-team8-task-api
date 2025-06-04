package academy.nhn.task_api.entity;

import academy.nhn.task_api.entity.dto.TaskDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int id;

    @OneToOne
    private MileStone mileStone;

    @ManyToOne
    @JoinColumn(name = "task_project_id")
    private Project project;

    @Column(name = "task_author_id")
    private String authorId;

    @Column(name = "task_title")
    private String title;

    @Column(name = "task_content", length = 5000)
    private String content;

    @Column(name = "task_created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "task")
    private Set<TaskTag> taskTags = new HashSet<>();

    public Task(TaskDto dto, Project project) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.authorId = dto.getAuthorId();
        this.project = project;
        this.mileStone = dto.getMileStone();
        this.createdAt = LocalDateTime.now();
    }

    public void addTag(Tag tag) {
        TaskTag taskTag = new TaskTag(this, tag);
        taskTags.add(taskTag);
        tag.getTaskTags().add(taskTag);
    }

    public void updateFromDto(TaskDto dto) {
    this.title = dto.getTitle();
    this.content = dto.getContent();
    this.mileStone = dto.getMileStone();
    }
}
