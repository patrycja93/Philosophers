package pl.patrycja;

class Philosopher {
    private int number;
    private String name;
    private Chopsticks chopsticks;

    public Philosopher(int number, String name, Chopsticks chopsticks) {
        this.number = number;
        this.name = name;
        this.chopsticks = chopsticks;
    }

    synchronized void eat(int firstChopstick, int secondChopstick) throws InterruptedException {
        chopsticks.getChopsticks(firstChopstick, secondChopstick);
        Thread.sleep(5000);
        finishEat(firstChopstick, secondChopstick);
    }

    synchronized void finishEat(int firstChopstick, int secondChopstick) {
        chopsticks.placeChopsticks(firstChopstick, secondChopstick);
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}