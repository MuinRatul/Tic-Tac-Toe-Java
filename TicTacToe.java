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
    int boardheight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    
    JPanel settingpanel = new JPanel();
    JLabel ternlebel = new JLabel();
    JPanel toppanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO ="O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turn =0;
    int matchcount=1;
    int Xtern = 0;
    int Otern = 0;
    TicTacToe()
    {
        frame.setVisible(true);
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.orange);
        textLabel.setFont(new Font("Arial", Font.BOLD,30));
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);

        ternlebel.setFont(new Font("Arial",Font.BOLD,30));
        ternlebel.setBackground(Color.darkGray);
        ternlebel.setForeground(Color.white);
        ternlebel.setText("Match: " +matchcount+ "         Player X: "+Xtern+"     Player O: "+Otern);
        ternlebel.setOpaque(true);

        toppanel.setLayout(new GridLayout(2,1));
        toppanel.setBackground(Color.white);
        toppanel.add(textPanel, BorderLayout.NORTH);
        toppanel.add(ternlebel, BorderLayout.NORTH);
        
        
        frame.add(toppanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.DARK_GRAY);
        frame.add(boardPanel);

        settingpanel.setLayout(new BorderLayout());
        settingpanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        settingpanel.setBackground(Color.gray);
        frame.add(settingpanel, BorderLayout.SOUTH);

        setBoard();
    }
    /**
     * The `setBoard` function creates a 3x3 grid of JButtons for a Tic-Tac-Toe game, allowing players
     * to take turns marking the buttons and checking for a winner.
     */
    void setBoard()
    {
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
   /**
    * The `checkWinner` function in Java checks for a winning condition in a Tic-Tac-Toe game board and
    * sets the winner or ends the game if there is a winner or a tie.
    */
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
        if(currentPlayer==playerX)
            Xtern=Xtern+1;
        else
            Otern=Otern+1;
        ternlebel.setText("Match: " +matchcount+ "         Player X: "+Xtern+"     Player O: "+Otern);
        resbutton();
    }
    void setTile(JButton tile)
    {
        tile.setForeground((Color.orange));
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
        resbutton();
    }
    /**
     * The `resbutton` function creates a JButton for resetting a game of Tic-Tac-Toe with specific
     * styling and functionality.
     */
    void resbutton()
    {
        JButton resbutton = new JButton();
        resbutton.setFont(new Font("Arial",Font.BOLD,20));
        resbutton.setBackground(Color.white);
        resbutton.setFocusable(false);
        resbutton.setText("Play Again!");
        resbutton.setVisible(true);
        settingpanel.add(resbutton);
        resbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                
                boardPanel.removeAll();
                settingpanel.removeAll();
                currentPlayer = playerX;
                gameOver = false;
                turn =0;
                textLabel.setText("Tic-Tac-Toe");
                setBoard();
                boardPanel.revalidate();           
                boardPanel.repaint();
                settingpanel.revalidate();
                settingpanel.repaint();
                matchcount++;
                ternlebel.setText("Match: " +matchcount+ "         Player X: "+Xtern+"     Player O: "+Otern);
            }
        });
    }
}
