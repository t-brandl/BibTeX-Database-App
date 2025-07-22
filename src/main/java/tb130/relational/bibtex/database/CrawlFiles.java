package tb130.relational.bibtex.database;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * This class handles all the file crawling
 */
public class CrawlFiles {
    private static final Logger log = LogManager.getLogger(CrawlFiles.class);

    /**
     * Crawls the given TeX-file with the set pattern and returns the specified capture group
     *
     * @param filepath      Path of the File that is to be crawled
     * @param pattern       RegEx to be used on the crawled String
     * @param capture_group Capture Group to be used for the compiled RegEx
     * @return a Set with the needed tokens
     * @throws IOException
     */
    public Set<String> crawlTex(String filepath, Pattern pattern, int capture_group) throws IOException {
        Stream<String> stream = Files.lines(Paths.get(filepath), Charset.defaultCharset());
        Set<String> collect = new HashSet<String>();
        stream.flatMap(input -> pattern.matcher(input)
                .results())
                .forEach(matchResult -> collect.add(matchResult.group(capture_group)));
        return collect;
    }


    /**
     * Calls the crawlTex function with a declared RegEx that focuses on parsing the \cite and \nocite commands
     *
     * @param filepath Path of the File that is to be crawled
     * @return A Set with the cite keys
     * @throws IOException
     */
    public Set<String> crawlTexForCite(String filepath) throws IOException {
        Pattern pattern = Pattern.compile("\\\\[no]?cite(?>\\[([\\w\\W]*?)?\\])?\\{([^.]*?)\\}");

        return crawlTex(filepath, pattern, 2);
    }

    /**
     * Calls the crawlTex function with a declared RegEx that focuses on parsing the \bibliography command
     *
     * @param filepath Path of the File that is to be crawled
     * @return A Set with the included bibliography files
     * @throws IOException
     */
    public Set<String> crawlTexForBibliography(String filepath) throws IOException {
        Pattern pattern = Pattern.compile("\\\\bibliography\\{([^.]*?)\\}");
        Set<String> tmp = crawlTex(filepath, pattern, 1);
        Set<String> result = new HashSet<String>();
        for (String i : tmp) {
            for (String j : i.split(",")) {
                result.add(j);
            }
        }
        return result;
    }

    /**
     * Calls the crawlTex function with a declared RegEx that focuses on parsing the include and input command
     *
     * @param filepath Path of the File that is to be crawled
     * @return A Set with the included include and input files
     * @throws IOException
     */
    public Set<String> crawlTexForInputInclude(String filepath) throws IOException {
        Pattern pattern = Pattern.compile("\\\\(?>include|input)\\{([^.]*?)\\}");
        Set<String> tmp = crawlTex(filepath, pattern, 1);
        Set<String> result = new HashSet<String>();
        for (String i : tmp) {
            result.add(i + ".tex");
        }
        return result;
    }


    /**
     * @param filepath Path of the File that is to be crawled
     * @return A Map with the Entries, mapped like this: cite_key : [mandatory attributes, optional attributes]
     * @throws IOException
     */
    public Map<String, Map<String, String>> crawlBibForEntries(String filepath) throws IOException {
        Stream<String> stream = Files.lines(Paths.get(filepath), Charset.defaultCharset());
        Pattern pattern = Pattern.compile("\\@(\\w+)\\s*\\{\\s*([\\w\\d\\-\\_]+),((?>\\s*?(?>\\w+)\\s*\\=\\s*\\\"(?>[^\"]+)\\\"\\,?)*)\\s*\\}");
        // \@(\w+)\s*\{\s*([\w\d\-\_]+),((?>\s*?(?>\w+)\s*\=\s*\"(?>[^"]+)\"\,?)*)\s*\}
        /* CaptureGroups: 1: cite_type(ARTICLE), 2: cite_key(howdy), 3: Corpus (author ... reg}")
            @ARTICLE {howdy,
            author   = "simpson",
            title    = "yea",
            journal  = "sfrgrhtjt",
            year     = "2015",
            volume   = "215245",
            number   = "grthtzj",
            pages    = "222-458",
            month    = "feb",
            crossref = "that",
            note     = "url{asdeareg}"
         */
        return null;
    }
}