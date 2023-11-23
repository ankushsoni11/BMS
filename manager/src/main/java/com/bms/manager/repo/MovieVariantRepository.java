package com.bms.manager.repo;


import com.bms.manager.entity.MovieVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieVariantRepository extends JpaRepository<MovieVariant, Integer> {
}
