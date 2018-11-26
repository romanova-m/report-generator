package romanova.data;

import org.junit.Assert;
import org.junit.Test;
import romanova.format.TextFormatter;
import romanova.settings.Settings;
import romanova.settings.SettingsDAO;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FileMakerTest {

    @Test
    public void writeFileTest() throws Exception {
        FileMaker fileMaker = new FileMaker();
        Settings settings = new SettingsDAO().readXML("settings.xml");
        FileDAO fileDAO = new FileDAO(settings.getColumns().getColumn(),
                "source-data.tsv");
        TextFormatter textFormatter = new TextFormatter(settings);
        ArrayList<String> data = textFormatter.format(fileDAO.getMatrix());
        assertEquals(0, fileMaker.writeFile(
                "output.txt", data));
    }
}