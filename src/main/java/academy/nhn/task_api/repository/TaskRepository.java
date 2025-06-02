package academy.nhn.task_api.repository;

import academy.nhn.task_api.entity.Task;
import academy.nhn.task_api.entity.dto.TaskIdTitleView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<TaskIdTitleView> findAllByProjectId(int projectId);
}
