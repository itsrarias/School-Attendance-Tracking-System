package com.proj;

import Base_login_page.Base_Login;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // creating static functions so that we can call it directly without the need of object from anywhere

        SwingUtilities.invokeLater(new Runnable() {             // backend thread
            @Override
            public void run() { // will start the GUI
                startPageGUI();

            }
        });
    }
    private static void startPageGUI()
    {
        Base_Login ui = new Base_Login();
        JPanel get_base_page = ui.create_Base_page();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(get_base_page);
        frame.pack();
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ui.start_base_page();

        /////////////////


    }

}
