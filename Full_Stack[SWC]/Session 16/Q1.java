import java.util.Scanner;

abstract class Ride {
    protected double surgeMultiplier;

    public Ride(boolean peakHour) {
        this.surgeMultiplier = peakHour ? 1.5 : 1.0;
    }

    public abstract double calculateFare(double distance);

    public abstract String getRideType();

    public void printFareDetails(double distance) {
        System.out.println("Ride Type: " + getRideType());
        System.out.println("Distance: " + distance + " km");
        System.out.println("Surge Multiplier: " + surgeMultiplier);
        System.out.println("Fare: " + calculateFare(distance));
    }
}

class MiniRide extends Ride {
    public MiniRide(boolean peakHour) {
        super(peakHour);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 10 * surgeMultiplier;
    }

    @Override
    public String getRideType() {
        return "Mini";
    }
}

class SedanRide extends Ride {
    public SedanRide(boolean peakHour) {
        super(peakHour);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 15 * surgeMultiplier;
    }

    @Override
    public String getRideType() {
        return "Sedan";
    }
}

class SuvRide extends Ride {
    public SuvRide(boolean peakHour) {
        super(peakHour);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 20 * surgeMultiplier;
    }

    @Override
    public String getRideType() {
        return "SUV";
    }
}

public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();
        double distance = sc.nextDouble();
        boolean peakHour = sc.nextBoolean();

        Ride ride;

        switch (choice) {
            case 1:
                ride = new MiniRide(peakHour);
                break;
            case 2:
                ride = new SedanRide(peakHour);
                break;
            case 3:
                ride = new SuvRide(peakHour);
                break;
            default:
                System.out.println("Invalid Ride Type");
                sc.close();
                return;
        }

        ride.printFareDetails(distance);

        sc.close();
    }
}