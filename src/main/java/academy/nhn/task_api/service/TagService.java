package academy.nhn.task_api.service;

import academy.nhn.task_api.entity.Tag;
import academy.nhn.task_api.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Tag findById(int id) {
        return tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    public void deleteTag(int id) {
        tagRepository.deleteById(id);
    }
}
