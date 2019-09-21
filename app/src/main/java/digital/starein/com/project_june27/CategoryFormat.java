package digital.starein.com.project_june27;

public class CategoryFormat {

    private String Title;
    private String Author;
    private String Lines;

    public CategoryFormat(String title,String author,String lines){
        this.Title=title;
        this.Author=author;
        this.Lines=lines;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getLines() {
        return Lines;
    }

    public void setLines(String lines) {
        Lines = lines;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.Author.equals(((CategoryFormat) obj).Author)
                && this.Title.equals(((CategoryFormat) obj).Title) && this.Lines
                .equals(((CategoryFormat) obj).Lines));
    }
}
