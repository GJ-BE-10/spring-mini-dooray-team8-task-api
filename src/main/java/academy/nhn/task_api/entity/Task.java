package academy.nhn.task_api.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @Column(name = "task_milestone_id")
    private int mileStoneId;

    @ManyToOne
    @JoinColumn(name = "task_project_id")
    private Project project;

    @Column(name = "task_author_id")
    private int authorId;

    @Column(name = "task_title")
    private String title;

    @Column(name = "task_content", length = 5000)
    private String content;

    @Column(name = "task_created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "task")
    private Set<TaskTag> taskTags = new HashSet<>();

    public void addTag(Tag tag) {
        TaskTag taskTag = new TaskTag(this, tag);
        taskTags.add(taskTag);
        tag.getTaskTags().add(taskTag);
    }

    /*
    * public void addDepartment(Department department) {
        EmployeeDepartment relation = new EmployeeDepartment(this, department);
        employeeDepartments.add(relation);
        department.getEmployeeDepartments().add(relation);
    }
    * */

}
