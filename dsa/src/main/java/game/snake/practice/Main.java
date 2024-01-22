package game.snake.practice;




import java.util.*;

public class Main {

  static class SnakeGame{

    class Pair{
      int x;
      int y;
      Pair(int x,int y){
        this.x=x;
        this.y=y;
      }



      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return x == pair.x && y == pair.y;
      }

      @Override
      public int hashCode() {
        return Objects.hash(x, y);
      }
    }

    boolean validate(int x,int y){
      if(snake.contains(new Pair(x,y)))return true;

      if(x<0 || x>=width)return true;
      if(y<0 || y>=height)return true;

      return false;
    }

    void changeSnakeSize(int x,int y){
      Pair food=foods.stream().findFirst().get();
      if(food.equals(new Pair(x,y))){
        snake.add(new Pair(x,y));
        foods.remove(food);
      }else{
        snake.add(new Pair(x,y));
        //Remove the last element from the linkedHashSet
        Pair p=snake.stream().findFirst().get();
        snake.remove(p);

      }
    }

    int width;
    int height;
    int x;
    int y;
    LinkedHashSet<Pair> snake;
    LinkedHashSet<Pair> foods;

    SnakeGame(int width,int height,int[][] foods){
      this.width=width;
      this.height=height;
      this.snake=new LinkedHashSet<>();
      this.foods=new LinkedHashSet<>();
      for(int i=0;i<foods.length;i++){
        this.foods.add(new Pair(foods[i][0],foods[i][1]));
      }
      snake.add(new Pair(0,0));
      this.x=0;
      this.y=0;
    }


    int move(Character c){

      if(c.equals('U')){
        if(validate(x-1,y))return -1;
        x--;
        changeSnakeSize(x,y);
      }else if(c.equals('D')){
        if(validate(x+1,y))return -1;
        x++;
        changeSnakeSize(x,y);
      }else if(c.equals('L')){
        if(validate(x,y-1))return -1;
        y--;
        changeSnakeSize(x,y);
      }else if(c.equals('R')){
        if(validate(x,y+1))return -1;
        y++;
        changeSnakeSize(x,y);
      }else{
        System.out.println("Invalid input");
        return -1;
      }
      if(false){
        for(Pair p:snake){
          System.out.println(p.x + "  " + p.y);
        }
      }
      return snake.size()-1;
    }

  }

  public static void main(String[] args) {

    SnakeGame snake=new SnakeGame(2, 3, new int[][]{{1,2},{0,1}});

    System.out.println(snake.move('R')); //-> Returns 0
    System.out.println(snake.move('D')); //-> Returns 0
    System.out.println(snake.move('R')); //-> Returns 1
    System.out.println(snake.move('U')); //-> Returns 1
    System.out.println(snake.move('L')); //-> Returns 2
    System.out.println(snake.move('U')); //-> Returns -1
  }
}