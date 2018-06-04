package at.fhv.roomix.webLogin.security.controller;

import at.fhv.roomix.webLogin.model.security.CreditcardPojo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DoReservation")
public class ReservationRestController {
    @RequestMapping(method = RequestMethod.POST)
    public void doReservation() {


    }

    public void saveContact(String sex, String fname, String lname,String eMail,String street,String housenumber,String creditcard) {
        if(checkCreditcardValidation(creditcard)== true){
            CreditcardPojo creditcardPojo = new CreditcardPojo(creditcard,checkCreditcardType(creditcard));
        }
        



    }

    public boolean checkCreditcardValidation(String creditcard){
        int i,d=0,sum=0,card[]=new int[creditcard.length()];

        for(i=creditcard.length()-1;i>=0;i--)
        {
            if(creditcard.charAt(i)==' ')
                continue;
            else
                card[d++]=creditcard.charAt(i)-'0';
        }

        for(i=0;i<d;i++)
        {
            if(i%2!=0)
            {
                card[i]=card[i]*2;
                if(card[i]>9)
                    sum+=digSum(card[i]);
                else
                    sum+=card[i];
            }
            else
                sum+=card[i];
        }
        if(sum%10==0)
            return true;
        else
            return false;
    }

    public String checkCreditcardType(String creditcard){
        int AM=Integer.parseInt(creditcard.substring(0,2));
        int D=Integer.parseInt(creditcard.substring(0,4)),d=0;
        for(int i=creditcard.length()-1;i>=0;i--)
        {
            if(creditcard.charAt(i)==' ')
                continue;
            else
                d++;
        }
        if((AM==34 || AM==37) && d==15)
            System.out.println("AMEX");
        else if(D==6011 && d==16)
            System.out.println("Discover");
        else if(AM>=51 && AM<=55 && d==16)
            System.out.println("MasterCard");
        else if(((creditcard.charAt(0)-'0')==4)&&(d==13 || d==16))
            System.out.println("Visa");
        else
            return "U";
        return "";

    }

    public int digSum(int n)
    {
        int sum=0;
        while(n>0)
        {
            sum+=n%10;
            n/=10;
        }
        return sum;
    }
}
