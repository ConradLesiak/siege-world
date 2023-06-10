package com.rgs.siegeworld.Player.Controls;

public class Controls {

    MoveJoystick moveJoystick = new MoveJoystick();
    AttackJoystick attackJoystick = new AttackJoystick();
    PickUp pickUp = new PickUp();
    Zoom zoom = new Zoom();

    public void load() {
        moveJoystick.load();
        attackJoystick.load();
        pickUp.load();
        zoom.load();
    }

    public void create() {
        moveJoystick.create();
        attackJoystick.create();
        pickUp.create();
        zoom.create();
    }

    public void show() {
        moveJoystick.show();
        attackJoystick.show();
        pickUp.show();
        zoom.show();
    }

    public void render() {
        moveJoystick.render();
        attackJoystick.render();
        pickUp.render();
        zoom.render();
    }
}