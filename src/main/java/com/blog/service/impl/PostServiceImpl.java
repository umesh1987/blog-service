package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.dto.PostDto;
import com.blog.dto.PostResponse;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = mapEntity(postDto);
		Post newPost = postRepository.save(post);
		
		return maptoDto(newPost);
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pagesize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pagesize, sort);
		Page<Post> posts = postRepository.findAll(pageable);
		List<Post> listOfPosts = posts.getContent();
		List<PostDto> content = listOfPosts.stream().map(this::maptoDto).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalNumberOfRecords(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		return postResponse;
	}
	
	private Post mapEntity(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		return post;
		
	}
	
	private PostDto maptoDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setDescription(post.getDescription());
		postDto.setContent(post.getContent());
		return postDto;
	}

	@Override
	public PostDto getPostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		return maptoDto(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		// get post by id from database before update or edit post.
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post updatePost = postRepository.save(post);
		return maptoDto(updatePost);
	}

	@Override
	public void deletePostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		postRepository.delete(post);
	}

}
