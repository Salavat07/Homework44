package kg.salavat;

import java.util.Random;

public class Main {

    public static int[] health = {800, 250, 250, 250, 150};
    public static int[] damage = {50, 20, 20, 20, 15};
    public static String[] hitTypes = {"Physical", "Physical",
            "Magical", "Mental",};

    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            changeBossDefence();
            round();
            printStatistics();
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomNumber = r.nextInt(3) + 1;
        hitTypes[0] = hitTypes[randomNumber];
    }

    public static int medicPlayer(int playerIndex) {

        if (health[playerIndex] > 0 && health[4] > 0) {
            return damage[4] + health[playerIndex];
        }
        return health[playerIndex];
    }


    public static void round() {
        for (int i = 1; i <= 4; i++) {
            int healthRemain = bossHit(i);
            if (healthRemain < 0) {
                health[i] = 0;
            } else {
                health[i] = healthRemain;
            }

        }


        for (int i = 1; i <= 3; i++) {
            int healthRemain = playerHit(i);
            if (healthRemain < 0) {
                health[0] = 0;
            } else {
                health[0] = healthRemain;
            }
        }
        for (int u = 1; u <= 4; u++) {
            health[u] = medicPlayer(u);   //added medic here
        }
    }

    public static boolean isFinished() {
        if (health[0] <= 0) {
            System.out.println("Heroes won the game");
            return true;
        }
        if (health[1] <= 0 && health[2] <= 0 && health[3] <= 0) {
            System.out.println("Boss won the game");
            return true;
        }
        return false;
    }


    public static int playerHit(int playerIndex) {
        if (hitTypes[0].equals(hitTypes[playerIndex])) {
            Random r = new Random();
            int randomNumber = r.nextInt(7) + 2;
            System.out.println(hitTypes[playerIndex]
                    + " critically hits Boss" +
                    damage[playerIndex] * randomNumber);
            return health[0] - damage[playerIndex] * randomNumber;
        } else {
            return health[0] - damage[playerIndex];
        }

    }

    public static int bossHit(int playerIndex) {
        return health[playerIndex] - damage[0];
    }

    public static void printStatistics() {
        System.out.println("_________________");
        System.out.println("Boss health =" + health[0]);
        System.out.println("Boss defence =" + hitTypes[0]);
        System.out.println("Warrior health =" + health[1]);
        System.out.println("Magic health =" + health[2]);
        System.out.println("Mental health =" + health[3]);
        System.out.println("Medic health =" + health[4]);
        System.out.println("_________________");
    }


}










