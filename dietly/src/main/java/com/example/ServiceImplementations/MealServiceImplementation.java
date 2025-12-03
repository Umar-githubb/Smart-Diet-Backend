package com.example.ServiceImplementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entities.Meal;
import com.example.Repositories.MealRepository;
import com.example.service.MealService;

@Service
public class MealServiceImplementation implements MealService{
	@Autowired
	public MealRepository mealRepo;
	
	@Override
	public Meal createMealPlan(Meal meal) {
		return mealRepo.save(meal);
	};
	
	@Override
	public void deleteMealPlan(Long mealId) {
		mealRepo.deleteById(mealId);
	};
	
	@Override
	public List<Meal> getAllMealPlans(){
		return mealRepo.findAll();
	};
	
	@Override
	public Meal getMealPlanByUserId(Long userId){
		return mealRepo.findById(userId)
				.orElseThrow(() -> new RuntimeException("Meal Plan Not Found for this userId" + userId));
	};
	
	@Override
	public Meal updateMealPlan(Long userId,Meal updatedMeal) {
		Meal existing = mealRepo.findById(userId)
				.orElseThrow(() -> new RuntimeException("Meal not Found for this id" + userId));
		
		existing.setMealPlan(updatedMeal.getMealPlan());
		existing.setCalories(updatedMeal.getCalories());
		existing.setDietician(updatedMeal.getDietician());
		
		return mealRepo.save(existing);
		
	};
}
