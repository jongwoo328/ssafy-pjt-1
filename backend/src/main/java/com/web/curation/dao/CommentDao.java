package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Comment;

@Mapper
public interface CommentDao {
	List<Comment> getComment(int boardno);
	int insert(Comment comment);
	int update(Comment comment);
	int delete(int commno);
}
