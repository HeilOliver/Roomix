package at.fhv.roomix.webLogin;

import javax.persistence.*;

/**
 * Building Attributes from the Sites into Java variables!
 * These Attributes and the Table itself are not existing, so if we start the application, the H2 Database draws automaticly the missing table!
 */
@Entity
@Table(name = "Formular")
public class LoginAttributes {
    private int id;
    private String pw;
    private String userid;

    @Id
    @Column(name = "userID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUserid() {
        return userid;
    }
    public void setUserid(String entry) {
        this.userid = entry;
    }

    @Basic
    @Column(name = "password")
    public String getPw() {
        return pw;
    }
    public void setPw(String entry) {
        this.pw = entry;
    }
}
