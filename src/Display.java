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
        this.setResizable(false);

        // set layout
        this.setLayout(new FlowLayout());

        // add panels
        this.addLeftPanel();
        this.addRightPanel();

    }

    private void addLeftPanel() {
        // left panel
        JPanel options = new JPanel();
        options.setBorder(new EmptyBorder(10, 5, 5, 5));
        options.setLayout(new FlowLayout());
        this.add(options);

        this.insert = new Button("新 增");
        this.modify = new Button("修 改");
        this.query = new Button("查 询");
        options.add(insert);
        options.add(modify);
        options.add(query);

        // insert event listener
        insert.addActionListener(e -> {
            this.setInsertDataWindow();
        });

    }

    private void addRightPanel() {
        //right panel
        JPanel queryDisplay = new JPanel();
        queryDisplay.setLayout(new FlowLayout());
        this.add(queryDisplay);

        this.textArea = new JTextArea();
        textArea.setColumns(30);
        textArea.setRows(15);
        textArea.setEditable(false);
        queryDisplay.add(textArea);
    }

    private void setInsertDataWindow() {
        JFrame insertData = new JFrame("添加联系人");

        // set bounds
        insertData.setBounds(150, 150, 300, 220);
        this.setResizable(false);

        // set layout
        insertData.setLayout(new FlowLayout());

        // set input
        JTextField name = new JTextField(20);
        JTextField phone = new JTextField(20);
        JTextField addr = new JTextField(20);

        JPanel namePanel = new JPanel();
        JPanel phonePanel = new JPanel();
        JPanel addrPanel = new JPanel();
        this.setInput(insertData, namePanel, "姓 名", name);
        this.setInput(insertData, phonePanel, "电 话", phone);
        this.setInput(insertData, addrPanel, "地 址", addr);

        // set button
        Button okBtn = new Button("确 定");
        Button cancelBtn = new Button("取 消");

        insertData.add(okBtn);
        insertData.add(cancelBtn);

        insertData.setVisible(true);
    }

    private void setInput(JFrame superFrame, JPanel panel, String label, JTextField input) {
        panel.setLayout(new FlowLayout());
        JLabel key = new JLabel(label);
        panel.add(key);
        panel.add(input);
        superFrame.add(panel);
    }


    public static void main(String args[]) {
        new Display();
    }
}
