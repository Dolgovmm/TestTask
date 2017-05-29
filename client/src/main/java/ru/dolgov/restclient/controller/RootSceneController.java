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
 * @author M.Dolgov
 * @date 28.05.2017
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
        if (contact != null) {
            String result = "";
            try {
                result = client.addContact(contact);
            } catch (IOException e) {
                result = "IO ошибка при добавлении контакта";
            }
            textFieldResult.setText(result);
        }
    }

    public void onGetByNameBtnClick() {
        List<Contact> list = null;
        String result = "";
        if (!textFieldNameToSearch.getText().isEmpty()) {
            try {
                list = client.getContactByName(textFieldNameToSearch.getText());
                if (list != null && list.size() > 0) {
                    setLabelTextFromContactList(list);
                } else {
                    result = "нет контактов с таким именеи";
                }
            } catch (IOException e) {
                result = "IO ошибка при поиске контакта по имени";
            }
            if (list == null) {
                result = "поиск по имени завершился с ошибкой";
            } else {
                if (list != null & list.size() > 0) {
                    result = "поиск контакта по имени выполнен";
                }
            }
            textFieldResult.setText(result);
        } else {
            textFieldResult.setText("введите имя для поиска");
        }
    }

    public void onUpdateBtnClick() {
        Contact contact = createContact();
        if (contact != null) {
            String result = "";
            try {
                result = client.updateContact(contact);
            } catch (IOException e) {
                result = "IO ошибка изменения контакта";
            }
            textFieldResult.setText(result);
        }
    }

    public void onDeleteBtnClick() {
        Contact contact = createContact();
        if (contact != null) {
            String result = "";
            try {
                result = client.deleteContact(contact);
            } catch (IOException e) {
                result = "IO ошибка удаления контакта";
            }
            textFieldResult.setText(result);
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
                textFieldResult.setText("в поле ID нужно вводить только цифры");
                return null;
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
