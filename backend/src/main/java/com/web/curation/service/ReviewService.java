package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Review;

public interface ReviewService {
	public List<Review> totalReview();
	public List<Review> retrieveReview(int servno);
	public Review detailReview(int revno);
	public Review checkReview(Review review);
	public boolean writeReview(Review review);
	public boolean updateReview(Review review);
	public boolean deleteReview(int revno);

}
