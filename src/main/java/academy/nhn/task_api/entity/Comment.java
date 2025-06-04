package academy.nhn.task_api.entity;

import academy.nhn.task_api.entity.dto.CommentAuthorContentDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int id;

    @Column(name = "comment_content", length = 100)
    private String content;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "author_id")
    private String authorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    //대댓글 필드 추가해야함. 어노테이션 manyToOne, oneToMany가 필요할 것 같은데 어떻게 해야할 지 모르겠음

    public Comment(Task task, CommentAuthorContentDto dto) {
        this.authorId = dto.getAuthorId();
        this.content = dto.getContent();
        this.task = task;
    }
}
