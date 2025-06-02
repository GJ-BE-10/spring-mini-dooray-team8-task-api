package academy.nhn.task_api.repository;

import academy.nhn.task_api.entity.ProjectMember;
import academy.nhn.task_api.entity.dto.ProjectIdNameView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Integer> {
    List<Integer> findProjectIdByMemberId(String memberId);
}
