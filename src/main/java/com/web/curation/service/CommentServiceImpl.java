package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.CommentDao;
import com.web.curation.model.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao commentDao;
	
	@Override
	public List<Comment> getComment(int boardno) {
		return commentDao.getComment(boardno);
	}

	@Override
	public boolean insert(Comment comment) {
		return commentDao.insert(comment) == 1;
	}

	@Override
	public boolean update(Comment comment) {
		return commentDao.update(comment) == 1;
	}

	@Override
	public boolean delete(int commno) {
		return commentDao.delete(commno) == 1;
	}

}
