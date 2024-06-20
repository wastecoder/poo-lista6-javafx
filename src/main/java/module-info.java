module org.example.poolista6javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.poolista6javafx to javafx.fxml;
    exports org.example.poolista6javafx;
}