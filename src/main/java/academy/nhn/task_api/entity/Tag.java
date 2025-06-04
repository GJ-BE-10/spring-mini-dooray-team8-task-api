package academy.nhn.task_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int id;

    @Column(name = "tag_name", length = 50)
    private String name;

    @JoinColumn(name = "tag_project_id", nullable = true)
    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "tag")
    private Set<TaskTag> taskTags = new HashSet<>();

    public void addTask(Task task) {
        TaskTag taskTag = new TaskTag(task, this);
        taskTags.add(taskTag);
        task.getTaskTags().add(taskTag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag tag)) return false;
        return getId() == tag.getId() && Objects.equals(getName(), tag.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
