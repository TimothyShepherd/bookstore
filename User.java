package Library;

public class User{

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;

    public User(){

    }

    public User(int id, String first_name, String last_name, String email, String password){

        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }

    public User(String first_name, String last_name, String email, String password){

        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password){

        this.email = email;
        this.password = password;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getFirstName(){
        return first_name;
    }

    public void setFirstName(String first_name){
        this.first_name = first_name;
    }

    public String getLastName(){
        return last_name;
    }

    public void setLastName(String last_name){
        this.last_name = last_name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}