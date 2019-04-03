/*
DinersAndThinkers.java
    - contains main method, starting point
    - recommended size: ~30 lines

Steps performed (all prompts assume return positive int):
    - prompt number of profs and their names
    - prompt number of forks
    - prompt number of bibs
    - create prof threads AS CLOSE IN TIME AS POSSIBLE

Authors: Philippe Nadon, Jack Shea
 */
package dinersandthinkers;
import java.util.Scanner;

public class DinersAndThinkers {

    public static void main( String[] args) {
        Scanner userIn = new Scanner( System.in);

        System.out.println( "Enter the number of profs: ");
        int numProfs = userIn.nextInt();
        userIn.nextLine();

        System.out.println(
                "Enter the names of the profs, separated by commas (, ): ");
        String[] profNames = userIn.nextLine().split(", ");

        System.out.println( "Enter the number of forks: ");
        int numForks = userIn.nextInt();

        System.out.println( "Enter the number of bibs: ");
        int numBibs = userIn.nextInt();

        Basket BibBasket = new Basket( numBibs, "bib");
        Basket ForkBasket = new Basket( numForks, "fork");

        Prof[] profArray = new Prof[numProfs];
        for( int i = 0; i < numProfs; i++) {
            profArray[i] = new Prof( BibBasket, ForkBasket);
            profArray[i].setName( profNames[i]);
        }
        // for loop split to start threads as closely as possible.
        for( int i = 0; i < numProfs; i++) {
            profArray[i].start();
        }
    }
}
