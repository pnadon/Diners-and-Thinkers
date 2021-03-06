/*
Prof.java
    - class for DinersAndThinkers
    - recommended size: ~90 lines

Cycles through 4 steps until goals met:
    - Sleep:
        - print message (indication)
        - sleep for random time between [0, 100)
    - Program:
        - print message (indication, # lines, total # lines written)
        - writes random number of lines [5, 20], 1 millisecond sleep per line
    - Hungry:
        - print message (indication)
    - Eat:
        - REQUIRES:
            - (1) fork
            - (1) bib
        - attempts to obtain bib and fork IN RANDOM ORDER
        - once bib & fork obtained:
            - print message (indication, # noodles, total # noodles eaten)
            - eats random number of noodles [2, 10], 1 millisecond per noodle
            - once done, places bib and fork back in RANDOM ORDER

Completes once the following is satisfied:
    - 85 noodles eaten
    - 200 lines written

prints indication of completion, starts with “===============>”

Authors: Philippe Nadon, Jack Shea
 */

package dinersandthinkers;

import java.util.Random;

public class Prof extends Thread{
    private static final int NUM_NOODLES_TO_EAT = 85;
    private static final int NUM_LINES_TO_WRITE = 200;
    private int noodlesEaten;
    private int linesWritten;
    private Basket BibBasket;
    private Basket ForkBasket;

    /*
    Constructor for Prof Class.
     */
    Prof( Basket BibBasket, Basket ForkBasket) {
        this.BibBasket = BibBasket;
        this.ForkBasket = ForkBasket;
    }

    /*
    Method for running Prof thread, implements diners and thinkers process.
     */
    @Override
    public void run(){
        int linesWrittenInTurn;
        int noodlesEatenInTurn;
        Random rand = new Random();
        while( (this.noodlesEaten < NUM_NOODLES_TO_EAT)
                && (this.linesWritten < NUM_LINES_TO_WRITE)) {

            System.out.println( this.getName() + " is sleeping!");
            try {
                sleep( rand.nextInt( 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            linesWrittenInTurn = writeCode();
            this.linesWritten += linesWrittenInTurn;
            System.out.println(
                    this.getName() + " wrote " + linesWrittenInTurn +
                            " lines of code, total: " + this.linesWritten);

            System.out.println( this.getName() + " is hungry!");
            noodlesEatenInTurn = eatNoodles();
            this.noodlesEaten += noodlesEatenInTurn;
                    System.out.println(
                            this.getName() + " ate " + noodlesEatenInTurn +
                                    " noodles, total: " + noodlesEaten);
        }
        System.out.println("===============>" + this.getName() + " is done!");
    }
    /*
    Simulates a professor writing a random number of lines between 5 and 20
    */
    private int writeCode() {
        Random rand = new Random();
        int linesWritten = rand.nextInt( 16) + 5;
        try {
            sleep( linesWritten);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return linesWritten;
    }
    /*
    Determines the number of noodles a professor eats between 2 and 10.
    This method also is where the professor attempts to obtain a fork
      and a bib (in order to eat).
    Therefore, it will need to run methods from the Basket
      file(defined in this file as BibBasket and ForkBasket) in order to get and
      return forks and bibs to a basket shared by all other professor threads.
    */
    private int eatNoodles() {
        Random rand = new Random();
        boolean pickBibFirst;
        boolean obtainedFork = false;
        boolean obtainedBib = false;

        while( !( obtainedBib && obtainedFork)) {
            pickBibFirst = rand.nextBoolean();
            if (pickBibFirst) {
                if (this.BibBasket.getItem(1000, getName())) {
                    obtainedBib = true;
                    if (this.ForkBasket.getItem(1100, getName())) {
                        obtainedFork = true;
                    } else {
                        obtainedBib = false;
                        this.BibBasket.returnItem(getName());
                    }
                }
            }
            else{
                if (this.ForkBasket.getItem(1075, getName())) {
                    obtainedFork = true;
                    if (this.BibBasket.getItem(1025, getName())) {
                        obtainedBib = true;
                    } else {
                        obtainedFork = false;
                        this.ForkBasket.returnItem(getName());
                    }
                }
            }
        }
        int noodlesEaten = rand.nextInt( 9) + 2;

        try {
            sleep( noodlesEaten * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pickBibFirst = rand.nextBoolean();
        if( pickBibFirst){
            this.BibBasket.returnItem( getName());
            this.ForkBasket.returnItem( getName());
        }
        else {
            this.ForkBasket.returnItem( getName());
            this.BibBasket.returnItem( getName());
        }

        return noodlesEaten;
    }
}
