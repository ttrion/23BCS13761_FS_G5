import java.util.*;

class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

class Inventory {
    private Map<Integer, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

    public void displayProducts() {
        for (Product product : products.values()) {
            System.out.println(product.getId() + " " +
                               product.getName() + " " +
                               product.getPrice() + " " +
                               product.getStock());
        }
    }
}

class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}

class Cart {
    private List<CartItem> items = new ArrayList<>();

    public boolean addToCart(Product product, int quantity) {
        if (product == null || quantity <= 0 || product.getStock() < quantity) {
            return false;
        }

        product.setStock(product.getStock() - quantity);
        items.add(new CartItem(product, quantity));
        return true;
    }

    public double calculateTotal() {
        double total = 0;

        for (CartItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    public void displayCart() {
        for (CartItem item : items) {
            System.out.println(
                item.getProduct().getName() + " " +
                item.getQuantity() + " " +
                item.getTotalPrice()
            );
        }

        System.out.println("Cart Total: " + calculateTotal());
    }
}

public class Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Inventory inventory = new Inventory();

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            sc.nextLine();
            String name = sc.nextLine();
            double price = sc.nextDouble();
            int stock = sc.nextInt();

            inventory.addProduct(new Product(id, name, price, stock));
        }

        Cart cart = new Cart();

        int cartItems = sc.nextInt();

        for (int i = 0; i < cartItems; i++) {
            int productId = sc.nextInt();
            int quantity = sc.nextInt();

            Product product = inventory.getProduct(productId);

            if (cart.addToCart(product, quantity)) {
                System.out.println("Added to cart");
            } else {
                System.out.println("Unable to add product");
            }
        }

        System.out.println("Products After Inventory Update:");
        inventory.displayProducts();

        System.out.println("Cart Details:");
        cart.displayCart();

        sc.close();
    }
}