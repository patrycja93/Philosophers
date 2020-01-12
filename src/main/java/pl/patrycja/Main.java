package pl.patrycja;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

public class Main {

    private static BiFunction<Runnable, Philosopher, Thread> createPhilosophers =
            (runnable, philosopher) -> new Thread(runnable, philosopher.getName());

    private static String[] philosophersName = {"Konfucjusz", "Laozi", "Mencjusz", "Mo Di", "Xunzi"};
    private static Chopsticks chopsticks = new Chopsticks(5);

    public static void main(String[] args) {

        List<Thread> threadList = new ArrayList<>();

        IntStream.range(0, philosophersName.length)
                .mapToObj(i -> new Philosopher(i, philosophersName[i], chopsticks))
                .forEach(philosopher -> {
                    Runnable r = () -> {
                        int chopstickNumber = philosopher.getNumber();
                        try {
                            philosopher.eat(chopstickNumber, chopstickNumber + 1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    };
                    Thread thread = createPhilosophers.apply(r, philosopher);
                    threadList.add(thread);
                });

        threadList.forEach(Thread::start);
    }
}
