/**
 * The TicTacToe class in Java creates a graphical Tic-Tac-Toe game with a GUI interface allowing two
 * players to take turns and determines the winner or a tie based on the game rules.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class TicTacToe {
    int boardwidth =600;
    int boardwight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO ="O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turn =0;

    TicTacToe()
    {
        frame.setVisible(true);
        frame.setSize(boardwidth, boardwight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD,30));
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.DARK_GRAY);
        frame.add(boardPanel);

        for(int i =0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                JButton tile = new JButton();
                board[i][j]= tile;
                boardPanel.add(tile);
               
                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD,70));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(gameOver)return;
                        JButton tile =(JButton)e.getSource();
                        if(tile.getText()=="")
                        {
                            tile.setText(currentPlayer);
                            turn++;
                            checkWinner();
                            if(!gameOver)
                            {
                                currentPlayer = currentPlayer==playerX? playerO:playerX;
                                textLabel.setText(currentPlayer+" 's turn");
                            }
                            
                        }
                        
                    }
                });

            }
        }

    }
    void checkWinner()
    {
        for (int i = 0; i < 3; i++) {
            if(board[i][0].getText()=="")
                continue;
            if(board[i][0].getText()==board[i][1].getText() && board[i][1].getText()==board[i][2].getText())
            {
                for (int j = 0; j < 3; j++) {
                    setWinner(board[i][j]);
                }
                gameOver=true;
                return;
            }
        }

        for (int i = 0; i < 3; i++) {
            if(board[0][i].getText()=="")continue;

            if(board[0][i].getText()==board[1][i].getText() && board[1][i].getText()==board[2][i].getText())
            {
                for (int j = 0; j < 3; j++) {
                    setWinner(board[j][i]);
                }
                gameOver=true;
                return;
            }
        }
        if(board[0][0].getText()==board[1][1].getText()&&board[1][1].getText()==board[2][2].getText()&&board[0][0].getText()!="")
        {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver=true;
            return;
        }
        if(board[0][2].getText()==board[1][1].getText()&&board[1][1].getText()==board[2][0].getText()&&board[0][2].getText()!="")
        {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver=true;
            return;
        }
        if(turn==9)
        {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    setTile(board[i][j]);
                }
            }
            gameOver=true;
        }

    }
    void setWinner(JButton tile)
    {
        tile.setBackground(Color.gray);
        tile.setForeground(Color.green);
        textLabel.setText(currentPlayer+ " is the winner!");
    }
    void setTile(JButton tile)
    {
        tile.setForeground((Color.orange));
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
}
