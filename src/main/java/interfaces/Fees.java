package interfaces;

import java.util.Date;

public interface Fees {

    void deductFees(double amount);
    void displayTotalMonthlyFees(Date startDate, Date endDate);
}
