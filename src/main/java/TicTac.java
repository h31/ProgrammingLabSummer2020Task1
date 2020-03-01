public class TicTac {
    // res = 1 - крестик, 2 - нолик
    private int[][] field;

    private int size;
    private int start;

    private int count = 0;
    private int result = 0;

    public TicTac(int size) {
        this.field = new int[size][size];
        this.size = size;
    }

    public int[][] getField() { return this.field; }

    public int getValue(int x, int y) { return field[y][x]; }

    public void add(int x, int y, int res) { field[y][x] = res; }

    public void delete(int x, int y) { field[y][x] = 0; }

    public int gorizontal(int res) {
        for (int y = 0; y < size; y++) {
            start = 0;
            for (int x = 0; x < size; x++) {
                int var = x;
                if (field[y][x] == res) {
                    int[] answer = check(start, x, y, res, var);
                    result = answer[0];
                    start = answer[1];
                    count = answer[2];
                }
                if (field[y][x] != res) {
                    start = 0;
                    if (count > result) result = count;
                    count = 0;
                }
            }
        }
        return result;
    }

    public  int vertical (int res) {
        int start;

        for (int x = 0; x < size; x++) {
            start = 0;
            for (int y = 0; y < size; y++) {
                int var = y;
                if (field[y][x] == res) {
                    int[] answer = check(start, x, y, res, var);
                    result = answer[0];
                    start = answer[1];
                    count = answer[2];
                }
                if (field[y][x] != res) {
                    start = 0;
                    if (count > result) result = count;
                    count = 0;
                }
            }
        }
        return result;
    }

    public  int diagonal1(int res) {
        for (int x = 0; x < size; x++) {
            int var = x;

            start = 0;
            int y = x;
            if (field[y][x] == res) {
                int[] answer = check(start, x, y, res, var);
                result = answer[0];
                start = answer[1];
                count = answer[2];
            }
            if (field[y][x] != res) {
                start = 0;
                if (count > result) result = count;
                count = 0;
            }

        }
        return result;
    }

    public int diagonal2 (int res) {
        int x;
        int y;
        for (x = size - 1, y = 0; x <= 0; x--, y ++) {
            int var = y;
            start = 0;
            if (field[y][x] == res) {
                int[] answer = check(start, x, y, res, var);
                result = answer[0];
                start = answer[1];
                count = answer[2];
            }
            if (field[y][x] != res) {
                start = 0;
                if (count > result) result = count;
                count = 0;
            }
        }
        return result;
    }

    public String search(int res) {
        int gorizontal = gorizontal(res);
        int vertical = vertical(res);
        int diagonal1 = diagonal1(res);
        int diagonal2 = diagonal2(res);
        int max1;
        int max2;
        int max;
        if (gorizontal > vertical) { max1 = gorizontal;}
        else max1 = vertical;
        if (diagonal1 > diagonal2) { max2 = diagonal1; }
        else max2 = diagonal2;
        if (max1 > max2) { max = max1; }
        else max = max2;
        return "Максимальное число " + res + " - " + max;
    }

    public int[] check (int start, int x, int y, int res, int var) {
        if (field[y][x] == res) {
            if (start == 0) {
                start = 1;
            }
            if (var == size - 1) {
                count++;
                if (count > result) result = count;
                count = 0;
            } else count++;
        }

        return new int[] {result, start, count} ;
    }

}
