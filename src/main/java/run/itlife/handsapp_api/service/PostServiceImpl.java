package run.itlife.handsapp_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.itlife.handsapp_api.dto.PostDto;
import run.itlife.handsapp_api.entity.Post;
import run.itlife.handsapp_api.repository.PostRepository;

// Уровень обслуживания
// Класс, реализующий интерфейс, который отвечает за логику создания постов, валидацию, изменение и т.д.
@Service
@Transactional
public class PostServiceImpl implements PostService {
    // сервисы в свою очередь включают репозиторий
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto getAsDto(long postId) {
        return toDto(postRepository.findById(postId).orElseThrow());
    }

    private PostDto toDto(Post post) {
        PostDto dto = new PostDto();
        dto.setPostId(post.getPostId());
        dto.setPhoto(post.getPhoto());
        dto.setContent(post.getContent());
        dto.setExtFile(post.getExtFile());
        dto.setUsername(post.getUser().getUsername());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        return dto;
    }
}