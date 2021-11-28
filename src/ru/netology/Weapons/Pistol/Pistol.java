package ru.netology.Weapons.Pistol;

import ru.netology.Weapons.Weapon.Weapon;

public class Pistol extends Weapon {

    @Override
    public void shot() {
        super.shot();
        System.out.println("Пуф!");
    }
}
