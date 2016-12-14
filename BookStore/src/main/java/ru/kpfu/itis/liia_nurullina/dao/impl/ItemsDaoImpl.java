package ru.kpfu.itis.liia_nurullina.dao.impl;

import ru.kpfu.itis.liia_nurullina.dao.ItemsDao;
import ru.kpfu.itis.liia_nurullina.dao.factory.JDBCTemplate;
import ru.kpfu.itis.liia_nurullina.model.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liia on 03.11.2016.
 */
public class ItemsDaoImpl extends JDBCTemplate implements ItemsDao {

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
            closeResources();
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
            closeResources();
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
            closeResources();
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
                addFieldsToItem(item);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
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
                addFieldsToItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
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
                addFieldsToItem(item);
                list.add(item);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }

    @Override
    public List<Item> viewItemsByGenre(int offset, int noOfRecords, String genre) {
//        String query = "select * from items WHERE genre like ? order by price limit " + noOfRecords + " offset" + offset + "";
        String query = "select * from items WHERE genre like ? order by price ";
        List<Item> list = new ArrayList<>();
        Item item;
        try {
            con = getConnection();
            ptmt = con.prepareStatement(query);
            ptmt.setString(1, genre);
//            ptmt.setInt(2, noOfRecords);
//            ptmt.setInt(3, offset);
            rs = ptmt.executeQuery();

            while (rs.next()) {
                item = new Item();
                addFieldsToItem(item);
                list.add(item);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }

    private void addFieldsToItem(Item item) throws SQLException {
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setPrice(rs.getFloat("price"));
        item.setPicture(rs.getString("picture"));
        item.setGenre(rs.getString("genre"));
        item.setId(rs.getLong("item_id"));
    }
}
