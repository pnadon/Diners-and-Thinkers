/*
DinersAndThinkers.java
    - contains main method, starting point
    - recommended size: ~30 lines

Steps performed (all prompts assume return positive int):
    - prompt number of profs and their names
    - prompt number of forks
    - prompt number of bibs
    - create prof threads AS CLOSE IN TIME AS POSSIBLE

Considerations for project ( not necessarily this file):
    - sleep( time);
    - wait( time);
    - notifyAll();
    - ILLEGAL: java.util.concurrency ( DO NOT USE)

Authors: Philippe Nadon, Jack Shea
 */
package dinersandthinkers;
import java.util.Scanner;

public class DinersAndThinkers {

    public static void main( String[] args) {
        Scanner userIn = new Scanner( System.in);
        int numForks, numBibs, numProfs;
        Prof[] profArray;
        String[] profNames;

        System.out.println( "Enter the number of profs: ");
        numProfs = userIn.nextInt();

        System.out.println( "Enter the names of the profs, separated by commas (, ): ");
        profNames = userIn.next().split(", ");

        System.out.println( "Enter the number of forks: ");
        numForks = userIn.nextInt();

        System.out.println( "Enter the number of bibs: ");
        numBibs = userIn.nextInt();

        Basket BibBasket = new Basket( numBibs, "bib");
        Basket forkBasket = new Basket( numForks, "fork");

        profArray = new Prof[ numProfs];
        for( int i = 0; i < numProfs; i++) {
            profArray[i] = new Prof();
            profArray[i].setName( profNames[i]);
        }
    }
}
