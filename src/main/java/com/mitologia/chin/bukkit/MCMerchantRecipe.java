package com.mitologia.chin.bukkit;

import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.abstraction.MCRecipe;

import java.util.List;

public interface MCMerchantRecipe extends MCRecipe {

	void addIngredient(MCItemStack item);

	List<MCItemStack> getIngredients();

	int getMaxUses();

	MCItemStack getResult();

	int getUses();

	boolean hasExperienceReward();

	void removeIngredient(int index);

	void setExperienceReward(boolean flag);

	void setIngredients(List<MCItemStack> ingredients);

	void setMaxUses(int maxUses);

	void setUses(int uses);

}
