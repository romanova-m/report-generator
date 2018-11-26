package romanova.data;

import java.io.*;
import java.util.ArrayList;

public class FileMaker {

    public int writeFile(String path, ArrayList<String> data) throws Exception {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(new File(path)), "UTF-16"));
        writeData(data, bufferedWriter);
        bufferedWriter.close();
        return 0;
    }

    private void writeData(ArrayList<String> data, BufferedWriter bufferedWriter) throws Exception {
        for (String string : data) {
            bufferedWriter.write(string, 0, string.length());
            bufferedWriter.newLine();
        }
    }
}
