/*
Prof.java
    - class for DinersAndThinkers
    - recommended size: ~90 lines

This class is a thread

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
    static final int NUM_NOODLES_TO_EAT = 85;
    static final int NUM_LINES_TO_WRITE = 200;
    int noodlesEaten;
    int linesWritten;

    @Override
    public void run(){
        Random rand = new Random();
        while( (this.noodlesEaten < NUM_NOODLES_TO_EAT) && (this.linesWritten < NUM_LINES_TO_WRITE)) {
            sleep( rand.nextInt( 100));
            this.linesWritten += program();
            System.out.println( this.getName() + " is hungry!");
            this.noodlesEaten += eat();
        }
    }

    private int program() {
        return 0;
    }

    private int eat() {
        return 0;
    }
}
