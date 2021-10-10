import java.util.Scanner;

public class CF-1216C {
    static int[][] white = new int[2][2];
    static int[][] black1 = new int[2][2];
    static int[][] black2 = new int[2][2];

    public static String whiteSheet() {
        for (int i=white[0][0]; i<=white[1][0]; i++) {
            if (isCoordinateNotInRectangle(new int[] {i, white[0][1]}, black1) && isCoordinateNotInRectangle(new int[] {i, white[1][1]}, black2)) {
                return "YES";
            }
        }

        for (int i=white[0][1]; i<=white[1][1]; i++) {
            if (isCoordinateNotInRectangle(new int[] {white[0][0], i}, black1) && isCoordinateNotInRectangle(new int[] {white[1][0], i}, black2)) {
                return "YES";
            }
        }

        return "NO";
    }

    public static boolean isCoordinateNotInRectangle(int[] coordinates, int[][] rectangle) {
        if (rectangle[0][0] > coordinates[0] || rectangle[1][0] < coordinates[0]) {
            return true;
        } else if (rectangle[0][1] > coordinates[1] || rectangle[1][1] < coordinates[1]) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        white[0][0] = sc.nextInt();
        white[0][1] = sc.nextInt();
        white[1][0] = sc.nextInt();
        white[1][1] = sc.nextInt();
        black1[0][0] = sc.nextInt();
        black1[0][1] = sc.nextInt();
        black1[1][0] = sc.nextInt();
        black1[1][1] = sc.nextInt();
        black2[0][0] = sc.nextInt();
        black2[0][1] = sc.nextInt();
        black2[1][0] = sc.nextInt();
        black2[1][1] = sc.nextInt();

        System.out.println(whiteSheet());
    }
}
