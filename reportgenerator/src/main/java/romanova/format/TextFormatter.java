package romanova.format;

import romanova.settings.Settings;

import java.util.ArrayList;

public class TextFormatter {

    private Settings settings;
    private ArrayList<String> queue;
    private ArrayList<String> queueHelper;
    private ArrayList<String> header;
    private int pageWidth;
    private int pageHeight;

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public TextFormatter(Settings settings) {
        this.settings = settings;
        this.pageWidth = settings.getPage().getWidth();
        this.pageHeight = settings.getPage().getHeight();
        this.queue = new ArrayList<String>(settings.getColumns().getColumn().size());
        this.header = settings.getHeader();
        this.header = formatRow(header, new ArrayList<String>());
    }

    private String makeSep() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < pageWidth; i++)
            result.append("-");
        return result.toString();
    }

    public ArrayList<String> format(ArrayList<ArrayList<String>> rawData) {
        return formatPages(formatColumns(rawData));
    }

    private ArrayList<String> formatPages(ArrayList<String> formattedData) {
        int index = 0;
        int step = addHeader(formattedData, index);
        index += step;
        while (index != formattedData.size()) {
            if (step % pageHeight == 0) {
                index = getLastSep(formattedData, index);
                step = addHeader(formattedData, index);
                formattedData.add(index, "~");
                index += step;
            }
            index++;
            step++;
        }
        return formattedData;
    }

    private int getLastSep(ArrayList<String> formattedData, int index) {
        for (int i = index; i > 0; i--) {
            if (formattedData.get(i).equals(makeSep()))
                return i;
        }
        return index;
    }

    //Returns num of rows
    private int addHeader(ArrayList<String> formattedData, int index) {
        int step = 0;
        for (String element : header) {
            formattedData.add(index++, element);
            step++;
        }
        return step;
    }

    private ArrayList<String> formatColumns(ArrayList<ArrayList<String>> rawData) {
        ArrayList<String> result = new ArrayList<String>();
        for (ArrayList<String> row : rawData) {
            result.add(makeSep());
            formatRow(row, result);
        }
        return result;
    }

    // Converts row to array with separators and fields with required length
    private ArrayList<String> formatRow(ArrayList<String> row, ArrayList<String> result) {
        String srow = "|";
        srow = processRow(row, srow, result);
        while (!isQueueEmpty()) {
            srow = processRow(queueHelper, srow, result);
        }
        return result;
    }

    private String processRow(ArrayList<String> row, String srow, ArrayList<String> result) {
        srow = writeStr(row, srow);
        result.add(srow);
        srow = "|";
        queueHelper = (ArrayList<String>) queue.clone();
        queue.clear();
        return srow;
    }

    //Convert row array to string
    private String writeStr(ArrayList<String> row, String srow) {
        for (int index = 0; index < row.size(); index++) {
            String element = row.get(index);
            int width = settings.getColumns().getColumn().get(index).getWidth();
            srow = addToStr(srow, subStr(element, width, index), width);
        }
        return srow;
    }

    private String addToStr(String srow, String element, int width) {
        StringBuilder add = new StringBuilder();
        for (int i = element.length(); i < width; i++)
            add.append(" ");
        srow += " " + element + add + " |";
        return srow;
    }


    //Crop string
    private String subStr(String element, int width, int index) {
        String result;
        if (element.length() > width) {
            //Has spaces?
            int spaceIndex = element.substring(0, width + 1).lastIndexOf(' ');
            if (spaceIndex > 0) {
                result = element.substring(0, spaceIndex);
                queue.add(index, element.substring(spaceIndex + 1, element.length()));
            } else {
                //Has separators?
                spaceIndex = getLastSep(element, width);
                if (spaceIndex > 0) {
                        result = element.substring(0, spaceIndex + 1);
                        queue.add(index, element.substring(spaceIndex + 1, element.length()));
                } else {
                    //Has nothing
                    result = element.substring(0, width);
                    queue.add(index, element.substring(width, element.length()));
                }
            }
        } else {
            //fits width
            result = element;
            queue.add(index, "");
        }
        return result;
    }

    //returns 0<=x<=width
    private int getLastSep(String element, int width) {
        for (int i = width - 1; i > 0; i--) {
            if (!Character.isLetterOrDigit(element.charAt(i))) return i;
        }
        return -1;
    }

    private boolean isQueueEmpty() {
        boolean empty = true;
        for (String string : queueHelper) {
            if (!string.equals("")) empty = false;
        }
        return empty;
    }
}
