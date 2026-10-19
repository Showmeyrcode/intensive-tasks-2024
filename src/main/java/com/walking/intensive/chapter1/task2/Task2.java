package com.walking.intensive.chapter1.task2;

/**
 * Реализуйте метод getFlatLocation(), который будет принимать параметрами следующие данные:
 * <ul>
 * <li> Количество этажей в доме;
 * <li> Количество подъездов;
 * <li> Номер нужной квартиры.
 * </ul>
 *
 * <p>Необходимо определить подъезд, этаж и расположение нужной квартиры относительно лифта,
 * руководствуясь следующими правилами:
 * <ul>
 * <li> На этаже 4 квартиры;
 * <li> Нумерация квартир возрастает по часовой стрелке.
 * </ul>
 *
 * <p>Примеры строки, возвращаемой из метода:
 * <ul>
 * <li> 1 кв – 1 подъезд, 1 этаж, слева от лифта, влево
 * <li> 2 кв – 1 подъезд, 1 этаж, слева от лифта, вправо
 * <li> 3 кв – 1 подъезд, 1 этаж, справа от лифта, влево
 * <li> 4 кв – 1 подъезд, 1 этаж, справа от лифта, вправо
 * </ul>
 *
 * <p>Если для дома с указанной этажностью и количеством подъездов квартиры с заданным номером не существует,
 * метод должен вернуть строку "Такой квартиры не существует".
 *
 * <p>Если хотя бы один из указанных параметров некорректный - например, отрицательное число или 0,
 * метод должен вернуть строку "Некорректные входные данные".
 *
 * <p><a href="https://github.com/KFalcon2022/intensive-tasks-2024/blob/master/README.md">Требования к оформлению</a>
 */
public class Task2 {
    public static void main(String[] args) {

    }

    static String getFlatLocation(int floorAmount, int entranceAmount, int flatNumber) {
        if (floorAmount <= 0 || entranceAmount <= 0 || flatNumber <= 0) {
            return "Некорректные входные данные";
        }

        int flatAmount = floorAmount * 4; // Квартир в одном подъезде
        int flatMaxNum = flatAmount * entranceAmount; // Всего квартир в доме

        if (flatNumber > flatMaxNum) {
            return "Такой квартиры не существует";
        }

        int currentEntrance = flatNumber % flatAmount == 0 ? flatNumber / flatAmount : (flatNumber / flatAmount) + 1;

        int flatFloor = (flatNumber - (flatAmount * (currentEntrance - 1))) / 4;
        if (flatNumber % 4 != 0) {
            flatFloor++;
        }

        String flatFloorLocation = flatNumber % 2 == 0 ? "вправо" : "влево"; // Расположение на этаже

        // Расположение относительно лифта
        String flatLiftLocation = switch (flatNumber % 4) {
            case 0, 3 -> " справа от лифта, " + flatFloorLocation;
            case 1, 2 -> " слева от лифта, " + flatFloorLocation;
            default -> throw new IllegalStateException("Unexpected value: " + flatNumber % 4);
        };

        return flatNumber + " кв - " + currentEntrance + " подъезд, " + flatFloor + " этаж," + flatLiftLocation;
    }
}
