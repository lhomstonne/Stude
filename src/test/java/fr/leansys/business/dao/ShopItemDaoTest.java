package fr.leansys.business.dao;

import fr.leansys.business.model.ShopItem;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/META-INF/spring/application-context.xml",
        "classpath:/META-INF/spring/local-data-context.xml"
        })
public class ShopItemDaoTest {

    @Autowired
    @Qualifier("shopItemDao")
    private ShopItemDao dao;

    @Test
    public void testDao() {
        assertNotNull("Injection failed", dao);
    }

    @Test
    public void testSaveAndDelete() {
        assert dao != null;
        ShopItem item = new ShopItem();
        item.setDescription("description");
        item.setName("name");
        item.setPrice(100);
        int id = dao.save(item);
        assertTrue(id > 0);
        assertTrue(dao.delete(id));
    }

    @Test
    public void testGetAll() {
        assert dao != null;
        assertTrue(dao.getAll().size() > 1);
    }
}
