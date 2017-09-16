package pogo;
import java.io.Serializable;

/**
 *
 * @author cibin
 */
public class CartItem implements Serializable {
    
    private String productId;
    private String name;
    private double sellPrice;
    private int qty;
    private int balance;
    
    public CartItem() {
        productId = null;
        name = null;
        sellPrice = 0.0;
        qty = 0;
    }
    
    public CartItem(String productId, String name, double sellPrice, int qty) {
        this.productId = productId;
        this.name = name;
        this.sellPrice = sellPrice;
        this.qty = qty;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    
    
    
}
