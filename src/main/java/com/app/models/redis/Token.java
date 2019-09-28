package com.app.models.redis;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@RedisHash("Token")
public class Token implements Serializable {
	private String id;
	private String jwtToken;
	private Integer expiredAfter;
	private Date createdAt;
	private Integer userId;
}