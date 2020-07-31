package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Review;

@Mapper
public interface ReviewDAO {
	public List<Review> selectReview();
	public Review selectReviewByNo (int revno);
	public int insertReview(Review review);
	public int updateReview(Review review);
	public int deleteReview(int revno);
}