package romanova.settings;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Settings {
    private Page page;
    private Columns columns;

    public Settings() {
    }

    public Settings(Page page, Columns columns) {
        this.page = page;
        this.columns = columns;
    }

    @XmlElement
    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @XmlElement
    public Columns getColumns() {
        return columns;
    }

    public void setColumns(Columns columns) {
        this.columns = columns;
    }

    public ArrayList<String> getHeader() {
        ArrayList<String> result = new ArrayList<String>();
        for (Column column : columns.getColumn()) {
            result.add(column.getTitle());
        }
        return result;
    }
}
