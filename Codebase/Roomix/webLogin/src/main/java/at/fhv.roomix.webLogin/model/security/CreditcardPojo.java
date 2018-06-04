package at.fhv.roomix.webLogin.model.security;

public class CreditcardPojo {
    private String creditcardNumber;
    private String institute;

    public CreditcardPojo(String creditcardNumber,String institute){
        this.creditcardNumber=creditcardNumber;
        this.institute=institute;
    }

    public String getCreditcardNumber() {
        return creditcardNumber;
    }

    public void setCreditcardNumber(String creditcardNumber) {
        this.creditcardNumber = creditcardNumber;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }
}
