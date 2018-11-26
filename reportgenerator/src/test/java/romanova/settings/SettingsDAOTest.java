package romanova.settings;

import org.junit.Test;
import romanova.settings.Settings;
import romanova.settings.SettingsDAO;

import static org.junit.Assert.*;

public class SettingsDAOTest {

    @Test
    public void readXMLTest() throws Exception{
        SettingsDAO settingsDAO = new SettingsDAO();
        Settings test = settingsDAO.readXML("settings.xml");
        assertNotNull(test);
        assertEquals(12, test.getPage().getHeight());
        assertEquals(32, test.getPage().getWidth());
        assertEquals(8, test.getColumns().getColumn().get(0).getWidth());
        assertEquals("ФИО", test.getColumns().getColumn().get(2).getTitle());
    }
}