package tb130.relational.bibtex.database;

/**
 * Exception in case a Bibitem is not an actual valid Bibitem
 */
public class NotValidBibentryException extends Exception {
    public NotValidBibentryException(String errorMessage) {
        super(errorMessage);
    }
}
