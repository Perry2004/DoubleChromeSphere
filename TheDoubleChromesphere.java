import java.util.Random;
import java.util.Scanner;


public class TheDoubleChromesphere {
    Scanner scanner = new Scanner(System.in);

    // static fields that contain how many numbers are met
    public static int blueWinNum = 0;
    public static int redWinNum = 0;

    // a class that holds both blue and red spheres' values
    // will be used to compare whether win a answer
    public class DoubleChromesphere {
        private BlueSphere blueValues;
        private RedSphere redValues;

        // default constructor;
        DoubleChromesphere() {
        }

        DoubleChromesphere(BlueSphere blue, RedSphere red) {
            redValues = red;
            blueValues = blue;
        }

        // accessor, get a blue sphere object
        public BlueSphere getBlueObjects() {
            return blueValues;
        }

        // accessor, get a red sphere object
        public RedSphere getRedObjects() {
            return redValues;
        }

        public void reportAll() {
            System.out.println("The prize numbers are: ");
            System.out.print("\tBlue: ");
        }
    }

    // class of blue spheres
    public class BlueSphere {
        int[] value = new int[1];

        // default constructor, assign a random int from 1 to 16 to a one int array,
        // value
        BlueSphere() {
            Random random = new Random();
            value[0] = random.nextInt(16) + 1;
        }

        // constructor with an int[] as parameter, assign the parameter int[0] to
        // value[0]
        BlueSphere(int[] val) {
            value[0] = val[0];
        }

        // accessor, return int[]
        public int[] getValue() {
            return value;
        }
    }

    // class of red spheres
    public class RedSphere {
        int[] value = new int[6];

        // default constructor, assign a random number from 1 to 33 to a six int array,
        // value
        RedSphere() {
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                value[i] = random.nextInt(33) + 1;
            }
        }

        // constructor with an int[] as parameter, assign all the elements in parameter
        // int[] to value[]
        RedSphere(int[] vals) {
            for (int i = 0; i < 6; i++) {
                value[i] = vals[i];
            }
        }

        // accessor, return int[6]
        public int[] getValue() {
            return value;
        }
    }

    // ask for input of the value of blue sphere, return an int[1]
    // due to the NoSuchElement exception, the scanner still need to be used by later methods, the io flow was not closed in this method
    public int[] blueInput() {
        int[] value = new int[1];
        // due to the NoSuchElement exception, the scanner still need to be used by later methods, the io flow was not closed in this method
        System.out.println("Please enter the value of the blue ball you choose (1 ~ 16): ");
        int input = scanner.nextInt();
        if (input >= 1 && input <= 16) {
            value[0] = input;
        } else {
            System.out.println("Can only choose blue sphere value from 1 to 16.");
            blueInput();
        }
        return value;
    }

    // ask for input of the value of blue spheres, return an int[6]
    public int[] redInput() {
        int[] value = new int[6];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the values of the red balls you choose (1 ~ 33): ");
        try {
            for (int i = 0; i < 6; i++) {
                int input = scanner.nextInt();
                if (input >= 1 && input <= 33) {
                    value[i] = input;
                } else {
                    System.out.println("Can only choose red sphere value from 1 to 33. ");
                    redInput();
                }
            }
            return value;
        } finally {
            scanner.close();
        }
    }

    public void reportWhichPrize(int cas) {
        switch (cas) {
            case 1:
                System.out.println("You won the first prize!");
                break;
            case 2:
                System.out.println("You won the second prize!");
                break;
            case 3:
                System.out.println("You won the thind prize!");
                break;
            case 4:
                System.out.println("You won the forth prize!");
                break;
            case 5:
                System.out.println("You won the fifth prize!");
                break;
            case 6:
                System.out.println("You won the sixth prize!");
                break;
        }
    }

    // Determin whether the user wins a prize, and which prize are won
    // Return a negative int if did not win any prize. Return a positive int if the
    // user won any prize
    public int whichPrize() {
        if (blueWinNum == 1 && redWinNum == 6) {
            return 1;
        } else if (blueWinNum == 0 && redWinNum == 6) {
            return 2;
        } else if (blueWinNum == 1 && redWinNum == 5) {
            return 3;
        } else if ((blueWinNum == 0 && redWinNum == 5) || (blueWinNum == 1 && redWinNum == 4)) {
            return 4;
        } else if ((blueWinNum == 0 && redWinNum == 4) || (blueWinNum == 1 && redWinNum == 3)) {
            return 5;
        } else if ((blueWinNum == 1 && redWinNum == 2) || (blueWinNum == 1 && redWinNum == 1) || (blueWinNum == 1 && redWinNum == 0)) {
            return 6;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        TheDoubleChromesphere tdc = new TheDoubleChromesphere();
        // create a answer object
        BlueSphere ansBlue = tdc.new BlueSphere();
        RedSphere ansRed = tdc.new RedSphere();
        // create a chosen object
        BlueSphere choBlue = tdc.new BlueSphere(tdc.blueInput());
        RedSphere choRed = tdc.new RedSphere(tdc.redInput());

        // compare
        int whichP = tdc.whichPrize();
        System.out.println();
        if (whichP > 0) {
            System.out.println("Congratulations! You won the prize!");
            tdc.reportWhichPrize(whichP);
        } else {
            System.out.println("Sorry, you did not won any prize.");
        }

        System.out.println("The result answers are: ");
        System.out.print("Blue: ");
        for (int val : ansBlue.getValue()) {
            System.out.print("\t" + val);
        }
        System.out.print("\nRed: ");
        for (int val : ansRed.getValue()) {
            System.out.print("\t" + val);
        }

        // report input and answer
        System.out.print("\nYour input numbers are: ");
        System.out.print("\nBlue: ");
        for (int val : choBlue.getValue()) {
            System.out.print("\t" + val);
        }
        System.out.print("\nRed: ");
        for (int val : choRed.getValue()) {
            System.out.print("\t" + val);
        }
        System.out.println();
    }
}
