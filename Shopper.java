import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Shopper class allows obects representing a shopper to be created so user may interact with FarmStand class
 * @author Grace Codd
 */
public class Shopper {

    /** Stores name of each item on shopping list */
    private ArrayList<String> groceryList;
    /** Shopper's budget for shopping */
    private double budget;
    /** Shopping cart to store items */
    protected Cart cart;

    /** Default constructor initializes groceryList as empty ArrayList, budget as zero, and cart as an object of Cart class */
    public Shopper(){
        this.groceryList = new ArrayList<String>();
        this.budget = 0;
        this.cart = new Cart();
    }

    /**
     * Manipulator for budget class field. Returns false if budget passed into method is not in valid format
     * @param budget String representing maximum amount to be spent on groceries
     * @return true if budget is in correct format and greater than zero, false if not
     */
    public boolean setBudget(String budget){
        //Try-catch will "try" to convert budget passed into method to a double data type
        try{
            //Convert budget string to double, store in budget class field
            this.budget = Double.parseDouble(budget);
            //If budget is less than or equal to zero, throw exception
            if(this.budget <= 0){
                throw new InputMismatchException();
            } 
            //Will return true if conversion is successful and budget is greater than zero
            else{
                return true;
            }
        //Catch block will "catch" any exceptions thrown by try block, including if budget string cannot be converted to double
        } catch (Exception a){
            //Print error message and return false
            System.out.println("Invalid input.");
            System.out.println("Budget must be greater than $0 and input may not contain any characters besides decimals and numbers.");
            return false;
        }  
    }

    /**
     * Accessor for budget class field
     * @return Maximum amount to be spent
     */
    public double getBudget(){
        return this.budget;
    }

    /**
     * Accessor for groceryList class field
     * @return List of items on grocery list
     */
    public ArrayList<String> getList(){
        return this.groceryList;
    }

    /**
     * Adds an item to the shopper's grocery list
     * @param itemName name of item to be added to list
     */
    public void addToList(String itemName){
        //Call add method from ArrayList class, passing itemName as arg
        groceryList.add(itemName);
    }

    /**
     * Adds an item to the shopper's cart
     * @param item Product to be added to cart
     */
    public void addToCart(Product item){
        cart.items.add(item);
    }

    /** 
     * Removes an item from the shoppers cart
     * @param itemName The name of the item to remove from cart
     * @param amount The number of units to remove from cart
     * @return ArrayList of products to be put back 
     */
    public ArrayList<Product> putBack(String itemName, int amount){
        //THANK YOU JORDAN FOR TAKING THE TIME TO FIGURE THIS OUT WITH ME!!!!!!!!
        int nRemoved = 0;   //Counter for items removed from cart
        ArrayList<Product> returnedItems = new ArrayList<Product>();    //Instantiate empty ArrayList, store in returnedItems
        //For loop will continue while counter i is less than amount
        for(int i = 0 ; i<amount ; i++){
            int item_num = 0; //Store index of element currently being evaluated 
            //Loop will continue while there are more items to be evaluated in cart
            while(item_num < cart.items.size()){
                //Check if name of item at index item_num is equal to name passed into method
                if(cart.items.get(item_num).getName().equals(itemName)){
                    //Get object stored at index item_num, store in itemObj 
                    Product itemObj = cart.items.get(item_num);
                    //Add itemObj to returnedItems ArrayList
                    returnedItems.add(itemObj);
                    //Call remove method from ArrayList class, passing item_num as arg. Will remove object at this index from cart
                    cart.items.remove(item_num);
                    //Increment counter
                    nRemoved++;
                    //Set item_num to size of cart to exit while loop
                    item_num = cart.items.size();
                }
                else{
                    //If item at index item_num does not match, increment item_num so loop will check next item in the ArrayList
                    item_num++;
                }
            }
        }
        //If the number of objects removed is less than the desired amount to be removed, print warning message
        if(nRemoved < amount){
            System.out.println("WARNING! You asked to remove " + amount + " but you only had " + nRemoved);
        }
        //Print how many items were removed from cart
        System.out.println("You put back " + nRemoved + " " + itemName + ".");
        return returnedItems;
    }

    /**
     * Checks if an item is present in the shopper's cart
     * @param itemName The name of the item to be checked for
     * @return True if amount of item in product is greater than zero, less if not
     */
    public boolean checkForItem(String itemName){
        //Call nProduct method from Cart class, passing itemName as arg. Return true if method returns a value greater than zero
        return (cart.nProduct(itemName) > 0);
    }

    /**
     * Checks if the shopper's cart is empty
     * @return True if number of items in cart is zero, false if not
     */
    public boolean cartEmpty(){
        //Call size method from ArrayList class, return true if method returns a value equal to zero
        return (cart.items.size() == 0);
    }

}
