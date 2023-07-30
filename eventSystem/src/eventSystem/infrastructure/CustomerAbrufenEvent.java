package eventSystem.infrastructure;

import verwaltung.LagerFassade;

import java.util.EventObject;

public class CustomerAbrufenEvent extends EventObject {
    LagerFassade lagerFassade;
    String[] customerCargo;

    public String[] getCustomerCargo() {
        return customerCargo;
    }

    public CustomerAbrufenEvent(Object source, String[] customerCargo) {
        super(source);
        this.customerCargo = customerCargo;
    }
}
