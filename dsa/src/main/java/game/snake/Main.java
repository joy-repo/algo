package game.snake;

public class Main {

  public static void main(String[] args) {



    System.out.println("Going to start game");

    Cell initPos = new Cell(0, 0);
    Snake initSnake = new Snake(initPos);
    Board board = new Board(10, 10);
    Game newGame = new Game(initSnake, board);
    newGame.setGameOver(false);
    newGame.setDirection(Game.DIRECTION_RIGHT); ;

    // We need to update the game at regular intervals,
    // and accept user input from the Keyboard.

    // here I have just called the different methods
    // to show the functionality
    for (int i = 0; i < 5; i++) {
      if (i == 2)
        newGame.getBoard().generateFood();
      newGame.update();
      if (i == 3)
        newGame.setDirection(Game.DIRECTION_RIGHT) ;
      if (newGame.isGameOver())
        break;
    }
  }
}
