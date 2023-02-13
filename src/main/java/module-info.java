module com.ptf.rs.tiket {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires java.sql.rowset;

    opens com.ptf.rs.tiket to javafx.fxml;
    exports com.ptf.rs.tiket;
    
    opens com.ptf.rs.tiket.controllers to javafx.fxml;
    exports com.ptf.rs.tiket.controllers;
    
    opens com.ptf.rs.tiket.model to javafx.fxml;
    exports com.ptf.rs.tiket.model;
}
