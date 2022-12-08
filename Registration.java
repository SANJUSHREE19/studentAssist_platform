/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registrationform;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author shravya
 */
public class Registration extends JFrame implements ActionListener {

    JFrame f = new JFrame("Registration Form with Files");
    JLabel lName, lRoll, lMobile;
    JTextField name, roll, mobile;
    JButton save, display;

    Registration() {
        f.setLayout(new FlowLayout());
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(3);
        f.setLocationRelativeTo(null);

        lName = new JLabel("Name: ");
        lName.setBounds(100, 30, 100, 30);
        f.add(lName);

        name = new JTextField(20);
        name.setBounds(200, 30, 100, 30);
        f.add(name);

        lRoll = new JLabel("Roll: ");
        lRoll.setBounds(100, 70, 100, 30);
        f.add(lRoll);

        roll = new JTextField(20);
        roll.setBounds(200, 70, 100, 30);
        f.add(roll);
        

        lMobile = new JLabel("Mobile: ");
        lMobile.setBounds(100, 110, 100, 30);
        f.add(lMobile);

        mobile = new JTextField(20);
        mobile.setBounds(200, 110, 100, 30);
        f.add(mobile);

        save = new JButton("SAVE");
        save.setBounds(150, 150, 50, 30);
        f.add(save);
        save.addActionListener(this);

        display = new JButton("DISPLAY");
        display.setBounds(200, 150, 50, 30);
        f.add(display);
        display.addActionListener(this);
    }

    public static void main(String[] args) {
        new Registration();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            String text = "";
            try (FileWriter fw = new FileWriter(new File("test.txt"), true)) {
                text = name.getText() + ":" + roll.getText() + ":" + mobile.getText() + "\n";
                if (text.length() > 3) {
                    fw.write(text);
                    JOptionPane.showMessageDialog(this, "Saved");

                } else {
                    JOptionPane.showMessageDialog(this, "Sth happened");
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
            name.setText("");
            roll.setText("");
            mobile.setText("");
        }
        if (e.getSource() == display) {
            int count = 0;
            try (Scanner sc = new Scanner(new File("test.txt"))) {
                for (; sc.hasNext(); sc.nextLine(), count++);

            } catch (Exception ex) {
                System.out.println(ex);
            }
            if (count != 0) {
                String Data[][] = new String[count][3];
                String colheader[] = {"Name", "Roll", "Phone"};
                System.out.println("count = " + count);
                try (Scanner sc = new Scanner(new File("test.txt"))) {
                    String text = "";
                    int i = 0;
                    while (sc.hasNext()) {
                        text = sc.nextLine();
                        StringTokenizer strtok = new StringTokenizer(text, ":");
                        int k = 0;
                        while (strtok.hasMoreTokens()) {
                            Data[i][k++] = strtok.nextToken();
                        }
                        ++i;
                    }
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
                JTable jtbl = new JTable(Data, colheader);
                JScrollPane jsp = new JScrollPane(jtbl);
                jsp.setBounds(60, 200, 300, 150);
                f.add(jsp);
                name.setText("");
                roll.setText("");
                mobile.setText("");
            }
        }
    }
}
