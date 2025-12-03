package com.example.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Meal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mealId;
	
	@NotEmpty
	private String mealPlan;
	
	@ManyToOne
	@JoinColumn(name="assigned_user_id")
	private User assignedTo;
	
	@ManyToOne
	@JoinColumn(name="dieticina_id")
	private User dietician;
	
	@Min(value = 100, message = "Calories should be at least 100")
	private int calories;

	public Meal(Long mealId, @NotEmpty String mealPlan, User assignedTo, User dietician, int calories) {
		super();
		this.mealId = mealId;
		this.mealPlan = mealPlan;
		this.assignedTo = assignedTo;
		this.dietician = dietician;
		this.calories = calories;
	}

	public Long getMealId() {
		return mealId;
	}

	public void setMealId(Long mealId) {
		this.mealId = mealId;
	}

	public String getMealPlan() {
		return mealPlan;
	}

	public void setMealPlan(String mealPlan) {
		this.mealPlan = mealPlan;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public User getDietician() {
		return dietician;
	}

	public void setDietician(User dietician) {
		this.dietician = dietician;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public Meal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}
