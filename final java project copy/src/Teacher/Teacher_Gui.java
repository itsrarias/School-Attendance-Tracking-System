package Teacher;

//import Registration.Registration_Gui;
import show_student_teacher.student_and_teacher;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Teacher_Gui implements ActionListener {
    private JButton button1;
    private JButton button2;
    private JPanel panel;
    private JLabel label;
    public JFrame frame;
    public void setActionCommand()
    {
        button1.setText("Take Attendence");
        button1.setActionCommand("1");
        button2.setActionCommand("2");
        button2.setVisible(false);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        student_and_teacher call = new student_and_teacher();

        if(e.getActionCommand() == "1")     // button register student has been clicked
        {
            if (button1.getActionCommand() == "1")
            {

                call.main_of_student_and_teacher(true, false, frame, true);
                frame.dispose();
                return;
            }
            System.out.println("ist button is clicked");
            return;
        }
        if(e.getActionCommand() == "2")
        {
            return;
        }

    }
    public static void main()
    {
        Teacher_Gui LOGIN = new Teacher_Gui();
        LOGIN.frame = new JFrame();
        LOGIN.frame.setSize(800, 300);
        LOGIN.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        LOGIN.frame.setContentPane(LOGIN.panel);    // to add the panel to the frame
        LOGIN.frame.setVisible(true);
        LOGIN.button1.addActionListener(LOGIN);
        LOGIN.button2.addActionListener(LOGIN);
        LOGIN.setActionCommand();
    }
}
