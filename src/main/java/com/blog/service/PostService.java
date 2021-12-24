package com.blog.service;

import java.util.List;

import com.blog.dto.PostDto;

public interface PostService {
	PostDto createPost(PostDto postDto);
	
	List<PostDto> getAllPosts();
	
	PostDto getPostById(long id);
	
	PostDto updatePost(PostDto postDto, long id);
}
