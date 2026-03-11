import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AirplaneBooking extends JFrame {
    private JComboBox<String> fromBox,toBox,classBox,seatBox;
    private JTextField nameField;
    private JButton bookButton;



    public AirplaneBooking() {
        setTitle("Airplane Ticket Booking System");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel=new JPanel(new GridLayout(7,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20 ));
        panel.add(new JLabel("Passenger Name"));
        nameField=new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("From:"));
        fromBox=new JComboBox<>(new String[]{"   ","Delhi","Lucknow","Mumbai","Chuttmalpur","Dehradun"});
        panel.add(fromBox);

        panel.add(new JLabel("Destination :"));
        toBox=new JComboBox<>(new String[] {"   ","Delhi","Lucknow","Mumbai","Chuttmalpur","Dehradun"});
        panel.add(toBox);

        panel.add(new JLabel("Class:"));
        classBox=new JComboBox<>(new String[] {"    ","Firstclass","Economy","Buisness"});
        panel.add(classBox);

        panel.add(new JLabel("Seat:"));
        seatBox=new JComboBox<>(new String[] {" ","1A","1B","2A","2B","3A","3B"});
        panel.add(seatBox);

        bookButton=new JButton("Book Ticket");
        panel.add(bookButton);
        panel.add(new JLabel());
        add(panel);

        bookButton.addActionListener(e->processBooking());
    }

    private void processBooking() {
        String name=nameField.getText().trim();
        String from=(String)fromBox.getSelectedItem();
        String to=(String)toBox.getSelectedItem();
        String flightclass=(String)classBox.getSelectedItem();
        String seat=(String)seatBox.getSelectedItem();

        if(name.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please enter your name");
            return;
        }
        if(from.equals(to)) {
            JOptionPane.showMessageDialog(this,"Departure and Destination couldnot be same");
            return;
        }


        int baseprice=2000;
        if(flightclass.equals("Buisness")) {
            baseprice=baseprice+1000;

        }
        else if(flightclass.equals("Firstclass")) {
            baseprice=baseprice+2000;
        }


        int confirm = JOptionPane.showConfirmDialog(this,
                "Proceed to payment of Rs " + baseprice + "?", "Payment",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this,
                    String.format("Ticket Booked Successfully!\n\nName: %s\nRoute: %s --> %s\nClass: %s\nSeat: %s\nAmount Paid: Rs %d",
                            name, from, to, flightclass, seat, baseprice),
                    "Booking Confirmed", JOptionPane.INFORMATION_MESSAGE);
        }



    }
    public static void main (String args[]) {
        SwingUtilities.invokeLater(()->{
            new AirplaneBooking().setVisible(true);
        });
    }

}