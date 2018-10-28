package com.mitologia.chin.abstraction;

import com.laytonsmith.PureUtilities.Vector3D;
import com.laytonsmith.abstraction.*;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.blocks.MCBlockFace;
import com.laytonsmith.abstraction.blocks.MCMaterial;
import com.laytonsmith.abstraction.bukkit.BukkitMCAnimalTamer;
import com.laytonsmith.abstraction.bukkit.BukkitMCInventory;
import com.laytonsmith.abstraction.bukkit.BukkitMCItemStack;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCBlock;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCMaterial;
import com.laytonsmith.abstraction.bukkit.entities.*;
import com.laytonsmith.abstraction.bukkit.events.BukkitInventoryEvents;
import com.laytonsmith.abstraction.entities.MCPigZombie;
import com.laytonsmith.abstraction.entities.MCSheep;
import com.laytonsmith.abstraction.entities.MCSlime;
import com.laytonsmith.abstraction.entities.MCVillager;
import com.laytonsmith.abstraction.enums.MCDyeColor;
import com.laytonsmith.abstraction.enums.MCEntityType;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCEntityType;
import com.laytonsmith.annotations.abstraction;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.constructs.CNull;
import com.laytonsmith.core.constructs.Target;
import com.mitologia.chin.bukkit.MCMapView;
import com.mitologia.chin.bukkit.MCMerchantRecipe;
import org.bukkit.DyeColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Statistic;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;

