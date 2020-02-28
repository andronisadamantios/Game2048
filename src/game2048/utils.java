package game2048;

public class utils {

    /**
     * συντόμευση
     *
     * @param powerOf2
     * @return
     */
    public static double log2(int powerOf2) {
        return Math.log(powerOf2) / Math.log(2);
    }

    /**
     * πως χρωματίζονται οι τιμές
     *
     * @param ektheths
     * @return
     */
    public static int getColorForValue(int ektheths) {
        // map [1,16] -> [0,766]
        int a = (ektheths - 1) * (766 / 15);
        int r;
        int g;
        int b;
        if (a < 256) {
            // yellow to red
            r = 255;
            g = 255 - a;
            b = 0;
        } else if (a < 511) {
            // red to cyan
            a -= 256;
            r = 255 - a;
            g = a;
            b = a;
        } else {
            // cyan to blue
            a -= 512;
            r = 0;
            g = 255 - a;
            b = 255;
        }
        return (255 << 24) | (r << 16) | (g << 8) | b;
    }

    private static int i = 0;

    public static void debugHelp(String msg) {
        i++;
        System.out.println(i + ") " + msg);
    }

}
