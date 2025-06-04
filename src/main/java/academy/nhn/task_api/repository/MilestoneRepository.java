package academy.nhn.task_api.repository;

import academy.nhn.task_api.entity.MileStone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<MileStone, Integer> {

}
