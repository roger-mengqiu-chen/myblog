package com.myblog.myblog.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommentRequest {
    @JsonProperty
    private Integer postId;
    @JsonProperty
    private Long pId;
    @JsonProperty
    private String content;
}
