package com.rgs.siegeworld.Player.Skills;

public class Attack {   //TODO Fix Bug | *Attack sometimes hits twice.*

    public static long startTime = System.currentTimeMillis();
    public static long time = System.currentTimeMillis();
    public static boolean attack = false;
    public static int frameX = 0;
    public static int attackSpeed = 500;

    public void create() {

    }

    public void show() {

    }

    public void render() {
        if(!attack){
            startTime = System.currentTimeMillis();
            frameX = 0;
        }
        time = System.currentTimeMillis() - startTime;
        if(attack) {
            if(time >  150) {
                frameX = 1;
            }
            if(time > 500) {
                frameX = 2;
                if(time < 520) {
                    System.out.println(time + " | " + attack);  //TODO Damage is dealt here <--
                }
            }
            if(time > 800) {
                frameX = 0;
            }
            if(time > 800 + attackSpeed) {
                startTime = System.currentTimeMillis();
                attack = false;
            }
        }
    }
}
