package tb130.relational.bibtex.database;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class BibitemTest {

    @Test
    public void testValidArticleBibitem() throws Exception {
        String[] values = new String[23];
        values[0] = "smith2023";
        values[1] = "ARTICLE";
        values[2] = "John Smith"; // author
        values[5] = "A Study on Stuff"; // title
        values[7] = "Journal of Stuff"; // journal
        values[9] = "2023"; // year

        Bibitem bibitem = new Bibitem(values);

        assertNotNull(bibitem.getItems());
        assertEquals("smith2023", bibitem.getItems()[0]);
    }

    @Test(expected = NotValidBibentryException.class)
    public void testInvalidArticle_missingAuthor() throws Exception {
        String[] values = new String[23];
        values[0] = "smith2023";
        values[1] = "ARTICLE";
        // missing author (index 2)
        values[5] = "Title";
        values[7] = "Journal";
        values[9] = "2023";

        new Bibitem(values); // should throw
    }

    @Test
    public void testValidBookBibitem() throws Exception {
        String[] values = new String[23];
        values[0] = "book2022";
        values[1] = "BOOK";
        values[2] = "Author";
        values[3] = "Editor";
        values[5] = "Book Title";
        values[8] = "Publisher";
        values[9] = "2022";

        Bibitem bibitem = new Bibitem(values);
        assertEquals("book2022", bibitem.getItems()[0]);
    }

    @Test(expected = NotValidBibentryException.class)
    public void testEmptyBibitemThrows() throws Exception {
        new Bibitem(new String[0]);
    }

    @Test(expected = NotValidBibentryException.class)
    public void testMissingCiteKeyOrTypeThrows() throws Exception {
        String[] values = new String[23];
        values[0] = ""; // cite key
        values[1] = ""; // type
        new Bibitem(values);
    }

    @Test(expected = NotValidBibentryException.class)
    public void testUnknownCiteTypeThrows() throws Exception {
        String[] values = new String[23];
        values[0] = "weird2024";
        values[1] = "ALIEN";
        new Bibitem(values);
    }

    @Test
    public void testToStringRepresentationIncludesFields() throws Exception {
        String[] values = new String[23];
        values[0] = "smith2023";
        values[1] = "ARTICLE";
        values[2] = "John Smith";
        values[5] = "A Study";
        values[7] = "Journal";
        values[9] = "2023";

        Bibitem bibitem = new Bibitem(values);
        String output = bibitem.toString();

        assertTrue(output.contains("@article{smith2023"));
        assertTrue(output.contains("author = \"John Smith\""));
        assertTrue(output.contains("title = \"A Study\""));
        assertTrue(output.contains("year = \"2023\""));
    }

    @Test
    public void testValidMiscDoesNotThrow() throws Exception {
        String[] values = new String[23];
        values[0] = "misc1";
        values[1] = "MISC";
        new Bibitem(values);
    }

    @Test
    public void testValidPhDThesis() throws Exception {
        String[] values = new String[23];
        values[0] = "phd1";
        values[1] = "PHDTHESIS";
        values[2] = "Author";
        values[5] = "Title";
        values[9] = "2022";
        values[13] = "MIT";

        new Bibitem(values); // should not throw
    }

    @Test(expected = NotValidBibentryException.class)
    public void testPhDThesisMissingSchoolThrows() throws Exception {
        String[] values = new String[23];
        values[0] = "phd2";
        values[1] = "PHDTHESIS";
        values[2] = "Author";
        values[5] = "Title";
        values[9] = "2022";
        // missing school (index 13)

        new Bibitem(values); // should throw
    }
}
