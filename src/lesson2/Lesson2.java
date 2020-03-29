package lesson2;

class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "MyArraySizeException{} " + super.toString();
    }
}

class MyArrayDataException extends Exception {
    int row;
    int column;

    public MyArrayDataException(String message, int row, int column) {
        super(message);
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "MyArrayDataException{" +
                "row=" + row +
                ", column=" + column +
                "} " + super.toString();
    }
}

public class Lesson2 {
    public static void main(String[] args) {
        String[][] strArr = {{"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}};

        try {
            System.out.println(sumArray(strArr));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static int sumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        boolean size = true;
        if (array.length != 4) {
            size = false;
        } else {
            for (String[] strings : array) {
                if (strings.length != 4) {
                    size = false;
                    break;
                }
            }
        }
        if (!size) {
            throw new MyArraySizeException("Неверный размер массива");
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверное значение в массиве", i + 1, j + 1);
                }
            }
        }
        return sum;
    }
}
