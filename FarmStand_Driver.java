import java.util.ArrayList;
import java.util.Scanner;

import java.io.IOException;

/** 
 * FarmStand_Driver class executes game loop for farm stand game 
 * @author Grace Codd
 */
public class FarmStand_Driver {

    /** Scanner item allowing user input */
    public static Scanner keyboard = new Scanner(System.in);

    /**
     * Executes game loop
     * @param args Command line arguments
     * @throws IOException If input or output exception is thrown
     */
    public static void main(String[] args) throws IOException{

        boolean playing = true; //Flag for if player wants to keep playing, set as true
        boolean valid = true; //Flag for if input is valid, set as true
        boolean found = false; //Flag for if user has found item, set as false
        int points = 0; //Store number of points earned, set to zero
        String response = ""; //Store user response, set as empty string
        ArrayList<String> userList; //Store user grocery list
        int amount; //Store amount to be taken or put back


        //Call printBanner method to print intro message
        printBanner();
        
        //Do while loop will execute at least once
        do{
            //Instantiate FarmStand, store in myFarmStand
            FarmStand myFarmStand = new FarmStand();
            //Call fillInventory method from FarmStand class
            myFarmStand.fillInventory();
            //Instantiate Shopper, store in user
            Shopper user = new Shopper();
            //Prompt user for budget
            System.out.println("First, what is your budget?");
            System.out.println("Please enter your budget in dollars and cents in decimal format without any extra characters.");
            //Do while loop will repeat while user input for budget is not valid
            do{
                //Store keyboard input in response
                response = keyboard.nextLine();
                //Call setBudget method from Shopper class, passing response as arg
                //Store boolean returned in valid
                valid = user.setBudget(response);
            }while(!valid);

            System.out.printf("Great, your budget is $%.2f.", user.getBudget()); 
            System.out.println();
            System.out.println();
            //Prompt user to input grocery list
            System.out.println("Enter fruits/veggies you would like to look for at the farmstand.");
            System.out.println("HINT: Enter each item one at a time");
            System.out.println("Enter DONE when you are finished making your list");
            //Store user input in response
            response = keyboard.nextLine();
            //While loop will continue until user enters "done"
            while(!response.toUpperCase().equals("DONE")){
                //Add user response to shopping list
                user.addToList(response.toUpperCase());
                //Store next user input in response
                response = keyboard.nextLine();
            }
            //Call getList method to access grocery list, store in userList
            userList = user.getList();
            System.out.println();

            System.out.println("Time to get shopping!");
            System.out.println("We will now go through your list and find each item at the farm stand.");
            System.out.println();
            int index = 0;  //Store index of element in groceryList, set to zero
            String itemName = userList.get(index); //Store value stored in element at index in itemName
            System.out.println("The first item on your list is  " + itemName + ".");
            //User may now enter any of the commands
            System.out.println("Remember, your available commands are: CHECK PRICE, NEXT, TAKE, PUT BACK, FIND, DONE, HELP");
            //Store user input in response
            response = keyboard.nextLine().toUpperCase();
            System.out.println();
            
            //While loop will continue until end of list is reached or until user enters "DONE" command
            while(index < userList.size() && !response.equals("DONE")){
                //Switch statement compares response to each possible command
                switch(response){
                    //Response matches HELP 
                    case "HELP":
                        //Print available commands
                        System.out.println("Your available commands are: NEXT, CHECK PRICE, TAKE, PUT BACK, FIND, DONE, HELP");
                        break;
                    //Response matches CHECK PRICE    
                    case "CHECK PRICE":
                        //If item has been found, find item in inventory matching itemName and call printPrice method
                        if(found){
                            myFarmStand.inventory.get(itemName).get(0).printPrice();
                        }
                        //If not, print error message
                        else{
                            System.out.println("OOPS! Looks like you haven't found " + itemName + ".");
                        }
                        break;
                    //Response mathes NEXT    
                    case "NEXT":
                        //Try block "tries" to find next item on shopping list
                        try{
                            //Increment index
                            ++index;
                            //Get next element in userList ArrayList, store in itemName
                            itemName = userList.get(index);
                            System.out.println("The next item on your list is  " + itemName);
                            System.out.println("Time to FIND " + itemName);
                            //Set found flag to false since item has not been found yet
                            found = false;
                        //Catch block will catch an exception if index exceeds elements in userList after being incremented  
                        }catch(IndexOutOfBoundsException e){
                            //Print error message telling user they have reached end of their list
                            System.out.println("Oops! Looks like you're at the end of your list.");
                            System.out.println("If you are done shopping, enter DONE");
                        }
                        break;
                    //Response matches FIND    
                    case "FIND":
                        //If inventory contains a key matching itemName AND a value is stored at that key in the inventory
                        if(myFarmStand.inventory.containsKey(itemName) && myFarmStand.inventory.get(itemName)!= null){
                            System.out.println("HOORAY! We have " + itemName + "!");
                            //Set found to true
                            found = true;
                            //Increment points since an item has been found
                            points++;
                        }
                        //If not, print error message, set found to false
                        else{
                            System.out.println("BUMMER! Looks like we don't have any " + itemName);
                            found = false;
                        }
                        break;
                    //Response matches TAKE    
                    case "TAKE":
                        //If item has been found
                        if(found){
                            //Ask user how many units of item they would like to take
                            System.out.println("How many " + itemName + " would you like to take?");
                            //Store user input in amount
                            amount = keyboard.nextInt();
                            keyboard.nextLine();
                            System.out.println();
                            //Loop will repeat while there are still items to be added
                            while(amount>0){
                                //Try block "tries" to retrieve object stored in inventory 
                                try{
                                    //Get first item in ArrayList at key matching itemName, store in itemObj
                                    Product itemObj = myFarmStand.inventory.get(itemName).get(0);
                                    //Call addToCart method from Shopper class, passing itemObj as arg
                                    user.addToCart(itemObj);
                                    //Remove item from farm stand's inventory
                                    myFarmStand.inventory.get(itemName).remove(0);
                                    //Decrement amount
                                    amount--;
                                //Catch block will catch exception thrown if there are no more objects of that item stored in inventory    
                                }catch(IndexOutOfBoundsException e){
                                    System.out.println("WARNING! Item out of stock. Cannot take any more " + itemName);
                                    //Set amount to zero to break loop
                                    amount = 0;
                                }
                            }
                            //Display amount of item user has in their cart by calling nProduct method 
                            System.out.println("You now have " + user.cart.nProduct(itemName) + " " + itemName + " in your cart.");
                        }
                        //If not, ask user if they have tried finding item
                        else{
                            System.out.println("Hm! Have you tried to FIND " + itemName + "?");
                            //Store user input in response
                            response = keyboard.nextLine().toUpperCase();
                            System.out.println();
                            //If user responds 'yes', inform user that we do not have this item in stock
                            if(response.equals("YES")){
                                System.out.println("Looks like we don't have this item! Can't take what we don't have!");
                            }
                            else{
                                //Inform user they must find object before trying to take it
                                System.out.println("You must FIND item before you may TAKE an item, silly!");
                            }
                        }
                        break;
                    //Respone matches PUT BACK    
                    case "PUT BACK":
                        //Instantiate new ArrayList of Product obects, store in returnedItems
                        ArrayList<Product> returnedItems = new ArrayList<Product>();
                        //Ask item which item they would like to put back
                        System.out.println("What item would you like to put back?");
                        //Store user input in itemName
                        itemName = keyboard.nextLine().toUpperCase();
                        System.out.println();
                        //If the item can be found in the cart, prompt user how many of the item they would like to put back 
                        if(user.checkForItem(itemName)){
                            System.out.println("How many " + itemName + " do you want to put back?");
                            //Store user input in amount
                            amount = keyboard.nextInt();
                            keyboard.nextLine();
                            System.out.println();
                            //Call putBack method passing itemName and amount as args, store returned ArrayList in returnedItems
                            returnedItems = user.putBack(itemName, amount);  
                                                       
                        }
                        //If not, print error messsage
                        else{
                            System.out.println("ERROR! This item cannot be found in your cart.");
                        }
                        //If the arrayList returned is not empty
                        if(returnedItems.size()>0){
                            //Instantiate new empty ArrayList of Product objects, store in currentStock
                            ArrayList<Product> currentStock = new ArrayList<Product>();
                            //Get ArrayList stored at itemName key in inventory, store in currentStock
                            currentStock = myFarmStand.inventory.get(itemName);
                            //Call addAll method, passing returnedItems as arg to add all objects stored in returnedItems to currentStock
                            currentStock.addAll(returnedItems);
                            //Replace the ArrayList stored at itemName key with currentStock
                            myFarmStand.inventory.replace(itemName, currentStock);
                        }
                        break;
                    //Respone matches QUIT    
                    case "QUIT":
                        //Print message
                        System.out.println("You have quit the game. goodbye.");
                        //Exit program
                        System.exit(0);
                        break;
                    //Default will execute if response does not match any of the set commands    
                    default:
                        //Prompt user to enter one of the set commands
                        System.out.println("Hm. I don't recognize that command. Please enter one of the listed commands.");
                        break;
                    }
                //Get new user input once code breaks out of switch, store in response    
                response = keyboard.nextLine().toUpperCase();
                System.out.println();
            }
            //Print message when loop ends
            System.out.println("Looks like you're done shopping.......");
            System.out.println();
            //If the user's cart is not empty call winLose method
            if(!user.cartEmpty()){
                //Pass points, budget, and total into winLose method
                winLose(points, user.getBudget(), user.cart.getTotal());
            }
            //If user's cart is empty, print message
            else{
                System.out.println("Huh! Looks like you didn't add anything to your cart.");
            }
            //Ask user if they would like to play again
            System.out.println("Would you like to play again?");
            response = keyboard.nextLine().toUpperCase();
            System.out.println();
            //If response equls no, set playing to false
            if(response.equals("NO")){
                playing = false;
            }
        }while(playing); //Loop will break when playing == false
        System.out.println("Thanks for playing!");
    }

