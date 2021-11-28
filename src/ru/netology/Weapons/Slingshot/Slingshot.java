package ru.netology.Weapons.Slingshot;

import ru.netology.Weapons.Weapon.Weapon;

public class Slingshot extends Weapon {

    @Override
    public void shot() {
        super.shot();
        System.out.println("Чпок!");
    }
}
