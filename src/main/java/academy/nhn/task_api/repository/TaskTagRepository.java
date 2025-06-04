package academy.nhn.task_api.repository;

import academy.nhn.task_api.entity.Tag;
import academy.nhn.task_api.entity.Task;
import academy.nhn.task_api.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, Integer> {
    void deleteByTaskAndTag(Task task, Tag tag);

    void deleteAllByTag(Tag tag);
}
