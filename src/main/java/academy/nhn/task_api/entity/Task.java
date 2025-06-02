package academy.nhn.task_api.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @Column(name = "task_content", length = 5000)
    private String content;

    @Column(name = "task_created_at")
    private LocalDateTime createdAt;

}
