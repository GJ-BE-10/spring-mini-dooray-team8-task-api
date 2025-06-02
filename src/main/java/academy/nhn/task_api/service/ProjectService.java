package academy.nhn.task_api.service;

import academy.nhn.task_api.entity.Project;
import academy.nhn.task_api.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public void save(Project project) {
        projectRepository.save(project);
    }
}
