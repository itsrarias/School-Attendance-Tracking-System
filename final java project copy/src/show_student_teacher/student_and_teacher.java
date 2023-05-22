package show_student_teacher;
// this file also contain the attendence of the students
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.plaf.IconUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class student_and_teacher implements ItemListener, ActionListener {
    private JLabel head_text;
    private JTable table1;
    private JComboBox comboBox1;
    private JLabel class_label;
    private JPanel panel;
    private JLabel TotalStudent_label;
    private JLabel msg;
    private JLabel msg2;
    private JFrame frame;
    public boolean take_attendence;
    public JButton submit;
    private JButton ABSENT;
    public String[] s = new String[13];
    private int current_class_is;
    private int[] absent_mark = new int[30];
    private int absent_mark_index;
    public void set_student_classes()
    {

       for(int i = 0; i < 13; i++)
       {
           if(i == 0)
           {
               s[i] = "All Students";
           }
           else
           {

               s[i] = Integer.toString(i);
           }
       };
    }
    public void create_table(String Name, String class_or_subject, String Attendence)
    {
//        System.out.println("this is attendence " + this.take_attendence);
        if(take_attendence)
        {
            String[] column = {Name, class_or_subject, Attendence};
            Object[][] data = null;
            table1.setModel(new DefaultTableModel(data, column));
            table1.setShowGrid(true);
            return;
        }
        String[] column = {Name, class_or_subject};
        Object[][] data = null;
        table1.setModel(new DefaultTableModel(data, column));
       table1.setShowGrid(true);
    }
    // using method overloading for default parameter
    public void create_table(String Name, String class_or_subject)
    {
        create_table(Name, class_or_subject, "");
    }
    public void set_frame(String heading, boolean show_student)
    {
        frame = new JFrame();
        panel.setLayout(new FlowLayout());
        head_text.setText(heading);
        frame.setVisible(true);
     //   frame.setLocationRelativeTo(null);
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setContentPane(panel);
        if(show_student)
        {
            class_label.setText("Class");
            for(int i = 0 ; i < 13; i++)
            {
                comboBox1.addItem(s[i]);
                if(i != 0)
                {
                    comboBox1.getItemAt(i); // for choosing classes with combobox, adding rows

                }

            }
         comboBox1.addItemListener(this);        // when any  class is selected

        }
        else
        {
            class_label.setText("");
        }

    }


    public void main_of_student_and_teacher ( boolean show_student, boolean show_teacher, JFrame temp_frame, boolean take_attendence)
    {
        msg.setVisible(false);
        msg2.setVisible(false);

        absent_mark_index = 0;
        for(int i = 0; i < 30; i++)
        {
            absent_mark[i] = -1;
        }

        this.take_attendence = take_attendence;
        temp_frame.dispose();
        set_student_classes();          // adding combobox of classes
        if(show_student)
        {
            set_frame("Students List", true);         // adding heading of frame
            if(take_attendence)                  // if comes into the take attendence window
            {
                submit = new JButton("Submit");            // will have submit button
                ABSENT = new JButton("Absent");            // will have absent button to mark him/she absent
                ABSENT.setBounds(60,100,95,30);

                submit.setBounds(50,100,95,30);
                frame.add(ABSENT);

                frame.add(submit);
               submit.setBackground(Color.red);
               ABSENT.setBackground(Color.green);

                submit.setVisible(false);
                msg.setVisible(false);
                msg2.setVisible(false);
                submit.addActionListener(this);
                ABSENT.setVisible(false);

                ABSENT.addActionListener(this);

            }
            show_student_of_class(0, show_student);
        }
        else if(show_teacher)
        {
            // 22 is dummy value in this case
            show_student_of_class(22, false);
            set_frame("Teachers List", false);
        }


    }
    // method overloading for default argument
    public void main_of_student_and_teacher( boolean show_student, boolean show_teacher, JFrame temp_frame)
    {
        main_of_student_and_teacher(show_student, show_teacher, temp_frame, false);

    }
    private void show_student_of_class(int number, boolean is_show_student)
    {
        String position = Integer.toString(number);
        List<String> get_row = new ArrayList<String>();


        if(is_show_student)
        {
            if(number == 0)
            {
                try{
                    BufferedReader br = new BufferedReader(new FileReader("src/com/proj/student_list.csv"));
                    String line;
                    while ((line = br.readLine()) != null) {
                        // use comma as separator
                        String[] cols = line.split(",");
                        get_row.add(cols[0]);                   // name, i.e ist column in csv file
                        get_row.add(cols[1]);                           // 2nd column in csv file which is class

                    }

                    } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            else
            {
                try{
                    BufferedReader br = new BufferedReader(new FileReader("src/com/proj/student_list.csv"));
                    String line;

                    while ((line = br.readLine()) != null) {
                        // use comma as separator
                        String[] cols = line.split(",");
                        String l= cols[1].toString() ;      // converting class value to integer
                         l = l.replaceAll("^\"+|\"+$", "");    // concerting json format string to normal one
                         int clAss = Integer.parseInt(l);
                        if (number == clAss )
                        {
                            get_row.add(cols[0]);                   // name, i.e ist column in csv file
                            get_row.add(cols[1]);                           // 2nd column in csv file which is class

                        }

                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            try{
                BufferedReader br = new BufferedReader(new FileReader("src/com/proj/teacher_list.csv"));
                String line;
                while ((line = br.readLine()) != null) {
                    // use comma as separator
                    String[] cols = line.split(",");
                    get_row.add(cols[0]);                   // name, i.e ist column in csv file
                    get_row.add(cols[1]);                           // 2nd column in csv file

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


            String NAME;
            String CLASS_OR_subject;
            if(is_show_student && take_attendence)
            {
                create_table( "Name ","Class", "Attendance");

            }
            else if(is_show_student)
            {
                create_table("Name ","Class");

            }
            else{
                create_table( "Name ","Subject");            // creating heading of table

            }
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            int Total_students_or_teachers = 0;
            if(is_show_student)
            {
                int i = 0;
                Integer totalLength = get_row.size();
                while(i < totalLength)
                {

                    NAME = get_row.get(i);          // name present at ist value and class present at 2nd value
                    i += 1;
                    CLASS_OR_subject = get_row.get(i);

                    i+= 1;
                    model.addRow(new Object[]{NAME, CLASS_OR_subject, "-"});
                    Total_students_or_teachers += 1;
                }
                TotalStudent_label.setText("Total Students are " + Total_students_or_teachers);

            }
            else
            {

                int i = 0;
                Integer total_length = get_row.size();
                while(i < total_length)
                {
                    NAME = get_row.get(i);          // name present at ist value and class present at 2nd value
                    i += 1;
                    CLASS_OR_subject = get_row.get(i);
                    i+= 1;
                    model.addRow(new Object[]{NAME, CLASS_OR_subject, "-"});
                    Total_students_or_teachers += 1;
                }
                 comboBox1.setVisible(false);

                TotalStudent_label.setText("Total Teachers are " + Total_students_or_teachers);

            }

            table1.setRowHeight(22);
            TableColumnModel columnModel = table1.getColumnModel();
            columnModel.getColumn(1).setPreferredWidth(120);
            columnModel.getColumn(0).setPreferredWidth(10);

        }

    @Override
    public void itemStateChanged(ItemEvent e) {
//        System.out.println("ITEM LISTENER PRESSED");

        for(int i = 0 ; i < 13; i++)
        {
            if(e.getItem() == s[i])
            {
                current_class_is = i;
                if(take_attendence && i!=0)
                {
                    submit.setVisible(true);
                    msg.setVisible(true);
                    msg2.setVisible(true);
                    submit.setActionCommand("S");

                    ABSENT.setVisible(true);
                    ABSENT.setActionCommand("A");
                }

                show_student_of_class(i, true);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "S")     // if submit button is clicked
        {

            System.out.println("yes submit button is pressed");
            Object[] rowData = new Object[table1.getColumnCount()];
            int column = table1.getColumnCount();

            int rows = table1.getRowCount();
            int row_index = 0;
            int total_files_of_attendence = 0;

            String saving_path = "src/Student_Attendence_Info/" + current_class_is+ "/";       // saving attendance of students to this path

            try {
                String file_path = "src/Student_Attendence_Info/" + current_class_is;
                File directory = new File(file_path);
                total_files_of_attendence = directory.list().length;
                total_files_of_attendence += 1;
                saving_path = saving_path + total_files_of_attendence + ".txt";
                FileWriter fout = new FileWriter(saving_path);
                fout.write("ID\tName\tClass\tAttendence\n");
                String student_info;
                while(row_index != rows)
                {
                    student_info = "";
                    for(int i = 0; i < column; i++)
                    {
                        rowData[i] = table1.getValueAt(row_index,i);

                    }
                    for(int i= 0; i < 3; i++)
                    {
                        student_info = student_info + rowData[i] + "\t";


                    }
                    fout.write(student_info+"\n");
                    System.out.println();
                    row_index += 1;
                }
                fout.close();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            frame.dispose();
        }
        else{   // if absent button is pressed then change the selected row to the absent

            int i = table1.getSelectedRow();    // taking the selected row
            System.out.println("selected row is " + i);

            for(int k = 0; k <= absent_mark_index; k++)
            {
                if(absent_mark[k] == i)     // if already changed this cell
                {
                    System.out.println("Marking present agAIN");
                    table1.setValueAt("-", i, 3);
                    absent_mark[k] = -1; // because now it is present
                    break;
                }
                else{
                    System.out.println("Marking it absent");
                    table1.setValueAt("A", i, 2);   // this line simply change the value of absent in a row
                    break;
                }
            }

            absent_mark[absent_mark_index] = i;
            absent_mark_index += 1;
        }

    }
}
