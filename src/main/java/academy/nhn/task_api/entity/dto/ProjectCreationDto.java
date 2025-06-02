package academy.nhn.task_api.entity.dto;

import academy.nhn.task_api.entity.MileStone;
import academy.nhn.task_api.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreationDto {
    private String projectName;
    private String ownerId;
    private String projectStatus;
    private List<Tag> tags;
    private List<MileStone> milestones;
}
