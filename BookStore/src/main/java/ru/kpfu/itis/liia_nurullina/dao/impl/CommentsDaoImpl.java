package ru.kpfu.itis.liia_nurullina.dao.impl;

import ru.kpfu.itis.liia_nurullina.dao.CommentsDao;
import ru.kpfu.itis.liia_nurullina.dao.factory.JDBCTemplate;
import ru.kpfu.itis.liia_nurullina.model.Comment;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liia on 20.11.2016.
 */
public class CommentsDaoImpl extends JDBCTemplate implements CommentsDao {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH.mm");

    public void add(Comment comment) {
        try {
            String querystring = "INSERT INTO comments(user_id, item_id, text, date, login) VALUES (?, ?, ?, ?, ?)";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            ptmt.setLong(1, comment.getUser_id());
            ptmt.setLong(2, comment.getItem_id());
            ptmt.setString(3, comment.getText());
            ptmt.setTimestamp(4, timestamp);
            ptmt.setString(5, comment.getLogin());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void delete(Long id) {
        try {
            String querystring = "DELETE FROM comments WHERE comment_id =?";
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

    public Comment findByPrimaryKey(Long id) {
        Comment comment = null;
        try {
            String querystring = "SELECT * FROM comments WHERE item_id = ?";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setLong(1, id);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                comment = new Comment();
                setFields(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return comment;
    }

    @Override
    public List findByItemId(Long id) {
        List comments = new ArrayList();
        Comment comment;
        try {
            String querystring = "SELECT * FROM comments WHERE item_id = ? ORDER BY date";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setLong(1, id);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                comment = new Comment();
                setFields(comment);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return comments;
    }

    private void setFields(Comment comment) throws SQLException {
        comment.setComment_id(rs.getLong("comment_id"));
        comment.setText(rs.getString("text"));
        comment.setUser_id(rs.getLong("user_id"));
        comment.setItem_id(rs.getLong("item_id"));
        comment.setDate(sdf.format(rs.getTimestamp("date")));
        comment.setLogin(rs.getString("login"));
    }
}
