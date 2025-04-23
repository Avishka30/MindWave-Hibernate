module lk.ijse.mindwave {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires lombok;
    requires com.jfoenix;
//    requires net.sf.jasperreports.core;


    requires org.hibernate.orm.core;
    requires java.naming;
    requires jakarta.persistence;
    requires jbcrypt;
    requires java.desktop;

    opens lk.ijse.mindwave.config to jakarta.persistence;
    opens lk.ijse.mindwave.view.tdm to javafx.base;
    opens lk.ijse.mindwave.entity to org.hibernate.orm.core;
    opens lk.ijse.mindwave.controller to javafx.fxml;
//    opens lk.ijse.mindwave.dto to javafx.base;

    exports lk.ijse.mindwave;
}