package Controllers;

import ConnectionLayer.ConexiuneProlog;

public class BaseController {
    //@FXML private Label customerName;
    ConexiuneProlog conexiune;

    public void initialize() {}
    public void prepareWithConnection(ConexiuneProlog conexiune) {
        //customerName.setText(customer.getName());
        this.conexiune = conexiune;
    }
}
