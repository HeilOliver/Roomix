package at.fhv.roomix.domain.guest.model;

import java.sql.Timestamp;

public class InvoiceDomain {
    private int invoiceId;
    private int contact;
    private Timestamp determinationDate;
    private String status;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public Timestamp getDeterminationDate() {
        return determinationDate;
    }

    public void setDeterminationDate(Timestamp determinationDate) {
        this.determinationDate = determinationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
