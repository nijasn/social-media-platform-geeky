package com.socialmediaplatform.service.dto;

import com.socialmediaplatform.domain.User;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO representing a Post, with its associated media.
 */
public class PostsResponseDTO {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 500)
    private String content;

    @Size(max = 10)
    private List<String> media;

    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getMedia() {
        return media;
    }

    public void setMedia(List<String> media) {
        this.media = media;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
