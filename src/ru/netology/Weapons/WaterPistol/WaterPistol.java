package ru.netology.Weapons.WaterPistol;

import ru.netology.Weapons.Weapon.Weapon;

public class WaterPistol extends Weapon {

    @Override
    public void shot() {
        super.shot();
        System.out.println("Пиу-пиу-пиу!");
    }
}
