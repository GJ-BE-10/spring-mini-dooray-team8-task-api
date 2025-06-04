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
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreationDto {
    private String title;
    private String content;
    private String authorId;
    private List<Tag> tags;
    private MileStone mileStone;
    private int projectId;
}
