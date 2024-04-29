package Models;

public class Stopwatch {
    private long startTime;
    private long stopTime;
    private boolean isRunning;

    public Stopwatch() {
        isRunning = false;
    }

    /**
     * Starts the stopwatch.
     * Sets the start time to the current system time and marks the stopwatch as running.
     */
    public void start() {
        this.startTime = System.currentTimeMillis();
        this.isRunning = true;
    }

    /**
     * Stops the stopwatch.
     * Sets the stop time to the current system time and marks the stopwatch as not running.
     */
    public void stop() {
        this.stopTime = System.currentTimeMillis();
        this.isRunning = false;
    }

    /**
     * Gets the elapsed time in seconds.
     * If the stopwatch is currently running, calculates the elapsed time from the start time.
     * If the stopwatch is stopped, calculates the elapsed time between start and stop times.
     *
     * @return The elapsed time in seconds
     */
    public long getElapsedTimeInSeconds() {
        long elapsedTimeMillis = (isRunning) ? System.currentTimeMillis() - startTime : stopTime - startTime;
        return elapsedTimeMillis / 1000;
    }

    /**
     * Checks if the stopwatch is currently running.
     *
     * @return True if the stopwatch is running, otherwise false
     */
    public boolean isRunning() {
        return isRunning;
    }
}
