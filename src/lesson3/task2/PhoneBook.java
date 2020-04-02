package lesson3.task2;

import java.util.HashMap;
import java.util.HashSet;

public class PhoneBook {
    private HashMap<String, HashSet<Integer>> phoneBook = new HashMap<>();

    public void add(String name, int phone) {
        if (phoneBook.get(name) == null) {
            HashSet<Integer> phones = new HashSet<>();
            phones.add(phone);
            phoneBook.put(name, phones);
        } else {
            phoneBook.get(name).add(phone);
        }
    }

    public void get(String name) {
        System.out.println("Поиск в телефонном справочнике.\nФамилии " + name + " соответствуют следующие номера:");
        for (Integer phone : phoneBook.get(name)) {
            System.out.println(phone);
        }
    }

    public void show() {
        System.out.println(phoneBook);
    }
}

class PhoneBookDemo {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", 253697);
        phoneBook.add("Петров", 369264);
        phoneBook.add("Сидоров", 305256);
        phoneBook.add("Иванов", 319055);
        phoneBook.add("Антонов", 226521);
        phoneBook.add("Харитонов", 193457);
        phoneBook.add("Петров", 235002);
        phoneBook.add("Иванов", 300205);
//        phoneBook.show();
        phoneBook.get("Иванов");

    }
}
