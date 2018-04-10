package com.example.android.tictactoegame;

import java.util.Random;

/**
 * Created by READWHAN on 04/04/2018.
 */

public class gameBrain {
    // Random object used by the ccomputer to play to empty cells
    private static final Random Rand = new Random();
    public char[] elt;
    private char currentPlayer;
    // Indicates if the game is ended or not
    private boolean ended;

    public gameBrain(){
        elt = new char[9];
        newGame();
    }

    public boolean isEnded(){

        return ended;
    }
    /**
     * Check positions where the user can play to.
     */
    public char play(int x, int y){

        if (!ended && elt[3 * y + x] == ' '){
            elt[3 * y + x] = currentPlayer;
            changePlayer();
        }
        return checkEnd();
    }
    // This method is called to change the player
    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X'? 'O' : 'X');
    }

    // Enters the content of the cell at a position indicated by the the coordinates x and y
    public char getElt (int x, int y){
        return elt [3*y + x];
    }


    // This method  start a new game
    public void newGame() {
        for (int i = 0; i < elt.length; i++) {
            elt[ i ] = ' ';
        }
        currentPlayer = 'X';
        ended = false;
    }
    // checks conditions that end the game, determine the winner or tie
    public char checkEnd() {
        // checks vertically
        for (int i=0; i < 3; i++){
            if(getElt(i, 0)!= ' ' && getElt(i,0) == getElt(i, 1) && getElt(i,1)
                    == getElt(i,2)){
                ended = true;
                return getElt(i, 0);
            }
        }
        // checks horizontally
        int i = 0;
            if(getElt(0, i) != ' ' && getElt(0, i) == getElt (1, i) && getElt(1, i)
                    == getElt(2, i)){
                ended = true;
                return getElt(0, i);
        }
            // checks diagonally from right to left
            if(getElt(0, 0) != ' ' && getElt(0,0) == getElt(1, 1) && getElt
                    (1, 1) == getElt(2,2)){
                ended = true;
                return getElt(0,0);

        }
            // checks diagonally from right to left
            if (getElt(2,0) != ' ' && getElt (2,0) == getElt(1,1) &&
                    getElt(1, 1)== getElt (0,2)){
                ended = true;
                return getElt(2, 0);
        }
        for (i = 0; i<9; i++){
                if (elt [i] == ' ')
                    return ' ';

        }
        // otherwise a tie
        return 'T';

    }
    //This method is called for the computer to play into random positions that are empty.
    public char computer(){
        if (!ended) {
            int position = -1;

            do {
                position = Rand.nextInt(9);
            } while (elt[ position ] != ' ');
            elt [ position ] = currentPlayer;
            changePlayer();
        }

    return checkEnd();

    }

}


