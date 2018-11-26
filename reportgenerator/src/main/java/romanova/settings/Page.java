package romanova.settings;

public class Page {

    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Page() {
    }

    public Page(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
