package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Review;

public interface ReviewService {
	public List<Review> retrieveReview();
	public Review detailReview(int revno);
	public boolean writeReview(Review review);
	public boolean updateReview(Review review);
	public boolean deleteReview(int revno);
}
