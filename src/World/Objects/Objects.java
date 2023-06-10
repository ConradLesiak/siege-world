package com.rgs.siegeworld.World.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.rgs.siegeworld.GUI.Menus.ForgeMenu.ForgeMenu;
import com.rgs.siegeworld.Player.Player;
import com.rgs.siegeworld.World.World;

import java.util.ArrayList;

public class Objects {

    public static ArrayList<RectangleMapObject> groundObjects = new ArrayList<RectangleMapObject>();
    public static Rectangle collisionArea = new Rectangle();
    public static WallZones wallZones = new WallZones();

    public void create() {
        int count = 1;
        for(int i = 0; i < World.map.getLayers().get("GroundObjects").getObjects().getCount(); i++){
            groundObjects.add((RectangleMapObject) World.map.getLayers().get("GroundObjects").getObjects().get(i));
            count++;
        }
        System.out.println(count + " Objects Added");
        wallZones.create();
    }

    public void render() {
        for(RectangleMapObject object : groundObjects) {
            if(Intersector.intersectRectangles(Player.collisionBounds, object.getRectangle(), collisionArea)) {
                if(collisionArea.getHeight() > 1) {     //Vertical Collisions
                    if(collisionArea.getY() < Player.collisionBounds.getY() + Player.collisionBounds.getHeight()/2) {  //Downward Collision
                        Player.playerPos.set(Player.playerPos.x, Player.playerPos.y + Player.moveSpeed * Gdx.graphics.getDeltaTime());
                    }
                    if(collisionArea.getY() + collisionArea.getHeight() > Player.collisionBounds.getY() + Player.collisionBounds.getHeight()/2) {  //Upward Collision
                        Player.playerPos.set(Player.playerPos.x, Player.playerPos.y - Player.moveSpeed * Gdx.graphics.getDeltaTime());

                    }
                }
                if(collisionArea.getWidth() > 1) {      //Horizontal Collisions
                    if(collisionArea.getX() + collisionArea.getWidth() > Player.collisionBounds.getX() + Player.collisionBounds.getWidth()/2) {  //Rightward Collision
                        Player.playerPos.set(Player.playerPos.x - Player.moveSpeed * Gdx.graphics.getDeltaTime(), Player.playerPos.y);
                    }
                    if(collisionArea.getX() < Player.collisionBounds.getX() + Player.collisionBounds.getWidth()/2) {  //Leftward Collision
                        Player.playerPos.set(Player.playerPos.x + Player.moveSpeed * Gdx.graphics.getDeltaTime(), Player.playerPos.y);
                    }
                }
            }
            if(Intersector.intersectRectangles(new Rectangle(Player.playerPos.x - 150, Player.playerPos.y - 100, 300, 200), object.getRectangle(), collisionArea)) {
                if(object.getProperties().containsKey("anvil")) {
                    ForgeMenu.forgeImage.setVisible(true);
                }
            } else {
                ForgeMenu.forgeImage.setVisible(false);
            }
        }

        wallZones.render();
    }

    public void update() {

    }
}
