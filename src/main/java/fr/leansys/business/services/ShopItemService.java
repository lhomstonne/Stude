package fr.leansys.business.services;

import fr.leansys.business.model.ShopItem;

import java.util.List;

/**
 * Created by jrenno on 26/08/15.
 */
public interface ShopItemService {
    int save(ShopItem shopItem);

    boolean delete(int id);

    boolean update(ShopItem shopItem);

    List<ShopItem> getAll();

    ShopItem getById(int id);
}
