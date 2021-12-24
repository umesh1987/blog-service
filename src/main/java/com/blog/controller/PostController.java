package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.PostDto;
import com.blog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/creatpost")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<PostDto> getAllPosts() {
		return postService.getAllPosts();
	}
	
	@GetMapping("/getpostbyId")
	public ResponseEntity<PostDto> getPostById(@RequestBody PostDto postDto) {
		return ResponseEntity.ok(postService.getPostById(postDto.getId()));
	}
	
	@PutMapping("/updatepost/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
		PostDto postResponse = postService.updatePost(postDto, id);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletepost")
	public ResponseEntity<String> deletePost(@RequestBody PostDto postDto) {
		postService.deletePostById(postDto.getId());
		return new ResponseEntity<>("Post entity delete successfully", HttpStatus.OK);
	}

}
