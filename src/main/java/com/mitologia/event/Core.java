package com.mitologia.event;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;

import com.mitologia.event.Events.*;

import java.util.Map;

public class Core  {

    @api
    public static class item_damage extends AbstractEvent {

        @Override
        public String getName() {
            return "item_damage";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Fired when a player's item damages."
                    + "{ player : the player | item : Gets the item that damaged."
                    + "| damage : Gets the amount of durability damage this item will be taking.}"
                    + "{}"
                    + "{damage}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCItemDamageEvent){

                MCItemDamageEvent ide = (MCItemDamageEvent) e;
                Map<String, Construct> mapEvent = evaluate_helper(ide);
                Target t = Target.UNKNOWN;

                mapEvent.put("player", new CString(ide.getPlayer().getName(), t));
                mapEvent.put("damage", ide.getDamage());
                mapEvent.put("item", ObjectGenerator.GetGenerator().item(ide.getItem(), t));

                return mapEvent;

            }else{
                throw new EventException("Cannot convert event to PlayerItemDamageEvent.");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCItemDamageEvent){
                if(key.equalsIgnoreCase("damage")){
                    ((MCItemDamageEvent) event).setDamage(Static.getInt32(value, Target.UNKNOWN));
                    return true;
                }
            }
            return false;
        }
    }

    @api
    public static class player_recipe_discover extends AbstractEvent {

        @Override
        public String getName() {
            return "player_recipe_discover";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when a player discovers a new recipe in the recipe book."
                    + "{player : the Player "
                    + "| key : Get the namespaced key of the discovered recipe."
                    + "| namespace : Get the namespace of the discovered recipe.}"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCPlayerRecipeDiscoverEvent){

                MCPlayerRecipeDiscoverEvent prde = (MCPlayerRecipeDiscoverEvent) e;
                Map<String, Construct> mapEvent = evaluate_helper(e);
                Target t = Target.UNKNOWN;

                mapEvent.put("player", new CString(prde.getPlayer().getName(), t));
                mapEvent.put("key", new CString(prde.getRecipe().getKey(), t));
                mapEvent.put("namespace", new CString(prde.getRecipe().getNamespace(), t));

                return mapEvent;

            }else{
                throw new EventException("Cannot convert event to PlayerRecipeDiscoverEvent.");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            return false;
        }
    }

    @api
    public static class block_physics extends AbstractEvent {

        @Override
        public String getName() {
            return "block_physics";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Thrown when a block physics check is called. "
                    + "{changedtype : Gets the type of block that changed, causing this event. }"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(!(e instanceof MCBlockPhysicsEvent)) {
                throw new EventException("Cannot convert event to BlockPhysicsEvent");
            }

            MCBlockPhysicsEvent event = (MCBlockPhysicsEvent) e;
            Target t = Target.UNKNOWN;
            Map<String, Construct> mapEvent = evaluate_helper(event);

            mapEvent.put("location", ObjectGenerator.GetGenerator().location(event.getBlock().getLocation(), false));
            mapEvent.put("changedtype", new CString(event.getChangedType().getName(), t));

            return mapEvent;
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            return false;
        }
    }

    @api
    public static class entity_drop_item extends AbstractEvent {

        @Override
        public String getName() {
            return "entity_drop_item";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Thrown when an entity creates an item drop."
                    + "{item : Gets the Item created by the entity."
                    + "| id : Returns the Entity involved in this event.}"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCEntityDropItemEvent) {

                MCEntityDropItemEvent event = (MCEntityDropItemEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                CArray item = new CArray(t);
                item.set("item", ObjectGenerator.GetGenerator().item(event.getItemDrop().getItemStack(), t), t);
                item.set("id", event.getItemDrop().getUniqueId().toString());
                ret.put("item", item);

                ret.put("id", new CString(event.getEntity().getUniqueId().toString(), t));

                return ret;

            } else {
                throw new EventException("Could not convert to EntityDropItemEvent");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            return false;
        }
    }

    @api
    public static class entity_toggle_swim extends AbstractEvent {

        @Override
        public String getName() {
            return "entity_toggle_swim";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Sent when an entity's swimming status is toggled."
                    + "{id : Entity who is involved in this event."
                    + "{swimming : true if the entity is entering swimming mode, false if the entity is leaving it.}"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCEntityToggleSwimEvent) {

                MCEntityToggleSwimEvent event = (MCEntityToggleSwimEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                ret.put("id", new CString(event.getEntity().getUniqueId().toString(), t));
                ret.put("swimming", CBoolean.get(event.isSwimming()));

                return ret;
            } else {
                throw new EventException("Could not convert to EntityToggleSwimEvent");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            return false;
        }
    }

    @api
    public static class pig_zombie_anger extends AbstractEvent {

        @Override
        public String getName() {
            return "pig_zombie_anger";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when a Pig Zombie is angered by another entity."
                    + "{id : Returns the Pigzombie's EntityID"
                    + "| anger : Gets the new anger resulting from this event."
                    + "| target : Gets the entity (if any) which triggered this anger update.}"
                    + "{}"
                    + "{anger}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCPigZombieAngerEvent) {

                MCPigZombieAngerEvent event = (MCPigZombieAngerEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                ret.put("id", new CString(event.getEntity().getUniqueId().toString(), t));
                ret.put("anger", event.getNewAnger());
                ret.put("target", new CString(event.getTarget().getUniqueId().toString(), t));

                return ret;

            } else {
                throw new EventException("Could not convert to PigZombieAngerEvent");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCPigZombieAngerEvent) {
                if(key.equalsIgnoreCase("anger")) {
                    ((MCPigZombieAngerEvent) event).setNewAnger(Static.getInt32(value, Target.UNKNOWN));
                    return true;
                }
            }
            return false;
        }
    }


    @api
    public static class player_riptide extends AbstractEvent {

        @Override
        public String getName() {
            return "player_riptide";
        }

        @Override
        public String docs() {
            return "{}"
                    + "This event is fired when the player activates the riptide enchantment."
                    + "{item : Gets the item containing the used enchantment."
                    + "| player : the Player}"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCPlayerRiptideEvent) {

                MCPlayerRiptideEvent pre = (MCPlayerRiptideEvent) e;
                Map<String, Construct> mapEvent = evaluate_helper(e);
                Target t = Target.UNKNOWN;

                mapEvent.put("item", ObjectGenerator.GetGenerator().item(pre.getItem(), t));
                mapEvent.put("player", new CString(pre.getPlayer().getName(), t));

                return mapEvent;

            } else {
                throw new EventException("Cannot convert event to PlayerRiptideEvent.");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            return false;
        }
    }

}
