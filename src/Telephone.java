public class Telephone {
    private String TelephoneModel;
    private String SerialNumber;
    private String TelephoneColor;
    private boolean isMobilePhone;

    public Telephone(String TelephoneModel, String SerialNumber, String TelephoneColor, boolean isMobilePhone) {
        this.TelephoneModel = TelephoneModel;
        this.SerialNumber = SerialNumber;
        this.TelephoneColor = TelephoneColor;
        this.isMobilePhone = isMobilePhone;
    }

    public boolean isMobilePhone() {
        return isMobilePhone;
    }

    public String getTelephoneColor() {
        return TelephoneColor;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public String getTelephoneModel() {
        return TelephoneModel;
    }
}
