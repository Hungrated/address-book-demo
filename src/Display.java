import database.*;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Display extends JFrame {

    private JTextField name = new JTextField(12);
    private JTextField phone = new JTextField(12);
    private JTextArea textArea = new JTextArea();

    private Display() {

        this.setFrame();
        this.setVisible(true);
    }

    private void setFrame() {

        // set title
        this.setTitle("简易通讯录");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set bounds
        this.setBounds(100, 100, 500, 450);
        this.setResizable(false);

        // set layout
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        // add panels
        this.addOptionPanel();
        this.addDisplayPanel();

    }

    private void addOptionPanel() {

        JPanel options = new JPanel();
        options.setBorder(new EmptyBorder(0, 5, 30, 5));
        options.setLayout(new FlowLayout());
        this.add(options);


        // options.left

        JPanel insertData = new JPanel();

        insertData.setBounds(0, 0, 300, 200);
        insertData.setLayout(new GridLayout(3, 1));
        JLabel title = new JLabel("                   简易通讯录");
        insertData.add(title);

        JPanel namePanel = new JPanel();
        JPanel phonePanel = new JPanel();
        this.setInput(insertData, namePanel, "姓 名", name);
        this.setInput(insertData, phonePanel, "电 话", phone);


        // options.right

        JPanel buttons = new JPanel();
        buttons.setSize(100, 100);
        options.setBorder(new EmptyBorder(30, 30, 0, 5));
        buttons.setLayout(new GridLayout(3, 1));

        Button insert = new Button("增 加");
        Button modify = new Button("修 改");
        Button query = new Button("查 询");
        buttons.add(query);
        buttons.add(modify);
        buttons.add(insert);

        options.add(insertData);
        options.add(buttons);

        // event listeners

        query.addActionListener(e -> {
            String name = this.name.getText();
            String result = database.Database.query(name.equals("") ? null : name);
            this.name.setText("");
            this.textArea.setText(result);
        });

        modify.addActionListener(e -> {
            String name = this.name.getText();
            String phone = this.phone.getText();
            if (!name.equals("") && !phone.equals("")) {
                try {
                    Database.update(name, phone);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } finally {
                    this.name.setText("");
                    this.phone.setText("");
                    this.textArea.setText("修改数据成功： " + database.Database.query(name));
                }
            }

        });

        insert.addActionListener(e -> {
            String name = this.name.getText();
            String phone = this.phone.getText();
            if (!name.equals("") && !phone.equals("")) {
                try {
                    Database.insert(name, phone);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } finally {
                    this.name.setText("");
                    this.phone.setText("");
                    this.textArea.setText("新增数据成功： " + database.Database.query(name));
                }
            }
        });

    }

    private void addDisplayPanel() {
        //right panel
        JPanel queryDisplay = new JPanel();
        queryDisplay.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(queryDisplay);

        textArea.setColumns(30);
        textArea.setRows(15);
        textArea.setEditable(false);
        queryDisplay.add(textArea);
    }


    private void setInput(JPanel superFrame, JPanel panel, String label, JTextField input) {
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
