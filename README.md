## Задача 1. Игра-шутер

### Описание
Один из проектов — это игра-шутер (~~Half-Life 3, только никому ни слова~~).
У игрока должна быть возможность использовать разные виды оружия, в будущем в игру могут быть добавлены новые.
Необходимо спроектировать иерархию классов, а также систему слотов для оружия у игрока.

### Функционал программы
1. Создание объекта Player у которого будет набор оружия;
2. Возможность у игрока вызвать метод выстрела, внутри которого будут проверки на допустимость номера оружия для выстрела;
3. Классы оружия должны быть в пакете `weapon` (вспомните какие ДВЕ вещи нужно сделать, чтобы поместить классы в пакеты; мы их проходили на втором занятии);
4. Возможность выбора оружия для выстрела в main.

### Процесс реализации

1. Создадим класс игрока и функцию main.

* Класс Player содержит список оружия и метод "_выстрелить_"
```java 
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
```

* Метод `main`
```java
class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    Player player = new Player();
    int slot;

    System.out.format("У игрока %d слотов с оружием,"
                    + " введите номер, чтобы выстрелить,"
                    + " -1 чтобы выйти%n",
            player.getSlotsCount()
    );

    while (true) {

      slot = scanner.nextInt();
      if (slot == -1) {

        break;
      }
      player.shotWithWeapon(slot);
    }

    System.out.println("Game over!");
  }
}
```

2. Создадим классы для некоторых видов оружия.
* Базовый класс для всех видов оружия
```java
public class Weapon {

  public void shot() {
    System.out.println(getClass().getSimpleName() + " Стреляет");
  }
}
```

> Как "заставить" дочерний класс переопределить поведение некоторых методов базового класса, мы узнаем на следующей лекции.

* Создадим дочерние классы:
    * Water pistol;
    * Pistol;
    * Grenade launcher;
    * Slingshot;
    * Machine gun.

* В каждом из дочерних классов переопределите метод `shot()`, чтобы он изменил поведение оружия в соответствии с его типом. Например, чтобы оно выводило в консоль соответствующие выстрелу звуки: `Пив-Пав!`.
```java
/**
 * Grenade launcher
 */
public class GrenadeLauncher extends Weapon {

  @Override
  public void shot() {
    super.shot();
    System.out.println("БАБАХ-БУМ!!!");
  }
}
/**
 * Machine gun
 */
public class MachineGun extends Weapon {

  @Override
  public void shot() {
    super.shot();
    System.out.println("Тра-та-та-та-та!");
  }
}
/**
 * Pistol
 */
public class Pistol extends Weapon {

  @Override
  public void shot() {
    super.shot();
    System.out.println("Пуф!");
  }
}
/**
 * Slingshot
 */
public class Slingshot extends Weapon {

  @Override
  public void shot() {
    super.shot();
    System.out.println("Чпок!");
  }
}

/**
 * Water pistol
 */
public class WaterPistol extends Weapon {

  @Override
  public void shot() {
    super.shot();
    System.out.println("Пиу-пиу-пиу!");
  }
}
```