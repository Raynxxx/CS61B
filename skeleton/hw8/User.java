/* User.java */

/**
 *  A User object represents a SodaPrintr customer. It stores the user ID of
 *  the user and the number of pages that user has printed.
 */
public class User {
    private int userId;
    private int pagesPrinted;

    /**
     *  User() constructs a user with the userId of "id" and a count of "pages"
     *  for pagesPrinted.
     */
    public User(int id, int pages){
        userId = id;
        pagesPrinted = pages;
    }

    /**
     *  getId() returns the user ID of the User.
     */
    public int getId(){
        return userId;
    }

    /**
     *  getPagesPrinted() returns the number of pages this User has printed.
     */
    public int getPagesPrinted(){
        return pagesPrinted;
    }

    public String toString(){
        return ("User ID: " + userId + ", Pages Printed: " + pagesPrinted);
    }

    /** Compares this to other based on ids. */
    public int compareById(User other) {
        return userId - other.userId;
    }

    /** Compares this to other based on pages printed. */
    public int compareByPagesPrinted(User other) {
        return pagesPrinted - other.pagesPrinted;
    }

    /** Returns true if other is equal to this. */
    public boolean equals(Object x) {
        if (x == this) return true;
        if (x == null) return false;
        if (x.getClass() != this.getClass()) return false;
        User that = (User) x;
        return (this.userId == that.userId) && (this.pagesPrinted == that.pagesPrinted);
    }
}
