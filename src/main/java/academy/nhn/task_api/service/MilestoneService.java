package academy.nhn.task_api.service;

import academy.nhn.task_api.entity.MileStone;
import academy.nhn.task_api.entity.Task;
import academy.nhn.task_api.repository.MilestoneRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class MilestoneService {
    private final MilestoneRepository milestoneRepository;

    public MileStone findById(int id) {
        MileStone mileStone = milestoneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return mileStone;
    }

    public void saveMileStone(MileStone mileStone) {
        milestoneRepository.save(mileStone);
    }

    public void deleteMileStone(int id) {
        milestoneRepository.deleteById(id);
    }
}
