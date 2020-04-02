package lesson3.task1;

import java.util.HashMap;
import java.util.Map;

public class RemoveDuplicates {
    public static void main(String[] args) {
        String[] animals = {"Cat", "Sheep", "Bull", "Horse", "Dog", "Cat", "Cow", "Mouse", "Sheep", "Hamster", "Cow", "Pig", "Sheep", "Mouse"};

        HashMap<String, Integer> hashMap = new HashMap<>();

        int count = 0;
        for (String animal1 : animals) {
            for (String animal2 : animals) {
                if (animal1.equals(animal2)) {
                    count++;
                }
            }
            hashMap.put(animal1, count);
            count = 0;
        }

//        Integer count;
//        for (String animal : animals) {
//            count = hashMap.get(animal);
//            if (count == null) {
//                hashMap.put(animal, 1);
//            } else {
//                hashMap.put(animal, count + 1);
//            }
//        }

        for (Map.Entry<String, Integer> mapEntry : hashMap.entrySet()) {
            System.out.println(mapEntry.getKey() + ": " + mapEntry.getValue());
        }
    }
}
