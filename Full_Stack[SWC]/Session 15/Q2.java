import java.util.LinkedList;
import java.util.Queue;

class DBConnection {
    private int connectionId;
    private boolean inUse;

    public DBConnection(int connectionId) {
        this.connectionId = connectionId;
        this.inUse = false;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}

class ConnectionPool {
    private Queue<DBConnection> availableConnections;
    private int maxConnections;

    public ConnectionPool(int maxConnections) {
        this.maxConnections = maxConnections;
        availableConnections = new LinkedList<>();

        for (int i = 1; i <= maxConnections; i++) {
            availableConnections.offer(new DBConnection(i));
        }
    }

    public synchronized DBConnection getConnection() {
        if (availableConnections.isEmpty()) {
            System.out.println("No connections available");
            return null;
        }

        DBConnection connection = availableConnections.poll();
        connection.setInUse(true);

        System.out.println("Connection " + connection.getConnectionId() + " acquired");
        return connection;
    }

    public synchronized void releaseConnection(DBConnection connection) {
        if (connection != null && connection.isInUse()) {
            connection.setInUse(false);
            availableConnections.offer(connection);

            System.out.println("Connection " + connection.getConnectionId() + " returned to pool");
        }
    }

    public void displayPoolStatus() {
        System.out.println("Available Connections: " + availableConnections.size());
        System.out.println("Max Connections: " + maxConnections);
    }
}

public class Q2 {
    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool(3);

        pool.displayPoolStatus();

        DBConnection c1 = pool.getConnection();
        DBConnection c2 = pool.getConnection();
        DBConnection c3 = pool.getConnection();
        DBConnection c4 = pool.getConnection();

        pool.displayPoolStatus();

        pool.releaseConnection(c2);
        pool.releaseConnection(c1);

        pool.displayPoolStatus();

        DBConnection c5 = pool.getConnection();

        pool.displayPoolStatus();
    }
}