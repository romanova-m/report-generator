package romanova.format;

import org.junit.Test;
import romanova.settings.Column;
import romanova.settings.Columns;
import romanova.settings.Page;
import romanova.settings.Settings;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class TextFormatterTest {

    @Test
    public void formatTest() {
        Settings settings = new Settings();
        Column column = new Column("A",5);
        settings.setColumns(new Columns(new ArrayList<Column>()));
        settings.setPage(new Page(17, 10));
        settings.getColumns().getColumn().add(0,column);
        settings.getColumns().getColumn().add(0,column);

        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        ArrayList<String> dataElem1 = new ArrayList<String>();
        ArrayList<String> dataElem2 = new ArrayList<String>();
        dataElem1.add("ABCDE"); dataElem1.add("ABCDE");
        dataElem2.add("ABCDEFG");dataElem2.add("A");
        data.add(dataElem1);
        data.add(dataElem2);

        TextFormatter textFormatter = new TextFormatter(settings);
        ArrayList<String> result =textFormatter.format(data);
        assertEquals("| A     | A     |", result.get(0));
        assertEquals("-----------------", result.get(1));
        assertEquals("| ABCDE | ABCDE |", result.get(2));
        assertEquals("-----------------", result.get(3));
        assertEquals("| ABCDE | A     |", result.get(4));
        assertEquals("| FG    |       |", result.get(5));
    }
}