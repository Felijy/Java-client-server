package GUI;

import data.Ticket;

import javax.swing.*;
import java.awt.*;

public class PictureWindow extends JDialog {

    private Ticket ticket;

    public PictureWindow(JFrame parent, Ticket ticket) {
        super(parent, "Picture", true);
        this.ticket = ticket;
        this.setSize(new Dimension(450, 260));

        JLabel contentPane = new JLabel("");
        ImageIcon imageIcon = new ImageIcon("pic.png"); // replace with path to your image
        contentPane.setIcon(imageIcon);

        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        
        JLabel movieTitleLabel = new JLabel("Name: " + ticket.getName());
        movieTitleLabel.setBounds(100, 20, 150, 20);
        movieTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        contentPane.add(movieTitleLabel);

        JLabel seatInfoLabel = new JLabel("X: " + ticket.getCoordinates().getX() + "               Y: " + ticket.getCoordinates().getY());
        seatInfoLabel.setBounds(100, 50, 150, 20);
        contentPane.add(seatInfoLabel);

        JLabel dateLabel = new JLabel("Date: " + ticket.getCreationDate().toString());
        dateLabel.setBounds(100, 80, 150, 20);
        contentPane.add(dateLabel);

        JLabel priceLabel = new JLabel("Price: " + String.valueOf(ticket.getPrice()));
        priceLabel.setBounds(100, 110, 150, 20);
        contentPane.add(priceLabel);

        JLabel discountLabel = new JLabel("Discount: " + String.valueOf(ticket.getDiscount()));
        discountLabel.setBounds(100, 140, 150, 20);
        contentPane.add(discountLabel);

        JLabel typeLabel;
        if (ticket.getType() == null) {
            typeLabel = new JLabel("Type: " + "---null---");
        }
        else {
            typeLabel = new JLabel("Type: " + ticket.getType().toString());
        }
        typeLabel.setBounds(250, 20, 150, 20);
        contentPane.add(typeLabel);

        JLabel commentLabel = new JLabel("Comment: " + ticket.getComment());
        commentLabel.setBounds(250, 50, 150, 20);
        contentPane.add(commentLabel);

        JLabel venueLabel = new JLabel("Venue:");
        venueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        venueLabel.setBounds(250, 80, 150, 20);
        contentPane.add(venueLabel);

        if (ticket.getVenue() != null) {
            JLabel nameLabel = new JLabel("Name: " + ticket.getVenue().getName());
            nameLabel.setBounds(250, 110, 150, 20);
            contentPane.add(nameLabel);

            JLabel typeLabel2 = new JLabel("Type: " + ticket.getVenue().getType().toString());
            typeLabel2.setBounds(250, 140, 150, 20);
            contentPane.add(typeLabel2);

            JLabel capacityLabel = new JLabel("Capacity: " + ticket.getVenue().getCapacity().toString());
            capacityLabel.setBounds(250, 170, 150, 20);
            contentPane.add(capacityLabel);
        } else {
            JLabel nameLabel = new JLabel("Name: -----null----");
            nameLabel.setBounds(250, 110, 150, 20);
            contentPane.add(nameLabel);

            JLabel typeLabel2 = new JLabel("Type: -----null----");
            typeLabel2.setBounds(250, 140, 150, 20);
            contentPane.add(typeLabel2);

            JLabel capacityLabel = new JLabel("Capacity: -----null----");
            capacityLabel.setBounds(250, 170, 150, 20);
            contentPane.add(capacityLabel);
        }
        // make sure the background image fills the entire content pane
        contentPane.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());

        this.setContentPane(contentPane);

        setVisible(true);
    }
}
