package game2048;

public class utils {

    public static int getRGB2(int powerOf2) {
        return getRGB((int) log2(powerOf2));
    }

    public static double log2(int powerOf2) {
        return Math.log(powerOf2) / Math.log(2);
    }

    public static int getRGB(int ektheths) {
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

}
