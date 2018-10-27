package com.mitologia.chin.abstraction;

import com.laytonsmith.abstraction.*;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.blocks.MCMaterial;
import com.laytonsmith.abstraction.bukkit.BukkitMCItemStack;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCBlock;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCMaterial;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCEntity;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCItem;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPigZombie;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.abstraction.entities.MCPigZombie;
import com.laytonsmith.annotations.abstraction;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.constructs.Target;
import com.mitologia.chin.bukkit.MCMapView;
import org.bukkit.NamespacedKey;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.entity.PigZombieAngerEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;
import org.bukkit.event.player.PlayerRiptideEvent;

import com.mitologia.chin.event.Events.*;
import org.bukkit.event.server.MapInitializeEvent;

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


}