import com.mitologia.chin.event.Events.*;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class AbstractionEvent {

    public static class BukkitMCItemDamageEvent implements MCItemDamageEvent {

        PlayerItemDamageEvent pide;

        public BukkitMCItemDamageEvent(PlayerItemDamageEvent e) {
            this.pide = e;
        }

        @Override
        public CInt getDamage() {
            return new CInt(pide.getDamage(), Target.UNKNOWN);
        }

        @Override
        public MCItemStack getItem() {
            return new BukkitMCItemStack(pide.getItem());
        }

        @Override
        public MCPlayer getPlayer() {
            return new BukkitMCPlayer(pide.getPlayer());
        }

        @Override
        public boolean isCancelled() {
            return pide.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancel) {
            pide.setCancelled(cancel);
        }

        @Override
        public void setDamage(int damage) {
            pide.setDamage(damage);
        }

        @Override
        public Object _GetObject() {
            return pide;
        }

    }

    public static class BukkitMCPlayerRecipeDiscoverEvent implements MCPlayerRecipeDiscoverEvent {

        PlayerRecipeDiscoverEvent prde;

        public BukkitMCPlayerRecipeDiscoverEvent(PlayerRecipeDiscoverEvent e) {
            this.prde = e;
        }

        @Override
        public NamespacedKey getRecipe() {
            return prde.getRecipe();
        }

        @Override
        public MCPlayer getPlayer() {
            return new BukkitMCPlayer(prde.getPlayer());
        }

        @Override
        public boolean isCancelled() {
            return prde.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancel) {
            prde.setCancelled(cancel);
        }

        @Override
        public Object _GetObject() {
            return prde;
        }
    }

    public static class BukkitMCBlockPhysicsEvent implements MCBlockPhysicsEvent {

        BlockPhysicsEvent bpe;

        public BukkitMCBlockPhysicsEvent(BlockPhysicsEvent e) {
            this.bpe = e;
        }

        @Override
        public Object _GetObject() {
            return bpe;
        }

        @Override
        public MCMaterial getChangedType() {
            return new BukkitMCMaterial(bpe.getChangedType());
        }

        @Override
        public MCBlock getBlock() {
            return new BukkitMCBlock(bpe.getBlock());
        }

        @Override
        public boolean isCancelled() {
            return bpe.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancel) {
            bpe.setCancelled(cancel);
        }

    }

    @abstraction(type = Implementation.Type.BUKKIT)
    public static class BukkitMCEntityDropItemEvent implements MCEntityDropItemEvent {

        EntityDropItemEvent edie;

        public BukkitMCEntityDropItemEvent(EntityDropItemEvent e) {
            this.edie = e;
        }

        @Override
        public MCItem getItemDrop() {
            return new BukkitMCItem(edie.getItemDrop());
        }

        @Override
        public MCEntity getEntity() {
            return new BukkitMCEntity(edie.getEntity());
        }

        @Override
        public boolean isCancelled() {
            return edie.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancelled) {
            edie.setCancelled(cancelled);
        }

        @Override
        public Object _GetObject() {
            return edie;
        }
    }

    @abstraction(type = Implementation.Type.BUKKIT)
    public static class BukkitMCEntityToggleSwimEvent implements MCEntityToggleSwimEvent {

        EntityToggleSwimEvent etse;

        public BukkitMCEntityToggleSwimEvent(EntityToggleSwimEvent e) {
            this.etse = e;
        }

        @Override
        public boolean isSwimming() {
            return etse.isSwimming();
        }

        @Override
        public MCEntity getEntity() {
            return new BukkitMCEntity(etse.getEntity());
        }

        @Override
        public boolean isCancelled() {
            return etse.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancelled) {
            etse.setCancelled(cancelled);
        }

        @Override
        public Object _GetObject() {
            return etse;
        }
    }

    public static class BukkitMCPigZombieAngerEvent implements MCPigZombieAngerEvent {

        PigZombieAngerEvent pzae;

        public BukkitMCPigZombieAngerEvent(PigZombieAngerEvent e) {
            this.pzae = e;
        }

        @Override
        public MCPigZombie getEntity() {
            return new BukkitMCPigZombie(pzae.getEntity());
        }

        @Override
        public CInt getNewAnger() {
            return new CInt(pzae.getNewAnger(), Target.UNKNOWN);
        }

        @Override
        public MCEntity getTarget() {
            return new BukkitMCEntity(pzae.getTarget());
        }

        @Override
        public boolean isCancelled() {
            return pzae.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancelled) {
            pzae.setCancelled(cancelled);
        }

        @Override
        public void setNewAnger(int newAnger) {
            pzae.setNewAnger(newAnger);
        }

        @Override
        public Object _GetObject() {
            return pzae;
        }

    }

    public static class BukkitMCPlayerRiptideEvent implements MCPlayerRiptideEvent {

        PlayerRiptideEvent pre;

        public BukkitMCPlayerRiptideEvent(PlayerRiptideEvent e) {
            this.pre = e;
        }

        @Override
        public MCItemStack getItem() {
            return new BukkitMCItemStack(pre.getItem());
        }

        @Override
        public MCPlayer getPlayer() {
            return new BukkitMCPlayer(pre.getPlayer());
        }

        @Override
        public Object _GetObject() {
            return pre;
        }
    }

    public static class BukkitMCMapInitializeEvent implements MCMapInitializeEvent {

        MapInitializeEvent mie;

        public BukkitMCMapInitializeEvent(MapInitializeEvent mie) {
            this.mie = mie;
        }

        @Override
        public MCMapView getMap() {
            return new BukkitMCMapView(mie.getMap());
        }

        @Override
        public Object _GetObject() {
            return mie;
        }
    }

    public static class BukkitMCAreaEffectCloudApplyEvent implements MCAreaEffectCloudApplyEvent {

        AreaEffectCloudApplyEvent aeca;

        public BukkitMCAreaEffectCloudApplyEvent(Event e) {
            this.aeca = (AreaEffectCloudApplyEvent) e;
        }

        @Override
        public List<MCLivingEntity> getAffectedEntities() {
            List<MCLivingEntity> list = new ArrayList<>();
            for(LivingEntity le : aeca.getAffectedEntities()) {
                list.add(new BukkitMCLivingEntity(le));
            }
            return list;
        }

        @Override
        public MCEntity getEntity() {
            return new BukkitMCEntity(aeca.getEntity());
        }

        @Override
        public Object _GetObject() {
            return aeca;
        }
    }


    public static class BukkitMCBrewingStandFuelEvent implements MCBrewingStandFuelEvent {

        BrewingStandFuelEvent bsfe;

        public BukkitMCBrewingStandFuelEvent(BrewingStandFuelEvent e) {
            this.bsfe = e;
        }

        @Override
        public MCItemStack getFuel() {
            return new BukkitMCItemStack(bsfe.getFuel());
        }

        @Override
        public int getFuelPower() {
            return bsfe.getFuelPower();
        }

        @Override
        public MCBlock getBlock() {
            return new BukkitMCBlock(bsfe.getBlock());
        }

        @Override
        public boolean isConsuming() {
            return bsfe.isConsuming();
        }

        @Override
        public boolean isCancelled() {
            return bsfe.isCancelled();
        }

        @Override
        public void setFuelPower(int fuelPower) {
            bsfe.setFuelPower(fuelPower);
        }

        @Override
        public void setConsuming(boolean consuming) {
            bsfe.setConsuming(consuming);
        }

        @Override
        public void setCancelled(boolean cancel) {
            bsfe.setCancelled(cancel);
        }

        @Override
        public Object _GetObject() {
            return bsfe;
        }
    }

    public static class BukkitMCBrewEvent implements MCBrewEvent {

        BrewEvent be;

        public BukkitMCBrewEvent(BrewEvent be) {
            this.be = be;
        }

        @Override
        public MCInventory getContents() {
            return new BukkitMCInventory(be.getContents());
        }

        @Override
        public int getFuelLevel() {
            return be.getFuelLevel();
        }

        @Override
        public MCBlock getBlock() {
            return new BukkitMCBlock(be.getBlock());
        }

        @Override
        public boolean isCancelled() {
            return be.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancelled) {
            be.setCancelled(cancelled);
        }

        @Override
        public Object _GetObject() {
            return be;
        }
    }

    public static class BukkitMCCauldronLevelChangeEvent implements MCCauldronLevelChangeEvent {

        CauldronLevelChangeEvent clce;

        public BukkitMCCauldronLevelChangeEvent(CauldronLevelChangeEvent e) {
            this.clce = e;
        }

        @Override
        public MCEntity getEntity() {
            return new BukkitMCEntity(clce.getEntity());
        }

        @Override
        public int getNewLevel() {
            return clce.getNewLevel();
        }

        @Override
        public int getOldLevel() {
            return clce.getOldLevel();
        }

        @Override
        public String getReason() {
            return clce.getReason().name();
        }

        @Override
        public MCBlock getBlock() {
            return new BukkitMCBlock(clce.getBlock());
        }

        @Override
        public boolean isCancelled() {
            return clce.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancelled) {
            clce.setCancelled(cancelled);
        }

        @Override
        public void setNewLevel(int newLevel) {
            clce.setNewLevel(newLevel);
        }

        @Override
        public Object _GetObject() {
            return clce;
        }
    }

    public static class BukkitMCFurnaceBurnEvent implements MCFurnaceBurnEvent {

        FurnaceBurnEvent fbe;

        public BukkitMCFurnaceBurnEvent(FurnaceBurnEvent e) {
            this.fbe = e;
        }

        @Override
        public int getBurnTine() {
            return fbe.getBurnTime();
        }

        @Override
        public MCItemStack getFuel() {
            return new BukkitMCItemStack(fbe.getFuel());
        }

        @Override
        public MCBlock getBlock() {
            return new BukkitMCBlock(fbe.getBlock());
        }

        @Override
        public boolean isBurning() {
            return fbe.isBurning();
        }

        @Override
        public boolean isCancelled() {
            return fbe.isCancelled();
        }

        @Override
        public void setBurning(boolean burning) {
            fbe.setBurning(burning);
        }

        @Override
        public void setBurnTime(int burnTime) {
            fbe.setBurnTime(burnTime);
        }

        @Override
        public void setCancelled(boolean cancel) {
            fbe.setCancelled(cancel);
        }

        @Override
        public Object _GetObject() {
            return fbe;
        }
    }

    public static class BukkitMCFurnaceExtractEvent implements MCFurnaceExtractEvent {

        FurnaceExtractEvent fee;

        public BukkitMCFurnaceExtractEvent(FurnaceExtractEvent e) {
            this.fee = e;
        }

        @Override
        public int getExpToDrop() {
            return fee.getExpToDrop();
        }

        @Override
        public int getItemAmount() {
            return fee.getItemAmount();
        }

        @Override
        public MCMaterial getItemType() {
            return new BukkitMCMaterial(fee.getItemType());
        }

        @Override
        public MCPlayer getPlayer() {
            return new BukkitMCPlayer(fee.getPlayer());
        }

        @Override
        public MCBlock getBlock() {
            return new BukkitMCBlock(fee.getBlock());
        }

        @Override
        public void setExpToDrop(int exp) {
            fee.setExpToDrop(exp);
        }

        @Override
        public Object _GetObject() {
            return fee;
        }
    }

    public static class BukkitMCFurnaceSmeltEvent implements MCFurnaceSmeltEvent {

        FurnaceSmeltEvent fse;

        public BukkitMCFurnaceSmeltEvent(FurnaceSmeltEvent e) {
            this.fse = e;
        }

        @Override
        public MCItemStack getResult() {
            return new BukkitMCItemStack(fse.getResult());
        }

        @Override
        public MCItemStack getSource() {
            return new BukkitMCItemStack(fse.getSource());
        }

        @Override
        public MCBlock getBlock() {
            return new BukkitMCBlock(fse.getBlock());
        }

        @Override
        public boolean isCancelled() {
            return fse.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancel) {
            fse.setCancelled(cancel);
        }

        @Override
        public void setResult(MCItemStack result) {
            fse.setResult(((BukkitMCItemStack) result).asItemStack());
        }

        @Override
        public Object _GetObject() {
            return fse;
        }
    }

    public static class BukkitMCLeavesDeacyEvent implements MCLeavesDecayEvent {

        LeavesDecayEvent lde;

        public BukkitMCLeavesDeacyEvent(LeavesDecayEvent e) {
            this.lde = e;
        }

        @Override
        public MCBlock getBlock() {
            return new BukkitMCBlock(lde.getBlock());
        }

        @Override
        public boolean isCancelled() {
            return lde.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancel) {
            lde.setCancelled(cancel);
        }

        @Override
        public Object _GetObject() {
            return lde;
        }
    }

    public static class BukkitMCEggThrowEvent implements MCEggThrowEvent {

        PlayerEggThrowEvent pete;

        public BukkitMCEggThrowEvent(PlayerEggThrowEvent e) {
            this.pete = e;
        }

        @Override
        public MCEgg getEgg() {
            return new BukkitMCEgg(pete.getEgg());
        }

        @Override
        public MCPlayer getPlayer() {
            return new BukkitMCPlayer(pete.getPlayer());
        }

        @Override
        public MCEntityType getHatchingType() {
            return BukkitMCEntityType.valueOfConcrete(pete.getHatchingType());
        }

        @Override
        public byte getNumHatches() {
            return pete.getNumHatches();
        }

        @Override
        public boolean isHatching() {
            return pete.isHatching();
        }

        @Override
        public void setHatching(boolean hatching) {
            pete.setHatching(hatching);
        }

        @Override
        public void setHatchingType(MCEntityType hatchingType) {
            pete.setHatchingType(EntityType.valueOf(hatchingType.name()));
        }

        @Override
        public void setNumHatches(byte numHatches) {
            pete.setNumHatches(numHatches);
        }

        @Override
        public Object _GetObject() {
            return pete;
        }
    }
    public static class BukkitMCEntityAirChangeEvent implements MCEntityAirChangeEvent {

        EntityAirChangeEvent eace;

        public BukkitMCEntityAirChangeEvent(Event e) {
            this.eace = (EntityAirChangeEvent) e;
        }

        @Override
        public int getAmount() {
            return eace.getAmount();
        }

        @Override
        public MCEntity getEntity() {
            return new BukkitMCEntity(eace.getEntity());
        }

        @Override
        public boolean isCancelled() {
            return eace.isCancelled();
        }

        @Override
        public void setAmount(int amount) {
            eace.setAmount(amount);
        }

        @Override
        public void setCancelled(boolean cancelled) {
            eace.setCancelled(cancelled);
        }

        @Override
        public Object _GetObject() {
            return eace;
        }
    }

    public static class BukkitMCEntityBreedEvent implements MCEntityBreedEvent {

        EntityBreedEvent ebe;

        public BukkitMCEntityBreedEvent(Event e) {
            this.ebe = (EntityBreedEvent) e;
        }

        @Override
        public MCItemStack getBredWith() {
            return new BukkitMCItemStack(ebe.getBredWith());
        }

        @Override
        public MCLivingEntity getBreeder() {
            return new BukkitMCLivingEntity(ebe.getBreeder());
        }

        @Override
        public MCLivingEntity getEntity() {
            return new BukkitMCLivingEntity(ebe.getEntity());
        }

        @Override
        public int getExperience() {
            return ebe.getExperience();
        }

        @Override
        public MCLivingEntity getFather() {
            return new BukkitMCLivingEntity(ebe.getFather());
        }

        @Override
        public MCLivingEntity getMother() {
            return new BukkitMCLivingEntity(ebe.getMother());
        }

        @Override
        public boolean isCancelled() {
            return ebe.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancelled) {
            ebe.setCancelled(cancelled);
        }

        @Override
        public void setExperience(int experience) {
            ebe.setExperience(experience);
        }

        @Override
        public Object _GetObject() {
            return ebe;
        }
    }

    public static class BukkitMCEntityResurrectEvent implements MCEntityResurrectEvent {

        EntityResurrectEvent ere;

        public BukkitMCEntityResurrectEvent(Event e) {
            this.ere = (EntityResurrectEvent) e;
        }

        @Override
        public MCLivingEntity getEntity() {
            return new BukkitMCLivingEntity(ere.getEntity());
        }

        @Override
        public boolean isCancelled() {
            return ere.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancelled) {
            ere.setCancelled(cancelled);
        }

        @Override
        public Object _GetObject() {
            return ere;
        }
    }

    public static class BukkitMCEntityShootBowEvent implements MCEntityShootBowEvent {

        EntityShootBowEvent esbe;

        public BukkitMCEntityShootBowEvent(Event e) {
            this.esbe = (EntityShootBowEvent) e;
        }

        @Override
        public MCItemStack getBow() {
            return new BukkitMCItemStack(esbe.getBow());
        }

        @Override
        public MCEntity getEntity() {
            return new BukkitMCEntity(esbe.getEntity());
        }

        @Override
        public float getForce() {
            return esbe.getForce();
        }

        @Override
        public MCEntity getProjectile() {
            return new BukkitMCEntity(esbe.getProjectile());
        }

        @Override
        public boolean isCancelled() {
            return esbe.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancelled) {
            esbe.setCancelled(cancelled);
        }

        @Override
        public void setProjectile(MCEntity projectile) {
            esbe.setProjectile((Entity) projectile.getHandle());
        }

        @Override
        public Object _GetObject() {
            return esbe;
        }
    }

    public static class BukkitMCEntityTameEvent implements MCEntityTameEvent {

        EntityTameEvent ete;

        public BukkitMCEntityTameEvent(Event e) {
            this.ete = (EntityTameEvent) e;
        }

        @Override
        public MCLivingEntity getEntity() {
            return new BukkitMCLivingEntity(ete.getEntity());
        }

        @Override
        public MCAnimalTamer getOwner() {
            return new BukkitMCAnimalTamer(ete.getOwner());
        }

        @Override
        public boolean isCancelled() {
            return ete.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancelled) {
            ete.setCancelled(cancelled);
        }

        @Override
        public Object _GetObject() {
            return ete;
        }
    }

    public static class BukkitMCItemMergeEvent implements MCItemMergeEvent {

        ItemMergeEvent ime;

        public BukkitMCItemMergeEvent(Event e) {
            this.ime = (ItemMergeEvent) e;
        }

        @Override
        public MCItem getEntity() {
            return new BukkitMCItem(ime.getEntity());
        }

        @Override
        public MCItem getTarget() {
            return new BukkitMCItem(ime.getTarget());
        }

        @Override
        public boolean isCancelled() {
            return ime.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancelled) {
            ime.setCancelled(cancelled);
        }

        @Override
        public Object _GetObject() {
            return ime;
        }
    }

    public static class BukkitMCLocaleChangeEvent implements MCLocaleChangeEvent {

        PlayerLocaleChangeEvent plce;

        public BukkitMCLocaleChangeEvent(PlayerLocaleChangeEvent e) {
            this.plce = e;
        }

        @Override
        public String getLocale() {
            return plce.getLocale();
        }

        @Override
        public MCPlayer getPlayer() {
            return new BukkitMCPlayer(plce.getPlayer());
        }

        @Override
        public Object _GetObject() {
            return plce;
        }
    }

    public static class BukkitMCPlayerResourcepackStatusEvent implements MCPlayerResourcepackStatusEvent {

        PlayerResourcePackStatusEvent prpse;

        public BukkitMCPlayerResourcepackStatusEvent(PlayerResourcePackStatusEvent e) {
            this.prpse = e;
        }

        @Override
        public MCPlayer getPlayer() {
            return new BukkitMCPlayer(prpse.getPlayer());
        }

        @Override
        public String getStatus() {
            return prpse.getStatus().name();
        }

        @Override
        public Object _GetObject() {
            return prpse;
        }
    }

    public static class BukkitMCPlayerStatisticIncrementEvent implements MCPlayerStatisticIncrementEvent {

        PlayerStatisticIncrementEvent psie;

        public BukkitMCPlayerStatisticIncrementEvent(PlayerStatisticIncrementEvent e) {
            this.psie = e;
        }

        @Override
        public EntityType getEntityType() {
            return psie.getEntityType();
        }

        @Override
        public Object getMaterial() {
            return (psie.getMaterial() == null ? CNull.NULL : new BukkitMCMaterial(psie.getMaterial()));
        }

        @Override
        public CInt getPreviousValue() {
            return new CInt(psie.getPreviousValue(), Target.UNKNOWN);
        }

        @Override
        public CInt getNewValue() {
            return new CInt(psie.getNewValue(), Target.UNKNOWN);
        }

        @Override
        public Statistic getStatistic() {
            return psie.getStatistic();
        }

        @Override
        public MCPlayer getPlayer() {
            return new BukkitMCPlayer(psie.getPlayer());
        }

        @Override
        public boolean isCancelled() {
            return psie.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancel) {
            psie.setCancelled(cancel);
        }

        @Override
        public Object _GetObject() {
            return psie;
        }
    }

    public static class BukkitMCPlayerVelocityEvent implements MCPlayerVelocityEvent {

        PlayerVelocityEvent pve;

        public BukkitMCPlayerVelocityEvent(PlayerVelocityEvent e) {
            this.pve = e;
        }

        @Override
        public Vector3D getVelocity() {
            return new Vector3D(pve.getVelocity().getX(), pve.getVelocity().getY(), pve.getVelocity().getZ());
        }

        @Override
        public MCPlayer getPlayer() {
            return new BukkitMCPlayer(pve.getPlayer());
        }

        @Override
        public boolean isCancelled() {
            return pve.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancel) {
            pve.setCancelled(cancel);
        }

        @Override
        public void setVelocity(Vector3D velocity) {
            pve.setVelocity(new Vector(velocity.X(), velocity.Y(), velocity.Z()));
        }

        @Override
        public Object _GetObject() {
            return pve;
        }
    }

    public static class BukkitMCPrepareAnvilEvent extends BukkitInventoryEvents.BukkitMCInventoryEvent implements MCPrepareAnvilEvent {

        PrepareAnvilEvent e;

        public BukkitMCPrepareAnvilEvent(PrepareAnvilEvent event) {
            super(event);
            e = event;
        }

        @Override
        public MCItemStack getResult() {
            return new BukkitMCItemStack(e.getResult());
        }

        @Override
        public int getCost() {
            return e.getInventory().getRepairCost();
        }

        @Override
        public String getRenameText() {
            return e.getInventory().getRenameText();
        }

        @Override
        public MCInventory getInventory() {
            return new BukkitMCInventory(e.getInventory());
        }

        @Override
        public void setResult(MCItemStack result) {
            e.setResult((ItemStack) result.getHandle());
        }

        @Override
        public void setCost(int cost) {
            e.getInventory().setRepairCost(cost);
        }
    }

    public static class BukkitMCSheepDyeWoolEvent implements MCSheepDyeWoolEvent {

        SheepDyeWoolEvent sdwe;

        public BukkitMCSheepDyeWoolEvent(SheepDyeWoolEvent e) {
            this.sdwe = e;
        }

        @Override
        public MCDyeColor getColor() {
            return MCDyeColor.valueOf(sdwe.getColor().name());
        }

        @Override
        public MCSheep getEntity() {
            return new BukkitMCSheep(sdwe.getEntity());
        }

        @Override
        public boolean isCancelled() {
            return sdwe.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancelled) {
            sdwe.setCancelled(cancelled);
        }

        @Override
        public void setColor(MCDyeColor color) {
            sdwe.setColor(DyeColor.valueOf(color.name()));
        }

        @Override
        public Object _GetObject() {
            return sdwe;
        }
    }

    public static class BukkitMCSheepRegrowWoolEvent implements MCSheepRegrowWoolEvent {

        SheepRegrowWoolEvent srwe;

        public BukkitMCSheepRegrowWoolEvent(SheepRegrowWoolEvent e) {
            this.srwe = e;
        }

        @Override
        public MCSheep getEnity() {
            return new BukkitMCSheep(srwe.getEntity());
        }

        @Override
        public boolean isCancelled() {
            return srwe.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancelled) {
            srwe.setCancelled(cancelled);
        }

        @Override
        public Object _GetObject() {
            return srwe;
        }
    }

    public static class BukkitMCSlimeSplitEvent implements MCSlimeSplitEvent {

        SlimeSplitEvent sse;

        public BukkitMCSlimeSplitEvent(SlimeSplitEvent e) {
            this.sse = e;
        }

        @Override
        public int getCount() {
            return sse.getCount();
        }

        @Override
        public MCSlime getEntity() {
            return new BukkitMCSlime(sse.getEntity());
        }

        @Override
        public boolean isCancelled() {
            return sse.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancelled) {
            sse.setCancelled(cancelled);
        }

        @Override
        public void setCount(int count) {
            sse.setCount(count);
        }

        @Override
        public Object _GetObject() {
            return sse;
        }
    }

    public static class BukkitMCVillagerAcquireTradeEvent implements MCVillagerAcquireTradeEvent {

        VillagerAcquireTradeEvent vate;

        public BukkitMCVillagerAcquireTradeEvent(VillagerAcquireTradeEvent e) {
            this.vate = e;
        }

        @Override
        public MCVillager getEntity() {
            return new BukkitMCVillager(vate.getEntity());
        }

        @Override
        public MCMerchantRecipe getRecipe() {
            return new BukkitMCMerchantRecipe(vate.getRecipe());
        }

        @Override
        public boolean isCancelled() {
            return vate.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancelled) {
            vate.setCancelled(cancelled);
        }

        @Override
        public void setRecipe(MCMerchantRecipe recipe) {
            vate.setRecipe((MerchantRecipe) recipe.getHandle());
        }

        @Override
        public Object _GetObject() {
            return vate;
        }
    }

    public static class BukkitMCVillagerReplenishTradeEvent implements MCVillagerReplenishTradeEvent {

        VillagerReplenishTradeEvent vrte;

        public BukkitMCVillagerReplenishTradeEvent(VillagerReplenishTradeEvent e) {
            this.vrte = e;
        }

        @Override
        public CInt getBonus() {
            return new CInt(vrte.getBonus(), Target.UNKNOWN);
        }

        @Override
        public MCVillager getEntity() {
            return new BukkitMCVillager(vrte.getEntity());
        }

        @Override
        public MCMerchantRecipe getRecipe() {
            return new BukkitMCMerchantRecipe(vrte.getRecipe());
        }

        @Override
        public boolean isCancelled() {
            return vrte.isCancelled();
        }

        @Override
        public void setBonus(int bonus) {
            vrte.setBonus(bonus);
        }

        @Override
        public void setCancelled(boolean cancelled) {
            vrte.setCancelled(cancelled);
        }

        @Override
        public void setRecipe(MCMerchantRecipe recipe) {
            vrte.setRecipe((MerchantRecipe) recipe.getHandle());
        }

        @Override
        public Object _GetObject() {
            return vrte;
        }
    }

    public static class BukkitMCCreeperPowerEvent implements MCCreeperPowerEvent {

        CreeperPowerEvent cpe;

        public BukkitMCCreeperPowerEvent(CreeperPowerEvent e) {
            this.cpe = e;
        }

        @Override
        public String getCause() {
            return cpe.getCause().name();
        }

        @Override
        public MCEntity getEntity() {
            return new BukkitMCEntity(cpe.getEntity());
        }

        @Override
        public MCLightningStrike getLightning() {
            return new BukkitMCLightningStrike(cpe.getLightning());
        }

        @Override
        public boolean isCancelled() {
            return cpe.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancelled) {
            cpe.setCancelled(cancelled);
        }

        @Override
        public Object _GetObject() {
            return cpe;
        }
    }

    public static class BukkitMCItemBreakEvent implements MCItemBreakEvent {

        PlayerItemBreakEvent pibe;

        public BukkitMCItemBreakEvent(PlayerItemBreakEvent e) {
            this.pibe = e;
        }

        @Override
        public MCItemStack getBrokenItem() {
            return new BukkitMCItemStack(pibe.getBrokenItem());
        }

        @Override
        public MCPlayer getPlayer() {
            return new BukkitMCPlayer(pibe.getPlayer());
        }

        @Override
        public Object _GetObject() {
            return pibe;
        }
    }

    public static class BukkitMCPlayerAdvancementDoneEvent implements MCPlayerAdvancementDoneEvent {

        PlayerAdvancementDoneEvent pade;

        public BukkitMCPlayerAdvancementDoneEvent(PlayerAdvancementDoneEvent e) {
            this.pade = e;
        }

        @Override
        public Advancement getAdvancement() {
            return pade.getAdvancement();
        }

        @Override
        public MCPlayer getPlayer() {
            return new BukkitMCPlayer(pade.getPlayer());
        }

        @Override
        public Object _GetObject() {
            return pade;
        }
    }

    public static class BukkitMCPlayerBucketFillEvent implements MCPlayerBucketEvent {

        PlayerBucketFillEvent pbe;

        public BukkitMCPlayerBucketFillEvent(PlayerBucketFillEvent e) {
            this.pbe = e;
        }

        @Override
        public MCBlock getBlockClicked() {
            return new BukkitMCBlock(pbe.getBlockClicked());
        }

        @Override
        public MCBlockFace getBlockFace() {
            MCBlock b = new BukkitMCBlock(pbe.getBlockClicked());
            return b.getFace(b);
        }

        @Override
        public MCMaterial getBucket() {
            return new BukkitMCMaterial(pbe.getBucket());
        }

        @Override
        public MCItemStack getItemStack() {
            return new BukkitMCItemStack(pbe.getItemStack());
        }

        @Override
        public MCPlayer getPlayer() {
            return new BukkitMCPlayer(pbe.getPlayer());
        }

        @Override
        public boolean isCancelled() {
            return pbe.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancel) {
            pbe.setCancelled(cancel);
        }

        @Override
        public void setItemStack(MCItemStack is) {
            pbe.setItemStack(((BukkitMCItemStack) is).asItemStack());
        }

        @Override
        public Object _GetObject() {
            return pbe;
        }
    }

    public static class BukkitMCPlayerBucketEmptyEvent implements MCPlayerBucketEvent {

        PlayerBucketEmptyEvent pbe;

        public BukkitMCPlayerBucketEmptyEvent(PlayerBucketEmptyEvent e) {
            this.pbe = e;
        }

        @Override
        public MCBlock getBlockClicked() {
            return new BukkitMCBlock(pbe.getBlockClicked());
        }

        @Override
        public MCBlockFace getBlockFace() {
            MCBlock b = new BukkitMCBlock(pbe.getBlockClicked());
            return b.getFace(b);
        }

        @Override
        public MCMaterial getBucket() {
            return new BukkitMCMaterial(pbe.getBucket());
        }

        @Override
        public MCItemStack getItemStack() {
            return new BukkitMCItemStack(pbe.getItemStack());
        }

        @Override
        public MCPlayer getPlayer() {
            return new BukkitMCPlayer(pbe.getPlayer());
        }

        @Override
        public boolean isCancelled() {
            return pbe.isCancelled();
        }

        @Override
        public void setCancelled(boolean cancel) {
            pbe.setCancelled(cancel);
        }

        @Override
        public void setItemStack(MCItemStack is) {
            pbe.setItemStack(((BukkitMCItemStack) is).asItemStack());
        }

        @Override
        public Object _GetObject() {
            return pbe;
        }
    }

}
