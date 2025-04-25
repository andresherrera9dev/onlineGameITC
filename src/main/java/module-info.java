module com.itc.onlinegameitc {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.typicons;
    requires org.kordamp.ikonli.fontawesome6;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.itc.onlinegameitc to javafx.fxml;
    exports com.itc.onlinegameitc;
}