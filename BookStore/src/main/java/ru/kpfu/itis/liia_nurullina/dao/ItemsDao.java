package ru.kpfu.itis.liia_nurullina.dao;

import ru.kpfu.itis.liia_nurullina.model.Item;

import java.util.List;

/**
 * Created by Liia on 03.11.2016.
 */
public interface ItemsDao {
    void add(Item item);

    void update(Item item);

    void delete(Long id);

    Item findByPrimaryKey(Long id);

    List<Item> viewAllItems(int offset, int noOfRecords);

    List<Item> viewItemsByGenre(int offset, int noOfRecords, String genre);

    int size();
}
