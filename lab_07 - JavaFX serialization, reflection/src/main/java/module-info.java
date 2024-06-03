module org.carshowroom.carshowroomclientapp
{
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens org.carshowroom.carshowroomclientapp to javafx.fxml;
    exports org.carshowroom.carshowroomclientapp;
}