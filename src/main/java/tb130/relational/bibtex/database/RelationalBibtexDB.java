package tb130.relational.bibtex.database;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RelationalBibtexDB extends Application {

    private static final Logger log = LogManager.getLogger(RelationalBibtexDB.class);
    @FXML
    private TextField citekeyRemDialogue;
    @FXML
    private Label statusLabel;
    @FXML
    private Label addRecordStatus;
    @FXML
    private Label remRecordStatus;
    @FXML
    private TextField filePathTextField;
    @FXML
    private TextField bibFileTextPath;
    @FXML
    private Button crawlButton;
    @FXML
    private Button cancelBibButton;
    @FXML
    private Button bibDirectoryButton;
    @FXML
    private CheckBox optionScanLinked;
    @FXML
    private Button cancelRemRecordButton;
    @FXML
    private Button cancelAddRecordButton;
    @FXML
    private Label bibCrawlStatus;
    @FXML
    private TextField citekeyDialogue;
    @FXML
    private TextField bibtypeDialogue;
    @FXML
    private TextField authorDialogue;
    @FXML
    private TextField editorDialogue;
    @FXML
    private TextField yearDialogue;
    @FXML
    private TextField titleDialogue;
    @FXML
    private TextField booktitleDialogue;
    @FXML
    private TextField journalDialogue;
    @FXML
    private TextField publisherDialogue;
    @FXML
    private TextField howpublishedDialogue;
    @FXML
    private TextField addressDialogue;
    @FXML
    private TextField organizationDialogue;
    @FXML
    private TextField institutionDialogue;
    @FXML
    private TextField schoolDialogue;
    @FXML
    private TextField volumeDialogue;
    @FXML
    private TextField seriesDialogue;
    @FXML
    private TextField typeDialogue;
    @FXML
    private TextField editionDialogue;
    @FXML
    private TextField chapterDialogue;
    @FXML
    private TextField monthDialogue;
    @FXML
    private TextField numberDialogue;
    @FXML
    private TextField pagesDialogue;
    @FXML
    private TextField noteDialogue;
    @FXML
    private String fileName;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method opens the File Chooser, in order to select the LaTeX-Document of the users choice
     *
     * @param event The Event that happened. Can be empty, since it isn't used
     */
    @FXML
    protected void handleDirectoryCall(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("LaTeX-Documents (*.tex)", "*.tex");
        fileChooser.getExtensionFilters().add(extFilter);
        Window owner = crawlButton.getScene().getWindow();
        File file = fileChooser.showOpenDialog(owner);
        if (file != null) {
            fileName = file.getAbsolutePath().replaceFirst("[.]tex$", "");
            ;
            statusLabel.setText("Successfully selected a directory");
            filePathTextField.setText(file.getPath());
            log.info("Successfully selected a directory - directory call");
        }
    }

    /**
     * This method opens the File Chooser, in order to select the LaTeX-Document of the users choice
     *
     * @param event The Event that happened. Can be empty, since it isn't used
     */
    @FXML
    protected void handleBibDirectoryCall(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("BibTeX-Documents (*.bib)", "*.bib");
        fileChooser.getExtensionFilters().add(extFilter);
        Window owner = bibDirectoryButton.getScene().getWindow();
        File file = fileChooser.showOpenDialog(owner);
        if (file != null) {
            bibCrawlStatus.setText("Successfully selected a directory");
            bibFileTextPath.setText(file.getPath());
            log.info("Successfully selected a directory - bib directory call");
        }
    }

    private DBAccess getDBaccess() throws IOException, ClassNotFoundException, SQLException {
        DBconfig dbconfig = new LoadConfigurations().loadDBconfig();
        DBAccess dbaccess = new DBAccess(dbconfig.getJdbcurl(), dbconfig.getUsername(), dbconfig.getPassword());
        return dbaccess;
    }

    /**
     * This method collects the values from the opened "remove Record"
     *
     * @param event The Event that happened. Can be empty, since it isn't used
     */
    @FXML
    protected void handleRemRecordCall(ActionEvent event) {
        remRecordStatus.setText("");
        String val = citekeyRemDialogue.getText();
        DBAccess dbaccess;
        try {
            dbaccess = getDBaccess();
            remRecordStatus.setText("Successfully removed one item from the Database!");
            log.debug("Successfully removed one item from the Database!");
        } catch (IOException e) {
            remRecordStatus.setText("Please specify a DBconfig.json containing jdbcurl, username and password");
            log.error("Please specify a DBconfig.json containing jdbcurl, username and password:\n{}", e.getLocalizedMessage());
            return;
        } catch (ClassNotFoundException e) {
            remRecordStatus.setText("JDBC Driver was not found");
            log.error("JDBC Driver was not found:\n{}", e.getLocalizedMessage());
            return;
        } catch (SQLException e) {
            remRecordStatus.setText("Cannot establish a DB connection, wrong credentials or URL maybe");
            log.error("Cannot establish a DB connection, wrong credentials or URL maybe:\n{}", e.getLocalizedMessage());
            return;
        }
        try {
            dbaccess.remRecordFromDB(val);
        } catch (SQLException e) {
            remRecordStatus.setText("Could not remove Entry.\nWrong key maybe?");
            log.error("Could not remove Entry.\nWrong key maybe?\n{}", e.getLocalizedMessage());
            return;
        }
    }

    /**
     * This method collects the values from the opened "add Record"
     *
     * @param event The Event that happened. Can be empty, since it isn't used
     */
    @FXML
    protected void handleAddRecordCall(ActionEvent event) {
        addRecordStatus.setText("");
        DBAccess dbaccess;
        String[] arr = {citekeyDialogue.getText(), bibtypeDialogue.getText(),
                authorDialogue.getText(), editorDialogue.getText(), addressDialogue.getText(),
                titleDialogue.getText(), booktitleDialogue.getText(), journalDialogue.getText(),
                publisherDialogue.getText(), yearDialogue.getText(), howpublishedDialogue.getText(),
                organizationDialogue.getText(), institutionDialogue.getText(), schoolDialogue.getText(),
                volumeDialogue.getText(), seriesDialogue.getText(), typeDialogue.getText(),
                editionDialogue.getText(), chapterDialogue.getText(), monthDialogue.getText(),
                numberDialogue.getText(), pagesDialogue.getText(), noteDialogue.getText()};
        try {
            dbaccess = getDBaccess();
        } catch (IOException e) {
            addRecordStatus.setText("Please specify a DBconfig.json containing jdbcurl, username and password");
            log.error("Please specify a DBconfig.json containing jdbcurl, username and password:\n{}", e.getLocalizedMessage());
            return;
        } catch (ClassNotFoundException e) {
            addRecordStatus.setText("JDBC Driver was not found");
            log.error("JDBC Driver was not found:\n{}", e.getLocalizedMessage());
            return;
        } catch (SQLException e) {
            addRecordStatus.setText("Cannot establish a DB connection, wrong credentials or URL maybe");
            log.error("Cannot establish a DB connection, wrong credentials or URL maybe:\n{}", e.getLocalizedMessage());
            return;
        }
        try {
            Bibitem bibitem = new Bibitem(arr);
            dbaccess.addRecordToDB(bibitem.getItems());
            addRecordStatus.setText("Successfully added one item to the Database!");
            log.debug("Successfully added one item to the Database!");
        } catch (NotValidBibentryException e) {
            addRecordStatus.setText(e.getMessage());
            log.error(e.getMessage());
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            addRecordStatus.setText("The record array was not the correct size.\nThis should not have happened");
            log.error("The record array was not the correct size. This should not have happened");
            return;
        } catch (SQLException e) {
            addRecordStatus.setText("Couldn't inssrt the Record");
            log.error("The record array was not the correct size. This should not have happened");
            return;
        }
    }

    @FXML
    protected void handleBibCall(ActionEvent event) {
        bibCrawlStatus.setText("This function isn't implemented yet, sorry!");
    }

    @FXML
    protected void handleTeXCall(ActionEvent event) {
        if (!filePathTextField.getText().isEmpty()) {
            DBAccess dbaccess;
            try {
                dbaccess = getDBaccess();
            } catch (IOException e) {
                statusLabel.setText("Please specify a DBconfig.json containing jdbcurl, username and password");
                log.error("Please specify a DBconfig.json containing jdbcurl, username and password:\n{}", e.getLocalizedMessage());
                return;
            } catch (ClassNotFoundException e) {
                statusLabel.setText("JDBC Driver was not found");
                log.error("JDBC Driver was not found:\n{}", e.getLocalizedMessage());
                return;
            } catch (SQLException e) {
                statusLabel.setText("Cannot establish a DB connection, wrong credentials or URL maybe");
                log.error("Cannot establish a DB connection, wrong credentials or URL maybe:\n{}", e.getLocalizedMessage());
                return;
            }
            statusLabel.setText("Status");
            CrawlFiles crawler = new CrawlFiles();
            Set<String> linkedFiles = new HashSet<>();
            Set<String> cites = new HashSet<>();
            if (optionScanLinked.isSelected()) {
                try {
                    statusLabel.setText("Crawling .tex for input and include");
                    log.info("scanLinked selected" + optionScanLinked);
                    linkedFiles = crawler.crawlTexForInputInclude(fileName + ".tex");
                } catch (IOException e) {
                    statusLabel.setText("Selected .tex file doesn't exist anymore");
                    log.error("Selected .tex file doesn't exist anymore", e);
                    return;
                }
            }
            linkedFiles.add(fileName + ".tex");
            log.info("LinkedFiles:\n{}", String.join(", ", linkedFiles));
            statusLabel.setText("Crawling .tex-file(s) for cite commands");
            linkedFiles.forEach(item -> {
                try {
                    Set<String> temp = crawler.crawlTexForCite(item);
                    for (String i : temp) {
                        cites.add(i);
                        log.info("attempt to add to cites: {}", i);
                    }
                } catch (IOException e) {
                    statusLabel.setText("Couldnt read file" + item);
                    log.error("Couldnt read file{}", item, e);
                    return;
                }
            });
            Set<String[]> dbRecords = new HashSet<>();
            try {
                statusLabel.setText("fetching database query");
                log.info("cites:\n{}", String.join(", ", cites));
                dbRecords = dbaccess.getRecordsFromDB(cites);
                log.info("DB successfully queried");
            } catch (SQLException e) {
                statusLabel.setText("Couldn't fetch database query");
                log.error("Couldn't fetch database query", e);
                return;
            }

            statusLabel.setText("Preparing Bibitems for writing");
            log.info("preparing bibitems");
            List<Bibitem> bibitems = new ArrayList<>();
            for (String[] item : dbRecords) {
                    try {
                        log.info("{} {}", item.length, String.join(", ", item));
                        Bibitem tempItem = new Bibitem(item);
                        bibitems.add(tempItem);
                    } catch (NotValidBibentryException e) {
                        statusLabel.setText(e.getMessage());
                        log.error("Error creating Bibitem", e);
                        return;
                    }
            }
            log.info("Bibitems:\n{}", String.join("\n\n", bibitems.toString()));
            try {
                statusLabel.setText("Writing Bibfile");
                OutputFiles out = new OutputFiles();
                out.writeToBib(bibitems, fileName);
            } catch (IOException e) {
                statusLabel.setText("Error writing to file");
                log.error("Error writing to file", e);
                return;
            }
            statusLabel.setText("Done!\nDon't forget to add your \\bibliography{fileName} Tag!");
        } else {
            statusLabel.setText("Please select a directory first");
        }
    }

    /**
     * Opens the add Record window
     *
     * @param event Can be Ignored
     * @throws IOException
     */
    @FXML
    protected void openAddRecordWindow(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/addRecordDialogue.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("add a bibitem to the database");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /**
     * Opens the remove Record window
     *
     * @param event Can be Ignored
     * @throws IOException
     */
    @FXML
    protected void openRemRecordWindow(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/removeRecordDialogue.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("remove a bibitem from the database");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /**
     * Opens the bib crawl window
     *
     * @param event Can be Ignored
     * @throws IOException
     */
    @FXML
    protected void openBibWindow(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/addBibfileDialogue.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Add a Bibfiles content to the database");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /**
     * closes the addRecord Dialogue window
     *
     * @param event Can be ignored, is not used
     */
    @FXML
    private void closeAddRecordDialogue(ActionEvent event) {
        Stage stage = (Stage) cancelAddRecordButton.getScene().getWindow();
        stage.close();
    }

    /**
     * closes the removeRecord Dialogue window
     *
     * @param event Can be ignored, is not used
     */
    @FXML
    private void closeRemRecordDialogue(ActionEvent event) {
        Stage stage = (Stage) cancelRemRecordButton.getScene().getWindow();
        stage.close();
    }

    /**
     * closes the Bib Read Dialogue window
     *
     * @param event Can be ignored, is not used
     */
    @FXML
    private void closeBibDialogue(ActionEvent event) {
        Stage stage = (Stage) cancelBibButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        DBAccess dbaccess;
        try {
            dbaccess = getDBaccess();
        } catch (IOException e) {
            statusLabel.setText("Please specify a DBconfig.json containing jdbcurl, username and password");
            log.error("Please specify a DBconfig.json containing jdbcurl, username and password:\n{}", e.getLocalizedMessage());
        } catch (ClassNotFoundException e) {
            statusLabel.setText("JDBC Driver was not found");
            log.error("JDBC Driver was not found:\n{}", e.getLocalizedMessage());
        } catch (SQLException e) {
            statusLabel.setText("Cannot establish a DB connection, wrong credentials or URL maybe");
            log.error("Cannot establish a DB connection, wrong credentials or URL maybe:\n{}", e.getLocalizedMessage());
        }
        Parent root = FXMLLoader.load(getClass().getResource("/gui.fxml"));
        primaryStage.setTitle("Relational BibTeX Database");
        primaryStage.setScene(new Scene(root));
        primaryStage.onCloseRequestProperty().setValue(e -> Platform.exit());
        primaryStage.show();
    }
}
