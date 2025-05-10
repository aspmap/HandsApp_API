package run.itlife.handsapp_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import run.itlife.handsapp_api.dto.PostDto;
import run.itlife.handsapp_api.entity.Post;
import run.itlife.handsapp_api.repository.PostRepository;
import run.itlife.handsapp_api.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;


// Уровень обслуживания
// Класс, реализующий интерфейс, который отвечает за логику создания постов, валидацию, изменение и т.д.
@Service
@Transactional
public class PostServiceImpl implements PostService {

    // сервисы в свою очередь включают репозиторий
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> listAllPosts() {
        List<Post> posts =  postRepository.findAll(Sort.by("createdAt").descending());
        return posts;
    }

    @Override
    public List<PostDto> listAllPostsAsDto() {
        return toDtoList(listAllPosts());
    }

    private List<PostDto> toDtoList(List<Post> posts) {
        return posts.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
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
        /////
        return dto;
    }

    @Override
    public List<PostDto> searchDtos(String search) {
        return toDtoList(search(search));
    }

    @Override
    public List<Post> search(String search) {
        return postRepository.findByContentLikeIgnoreCase("%" + search +"%");
    }


}