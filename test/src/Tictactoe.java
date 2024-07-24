import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tictactoe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JPanel board = new JPanel();

    JButton[][] buttons = new JButton[3][3];
    String player1 = "X";
    String player2 = "O";
    String currentPlayer = player1;
    boolean gameOver = false;
    int count = 0;

    Tictactoe(){
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        label.setBackground(Color.darkGray);
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("TicTacToe");
        label.setOpaque(true);

        panel.setLayout(new BorderLayout());
        panel.add(label);
        frame.add(panel, BorderLayout.NORTH);

        board.setLayout(new GridLayout(3,3));
        board.setBackground(Color.darkGray);
        frame.add(board);

        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                JButton tile = new JButton();
                buttons[r][c] = tile;
                board.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if (gameOver) return;
                        JButton tile  = (JButton) e.getSource();
                        if(tile.getText()==""){
                            tile.setText(currentPlayer);
                            count++;
                            checkWinner();
                            if(!gameOver){
                                currentPlayer = currentPlayer == player1 ? player2 : player1;
                                label.setText(currentPlayer + "'s turn");
                            }
                        }
                    }
                });
            }
        }
    }
    void checkWinner(){
        for(int r = 0; r < 3; r++){
            if (buttons[r][0].getText() == "") continue;
            if (buttons[r][0].getText() == buttons[r][1].getText() &&
                buttons[r][1].getText() == buttons[r][2].getText()){
                for(int i = 0; i < 3; i++){
                    setWinner(buttons[r][i]);
                }
                gameOver = true;
                return;
            }
        }
        for(int c = 0; c < 3; c++){
            if (buttons[0][c].getText() == "") continue;
            if (buttons[0][c].getText() == buttons[1][c].getText() &&
                buttons[1][c].getText() == buttons[2][c].getText()){
                for(int i = 0; i < 3; i++){
                    setWinner(buttons[i][c]);
                }
                gameOver = true;
                return;
            }
        }
        if (buttons[0][0].getText() == buttons[1][1].getText() &&
            buttons[1][1].getText() == buttons[2][2].getText() &&
            buttons[0][0].getText() != ""){
            for(int i = 0; i < 3; i++){
                setWinner(buttons[i][i]);
            }
            gameOver = true;
            return;
        }
        if (buttons[0][2].getText() == buttons[1][1].getText() &&
            buttons[1][1].getText() == buttons[2][0].getText() &&
            buttons[0][2].getText() != ""){
            setWinner(buttons[0][2]);
            setWinner(buttons[1][1]);
            setWinner(buttons[2][0]);
            gameOver = true;
            return;
        }
        if (count==9){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    setTie(buttons[i][j]);
                }
            }    
        }
        
    }
    void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        label.setText(currentPlayer + " win");
        
    }
    void setTie(JButton tile){
        tile.setBackground(Color.orange);
        label.setText(currentPlayer + " win");

    }

}
