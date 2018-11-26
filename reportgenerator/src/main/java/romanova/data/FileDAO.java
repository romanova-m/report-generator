package romanova.data;

import romanova.settings.Column;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileDAO {

    private ArrayList<String> headers = new ArrayList<String>();
    private ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();

    public FileDAO() {
    }

    public FileDAO(List<Column> columns, String path) {
        initHeaders(columns);
        try {
            initMatrix(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initMatrix(String path) throws Exception {
        BufferedReader TSVFile = new BufferedReader(new InputStreamReader(new FileInputStream(
                (new File(path))), "UTF-16"));
        int row = 0;
        String dataRow = TSVFile.readLine();
        while (dataRow != null) {
            String[] dataArray = dataRow.split("\\t");
            matrix.add(row++, new ArrayList<String>(Arrays.asList(dataArray)));
            dataRow = TSVFile.readLine(); // Read next line of data.
        }
        TSVFile.close();
    }

    public void initHeaders(List<Column> columns) {
        for (int i = 0; i < columns.size(); i++) {
            headers.add(i, columns.get(i).getTitle());
        }
    }

    public ArrayList<String> getHeaders() {
        return headers;
    }

    public ArrayList<ArrayList<String>> getMatrix() {
        return matrix;
    }
}
