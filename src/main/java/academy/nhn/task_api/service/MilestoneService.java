package academy.nhn.task_api.service;

import academy.nhn.task_api.entity.MileStone;
import academy.nhn.task_api.entity.Task;
import academy.nhn.task_api.repository.MilestoneRepository;
import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
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

    public void editMileStone(MileStone mileStone) {
        MileStone existingMilestone = findById(mileStone.getId());
        existingMilestone.setName(mileStone.getName());
        existingMilestone.setStartDate(mileStone.getStartDate());
        existingMilestone.setEndDate(mileStone.getEndDate());
    }
}
