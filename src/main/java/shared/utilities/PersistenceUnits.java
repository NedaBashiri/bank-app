package shared.utilities;

public enum PersistenceUnits {
    UNIT_ONE("BankSystem");
    private final String unitName;

    PersistenceUnits(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return this.unitName;
    }
}
