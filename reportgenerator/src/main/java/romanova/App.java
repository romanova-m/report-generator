package romanova;

import romanova.data.FileDAO;
import romanova.data.FileMaker;
import romanova.format.TextFormatter;
import romanova.settings.Settings;
import romanova.settings.SettingsDAO;

public class App {
    public static void main(String args[]) throws Exception {
        if (args.length < 3) {System.out.println("Missing files"); return;}

        String xmlFile = args[0];
        String tsvFile = args[1];
        String txtFile = args[2];

        //Receiving settings
        Settings settings = new SettingsDAO().readXML(xmlFile);

        //Receiving data
        FileDAO fileDAO = new FileDAO(settings.getColumns().getColumn(), tsvFile);
        TextFormatter textFormatter = new TextFormatter(settings);

        //Writing to file
            new FileMaker().writeFile(txtFile,
                    textFormatter.format(fileDAO.getMatrix()));

    }
}
