package com.example.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entities.Meal;

public interface MealRepository extends JpaRepository<Meal, Long>{
	List<Meal> findByAssignedToId(Long userId);
}
