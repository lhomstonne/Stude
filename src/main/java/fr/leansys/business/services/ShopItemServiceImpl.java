/*
 */
package fr.leansys.business.services;

import fr.leansys.business.dao.ShopItemDao;
import fr.leansys.business.model.ShopItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Class "passe plat" sert uniquement à transporter les données d'une classe à une autre entre la
 * couche d'accès au données et le controller
 */


@Service("shopItemService")
public class ShopItemServiceImpl implements ShopItemService {
    private static final Logger log = LoggerFactory.getLogger(ShopItemServiceImpl.class);

    @Autowired // sert à lier la classe shopItemDao automatiquement
    private ShopItemDao shopItemDao;



    @Override
    public int save(ShopItem shopItem) {
        return shopItemDao.save(shopItem);
    }

    @Override
    public boolean delete(int id) {
        return shopItemDao.delete(id);
    }

    @Override
    public boolean update(ShopItem shopItem) {
        return shopItemDao.update(shopItem);
    }

    @Override
    public List<ShopItem> getAll() {
        return shopItemDao.getAll();
    }

    @Override
    public ShopItem getById(int id) {
        return shopItemDao.getById(id);
    }
}
