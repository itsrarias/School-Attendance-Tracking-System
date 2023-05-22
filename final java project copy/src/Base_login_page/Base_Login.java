package Base_login_page;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Base_Login implements ActionListener {
    private JLabel Base_Login;
    private JPanel Start;
    private JButton Login_Registration;
    private JButton Login_Teacher;
    private JButton Login_Principal;
    public JPanel create_Base_page()
    {
        return Start;
    }
    public void start_base_page()
    {
        Login_Principal.setActionCommand("p");      // assigning ID to the each button so that we could access them later
        Login_Teacher.setActionCommand("t");
        Login_Registration.setActionCommand("r");
        Login_Principal.addActionListener(new Base_Login());           // creating event, i.e when the button is clicked
        Login_Teacher.addActionListener(new Base_Login());
        Login_Registration.addActionListener(new Base_Login());
    }

    @Override
    public void actionPerformed(ActionEvent e) {        // if click on button
        if (e.getActionCommand() == "p")
        {
            make_login_page("Principal Login", 'p');
            System.out.println("returned here");

            return;
        }
        if(e.getActionCommand() == "r")
        {
            make_login_page("Registration Login", 'r');
            return;
        }
        if(e.getActionCommand() == "t")
        {
            make_login_page("Teachers Login", 't');
            return;
        }

    }
    public void make_login_page (String header, char name)
    {
//        System.out.println("this is " + header);

        Login_Password_GUI.main(header,name );
    }


}
