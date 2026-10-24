module br.com.joaovjsilva {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;



    opens br.com.joaovjsilva to javafx.fxml;
    exports br.com.joaovjsilva;
    exports br.com.joaovjsilva.model;

}
