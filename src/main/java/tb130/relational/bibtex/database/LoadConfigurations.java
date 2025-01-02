package tb130.relational.bibtex.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;

/**
 * This class is used to load any and all Configurations necessary in order to run the program
 */
public class LoadConfigurations {
    private static Logger log = LogManager.getLogger(LoadConfigurations.class);

    /**
     * Reads the configuration file DBconfig.json and returns the jdbcurl, the username and the password
     * @return the jdbcurl, the username and the password
     * @throws IOException
     */
    protected DBconfig loadDBconfig() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DBconfig config = mapper.readValue(new File("DBconfig.json"), DBconfig.class);
        log.debug("Loaded DB Config");
        return config;
    }
}
