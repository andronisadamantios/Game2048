package game2048;

import java.util.Arrays;
import java.util.List;

public class test {

    public static void main(String args[]) {
        //ai.stats(ai::ai2, 10000);
        test1();
    }

    private static void test1() {
        int[] ar1 = new int[]{1, 2, 3, 4};
        int[] ar2 = new int[]{1, 2, 0, 4};
        int[] ar3 = new int[]{1, 0, 0, 0, 2, 0, 0, 0, 0, 4};
        int[] ar4 = new int[]{1, 2, 3, 4, 0, 0, 0, 0, 0, 0};

        int[] ar5 = new int[]{1, 2, 2, 4};
        int[] ar6 = new int[]{0, 2, 0, 4};
        int[] ar7 = new int[]{1, 1, 2, 2, 3, 4, 4, 4, 5, 4};

        final List<int[]> asList = Arrays.asList(ar1, ar2, ar3, ar4, ar5, ar6, ar7);

        asList.stream().map(test::upCol).forEach(System.out::println);
    }

    private static void test2() {
        for (Direction v : Direction.values()) {
            System.out.println(String.format("H: %d, V: %d", v.getH(), v.getV()));
        }

    }
    
    
    public static boolean upCol(int[] array ) {
        boolean result = false;
        int[] newArray = new int[array.length];
        int destIndex = 0;
        for (int srcIndex = 0; srcIndex < array.length; srcIndex++) {
            int n = array[srcIndex];
            if (n == 0) {
                continue;
            }
            if (newArray[destIndex] == n) {
                newArray[destIndex] <<= 1;
                destIndex++;
                result = true;
            } else {
                if (newArray[destIndex] != 0) {
                    destIndex++;
                }
                newArray[destIndex] = n;
                result = result || srcIndex != destIndex;
            }
        }
        if (newArray[destIndex] != 0) {
            destIndex++;
        }
//        for (int i = destIndex; i < destIndex; i++) {
//            array[i] = newArray[i];
//        }
//        for (int i = destIndex; i < array.length; i++) {
//            array[i] = 0;
//        }
        return result;
    }

}
