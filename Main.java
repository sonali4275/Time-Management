import java.util.Random;
import java.util.Scanner;

// Q1: Define the class for time and other needed classes, with attributes, accessor/mutator methods, and constructors
class Time {
    private int hours;
    private int minutes;
    private int seconds;

    // Constructors
    public Time() {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }

    public Time(int hours, int minutes, int seconds) {
        if (isValidTime(hours, minutes, seconds)) {
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
        } else {
            throw new IllegalArgumentException("Invalid time provided");
        }
    }

    // Q2: Generate a time with keyboard input
    public Time(Scanner scanner) {
        System.out.print("Enter hours: ");
        this.hours = scanner.nextInt();
        System.out.print("Enter minutes: ");
        this.minutes = scanner.nextInt();
        System.out.print("Enter seconds: ");
        this.seconds = scanner.nextInt();
        if (!isValidTime(this.hours, this.minutes, this.seconds)) {
            throw new IllegalArgumentException("Invalid time provided");
        }
    }

    // Accessor methods
    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    // Mutator methods
    public void setHours(int hours) {
        if (hours >= 0 && hours < 24) {
            this.hours = hours;
        } else {
            throw new IllegalArgumentException("Invalid hours provided");
        }
    }

    public void setMinutes(int minutes) {
        if (minutes >= 0 && minutes < 60) {
            this.minutes = minutes;
        } else {
            throw new IllegalArgumentException("Invalid minutes provided");
        }
    }

    public void setSeconds(int seconds) {
        if (seconds >= 0 && seconds < 60) {
            this.seconds = seconds;
        } else {
            throw new IllegalArgumentException("Invalid seconds provided");
        }
    }

    // Check if the time is valid
    private boolean isValidTime(int hours, int minutes, int seconds) {
        return (hours >= 0 && hours < 24) && (minutes >= 0 && minutes < 60) && (seconds >= 0 && seconds < 60);
    }

    // Q3: Generate a random time
    public static Time generateRandomTime() {
        Random random = new Random();
        int hours = random.nextInt(24);
        int minutes = random.nextInt(60);
        int seconds = random.nextInt(60);
        return new Time(hours, minutes, seconds);
    }

    // Q4: Add two times
    public Time add(Time other) {
        int totalSeconds = this.toSeconds() + other.toSeconds();
        return Time.fromSeconds(totalSeconds);
    }

    // Convert the time to seconds
    public int toSeconds() {
        return hours * 3600 + minutes * 60 + seconds;
    }

    // Q6: Convert seconds to Time object
    public static Time fromSeconds(int totalSeconds) {
        int hours = (totalSeconds / 3600) % 24;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        return new Time(hours, minutes, seconds);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Q2: Generate a time with keyboard input
        System.out.println("Enter hours: ");
        int hours = scanner.nextInt();
        System.out.println("Enter minutes: ");
        int minutes = scanner.nextInt();
        System.out.println("Enter seconds: ");
        int seconds = scanner.nextInt();

        try {
            Time userTime = new Time(hours, minutes, seconds);
            System.out.println("User Time: " + userTime);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Q3: Generate a random time
        Time randomTime = Time.generateRandomTime();
        System.out.println("Random Time: " + randomTime);

        // Q4: Generate two random times and add them
        Time randomTime1 = Time.generateRandomTime();
        Time randomTime2 = Time.generateRandomTime();
        Time addedTime = randomTime1.add(randomTime2);
        System.out.println("Random Time 1: " + randomTime1);
        System.out.println("Random Time 2: " + randomTime2);
        System.out.println("Added Time: " + addedTime);

        // Q5: Generate multiple random times and add them
        System.out.println("Enter the number of random times to generate (max 10): ");
        int n = scanner.nextInt();
        if (n > 10) {
            n = 10; // Limit to 10 for testing purposes
        }
        Time totalRandomTime = new Time();
        for (int i = 0; i < n; i++) {
            Time randTime = Time.generateRandomTime();
            totalRandomTime = totalRandomTime.add(randTime);
            System.out.println("Random Time " + (i + 1) + ": " + randTime);
        }
        System.out.println("Total Random Time: " + totalRandomTime);

        // Q6: Input total seconds and convert to time
        System.out.println("Enter the number of total seconds inputs: ");
        int iterations = scanner.nextInt();
        for (int i = 0; i < iterations; i++) {
            System.out.println("Enter total seconds: ");
            int totalSeconds = scanner.nextInt();
            Time convertedTime = Time.fromSeconds(totalSeconds);
            System.out.println("Converted Time: " + convertedTime);
        }

        scanner.close();
    }
}
