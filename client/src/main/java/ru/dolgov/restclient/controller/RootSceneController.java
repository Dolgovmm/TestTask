package ru.dolgov.restclient.controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.dolgov.restclient.client.User;

/**
 * Created by Михалыч on 28.05.2017.
 */
public class RootSceneController {
    public TextField textFieldLogin;
    public PasswordField passFieldPassword;
    public Label loginLbl;
    public Label passwordLbl;
    public Button authorizeBtn;

    public void onAuthorizeBtnClick() {
        if (User.getLogin().isEmpty()) {
            User.setLogin(textFieldLogin.getText());
            User.setPassword(passFieldPassword.getText());
            setAuthorizeDisable();
        } else {
            User.setLogin("");
            User.setPassword("");
            setAuthorizeEnable();
        }
    }

    private void setAuthorizeDisable() {
        loginLbl.setText("Добро пожаловать " + User.getLogin());
        textFieldLogin.setText("");
        textFieldLogin.setVisible(false);
        passwordLbl.setVisible(false);
        passFieldPassword.setText("");
        passFieldPassword.setVisible(false);
        authorizeBtn.setText("Выйти");
    }

    private void setAuthorizeEnable() {
        loginLbl.setText("Логин");
        textFieldLogin.setVisible(true);
        passwordLbl.setVisible(true);
        passFieldPassword.setVisible(true);
        authorizeBtn.setText("Войти");
    }
}
