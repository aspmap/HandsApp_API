package run.itlife.handsapp_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import run.itlife.handsapp_api.dto.PostDto;
import run.itlife.handsapp_api.service.PostService;

@RestController
public class HelloController {
    private final PostService postService;
    @Autowired
    public HelloController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String hello(Authentication authentication) {
        return "Hello, " + authentication.getName() + "!";
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDto> findById(@PathVariable long id) {
        return new ResponseEntity<>(postService.getAsDto(id), HttpStatus.OK);
    }
}