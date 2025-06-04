package academy.nhn.task_api.controller;

import academy.nhn.task_api.entity.MileStone;
import academy.nhn.task_api.entity.Project;
import academy.nhn.task_api.entity.Tag;
import academy.nhn.task_api.entity.dto.ProjectCreationDto;
import academy.nhn.task_api.entity.dto.ProjectIdNameView;
import academy.nhn.task_api.entity.dto.ProjectMainboardDto;
import academy.nhn.task_api.repository.ProjectRepository;
import academy.nhn.task_api.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ProjectControllerIntegrationTest {

    @Autowired
    private ProjectController projectController;

    @Autowired
    private ProjectRepository projectRepository;

    private ProjectCreationDto dto;

    @BeforeEach
    void setUp() {
        dto = new ProjectCreationDto();
        dto.setProjectName("Test Project");
        dto.setOwnerId("admin");
        dto.setProjectStatus("활성");

        // 태그 추가
        Tag tag1 = new Tag();
        tag1.setName("urgent");

        Tag tag2 = new Tag();
        tag2.setName("backend");

        dto.setTags(List.of(tag1, tag2));

        // 마일스톤 추가
        MileStone milestone = new MileStone();
        milestone.setName("M1");
        milestone.setStartDate(LocalDate.of(2025, 6, 1));
        milestone.setEndDate(LocalDate.of(2025, 6, 30));

        dto.setMilestones(List.of(milestone));
    }

    @Test
    void createProject_shouldSaveToDatabase() {
        // when
        projectController.createProject("admin", dto);

        // then
        List<Project> projects = projectRepository.findAll();
        assertEquals(1, projects.size());

        Project saved = projects.get(0);
        assertEquals("Test Project", saved.getName());
        assertEquals("admin", saved.getAdminId());
        assertEquals(2, saved.getTags().size());
        assertEquals(1, saved.getMileStones().size());
    }

    @Test
    void getProjectListByUserId_shouldReturnCorrectProjects() {
        // given
        projectController.createProject("admin", dto);

        // when
        List<ProjectIdNameView> result = projectController.getProjectListByUserId("admin");

        // then
        assertEquals(1, result.size());
        assertEquals("Test Project", result.get(0).getName());
    }

    @Test
    void getProjectMainboard_shouldReturnCorrectProjectMainboardDto() {
        // given
        projectController.createProject("admin", dto);

        Optional<Project> testProject = projectRepository.findByNameAndAdminId("Test Project", dto.getOwnerId());
        Project project = testProject.get();
        // when
        ProjectMainboardDto result = projectController.getProjectMainboard("admin", project.getId());

        assertEquals(project.getId(), result.getProjectId());
        assertEquals("admin", result.getAdminId());
        assertEquals("Test Project", result.getProjectName());
        assertEquals(2, result.getTags().size());
        assertEquals(1, result.getMileStones().size());
    }


}
