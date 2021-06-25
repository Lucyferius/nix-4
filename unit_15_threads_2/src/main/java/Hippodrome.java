import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Hippodrome {
    private final int horseCount;
    private final ConcurrentHashMap<Horse, Integer> score;
    private final CountDownLatch latch;
    private final AtomicInteger place;
    private final Horse[] horses;

    public Hippodrome(int horseCount) {
        this.horseCount = horseCount;
        score = new ConcurrentHashMap<>();
        latch = new CountDownLatch(horseCount);
        place = new AtomicInteger(0);
        horses = new Horse[horseCount];
    }

    public Map<Horse, Integer> getScore(){
        while(score.size() != horseCount){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return  score.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    }

    public int getPlace (int id){
        while(score.size() != horseCount){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return score.get(horses[id-1]);
    }
    public synchronized void startCompetition(Horse[] horseOfRace) throws RuntimeException{
        if(horseOfRace.length!=horseCount) throw new RuntimeException("Invalid participants` count");
        for (int i = 0; i < horseCount; i++) {
            horses[i] = horseOfRace[i];
            new Thread (horseOfRace[i]).start();
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
        private final CountDownLatch countDownLatch;
        private final Hippodrome hippodrome;
        private int currentDist;

        public Horse(String name,  Hippodrome hippodrome) {
            this.horseName = name;
            this.hippodrome = hippodrome;
            this.countDownLatch = hippodrome.latch;
            random = new Random();
        }
        public String getHorseName(){
            return horseName;
        }
        @Override
        public void run() {
            try {
                System.out.printf("Horse %s start running\n", horseName);
                countDownLatch.countDown();

                try {
                    while (currentDist < TRACK_LENGTH) {
                        move();
                        System.out.println("Horse "+horseName+ " run distance " + currentDist);
                        waiting();
                    }
                }catch (RuntimeException e){
                    System.out.println("Horse cann`t continue moving");
                }
                System.out.printf("Horse %s finished\n", horseName);
                hippodrome.place.getAndIncrement();
                hippodrome.score.put(this, hippodrome.place.get());

                countDownLatch.await();

            } catch (InterruptedException e) {
                throw new RuntimeException("The race should be stopped");
            }
        }

        private void move() {
            int dist = random.nextInt(MAX_DIST_MOVE - MIN_DIST_MOVE) + MIN_DIST_MOVE;
            if(dist+currentDist >= TRACK_LENGTH){
                currentDist = TRACK_LENGTH;
            }else{
                currentDist += dist;
            }
        }
        private void waiting(){
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
