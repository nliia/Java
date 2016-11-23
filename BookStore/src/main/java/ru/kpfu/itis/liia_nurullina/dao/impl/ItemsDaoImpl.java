package ru.kpfu.itis.liia_nurullina.dao.impl;

import ru.kpfu.itis.liia_nurullina.dao.ItemsDao;
import ru.kpfu.itis.liia_nurullina.dao.factory.ConnectionFactory;
import ru.kpfu.itis.liia_nurullina.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liia on 03.11.2016.
 */
public class ItemsDaoImpl implements ItemsDao {
    Connection con = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;
    Statement stmt;
    private int noOfRecords;

    private Connection getConnection() throws SQLException {
        return ConnectionFactory.getInstance().getConnection();
    }

    public void add(Item item) {

        try {
            String querystring = "INSERT INTO items(name, description, price, picture) VALUES (?, ?,?, ?)";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);

            ptmt.setString(1, item.getName());
            ptmt.setString(2, item.getDescription());
            ptmt.setFloat(3, item.getPrice());
            ptmt.setString(4, item.getPicture());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ptmt != null)
                    ptmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Item item) {
        try {
            String querystring = "UPDATE items SET name =?,description =?, price =?, picture =? WHERE item_id = ?";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);

            ptmt.setString(1, item.getName());
            ptmt.setString(2, item.getDescription());
            ptmt.setFloat(3, item.getPrice());
            ptmt.setString(4, item.getPicture());
            ptmt.setLong(5, item.getId());
            ptmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ptmt != null)
                    ptmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Long id) {
        try {
            String querystring = "DELETE FROM items WHERE item_id =?";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setLong(1, id);
            ptmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ptmt != null)
                    ptmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public List findAll() {
        List items = new ArrayList();
        Item item;
        try {
            String querystring = "SELECT * FROM items";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                item = new Item();
                item.setId(rs.getLong("item_id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getFloat("price"));
                item.setPicture(rs.getString("picture"));

                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ptmt != null)
                    ptmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return items;
    }

    public Item findByPrimaryKey(Long id) {
        Item item = null;
        try {
            String querystring = "SELECT * FROM items WHERE item_id = ?";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setLong(1, id);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                item = new Item();
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getFloat("price"));
                item.setPicture(rs.getString("picture"));
                item.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ptmt != null)
                    ptmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return item;
    }

    @Override
    public List<Item> viewAllItems(int offset, int noOfRecords) {
        String query = "select * from items order by price limit " + noOfRecords + " offset " + offset;
        List<Item> list = new ArrayList<>();
        Item item;
        try {
            con = getConnection();
            ptmt = con.prepareStatement(query);
            rs = ptmt.executeQuery();

            while (rs.next()) {
                item = new Item();
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getFloat("price"));
                item.setPicture(rs.getString("picture"));
                item.setId(rs.getLong("item_id"));
                list.add(item);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
