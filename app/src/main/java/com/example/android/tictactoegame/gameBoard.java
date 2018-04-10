package com.example.android.tictactoegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by READWHAN on 08/04/2018.
 */

public class gameBoard extends View {
    private static final int LINE_THICK = 5;
    private static final int CELL_MARGIN = 20;
    private static final int CELL_STROKE_WIDTH = 15;
    private int width, height, cellWidth, cellHeight;
    private Paint gridPaint, oPaint, xPaint;
    private gameBrain gameLogic;
    private MainActivity activity;


    public gameBoard(Context context) {
        super(context);

    }

    public gameBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        gridPaint = new Paint();
        oPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
        oPaint.setColor(Color.rgb(200, 50, 50));
        oPaint.setStrokeWidth(CELL_STROKE_WIDTH);
        oPaint.setStyle(Paint.Style.STROKE);
        xPaint = new Paint(oPaint);
        xPaint.setColor(Color.rgb(178, 188, 21));
    }

    public void setMainActivity(MainActivity m) {
        activity = m;

    }

    public void setgameBrain(gameBrain b) {
        gameLogic = b;
    }

    protected void onMeasure(int heightMeasureSpec, int widthMeasureSpec) {
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        cellHeight = (height - LINE_THICK / 3);
        cellWidth = (width - LINE_THICK / 3);
        setMeasuredDimension(width, height);
    }
    // Draw canvas
    protected void onDraw(Canvas canvas) {
        drawGrid(canvas);
        drawBoard(canvas);
    }

    /**
     * Get the activity from the board using the on touch event
     *
     * @param event get the cell touch from the board when the x and coordinates is palyed to
     * @return
     */
    public boolean onTouchEvent(MotionEvent event) {
        if (!gameLogic.isEnded() && event.getAction() == MotionEvent.ACTION_DOWN) ;
        {
            int x = (int) (event.getX() / cellWidth);
            int y = (int) (event.getY() / cellHeight);
            char win = gameLogic.play(x, y);
            invalidate();
            // checks if there is a winner
            if (win != ' ') {
                activity.gameEnded(win);
            }
            // comp plays
            else {
                win = gameLogic.computer();
                invalidate();

                if (win != ' ') {
                    activity.gameEnded(win);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * This method is called to draw the grid lines .
     *
     * @param canvas blank rectangular area on the screen that gets inputs events.
     *
     */
    private void drawGrid(Canvas canvas) {
        for (int i = 0; i < 2; i++) {
            // For vertical line
            float left1 = cellWidth * (i + 1);
            float right1 = left1 + LINE_THICK;
            float top1 = 0;
            float bottom1 = height;

            canvas.drawRect(left1, right1, top1, bottom1, gridPaint);

            // For Horizontal lines
            float top2 = cellHeight * (i + 1);
            float left2 = 0;
            float right2 = width;
            float bottom2 = top2 + LINE_THICK;

            canvas.drawRect(top2, left2, right2, bottom2, gridPaint);
        }
    }
    // Called to draw the board.
    private void drawBoard(Canvas canvas){
        for (int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                drawELT(canvas, gameLogic.getElt(i,j), i, j);
            }
        }
    }

    // Draws 'X' or 'O' at a specific position within the cells.
    private void drawELT(Canvas canvas, char c, int x, int y) {
        if (c == 'O') {
            float cx = (cellWidth * x) + cellWidth / 2;
            float cy = (cellHeight * y) + cellHeight / 2;
            // Draws "O" inside the grid.
            canvas.drawCircle(cx, cy, Math.min(cellWidth, cellHeight) / 2 -
                    CELL_MARGIN * 2, oPaint);
        } else if (c == 'X') {
            float startX1 = (cellWidth * x) + CELL_MARGIN;
            float startY1 = (cellHeight * y) + CELL_MARGIN;
            float endX1 = startX1 + cellWidth - CELL_MARGIN * 2;
            float endY1 = startY1 + cellHeight - CELL_MARGIN;
            canvas.drawLine(startX1, startY1, endX1, endY1, xPaint);


            float startX2 = (cellWidth * x) + CELL_MARGIN;
            float startY2 = (cellHeight * y) + CELL_MARGIN;
            float endX2 = startX2 + cellWidth - CELL_MARGIN * 2;
            float endY2 = startY2 + cellHeight - CELL_MARGIN;
            canvas.drawLine(startX2, startY2, endX2, endY2, xPaint);
        }

    }
}


