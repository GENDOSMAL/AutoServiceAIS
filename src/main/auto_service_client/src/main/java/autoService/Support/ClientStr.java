package autoService.Support;

import javafx.beans.property.SimpleStringProperty;

public class ClientStr {
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty secondName;
    private final SimpleStringProperty patronymic;
    private final SimpleStringProperty phoneNumber;
    public ClientStr(String firstName,String secondName,String patronymic,String phoneNumber){
        this.firstName = new SimpleStringProperty(firstName);
        this.secondName = new SimpleStringProperty(secondName);
        this.patronymic = new SimpleStringProperty(patronymic);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public SimpleStringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getPatronymic() {
        return patronymic.get();
    }

    public SimpleStringProperty patronymicProperty() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic.set(patronymic);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
}
