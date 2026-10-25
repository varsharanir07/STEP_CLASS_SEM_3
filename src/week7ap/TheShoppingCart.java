package week7ap;

/**
 * Problem 5: The Shopping Cart
 * 
 * Scenario:
 * An online store's cart holds the prices of items you're about to buy.
 * 
 * Problem Statement:
 * Design a Cart class that stores item prices internally but only exposes the total and item count — never direct access to the prices themselves.
 * 
 * Requirements:
 * - Store item prices in a private array (assume a fixed maximum number of items).
 * - Provide a method to add an item's price to the cart.
 * - Provide a read-only total (sum of all prices) and a read-only item count — computed on request, not stored separately.
 * - Give the cart a final cart ID, fixed when it's created.
 */
class Cart {
    private final String cartId;
    private final double[] prices;
    private int count;

    public Cart(String cartId, int capacity) {
        this.cartId = cartId;
        this.prices = new double[capacity > 0 ? capacity : 10];
        this.count = 0;
    }

    public String getCartId() {
        return cartId;
    }

    public void addItem(double price) {
        if (price >= 0 && count < prices.length) {
            prices[count++] = price;
        }
    }

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += prices[i];
        }
        return total;
    }

    public int getItemCount() {
        return count;
    }
}

public class TheShoppingCart {
    public static void main(String[] args) {
        Cart cart = new Cart("CART-5", 20);
        System.out.println("Cart cart = new Cart(\"CART-5\", 20);");

        cart.addItem(250);
        cart.addItem(99);
        cart.addItem(151);

        System.out.println("cart.getTotal() -> " + (int) cart.getTotal());
        System.out.println("cart.getItemCount() -> " + cart.getItemCount());
    }
}
