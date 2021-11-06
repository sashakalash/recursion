public class Main {
    public static final int FIELD_SIZE = 10;
    public static final char DOG = 'Щ';
    public static final char MAN = 'Ч';
    public static final char OBSTACLE = '*';
    public static final char EMPTY_CELL = '-';
    public static final char NO_WAY = 'N';
    public static final char UP_WAY = 'U';
    public static final char LEFT_WAY = 'L';
    public static final char RIGHT_WAY = 'х';
    public static final String NOW_WAY_MESSAGE = "Нет такого пути :(";

    public static void main(String[] args) {
        char[][] field = createField(FIELD_SIZE);
        field[0][0] = DOG;
        field[0][3] = OBSTACLE;
        field[0][4] = OBSTACLE;
        field[1][4] = OBSTACLE;
        field[1][6] = OBSTACLE;
        field[1][7] = OBSTACLE;
        field[2][3] = OBSTACLE;
        field[2][5] = OBSTACLE;
        field[2][9] = OBSTACLE;
        field[3][1] = OBSTACLE;
        field[4][6] = OBSTACLE;
        field[5][2] = OBSTACLE;
        field[5][5] = OBSTACLE;
        field[6][3] = OBSTACLE;
        field[6][6] = OBSTACLE;
        field[6][7] = OBSTACLE;
        field[6][8] = OBSTACLE;
        field[7][7] = OBSTACLE;
        field[8][7] = OBSTACLE;
        field[9][5] = OBSTACLE;
        field[9][6] = OBSTACLE;
        showField(field);
        find_path(field, 8, 3);
    }

    public static void showField(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println("\n");
        }
        System.out.println("\n");
    }

    public static char[][] createField(int size) {
        char[][] field = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = EMPTY_CELL;
            }
        }
        return field;
    }

    public static boolean[][] createPathField(int size) {
        boolean[][] field = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = false;
            }
        }
        return field;
    }

    public static void find_path(char[][] field, int x0, int y0) {
        boolean[][] path = createPathField(field.length);
        int x = x0;
        int y = y0;
        while (x != 0 || y != 0) {
            char direction = where_from(field, x, y, field.clone());
            switch (direction) {
                case NO_WAY:
                    System.out.println(NOW_WAY_MESSAGE);
                    return;
                case UP_WAY:
                    path[y][x] = true;
                    y -= 1;
                    break;
                case LEFT_WAY:
                    path[y][x] = true;
                    x -= 1;
                    break;
            }
        }
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (j == x0 && i == y0) {
                    System.out.print(MAN);
                } else if (path[i][j] == true) {
                    System.out.print(RIGHT_WAY);
                } else {
                    System.out.print(field[i][j]);
                }
            }
            System.out.println("\n");
        }
    }

    public static char where_from(char[][] field, int x, int y, char[][] memory) {
        int left_x;
        int left_y;
        int up_x;
        int up_y;
        if (x > 0) {
            left_x = x - 1;
            left_y = y;
            if (left_x == 0 && left_y == 0) {
                memory[y][x] = LEFT_WAY;
                return LEFT_WAY;
            }
            if (field[left_y][left_x] != OBSTACLE) {
                if (where_from(field, left_x, left_y, memory.clone()) != NO_WAY) {
                    memory[y][x] = LEFT_WAY;
                    return LEFT_WAY;
                }
            }
        }
        if (y > 0) {
            up_x = x;
            up_y = y - 1;
            if (up_x == 0 && up_y == 0) {
                memory[y][x] = UP_WAY;
                return UP_WAY;
            }
            if (field[up_y][up_x] != OBSTACLE) {
                if (where_from(field, up_x, up_y, memory.clone()) != NO_WAY) {
                    memory[y][x] = UP_WAY;
                    return UP_WAY;
                }
            }
        }
        return NO_WAY;
    }
}