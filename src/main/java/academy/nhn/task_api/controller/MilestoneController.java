package academy.nhn.task_api.controller;

import academy.nhn.task_api.entity.MileStone;
import academy.nhn.task_api.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping("/{userId}/projects/{projectId}/milestones/{mileStoneId}")
    public MileStone getMileStoneById(@PathVariable String userId, @PathVariable int projectId, @PathVariable int mileStoneId) {
        MileStone mileStone = milestoneService.findById(mileStoneId);
        return mileStone;
    }

    @PutMapping("/{userId}/projects/{projectId}/milestones/{mileStoneId}")
    public ResponseEntity<Void> updateMilestone(@PathVariable String userId, @PathVariable int projectId, @PathVariable int mileStoneId, @RequestBody MileStone mileStone) {
        MileStone oldMileStone = milestoneService.findById(mileStone.getId());
        milestoneService.saveMileStone(mileStone);

        return ResponseEntity.noContent().build(); // 204 No Content 응답
    }

    @DeleteMapping("/{userId}/projects/{projectId}/milestones/{mileStoneId}")
    public void deleteMileStone(@PathVariable String userId, @PathVariable int projectId, @PathVariable int mileStoneId) {
        milestoneService.deleteMileStone(mileStoneId);
    }
}
