package handler;

import data.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class DBHandler {

    private static Connection conn;

    {
        var url = "jdbc:postgresql://localhost:5432/lab7";
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("bd.cfg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isUserExists(String login) throws SQLException {
        String query = "SELECT * FROM users WHERE login = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return true;
        else return false;
    }

    public static Integer getUserId(String login) throws SQLException {
        String query = "SELECT user_id FROM users WHERE login = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt("user_id");
        else return null;
    }

    public static boolean checkPassword(String login, String password) {
        String query = "SELECT * FROM users WHERE login = ? AND password = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return true;
            else return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean createUser(String login, String password) throws SQLException {
        String query = "INSERT INTO users (login, password) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, login);
        ps.setString(2, password);
        return ps.executeUpdate() > 0;
    }

    public static boolean checkPermission(String login, String key) {
        String query = "SELECT ticket_id FROM tickets JOIN users u on u.user_id = tickets.user_id WHERE login = ? AND key = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return true;
            else return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void removeByKey (String key) throws SQLException {
        String query = "DELETE FROM tickets WHERE key = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, key);
        ps.executeUpdate();
    }

    public static void executeQuery(String query) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
    }

    public static boolean updateTicket(String key, Ticket ticket) throws SQLException {
        if (ticket.getVenue() != null) {
            String query = "UPDATE venue SET name = ?, capacity = ?, venue_type = ? WHERE venue_id = ?; ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ticket.getVenue().getName());
            if (ticket.getVenue().getCapacity() == null) ps.setNull(2, Types.BIGINT);
            else ps.setLong(2, ticket.getVenue().getCapacity());
            ps.setObject(3, ticket.getVenue().getType(), Types.OTHER);
            ps.setLong(4, ticket.getVenue().getId());
            ps.executeUpdate();
        }
        String query1 = "UPDATE tickets SET name = ?, x = ?, y = ?, price = ?, discount = ?, comment = ?, ticket_type = ?, venue_id = ? WHERE key = ?;";
        PreparedStatement ps = conn.prepareStatement(query1);
        ps.setString(1, ticket.getName());
        ps.setFloat(2, ticket.getCoordinates().getX());
        ps.setFloat(3, ticket.getCoordinates().getY());
        ps.setFloat(4, ticket.getPrice());
        ps.setInt(5, ticket.getDiscount());
        if (ticket.getComment() == null) ps.setNull(6, Types.VARCHAR);
        else ps.setString(6, ticket.getComment());
        if (ticket.getType() == null) ps.setNull(7, Types.OTHER);
        else ps.setObject(7, ticket.getType(), Types.OTHER);
        if (ticket.getVenue() == null) ps.setNull(8, Types.INTEGER);
        else ps.setLong(8, ticket.getVenue().getId());
        ps.setString(9, key);
        ps.executeUpdate();
        return true;
    }

    public static boolean createTicket(String key, Ticket ticket, int user) throws SQLException {
        if (ticket.getVenue() != null) {
            String query = "INSERT INTO venue (name, capacity, venue_type) VALUES (?, ?, ?) RETURNING venue_id; ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ticket.getVenue().getName());
            if (ticket.getVenue().getCapacity() == null) ps.setNull(2, Types.BIGINT);
            else ps.setLong(2, ticket.getVenue().getCapacity());
            ps.setObject(3, ticket.getVenue().getType(), Types.OTHER);
            ResultSet venue_id = ps.executeQuery();
            if (venue_id.next()) {
                ticket.getVenue().setIDA(venue_id.getLong("venue_id"));
            }
        }
        String query1 = "INSERT INTO tickets (name, x, y, price, discount, comment, ticket_type, venue_id, user_id, key) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(query1);
        ps.setString(1, ticket.getName());
        ps.setFloat(2, ticket.getCoordinates().getX());
        ps.setFloat(3, ticket.getCoordinates().getY());
        ps.setFloat(4, ticket.getPrice());
        ps.setInt(5, ticket.getDiscount());
        if (ticket.getComment() == null) ps.setNull(6, Types.VARCHAR);
        else ps.setString(6, ticket.getComment());
        if (ticket.getType() == null) ps.setNull(7, Types.OTHER);
        else ps.setObject(7, ticket.getType(), Types.OTHER);
        if (ticket.getVenue() == null) ps.setNull(8, Types.INTEGER);
        else ps.setLong(8, ticket.getVenue().getId());
        ps.setInt(9, user);
        ps.setString(10, key);
        ps.executeUpdate();
        return true;
    }

    public static Ticket getTicket(String key) throws SQLException {
        String query = "SELECT * FROM tickets LEFT JOIN venue v on v.venue_id = tickets.venue_id JOIN users u on u.user_id = tickets.user_id WHERE key = ?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, key);
        ResultSet rs = ps.executeQuery();
        Ticket ticket = new Ticket();
        rs.next();
        ticket.setId(rs.getLong(1));
        ticket.setName(rs.getString(2));
        ticket.setCoordinates(new Coordinates(rs.getFloat(3), rs.getFloat(4)));
        ticket.setCreationDate(rs.getDate(5).toLocalDate());
        ticket.setPrice(rs.getFloat(6));
        ticket.setDiscount(rs.getInt(7));
        ticket.setComment(rs.getString(8));
        if (rs.getObject(9) != null) {
            ticket.setType(convertToTicketType(rs.getString(9)));
        } else {
            ticket.setType(null);
        }
        if (rs.getInt("venue_id") != 0) {
            ticket.setVenue(new Venue());
            ticket.getVenue().setIDA(rs.getInt("venue_id"));
            ticket.getVenue().setName(rs.getString(14));
            ticket.getVenue().setCapacity(rs.getLong("capacity"));
            ticket.getVenue().setType(convertToVenueType(rs.getString("venue_type")));
        } else ticket.setVenue(null);
        ticket.setUser(rs.getString("login"));
        return ticket;
    }

    public static void refreshMap() throws SQLException {
        HashMap<String, Ticket> map = new HashMap<>();
        String query = "SELECT * FROM tickets LEFT JOIN venue v on v.venue_id = tickets.venue_id JOIN users u on u.user_id = tickets.user_id;";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Ticket ticket = new Ticket();
            ticket.setId(rs.getLong(1));
            ticket.setName(rs.getString(2));
            ticket.setCoordinates(new Coordinates(rs.getFloat(3), rs.getFloat(4)));
            ticket.setCreationDate(rs.getDate(5).toLocalDate());
            ticket.setPrice(rs.getFloat(6));
            ticket.setDiscount(rs.getInt(7));
            ticket.setComment(rs.getString(8));
            if (rs.getObject(9) != null) {
                ticket.setType(convertToTicketType(rs.getString(9)));
            } else {
                ticket.setType(null);
            }
            if (rs.getInt("venue_id") != 0) {
                ticket.setVenue(new Venue());
                ticket.getVenue().setIDA(rs.getInt("venue_id"));
                ticket.getVenue().setName(rs.getString(14));
                ticket.getVenue().setCapacity(rs.getLong("capacity"));
                ticket.getVenue().setType(convertToVenueType(rs.getString("venue_type")));
            } else ticket.setVenue(null);
            String key = rs.getString("key");
            ticket.setUser(rs.getString("login"));
            map.put(key, ticket);
        }
        mapHandler.setCollection(map);
    }

    private static TicketType convertToTicketType(String ticketTypeValue) {
        if (ticketTypeValue.equals("USUAL")) {
            return TicketType.USUAL;
        } else if (ticketTypeValue.equals("BUDGETARY")) {
            return TicketType.BUDGETARY;
        } else if (ticketTypeValue.equals("CHEAP")) {
            return TicketType.CHEAP;
        } else {
            return null;
        }
    }

    private static VenueType convertToVenueType(String venueTypeValue) {
        if (venueTypeValue.equals("BAR")) {
            return VenueType.BAR;
        } else if (venueTypeValue.equals("LOFT")) {
            return VenueType.LOFT;
        } else if (venueTypeValue.equals("STADIUM")) {
            return VenueType.STADIUM;
        } else if (venueTypeValue.equals("OPEN_AREA")) {
            return VenueType.OPEN_AREA;
        } else if (venueTypeValue.equals("THEATRE")) {
            return VenueType.THEATRE;
        } else {
            return null;
        }
    }
}
