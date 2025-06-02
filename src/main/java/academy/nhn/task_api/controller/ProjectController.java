package academy.nhn.task_api.controller;

import academy.nhn.task_api.entity.Project;
import academy.nhn.task_api.entity.dto.ProjectCreationDto;
import academy.nhn.task_api.entity.dto.ProjectIdNameView;
import academy.nhn.task_api.repository.ProjectMemberRepository;
import academy.nhn.task_api.repository.ProjectRepository;
import academy.nhn.task_api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectService projectService;

    @PostMapping("/{id}/projects/new")
    public void createProject(@PathVariable String id, @RequestBody ProjectCreationDto projectCreationDto) {
        Project project = new Project(projectCreationDto);
        projectService.save(project);
    }


    @GetMapping("/{id}/projects")
    public List<ProjectIdNameView> getProjectListByUserId(@PathVariable String id) {
        List<Integer> projectIdsById = projectMemberRepository.findProjectIdByMemberId(id);
        List<ProjectIdNameView> projectIdNameViews = projectRepository.findByIdIn(projectIdsById);
        return projectIdNameViews;
    }
}
