import java.io.File;

/**
 * Piece
 * Created by rayn on 2015-9-4.
 */
public class Piece {
    private boolean isFire;
    private Board board;
    private int x;
    private int y;
    private String type;
    private boolean hasCaptured;
    private boolean isKing;

    public Piece(boolean isFire, Board b, int x, int y, String type) {
        this.isFire = isFire;
        this.board = b;
        this.x = x;
        this.y = y;
        this.type = type;
        this.hasCaptured = false;
        this.isKing = false;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getType() {
        return type;
    }

    public boolean isFire() {
        return isFire;
    }
    public int side() {
        return isFire ? 0 : 1;
    }
    public boolean isKing() {
        return isKing;
    }
    public boolean isBomb() {
        return type.equals("bomb");
    }
    public boolean isShield() {
        return type.equals("shield");
    }
    public void move(int x, int y) {
        if (board.hasCaptured != null) {
            board.remove(board.hasCaptured.x, board.hasCaptured.y);
        }
        board.pieces[x][y] = board.remove(this.x, this.y);
        this.x = x;
        this.y = y;
        board.hasMoved = true;
    }
    public boolean hasCaptured() {
        return hasCaptured;
    }
    public void doneCapturing() {
        hasCaptured = true;
    }
    public String imgPath() {
        String ret = "img" + File.separatorChar + this.type;
        ret += this.isFire() ? "-fire" : "-water";
        ret += this.isKing() ? "-crowned.png" : ".png";
        return ret;
    }
}
