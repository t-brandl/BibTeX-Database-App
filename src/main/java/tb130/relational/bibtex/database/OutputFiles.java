package tb130.relational.bibtex.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * This class handles all the file writing cases
 */
public class OutputFiles {

    private static Logger log = LogManager.getLogger(OutputFiles.class);

    /**
     * This method writes the Bibitems to a Bibfile
     * @param items Bibitems that are supposed to be written to the file
     * @param fileName name of the bib-file that is to be created
     * @return
     */
    public boolean writeToBib(List<Bibitem> items, String fileName) throws IOException {
        fileName = fileName + ".bib";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for(Bibitem item : items){
                writer.write(item.toString());
            }
        } catch(Exception e) {
            log.error("Something went wrong writing to the file:\n" + e.getLocalizedMessage());
            throw new IOException(e);
        }
        return true;
    }

    //public boolean updateBibliographyReference(String texFile, String newEntry){
    //    CrawlFiles crawler = new CrawlFiles();
    //    Set<String> bibliography;
    //    try {
    //        bibliography = crawler.crawlTexForBibliography(texFile);
    //    } catch(IOException e){
    //        log.error("Something went wrong crawling the file:\n" + texFile + "\n" + e.getLocalizedMessage());
    //        return false;
    //    }
    //    if(bibliography.isEmpty()){
    //        Pattern pattern =
    //
    //    } else {
    //
    //    }
    //
    //    bibliography.add(newEntry);
    //    String bib = String.join(",", bibliography);
    //
    //}
}
