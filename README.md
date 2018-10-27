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
### map_initialize
map

## Example for map_initialize
```javascript
  bind(map_initialize, null, null, @e){
    
    @mapview = @e[mapview];
    remove_all_renderer(@mapview[key]);
    
    @renderer = create_renderer();
    set_renderer(@renderer, closure(@mapview, @mapcanvas, @player){
      if(@mapview[X] == 0 && @view[Z] == 0){
        @jpg = create_image('image.jpg');
        draw_canvas(@mapcanvas[key], 0, 0, @jpg);
      }
    })
    add_renderer(@mapview[key], @renderer);
    
  }
```

##상당한 개적화 이므로 사용주의!
##Warring! These cord are so dirty!
