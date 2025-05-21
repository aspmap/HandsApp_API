package run.itlife.handsapp_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import run.itlife.handsapp_api.dto.PostDto;
import run.itlife.handsapp_api.entity.Post;
import run.itlife.handsapp_api.service.PostService;

import java.util.ArrayList;

@RestController
public class PostController {
    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public ResponseEntity<PostDto> hello(Authentication authentication) {
        PostDto postDtoMock = new PostDto();
        postDtoMock.setPostId(1L);
        postDtoMock.setContent("Content");
        return new ResponseEntity<>(postDtoMock, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDto> findById(@PathVariable long id) {
        return new ResponseEntity<>(postService.getAsDto(id), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<ArrayList<Post>> findPostsByUsername(Authentication authentication) {
        return new ResponseEntity<>(postService.findPostsByUsername(authentication.getName()), HttpStatus.OK);
    }
}