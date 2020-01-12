package pl.patrycja;

class Chopstick {

    private int number;
    private boolean isBusy;

    public Chopstick(int number) {
        this.number = number;
        this.isBusy = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
