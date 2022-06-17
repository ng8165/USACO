// Daisy Chains - USACO Bronze December 2020 (http://www.usaco.org/index.php?page=viewproblem&cpid=1048)
// This problem was completed on December 21, 2020, in 19 minutes, with 10/10 test cases passed

import java.util.Scanner;

public class daisy_2 {
    static int numFlowers;
    static float[] flowerPetals;

    public static int daisy() {
        int averagePhotos = 0;
        averagePhotos += numFlowers;

        for (int i=0; i<numFlowers; i++) {
            for (int j=i+1; j<numFlowers; j++) {
                float average = calculateAveragePetals(i, j);
                if (isAveragePhoto(i, j, average)) {
                    averagePhotos++;
                }
            }
        }

        return averagePhotos;
    }

    public static float calculateAveragePetals(int startRange, int stopRange) {
        float sum=0;

        for (int i=startRange; i<=stopRange; i++) {
            sum += flowerPetals[i];
        }

        return sum / (stopRange-startRange+1);
    }
    public static boolean isAveragePhoto(int startRange, int stopRange, float average) {
        for (int i=startRange; i<=stopRange; i++) {
            if (flowerPetals[i] == average) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // input
        Scanner sc = new Scanner(System.in);

        numFlowers = sc.nextInt();
        flowerPetals = new float[numFlowers];
        for (int i=0; i<numFlowers; i++) {
            flowerPetals[i] = sc.nextInt();
        }

        // algorithm
        int averagePhotos = daisy();

        // output
        System.out.println(averagePhotos);
    }
}
