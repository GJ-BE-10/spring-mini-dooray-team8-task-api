package academy.nhn.task_api.controller;

import academy.nhn.task_api.entity.Project;
import academy.nhn.task_api.entity.dto.ProjectCreationDto;
import academy.nhn.task_api.entity.dto.ProjectIdNameView;
import academy.nhn.task_api.entity.dto.ProjectMainboardDto;
import academy.nhn.task_api.entity.dto.TaskIdTitleView;
import academy.nhn.task_api.exception.InvalidAccessException;
import academy.nhn.task_api.repository.ProjectMemberRepository;
import academy.nhn.task_api.repository.ProjectRepository;
import academy.nhn.task_api.repository.TaskRepository;
import academy.nhn.task_api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final TaskRepository taskRepository;
    private final ProjectService projectService;

    @PostMapping("/{id}/projects/new")
    public void createProject(@PathVariable String id, @RequestBody ProjectCreationDto projectCreationDto) {
        Project project = new Project(projectCreationDto);
        projectService.save(project);
    }


    @GetMapping("/{userId}/projects")
    public List<ProjectIdNameView> getProjectListByUserId(@PathVariable String userId) {
        List<Integer> projectIdsById = projectMemberRepository.findProjectIdByMemberId(userId);
        List<ProjectIdNameView> projectIdNameViews = projectRepository.findByIdIn(projectIdsById);
        return projectIdNameViews;
    }

    @GetMapping("/{userId}/projects/{projectId}")
    public ProjectMainboardDto getProjectMainboard(@PathVariable String userId, @PathVariable int projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isEmpty()) {
            throw new InvalidAccessException();
        }
        Project project = projectOptional.get();
        List<TaskIdTitleView> taskIdTitleViews = taskRepository.findAllByProjectId(project.getId());
        ProjectMainboardDto projectMainboardDto = new ProjectMainboardDto(  project.getId(),
                                                                            project.getAdminId(),
                                                                            project.getName(),
                                                                            project.getStatus().getStatusName(),
                                                                            taskIdTitleViews,
                                                                            project.getTags(),
                                                                            project.getMileStones());
        return projectMainboardDto;
    }
}
