package academy.nhn.task_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MileStone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mileStone_id")
    private int id;

    @Column(name = "mileStone_name")
    private String name;

    @Column(name = "mileStone_start_date")
    private LocalDate startDate;

    @Column(name = "mileStone_end_date")
    private LocalDate endDate;

    @ManyToOne
    private Project projects;
}
