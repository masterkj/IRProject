import imsspell.SpellCheck;
import org.apache.commons.configuration.ConfigurationException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ConfigurationException {
        SpellCheck spellCheck = new SpellCheck();
        spellCheck.getMisspelled("the boyss");
//        new IndexBuilder().build();
    }
}
