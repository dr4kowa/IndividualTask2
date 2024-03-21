import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println("Практическое задание № 1.8, Индивидуальное задание № 2 \n" +
                "Выполнил: Цуранов Артем Юрьевич, РИБО-02-22, вариант № 2 \n" +
                "----------------------------------------------------------------------");
        HashMap<String, Telephone> telephonesMap = new HashMap<>();
        telephonesMap.put("X35235ZMD", new Telephone("Panasonic", "X35235ZMD", "white", false));
        telephonesMap.put("XYZ123456789", new Telephone("Samsung S10", "XYZ123456789", "black", true));
        telephonesMap.put("ASIOS77777QWERTY", new Telephone("Iphone X", "ASIOS77777QWERTY", "black", true));

        // Приветствие
        System.out.println("Привет, я Ваш личный ассистент и я здесь, чтобы помочь Вам со списком телефонов!");
        System.out.println("Кстати, вот и он:");
        int index = 0;
        for (Map.Entry<String, Telephone> entry : telephonesMap.entrySet()) {
            index += 1;
            String Model = entry.getValue().getTelephoneModel();
            String SerialNum = entry.getValue().getSerialNumber();
            String Color = entry.getValue().getTelephoneColor();
            boolean Mobile = entry.getValue().isMobilePhone();
            System.out.println(index + ". " + Model + ", " + SerialNum + ", " + Color + ", " + Mobile);
        }

        Scanner scan = new Scanner(System.in);
        int flag1 = 0;
        while (flag1 != 4) {
            System.out.println("Введите: 1 - для добавления телефона, 2 - для удаления телефона по серийному номеру, 3 - для удаления всего списка, 4 - для выхода:");

// Поиск ошибок
            if (scan.hasNextInt()) {
                flag1 = scan.nextInt();
                scan.nextLine();
            } else {
                System.out.println("Ошибка ввода, попробуйте снова");
            }
            if (flag1 < 1 || flag1 > 4) {
                System.out.println("Ошибка ввода, попробуйте снова");
                continue;
            }

// Добавление телефона в список
            if (flag1 == 1) {
                String phoneModel = "";
                while (phoneModel.isBlank()) {
                    System.out.println("Введите модель телефона:");
                    phoneModel = scan.nextLine();
                }
                String serialNum = "";
                boolean isNewSerialNum = false;
                while (serialNum.isBlank() && !isNewSerialNum) {
                    System.out.println("Введите серийный номер телефона:");
                    serialNum = scan.nextLine();
                    if (!serialNum.isBlank()) {
                        isNewSerialNum = true;
                        for (Map.Entry<String, Telephone> entr : telephonesMap.entrySet()) {
                            String SerNum = entr.getValue().getSerialNumber();
                            if (SerNum.equals(serialNum)) {
                                System.out.println("Данный серийный номер уже существует, попробуйте снова");
                                isNewSerialNum = false;
                                break;
                            }
                        }
                    }
                }
                serialNum = serialNum.toUpperCase();
                String TeleColor;
                while (true) {
                    System.out.println("Введите цвет телефона:");
                    TeleColor = scan.nextLine().toLowerCase();
                    try {
                        java.lang.reflect.Field field = Color.class.getField(TeleColor);
                        break;
                    } catch (Exception e) {
                        System.out.println("Вы ввели несуществующий цвет");
                    }
                }
                String Mobile = null;
                boolean flag3 = true;
                int Mob = 0;
                boolean isMobilePhone = true;
                while (flag3) {
                    System.out.println("Данный телефон - мобильный? 1 - да, 2 - нет:");
                    Mobile = scan.nextLine();
                    try {
                        Mob = Integer.parseInt(Mobile);
                        if (Mob == 1) {
                            isMobilePhone = true;
                            flag3 = false;
                        } else if (Mob == 2) {
                            isMobilePhone = false;
                            flag3 = false;
                        } else {
                            System.out.println("Ошибка ввода, попробуйте снова");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка ввода, попробуйте снова");
                    }
                }
                telephonesMap.put(serialNum, new Telephone(phoneModel, serialNum, TeleColor, isMobilePhone));
            }

// Удаление телефона из списка по серийному номеру
            if (flag1 == 2) {
                System.out.println("Введите серийный номер телефона, который Вы хотите удалить:");
                String SerialNumberRemove = scan.nextLine().toUpperCase();
                boolean isHasSerialNumber = false;
                for (Map.Entry<String, Telephone> entry : telephonesMap.entrySet()) {
                    String Key = entry.getKey();
                    if (SerialNumberRemove.equals(Key)) {
                        telephonesMap.remove(SerialNumberRemove);
                        System.out.println("Телефон с серийным номером " + SerialNumberRemove + " удален из списка");
                        isHasSerialNumber = true;
                        break;
                    }
                }
                if (!isHasSerialNumber) {
                    System.out.println("Телефона с таким серийным номером нет в списке");
                }
            }

// Удаление всего списка
            if (flag1 == 3) {
                telephonesMap.clear();
            }

// Выход из программы
            if (flag1 == 4) {
                System.out.println("Пока-пока");
                break;
            }

// Вывод списка
            index = 0;
            for (Map.Entry<String, Telephone> entry : telephonesMap.entrySet()) {
                index += 1;
                String Model = entry.getValue().getTelephoneModel();
                String SerialNum = entry.getValue().getSerialNumber();
                String Color = entry.getValue().getTelephoneColor();
                boolean Mobile = entry.getValue().isMobilePhone();
                System.out.println(index + ". " + Model + ", " + SerialNum + ", " + Color + ", " + Mobile);
            }
            if (telephonesMap.isEmpty()) {
                System.out.println("Список пуст ;(");
            }
        }
    }
}