package academy.nhn.task_api.repository;

import academy.nhn.task_api.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Integer> {

    @Query("select pm.projectId from ProjectMember pm where pm.memberId = :memberId")
    List<Integer> findProjectIdByMemberId(@Param("memberId") String memberId);
}
