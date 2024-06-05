module org.carshowroom.carshowroomclientapp
{
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    opens org.carshowroom.carshowroomclientapp to javafx.fxml, org.hibernate.orm.core;
    exports org.carshowroom.carshowroomclientapp;

}