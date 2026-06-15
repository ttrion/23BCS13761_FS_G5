import java.util.Scanner;

class TicketBookingSystem {
    private int availableSeats;

    public TicketBookingSystem(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public synchronized void bookTicket(int seats) {
        String user = Thread.currentThread().getName();

        if (seats <= availableSeats) {
            System.out.println(user + " booked " + seats + " seat(s)");
            availableSeats -= seats;
            System.out.println("Remaining Seats: " + availableSeats);
        } else {
            System.out.println(user + " booking failed for " + seats + " seat(s)");
            System.out.println("Only " + availableSeats + " seat(s) available");
        }
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
}

class UserThread extends Thread {
    private TicketBookingSystem bookingSystem;
    private int seats;

    public UserThread(TicketBookingSystem bookingSystem, int seats, String name) {
        super(name);
        this.bookingSystem = bookingSystem;
        this.seats = seats;
    }

    @Override
    public void run() {
        bookingSystem.bookTicket(seats);
    }
}

public class Q3 {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        int totalSeats = sc.nextInt();
        int users = sc.nextInt();

        TicketBookingSystem bookingSystem = new TicketBookingSystem(totalSeats);

        UserThread[] threads = new UserThread[users];

        for (int i = 0; i < users; i++) {
            int seatsRequested = sc.nextInt();
            threads[i] = new UserThread(
                    bookingSystem,
                    seatsRequested,
                    "User-" + (i + 1)
            );
        }

        for (UserThread thread : threads) {
            thread.start();
        }

        for (UserThread thread : threads) {
            thread.join();
        }

        System.out.println("Final Available Seats: " + bookingSystem.getAvailableSeats());

        sc.close();
    }
}