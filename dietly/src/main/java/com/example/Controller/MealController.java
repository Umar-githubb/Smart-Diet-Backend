package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entities.Meal;
import com.example.service.MealService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/meal")
public class MealController {

	@Autowired
	public MealService mealService;
	
	@GetMapping
	public ResponseEntity<List<Meal>> getAllMealPlans(){
		return ResponseEntity.ok(mealService.getAllMealPlans());
	};
	
	@GetMapping("/{userId}")
	public ResponseEntity<Meal> getMealPlanByUserId(Long userId){
		return ResponseEntity.ok(mealService.getMealPlanByUserId(userId));
	};
	
	@DeleteMapping("/{mealId}")
	public void deleteMealPlan(@PathVariable Long mealId){
		mealService.deleteMealPlan(mealId);
		
	}; 
	
	@PostMapping
	public ResponseEntity<Meal> createMealPlan(@Valid @RequestBody Meal meal) {		
		return ResponseEntity.ok(mealService.createMealPlan(meal));
	};
	
	@PutMapping
	public ResponseEntity<Meal> updateMealPlan(@RequestBody Long id,Meal updatedmeal){
		return ResponseEntity.ok(mealService.updateMealPlan(id, updatedmeal));
	};
	
}
