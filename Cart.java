import java.util.ArrayList;

/** 
 * Cart class allows objects representing a shopping cart to be created
 * @author Grace Codd
 */
public class Cart {

    /** ArrayList of Product objects represents items in cart*/
    protected ArrayList<Product> items;
    /** Total sum of the price of all items in the cart */
    private double total;

    /** Default constructor sets items to an empty ArrayList and sets total to 0 */
    public Cart(){
        items = new ArrayList<Product>();
        total = 0;
    }

    /** 
     * Returns the amount of a specific item found in the cart.
     * @param itemName The name of an item
     * @return The amount of an item in the cart
     */
    public int nProduct(String itemName){
        int i = 0;  //Counter
        //Enhanced for loop iterates through each Product object in the items ArrayList
        for(Product item : items){
            //If the name field of the item Product equals the itemName passed into the method, increment counter
            if(item.getName().equals(itemName)){
                i++;
            }
        }
        return i;
    }

    /**
     * Calculates total sum of the price of all items in the cart. 
     * @return The total price of all items in the cart
     */
    public double getTotal(){
        //Enhanced for loop iterates through each Product object in the items ArrayList
        for(Product item : items){
            //Call getPrice instance method from Product class, add price to total
            total += item.getPrice();
        }
        return total;
    }
}
