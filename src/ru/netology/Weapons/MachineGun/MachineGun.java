package ru.netology.Weapons.MachineGun;

import ru.netology.Weapons.Weapon.Weapon;

public class MachineGun extends Weapon {

    @Override
    public void shot() {
        super.shot();
        System.out.println("Тра-та-та-та-та!");
    }
}
