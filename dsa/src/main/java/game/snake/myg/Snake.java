package game.snake.myg;

import game.snake.practice.Main;

import java.util.LinkedHashSet;

public class Snake {

  int width;
  int height;
  int x;
  int y;
  LinkedHashSet<Pair> snake;
  LinkedHashSet<Pair> foods;

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

  int move(Character c){

    switch (c) {
    case 'U':
      if (validate(x - 1, y))
        return -1;
      x--;
      changeSnakeSize(x, y);
      break;
    case 'D':
      if (validate(x + 1, y))
        return -1;
      x++;
      changeSnakeSize(x, y);
      break;
    case 'L':
      if (validate(x, y - 1))
        return -1;
      y--;
      changeSnakeSize(x, y);
      break;
    case 'R':
      if (validate(x, y + 1))
        return -1;
      y++;
      changeSnakeSize(x, y);
      break;
    }
    return snake.size()-1;

  }




}
