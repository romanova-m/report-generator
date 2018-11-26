package romanova.settings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class SettingsDAO {

    public synchronized Settings readXML(String path) throws Exception {
            JAXBContext jc = JAXBContext.newInstance(Settings.class);
            Unmarshaller u = jc.createUnmarshaller();
            Settings settings = (Settings) u.unmarshal(new File(path));
            return settings;
    }
}
