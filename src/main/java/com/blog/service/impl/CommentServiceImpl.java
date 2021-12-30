package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.CommentDto;
import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	public CommentRepository commentRepository;
	
	@Autowired
	public PostRepository postRepository;

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		Comment comment = mapToEntity(commentDto);
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		comment.setPost(post);
		Comment newComment = commentRepository.save(comment);
		return mapToDto(newComment);
	}
	
	private CommentDto mapToDto(Comment comment) {
		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setBody(comment.getBody());
		commentDto.setEmail(comment.getEmail());
		commentDto.setName(comment.getName());
		return commentDto;
	}
	
	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment = new Comment();
		comment.setId(commentDto.getId());
		comment.setBody(commentDto.getBody());
		comment.setEmail(commentDto.getEmail());
		comment.setName(commentDto.getName());
		return comment;
	}

	@Override
	public List<CommentDto> getCommentsByPostId(long postId) {
		List<Comment> comments = commentRepository.findByPostId(postId);
		return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
	}

}
