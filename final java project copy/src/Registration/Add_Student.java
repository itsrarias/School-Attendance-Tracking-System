package Registration;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Add_Student implements ActionListener {
    private JPanel panel1;
    private JTextField textClass;
    private JTextField Name;
    private JButton enterButton;
    private JTable table1;
    private JLabel error_label;
    public DefaultTableModel model;
    public JComponent component;

    public Add_Student() {
        Name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)           // adding key event to detect if enter key is pressed
                {
                    button_or_enter_is_pressed();
                }
            }
        });
        textClass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    button_or_enter_is_pressed();
                }
            }
        });
    }

    private void createTable(){

        Object[][] data = {
                {"Example Name", "0"},
        };
        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"Student Name", "Class"}
        ));

        TableColumnModel columns = table1.getColumnModel();
     //   columns.getColumn( 1).setMaxWidth(200); // setting column of section
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < 2; i++)
        {
            columns.getColumn(i).setCellRenderer(centerRenderer);   // setting every column to center
        }
    }
    public static void main(JFrame tmp_frame)
    {
        tmp_frame.dispose();
        Add_Student add = new Add_Student();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(add.panel1);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        add.enterButton.addActionListener(add);
        add.createTable();
        frame.setVisible(true);

    }
    private void button_or_enter_is_pressed()
    {
        String Student_name_field = Name.getText();
        String Student_class_field = textClass.getText();
        String regex = "^[ a-zA-Z]+$";       // for validating string
        String regex_of_class = "1[012]|[1-9]";
        int len_of_class_field = Student_class_field.length();
        boolean execute_else = false;
        if(len_of_class_field == 0 || Student_name_field.length() == 0)
        {
            error_label.setText("Please fill all fields");
        }
        else if (!(Student_name_field.matches(regex))) {
            error_label.setText("Student Name is Invalid");
        }
        else if(Student_name_field.length() <= 2)
        {
            error_label.setText("Name is too short");
        }
        else if(len_of_class_field > 2)
        {
            textClass.setText("");
            error_label.setText("Invalid Class. Only 1 to 12 classes are Present");
        }
        else if(!(Student_class_field.matches(regex_of_class)))
        {
            error_label.setText("Invalid Class. Only 1 to 12 classes are Present");

        }
        else
        {
//            System.out.println("ROW ADDED SUCCESSFULLY");

            // first create file object for file placed at location
            // specified by filepath
            csvObject ParentCl;
            ParentCl = new studentList();
            String[] data1 = { Student_name_field, Student_class_field};

            ParentCl.DoInsertion(data1);
            error_label.setText("Student Registered Successfully");
            textClass.setText("");
            Name.setText("");
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.addRow(new Object[]{Student_name_field, Student_class_field});

        }

    }
        @Override
    public void actionPerformed(ActionEvent e)  {

        button_or_enter_is_pressed();

    }

}
