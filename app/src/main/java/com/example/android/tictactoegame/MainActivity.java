package com.example.android.tictactoegame;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private gameBoard gameBoard;
    private gameBrain gameScope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameBoard = findViewById(R.id.game_board);
        gameScope = new gameBrain();
        gameBoard.setgameBrain(gameScope);
        gameBoard.setMainActivity(this);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }
    // inflates a new game when the start new game action ba is clicked
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.action_new_game){
            // starts new game
            newGame();
        }
        return super.onOptionsItemSelected(item);
        }
    // called to end the game
    public void gameEnded(char c){
        //Display message wether the user wins or Tie.
        String message = (c =='T')? "Game Over. Tie": "GameOver. " + c + " wins";
        //Set Message to the screen and remove it
        new AlertDialog.Builder(this). setTitle("TIC TAC TOE GAME").setMessage(message).
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            // Call a new game method after the message is shown to the user
            public void onDismiss(DialogInterface dialog) {
                newGame();
            }
        }).show();

    }
    // Starts a new game
    private void newGame(){
        gameScope.newGame();
        //read the board and call the invalidate method
        gameBoard.invalidate();
    }

}


