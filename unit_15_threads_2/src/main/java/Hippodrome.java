import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Hippodrome {
    private final int horseCount;
    //private final ConcurrentHashMap<Horse, Integer> score;
    private final ConcurrentNavigableMap<Integer, Horse> score;
    private final CountDownLatch startLatch;
    private final CountDownLatch finishLatch;
    private final AtomicInteger place;
    private final Horse[] horses;

    public Hippodrome(int horseCount) {
        this.horseCount = horseCount;
        //score = new ConcurrentHashMap<>();
        score = new ConcurrentSkipListMap<>();
        startLatch = new CountDownLatch(horseCount);
        finishLatch = new CountDownLatch(horseCount);
        place = new AtomicInteger(0);
        horses = new Horse[horseCount];
    }

    public Map<Integer, Horse> getScore() {
        return score;
    }

    public Optional<Integer> getPlace(Integer id) {
        return score.entrySet()
                .stream()
                .filter(entry -> id.equals(entry.getValue().id))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public synchronized void startCompetition(Horse[] horseOfRace) throws RuntimeException {
        if (horseOfRace.length != horseCount) throw new RuntimeException("Invalid participants` count");
        try {
            for (int i = 0; i < horseCount; i++) {
                horses[i] = horseOfRace[i];
                new Thread(horseOfRace[i]).start();
            }
            startLatch.await();
            finishLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Horse implements Runnable {
        private static final int TRACK_LENGTH = 1000;

        private static final int MIN_DIST_MOVE = 100;
        private static final int MAX_DIST_MOVE = 200;

        private static final int MIN_SLEEP = 400;
        private static final int MAX_SLEEP = 500;

        private final Random random;
        private final String horseName;
        private final Hippodrome hippodrome;
        private int currentDist;
        private final int id;

        public Horse(String name, int id, Hippodrome hippodrome) {
            this.horseName = name;
            this.id = id;
            this.hippodrome = hippodrome;
            random = new Random();
        }

        public String getHorseName() {
            return horseName;
        }

        @Override
        public void run() {
            System.out.printf("Horse %s start running\n", horseName);
            try {
                while (currentDist < TRACK_LENGTH) {
                    if (currentDist == 0) hippodrome.startLatch.countDown();
                    move();
                    System.out.println("Horse " + horseName + " run distance " + currentDist);
                    waiting();
                }
            } catch (RuntimeException e) {
                System.out.println("Horse cann`t continue moving");
            }
            System.out.printf("Horse %s finished\n", horseName);
            hippodrome.place.getAndIncrement();
            hippodrome.score.put(hippodrome.place.get(), this);

            hippodrome.finishLatch.countDown();

        }

        private void move() {
            int dist = random.nextInt(MAX_DIST_MOVE - MIN_DIST_MOVE) + MIN_DIST_MOVE;
            if (dist + currentDist >= TRACK_LENGTH) {
                currentDist = TRACK_LENGTH;
            } else {
                currentDist += dist;
            }
        }

        private void waiting() {
            try {
                int sleepTime = random.nextInt(MAX_SLEEP - MIN_SLEEP) + MIN_SLEEP;
                Thread.sleep(sleepTime);
                //System.out.println("Horse " + horseName + " sleep " + sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
