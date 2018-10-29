package com.mitologia.chin.event;


import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import com.mitologia.chin.abstraction.AbstractionEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.block.LeavesDecayEvent;

import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.EntityAirChangeEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.entity.PigZombieAngerEvent;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.entity.SheepRegrowWoolEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.event.entity.VillagerReplenishTradeEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.BrewingStandFuelEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerLocaleChangeEvent;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.event.server.MapInitializeEvent;

public class Listener implements org.bukkit.event.Listener {

    private static Listener listener;

    public static void register() {
        if (listener == null)
            listener = new Listener();
        CommandHelperPlugin.self.registerEvents(listener);
    }

    public static void unregister() {

        AreaEffectCloudApplyEvent.getHandlerList().unregister(listener);
        CreeperPowerEvent.getHandlerList().unregister(listener);
        EntityAirChangeEvent.getHandlerList().unregister(listener);
        EntityBreedEvent.getHandlerList().unregister(listener);
        EntityDropItemEvent.getHandlerList().unregister(listener);
        EntityResurrectEvent.getHandlerList().unregister(listener);
        EntityShootBowEvent.getHandlerList().unregister(listener);
        EntityTameEvent.getHandlerList().unregister(listener);
        EntityToggleSwimEvent.getHandlerList().unregister(listener);
        ItemMergeEvent.getHandlerList().unregister(listener);
        PigZombieAngerEvent.getHandlerList().unregister(listener);
        SheepDyeWoolEvent.getHandlerList().unregister(listener);
        SheepRegrowWoolEvent.getHandlerList().unregister(listener);
        SlimeSplitEvent.getHandlerList().unregister(listener);
        VillagerAcquireTradeEvent.getHandlerList().unregister(listener);
        VillagerReplenishTradeEvent.getHandlerList().unregister(listener);
        BrewEvent.getHandlerList().unregister(listener);
        BrewingStandFuelEvent.getHandlerList().unregister(listener);
        FurnaceBurnEvent.getHandlerList().unregister(listener);
        FurnaceExtractEvent.getHandlerList().unregister(listener);
        FurnaceSmeltEvent.getHandlerList().unregister(listener);
        PrepareAnvilEvent.getHandlerList().unregister(listener);
        PlayerAdvancementDoneEvent.getHandlerList().unregister(listener);
        PlayerBucketEmptyEvent.getHandlerList().unregister(listener);
        PlayerBucketFillEvent.getHandlerList().unregister(listener);
        PlayerEggThrowEvent.getHandlerList().unregister(listener);
        PlayerItemBreakEvent.getHandlerList().unregister(listener);
        PlayerItemDamageEvent.getHandlerList().unregister(listener);
        PlayerLocaleChangeEvent.getHandlerList().unregister(listener);
        PlayerRecipeDiscoverEvent.getHandlerList().unregister(listener);
        PlayerResourcePackStatusEvent.getHandlerList().unregister(listener);
        PlayerRiptideEvent.getHandlerList().unregister(listener);
        PlayerStatisticIncrementEvent.getHandlerList().unregister(listener);
        PlayerVelocityEvent.getHandlerList().unregister(listener);
        MapInitializeEvent.getHandlerList().unregister(listener);

    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        AbstractionEvent.BukkitMCItemDamageEvent ide = new AbstractionEvent.BukkitMCItemDamageEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "item_damage", ide);
    }

    @EventHandler
    public void onPlayerRecipeDiscover(PlayerRecipeDiscoverEvent event) {
        AbstractionEvent.BukkitMCPlayerRecipeDiscoverEvent prde = new AbstractionEvent.BukkitMCPlayerRecipeDiscoverEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_recipe_discover", prde);
    }

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent e) {
        AbstractionEvent.BukkitMCBlockPhysicsEvent bpe = new AbstractionEvent.BukkitMCBlockPhysicsEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "block_physics", bpe);
    }

    @EventHandler
    public void onEntityDropItem(EntityDropItemEvent event) {
        AbstractionEvent.BukkitMCEntityDropItemEvent edie = new AbstractionEvent.BukkitMCEntityDropItemEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_drop_item", edie);
    }

    @EventHandler
    public void onEntityToggleSwim(EntityToggleSwimEvent event) {
        AbstractionEvent.BukkitMCEntityToggleSwimEvent etse = new AbstractionEvent.BukkitMCEntityToggleSwimEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_toggle_swim", etse);
    }

    @EventHandler
    public void onPigZombieAnger(PigZombieAngerEvent event) {
        AbstractionEvent.BukkitMCPigZombieAngerEvent pzae = new AbstractionEvent.BukkitMCPigZombieAngerEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "pig_zombie_anger", pzae);
    }

    @EventHandler
    public void onPlayerRiptide(PlayerRiptideEvent event) {
        AbstractionEvent.BukkitMCPlayerRiptideEvent pre = new AbstractionEvent.BukkitMCPlayerRiptideEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_riptide", pre);
    }

    @EventHandler
    public void onMapInitialize(MapInitializeEvent event) {
        AbstractionEvent.BukkitMCMapInitializeEvent mie = new AbstractionEvent.BukkitMCMapInitializeEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "map_initialize", mie);
    }

    @EventHandler
    public void onPreAnvil(PrepareAnvilEvent event) {
        AbstractionEvent.BukkitMCPrepareAnvilEvent pa = new AbstractionEvent.BukkitMCPrepareAnvilEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "pre_anvil", pa);
    }

    @EventHandler
    public void onAreaEffectCloudApply(AreaEffectCloudApplyEvent event) {
        AbstractionEvent.BukkitMCAreaEffectCloudApplyEvent aeca = new AbstractionEvent.BukkitMCAreaEffectCloudApplyEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "area_effect_cloud_apply", aeca);
    }

    @EventHandler
    public void onCreeperPower(CreeperPowerEvent event) {
        AbstractionEvent.BukkitMCCreeperPowerEvent cpe = new AbstractionEvent.BukkitMCCreeperPowerEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "creeper_power", cpe);
    }

    @EventHandler
    public void onEntityAirChange(EntityAirChangeEvent event) {
        AbstractionEvent.BukkitMCEntityAirChangeEvent eace = new AbstractionEvent.BukkitMCEntityAirChangeEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_air_change", eace);
    }

    @EventHandler
    public void onEntityBreed(EntityBreedEvent event) {
        AbstractionEvent.BukkitMCEntityBreedEvent ebe = new AbstractionEvent.BukkitMCEntityBreedEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_breed", ebe);
    }

    @EventHandler
    public void onEntityResurrect(EntityResurrectEvent event) {
        AbstractionEvent.BukkitMCEntityResurrectEvent ere = new AbstractionEvent.BukkitMCEntityResurrectEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_resurrect", ere);
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        AbstractionEvent.BukkitMCEntityShootBowEvent esbe = new AbstractionEvent.BukkitMCEntityShootBowEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_shoot_bow", esbe);
    }

    @EventHandler
    public void onEntityTame(EntityTameEvent event) {
        AbstractionEvent.BukkitMCEntityTameEvent ete = new AbstractionEvent.BukkitMCEntityTameEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_tame", ete);
    }

    @EventHandler
    public void onItemMerge(ItemMergeEvent event) {
        AbstractionEvent.BukkitMCItemMergeEvent ime = new AbstractionEvent.BukkitMCItemMergeEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "item_merge", ime);
    }

    @EventHandler
    public void onSheepDyeWool(SheepDyeWoolEvent event) {
        AbstractionEvent.BukkitMCSheepDyeWoolEvent sdwe = new AbstractionEvent.BukkitMCSheepDyeWoolEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "sheep_dye_wool", sdwe);
    }

    @EventHandler
    public void onSheepRegrowWool(SheepRegrowWoolEvent event) {
        AbstractionEvent.BukkitMCSheepRegrowWoolEvent srwe = new AbstractionEvent.BukkitMCSheepRegrowWoolEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "sheep_regrow_wool", srwe);
    }

    @EventHandler
    public void onSlimeSplit(SlimeSplitEvent event) {
        AbstractionEvent.BukkitMCSlimeSplitEvent sse = new AbstractionEvent.BukkitMCSlimeSplitEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "slime_split", sse);
    }

    @EventHandler
    public void onVillagerAcquireTrade(VillagerAcquireTradeEvent event) {
        AbstractionEvent.BukkitMCVillagerAcquireTradeEvent vate = new AbstractionEvent.BukkitMCVillagerAcquireTradeEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "villager_acquire_trade", vate);
    }

    @EventHandler
    public void onVillagerReplenishTrade(VillagerReplenishTradeEvent event) {
        AbstractionEvent.BukkitMCVillagerReplenishTradeEvent vrte = new AbstractionEvent.BukkitMCVillagerReplenishTradeEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "villager_replenish_trade", vrte);
    }

    @EventHandler
    public void onEggThrow(PlayerEggThrowEvent event) {
        AbstractionEvent.BukkitMCEggThrowEvent ete = new AbstractionEvent.BukkitMCEggThrowEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "egg_throw", ete);
    }

    @EventHandler
    public void onItemBreak(PlayerItemBreakEvent event) {
        AbstractionEvent.BukkitMCItemBreakEvent ibe = new AbstractionEvent.BukkitMCItemBreakEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "item_break", ibe);
    }

    @EventHandler
    public void onLocaleChange(PlayerLocaleChangeEvent event) {
        AbstractionEvent.BukkitMCLocaleChangeEvent lce = new AbstractionEvent.BukkitMCLocaleChangeEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "locale_change", lce);
    }

    @EventHandler
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {
        AbstractionEvent.BukkitMCPlayerAdvancementDoneEvent pade = new AbstractionEvent.BukkitMCPlayerAdvancementDoneEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_advancement_done", pade);
    }

    @EventHandler
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
        AbstractionEvent.BukkitMCPlayerBucketFillEvent pbfe = new AbstractionEvent.BukkitMCPlayerBucketFillEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_bucket_fill", pbfe);
    }

    @EventHandler
    public void onPlayerBucket(PlayerBucketEmptyEvent event) {
        AbstractionEvent.BukkitMCPlayerBucketEmptyEvent pbe = new AbstractionEvent.BukkitMCPlayerBucketEmptyEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_bucket_empty", pbe);
    }

    @EventHandler
    public void onPlayerResourcepackStatus(PlayerResourcePackStatusEvent event) {
        AbstractionEvent.BukkitMCPlayerResourcepackStatusEvent prpse = new AbstractionEvent.BukkitMCPlayerResourcepackStatusEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_resourcepack_status", prpse);
    }

    @EventHandler
    public void onPlayerStatisticIncrement(PlayerStatisticIncrementEvent event) {
        AbstractionEvent.BukkitMCPlayerStatisticIncrementEvent psie = new AbstractionEvent.BukkitMCPlayerStatisticIncrementEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_statistic_increment", psie);
    }

    @EventHandler
    public void onPlayerVelocity(PlayerVelocityEvent event) {
        AbstractionEvent.BukkitMCPlayerVelocityEvent pve = new AbstractionEvent.BukkitMCPlayerVelocityEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_velocity", pve);
    }

    @EventHandler
    public void onBrewingStandFuel(BrewingStandFuelEvent e) {
        AbstractionEvent.BukkitMCBrewingStandFuelEvent bsfe = new AbstractionEvent.BukkitMCBrewingStandFuelEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "brewing_stand_fuel", bsfe);
    }

    @EventHandler
    public void onCauldronLevelChange(CauldronLevelChangeEvent e) {
        AbstractionEvent.BukkitMCCauldronLevelChangeEvent clce = new AbstractionEvent.BukkitMCCauldronLevelChangeEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "cauldron_level_change", clce);
    }

    @EventHandler
    public void onBrew(BrewEvent e) {
        AbstractionEvent.BukkitMCBrewEvent be = new AbstractionEvent.BukkitMCBrewEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "brew", be);
    }

    @EventHandler
    public void onFurnaceBurn(FurnaceBurnEvent e) {
        AbstractionEvent.BukkitMCFurnaceBurnEvent fbe = new AbstractionEvent.BukkitMCFurnaceBurnEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "furnace_burn", fbe);
    }

    @EventHandler
    public void onFurnaceExtract(FurnaceExtractEvent e) {
        AbstractionEvent.BukkitMCFurnaceExtractEvent fee = new AbstractionEvent.BukkitMCFurnaceExtractEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "furnace_extract", fee);
    }

    @EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent e) {
        AbstractionEvent.BukkitMCFurnaceSmeltEvent fse = new AbstractionEvent.BukkitMCFurnaceSmeltEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "furnace_smelt", fse);
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {
        AbstractionEvent.BukkitMCLeavesDeacyEvent lde = new AbstractionEvent.BukkitMCLeavesDeacyEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "leaves_decay", lde);
    }


}
