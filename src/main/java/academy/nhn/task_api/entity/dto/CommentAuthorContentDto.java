package academy.nhn.task_api.entity.dto;

import academy.nhn.task_api.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentAuthorContentDto {
    private String authorId;
    private String content;
}
