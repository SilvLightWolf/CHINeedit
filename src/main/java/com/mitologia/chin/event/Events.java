package com.mitologia.chin.event;

import com.laytonsmith.PureUtilities.Vector3D;
import com.laytonsmith.abstraction.*;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.blocks.MCBlockFace;
import com.laytonsmith.abstraction.blocks.MCMaterial;
import com.laytonsmith.abstraction.entities.MCPigZombie;
import com.laytonsmith.abstraction.entities.MCSheep;
import com.laytonsmith.abstraction.entities.MCSlime;
import com.laytonsmith.abstraction.entities.MCVillager;
import com.laytonsmith.abstraction.enums.MCDyeColor;
import com.laytonsmith.abstraction.enums.MCEntityType;
import com.laytonsmith.abstraction.events.MCInventoryEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.constructs.CInt;
import com.mitologia.chin.bukkit.MCMapView;
import com.mitologia.chin.bukkit.MCMerchantRecipe;
import org.bukkit.NamespacedKey;
import org.bukkit.Statistic;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.EntityType;

import java.util.List;


public class Events {

    public interface MCItemDamageEvent extends BindableEvent {

        CInt getDamage();

        MCItemStack getItem();

        MCPlayer getPlayer();

        boolean isCancelled();

        void setCancelled(boolean cancel);

        void setDamage(int damage);

    }

    public interface MCPlayerRecipeDiscoverEvent extends BindableEvent {

        NamespacedKey getRecipe();

        MCPlayer getPlayer();

        boolean isCancelled();

        void setCancelled(boolean cancel);

    }

    public interface MCBlockPhysicsEvent extends BindableEvent {

        MCMaterial getChangedType();

        MCBlock getBlock();

        boolean isCancelled();

        void setCancelled(boolean cancel);

    }

    public interface MCEntityDropItemEvent extends BindableEvent {

        MCItem getItemDrop();

        MCEntity getEntity();

        boolean isCancelled();

        void setCancelled(boolean cancelled);

    }

    public interface MCEntityToggleSwimEvent extends BindableEvent {

        boolean isSwimming();

        MCEntity getEntity();

        boolean isCancelled();

        void setCancelled(boolean cancelled);

    }

    public interface MCPigZombieAngerEvent extends BindableEvent {

        MCPigZombie getEntity();

        CInt getNewAnger();

        MCEntity getTarget();

        boolean isCancelled();

        void setCancelled(boolean cancelled);

        void setNewAnger(int newAnger);

    }

    public interface MCPlayerRiptideEvent extends BindableEvent {

        MCItemStack getItem();

        MCPlayer getPlayer();

    }

    public interface MCMapInitializeEvent extends BindableEvent {

        MCMapView getMap();

    }

    public interface MCAreaEffectCloudApplyEvent extends BindableEvent {

        List<MCLivingEntity> getAffectedEntities();

        MCEntity getEntity();

    }

    public interface MCBrewEvent extends BindableEvent {

        MCInventory getContents();

        int getFuelLevel();

        MCBlock getBlock();

        boolean isCancelled();

        void setCancelled(boolean cancelled);

    }

    public interface MCBrewingStandFuelEvent extends BindableEvent {

        MCItemStack getFuel();

        int getFuelPower();

        MCBlock getBlock();

        boolean isConsuming();

        boolean isCancelled();

        void setFuelPower(int fuelPower);

        void setConsuming(boolean consuming);

        void setCancelled(boolean cancel);


    }

    public interface MCCauldronLevelChangeEvent extends BindableEvent {

        MCEntity getEntity();

        int getNewLevel();

        int getOldLevel();

        String getReason();

        MCBlock getBlock();

        boolean isCancelled();

        void setCancelled(boolean cancelled);

        void setNewLevel(int newLevel);

    }

    public interface MCEggThrowEvent extends BindableEvent {

        MCEgg getEgg();

        MCPlayer getPlayer();

        MCEntityType getHatchingType();

        byte getNumHatches();

        boolean isHatching();

        void setHatching(boolean hatching);

        void setHatchingType(MCEntityType hatchingType);

        void setNumHatches(byte numHatches);

    }

    public interface MCEntityAirChangeEvent extends BindableEvent {

        int getAmount();

        MCEntity getEntity();

        boolean isCancelled();

        void setAmount(int amount);

        void setCancelled(boolean cancelled);

    }

    public interface MCEntityBreedEvent extends BindableEvent {

        MCItemStack getBredWith();

        MCLivingEntity getBreeder();

        MCLivingEntity getEntity();

        int getExperience();

        MCLivingEntity getFather();

        MCLivingEntity getMother();

        boolean isCancelled();

        void setCancelled(boolean cancelled);

        void setExperience(int experience);

    }

    public interface MCEntityResurrectEvent extends BindableEvent {

        MCLivingEntity getEntity();

        boolean isCancelled();

        void setCancelled(boolean cancelled);

    }

    public interface MCEntityShootBowEvent extends BindableEvent {

