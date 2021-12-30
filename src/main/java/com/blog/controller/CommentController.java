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

import com.blog.dto.CommentDto;
import com.blog.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	public CommentService commentService;

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId, @RequestBody CommentDto commentDto){
		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/post/{postId}/comments")
	public List<CommentDto> getCommentByPostId(@PathVariable(value = "postId") long postId){
		return commentService.getCommentsByPostId(postId);
		
	}
	
	@GetMapping("/post/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentId) {
		CommentDto commentDto = commentService.getCommentById(postId, commentId);
		return new ResponseEntity<>(commentDto, HttpStatus.OK);
		
	}
	
	@PutMapping("/post/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long postId,
			@PathVariable(value = "commentId") long commentId, @RequestBody CommentDto commentDto) {
		
		CommentDto updateComment = commentService.updateComment(postId, commentId, commentDto);
		
		return new ResponseEntity<>(updateComment, HttpStatus.OK);

	}
	
	@DeleteMapping("/post/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentId) {
		commentService.deleteComment(postId, commentId);
		return new ResponseEntity<>("Comment ID " + commentId + " deleted successfully", HttpStatus.OK);
	}

}
