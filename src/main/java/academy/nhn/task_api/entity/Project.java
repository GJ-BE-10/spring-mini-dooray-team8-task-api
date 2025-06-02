package academy.nhn.task_api.entity;

import academy.nhn.task_api.entity.dto.ProjectCreationDto;
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
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<Tag> tags = new ArrayList<>();

    @OneToMany
    private List<MileStone> mileStones = new ArrayList<>();

    public Project(ProjectCreationDto dto) {
        this.adminId = dto.getOwnerId();
        this.name = dto.getProjectName();
        this.status = ProjectStatus.fromString(dto.getProjectStatus());
        this.tags = dto.getTags();
        this.mileStones = dto.getMilestones();
    }
}
