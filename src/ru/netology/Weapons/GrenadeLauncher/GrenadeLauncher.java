package ru.netology.Weapons.GrenadeLauncher;

import ru.netology.Weapons.Weapon.Weapon;

public class GrenadeLauncher extends Weapon {

    @Override
    public void shot() {
        super.shot();
        System.out.println("БАБАХ-БУМ!!!");
    }
}
