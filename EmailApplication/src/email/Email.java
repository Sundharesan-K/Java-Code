package email;

import java.util.Scanner;

public class Email {
    private String firstName;
    private String lastName;
    private String password;
    private String department;
    private int mailboxCapacity=500; ;
    private String email;
    private int defaultPasswordLength=3;
    private String alternateEmail;
    private String companyName="trustrace.com";

    public Email(String firstName,String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = setDepartment ();

        this.password=randomPassword (defaultPasswordLength);
        System.out.println ("Your Password :"+password);
        email=firstName.toLowerCase ()+""+lastName.toLowerCase ()+"@"+department+"."+companyName;

    }

    private String setDepartment(){
        System.out.println ("DEPARTMENT CODES :\n1 for a Sales\n2 for a Development\n3 for a Accounting\n0 for none\nEnter the Department Code :");
        Scanner scanner=new Scanner (System.in);
        int depChoice=scanner.nextInt ();
        if (depChoice==1){
            return "Sales";
        }else if (depChoice==2){
            return "Dev";
        }else if (depChoice==3){
            return "Acc";
        }else return "";
    }

    private String randomPassword(int length){
        String PasswordSet="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$^&*";
        char[] password=new char[length];
        for (int i=0;i<length;i++){
        int rand  = (int) (Math.random ()*PasswordSet.length());
        }
        return new String (password);
    }
    public String showInfo(){
        return "DISPLAY NAME: "+ firstName +" " + lastName + "\nCOMPANY NAME: " + email + "\nMAILBOX CAPACITY: " + mailboxCapacity + "mb";
    }

}
