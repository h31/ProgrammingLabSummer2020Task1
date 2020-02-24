public class TicTac {
    private int[][] field;

    public TicTac(int size) { this.field = new int[size][size]; }

    public int[][] getField() { return this.field; }

    public int getValue(int x, int y) { return getField()[x][y]; }

    public void add(int x, int y, int res) { field[x][y] = res; }

    public void delete(int x, int y) { field[y][x] = 0; }

    public String search(int size, int res) {         //Поиск самой длинной последовательности
        boolean start;
        int startX = 0;
        int startY = 0;
        int finishX = 0;
        int finishY = 0;
        int conStartX = 0;
        int conStartY = 0;
        int confinishX = 0;
        int confinishY = 0;
        int maxStX = 0;
        int maxStY = 0;
        int maxResult = 0;
        int count = 0;
        int result = 0;
        for (int y = 0; y < size; y++) {                                       //Проверка по горизонталь
            start = true;
            for (int x = 0; x < size; x++) {
                if (field[y][x] == res) {
                    if (start) {
                        start = false;
                        startX = x;
                        startY = y;
                    }
                    if (x == size - x) {
                        count++;
                        if (count > maxResult) {
                            maxResult = count;
                            finishX = x;
                            finishY = y;
                            maxStX = startX;
                            maxStY = startY;
                        }
                        count = 0;
                    } else count++;
                }
                if (field[y][x] != res) {
                    start = true;
                    if (count > maxResult) {
                        maxResult = count;
                        finishX = x - 1;
                        finishY = y;
                        maxStX = startX;
                        maxStY = startY;
                    }
                    count = 0;
                }
            }
            maxResult = result;
            conStartX = maxStX;
            conStartY = maxStY ;
            confinishX = finishX;
            confinishY = finishY;

        }
        for (int x = 0; x < size; x++) {                                       //Проверка по вертикали
            start = true;
            for (int y = 0; y < size; y++) {
                if (field[y][x] == res) {
                    if (start) {
                        start = false;
                        startX = x;
                        startY = y;
                    }
                    if (x == size - x) {
                        count++;
                        if (count > maxResult) {
                            maxResult = count;
                            finishX = x;
                            finishY = y;
                            maxStX = startX;
                            maxStY = startY;
                        }
                        count = 0;
                    } else count++;
                }
                if (field[y][x] != res) {
                    start = true;
                    if (count > maxResult) {
                        maxResult = count;
                        finishX = x;
                        finishY = y - 1;
                        maxStX = startX;
                        maxStY = startY;
                    }
                    count = 0;
                }
            }
            if (result > maxResult) {
                maxResult = result;
                conStartX = maxStX;
                conStartY = maxStY ;
                confinishX = finishX;
                confinishY = finishY;
            }
        }
        for (int x = 0; x < size; x++) {                                       //Проверка 1 диагонали
            start = true;

            if (field[x][x] == res) {
                if (start) {
                    start = false;
                    startX = x;
                    startY = x;
                }
                if (x == size - x) {
                    count++;
                    if (count > maxResult) {
                        maxResult = count;
                        finishX = x;
                        finishY = x;
                        maxStX = startX;
                        maxStY = startY;
                    }
                    count = 0;
                } else count++;
            }
            if (field[x][x] != res) {
                start = true;
                if (count > maxResult) {
                    maxResult = count;
                    finishX = x - 1;
                    finishY = x - 1;
                    maxStX = startX;
                    maxStY = startY;
                }
                count = 0;
            }
            if (result > maxResult) {
                maxResult = result;
                conStartX = maxStX;
                conStartY = maxStY ;
                confinishX = finishX;
                confinishY = finishY;
            }

        }
        for (int x = 0; x < size; x++) {                                       //Проверка 2 диагонали
            start = true;
            if (field[size - 1 - x][x] == res) {
                if (start) {
                    start = false;
                    startX = x;
                    startY = size - 1 - x;
                }
                if (x == size - x) {
                    count++;
                    if (count > maxResult) {
                        maxResult = count;
                        finishX = x;
                        finishY = size - 1 - x;
                        maxStX = startX;
                        maxStY = startY;
                    }
                    count = 0;
                } else count++;
            }
            if (field[size - 1 - x][x] != res) {
                start = true;
                if (count > maxResult) {
                    maxResult = count;
                    finishX = x - 1;
                    finishY = size - x;
                    maxStX = startX;
                    maxStY = startY;
                }
                count = 0;
            }
            if (result > maxResult) {
                maxResult = result;
                conStartX = maxStX;
                conStartY = maxStY ;
                confinishX = finishX;
                confinishY = finishY;
            }
        }
        String str = "";
        return str = maxResult + " " + conStartX + " " + conStartY + " " + confinishX + " " + confinishY;
    }
}
