package com.bookrealm.favorite.repository;

import com.bookrealm.favorite.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {
}
