import java.util.*;

class Board{
    char[][] board;

    Board() {
        this.board = new char[][] {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
    }

    public void printBoard() {
        System.out.print(" === === ===\n");
        for(int i = 0; i < 3; i++) {
            System.out.print("| ");
            for(int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.print("\n === === ===\n");
        }
    }

    public void updateBoard(int position, char ch) {
        if (position < 0 || position > 9) {
            System.out.println("You entered an out of bounds position.\nChoose legal position between 1 and 9.");
            return;
        }

        int r = (position - 1) / 3;
        int c = (position - 1) % 3;

        
        this.board[r][c] = ch;  

        printBoard();
    }

    public void clearBoard() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                this.board[i][j] = ' ';
            }
        }
    }

    public boolean gameOver() {
        // check rows
        for(int i = 0; i < 3; i++) {
            if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                if (board[i][0] == 'x') {
                    System.out.println("\nWinner is Player1");
                } else {
                    System.out.println("\nWinner is Player2");
                }
                return true;
            }
        }

        //check columns
        for(int i = 0; i < 3; i++) {
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                if (board[0][i] == 'x') {
                    System.out.println("\nWinner is Player1");
                } else {
                    System.out.println("\nWinner is Player2");
                }
                return true;
            }
        }

        //check diagonal1
        if (board[1][1] != ' ' && board[1][1] == board[0][0] && board[1][1] == board[2][2]) {
            if (board[1][1] == 'x') {
                    System.out.println("\nWinner is Player1");
                } else {
                    System.out.println("\nWinner is Player2");
                }
                return true;
        }

        //check diagonal2
        if (board[1][1] != ' ' && board[1][1] == board[2][0] && board[1][1] == board[0][2]) {
            if (board[1][1] == 'x') {
                    System.out.println("Winner is Player1");
                } else {
                    System.out.println("Winner is Player2");
                }
                return true;
        }

        return false;
    }

    public void playGame(Scanner sc, int choice) {
        printBoard();
        while(true) {
            while(true) {
                System.out.print("\nEnter your choice Player1(1-9): ");
                int position = sc.nextInt();
                if(validMove(position)) {
                    updateBoard(position, 'x');
                    break;
                } else {
                    System.out.println("Invalid choice!");
                }
            }

            if(gameOver() || gameDraw()) {
                clearBoard();
                break;
            }

            while(true) {
                System.out.print("\nEnter your choice Player2(1-9): ");
                int position = sc.nextInt();
                if(validMove(position)) {
                    updateBoard(position, 'o');
                    break;
                } else {
                    System.out.println("Invalid choice!");
                }
            }

            if(gameOver() || gameDraw()) {
                clearBoard();
                break;
            }
        }
        System.out.println("\n1. Play again?\n2. Exit");
        choice = sc.nextInt();
        if(choice == 1) {
            playGame(sc, choice);
        }
    }

    private boolean validMove(int position) {
        int r = (position - 1) / 3;
        int c = (position - 1) % 3;

        return this.board[r][c] == ' ';
    }

    private boolean gameDraw() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(this.board[i][j] == ' ') {
                    return false;
                }
            }
        }

        System.out.println("\nTis a Draw!");
        return true;
    }
}

class tic_tac_toe{
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        System.out.println("\n ~~~ Welcome to Tic-Tac-Toe ~~~ ");
        Board board = new Board();
        
        System.out.println("1. Play game\n2. Exit");
        int choice = sc.nextInt();

        if(choice == 1) {
            board.playGame(sc, choice);
        }

        System.out.println("Goodbye.");
        sc.close();
    }
}