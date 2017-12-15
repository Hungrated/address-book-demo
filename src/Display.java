import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class Display extends JFrame {
    private Button query, modify, insert;
    private JTextField name;
    private JTextField phone;
    private JTextArea textArea;
    private Statement stmt;
    private Connection conn;
    private ResultSet resultSet;

    private Display() {
        this.setFrame();
        this.setVisible(true);
    }

    private void setFrame() {

        // set title
        this.setTitle("简易通讯录");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set bounds
        this.setBounds(100, 100, 500, 600);

        // set layout
        this.setLayout(new FlowLayout());

        // add panels
        this.addLeftPanel();
        this.addRightPanel();

    }

    private void addLeftPanel() {
        // left panel
        JPanel options = new JPanel();
        options.setBorder(new EmptyBorder(5, 5, 5, 5));
        options.setLayout(new FlowLayout());
        this.add(options);

        this.insert = new Button("新 增");
        this.modify = new Button("修 改");
        this.query = new Button("查 询");
        options.add(insert);
        options.add(modify);
        options.add(query);
    }

    private void addRightPanel() {
        //right panel
        JPanel queryDisplay = new JPanel();
        queryDisplay.setBorder(new EmptyBorder(5, 5, 5, 5));
        queryDisplay.setLayout(new FlowLayout());
        this.add(queryDisplay);

        this.textArea = new JTextArea();
        textArea.setColumns(30);
        textArea.setRows(15);
        textArea.setEditable(false);
        queryDisplay.add(textArea);

    }


    public static void main(String args[]) {
        new Display();
    }
}
