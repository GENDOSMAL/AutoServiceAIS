package autoService.utils;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ServiceStr {
    private final SimpleStringProperty category;
    private final SimpleIntegerProperty duration;
    private final SimpleStringProperty type;

    public ServiceStr(String type, int duration, String category){
        this.type = new SimpleStringProperty(type);
        this.duration = new SimpleIntegerProperty(duration);
        this.category= new SimpleStringProperty(category);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }


    public SimpleIntegerProperty durationProperty() {
        return duration;
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }
}
