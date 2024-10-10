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
    //다음 게임 진행 여부
    public static boolean checkNextGame() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String inputString = Console.readLine();
        if (inputString.contentEquals("1")) {
            return true;
        } else if (inputString.contentEquals("2")) {
            return false;
        } else {
            throw new IllegalArgumentException();
        }
    }


    public static boolean checkAnswer(int[] inputNumber) {
        return (inputNumber[0] == answer1) && (inputNumber[1] == answer2) && (inputNumber[2] == answer3);
    }
    public static void printStrikeBall(int[] inputNumber) {
        int strikeCount = 0;
        int ballCount = 0;
        StringBuilder printString = new StringBuilder();

        if (answer1 == inputNumber[0]) {
            strikeCount++;
        } else if (answer2 == inputNumber[0] || answer3 == inputNumber[0]) {
            ballCount++;
        }

        if (answer2 == inputNumber[1]) {
            strikeCount++;
        } else if (answer1 == inputNumber[1] || answer3 == inputNumber[1]) {
            ballCount++;
        }

        if (answer3 == inputNumber[2]) {
            strikeCount++;
        } else if (answer1 == inputNumber[2] || answer2 == inputNumber[2]) {
            ballCount++;
        }

        if (ballCount > 0) {
            printString.append(ballCount + "볼 ");
        }

        if (strikeCount > 0) {
            printString.append(strikeCount + "스트라이크");
        }

        if (ballCount == 0 && strikeCount == 0) {
            printString.append("낫싱");
        }

        System.out.println(printString);
    }
    public static boolean play() throws IllegalArgumentException {
        generateNumber();
        while (true) {
            int[] userData = getUserInput();
            boolean finish = checkAnswer(userData);
            printStrikeBall(userData);
            if (finish) {
                return checkNextGame();
            }
        }
    }

    public static void main(String[] args) {
        try {
            while (play()) {}
        } catch (IllegalArgumentException e) {
            System.exit(0);
        }
    }
}