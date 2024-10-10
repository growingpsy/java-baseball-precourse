package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;
public class Application {
    public static int answer1;
    public static int answer2;
    public static int answer3;

    public static void generateNumber() {
        //answer1, answer2, answer3에 랜덤으로 생성한 변수를 넣어준다.
        answer1 = Randoms.pickNumberInRange(1, 9);
        while (true) {
            answer2 = Randoms.pickNumberInRange(1, 9);
            if (answer2 != answer1) {
                break;
            }
        }
        while (true) {
            answer3 = Randoms.pickNumberInRange(1, 9);
            if ((answer1 != answer3) && (answer2 != answer3)) {
                break;
            }
        }
    }
    public static int[] getUserInput() {
        //문자열 입력
        String inputNumber = Console.readLine();

        if (inputNumber.length() != 3) {
            throw new IllegalArgumentException();
        }

        //문자가 포함되어 있으면 예외
        try {
            Integer.parseInt(inputNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }

        // 각 문자를 숫자로 변환
        return new int[]{
                inputNumber.charAt(0) - '0',
                inputNumber.charAt(1) - '0',
                inputNumber.charAt(2) - '0'
        };
    }
    public static boolean checkAnswer(int[] inputNumber) {
        return (inputNumber[0] == answer1) && (inputNumber[1] == answer2) && (inputNumber[2] == answer3);
    }
    public static void main(String[] args) {
        generateNumber();
    }
}