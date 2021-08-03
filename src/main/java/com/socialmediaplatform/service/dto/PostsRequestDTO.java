package com.socialmediaplatform.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

/**
 * A DTO representing a Post, with its associated media.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostsRequestDTO {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 500)
    private String content;

    @Size(max = 10)
    private List<MultipartFile> media;

    public PostsRequestDTO() {}

    public PostsRequestDTO(String content, List<MultipartFile> media) {
        this.content = content;
        this.media = media;
    }

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

    public List<MultipartFile> getMedia() {
        return media;
    }

    public void setMedia(List<MultipartFile> media) {
        this.media = media;
    }
}
