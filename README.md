# CHINeedit

some events and some function

## Functions

### is_material
is_material(materialname)
### player_locale
is_material(player)
### repsawn
respawn(player)

## Map Initialize Event's Functions

### add_renderer
add_renderer(mapview, maprenderer)
### create_image
create_image(path)
### create_charsprite
create_charsprite(array)
### create_cursor
create_cursor(array)
### create_cursor_collection
create_cursor_collection(array)
### create_renderer
create_renderer([boolean])
### draw_canvas
create_draw_canvas(canvas, x, y, url/path/color/text[, cursorcollection])
### edit_map_object
edit_map_object(object, key, value)
### key_info
key_info(key)
### key_list
key_list()
### key_remove
key_remove(key)
### remove_all_renderer
remove_all_renderer(mapview)
### set_renderer
set_renderer(maprenderer, closure)

## Events
### item_damage
damage[can modify], item, player
### player_recipe_discover
recipe, player
### block_physics
block, type
### entity_drop_item
item, entity
### entity_toggle_swim
is_swimming, entity
### pig_zombie_anger
entity, newanger[can modify], target
### player_riptide
item, player
### area_effect_cloud_apply
affected, entity
### brew
inventory, level, block
### brewing_stand_fuel
fuel, fuelpower[can modify], block, consuming[can modify]
### cauldron_level_change
entity, newlevel[can modify], oldlevel, reason, block
### egg_throw
egg, player, hatchingtype[can modify], hatchnum[can modify], hatching[can modify]
### entity_air_change
amount[can modify], entity
### entity_breed
bredwith, breeder, entity, getexp[can modify], getfather, getmother
### entity_resurrect
entity
### entity_shoot_bow
bow, entity, force, projectile[can modify]
### entity_tame
entity, owner
### furnace_burn
burntime[can modify], fuel, block, is_burning[can modify]
### furnace_extract
exp[can modify], amount, itemtype, player, block
### furnace_smelt
result[can modify], source, block
### item_merge
entity, target
### leaves_decay
block
### locale_change
locale, player
### player_bucket_fill
blockclicked, blockface, bucket, item[can_modify], palyer
### player_bucket_empty
blockclicked, blockface, bucket, item[can_modify], palyer
### resourcepack_status
player, status
### player_statistic_increment
entitytype, material, previousvalue, newvalue, statistic, player
### player_velocity
velocity[can modify], player
### pre_anvil
result[can modify], cost[can modify], renametext
### sheep_dye_wool
color[can modify], entity
### sheep_regrow_wool
entity
### slime_split
count[can modify], entity
### villager_acquire_trade
entity, recipe[can modify]
### villager_replenish_trade
bonus[can modify], entity, recipe[can modify]
### creeper_power
cause, entity, lightning
### item_break
item, player
### player_advancement_done
advancement, player
### map_initialize
map

## Example for map_initialize
```javascript
  bind(map_initialize, null, null, @e){
    
    @mapview = @e[mapview];
    remove_all_renderer(@mapview[key]);
    
    @renderer = create_renderer();
    set_renderer(@renderer, closure(@mapview, @mapcanvas, @player){
      if(@mapview[X] == 0 && @mapview[Z] == 0){
        @jpg = create_image('image.jpg');
        draw_canvas(@mapcanvas[key], 0, 0, @jpg);
      }
    })
    add_renderer(@mapview[key], @renderer);
    
  }
```

##상당한 개적화 이므로 사용주의!
##Warring! These cord are so dirty!
