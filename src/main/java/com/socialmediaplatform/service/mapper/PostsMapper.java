package com.socialmediaplatform.service.mapper;

import com.socialmediaplatform.domain.Authority;
import com.socialmediaplatform.domain.Media;
import com.socialmediaplatform.domain.Posts;
import com.socialmediaplatform.domain.User;
import com.socialmediaplatform.service.dto.AdminUserDTO;
import com.socialmediaplatform.service.dto.PostsRequestDTO;
import com.socialmediaplatform.service.dto.PostsResponseDTO;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Mapper for the entity {@link com.socialmediaplatform.domain.Posts} and its DTO called {@link com.socialmediaplatform.service.dto.PostsResponseDTO}.
 *
 */
@Service
public class PostsMapper {

    @Autowired
    private UserMapper userMapper;

    public PostsResponseDTO postToPostsResponseDto(Posts post) {
        if (post == null) {
            return null;
        } else {
            PostsResponseDTO postsResponseDTO = new PostsResponseDTO();
            postsResponseDTO.setContent(post.getContent());
            postsResponseDTO.setId(post.getId());
            if (post.getMedias() != null) {
                postsResponseDTO.setMedia(post.getMedias().stream().map(this::mediaToString).collect(Collectors.toList()));
            }
            postsResponseDTO.setUser(this.userMapper.userToUserDTO(post.getUser()));
            return postsResponseDTO;
        }
    }

    private String mediaToString(Media media) {
        return media.getContent();
    }
}