        MCItemStack getBow();

        MCEntity getEntity();

        float getForce();

        MCEntity getProjectile();

        boolean isCancelled();

        void setCancelled(boolean cancelled);

        void setProjectile(MCEntity projectile);

    }

    public interface MCEntityTameEvent extends BindableEvent {

        MCLivingEntity getEntity();

        MCAnimalTamer getOwner();

        boolean isCancelled();

        void setCancelled(boolean cancelled);

    }

    public interface MCFurnaceBurnEvent extends BindableEvent {

        int getBurnTine();

        MCItemStack getFuel();

        MCBlock getBlock();

        boolean isBurning();

        boolean isCancelled();

        void setBurning(boolean burning);

        void setBurnTime(int burnTime);

        void setCancelled(boolean cancel);

    }

    public interface MCFurnaceExtractEvent extends BindableEvent {

        int getExpToDrop();

        int getItemAmount();

        MCMaterial getItemType();

        MCPlayer getPlayer();

        MCBlock getBlock();

        void setExpToDrop(int exp);

    }

    public interface MCFurnaceSmeltEvent extends BindableEvent {

        MCItemStack getResult();

        MCItemStack getSource();

        MCBlock getBlock();

        boolean isCancelled();

        void setCancelled(boolean cancel);

        void setResult(MCItemStack result);

    }

    public interface MCItemMergeEvent extends BindableEvent {

        MCItem getEntity();

        MCItem getTarget();

        boolean isCancelled();

        void setCancelled(boolean cancelled);

    }

    public interface MCLeavesDecayEvent extends BindableEvent {

        MCBlock getBlock();

        boolean isCancelled();

        void setCancelled(boolean cancel);

    }

    public interface MCLocaleChangeEvent extends BindableEvent {

        String getLocale();

        MCPlayer getPlayer();

    }

    public interface MCPlayerBucketEvent extends BindableEvent {

        MCBlock getBlockClicked();

        MCBlockFace getBlockFace();

        MCMaterial getBucket();

        MCItemStack getItemStack();

        MCPlayer getPlayer();

        boolean isCancelled();

        void setCancelled(boolean cancel);

        void setItemStack(MCItemStack is);

    }

    public interface MCPlayerResourcepackStatusEvent extends BindableEvent {

        MCPlayer getPlayer();

        String getStatus();

    }

    public interface MCPlayerStatisticIncrementEvent extends BindableEvent {

        EntityType getEntityType();

        Object getMaterial();

        CInt getPreviousValue();

        CInt getNewValue();

        Statistic getStatistic();

        MCPlayer getPlayer();

        boolean isCancelled();

        void setCancelled(boolean cancel);

    }

    public interface MCPlayerVelocityEvent extends BindableEvent {

        Vector3D getVelocity();

        MCPlayer getPlayer();

        boolean isCancelled();

        void setCancelled(boolean cancel);

        void setVelocity(Vector3D velocity);

    }

    public interface MCPrepareAnvilEvent extends MCInventoryEvent {

        MCItemStack getResult();

        int getCost();

        String getRenameText();

        void setResult(MCItemStack result);

        void setCost(int cost);

    }

    public interface MCSheepDyeWoolEvent extends BindableEvent {

        MCDyeColor getColor();

        MCSheep getEntity();

        boolean isCancelled();

        void setCancelled(boolean cancelled);

        void setColor(MCDyeColor color);

    }

    public interface MCSheepRegrowWoolEvent extends BindableEvent {

        MCSheep getEnity();

        boolean isCancelled();

        void setCancelled(boolean cancelled);

    }

    public interface MCSlimeSplitEvent extends BindableEvent {

        int getCount();

        MCSlime getEntity();

        boolean isCancelled();

        void setCancelled(boolean cancelled);

        void setCount(int count);

    }

    public interface MCVillagerAcquireTradeEvent extends BindableEvent {

        MCVillager getEntity();

        MCMerchantRecipe getRecipe();

        boolean isCancelled();

        void setCancelled(boolean cancelled);

        void setRecipe(MCMerchantRecipe recipe);

    }

    public interface MCVillagerReplenishTradeEvent extends BindableEvent {

        CInt getBonus();

        MCVillager getEntity();

        MCMerchantRecipe getRecipe();

        boolean isCancelled();

        void setBonus(int bonus);

        void setCancelled(boolean cancelled);

        void setRecipe(MCMerchantRecipe recipe);

    }

    public interface MCCreeperPowerEvent extends BindableEvent  {

        String getCause();

        MCEntity getEntity();

        MCLightningStrike getLightning();

        boolean isCancelled();

        void setCancelled(boolean cancelled);

    }

    public interface MCItemBreakEvent extends BindableEvent {

        MCItemStack getBrokenItem();

        MCPlayer getPlayer();

    }

    public interface MCPlayerAdvancementDoneEvent extends BindableEvent {

        Advancement getAdvancement();

        MCPlayer getPlayer();

    }


}
