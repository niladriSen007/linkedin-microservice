package com.niladri.post_service.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostLikeEvent implements Serializable {
    Long postId;
    Long creatorId;
    Long likedByUserId;
}
