package Library;

public class Book{

    public int isbn;
    public String title;
    public String author;
    public float price;
    public String category;
    public String description;

    public Book(){

    }

    public Book(String category){
        this.category = category;
    }

    public Book(String title, String author, float price){
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public Book(int isbn, String title, String author, float price, String category, String description){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public int getIsbn(){
        return isbn;
    }

    public void setIsbn(int isbn){
        this.isbn = isbn;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public float getPrice(){
        return price;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
