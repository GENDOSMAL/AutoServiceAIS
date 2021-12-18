module com.genderfrender.autoserviceclient {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
        
    opens com.genderfrender.autoserviceclient to javafx.fxml;
    exports com.genderfrender.autoserviceclient;
}