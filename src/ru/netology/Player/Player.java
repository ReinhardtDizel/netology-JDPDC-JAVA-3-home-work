package ru.netology.Player;

import ru.netology.Weapons.GrenadeLauncher.GrenadeLauncher;
import ru.netology.Weapons.MachineGun.MachineGun;
import ru.netology.Weapons.Pistol.Pistol;
import ru.netology.Weapons.Slingshot.Slingshot;
import ru.netology.Weapons.WaterPistol.WaterPistol;
import ru.netology.Weapons.Weapon.Weapon;

public class Player {

    private Weapon[] weaponSlots;

    public Player() {

        weaponSlots = new Weapon[]{
                new WaterPistol(),
                new Pistol(),
                new GrenadeLauncher(),
                new Slingshot(),
                new MachineGun()
        };
    }

    public int getSlotsCount() {

        return weaponSlots.length;
    }

    public void shotWithWeapon(int slot) {

        String noWeaponSlot = "Такого оружия нет!";
        if (slot < 0 || slot >= weaponSlots.length) {

            System.out.println(noWeaponSlot);
        } else {

            Weapon weapon = weaponSlots[slot];
            weapon.shot();
        }
    }
}
