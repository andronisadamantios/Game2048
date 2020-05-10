package game2048;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class autoPlay {


    public static void stats(Consumer<Game2048> c, int fores) {
        Duration d = Duration.ZERO;
        Game2048 g = new Game2048();
        Map<Integer, Integer> m = new HashMap<>();
        Temporal t0;
        Temporal t1;
        for (int i = 0; i < fores; i++) {
            g.reset();
            t0 = Instant.now();
            c.accept(g);
            t1 = Instant.now();
            final int s = g.getMax();
            d = d.plus(Duration.between(t0, t1));
            //System.out.print(Integer.toString(s) + ", ");
            m.computeIfAbsent(s, (s1) -> 1);
            m.computeIfPresent(s, (s1, i1) -> i1 + 1);
        }

        System.out.println();
        System.out.println("fores: " + fores);
        System.out.println("time: " + d.toMillis() + " ms");
        System.out.println("rate: " + (((double) fores) / ((double) d.toMillis())) + " games/ms");
        System.out.println("");
        System.out.println(m.size() + " diaforetika results");
        System.out.println("max result: " + m.keySet().stream().max(Comparator.naturalOrder()).get());
        System.out.println("map:");
        m.entrySet().stream().sorted((o1, o2) -> o1.getKey().compareTo(o2.getKey()))
                .forEach(e -> {
                    System.out.println(e.getKey() + ":\t\t" + e.getValue() + "\t\t" + ((double) e.getValue()) / ((double) fores) * 100 + "%");
                });
    }

    public static void autoPlay1(Game2048 g) {
        boolean b = true;
        while (b) {
            b = g.up() || g.left();
        }
    }

    public static void autoPlay2(Game2048 g) {
        boolean b = true;
        while (b) {
            b = g.up() || g.left() || g.right() || g.down();
        }
    }

    public static void autoPlay3(Game2048 g) {
        boolean b = true;
        while (b) {
            b = g.up() || g.left() || g.down() || g.right();
        }
    }

}
