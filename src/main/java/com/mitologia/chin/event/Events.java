package com.mitologia.chin.event;

import com.laytonsmith.abstraction.*;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.blocks.MCMaterial;
import com.laytonsmith.abstraction.entities.MCPigZombie;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.constructs.CInt;
import com.mitologia.chin.bukkit.MCMapView;
import org.bukkit.NamespacedKey;


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
}
