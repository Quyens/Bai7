module com.vanquyenit.login {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vanquyenit.login to javafx.fxml;
    exports com.vanquyenit.login;
}