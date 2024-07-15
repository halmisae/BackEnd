package com.halmisae.repository.user;

import com.halmisae.entity.User.Favorite;
import com.halmisae.entity.User.FavoriteID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteID> {
}
