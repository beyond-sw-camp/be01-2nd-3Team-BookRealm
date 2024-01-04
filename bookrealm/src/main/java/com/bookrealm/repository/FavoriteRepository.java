package com.bookrealm.repository;

import com.bookrealm.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {
}
