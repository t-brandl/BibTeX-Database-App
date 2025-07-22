package tb130.relational.bibtex.database;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.*;

public class CrawlFilesTest {

    private final CrawlFiles crawler = new CrawlFiles();

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private File createTempFile(String fileName, String content) throws IOException {
        File file = tempFolder.newFile(fileName);
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
        return file;
    }

    @Test
    public void testCrawlTex_basicRegexCaptureGroup() throws IOException {
        File file = createTempFile("test.tex", "Hello \\command{value1} and \\command{value2}");

        Set<String> result = crawler.crawlTex(file.getAbsolutePath(),
                java.util.regex.Pattern.compile("\\\\command\\{(.*?)\\}"), 1);

        assertEquals(2, result.size());
        assertTrue(result.contains("value1"));
        assertTrue(result.contains("value2"));
    }

    @Test
    public void testCrawlTexForCite() throws IOException {
        File file = createTempFile("cite.tex", "\\cite{ref1}\\cite[page]{ref2}");

        Set<String> result = crawler.crawlTexForCite(file.getAbsolutePath());

        assertEquals(2, result.size());
        assertTrue(result.contains("ref1"));
        assertTrue(result.contains("ref2"));
    }

    @Test
    public void testCrawlTexForBibliography() throws IOException {
        File file = createTempFile("bib.tex", "\\bibliography{file1,file2}");

        Set<String> result = crawler.crawlTexForBibliography(file.getAbsolutePath());

        assertEquals(2, result.size());
        assertTrue(result.contains("file1"));
        assertTrue(result.contains("file2"));
    }

    @Test
    public void testCrawlTexForInputInclude() throws IOException {
        File file = createTempFile("input.tex", "\\include{chapter1}\\input{intro}");

        Set<String> result = crawler.crawlTexForInputInclude(file.getAbsolutePath());

        assertEquals(2, result.size());
        assertTrue(result.contains("chapter1.tex"));
        assertTrue(result.contains("intro.tex"));
    }

    @Test
    public void testCrawlTex_emptyFileReturnsEmptySet() throws IOException {
        File file = createTempFile("empty.tex", "");

        Set<String> result = crawler.crawlTex(file.getAbsolutePath(),
                java.util.regex.Pattern.compile("\\\\command\\{(.*?)\\}"), 1);

        assertTrue(result.isEmpty());
    }

    @Test(expected = IOException.class)
    public void testCrawlTex_invalidFile_throwsIOException() throws IOException {
        crawler.crawlTex("nonexistent_file.tex", java.util.regex.Pattern.compile(".*"), 1);
    }

    @Test
    public void testCrawlTexForCite_handlesNoMatch() throws IOException {
        File file = createTempFile("nocite.tex", "This file has no citations.");

        Set<String> result = crawler.crawlTexForCite(file.getAbsolutePath());

        assertTrue(result.isEmpty());
    }
}
