package run.itlife.handsapp_api.service;

import run.itlife.handsapp_api.dto.PostDto;
import run.itlife.handsapp_api.entity.Post;

import java.util.ArrayList;

//Интерфейс, отвечающий за логику создания постов, валидацию, изменение и т.д.
public interface PostService {
    PostDto getAsDto(long postId);
    ArrayList<Post> findPostsByUsername(String username);
}
