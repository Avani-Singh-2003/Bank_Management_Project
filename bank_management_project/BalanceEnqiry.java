package bank_management_project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

public class BalanceEnqiry extends JFrame implements ActionListener {
    JButton back;
    String pinnumber;

    BalanceEnqiry(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("iCONS/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(850, 690, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 850, 690);
        add(image);

        back = new JButton("Back");
        back.setBounds(337, 396, 150, 22);
        back.addActionListener(this);
        image.add(back);

        conn c = new conn();
        int balance = 0;
        try {
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");

            while (rs.next()) {
                if (rs.getString("Type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        JLabel text = new JLabel("Your Current Account balance is Rs " + balance);
        text.setForeground(Color.WHITE);
        text.setBounds(200, 230, 400, 30);
        image.add(text);

        setSize(850, 690);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new transaction(pinnumber).setVisible(true);

    }

    public static void main(String args[]) {
        new BalanceEnqiry("");
    }

}
