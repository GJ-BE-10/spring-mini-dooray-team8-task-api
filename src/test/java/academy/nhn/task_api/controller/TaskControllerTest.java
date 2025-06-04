package academy.nhn.task_api.controller;

import academy.nhn.task_api.entity.MileStone;
import academy.nhn.task_api.entity.Project;
import academy.nhn.task_api.entity.Tag;
import academy.nhn.task_api.entity.Task;
import academy.nhn.task_api.entity.dto.ProjectCreationDto;
import academy.nhn.task_api.entity.dto.TaskDto;
import academy.nhn.task_api.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class TaskControllerTest {

    @Autowired
    ProjectController projectController;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskController taskController;

    private TaskDto taskDto;
    ProjectCreationDto projectCreationDto;
    @BeforeEach
    void setup() {
        //----------------Project Create-----------------
        projectCreationDto = new ProjectCreationDto();
        projectCreationDto.setProjectName("Test Project");
        projectCreationDto.setOwnerId("admin");
        projectCreationDto.setProjectStatus("활성");

        // 태그 추가
        Tag tag1 = new Tag();
        tag1.setName("urgent");

        Tag tag2 = new Tag();
        tag2.setName("backend");

        projectCreationDto.setTags(List.of(tag1, tag2));

        // 마일스톤 추가
        MileStone milestone = new MileStone();
        milestone.setName("M1");
        milestone.setStartDate(LocalDate.of(2025, 6, 1));
        milestone.setEndDate(LocalDate.of(2025, 6, 30));

        projectCreationDto.setMilestones(List.of(milestone));
        projectController.createProject("admin", projectCreationDto);
        List<Project> projects = projectRepository.findAll();
        assertEquals(1, projects.size());

        Project project = projects.get(0);
        //----------------Project Create End-----------------

        taskDto = new TaskDto(project.getId(), project.getAdminId(), "admin_title", "admin_content", projectCreationDto.getTags(), projectCreationDto.getMilestones().get(0));


    }

    @Test
    void getTaskByTaskId() {
        Task task = taskController.createTask(taskDto.getAuthorId(), taskDto.getProjectId(), taskDto);

        TaskDto result = taskController.getTaskByTaskId(taskDto.getAuthorId(), taskDto.getProjectId(), task.getId());

        assertNotNull(result);

        assertEquals(taskDto.getProjectId(), result.getProjectId());
        assertEquals(taskDto.getAuthorId(), result.getAuthorId());
        assertEquals(taskDto.getTitle(), result.getTitle());
        assertEquals(taskDto.getContent(), result.getContent());
    }
}
