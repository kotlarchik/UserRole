package kotlarchik.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import kotlarchik.dao.DAO;
import kotlarchik.model.Role;
import kotlarchik.model.User;
import kotlarchik.service.ServiceRole;
import kotlarchik.service.ServiceUser;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainController {
    ObservableList<User> userList = FXCollections.observableArrayList();
    ObservableList<Role> roleList = FXCollections.observableArrayList();

    // Table Users
    @FXML
    private TableView<User> tableUsers;
    @FXML
    private TableColumn<User, String> columnFirstName;
    @FXML
    private TableColumn<User, String> columnLastName;

    // Label
    @FXML
    private Label labelID;
    @FXML
    private Label labelFirstName;
    @FXML
    private Label labelLastName;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelCountRoles;
    @FXML
    private Label labelRegDate;

    // ComboBox
    @FXML
    private ComboBox<Role> roleComboBox;

    // Table Role
    @FXML
    private TableView<Role> tableRoles;
    @FXML
    private TableColumn<Role, Integer> columnIDRole;
    @FXML
    private TableColumn<Role, String> columnNameRole;


    public void initialize(){
        findByAll();

        // Users
        columnFirstName.setCellValueFactory(u ->
                new SimpleObjectProperty<>(u.getValue().getFirstName()));
        columnLastName.setCellValueFactory(u ->
                new SimpleObjectProperty<>(u.getValue().getLastName()));
        tableUsers.setItems(userList);

        // Roles
        columnIDRole.setCellValueFactory(u ->
                new SimpleObjectProperty<>(u.getValue().getId()));
        columnNameRole.setCellValueFactory(u ->
                new SimpleObjectProperty<>(u.getValue().getName()));

        tableRoles.setItems(roleList);

//        roleColumn.setCellValueFactory(u ->
//                new SimpleObjectProperty<>(u.getValue().getUsers().iterator().next().getRole().getName()));
//        emailColumn.setCellValueFactory(u ->
//                new SimpleObjectProperty<>(u.getValue().getEmail()));
//        countColumn.setCellValueFactory(u ->
//                new SimpleObjectProperty<>(u.getValue().getUsers().size()));
//        regDateColumn.setCellValueFactory(u ->
//                new SimpleObjectProperty<>(u.getValue().getUsers().iterator().next().getRegistrationDate()));

        tableUsers.getSelectionModel().selectedItemProperty().addListener((observableValue, user, t1) -> showUserDetail(t1));
    }

    public void showUserDetail(User user){
        labelID.setText(String.valueOf(user.getId()));
        labelFirstName.setText(user.getFirstName());
        labelLastName.setText(user.getLastName());
        roleComboBox.setItems(roleList);
        labelEmail.setText(user.getEmail());
        labelCountRoles.setText(String.valueOf(roleList.size()));
        labelRegDate.setText(String.valueOf(user.getUsers().iterator().next().getRegistrationDate()));
    }

     private void findByAll(){
         SessionFactory factory = new Configuration().configure().buildSessionFactory();
         DAO<User, Integer> userDao = new ServiceUser(factory);
         DAO<Role, Integer> roleDao = new ServiceRole(factory);
         userList.addAll(userDao.readAll());
         roleList.addAll(roleDao.readAll());

         factory.close();
     }
}