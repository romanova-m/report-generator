package romanova.settings;

import java.util.List;

public class Columns {

    private List<Column> columns;

    public List<Column> getColumn() {
        return columns;
    }

    public void setColumn(List<Column> columns) {
        this.columns = columns;
    }

    public Columns(){}
    public Columns(List<Column> columns){
        this.columns = columns;
    }
}
