package academy.nhn.task_api.service;

import academy.nhn.task_api.entity.Tag;
import academy.nhn.task_api.repository.TagRepository;
import academy.nhn.task_api.repository.TaskTagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {
    private final TaskTagRepository taskTagRepository;
    private final TagRepository tagRepository;

    public Tag findById(int id) {
        return tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    public void editTag(Tag tag) {
        Tag existingTag = findById(tag.getId());
        existingTag.setName(tag.getName());
    }

    public void deleteTag(int id) {
        Tag tag = findById(id);
        taskTagRepository.deleteAllByTag(tag);

        tagRepository.deleteById(id);
    }
}
