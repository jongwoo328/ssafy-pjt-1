package com.web.curation.dao.board;

import java.util.List;

import com.web.curation.model.Comment;

public interface CommentDao {
	List<Comment> getComment(int boardno);
	int insert(Comment comment);
	int update(Comment comment);
	int delete(int commno);
}
