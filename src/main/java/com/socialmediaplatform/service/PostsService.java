package com.socialmediaplatform.service;

import com.socialmediaplatform.domain.Media;
import com.socialmediaplatform.domain.Posts;
import com.socialmediaplatform.domain.User;
import com.socialmediaplatform.repository.MediaRepository;
import com.socialmediaplatform.repository.PostsRepository;
import com.socialmediaplatform.service.dto.PostsRequestDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service class for managing posts.
 */
@Service
@Transactional
public class PostsService {

    private final Logger log = LoggerFactory.getLogger(PostsService.class);

    private final PostsRepository postsRepository;

    private final MediaRepository mediaRepository;

    private final AwsS3Service awsS3Service;

    public PostsService(PostsRepository postsRepository, MediaRepository mediaRepository, AwsS3Service awsS3Service) {
        this.postsRepository = postsRepository;
        this.mediaRepository = mediaRepository;
        this.awsS3Service = awsS3Service;
    }

    public List<Posts> getAllPosts() {
        // TODO: to apply pagination in the query
        log.info("GET all posts");
        return this.postsRepository.findAll();
    }

    public List<Posts> getPosts(User user) {
        log.info("GET user posts");
        List<Posts> posts = this.postsRepository.findPostsByUser(user);
        return posts;
    }

    public Posts getPostsById(Long postId) {
        log.info("GET user posts");
        Posts posts = this.postsRepository.findById(postId).get();
        return posts;
    }

    public Posts createPost(PostsRequestDTO postsRequestDTO, User user) {
        log.info("Create user posts");
        Posts posts = new Posts();
        posts.setUser(user);
        posts.setContent(postsRequestDTO.getContent());
        Posts savedPosts = this.postsRepository.save(posts);
        List<MultipartFile> medias = postsRequestDTO.getMedia();
        if (medias != null) {
            List<String> s3Urls = medias.stream().map(this.awsS3Service::saveMultipartFiletoS3).collect(Collectors.toList());
            List<Media> savedMedias = new ArrayList<>();
            for (String url : s3Urls) {
                Media media = new Media(url);
                media.setPost(savedPosts);
                savedMedias.add(this.mediaRepository.save(media));
            }
            savedPosts.setMedias(savedMedias);
        }
        return savedPosts;
    }
}
