package Principal;
import show_student_teacher.student_and_teacher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal_Gui implements ActionListener {
    private JButton shortAttendenceStudentsButton;
    private JButton allStudentsListButton;
    private JButton allTeacherListButton;
    private JButton a100AttendenceOfStudentsButton;
    private JButton classTeachersButton;
    private JPanel root_panel;
    public JFrame frame;

    public void setActionCommand()
    {
        shortAttendenceStudentsButton.setActionCommand("1");
        shortAttendenceStudentsButton.setVisible(false);
        allStudentsListButton.setActionCommand("2");
        allTeacherListButton.setActionCommand("3");
        a100AttendenceOfStudentsButton.setActionCommand("4");
        a100AttendenceOfStudentsButton.setVisible(false);
        classTeachersButton.setActionCommand("5");

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand() == "2")
        {
            student_and_teacher show = new student_and_teacher();
            show.main_of_student_and_teacher(true, false, frame); // this function will show student or teacher list, with argument true or false for student and teacher
            frame.dispose();
//            System.out.println("2nd button is clicke");
            return;
        }
        if(e.getActionCommand() == "3")
        {
            student_and_teacher show = new student_and_teacher();
            show.main_of_student_and_teacher(false, true, frame);
            frame.dispose();

//            System.out.println("3rd button is clicke");
            return;
        }

    }
    public static void main()
    {
        Principal_Gui LOGIN = new Principal_Gui();
        LOGIN.frame = new JFrame();
        LOGIN.frame.setSize(800, 300);
        LOGIN.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        LOGIN.frame.setContentPane(LOGIN.root_panel);    // to add the panel to the frame
        LOGIN.frame.setVisible(true);
        LOGIN.shortAttendenceStudentsButton.addActionListener(LOGIN);
        LOGIN.allStudentsListButton.addActionListener(LOGIN);
        LOGIN.allTeacherListButton.addActionListener(LOGIN);
        LOGIN.a100AttendenceOfStudentsButton.addActionListener(LOGIN);
        LOGIN.classTeachersButton.addActionListener(LOGIN);
        LOGIN.setActionCommand();       //to name the buttons
    }
}
