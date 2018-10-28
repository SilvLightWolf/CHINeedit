package com.mitologia.chin.event;


import com.laytonsmith.abstraction.bukkit.events.BukkitBlockEvents;
import com.laytonsmith.abstraction.bukkit.events.BukkitInventoryEvents;
import com.laytonsmith.annotations.EventIdentifier;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;

import com.mitologia.chin.abstraction.AbstractionEvent.*;
import org.bukkit.event.server.MapInitializeEvent;

public class Listener implements org.bukkit.event.Listener {

    private static Listener listener;

    public static void register() {
        if (listener == null)
            listener = new Listener();
        CommandHelperPlugin.self.registerEvents(listener);
    }

    public static void unregister() {

        PlayerItemDamageEvent.getHandlerList().unregister(listener);
        PlayerRecipeDiscoverEvent.getHandlerList().unregister(listener);
        BlockPhysicsEvent.getHandlerList().unregister(listener);
        EntityDropItemEvent.getHandlerList().unregister(listener);
        EntityToggleSwimEvent.getHandlerList().unregister(listener);
        PigZombieAngerEvent.getHandlerList().unregister(listener);
        PlayerRiptideEvent.getHandlerList().unregister(listener);
        MapInitializeEvent.getHandlerList().unregister(listener);

    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        BukkitMCItemDamageEvent ide = new BukkitMCItemDamageEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "item_damage", ide);
    }

    @EventHandler
    public void onPlayerRecipeDiscover(PlayerRecipeDiscoverEvent event) {
        BukkitMCPlayerRecipeDiscoverEvent prde = new BukkitMCPlayerRecipeDiscoverEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_recipe_discover", prde);
    }

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent e) {
        BukkitMCBlockPhysicsEvent bpe = new BukkitMCBlockPhysicsEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "block_physics", bpe);
    }

    @EventHandler
    public void onEntityDropItem(EntityDropItemEvent event) {
        BukkitMCEntityDropItemEvent edie = new BukkitMCEntityDropItemEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_drop_item", edie);
    }

    @EventHandler
    public void onEntityToggleSwim(EntityToggleSwimEvent event) {
        BukkitMCEntityToggleSwimEvent etse = new BukkitMCEntityToggleSwimEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_toggle_swim", etse);
    }

    @EventHandler
    public void onPigZombieAnger(PigZombieAngerEvent event) {
        BukkitMCPigZombieAngerEvent pzae = new BukkitMCPigZombieAngerEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "pig_zombie_anger", pzae);
    }

    @EventHandler
    public void onPlayerRiptide(PlayerRiptideEvent event) {
        BukkitMCPlayerRiptideEvent pre = new BukkitMCPlayerRiptideEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_riptide", pre);
    }

    @EventHandler
    public void onMapInitialize(MapInitializeEvent event) {
        BukkitMCMapInitializeEvent mie = new BukkitMCMapInitializeEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "map_initialize", mie);
    }

    @EventHandler
    public void onPreAnvil(PrepareAnvilEvent event) {
        BukkitMCPrepareAnvilEvent pa = new BukkitMCPrepareAnvilEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "pre_anvil", pa);
    }

    @EventHandler
    public void onAreaEffectCloudApply(AreaEffectCloudApplyEvent event) {
        BukkitMCAreaEffectCloudApplyEvent aeca = new BukkitMCAreaEffectCloudApplyEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "area_effect_cloud_apply", aeca);
    }

    @EventHandler
    public void onCreeperPower(CreeperPowerEvent event) {
        BukkitMCCreeperPowerEvent cpe = new BukkitMCCreeperPowerEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "creeper_power", cpe);
    }

    @EventHandler
    public void onEntityAirChange(EntityAirChangeEvent event) {
        BukkitMCEntityAirChangeEvent eace = new BukkitMCEntityAirChangeEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_air_change", eace);
    }

    @EventHandler
    public void onEntityBreed(EntityBreedEvent event) {
        BukkitMCEntityBreedEvent ebe = new BukkitMCEntityBreedEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_breed", ebe);
    }

    @EventHandler
    public void onEntityResurrect(EntityResurrectEvent event) {
        BukkitMCEntityResurrectEvent ere = new BukkitMCEntityResurrectEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_resurrect", ere);
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        BukkitMCEntityShootBowEvent esbe = new BukkitMCEntityShootBowEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_shoot_bow", esbe);
    }

    @EventHandler
    public void onEntityTame(EntityTameEvent event) {
        BukkitMCEntityTameEvent ete = new BukkitMCEntityTameEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_tame", ete);
    }

    @EventHandler
    public void onItemMerge(ItemMergeEvent event) {
        BukkitMCItemMergeEvent ime = new BukkitMCItemMergeEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "item_merge", ime);
    }

    @EventHandler
    public void onSheepDyeWool(SheepDyeWoolEvent event) {
        BukkitMCSheepDyeWoolEvent sdwe = new BukkitMCSheepDyeWoolEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "sheep_dye_wool", sdwe);
    }

    @EventHandler
    public void onSheepRegrowWool(SheepRegrowWoolEvent event) {
        BukkitMCSheepRegrowWoolEvent srwe = new BukkitMCSheepRegrowWoolEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "sheep_regrow_wool", srwe);
    }

    @EventHandler
    public void onSlimeSplit(SlimeSplitEvent event) {
        BukkitMCSlimeSplitEvent sse = new BukkitMCSlimeSplitEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "slime_split", sse);
    }

    @EventHandler
    public void onVillagerAcquireTrade(VillagerAcquireTradeEvent event) {
        BukkitMCVillagerAcquireTradeEvent vate = new BukkitMCVillagerAcquireTradeEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "villager_acquire_trade", vate);
    }

    @EventHandler
    public void onVillagerReplenishTrade(VillagerReplenishTradeEvent event) {
        BukkitMCVillagerReplenishTradeEvent vrte = new BukkitMCVillagerReplenishTradeEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "villager_replenish_trade", vrte);
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onEggThrow(PlayerEggThrowEvent event) {
        BukkitMCEggThrowEvent ete = new BukkitMCEggThrowEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "egg_throw", ete);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onItemBreak(PlayerItemBreakEvent event) {
        BukkitMCItemBreakEvent ibe = new BukkitMCItemBreakEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "item_break", ibe);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLocaleChange(PlayerLocaleChangeEvent event) {
        BukkitMCLocaleChangeEvent lce = new BukkitMCLocaleChangeEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "locale_change", lce);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {
        BukkitMCPlayerAdvancementDoneEvent pade = new BukkitMCPlayerAdvancementDoneEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_advancement_done", pade);
    }

    @EventHandler
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
        BukkitMCPlayerBucketFillEvent pbfe = new BukkitMCPlayerBucketFillEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_bucket_fill", pbfe);
    }

    @EventHandler
    public void onPlayerBucket(PlayerBucketEmptyEvent event) {
        BukkitMCPlayerBucketEmptyEvent pbe = new BukkitMCPlayerBucketEmptyEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_bucket_empty", pbe);
    }

    @EventHandler
    public void onPlayerResourcepackStatus(PlayerResourcePackStatusEvent event) {
        BukkitMCPlayerResourcepackStatusEvent prpse = new BukkitMCPlayerResourcepackStatusEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_resourcepack_status", prpse);
    }

    @EventHandler
    public void onPlayerStatisticIncrement(PlayerStatisticIncrementEvent event) {
        BukkitMCPlayerStatisticIncrementEvent psie = new BukkitMCPlayerStatisticIncrementEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_statistic_increment", psie);
    }

    @EventHandler
    public void onPlayerVelocity(PlayerVelocityEvent event) {
        BukkitMCPlayerVelocityEvent pve = new BukkitMCPlayerVelocityEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_velocity", pve);
    }

    @EventHandler
    public void onBrewingStandFuel(BrewingStandFuelEvent e) {
        BukkitMCBrewingStandFuelEvent bsfe = new BukkitMCBrewingStandFuelEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "brewing_stand_fuel", bsfe);
    }

    @EventHandler
    public void onCauldronLevelChange(CauldronLevelChangeEvent e) {
        BukkitMCCauldronLevelChangeEvent clce = new BukkitMCCauldronLevelChangeEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "cauldron_level_change", clce);
    }

    @EventHandler
    public void onBrew(BrewEvent e) {
        BukkitMCBrewEvent be = new BukkitMCBrewEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "brew", be);
    }

    @EventHandler
    public void onFurnaceBurn(FurnaceBurnEvent e) {
        BukkitMCFurnaceBurnEvent fbe = new BukkitMCFurnaceBurnEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "furnace_burn", fbe);
    }

    @EventHandler
    public void onFurnaceExtract(FurnaceExtractEvent e) {
        BukkitMCFurnaceExtractEvent fee = new BukkitMCFurnaceExtractEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "furnace_extract", fee);
    }

    @EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent e) {
        BukkitMCFurnaceSmeltEvent fse = new BukkitMCFurnaceSmeltEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "furnace_smelt", fse);
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {
        BukkitMCLeavesDeacyEvent lde = new BukkitMCLeavesDeacyEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "leaves_decay", lde);
    }


}
