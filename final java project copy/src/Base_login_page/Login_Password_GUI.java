package Base_login_page;

import Principal.Principal_Gui;
import Registration.Registration_Gui;
import Teacher.Teacher_Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login_Password_GUI implements ActionListener {
    public JFrame main_frame;
    public   JLabel head_label;
    private  JPanel child_panel;
    public   JPanel root_panel;
    private  JTextField User_textField;
    private   JLabel User_label;
    public  JPasswordField passwordField1;
    private  JLabel Password;
    public  JButton loginButton;
    private JLabel error_label;
    private String my_password;
    private String my_user_name;
    public char caller_name;
    private void login_key_or_enter_pressed()
    {
        main_frame.dispose();
        error_label.setText("");
//        System.out.println("yes i am called on button");
        System.out.println(passwordField1.getPassword());
        char[] get_password = passwordField1.getPassword();
        String set_password = "";
        String set_user = User_textField.getText();
        for(int i = 0; i < get_password.length; i++)
        {
            set_password += get_password[i];
        }
        if(set_password.equals(get_my_password()) && set_user.equals(get_my_user_name()) )
        {
        if(caller_name == 'r') {
//            System.out.println("calling to REGISTRATION GUI");
            Registration_Gui.main();               // p means Principal
        }
        if(caller_name == 'p')
        {
//            System.out.println("calling to PriNCIPAL GUI");

            Principal_Gui.main();
        }
        if(caller_name == 't')
        {
//            System.out.println("calling to TEACHER GUI");

//            System.out.println("calling to Teacher GUI");
            Teacher_Gui.main();
        }
        System.out.println("valid password");
    }
        else{
            System.out.println("else not executed");
        error_label.setText("Invalid Password");
            JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(null), "Please Enter Valid Password",
                    "Password Error", JOptionPane.ERROR_MESSAGE);

        }

}
    public Login_Password_GUI() {
        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)             // adding enter key listener to text field of password
                {
                    login_key_or_enter_pressed();
                }
            }
        });
        User_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {          //adding enter key listener to text field of user
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    login_key_or_enter_pressed();
                }
            }
        });
    }

    public void set_login_password(String my_password)
    {
        this.my_password = my_password;
    }
    public String get_my_password()
    {
        return my_password;
    }
    public void set_login_user_name(String my_user_name)
    {
        this.my_user_name = my_user_name;
    }
    public String get_my_user_name()
    {
        return my_user_name;
    }
    @Override

    public void actionPerformed(ActionEvent e)
    {        // if click on button
        login_key_or_enter_pressed();
    }
    public static void main(String header, char name)
    {
        Login_Password_GUI LOGIN = new Login_Password_GUI();
        LOGIN.set_login_password("admin");      // this is the password for the principal
        LOGIN.set_login_user_name("admin");  // this is the user for the principal
        LOGIN.head_label.setText(header);
        JFrame frame = new JFrame();
        frame.setSize(800, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        LOGIN.main_frame = frame;
        LOGIN.User_label.setText(header);
        frame.setContentPane(LOGIN.root_panel);    // to add the panel to the frame
        frame.setVisible(true);
        LOGIN.loginButton.addActionListener(LOGIN);
        LOGIN.caller_name = name;
    }

}