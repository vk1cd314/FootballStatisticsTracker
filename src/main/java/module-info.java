module com.football.statisticstracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires fontawesomefx;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires sqlite.jdbc;

    exports com.football.statisticstracker;
    opens com.football.statisticstracker;
    exports dashboard;
    opens dashboard;
    exports teams;
    opens teams;
    exports leagues;
    opens leagues;
    exports players;
    opens players;
    exports signup;
    opens signup;
}