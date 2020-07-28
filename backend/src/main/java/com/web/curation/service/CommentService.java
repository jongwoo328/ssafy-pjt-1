package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Comment;

public interface CommentService {
	List<Comment> getComment(int boardno);
	boolean insert(Comment comment);
	boolean update(Comment comment);
	boolean delete(int commno);
}
