import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * FarmStand allows object representing a farm stand to be created
 * @author Grace Codd
 * Sources: 
 * https://www.geeksforgeeks.org/how-to-iterate-through-hashtable-in-java/
 */

public class FarmStand {

    /** Data structure associating name of product with ArrayList of individual items in inventory  */
    protected Hashtable<String, ArrayList<Product>> inventory;

    /** Default constructor initialzes inventory as empty Hashtable */
    public FarmStand(){
        inventory = new Hashtable<String, ArrayList<Product>>();
    }


    /**
     * Reads file containing inventory information and stores information in inventory class field
     * @throws IOException If an input or output exception occured such as if file cannot be found
     */
    public void fillInventory() throws IOException{
        //Instantiate FileReader object, passing file name 'inventory.txt' as arg, store in fileName
        //This will create a FileReader object representing the file containing inventory information
        FileReader fileName = new FileReader("inventory.txt");
        //Intantiate BufferedReader, passing fileName as arg, store in input variable
        //BufferedReader allows file to be read as input to code
        BufferedReader input = new BufferedReader(fileName);
        String name;    //Store name of product
        int stock;  //Store amount of stock of product
        double price;   //Store price of the product
        char type;  //Store type of the product
        ArrayList<Product> productArray;    //ArrayList to store individual Product objects

        //Priming read of file, store in name
        name = input.readLine();

        //Loop will continue while line is not empty
        //Pretest loop will not run if first line of file is empty
        while(name!=null){
            //Instantiate new ArrayList to store individual Product objects
            productArray = new ArrayList<Product>();
            //Read next line, convert to int by calling parseInt method, store in stock
            stock = Integer.parseInt(input.readLine());
            //Read next line, convert to double by calling parseDouble method, store in price
            price = Double.parseDouble(input.readLine());
            //Read next line, store first character in type
            type = input.readLine().charAt(0);

            //Loop will continue while there is still stock to be added to the inventory
            while(stock>0){
                //Instantiate Product passing name, price, and type into constructor, add product to ArrayList 
                productArray.add(new Product(name, price, type));
                //Decrement stock
                stock--;
            }
            //Add ArrayList of products to the inventory with name of the product acting as the key
            inventory.put(name, productArray);
            //Read next line, store in name
            name = input.readLine();
        }
        //Close input once loop is exited
        input.close();
    }

    /**
     * Prints inventory in a readable format
     */
    public void printInventory(){

        //Source: https://www.geeksforgeeks.org/how-to-iterate-through-hashtable-in-java/
        //Instantiate enumeration of each key in inventory Hashtable, store in e
        Enumeration<String> e = inventory.keys();

        //Loop will continue until end of enumeration, while there are more keys to be "read"
        while(e.hasMoreElements()){
            //Get next element in enumeration of keys, store in key
            String key = e.nextElement();
            
            //Get first item stored in ArrayList at key in inventory, store in object representing a product
            Product item = inventory.get(key).get(0);
            //Get number of object in ArrayList stored at key in inventory, store in stock
            int stock = inventory.get(key).size();

            //Print product name, price, and stock
            System.out.println("Product : " + key + "\t\t Price : $" + item.getPrice() + "\t\t Stock : " + stock);
        }
        
    }

}
    
