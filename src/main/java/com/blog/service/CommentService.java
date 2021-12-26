package com.blog.service;

import org.springframework.stereotype.Service;

import com.blog.dto.CommentDto;

@Service
public interface CommentService {
	
	CommentDto createComment(long postId, CommentDto commentDto);

}
