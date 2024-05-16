package com.vanquyenit.login.Models;

import com.vanquyenit.login.Controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private Connection connection;

    public Login() {
        connection = DataBase.getInstance().getConnection();
    }

    //kiểm tra login
    public boolean loginData( String username,String password) {
        String encodeUsername = Encrypt.encoded(username);
        String encodePassword = Encrypt.encoded(password);
        try {
            // Kiểm tra trong bảng user
            String userQuery = "SELECT * FROM user WHERE username=? AND password=?";
            PreparedStatement userStatement = connection.prepareStatement(userQuery);
            userStatement.setString(1, encodeUsername);
            userStatement.setString(2, encodePassword);
            ResultSet userResultSet = userStatement.executeQuery();

            if (userResultSet.next()) {
                showChatWindow();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Đăng nhập thất bại
    }
    public void showChatWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/MainView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Welcome");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get màn hình tạo tài khoản
    public void showCreateAccountWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SignUpView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("SignUp");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}