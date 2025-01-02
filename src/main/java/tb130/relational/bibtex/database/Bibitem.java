package tb130.relational.bibtex.database;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class for an individual Bibitem
 */
public class Bibitem {
    private int length;
    private String[] items;
    private String type;

    /**
     * Constructor of Bibitem. Creates the specified type of Bibitem with the required and optional values necessary
     * See Type Keyword Mapping for an overview of the required fields
     *
     * @param values String array containing a database row
     * @throws NotValidBibentryException if entry is not a valid bibitem and is missing required fields or is using an unknown cite type
     */
    public Bibitem(String[] values) throws NotValidBibentryException, ArrayIndexOutOfBoundsException {
        length = values.length;
        items = values;
        if (isEmpty(items))
            throw new NotValidBibentryException("Incorrect Bibitem. It is empty");
        if ((isEmpty(items[0])) || (isEmpty(items[1])))
            throw new NotValidBibentryException("Incorrect Bibitem. It is missing it's cite key/cite type");
        type = items[1].toLowerCase();
        items[1] = items[1].toLowerCase();

        switch (type.toUpperCase()) {
            case "ARTICLE":
                if ((isEmpty(items[2])) || (isEmpty(items[5])) || (isEmpty(items[7])) || (isEmpty(items[9])))
                    throw new NotValidBibentryException("Incorrect Bibitem: " + type +
                            "\nIt contains/misses required Values that it shouldnt\n" + values);
                break;
            case "BOOK":
                if ((isEmpty(items[2])) || (isEmpty(items[3])))
                    if ((isEmpty(items[5])) || (isEmpty(items[8])) || (isEmpty(items[9])))
                        throw new NotValidBibentryException("Incorrect Bibitem: " + type +
                                "\nIt contains/misses required Values that it shouldnt\n" + values);
                break;
            case "BOOKLET":
                if ((isEmpty(items[5])))
                    throw new NotValidBibentryException("Incorrect Bibitem: " + type +
                            "\nIt contains/misses required Values that it shouldnt\n" + values);
                break;
            case "INBOOK":
                if ((isEmpty(items[2])) || (isEmpty(items[3])))
                    if ((values[18].isEmpty()) || (values[21].isEmpty()))
                        if ((isEmpty(items[5])) || (isEmpty(items[8])) || (isEmpty(items[9])))
                            throw new NotValidBibentryException("Incorrect Bibitem: " + type +
                                    "\nIt contains/misses required Values that it shouldnt\n" + values);
                break;
            case "INPROCEEDING":
                if ((isEmpty(items[2])) || (isEmpty(items[5])) || (isEmpty(items[6])) || (isEmpty(items[9])))
                    throw new NotValidBibentryException("Incorrect Bibitem: " + type +
                            "\nIt contains/misses required Values that it shouldnt\n" + values);
                break;
            case "CONFERENCE":
                if ((isEmpty(items[2])) || (isEmpty(items[5])) || (isEmpty(items[6])) || (isEmpty(items[9])))
                    throw new NotValidBibentryException("Incorrect Bibitem: " + type +
                            "\nIt contains/misses required Values that it shouldnt\n" + values);
                break;
            case "INCOLLECTION":
                if ((isEmpty(items[2])) || (isEmpty(items[5])) || (isEmpty(items[6])) || (isEmpty(items[8])) || (isEmpty(items[9])))
                    throw new NotValidBibentryException("Incorrect Bibitem: " + type +
                            "\nIt contains/misses required Values that it shouldnt\n" + values);
                break;
            case "MANUAL":
                if ((isEmpty(items[5])))
                    throw new NotValidBibentryException("Incorrect Bibitem: " + type +
                            "\nIt contains/misses required Values that it shouldnt\n" + values);
                break;
            case "PHDTHESIS":
                if ((isEmpty(items[2])) || (isEmpty(items[5])) || (isEmpty(items[9])) || (values[13].isEmpty()))
                    throw new NotValidBibentryException("Incorrect Bibitem: " + type +
                            "\nIt contains/misses required Values that it shouldnt\n" + values);
                break;
            case "MASTERSTHESIS":
                if ((isEmpty(items[2])) || (isEmpty(items[5])) || (isEmpty(items[9])) || (values[13].isEmpty()))
                    throw new NotValidBibentryException("Incorrect Bibitem: " + type +
                            "\nIt contains/misses required Values that it shouldnt\n" + values);
                break;
            case "MISC":
                break;
            case "PROCEEDINGS":
                if ((isEmpty(items[5])) || (isEmpty(items[9])))
                    throw new NotValidBibentryException("Incorrect Bibitem: " + type +
                            "\nIt contains/misses required Values that it shouldnt\n" + values);
                break;
            case "TECHREPORT":
                if ((isEmpty(items[2])) || (isEmpty(items[5])) || (isEmpty(items[9])) || (values[12].isEmpty()))
                    throw new NotValidBibentryException("Incorrect Bibitem: " + type +
                            "\nIt contains/misses required Values that it shouldnt\n" + values);
                break;
            case "UNPUBLISHED":
                if ((isEmpty(items[2])) || (isEmpty(items[5])) || (values[22].isEmpty()))
                    throw new NotValidBibentryException("Incorrect Bibitem: " + type +
                            "\nIt contains/misses required Values that it shouldnt\n" + values);
                break;
            default:
                throw new NotValidBibentryException("Incorrect Bibitem: " + type + "\nIt is not a valid cite type");
        }
    }

    private boolean isEmpty(String[] data) {
        return ((data == null) || (data.length == 0));
    }

    private boolean isEmpty(String data) {
        return ((data == null) || (data.isEmpty()));
    }

    public String[] getItems() {
        return items;
    }

    @Override
    public String toString() {
        String[] entries = {"cite_key", "cite_type", "author", "editor", "address", "title",
                "booktitle", "journal", "publisher", "year", "howpublished",
                "organization", "institution", "school", "volume", "series",
                "type", "edition", "chapter", "month", "number", "pages",
                "note"};
        ;
        String result = "@" + items[1] + "{" + items[0];

        for (int i = 2; i < entries.length; i++) {
            if ((!isEmpty(items[i]))) {
                result += ",\n    " + entries[i] + " = \"" + items[i ] + "\"";
            }
        }
        result += "\n}\n\n";
        return result;
    }
}
