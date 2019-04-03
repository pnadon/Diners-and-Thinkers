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

class Basket {
    private int numItems;
    private String itemName;

    /*
    Constructor for Basket class.
     */
    Basket(int numItems, String itemName) {
        this.numItems = numItems;
        this.itemName = itemName;
    }

    /*
    Simulates getting an item from the basket, by waiting until an item is
        available and then decrementing numItems.
    A timeout is used to ensure there is no deadlock.
    Method is synchronized to ensure no data races,
        as only one thread can access this method at a time.
     */
    synchronized boolean getItem( int timeout, String profName) {
        long startWait;
        long waitMilliSeconds;
        System.out.println("===" +
            profName + " wants a " + this.itemName);
        System.out.println("===" +
                "there are " + this.numItems + " " + this.itemName);
            if (this.numItems < 1) {
                System.out.println("===" +
                        profName + " is waiting for a " + this.itemName);
                startWait = System.nanoTime();
                try {
                    this.wait( timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitMilliSeconds = (System.nanoTime() - startWait) / 1000000;
                System.out.println("===" +
                        profName + " waited " +
                        waitMilliSeconds + "ms for a " + this.itemName);
            }
            if (this.numItems > 0) {
                System.out.println("===" +
                        "A " + this.itemName + " was lent to " + profName);
                this.numItems--;
                return true;
            }
            return false;
    }

    /*
    Simulates returning an item to the basket, by incrementing numItems
        and notifying other threads of this change.
    Method is synchronized to ensure no data races,
        as only one thread can access this method at a time.
     */
    synchronized void returnItem( String profName) {
        System.out.println("===" +
            "A " + this.itemName + " was returned by " + profName);
            this.numItems++;
            this.notifyAll();
    }
}
