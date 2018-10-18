package com.mitologia.event;


import com.laytonsmith.abstraction.bukkit.events.BukkitBlockEvents;
import com.laytonsmith.annotations.EventIdentifier;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;

import com.mitologia.event.Abstraction.*;
import org.bukkit.event.player.PlayerRiptideEvent;

public class Listener implements org.bukkit.event.Listener {

    private static Listener listener;

    public static void register(){
        if(listener == null)
            listener = new Listener();
        CommandHelperPlugin.self.registerEvents(listener);
    }

    public static void unregister(){

        PlayerItemDamageEvent.getHandlerList().unregister(listener);
        PlayerRecipeDiscoverEvent.getHandlerList().unregister(listener);

    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event){
        BukkitMCItemDamageEvent ide = new BukkitMCItemDamageEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "item_damage", ide);
    }

    @EventHandler
    public void onPlayerRecipeDiscover(PlayerRecipeDiscoverEvent event){
        BukkitMCPlayerRecipeDiscoverEvent prde = new BukkitMCPlayerRecipeDiscoverEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_recipe_discover", prde);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPhysics(BlockPhysicsEvent e) {
        BukkitMCBlockPhysicsEvent bpe = new BukkitMCBlockPhysicsEvent(e);
        EventUtils.TriggerListener(Driver.EXTENSION, "block_physics", bpe);
    }

    @EventHandler
    public void onEntityDropItem(Event event) {
        BukkitMCEntityDropItemEvent edie = new BukkitMCEntityDropItemEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_drop_item", edie);
    }

    @EventHandler
    public void onEntityToggleSwim(Event event) {
        BukkitMCEntityToggleSwimEvent etse = new BukkitMCEntityToggleSwimEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_toggle_swim", etse);
    }

    @EventHandler
    public void onPigZombieAnger(Event event) {
        BukkitMCPigZombieAngerEvent pzae = new BukkitMCPigZombieAngerEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "pig_zombie_anger", pzae);
    }

    @EventHandler
    public void onPlayerRiptide(PlayerRiptideEvent event) {
        BukkitMCPlayerRiptideEvent pre = new BukkitMCPlayerRiptideEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_riptide", pre);
    }
}
