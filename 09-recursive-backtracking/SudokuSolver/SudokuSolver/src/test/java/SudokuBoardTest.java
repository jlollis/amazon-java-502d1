import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuBoardTest {
    int[][] SOLUTION = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},


            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},

            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
    };

    public SudokuBoard getSolvedBoard() {
        return Puzzles.initializeBoard(SOLUTION);
    }

    @Test
    public void isEmptyBoardComplete() throws Exception {
        // an empty board is valid, but it's not considered complete.
        SudokuBoard board = new SudokuBoard();
        assertFalse(board.isBoardComplete());
    }

    @Test
    public void isEmptyBoardValid() throws Exception {
        // an empty board is considered valid because it doesn't break
        // and constraints. It's simply not finished yet.
        SudokuBoard board = new SudokuBoard();
        assertFalse(board.isBoardComplete());
    }

    @Test
    public void isFullBoardComplete() {
        SudokuBoard board = getSolvedBoard();
        assertTrue(board.isBoardComplete());
    }

    @Test
    public void isValidationCorrect() {
        for (int row = 0; row < SudokuBoard.SIZE; row++) {
            for (int col = 0; col < SudokuBoard.SIZE; col++) {
                SudokuBoard board = getSolvedBoard();
                int val = board.get(row, col);
                if (val == 1) {
                    board.set(row, col, 2);
                } else {
                    board.set(row, col, 1);
                }

                assertFalse(board.isBoardValid());
                assertFalse(board.isBoardComplete());
            }
        }
    }

    @Test
    public void testSetIfSafe() {
        SudokuBoard board = getSolvedBoard();

        // unset two values
        board.unset(0, 0);
        board.unset(0, 1);

        assertEquals(board.get(0, 0), 0);
        assertEquals(board.get(0, 1), 0);

        // try to set five in two different spaces
        boolean isFirstFiveSet = board.setIfSafe(0, 0, 5);
        boolean isSecondFiveSet = board.setIfSafe(0, 1, 5);

        assertEquals(true, isFirstFiveSet);
        assertEquals(false, isSecondFiveSet);

        // the first five should be set
        assertEquals(board.get(0, 0), 5);
        // the second five should remain a zero
        assertEquals(board.get(0, 1), 0);
    }

    @Test
    public void testToString() {
        SudokuBoard board = getSolvedBoard();
        String ss = board.toString();
        String expected = "534 678 912 \n" +
                "672 195 348 \n" +
                "198 342 567 \n" +
                "\n" +
                "859 761 423 \n" +
                "426 853 791 \n" +
                "713 924 856 \n" +
                "\n" +
                "961 537 284 \n" +
                "287 419 635 \n" +
                "345 286 179 \n\n";
        assertEquals(expected, ss);
    }
}