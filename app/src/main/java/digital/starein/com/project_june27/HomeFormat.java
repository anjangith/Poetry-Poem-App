package digital.starein.com.project_june27;

public class HomeFormat {

    private String title;
    private String author;
    private String lines;
    private String lineCount;


    public HomeFormat(String t,String a,String l){
        this.title=t;
        this.author=a;
        this.lines=l;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLines() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }

    public String getLineCount() {
        return lineCount;
    }

    public void setLineCount(String lineCount) {
        this.lineCount = lineCount;
    }


}