    /**
     * Prints intro message banner
     */
    public static void printBanner(){

        //Source: https://www.asciiart.eu/miscellaneous/signs

        System.out.println(" _____________________");
        System.out.println("/      WELCOME TO     \\");
        System.out.println("!       GRACE'S        !");
        System.out.println("!      FARM STAND      !");
        System.out.println("\\_____________________/");
        System.out.println("         !  !");
        System.out.println("         !  !");
        System.out.println("         L_ !");
        System.out.println("        / _)!");
        System.out.println("       / /__L");
        System.out.println(" _____/ (____)");
        System.out.println("        (____)");
        System.out.println(" _____  (____)");
        System.out.println("      \\_(____)");
        System.out.println("        !  !");
        System.out.println("        !  !");
        System.out.println("        \\__/");

    }

    /**
     * Calculates if user has won or lost the game
     * @param points Number of items found during course of game
     * @param budget User's budget
     * @param total Total sum of the price of all items in the user's cart
     */
    public static void winLose(int points, double budget, double total){
        //If user has found more than 10 items and their total does not exceed their budget, they have won
        if(points >= 10 && total <= budget){
            System.out.println("CONGRATULATIONS YOU WON!!!!!");
            System.out.println("YOU FOUND 10 OR MORE ITEMS AND STUCK TO YOUR BUDGET!");
        }
        //If user found more than 10 items but exceeded their budget, print message
        else if(points >= 10 && total>budget){
            System.out.println("DARN! YOU FOUND 10 OR MORE ITEMS BUT YOU DID NOT STICK TO YOUR BUDGET");
            System.out.println("A FOR EFFORT!!!!!!");
        }
        //If user found less than 10 items but stuck to their budget, print message
        else if(points<10 && total<=budget){
            System.out.println("DARN! YOU STUCK TO YOUR BUDGET BUT YOU DID NOT FIND 10 OR MORE ITEMS");
            System.out.println("A FOR EFFORT!!!!!");
        }
        //If user did not meet either conditions, print message
        else{
            System.out.println("BUMMER! YOU DID NOT MEET EITHER REQUIREMENTS :(");
        }
    }
    
}


