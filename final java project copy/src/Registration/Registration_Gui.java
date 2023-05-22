package Registration;


import show_student_teacher.student_and_teacher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Registration_Gui implements ActionListener {
    public  JPanel panel;
    public JButton Register_student;
    public  JButton allStudentsListButton;
    public JButton allTeachersListButton;
    public  JButton registerTeacherButton;

    public JLabel head_label;
    public JFrame frame;
    public void setActionCommand()
    {
        Register_student.setActionCommand("1");         // register student
        allStudentsListButton.setActionCommand("2");    // all student list
        allTeachersListButton.setActionCommand("3");    // all teacher list
        registerTeacherButton.setActionCommand("4");    // register teacher

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand() == "1")     // button register student has been clicked
        {
            Add_Student.main(frame);
            System.out.println("ist button is clicked");
            frame.dispose();
            return;
        }
        if(e.getActionCommand() == "2")
        {
            student_and_teacher show = new student_and_teacher();
            show.main_of_student_and_teacher(true, false, frame);
            frame.dispose();
            return;
        }
        if(e.getActionCommand() == "3")
        {
            student_and_teacher show = new student_and_teacher();
            show.main_of_student_and_teacher(false, true, frame);
            frame.dispose();
            return;
        }
        if(e.getActionCommand() == "4")
        {
            Add_Teacher.main(frame);


            frame.dispose();
            return;
        }
    }
    public static void main()
    {
        String header = "Registration Staff";
        Registration_Gui LOGIN = new Registration_Gui();
        LOGIN.head_label.setText(header);
        LOGIN.frame = new JFrame();
        LOGIN.frame.setSize(800, 300);
        LOGIN.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        LOGIN.frame.setContentPane(LOGIN.panel);    // to add the panel to the frame
        LOGIN.frame.setVisible(true);
        LOGIN.Register_student.addActionListener(LOGIN);
        LOGIN.allStudentsListButton.addActionListener(LOGIN);
        LOGIN.allTeachersListButton.addActionListener(LOGIN);
        LOGIN.registerTeacherButton.addActionListener(LOGIN);
        LOGIN.setActionCommand();
    }
}
