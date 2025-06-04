package academy.nhn.task_api.service;

import academy.nhn.task_api.entity.Project;
import academy.nhn.task_api.entity.ProjectMember;
import academy.nhn.task_api.repository.ProjectMemberRepository;
import academy.nhn.task_api.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public void save(Project project) {
        projectRepository.save(project);
        projectMemberRepository.save(new ProjectMember(project.getId(), project.getAdminId()));
    }

    public Project findById(int id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("project not found"));
    }
}
