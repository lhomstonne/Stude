package fr.leansys.business.dao;

import fr.leansys.business.model.ShopItem;

import java.util.List;

public interface ShopItemDao {
    int save(ShopItem shopItem);

    boolean delete(int id);

    boolean update(ShopItem shopItem);

    List<ShopItem> getAll();

    ShopItem getById(int id);
}