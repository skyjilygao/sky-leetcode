package cn.skyjilygao.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode题: <a href="https://leetcode-cn.com/problems/valid-sudoku"> 36. 有效的数独 </a>
 *
 * @author skyjilygao
 * @date 20200922
 */
public class ValidSudoku {
    public static void main(String[] args) {
        /*char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'9', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};*/

        /* char[][] board = {
                 {'.','.','.','.','5','.','.','1','.'},
                 {'.','4','.','3','.','.','.','.','.'},
                 {'.','.','.','.','.','3','.','.','1'},
                 {'8','.','.','.','.','.','.','2','.'},
                 {'.','.','2','.','7','.','.','.','.'},
                 {'.','1','5','.','.','.','.','.','.'},
                 {'.','.','.','.','.','2','.','.','.'},
                 {'.','2','.','9','.','.','.','.','.'},
                 {'.','.','4','.','.','.','.','.','.'}};*/

        /*char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};*/

        char[][] board ={
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};

        System.out.println(isValidSudoku(board));
        System.out.println(isValidSudoku1(board));
        // 逐个比较
        System.out.println(isValidSudoku0(board));

    }

    /**
     * 数组，跟 map存储一直
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {

        // 数组存
        Map<Integer, Integer>[] arrRows = new HashMap[9];
        Map<Integer, Integer>[] arrCols = new HashMap[9];
        Map<Integer, Integer>[] arrGongs = new HashMap[9];
        for (int i = 0;i<9;i++){
            arrRows[i] = new HashMap<>();
            arrCols[i] = new HashMap<>();
            arrGongs[i] = new HashMap<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char g11 = board[i][j];
                if('.' == g11){
                    continue;
                }
                int g1 = (int)g11;
                arrRows[i].put(g1, arrRows[i].getOrDefault(g1, 0)+1);

                arrCols[j].put(g1, arrCols[j].getOrDefault(g1, 0)+1);

                int box_index = (i / 3 ) * 3 + j / 3;
                arrGongs[box_index].put(g1, arrGongs[box_index].getOrDefault(g1, 0)+1);

                if(arrRows[i].getOrDefault(g1, 0) > 1||arrCols[j].getOrDefault(g1, 0) > 1 || arrGongs[box_index].getOrDefault(g1, 0) > 1){
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * map
     * @param board
     * @return
     */
    public static boolean isValidSudoku1(char[][] board) {
        // 用map存
        Map<Integer, Map<Integer, Integer>> mapRows = new HashMap<>(9);
        Map<Integer, Map<Integer, Integer>> mapCols = new HashMap<>(9);
        Map<Integer, Map<Integer, Integer>> mapGongs = new HashMap<>(9);

        for (int i = 0;i<9;i++){
            mapRows.put(i, new HashMap<>());
            mapCols.put(i, new HashMap<>());
            mapGongs.put(i, new HashMap<>());

        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char g11 = board[i][j];
                if('.' == g11){
                    continue;
                }
                int g1 = (int)g11;
                Map<Integer, Integer> mapRow = mapRows.getOrDefault(i, new HashMap<>());
                mapRow.put(g1, mapRow.getOrDefault(g1, 0)+1);
                mapRows.put(i, mapRow);

                Map<Integer, Integer> mapCol = mapCols.getOrDefault(j, new HashMap<>());
                mapCol.put(g1, mapCol.getOrDefault(g1, 0)+1);
                mapCols.put(j, mapCol);

                int box_index = (i / 3 ) * 3 + j / 3;
                Map<Integer, Integer> mapGong = mapGongs.getOrDefault(box_index, new HashMap<>());
                mapGong.put(g1, mapGong.getOrDefault(g1, 0)+1);
                mapGongs.put(box_index, mapGong);

                if(mapRow.getOrDefault(g1, 0) > 1 || mapCol.getOrDefault(g1, 0) > 1 || mapGong.getOrDefault(g1, 0) > 1){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 96.78% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 96.58% 的用户
     * @param board
     * @return
     */
    public static boolean isValidSudoku0(char[][] board) {
        int xi = 0, yi = 0;
        for (int i = 0; i < 9; i++) {
            xi = i;
            for (int j = 0; j < 9; j++) {
                yi = j;
                // 宫 第一个元素
                char g1 = board[xi][yi];
                // 查 行是否有重复
                if(validRow(board, g1, xi, yi)){
                    return false;
                }
                 // 查 列是否有重复
                if(validColumn(board, g1, xi, yi)){
                    return false;
                }
                 // 查 宫是否有重复
                if(validGong(board, g1, xi, yi)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 检查当前行是否有重复
     * @param board 数度
     * @param g1 宫内某个元素
     * @param gxi 元素的 x 坐标
     * @param gyi 元素的 y 坐标
     * @return
     */
    private static boolean validRow(char[][] board, char g1, int gxi, int gyi){
        if('.' == g1){
            return false;
        }
        for (int i1 = gyi + 1; i1 < 9; i1++) {
            char c = board[gxi][i1];
            if('.' == c){
                continue;
            }
            if(g1 == c){
                return true;
            }
        }
        return false;
    }

    /**
     * 检查当前列是否有重复
     * @param board 数度
     * @param g1 宫内某个元素
     * @param gxi 元素的 x 坐标
     * @param gyi 元素的 y 坐标
     * @return
     */
    private static boolean validColumn(char[][] board, char g1, int gxi, int gyi){
        if('.' == g1){
            return false;
        }
        for (int i1 = gxi + 1; i1 < 9; i1++) {
            char c = board[i1][gyi];
            if('.' == c){
                continue;
            }
            if(g1 == c){
                return true;
            }
        }

        return false;
    }

    /**
     * 检查当前宫是否有重复
     * @param board 数度
     * @param g1 宫内某个元素
     * @param gxi 元素的 x 坐标
     * @param gyi 元素的 y 坐标
     * @return
     */
    private static boolean validGong(char[][] board, char g1, int gxi, int gyi){
        if('.' == g1){
            return false;
        }
        int ximax = getXmax(gxi);
        int yimax = getXmax(gyi);

        for (int i = gxi; i <= ximax; i++) {
            int tmpJ = i > gxi ? yimax - 2 : gyi;
            for (int j = tmpJ; j <= yimax; j++) {
                if(i == gxi && j == gyi){
                    continue;
                }
                char c = board[i][j];
                if('.' == c){
                    continue;
                }
                if(g1 == c){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取坐标当前最大坐标
     * @param x
     * @return
     */
    private static int getXmax(int x){
        switch (x){
            case 0:
            case 1:
            case 2:
                return 2;
            case 3:
            case 4:
            case 5:
                return 5;
            case 6:
            case 7:
            case 8:
                return 8;
            default: return -1;
        }
    }

}
