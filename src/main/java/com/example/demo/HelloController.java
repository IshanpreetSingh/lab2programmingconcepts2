package com.example.demo;

import com.example.demo.ClassInfo; // Update import statement


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
  public  TextField eid;
    public TextField ename;
    public TextField esubject;
    public TextField emarks;
    @FXML
    private TableView<ClassInfo> tableView; // Update TableView type
    @FXML
    private TableColumn<ClassInfo, Integer> id; // Update TableColumn type
    @FXML
    private TableColumn<ClassInfo, String> name; // Update TableColumn type
    @FXML
    private TableColumn<ClassInfo, String> subject; // Update TableColumn type
    @FXML
    private TableColumn<ClassInfo, Integer> marks; // Update TableColumn type

    ObservableList<ClassInfo> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        marks.setCellValueFactory(new PropertyValueFactory<>("marks"));
        tableView.setItems(list);
    }

    @FXML
    protected void onHelloButtonClick() {
        populateTable();
    }

    public void populateTable() {

        list.clear();
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab1";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM classinfo"; // Use the correct table name
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String subject = resultSet.getString("subject");
                int marks = resultSet.getInt("marks");
                tableView.getItems().add(new ClassInfo(id, name, subject, marks));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Addingbutton(ActionEvent actionEvent) {


    String Ename = ename.getText();
    String Esubject = esubject.getText();
    String Emarks = emarks.getText();

    String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab1";
    String dbUser = "root";
    String dbPassword = "";

    try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
        // Execute a SQL query to retrieve data from the database
        String query = "INSERT INTO `classInfo`(`name`, `subject`, `marks`) VALUES ('"+Ename+"','"+Esubject+"','"+Emarks+"')";
        Statement statement = connection.createStatement();
        statement.execute(query);

        populateTable();


    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void DeletingButton(ActionEvent actionEvent) {
        Integer id = Integer.valueOf(eid.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab1";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM `classinfo` WHERE id = '"+id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void UpdateData(ActionEvent actionEvent) {

        String Eid =eid.getText();
        String Ename = ename.getText();
        String Esubject = esubject.getText();
        String Emarks = emarks.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab1";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "UPDATE `classinfo` SET `name`='"+Ename+"',`subject`='"+Esubject+"',`marks`='"+Emarks+"' WHERE id='"+Eid+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);

            populateTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    }






