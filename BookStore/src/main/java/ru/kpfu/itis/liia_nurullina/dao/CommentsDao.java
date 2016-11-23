package ru.kpfu.itis.liia_nurullina.dao;

import ru.kpfu.itis.liia_nurullina.model.Comment;
import java.util.List;

public interface CommentsDao {
    void add(Comment comment);

    void delete(Long id);

    List findAll();

    Comment findByPrimaryKey(Long id);

    List findByItemId(Long id);
}
