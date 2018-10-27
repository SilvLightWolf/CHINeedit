# CHINeedit
some events and some function

##Functions
###is_material
###player_locale
###repsawn

##Map Initialize Event's Functions
###add_renderer
###create_image
###create_charsprite
###create_cursor
###create_cursor_collection
###create_renderer
###draw_canvas
###edit_map_object
###key_info
###key_list
###key_remove
###remove_all_renderer
###set_renderer

##Events
###item_damage
###player_recipe_discover
###block_physics
###entity_drop_item
###entity_toggle_swim
###pig_zombie_anger
###player_riptide
###map_initialize

##Example for map_initialize
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
