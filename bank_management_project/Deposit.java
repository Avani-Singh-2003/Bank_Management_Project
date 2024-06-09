package bank_management_project;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;

public class Deposit extends JFrame implements ActionListener {
    JTextField amount;
    JButton deposit, back;
    String pinnumber;

    Deposit(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("iCONS/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(850, 690, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 850, 690);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to deposit");
        text.setBounds(175, 240, 690, 20);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        amount = new JTextField();
        amount.setBounds(175, 270, 290, 25);
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        image.add(amount);

        deposit = new JButton("Deposit");
        deposit.setBounds(337, 368, 150, 22);
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("Back");
        back.setBounds(337, 396, 150, 22);
        back.addActionListener(this);
        image.add(back);

        setSize(850, 690);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit) {
            String number = amount.getText();
            Date date = new Date();
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit");
            } else {
                try {
                    conn conn = new conn();
                    String query = "insert into bank values('" + pinnumber + "','" + date + "','Deposit', '" + number
                            + "')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs " + number + " Depsoited Successfully");
                    setVisible(false);
                    new transaction(pinnumber).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
            new transaction(pinnumber).setVisible(true);
        }
    }

    public static void main(String args[]) {
        new Deposit("");
    }

}
