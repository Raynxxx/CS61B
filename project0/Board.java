/**
 * Board
 * Created by rayn on 2015-9-4.
 */
public class Board {
    public static int N = 8;
    public static Piece[][] pieces;
    public static int player = 0;
    public Piece selectedPiece;
    public Piece hasCaptured;
    public boolean hasMoved;

    public static void main(String[] args) {
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while (true) {
            Board.drawBoard(N);
            while (!b.canEndTurn()) {
                if (StdDrawPlus.mousePressed()) {
                    int x = (int) StdDrawPlus.mouseX();
                    int y = (int) StdDrawPlus.mouseY();
                    System.out.println("pressed (" + x + ", " + y + ").");
                    if (b.canSelect(x, y)) {
                        b.select(x, y);
                        System.out.println("select (" + x + ", " + y + ").");
                    }
                }
                StdDrawPlus.show(100);
            }
            b.endTurn();
            System.out.println("end turn");
            StdDrawPlus.show(100);
        }
    }

    public Board(boolean shouldBeEmpty) {
        this.selectedPiece = null;
        this.hasCaptured = null;
        this.hasMoved = false;
        this.pieces = new Piece[N][N];
        if (!shouldBeEmpty) {
            configDefaultBoard();
        }
    }
    private void configDefaultBoard() {
        for (int i = 0; i < N; i += 2) {
            pieces[i][0] = new Piece(true, this, i, 0, "pawn");
            pieces[i + 1][1] = new Piece(true, this, i + 1, 1, "shield");
            pieces[i][2] = new Piece(true, this, i, 2, "bomb");
            pieces[i + 1][5] = new Piece(false, this, i + 1, 5, "bomb");
            pieces[i][6] = new Piece(false, this, i, 6, "shield");
            pieces[i + 1][7] = new Piece(false, this, i + 1, 7, "pawn");
        }
    }
    private void switchTurn() {
        if (player == 0) {
            player = 1;
        } else {
            player = 0;
        }
    }
    private boolean inBound(int x, int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (inBound(xf, yf) && pieceAt(xf, yf) == null) {
            Piece cur = pieceAt(xi, yi);
            boolean isKing = cur.isKing();
            int dx = Math.abs(xf - xi);
            int dy = yf - yi;
            if (dx == 1 && !cur.hasCaptured()) {
                return isKing ?
                        Math.abs(dy) == 1 :
                        (cur.isFire() ? dy == 1 : dy == -1);
            } else if (dx == 2) {
                if (Math.abs(dy) != 2) {
                    return false;
                } else {
                    Piece pass = pieceAt((xi + xf) / 2, (yi + yf) / 2);
                    if (pass != null && pass.side() != cur.side()) {
                        return isKing || (cur.isFire() ? dy == 2 : dy == -2);
                    }
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public Piece pieceAt(int x, int y) {
        return inBound(x, y) ? pieces[x][y] : null;
    }
    public boolean canSelect(int x, int y) {
        if (!inBound(x, y)) return false;
        Piece curPiece = pieceAt(x, y);
        if (curPiece != null && curPiece.side() == player) {
            if (selectedPiece == null || !hasMoved) {
                return true;
            }
        } else if (curPiece == null) {
            if (selectedPiece != null && !hasMoved &&
                    validMove(selectedPiece.getX(), selectedPiece.getY(), x, y)) {
                return true;
            } else if (selectedPiece != null && selectedPiece.hasCaptured() &&
                    validMove(selectedPiece.getX(), selectedPiece.getY(), x, y)) {
                return true;
            }
        }
        return false;
    }
    public void select(int x, int y) {
        if (pieceAt(x, y) == null) {
            selectedPiece.move(x, y);
        } else {
            selectedPiece = pieceAt(x, y);
        }
    }
    public void place(Piece p, int x, int y) {
        if (inBound(x, y) && p != null) {
            pieces[x][y] = p;
        }
    }
    public Piece remove(int x, int y) {
        if (!inBound(x, y)) {
            System.out.println("Bad (x, y) remove request at (" + x + ", " + y + ")!");
            return null;
        } else if (pieces[x][y] == null) {
            System.out.println("Attemped to remove a null at (" + x + ", " + y + ")!");
            return null;
        }
        Piece ret = pieces[x][y];
        pieces[x][y] = null;
        return ret;
    }
    public boolean canEndTurn() {
        if (hasMoved || hasCaptured != null) {
            return true;
        }
        return false;
    }
    public void endTurn() {
        selectedPiece = null;
        hasCaptured = null;
        hasMoved = false;
        switchTurn();
    }
    public String winner() {
        return null;
    }

    /**
     * Draws an N x N board.
     */
    private static void drawBoard(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + 0.5, j + 0.5,
                            pieces[i][j].imgPath(), 1, 1);
                }
            }
        }
    }
}
