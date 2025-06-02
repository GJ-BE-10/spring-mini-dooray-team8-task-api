package academy.nhn.task_api.controller;

import academy.nhn.task_api.entity.Project;
import academy.nhn.task_api.entity.dto.ProjectCreationDto;
import academy.nhn.task_api.entity.dto.ProjectIdNameDto;
import academy.nhn.task_api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    @PostMapping("/{id}/projects/new")
    public void createProject(@PathVariable String id, @RequestBody ProjectCreationDto projectCreationDto) {
        Project project = new Project(projectCreationDto);
        projectService.save(project);
    }


    @GetMapping("/{id}/projects")
    public List<ProjectIdNameDto> getProjectListByUserId(@PathVariable String id) {

        // 유저아이디로 프로젝트-멤버 테이블에서 유저가 속한 프로젝트 아이디 가져오고, 그 프로젝트 아이디로 (프로젝트아이디,프로젝트명)프로젝션으로 가져와서 리스트에 담아서 반환하면 됨

    }
}
