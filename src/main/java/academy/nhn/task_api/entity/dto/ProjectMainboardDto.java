package academy.nhn.task_api.entity.dto;

import academy.nhn.task_api.entity.MileStone;
import academy.nhn.task_api.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectMainboardDto {
    private int projectId;
    private String adminId;
    private String projectName;
    private String ProjectStatus;
    private List<TaskIdTitleView> taskIdTitleViews;
    private List<Tag> tags;
    private List<MileStone> mileStones;

}
