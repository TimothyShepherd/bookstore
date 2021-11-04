package Library;

import java.sql.SQLException;
import java.util.List;

public interface BookDao{

    List<Book> getCategories() throws SQLException;

}
