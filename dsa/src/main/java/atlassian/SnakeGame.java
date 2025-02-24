package atlassian;

import java.util.*;

class Position {
    int row, col;

    Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Equals & HashCode for HashSet storage
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Position)) return false;
        Position other = (Position) obj;
        return this.row == other.row && this.col == other.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

// Food Manager Class
class FoodManager {
    private Queue<Position> foodQueue;

    public FoodManager(int[][] food) {
        foodQueue = new LinkedList<>();
        for (int[] f : food) {
            foodQueue.offer(new Position(f[0], f[1]));
        }
    }

    public Position getNextFood() {
        return foodQueue.peek(); // Peek next food without removing
    }

    public void consumeFood() {
        foodQueue.poll(); // Remove consumed food
    }
}

// Snake Class
class Snake {
    private Deque<Position> body;
    private Set<Position> bodySet;

    public Snake() {
        body = new LinkedList<>();
        bodySet = new HashSet<>();
        Position start = new Position(0, 0);
        body.add(start);
        bodySet.add(start);
    }

    public Position getHead() {
        return body.peekFirst();
    }

    public boolean move(Position newHead, boolean grows) {
        if (bodySet.contains(newHead) && !newHead.equals(body.peekLast())) {
            return false; // Collision with itself
        }

        body.addFirst(newHead);
        bodySet.add(newHead);

        if (!grows) { // Remove tail if not growing
            Position tail = body.pollLast();
            bodySet.remove(tail);
        }
        return true;
    }

    public int getSize() {
        return body.size();
    }
}

// SnakeGame Class
class SnakeGame {
    private int width, height, score;
    private FoodManager foodManager;
    private Snake snake;
    private Map<String, Position> directions;

    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.score = 0;
        this.snake = new Snake();
        this.foodManager = new FoodManager(food);

        // Movement mapping
        directions = new HashMap<>();
        directions.put("U", new Position(-1, 0));
        directions.put("D", new Position(1, 0));
        directions.put("L", new Position(0, -1));
        directions.put("R", new Position(0, 1));
    }

    public int move(String direction) {
        Position head = snake.getHead();
        Position delta = directions.get(direction);
        Position newHead = new Position(head.row + delta.row, head.col + delta.col);

        // Check boundary collision
        if (newHead.row < 0 || newHead.row >= height || newHead.col < 0 || newHead.col >= width) {
            return -1;
        }

        // Check if new head is food
        boolean grows = foodManager.getNextFood() != null && foodManager.getNextFood().equals(newHead);
        if (grows) {
            foodManager.consumeFood();
            score++;
        }

        // Move the snake
        if (!snake.move(newHead, grows)) {
            return -1; // Collision with itself
        }

        return score;
    }

    public static void main(String[] args) {
        int[][] food = {{2, 2}, {0, 1}};
        SnakeGame game = new SnakeGame(3, 3, food);

        System.out.println(game.move("R")); // 0
        System.out.println(game.move("D")); // 0
        System.out.println(game.move("R")); // 1 (Food eaten)
        System.out.println(game.move("U")); // 1
        System.out.println(game.move("L")); // 2 (Food eaten)
        System.out.println(game.move("U")); // -1 (Collision)
    }
}