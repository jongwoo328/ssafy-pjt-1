package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.PayDAO;
import com.web.curation.dao.ReviewDAO;
import com.web.curation.model.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	ReviewDAO dao;
	
	
	@Override
	public List<Review> retrieveReview(int servno) {
		return dao.selectReview(servno);
	}

	@Override
	public Review detailReview(int revno) {
		return dao.selectReviewByNo(revno);
	}

	@Override
	public boolean writeReview(Review review) {
		return dao.insertReview(review) == 1;
	}

	@Override
	public boolean updateReview(Review review) {
		return dao.updateReview(review) == 1;
	}

	@Override
	public boolean deleteReview(int revno) {
		return dao.deleteReview(revno) == 1;
	}

	@Override
	public List<Review> totalReview() {
		return dao.totalReview();
	}

	@Override
	public Review checkReview(Review review) {
		return dao.checkReview(review);
	}

}
