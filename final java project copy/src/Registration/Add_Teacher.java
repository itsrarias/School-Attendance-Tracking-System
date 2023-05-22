package Registration;

import com.opencsv.CSVWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Add_Teacher implements ActionListener {
    private JTextField TeachertextField1;
    private JTextField SubjecttextField;
    private JButton Enter;
    private JTable table1;
    private JPanel panel;
    private JLabel error_label;

    public Add_Teacher() {
        TeachertextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    button_or_enter_is_pressed();
                }
            }
        });
        SubjecttextField.addKeyListener(new KeyAdapter() {
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
                {"Example Name", "Computer Science"},
        };
        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"Teacher Name", "Subject"}
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
        Add_Teacher add = new Add_Teacher();
        JFrame frame = new JFrame();
        /////////////////////


        //////////////////
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(add.panel);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        add.Enter.addActionListener(add);
//        try {
//            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/com/IanZonga/homeImage.jpg")))));
//            System.out.println("yes this is added");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        frame.pack();

        add.createTable();


        frame.setVisible(true);

    }

    private void button_or_enter_is_pressed()
    {
        String Teacher_name_field = TeachertextField1.getText();
        String Teacher_subject_field = SubjecttextField.getText();
        String regex = "^[ a-zA-Z]+$";
        if(!(Teacher_name_field.matches(regex)))
        {
            error_label.setText("Invalid Name");
            return;
        }

        TeachertextField1.setText("");
        SubjecttextField.setText("");

        System.out.println("ROW ADDED SUCCESSFULLY");

        // first create file object for file placed at location
        // specified by filepath
        System.out.println("ROW ADDED SUCCESSFULLY");

        // first create file object for file placed at location
        // specified by filepath
        csvObject ParentCl;
        ParentCl = new csvObject();
        String[] data1 = { Teacher_name_field, Teacher_subject_field};

        ParentCl.DoInsertion(data1);


        error_label.setText("Registered Successfully");

        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.addRow(new Object[]{Teacher_name_field, Teacher_subject_field});



    }
    @Override
    public void actionPerformed(ActionEvent e) {

        button_or_enter_is_pressed();
    }
}
