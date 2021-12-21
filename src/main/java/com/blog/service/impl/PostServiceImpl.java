package com.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.PostDto;
import com.blog.entity.Post;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		Post newPost = postRepository.save(post);
		
		PostDto postResponse = new PostDto();
		postResponse.setId(newPost.getId());
		postResponse.setDescription(newPost.getDescription());
		postResponse.setTitle(newPost.getTitle());
		postResponse.setContent(newPost.getContent());
		
		return postResponse;
	}

}
