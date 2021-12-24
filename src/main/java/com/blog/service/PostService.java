package com.blog.service;

import java.util.List;

import com.blog.dto.PostDto;
import com.blog.dto.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto);
	
	PostResponse getAllPosts(int pageNo, int pageSize);
	
	PostDto getPostById(long id);
	
	PostDto updatePost(PostDto postDto, long id);
	
	void deletePostById(long id);
}
