package preprocessing;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import utils.FileCollectingFunctions;

import java.io.IOException;
import java.util.*;

public class StopWordsRemoval {
    List<String> stopWords;

    String stopWordPath;

    public StopWordsRemoval() throws IOException, ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration();
        config.load("Application.properties");
        stopWordPath = config.getString("stopwords.path");
        stopWords = FileCollectingFunctions.getAllWordsInFile(stopWordPath);
    }
    public List<String> removeStopWords(List<String> line){
        line.removeAll(stopWords);
        return line;
    }
}
