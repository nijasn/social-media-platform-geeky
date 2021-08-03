package com.socialmediaplatform.web.rest;

import com.socialmediaplatform.domain.Posts;
import com.socialmediaplatform.domain.User;
import com.socialmediaplatform.service.PostsService;
import com.socialmediaplatform.service.dto.PostsRequestDTO;
import com.socialmediaplatform.service.dto.PostsResponseDTO;
import com.socialmediaplatform.service.mapper.PostsMapper;
import com.socialmediaplatform.web.rest.base.BaseController;
import com.socialmediaplatform.web.rest.vm.Page;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller to handle Posts.
 */
@RestController
@RequestMapping("/api")
public class PostsController extends BaseController {

    @Autowired
    private PostsMapper postsMapper;

    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/posts/all")
    public ResponseEntity<List<PostsResponseDTO>> getAllPosts() {
        List<Posts> posts = this.postsService.getAllPosts();
        List<PostsResponseDTO> response = posts.stream().map(this.postsMapper::postToPostsResponseDto).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostsResponseDTO>> getUserPosts() {
        User activeUser = this.getActiveUser();
        List<Posts> posts = this.postsService.getPosts(activeUser);
        List<PostsResponseDTO> response = posts.stream().map(this.postsMapper::postToPostsResponseDto).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/posts", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<PostsResponseDTO> saveUserPosts(
        @RequestPart("content") String content,
        @RequestPart(value = "media", required = false) List<MultipartFile> medias
    ) {
        User activeUser = this.getActiveUser();
        PostsRequestDTO postsRequestDTO = new PostsRequestDTO(content, medias);
        Posts posts = this.postsService.createPost(postsRequestDTO, activeUser);
        return new ResponseEntity<>(this.postsMapper.postToPostsResponseDto(posts), HttpStatus.OK);
    }
}
