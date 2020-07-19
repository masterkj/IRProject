import indexing.Indexer;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import preprocessing.PreProcessor;
import utils.HelpingFunctions;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class IndexBuilder {
    PropertiesConfiguration config;
    String corpusPath;
    PreProcessor preProcessor;
    Indexer indexer;
    FileReader fileReader;
    BufferedReader bufferedReader;
    List<String> splitedLine;

    IndexBuilder() throws ConfigurationException, IOException {
        config = new PropertiesConfiguration();
        config.load("Application.properties");
        corpusPath = config.getString("corpus.path");
        preProcessor = new PreProcessor();
        indexer = new Indexer();
    }


    public void build() throws IOException {

        final File folder = new File(corpusPath);
        for (final File fileEntry : folder.listFiles()) {
            processFile(fileEntry);
            break;
            }
        indexer.build();
        }

    private void processFile(File fileEntry) throws IOException {
        FileReader fr = new FileReader(fileEntry);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null)
            processLine(line, fileEntry.getName());

    }

    private void processLine(String line, String fileName) {
        line = HelpingFunctions.removeDecimeters(line);
        List<String> splitedLine = (new ArrayList<String>(Arrays.asList(line.toLowerCase().split("\\s"))));

        splitedLine = preProcessor.linePreProcess(splitedLine);
        indexer.addLineToDoc(fileName, splitedLine);
    }
}
