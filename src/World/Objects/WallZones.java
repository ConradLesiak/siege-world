package com.rgs.siegeworld.World.Objects;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.rgs.siegeworld.Player.Player;
import com.rgs.siegeworld.World.World;

import java.util.ArrayList;

public class WallZones {

    public static ArrayList<RectangleMapObject> zones = new ArrayList<RectangleMapObject>();

    public void create() {
        int count = 0;
        for(int i = 0; i < World.map.getLayers().get("WallZones").getObjects().getCount(); i++){
            zones.add((RectangleMapObject) World.map.getLayers().get("WallZones").getObjects().get(i));
            count++;
        }
        System.out.println(count + " Wall Zones Created");
    }

    public void render() {
        for(RectangleMapObject object : zones) {
            if(object.getRectangle().overlaps(Player.collisionBounds)) {
                World.renderWalls = true;
                break;
            } else {
                if(!object.getRectangle().contains(Player.playerPos)) {
                    World.renderWalls = false;
                }
            }
        }
    }

    public void update() {

    }
}
