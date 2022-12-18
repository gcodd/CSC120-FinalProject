/** 
 * Product class allows objects representing a product to be created
 * @author Grace Codd
 */
public class Product{

    /** The name of the product */
    private String name;
    /** The price of the product */
    private double price;
    /** The unit by which an individual product is sold (by the each, pint, or bunch) */
    private char type;

    /**
     * Constructor initializes each class field
     * @param name The name of the product
     * @param price The price of the product
     * @param type The type of product
     */
    public Product(String name, double price, char type){
        this.name = name;
        this.price = price;
        this.type = type;

    }

    /** 
     * Displays how much each unit of a product costs
     */
    public void printPrice(){
        //Switch statement compares type to each condition
        switch(this.type){
            //Condition A, item is sold by the each
            case 'A':
                System.out.print("Each " + this.name);
                System.out.printf(" costs $%.2f.", this.price);
                System.out.println();
                break;
            //Condition B, item is sold by the bunch    
            case 'B':
                System.out.print("Each bunch of " + this.name);
                System.out.printf(" costs $%.2f.", this.price);
                System.out.println();
                break;
            //Condition C, item is sold by the pint    
            case 'C':
                System.out.print("Each pint of " + this.name);
                System.out.printf(" costs $%.2f.", this.price);
                System.out.println();
                break;
        }
    }

    /** 
     * Accessor for product name
     * @return The name of the product
     */
    public String getName(){
        return name;
    }

    /**
     * Accessor for the price of the product
     * @return The price of a product
     */
    public double getPrice(){
        return price;
    }

    /**
     * Accessor for the type of product
     * @return The char assigned to product type
     */
    public char getType(){
        return type;
    }

}