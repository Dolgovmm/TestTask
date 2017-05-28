package ru.dolgov.restclient.controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.dolgov.restclient.client.Client;
import ru.dolgov.restclient.client.User;
import ru.dolgov.restclient.entity.Contact;

import java.io.IOException;
import java.util.List;

/**
 * Created by Михалыч on 28.05.2017.
 */
public class RootSceneController {
    public TextField textFieldResult;
    public TextField textFieldLogin;
    public PasswordField passFieldPassword;
    public Label loginLbl;
    public Label passwordLbl;
    public Button authorizeBtn;
    public TextField textFieldID;
    public TextField textFieldFirstName;
    public TextField textFieldLastName;
    public TextField textFieldEmail;
    public TextField textFieldTelephone;
    public TextField textFieldNameToSearch;
    public Label getContactByNameIdLbl;
    public Label getContactByNameFirstNameLbl;
    public Label getContactByNameLastNameLbl;
    public Label getContactByNameEmailLbl;
    public Label getContactByNameTelephoneLbl;

    private Client client;

    public RootSceneController() {
        client = new Client();
    }



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

    public void onAddBtnClick() {
        Contact contact = createContact();
        String result = "";
        try {
            result = client.addContact(contact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textFieldResult.setText(result);
    }

    public void onGetByNameBtnClick() {
        List<Contact> list = null;

        try {
            list = client.getContactByName(textFieldNameToSearch.getText());
            setLabelTextFromContactList(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (list != null & list.size() > 0) {
            textFieldResult.setText("get contact by name successfully");
        }
        if (list != null & list.size() == 0) {
            textFieldResult.setText("no contact with this name");
        }
        if (list == null) {
            textFieldResult.setText("get by name end with error");
        }
    }

    private void setLabelTextFromContactList(List<Contact> list) {
        getContactByNameIdLbl.setText(list.get(0).getId().toString());
        getContactByNameFirstNameLbl.setText(list.get(0).getFirstName());
        getContactByNameLastNameLbl.setText(list.get(0).getLastName());
        getContactByNameEmailLbl.setText(list.get(0).getEmail());
        getContactByNameTelephoneLbl.setText(list.get(0).getTelephone());
    }

    private Contact createContact() throws NumberFormatException{
        Contact contact = new Contact();
        if (!textFieldID.getText().isEmpty()) {
            try {
                int id = Integer.parseInt(textFieldID.getText());
                contact.setId(id);
            } catch (NumberFormatException e) {
                System.out.println("error parse id");
                contact.setId(-1);
            }
        }
        contact.setFirstName(textFieldFirstName.getText());
        contact.setLastName(textFieldLastName.getText());
        contact.setEmail(textFieldEmail.getText());
        contact.setTelephone(textFieldTelephone.getText());
        return contact;
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
