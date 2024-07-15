package com.halmisae.repository.user;

import com.halmisae.entity.User.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
