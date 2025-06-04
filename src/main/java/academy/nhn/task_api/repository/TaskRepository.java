package academy.nhn.task_api.repository;

import academy.nhn.task_api.entity.Task;
import academy.nhn.task_api.entity.dto.TaskIdTitleView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<TaskIdTitleView> findAllByProjectId(int projectId);

    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.taskTags WHERE t.id = :taskId")
    Optional<Task> findByIdWithTags(@Param("taskId") int taskId);

}
