package com.mitologia.chin.event;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Vector3D;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.*;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.blocks.MCBlockState;
import com.laytonsmith.abstraction.blocks.MCMaterial;
import com.laytonsmith.abstraction.enums.MCDyeColor;
import com.laytonsmith.abstraction.enums.MCEntityType;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.Prefilters;
import com.laytonsmith.core.exceptions.CRE.CREBadEntityException;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.EventException;

import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.mitologia.chin.ObjGenerator;
import com.mitologia.chin.bukkit.MCMerchantRecipe;
import com.mitologia.chin.event.Events.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Core {

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
            if (e instanceof MCItemDamageEvent) {

                MCItemDamageEvent ide = (MCItemDamageEvent) e;
                Map<String, Construct> mapEvent = evaluate_helper(ide);
                Target t = Target.UNKNOWN;

                mapEvent.put("player", new CString(ide.getPlayer().getName(), t));
                mapEvent.put("damage", ide.getDamage());
                mapEvent.put("item", ObjectGenerator.GetGenerator().item(ide.getItem(), t));

                return mapEvent;

            } else {
                throw new EventException("Cannot convert event to PlayerItemDamageEvent.");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if (event instanceof MCItemDamageEvent) {
                if (key.equalsIgnoreCase("damage")) {
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
            if (e instanceof MCPlayerRecipeDiscoverEvent) {

                MCPlayerRecipeDiscoverEvent prde = (MCPlayerRecipeDiscoverEvent) e;
                Map<String, Construct> mapEvent = evaluate_helper(e);
                Target t = Target.UNKNOWN;

                mapEvent.put("player", new CString(prde.getPlayer().getName(), t));
                mapEvent.put("key", new CString(prde.getRecipe().getKey(), t));
                mapEvent.put("namespace", new CString(prde.getRecipe().getNamespace(), t));

                return mapEvent;

            } else {
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
            if (!(e instanceof MCBlockPhysicsEvent)) {
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
            if (e instanceof MCEntityDropItemEvent) {

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
            if (e instanceof MCEntityToggleSwimEvent) {

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
            if (e instanceof MCPigZombieAngerEvent) {

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
            if (event instanceof MCPigZombieAngerEvent) {
                if (key.equalsIgnoreCase("anger")) {
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
            if (e instanceof MCPlayerRiptideEvent) {

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

    @api
    public static class map_initialize extends AbstractEvent {

        @Override
        public String getName() {
            return "map_initialize";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when a map is initialized."
                    + "{location : where showing}"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> map, BindableEvent bindableEvent) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray cArray, Target target) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent bindableEvent) throws EventException {
            if (bindableEvent instanceof MCMapInitializeEvent) {

                MCMapInitializeEvent e = (MCMapInitializeEvent) bindableEvent;
                Map<String, Construct> ret = new HashMap<>();
                Target t = Target.UNKNOWN;

                CArray location = ObjGenerator.getMapView(e.getMap(), t);
                ret.put("mapview", location);

                return ret;

            } else {
                throw new EventException("Could not cast to MapInitializeEvent");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String s, Construct construct, BindableEvent bindableEvent) {
            return false;
        }
    }


    @api
    public static class brewing_stand_fuel extends AbstractEvent {

        @Override
        public String getName() {
            return "brewing_stand_fuel";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when an ItemStack is about to increase the fuel level of a brewing stand."
                    + "{fuel : Gets the ItemStack of the fuel before the amount was subtracted."
                    + "| power : Gets the fuel power for this fuel."
                    + "| consuming : Gets whether the brewing stand's fuel will be reduced / consumed or not."
                    + "| location : Gets the location involved in this event.}"
                    + "{}"
                    + "{power|consuming}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {

            if(!(e instanceof MCBrewingStandFuelEvent)) {
                throw new EventException("Cannot convert event to BrewingStandFuelEvent");
            }

            MCBrewingStandFuelEvent event = (MCBrewingStandFuelEvent) e;
            Target t = Target.UNKNOWN;
            Map<String, Construct> mapEvent = evaluate_helper(event);

            mapEvent.put("fuel", ObjectGenerator.GetGenerator().item(event.getFuel(), t));
            mapEvent.put("power", new CInt(event.getFuelPower(), t));
            mapEvent.put("location", ObjectGenerator.GetGenerator().location(event.getBlock().getLocation(), false));
            mapEvent.put("consuming", CBoolean.get(event.isConsuming()));

            return mapEvent;
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCBrewingStandFuelEvent) {
                MCBrewingStandFuelEvent bsfe = (MCBrewingStandFuelEvent) event;
                if(key.equalsIgnoreCase("power")) {
                    bsfe.setFuelPower(Static.getInt32(value, Target.UNKNOWN));
                    return true;
                } else if(key.equalsIgnoreCase("consuming")) {
                    bsfe.setConsuming(Static.getBoolean(value));
                    return true;
                }
            }
            return false;
        }
    }

    @api
    public static class brew extends AbstractEvent {

        @Override
        public String getName() {
            return "brew";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when the brewing of the contents inside the Brewing Stand is complete."
                    + "{inventory: gets the contents of the brewing stand."
                    + "| fuellevel: gets the remaining fuel level."
                    + "| location : Gets the location involved in this event. }"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(!(e instanceof MCBrewEvent)) {
                throw new EventException("Cannot convert event to BrewEvent");
            }

            MCBrewEvent event = (MCBrewEvent) e;
            Target t = Target.UNKNOWN;
            Map<String, Construct> mapEvent = evaluate_helper(event);

            CArray inv = new CArray(t);
            for(int i = 0; i < event.getContents().getSize(); i++) {
                inv.set(i, ObjectGenerator.GetGenerator().item(event.getContents().getItem(i), t), t);
            }
            mapEvent.put("inventory", inv);

            mapEvent.put("location", ObjectGenerator.GetGenerator().location(event.getBlock().getLocation(), false));
            mapEvent.put("fuellevel", new CInt(event.getFuelLevel(), t));

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
    public static class cauldron_level_change extends AbstractEvent {

        @Override
        public String getName() {
            return "cauldron_level_change";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when changed cauldron's water level."
                    + "{ id : Get entity which did this."
                    + "| new : Gets the new level of this block. "
                    + "| old : Gets the old level of this block. "
                    + "| reason : Gets the reason why the level has changed. "
                    + "| location : Gets the location involved in this event.}"
                    + "{}"
                    + "{new}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {

            if(!(e instanceof MCCauldronLevelChangeEvent)) {
                throw new EventException("Cannot convert event to CauldronLevelChangeEvent");
            }

            MCCauldronLevelChangeEvent event = (MCCauldronLevelChangeEvent) e;
            Target t = Target.UNKNOWN;
            Map<String, Construct> mapEvent = evaluate_helper(event);

            mapEvent.put("id", new CString(event.getEntity().getUniqueId().toString(), t));
            mapEvent.put("new", new CInt(event.getNewLevel(), t));
            mapEvent.put("old", new CInt(event.getOldLevel(), t));
            mapEvent.put("reason", new CInt(event.getReason(), t));
            mapEvent.put("location", ObjectGenerator.GetGenerator().location(event.getBlock().getLocation(), false));

            return mapEvent;
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCCauldronLevelChangeEvent) {
                if(key.equalsIgnoreCase("new")) {
                    ((MCCauldronLevelChangeEvent) event).setNewLevel(Static.getInt32(value, Target.UNKNOWN));
                    return true;
                }
            }
            return false;
        }
    }

    @api
    public static class furnace_burn extends AbstractEvent {

        @Override
        public String getName() {
            return "furnace_burn";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when an ItemStack is successfully burned as fuel in a furnace."
                    + "{burntime : Gets the burn time for this fuel. "
                    + "| fuel : Gets the fuel ItemStack for this event. "
                    + "| burning: Gets whether the furnace's fuel is burning or not."
                    + "| location : Gets the location involved in this event.}"
                    + "{}"
                    + "{burntime|burning}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(!(e instanceof MCFurnaceBurnEvent)) {
                throw new EventException("Cannot convert event to FurnaceBurnEvent");
            }

            MCFurnaceBurnEvent event = (MCFurnaceBurnEvent) e;
            Target t = Target.UNKNOWN;
            Map<String, Construct> mapEvent = evaluate_helper(event);

            mapEvent.put("burntime", new CInt(event.getBurnTine(), t));
            mapEvent.put("fuel", ObjectGenerator.GetGenerator().item(event.getFuel(), t));
            mapEvent.put("burning", CBoolean.get(event.isBurning()));
            mapEvent.put("location", ObjectGenerator.GetGenerator().location(event.getBlock().getLocation(), false));

            return mapEvent;
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCFurnaceBurnEvent) {
                MCFurnaceBurnEvent fbe = (MCFurnaceBurnEvent) event;
                if(key.equalsIgnoreCase("burntime")) {
                    fbe.setBurnTime(Static.getInt32(value, Target.UNKNOWN));
                    return true;
                } else if(key.equalsIgnoreCase("burning")) {
                    fbe.setBurning(Static.getBoolean(value));
                    return true;
                }
            }
            return false;
        }
    }

    @api
    public static class furnace_extract extends AbstractEvent {

        @Override
        public String getName() {
            return "furnace_extract";
        }

        @Override
        public String docs() {
            return "{}"
                    + "An event that's called when a block yields experience."
                    + "{exp : Get the experience dropped by the block after the event has processed."
                    + "| location : Gets the block involved in this event."
                    + "| amount : Get the item count being retrieved."
                    + "| item : Get the Material of the item being retrieved."
                    + "| player : Get the player that triggered the event.}"
                    + "{}"
                    + "{exp}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0 , 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(!(e instanceof MCFurnaceExtractEvent)) {
                throw new EventException("Cannot convert event to FurnaceExtractEvent");
            }

            MCFurnaceExtractEvent event = (MCFurnaceExtractEvent) e;
            Target t = Target.UNKNOWN;
            Map<String, Construct> mapEvent = evaluate_helper(event);

            mapEvent.put("exp", new CInt(event.getExpToDrop(), t));
            mapEvent.put("amount", new CInt(event.getItemAmount(), t));
            mapEvent.put("item", new CString(event.getItemType().getName(), t));
            mapEvent.put("player", new CString(event.getPlayer().getName(), t));

            mapEvent.put("location", ObjectGenerator.GetGenerator().location(event.getBlock().getLocation(), false));

            return mapEvent;
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCFurnaceExtractEvent) {
                if(key.equalsIgnoreCase("exp")) {
                    ((MCFurnaceExtractEvent) event).setExpToDrop(Static.getInt32(value, Target.UNKNOWN));
                    return true;
                }
            }
            return false;
        }
    }

    @api
    public static class furnace_smelt extends AbstractEvent {

        @Override
        public String getName() {
            return "furnace_smelt";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when an ItemStack is successfully smelted in a furnace."
                    + "{result : Gets the resultant ItemStack for this event. "
                    + "| source : Gets the smelted ItemStack for this event. "
                    + "| location : Gets the block involved in this event.}"
                    + "{}"
                    + "{result}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(!(e instanceof MCFurnaceSmeltEvent)) {
                throw new EventException("Cannot convert event to FurnaceSmeltEvent");
            }

            MCFurnaceSmeltEvent event = (MCFurnaceSmeltEvent) e;
            Target t = Target.UNKNOWN;
            Map<String, Construct> mapEvent = evaluate_helper(event);

            mapEvent.put("result", ObjectGenerator.GetGenerator().item(event.getResult(), t));
            mapEvent.put("source", ObjectGenerator.GetGenerator().item(event.getSource(), t));
            mapEvent.put("location", ObjectGenerator.GetGenerator().location(event.getBlock().getLocation(), false));

            return mapEvent;
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCFurnaceSmeltEvent) {
                if(key.equalsIgnoreCase("result")) {
                    ((MCFurnaceSmeltEvent) event).setResult(ObjectGenerator.GetGenerator().item(value, value.getTarget()));
                    return true;
                }
            }
            return false;
        }

        @api
        public static class leaves_deacy extends AbstractEvent {

            @Override
            public String getName() {
                return "leaves_decay";
            }

            @Override
            public String docs() {
                return "{}"
                        + "Called when leaves are decaying naturally."
                        + "{location : Gets the block involved in this event.}"
                        + "{}"
                        + "{}";
            }

            @Override
            public Version since() {
                return new SimpleVersion(1, 0, 0);
            }

            @Override
            public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
                return true;
            }

            @Override
            public BindableEvent convert(CArray manualObject, Target t) {
                return null;
            }

            @Override
            public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
                if(!(e instanceof MCLeavesDecayEvent)) {
                    throw new EventException("Cannot convert event to LeavesDecayEvent");
                }

                MCLeavesDecayEvent event = (MCLeavesDecayEvent) e;
                Target t = Target.UNKNOWN;
                Map<String, Construct> mapEvent = evaluate_helper(event);

                mapEvent.put("location", ObjectGenerator.GetGenerator().location(event.getBlock().getLocation(), false));

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
    }

    @api
    public static class pre_anvil extends AbstractEvent {

        @Override
        public String getName() {
            return "pre_anvil";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when an item is put in a slot for repair by an anvil."
                    + "{viewers: all humanentities viewing the screen this event takes place in | inventory | result"
                    + "| rename : Get the name to be applied to the repaired item."
                    + "| cost : Get the experience cost (in levels) to complete the current repair.}"
                    + "{}"
                    + "{result}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
            if(event instanceof MCPrepareAnvilEvent) {

                MCPrepareAnvilEvent e = (MCPrepareAnvilEvent) event;
                Map<String, Construct> ret = evaluate_helper(e);
                Target t = Target.UNKNOWN;

                CArray viewers = new CArray(t);
                for(MCHumanEntity v : e.getViewers()) {
                    viewers.push(new CString(v.getName(), t), t);
                }
                ret.put("viewers", viewers);

                CArray inventory = CArray.GetAssociativeArray(t);
                MCInventory inv = e.getInventory();
                for(int i = 0; i < e.getInventory().getSize(); i++) {
                    inventory.set(i, ObjectGenerator.GetGenerator().item(e.getInventory().getItem(i), t), t);
                }
                ret.put("inventory", inventory);

                ret.put("result", ObjectGenerator.GetGenerator().item(e.getResult(), t));
                ret.put("cost", new CInt(e.getCost(), t));
                ret.put("rename", new CString(e.getRenameText(), t));

                return ret;

            } else {
                throw new EventException("Event received was not an MCPrepareAnvilEvent.");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCPrepareAnvilEvent) {
                if(key.equalsIgnoreCase("result")) {
                    if(value instanceof CArray) {
                        ((MCPrepareAnvilEvent) event).setResult(ObjectGenerator.GetGenerator().item(value, value.getTarget()));
                        return true;
                    } else if(value instanceof CNull) {
                        ((MCPrepareAnvilEvent) event).setResult(null);
                        return true;
                    } else {
                        throw new CRECastException("Expected an array but received " + value, value.getTarget());
                    }
                } else if(key.equalsIgnoreCase("cost")) {
                    ((MCPrepareAnvilEvent) event).setCost(Static.getInt32(value, value.getTarget()));
                    return true;
                }
            }
            return false;
        }
    }


    @api
    public static class egg_throw extends AbstractEvent {

        @Override
        public String getName() {
            return "egg_throw";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when a player throws an egg and it might hatch."
                    + "{player : the player | egg : Gets the egg involved in this event."
                    + "| hatchtype : Get the type of the mob being hatched"
                    + "| numhatch : Get the number of mob hatches from the egg."
                    + "| hatching : Gets whether the egg is hatching or not.}"
                    + "{}"
                    + "{hatching|hatchtype|numhatch}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCEggThrowEvent) {

                MCEggThrowEvent ete = (MCEggThrowEvent) e;
                Map<String, Construct> mapEvent = evaluate_helper(ete);
                Target t = Target.UNKNOWN;

                mapEvent.put("player", new CString(ete.getPlayer().getName(), t));
                mapEvent.put("egg", new CString(ete.getEgg().getUniqueId().toString(), t));
                mapEvent.put("hatchtype", new CString(ete.getHatchingType().name(), t));
                mapEvent.put("numhatch", new CInt(ete.getNumHatches(), t));
                mapEvent.put("hatching", CBoolean.get(ete.isHatching()));

                return mapEvent;

            } else {
                throw new EventException("Cannot conver event to PlayerEggThrowEvent.");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCEggThrowEvent) {
                if(key.equalsIgnoreCase("hatching")) {
                    ((MCEggThrowEvent) event).setHatching(Static.getBoolean(value));
                    return true;
                } else if(key.equalsIgnoreCase("hatchtype")) {
                    ((MCEggThrowEvent) event).setHatchingType(MCEntityType.valueOf(value.val()));
                    return true;
                } else if(key.equalsIgnoreCase("numhatch")) {
                    ((MCEggThrowEvent) event).setNumHatches(Byte.valueOf(value.val()));
                    return true;
                }
            }
            return false;
        }
    }

    @api
    public static class item_break extends AbstractEvent {

        @Override
        public String getName() {
            return "item_break";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Fired when a player's item breaks."
                    + "{ player : the player | item : Gets the item that broke. }"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCItemBreakEvent) {

                MCItemBreakEvent ibe = (MCItemBreakEvent) e;
                Map<String, Construct> mapEvent = evaluate_helper(ibe);
                Target t = Target.UNKNOWN;

                mapEvent.put("player", new CString(ibe.getPlayer().getName(), t));
                mapEvent.put("item", ObjectGenerator.GetGenerator().item(ibe.getBrokenItem(), t));

                return mapEvent;

            } else {
                throw new EventException("Cannot convert event to PlayerItemBreakEvent.");
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
    public static class locale_change extends AbstractEvent {

        @Override
        public String getName() {
            return "locale_change";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when a player changes their locale in the client settings."
                    + "{player : the Player | locale : the Locale}"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCLocaleChangeEvent) {

                MCLocaleChangeEvent lce = (MCLocaleChangeEvent) e;
                Map<String, Construct> mapEvent = evaluate_helper(e);
                Target t = Target.UNKNOWN;

                mapEvent.put("player", new CString(lce.getPlayer().getName(), t));
                mapEvent.put("locale", new CString(lce.getLocale(), t));

                return mapEvent;

            } else {
                throw new EventException("Cannot convert event to PlayerLocaleChangeEvent.");
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
    public static class player_advancement_done extends AbstractEvent {

        @Override
        public String getName() {
            return "player_advancement_done";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when a player has completed all criteria in an advancement."
                    + "{player : the Player"
                    + "| criteria : Get the advancement which has been completed. "
                    + "| key : Return the key identifier for this object."
                    + "| namespace : Return the namespaced identifier for this object.}"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCPlayerAdvancementDoneEvent) {

                MCPlayerAdvancementDoneEvent pade = (MCPlayerAdvancementDoneEvent) e;
                Map<String, Construct> mapEvent = evaluate_helper(e);
                Target t = Target.UNKNOWN;

                mapEvent.put("player", new CString(pade.getPlayer().getName(), t));

                CArray criteria = new CArray(t);
                for(String s : pade.getAdvancement().getCriteria()) {
                    criteria.push(new CString(s, t), t);
                }
                mapEvent.put("criteria", criteria);

                mapEvent.put("key", new CString(pade.getAdvancement().getKey().getKey(), t));
                mapEvent.put("namespace", new CString(pade.getAdvancement().getKey().getNamespace(), t));

                return mapEvent;

            } else {
                throw new EventException("Cannot convert event to PlayerAdvancementDoneEvent.");
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
    public static class player_bucket_fill extends AbstractEvent {

        @Override
        public String getName() {
            return "player_bucket_fill";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when a player interacts with a Bucket."
                    + "{player : the Player | block : Return the block clicked."
                    + "| blockface : Get the face on the clicked block."
                    + "| bucket : Returns the bucket used in this event."
                    + "| item : Get the resulting item in hand after the bucket event. }"
                    + "{}"
                    + "{item}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCPlayerBucketEvent) {

                MCPlayerBucketEvent pbe = (MCPlayerBucketEvent) e;
                Map<String, Construct> mapEvent = evaluate_helper(e);
                Target t = Target.UNKNOWN;

                MCBlock block = pbe.getBlockClicked();

                mapEvent.put("player", new CString(pbe.getPlayer().getName(), t));
                mapEvent.put("block", new CString(block.getType().getName(), t));
                mapEvent.put("location", ObjectGenerator.GetGenerator().location(block.getLocation(), false));

                CArray face = CArray.GetAssociativeArray(t);
                face.set("x", new CInt(pbe.getBlockFace().getModX(), t), t);
                face.set("y", new CInt(pbe.getBlockFace().getModY(), t), t);
                face.set("z", new CInt(pbe.getBlockFace().getModZ(), t), t);
                mapEvent.put("blockface", face);

                mapEvent.put("bucket", new CString(pbe.getBucket().getName(), t));
                mapEvent.put("item", ObjectGenerator.GetGenerator().item(pbe.getItemStack(), t));

                return mapEvent;

            } else {
                throw new EventException("Cannot convert event to PlayerBucketEvent.");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCPlayerBucketEvent) {
                if(key.equalsIgnoreCase("item") && value instanceof CArray) {
                    ((MCPlayerBucketEvent) event).setItemStack(ObjectGenerator.GetGenerator().item(value, Target.UNKNOWN));
                    return true;
                }
            }
            return false;
        }
    }

    @api
    public static class player_bucket_empty extends AbstractEvent {

        @Override
        public String getName() {
            return "player_bucket_empty";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when a player interacts with a Bucket."
                    + "{player : the Player | block : Return the block clicked."
                    + "| blockface : Get the face on the clicked block."
                    + "| bucket : Returns the bucket used in this event."
                    + "| item : Get the resulting item in hand after the bucket event. }"
                    + "{}"
                    + "{item}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCPlayerBucketEvent) {

                MCPlayerBucketEvent pbe = (MCPlayerBucketEvent) e;
                Map<String, Construct> mapEvent = evaluate_helper(e);
                Target t = Target.UNKNOWN;

                MCBlock block = pbe.getBlockClicked();

                mapEvent.put("player", new CString(pbe.getPlayer().getName(), t));
                mapEvent.put("block", new CString(block.getType().getName(), t));
                mapEvent.put("location", ObjectGenerator.GetGenerator().location(block.getLocation(), false));

                CArray face = CArray.GetAssociativeArray(t);
                face.set("x", new CInt(pbe.getBlockFace().getModX(), t), t);
                face.set("y", new CInt(pbe.getBlockFace().getModY(), t), t);
                face.set("z", new CInt(pbe.getBlockFace().getModZ(), t), t);
                mapEvent.put("blockface", face);

                mapEvent.put("bucket", new CString(pbe.getBucket().getName(), t));
                mapEvent.put("item", ObjectGenerator.GetGenerator().item(pbe.getItemStack(), t));

                return mapEvent;

            } else {
                throw new EventException("Cannot convert event to PlayerBucketEvent.");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCPlayerBucketEvent) {
                if(key.equalsIgnoreCase("item") && value instanceof CArray) {
                    ((MCPlayerBucketEvent) event).setItemStack(ObjectGenerator.GetGenerator().item(value, Target.UNKNOWN));
                    return true;
                }
            }
            return false;
        }
    }

    @api
    public static class player_resourcepack_status extends AbstractEvent {

        @Override
        public String getName() {
            return "player_resourcepack_status";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when a player takes action on a resource pack request sent via"
                    + "{player : the Player | status : Gets the status of this pack.}"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCPlayerResourcepackStatusEvent) {

                MCPlayerResourcepackStatusEvent prse = (MCPlayerResourcepackStatusEvent) e;
                Map<String, Construct> mapEvent = evaluate_helper(e);
                Target t = Target.UNKNOWN;

                mapEvent.put("status", new CString(prse.getStatus(), t));
                mapEvent.put("player", new CString(prse.getPlayer().getName(), t));

                return mapEvent;

            } else {
                throw new EventException("Cannot convert event to PlayerResourcepackStatusEvent.");
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
    public static class player_statistic_increment extends AbstractEvent {

        @Override
        public String getName() {
            return "player_statistic_increment";
        }

        @Override
        public String docs() {
            return "{name : <macro> Gets the statistic name that is being incremented."
                    + "type : <macro> Gets the statistic type that is being incremented.}"
                    + "Called when a player statistic is incremented."
                    + "{entitytype : Gets the EntityType | player : the Player"
                    + "| material : Gets the Material. | prev : Gets the previous value of the statistic."
                    + "| new : Gets the new value of the statistic."
                    + "| name : Gets the statistic name that is being incremented."
                    + "| type : Gets the statistic type that is being incremented.}"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            if(e instanceof MCPlayerStatisticIncrementEvent) {
                MCPlayerStatisticIncrementEvent event = (MCPlayerStatisticIncrementEvent) e;
                Prefilters.match(prefilter, "name", event.getStatistic().name(), Prefilters.PrefilterType.MACRO);
                Prefilters.match(prefilter, "type", event.getStatistic().getType().name(), Prefilters.PrefilterType.MACRO);

                return true;
            } else {
                return false;
            }
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCPlayerStatisticIncrementEvent) {

                MCPlayerStatisticIncrementEvent psie = (MCPlayerStatisticIncrementEvent) e;
                Map<String, Construct> mapEvent = evaluate_helper(e);
                Target t = Target.UNKNOWN;

                if(psie.getEntityType() != null) {
                    mapEvent.put("entitytype", new CString(psie.getEntityType().name(), t));
                }

                if(psie.getMaterial() instanceof MCMaterial) {
                    mapEvent.put("material", new CString(((MCMaterial) psie.getMaterial()).getName(), t));
                }

                mapEvent.put("prev", psie.getPreviousValue());
                mapEvent.put("new", psie.getNewValue());
                mapEvent.put("player", new CString(psie.getPlayer().getName(), t));

                mapEvent.put("name", new CString(psie.getStatistic().name(), t));
                mapEvent.put("type", new CString(psie.getStatistic().getType().name(), t));

                return mapEvent;

            } else {
                throw new EventException("Cannot convert event to PlayerStatisticIncrementEvent.");
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
    public static class player_velocity extends AbstractEvent {

        @Override
        public String getName() {
            return "player_velocity";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when the velocity of a player changes."
                    + "{velocity : Gets the velocity vector that will be sent to the player."
                    + "| player : the Player}"
                    + "{}"
                    + "{velocity}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCPlayerVelocityEvent) {

                MCPlayerVelocityEvent pve = (MCPlayerVelocityEvent) e;
                Map<String, Construct> mapEvent = evaluate_helper(e);
                Target t = Target.UNKNOWN;

                CArray vector = ObjectGenerator.GetGenerator().vector(pve.getVelocity());
                vector.set("magnitude", new CDouble(pve.getVelocity().length(), t), t);
                mapEvent.put("velocity", vector);
                mapEvent.put("player", new CString(pve.getPlayer().getName(), t));

                return mapEvent;

            } else {
                throw new EventException("Cannot convert event to PlayerVelocityEvent.");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCPlayerVelocityEvent) {
                if(key.equalsIgnoreCase("velocity")) {
                    Vector3D vector = ObjectGenerator.GetGenerator().vector(value, Target.UNKNOWN);
                    ((MCPlayerVelocityEvent) event).setVelocity(vector);
                    return true;
                }
            }
            return false;
        }
    }

    @api
    public static class area_effect_cloud_apply extends AbstractEvent {

        @Override
        public String getName() {
            return "area_effect_cloud_apply";
        }

        @Override
        public String docs() {
            return "{}"
                    + " Called when a lingering potion applies it's effects. Happens once every 5 ticks."
                    + " {id: Returns the Entity involved in this event."
                    + " affected: Retrieves a mutable list of the effected entities}"
                    + " {}"
                    + " {}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCAreaEffectCloudApplyEvent) {

                MCAreaEffectCloudApplyEvent event = (MCAreaEffectCloudApplyEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                ret.put("id", new CString(event.getEntity().getUniqueId().toString(), t));

                CArray affected = new CArray(t);
                for(MCLivingEntity le : event.getAffectedEntities()) {
                    affected.push(new CString(le.getUniqueId().toString(), t), t);
                }
                ret.put("affected", affected);

                return ret;

            } else {
                throw new EventException("Could not convert to AreaEffectCloudApplyEvent");
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
    public static class creeper_power extends AbstractEvent {

        @Override
        public String getName() {
            return "creeper_power";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when a Creeper is struck by lightning."
                    + "{id : Returns the entityID involved in this event."
                    + "| lightning : Gets the lightning bolt which is striking the Creeper"
                    + "| cause : Gets the cause of the creeper being (un)powered.}"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCCreeperPowerEvent) {
                MCCreeperPowerEvent event = (MCCreeperPowerEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                ret.put("id", new CString(event.getEntity().getUniqueId().toString(), t));

                CArray lightning = new CArray(t);
                lightning.set("world", event.getLightning().getWorld().getName());
                lightning.set("id", event.getLightning().getUniqueId().toString());
                lightning.set("location", ObjectGenerator.GetGenerator().location(event.getLightning().getLocation(), false), t);
                lightning.set("is_effect", CBoolean.get(event.getLightning().isEffect()), t);
                ret.put("lightning", lightning);

                ret.put("cause", new CString(event.getCause(), t));

                return ret;
            } else {
                throw new EventException("Could not convert to CreeperPowerEvent");
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
    public static class entity_air_change extends AbstractEvent {

        @Override
        public String getName() {
            return "entity_air_change";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when the amount of air an entity has remaining changes."
                    + "{id : Returns the Entity involved in this event."
                    + "| amount : Gets the amount of air the entity has left.}"
                    + "{}"
                    + "{amount}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCEntityAirChangeEvent) {

                MCEntityAirChangeEvent event = (MCEntityAirChangeEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                ret.put("id", new CString(event.getEntity().getUniqueId().toString(), t));
                ret.put("amount", new CInt(event.getAmount(), t));

                return ret;
            } else {
                throw new EventException("Could not convert to EntityAirChangeEvent");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCEntityAirChangeEvent) {
                if(key.equalsIgnoreCase("amount")) {
                    ((MCEntityAirChangeEvent) event).setAmount(Static.getInt32(value, Target.UNKNOWN));
                    return true;
                }
            }
            return false;
        }
    }

    @api
    public static class entity_breed extends AbstractEvent {

        @Override
        public String getName() {
            return "entity_breed";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when one Entity breeds with another Entity."
                    + "{ bredwith : The ItemStack that was used to initiate breeding, if present."
                    + "| breeder : Gets the Entity responsible for breeding."
                    + "| id : Returns the Entity involved in this event"
                    + "| exp : Get the amount of experience granted by breeding."
                    + "| mother : Gets the parent creating this entity."
                    + "| father : Gets the other parent of the newly born entity.}"
                    + "{}"
                    + "{exp}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCEntityBreedEvent) {

                MCEntityBreedEvent event = (MCEntityBreedEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                ret.put("bredwith", ObjectGenerator.GetGenerator().item(event.getBredWith(), t));
                ret.put("breeder", new CString(event.getBreeder().getUniqueId().toString(), t));
                ret.put("id", new CString(event.getEntity().getUniqueId().toString(), t));
                ret.put("exp", new CInt(event.getExperience(), t));
                ret.put("father", new CString(event.getFather().getUniqueId().toString(), t));
                ret.put("mother", new CString(event.getMother().getUniqueId().toString(), t));

                return ret;

            } else {
                throw new EventException("Could not convert to EntityBreedEvent");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCEntityBreedEvent) {
                if(key.equalsIgnoreCase("exp")) {
                    ((MCEntityBreedEvent) event).setExperience(Static.getInt32(value, Target.UNKNOWN));
                    return true;
                }
            }
            return false;
        }
    }

    @api
    public static class entity_resurrect extends AbstractEvent {

        @Override
        public String getName() {
            return "entity_resurrect";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when an entity dies and may have the opportunity to be resurrected."
                    + "{id : Returns the Entity involved in this event.}"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCEntityResurrectEvent) {

                MCEntityResurrectEvent event = (MCEntityResurrectEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                ret.put("id", new CString(event.getEntity().getUniqueId().toString(), t));

                return ret;

            } else {
                throw new EventException("Could not convert to EntityResurrectEvent");
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
    public static class entity_shoot_bow extends AbstractEvent {

        @Override
        public String getName() {
            return "entity_shoot_bow";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when a LivingEntity shoots a bow firing an arrow."
                    + "{bow : Gets the bow ItemStack used to fire the arrow."
                    + "| id : Returns the Entity involved in this event."
                    + "| force : Gets the force the arrow was launched with."
                    + "| projectile : Gets the projectile which will be launched by this event."
                    + "| velocity : Gets the projectile velocity.}"
                    + "{}"
                    + "{projectile}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCEntityShootBowEvent) {

                MCEntityShootBowEvent event = (MCEntityShootBowEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                ret.put("bow", ObjectGenerator.GetGenerator().item(event.getBow(), t));
                ret.put("id", new CString(event.getEntity().getUniqueId().toString(), t));
                ret.put("force", new CDouble(event.getForce(), t));
                ret.put("projectile", new CString(event.getProjectile().getUniqueId().toString(), t));

                CArray ve = ObjectGenerator.GetGenerator().vector(event.getProjectile().getVelocity(), t);
                ve.set("magnitude", new CDouble(event.getProjectile().getVelocity().length(), t), t);
                ret.put("velocity", ve);

                return ret;

            } else {
                throw new EventException("Could not convert to EntityShootBowEvent.");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCEntityShootBowEvent) {
                if(key.equalsIgnoreCase("projectile")) {
                    MCEntity le;
                    if(value instanceof CNull) {
                        le = null;
                    } else {
                        le = Static.getEntity(value, Target.UNKNOWN);
                    }
                    if(le != null) {
                        ((MCEntityShootBowEvent) event).setProjectile(le);
                    } else {
                        throw new CREBadEntityException("That entity (UUID: " + value.val() + ")is not exists.", Target.UNKNOWN);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    @api
    public static class entity_tame extends AbstractEvent {

        @Override
        public String getName() {
            return "entity_tame";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Thrown when a LivingEntity is tamed."
                    + "{player : Gets the owning AnimalTamer."
                    + "tamed : Returns the Entity involved in this event.}"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCEntityTameEvent) {

                MCEntityTameEvent event = (MCEntityTameEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                ret.put("player", new CString(event.getOwner().getName(), t));
                ret.put("tamed", new CString(event.getEntity().getUniqueId().toString(), t));

                return ret;

            } else {
                throw new EventException("Could not convert to EntityTameEvent");
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
    public static class item_merge extends AbstractEvent {

        @Override
        public String getName() {
            return "item_merge";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Thrown when Item merge."
                    + "{item : Returns the Entity involved in this event."
                    + "target : Gets the Item entity the main Item is being merged into.}"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCItemMergeEvent) {

                MCItemMergeEvent event = (MCItemMergeEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                ret.put("item", ObjectGenerator.GetGenerator().item(event.getEntity().getItemStack(), t));
                ret.put("target", ObjectGenerator.GetGenerator().item(event.getTarget().getItemStack(), t));

                return ret;

            } else {
                throw new EventException("Could not convert to ItemMergeEvent");
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
    public static class sheep_dye_wool extends AbstractEvent {

        @Override
        public String getName() {
            return "sheep_dye_wool";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when a sheep's wool is dyed."
                    + "{color : Gets the DyeColor the sheep is being dyed."
                    + "| id : Returns the Sheep's EntityID involved in this event.}"
                    + "{}"
                    + "{color}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCSheepDyeWoolEvent) {

                MCSheepDyeWoolEvent event = (MCSheepDyeWoolEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                ret.put("id", new CString(event.getEntity().getUniqueId().toString(), t));
                ret.put("color", new CString(event.getColor().name(), t));

                return ret;


            } else {
                throw new EventException("Could not convert to SheepDyeWoolEvent");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCSheepDyeWoolEvent) {
                if(key.equalsIgnoreCase("color")) {
                    String[] possible = new String[] {"BLACK", "BLUE", "BROWN", "CYAN", "GRAY", "GREEN", "LIGHT_BLUE", "LIGHT_GRAY", "LIME",
                            "MAGENTA", "ORANGE", "PINK", "PUPPLE", "RED", "WHITE", "YELLOW"};
                    if(Arrays.asList(possible).contains(value.getValue().toUpperCase())) {
                        ((MCSheepDyeWoolEvent) event).setColor(MCDyeColor.valueOf(value.getValue().toUpperCase()));
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @api
    public static class sheep_regrow_wool extends AbstractEvent {

        @Override
        public String getName() {
            return "sheep_regrow_wool";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when a sheep regrows its wool."
                    + "{id : Returns the Entity involved in this event.}"
                    + "{}"
                    + "{}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCSheepRegrowWoolEvent) {

                MCSheepRegrowWoolEvent event = (MCSheepRegrowWoolEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                ret.put("id", new CString(event.getEnity().getUniqueId().toString(), t));

                return ret;

            } else {
                throw new EventException("Could not convert to SheepRegrowWoolEvent");
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
    public static class slime_split extends AbstractEvent {

        @Override
        public String getName() {
            return "slime_split";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when a Slime splits into smaller Slimes upon death."
                    + "{count : Gets the amount of smaller slimes to spawn."
                    + "| id : Returns the Entity involved in this event.}"
                    + "{}"
                    + "{count}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCSlimeSplitEvent) {

                MCSlimeSplitEvent event = (MCSlimeSplitEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                ret.put("count", new CInt(event.getCount(), t));
                ret.put("id", new CString(event.getEntity().getUniqueId().toString(), t));

                return ret;

            } else {
                throw new EventException("Could not convert to SlimeSplitEvent");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCSlimeSplitEvent) {
                if(key.equalsIgnoreCase("count")) {
                    ((MCSlimeSplitEvent) event).setCount(Static.getInt32(value, Target.UNKNOWN));
                    return true;
                }
            }
            return false;
        }
    }

    @api
    public static class villager_acquire_trade extends AbstractEvent {

        @Override
        public String getName() {
            return "villager_acquire_trade";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called whenever a villager acquires a new trade."
                    + "{id : Returns the Entity involved in this event. "
                    + "| recipe : Get the recipe to be acquired. }"
                    + "{}"
                    + "{recipe}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCVillagerAcquireTradeEvent) {

                MCVillagerAcquireTradeEvent event = (MCVillagerAcquireTradeEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                ret.put("id", new CString(event.getEntity().getUniqueId().toString(), t));

                CArray recipe = new CArray(t);
                CArray ingred = new CArray(t);
                for(MCItemStack is : event.getRecipe().getIngredients()) {
                    ingred.push(ObjectGenerator.GetGenerator().item(is, t), t);
                }
                recipe.set("ingredients", ingred, t);
                recipe.set("maxuses", new CInt(event.getRecipe().getMaxUses(), t), t);
                recipe.set("uses", new CInt(event.getRecipe().getUses(), t), t);
                recipe.set("result", ObjectGenerator.GetGenerator().item(event.getRecipe().getResult(), t), t);
                recipe.set("expreward", CBoolean.get(event.getRecipe().hasExperienceReward()), t);

                ret.put("recipe", recipe);

                return ret;

            } else {
                throw new EventException("Could not convert to VillagerAcquireTradeEvent");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCVillagerAcquireTradeEvent) {
                if(key.equalsIgnoreCase("recipe")) {
                    if(value instanceof CArray) {
                        CArray arr = (CArray) value;
                        MCVillagerAcquireTradeEvent e = (MCVillagerAcquireTradeEvent) event;
                        Target t = Target.UNKNOWN;
                        arr.set("type", new CString("MERCHANT", t), t);
                        MCMerchantRecipe mr = (MCMerchantRecipe) ObjectGenerator.GetGenerator().recipe(arr, t);
                        e.setRecipe(mr);
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @api
    public static class villager_replenish_trade extends AbstractEvent {

        @Override
        public String getName() {
            return "villager_replenish_trade";
        }

        @Override
        public String docs() {
            return "{}"
                    + "Called when a villager's trade's maximum uses is increased, due to a player's trade."
                    + "{bonus : Get the bonus uses added."
                    + "| id : Returns the Entity involved in this event."
                    + "| recipe : Get the recipe to replenish.}"
                    + "{}"
                    + "{bonus|recipe}";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            return true;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
            if(e instanceof MCVillagerReplenishTradeEvent) {

                MCVillagerReplenishTradeEvent event = (MCVillagerReplenishTradeEvent) e;
                Map<String, Construct> ret = evaluate_helper(event);
                Target t = Target.UNKNOWN;

                ret.put("bonus", event.getBonus());
                ret.put("id", new CString(event.getEntity().getUniqueId().toString(), t));

                CArray recipe = new CArray(t);
                CArray ingred = new CArray(t);
                for(MCItemStack is : event.getRecipe().getIngredients()) {
                    ingred.push(ObjectGenerator.GetGenerator().item(is, t), t);
                }
                recipe.set("ingredients", ingred, t);
                recipe.set("maxuses", new CInt(event.getRecipe().getMaxUses(), t), t);
                recipe.set("uses", new CInt(event.getRecipe().getUses(), t), t);
                recipe.set("result", ObjectGenerator.GetGenerator().item(event.getRecipe().getResult(), t), t);
                recipe.set("expreward", CBoolean.get(event.getRecipe().hasExperienceReward()), t);

                ret.put("recipe", recipe);


                return ret;

            } else {
                throw new EventException("Could not convert to VillagerReplenishTradeEvent");
            }
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            if(event instanceof MCVillagerReplenishTradeEvent) {
                MCVillagerReplenishTradeEvent e = (MCVillagerReplenishTradeEvent) event;
                if(key.equalsIgnoreCase("bonus")) {
                    e.setBonus(Static.getInt32(value, Target.UNKNOWN));
                    return true;
                } else if(key.equalsIgnoreCase("recipe")) {
                    if(value instanceof CArray) {
                        CArray arr = (CArray) value;
                        Target t = Target.UNKNOWN;
                        arr.set("type", new CString("MERCHANT", t), t);
                        MCMerchantRecipe mr = (MCMerchantRecipe) ObjectGenerator.GetGenerator().recipe(arr, t);
                        e.setRecipe(mr);
                        return true;
                    }
                }
            }
            return false;
        }
    }


}
