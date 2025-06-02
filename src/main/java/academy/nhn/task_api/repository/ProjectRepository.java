package academy.nhn.task_api.repository;

import academy.nhn.task_api.entity.Project;
import academy.nhn.task_api.entity.dto.ProjectIdNameView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<ProjectIdNameView> findByIdIn(List<Integer> projectIds);
}
