/*
Basket.java
    - class for DinersAndThinkers
    - recommended size: ~50 lines

Implements the concept of a basket containing one type of item

Contains:
    - name for the items
    - number of items available
    - getItem, with parameter for the LIMIT ON TIME TO GET ITEM (deadlock)
    - returnItem, returns the item

Any message from this class starts with "==="
    - send message when:
        - when someone is waiting for a resource
        - when someone gets a resource
        - when someone returns a resource

Authors: Philippe Nadon, Jack Shea
 */

package dinersandthinkers;

public class Basket {
    private int numItems;
    private String itemName;

    Basket(int numItems, String itemName) {
        this.numItems = numItems;
        this.itemName = itemName;
    }

    public boolean getItem( int timeout, String profName) {
        System.out.println("===" +
            profName + " is waiting for a " + this.itemName);
        // implement timeout
        System.out.println("===" +
            "A " + this.itemName + " was lent to " + profName);
        this.numItems--;
        return true;
    }

    public void returnItem( String profName) {
        System.out.println("===" +
            "A " + this.itemName + " was returned by " + profName);
        this.numItems++;
    }
}
