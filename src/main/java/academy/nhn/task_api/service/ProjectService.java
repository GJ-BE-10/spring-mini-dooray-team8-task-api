package academy.nhn.task_api.service;

import academy.nhn.task_api.entity.Project;
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

    public void save(Project project) {
        projectRepository.save(project);
    }

    public Project findById(int id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("project not found"));
    }
}
