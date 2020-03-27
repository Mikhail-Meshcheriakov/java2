package lesson1;

interface Action {
    double run();

    double jump();
}

interface Overcoming {
    boolean overcomeObstacle(Action action);
}

class Human implements Action {
    private double maxDistance;
    private double maxHeight;

    public Human(double maxDistance, double maxHeight) {
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
    }

    @Override
    public double run() {
        System.out.println("Человек бежит. Максимальная дистанция: " + maxDistance);
        return maxDistance;
    }

    @Override
    public double jump() {
        System.out.println("Человек прыгает. Максимальная высота прыжка: " + maxHeight);
        return maxHeight;
    }
}

class Cat implements Action {
    private double maxDistance;
    private double maxHeight;

    public Cat(double maxDistance, double maxHeight) {
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
    }

    @Override
    public double run() {
        System.out.println("Кот бежит. Максимальная дистанция: " + maxDistance);
        return maxDistance;
    }

    @Override
    public double jump() {
        System.out.println("Кот прыгает. Максимальная высота прыжка: " + maxHeight);
        return maxHeight;
    }
}

class Robot implements Action {
    private double maxDistance;
    private double maxHeight;

    public Robot(double maxDistance, double maxHeight) {
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
    }

    @Override
    public double run() {
        System.out.println("Робот бежит. Максимальная дистанция: " + maxDistance);
        return maxDistance;
    }

    @Override
    public double jump() {
        System.out.println("Робот прыгает. Максимальная высота прыжка: " + maxHeight);
        return maxHeight;
    }
}

class Wall implements Overcoming {
    private double height;

    public Wall(double height) {
        this.height = height;
    }

    @Override
    public boolean overcomeObstacle(Action participant) {
        if (participant.jump() >= height) {
            System.out.println("Стена высотой " + height + ". Препятствие преодолено!");
            return true;
        }
        System.out.println("Стена высотой " + height + ". Препятствие не преодолено!");
        return false;
    }
}

class Treadmill implements Overcoming {
    private double distance;

    public Treadmill(double distance) {
        this.distance = distance;
    }

    @Override
    public boolean overcomeObstacle(Action participant) {
        if (participant.run() >= distance) {
            System.out.println("Бег дистанция " + distance + ". Препятствие преодолено!");
            return true;
        }
        System.out.println("Бег дистанция " + distance + ". Препятствие не преодолено!");
        return false;

    }
}

public class Lesson1 {
    public static void main(String[] args) {
        Action[] participants = new Action[]{new Human(500, 1.5),
                new Cat(300, 2),
                new Robot(100, 0.5),
                new Human(640, 1.3),
                new Cat(420, 2.2)};

        Overcoming[] obstacles = new Overcoming[]{new Wall(1.1),
                new Treadmill(400),
                new Wall(2.1)};

        for (Action participant : participants) {
            for (Overcoming obstacle : obstacles) {
                if (!obstacle.overcomeObstacle(participant)) {
                    System.out.println();
                    break;
                }
            }
        }
    }
}