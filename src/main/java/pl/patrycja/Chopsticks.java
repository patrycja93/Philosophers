package pl.patrycja;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Chopsticks {

    private List<Chopstick> chopsticks;

    public Chopsticks(int size) {
        chopsticks = IntStream.range(0, size)
                .mapToObj(Chopstick::new)
                .collect(Collectors.toList());
    }

    public void getChopsticks() {
        chopsticks.forEach(chopstick ->
                System.out.print(chopstick.getNumber() + " " + chopstick.isBusy() + " "));
    }

    void getSize() {
        long count = chopsticks.stream()
                .filter(chopstick -> !chopstick.isBusy())
                .count();
        System.out.println("Chopsticks size: " + count);
    }

    synchronized void getChopsticks(int firstChopstick, int secondChopstick) throws InterruptedException {
        int chopstickFirstNumber = firstChopstick % 5;
        int chopstickSecondNumber = secondChopstick % 5;
        while (chopsticks.get(chopstickFirstNumber).isBusy() || chopsticks.get(chopstickSecondNumber).isBusy()) {
            System.out.println(Thread.currentThread() + " is waiting on chopsticks number: " + chopstickFirstNumber + " and "  +chopstickSecondNumber);
            wait();
        }
        System.out.println(Thread.currentThread() + " is eating by the use of chopsticks number: " + chopstickFirstNumber + " and "  +chopstickSecondNumber);
        chopsticks.get(chopstickFirstNumber).setBusy(true);
        chopsticks.get(chopstickSecondNumber).setBusy(true);
    }

    synchronized void placeChopsticks(int firstChopstick, int secondChopstick) {
        int chopstickFirstNumber = firstChopstick % 5;
        int chopstickSecondNumber = secondChopstick % 5;
        chopsticks.get(chopstickFirstNumber).setBusy(false);
        chopsticks.get(chopstickSecondNumber).setBusy(false);
        System.out.println(Thread.currentThread() + " finished eat by the use of chopsticks number: " + chopstickFirstNumber + " and "  +chopstickSecondNumber);
        getChopsticks();
        getSize();
        notifyAll();
    }
}
