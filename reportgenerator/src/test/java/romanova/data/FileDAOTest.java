package romanova.data;

import org.junit.Test;
import romanova.data.FileDAO;
import romanova.settings.Settings;
import romanova.settings.SettingsDAO;

import static org.junit.Assert.*;

public class FileDAOTest {

    FileDAO fileDAO;

    public FileDAOTest() throws Exception{
        Settings settings = new SettingsDAO().readXML("settings.xml");
        this.fileDAO = new FileDAO(settings.getColumns().getColumn(),
                "source-data.tsv");
    }

    @Test
    public void initHeadersTest() {
        assertEquals("Дата", fileDAO.getHeaders().get(1));
    }

    @Test
    public void initMatrixTest() {
        assertEquals("1", fileDAO.getMatrix().get(0).get(0));
        assertEquals("25/11", fileDAO.getMatrix().get(0).get(1));
        assertEquals("Павлов Дмитрий", fileDAO.getMatrix().get(0).get(2));
        assertEquals("Юлианна-Оксана Сухово-Кобылина", fileDAO.getMatrix().get(4).get(2));
    }
}