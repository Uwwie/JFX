package scraps;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateColumn {
    String sql;
    public void CreateColumns(Connection f) throws SQLException, FileNotFoundException {
        Statement st = f.createStatement();
        sql = "CREATE TABLE IF NOT EXISTS FIRST "
                +  "(id INT AUTO_INCREMENT, "
                + " word VARCHAR(255))";
        st.executeUpdate(sql);

        sql = "CREATE TABLE IF NOT EXISTS SECOND "
                +  "(id INT AUTO_INCREMENT, "
                + " word VARCHAR(255))";
        st.executeUpdate(sql);

        sql = "CREATE TABLE IF NOT EXISTS THIRD "
                +  "(id INT AUTO_INCREMENT, "
                + " word VARCHAR(255))";
        st.executeUpdate(sql);

        sql = "CREATE TABLE IF NOT EXISTS FOURTH "
                +  "(id INT AUTO_INCREMENT, "
                + " word VARCHAR(255))";
        st.executeUpdate(sql);

        sql = "CREATE TABLE IF NOT EXISTS FACTS "
                +  "(id INT AUTO_INCREMENT, "
                + " word VARCHAR(255))";
        st.executeUpdate(sql);

        InjectInfo(st);
        st.close();
    }

    public void InjectInfo(Statement st) throws FileNotFoundException, SQLException {
        File file = new File("1.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            sql = "INSERT INTO FIRST(word) values";
            sql = sql + "('"+ sc.nextLine() + "')";
            st.executeUpdate(sql);
        }

        file = new File("2.txt");
        sc = new Scanner(file);
        while (sc.hasNextLine()) {
            sql = "INSERT INTO SECOND(word) values";
            sql = sql + "('"+ sc.nextLine() + "')";
            st.executeUpdate(sql);
        }

        file = new File("3.txt");
        sc = new Scanner(file);
        while (sc.hasNextLine()) {
            sql = "INSERT INTO THIRD(word) values";
            sql = sql + "('"+ sc.nextLine() + "')";
            st.executeUpdate(sql);
        }

        file = new File("4.txt");
        sc = new Scanner(file);
        while (sc.hasNextLine()) {
            sql = "INSERT INTO FOURTH(word) values";
            sql = sql + "('"+ sc.nextLine() + "')";
            st.executeUpdate(sql);
        }

        file = new File("5.txt");
        sc = new Scanner(file);
        while (sc.hasNextLine()) {
            sql = "INSERT INTO FACTS(word) values";
            sql = sql + "('"+ sc.nextLine() + "')";
            st.executeUpdate(sql);
        }

    }
}
