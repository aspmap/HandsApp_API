package run.itlife.handsapp_api.service;

import run.itlife.handsapp_api.dto.PostDto;
import run.itlife.handsapp_api.entity.Post;

import java.util.List;

//Интерфейс, отвечающий за логику создания постов, валидацию, изменение и т.д.
public interface PostService {

    List<Post> listAllPosts();
    List<PostDto> listAllPostsAsDto();
    List<PostDto> searchDtos(String search);
    List<Post> search(String search);
}
