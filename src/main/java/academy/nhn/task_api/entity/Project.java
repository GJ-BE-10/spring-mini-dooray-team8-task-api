package academy.nhn.task_api.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private int id;

    @Column(name = "project_admin_id")
    private String adminId;

    @Column(name = "project_name", length = 50)
    private String name;

    @Column(name = "project_status")
    private ProjectStatus status;

    @OneToMany(mappedBy = "project")
    List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    List<MileStone> mileStones = new ArrayList<>();
}
