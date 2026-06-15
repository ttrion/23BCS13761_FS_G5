import java.util.Scanner;

interface Notification {
    void send(String message);

    default void sendBulk(String[] messages) {
        for (String message : messages) {
            send(message);
        }
    }
}

class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Email Notification: " + message);
    }
}

class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("SMS Notification: " + message);
    }
}

class NotificationService {
    private Notification notification;

    public NotificationService(Notification notification) {
        this.notification = notification;
    }

    public void sendNotification(String message) {
        notification.send(message);
    }

    public void sendBulkNotifications(String[] messages) {
        notification.sendBulk(messages);
    }
}

public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();
        sc.nextLine();

        Notification notification;

        if (choice == 1) {
            notification = new EmailNotification();
        } else {
            notification = new SMSNotification();
        }

        NotificationService service = new NotificationService(notification);

        String message = sc.nextLine();
        service.sendNotification(message);

        int n = sc.nextInt();
        sc.nextLine();

        String[] messages = new String[n];

        for (int i = 0; i < n; i++) {
            messages[i] = sc.nextLine();
        }

        service.sendBulkNotifications(messages);

        sc.close();
    }
}