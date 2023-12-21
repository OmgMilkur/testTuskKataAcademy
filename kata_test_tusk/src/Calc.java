import java.util.Scanner;

public class Calc {
    public static void main(String[] args) throws Exception {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите выражение из двух чисел (Арабские или Римские): ");
            String input = scanner.nextLine();
            System.out.println(calc(input));
        }
    }

    public static String calc(String input) throws Exception {
        int firstNumber;
        int secondNumber;
        String firstNum;
        String secondNum;
        boolean isRoman;
        int result;
        String sign;

        String[] numbers = input.split("[*/+\\-]");

        if (numbers.length != 2) {
            throw new Exception("Операции: сложение, вычетание, умножение и деление могут выполняться только с двумя" +
                    " числами!");
        }

        firstNum = numbers[0].trim();
        secondNum = numbers[1].trim();

        if (firstNum.contains(".") || secondNum.contains(".")) {
            throw new Exception("Для ввода допускаются только целые числа!");
        }

        if (RomanNumerals.isRomanNumber(firstNum) && RomanNumerals.isRomanNumber(secondNum)) {
            isRoman = true;
            firstNumber = RomanNumerals.translateIntoArabic(firstNum);
            secondNumber = RomanNumerals.translateIntoArabic(secondNum);
        } else if (!RomanNumerals.isRomanNumber(firstNum) && !RomanNumerals.isRomanNumber(secondNum)) {
            isRoman = false;
            firstNumber = Integer.parseInt(firstNum);
            secondNumber = Integer.parseInt(secondNum);
        } else throw new Exception("Недопустимый формат чисел!");

        if (1 > firstNumber || 10 < firstNumber || 1 > secondNumber || 10 < secondNumber) {
            throw new Exception("Вводимые числа должны быть в диапазоне от 1 до 10 включительно!");
        }

        sign = checkSign(input);

        if (sign == null) throw new Exception("Недопустимая операция!");

        result = carryOutCalculations(firstNumber, secondNumber, sign);

        if (isRoman) {
            if (result <= 0) throw new Exception("Недопустимый результат для римского числа! Должно быть больше 0.");
            return RomanNumerals.translateIntoRoman(result);
        } else return String.valueOf(result);
    }

    static String checkSign(String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("/")) return "/";
        else if (input.contains("*")) return "*";
        else return null;
    }

    static int carryOutCalculations(int firstNumber, int secondNumber, String sign) {
        if (sign.equals("+")) return firstNumber + secondNumber;
        else if (sign.equals("-")) return firstNumber - secondNumber;
        else if (sign.equals("/")) return firstNumber / secondNumber;
        else return firstNumber * secondNumber;
    }
}

class RomanNumerals {
    static String[] romanNumeralsArray = new String[]{
            " ", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI",
            "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX",
            "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI",
            "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV",
            "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII",
            "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI",
            "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };

    static boolean isRomanNumber(String value) {
        for (int i = 0; i < romanNumeralsArray.length; i++) {
            if (value.equals(romanNumeralsArray[i])) {
                return true;
            }
        }
        return false;
    }

    static int translateIntoArabic(String romanNumber) {
        for (int i = 0; i < romanNumeralsArray.length; i++) {
            if (romanNumber.equals(romanNumeralsArray[i])) {
                return i;
            }
        }
        return 0;
    }

    static String translateIntoRoman(int number) {
        return romanNumeralsArray[number];
    }
}