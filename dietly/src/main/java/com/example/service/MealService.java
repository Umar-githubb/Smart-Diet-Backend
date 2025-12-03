package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Entities.Meal;

@Service
public interface MealService {
	Meal createMealPlan(Meal meal);
	void deleteMealPlan(Long mealId);
	List<Meal> getAllMealPlans();
	Meal getMealPlanByUserId(Long userId);
	Meal updateMealPlan(Long id,Meal updatedmeal);
}
