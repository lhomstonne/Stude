package fr.leansys.business.dao;

import fr.leansys.business.model.ShopItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("shopItemDao")
public class ShopItemDaoImpl implements ShopItemDao {
    private static final Logger log = LoggerFactory.getLogger(ShopItemDaoImpl.class);

    // Default resultset row mapper to java bean Mappage entre les données de la bdd et la classe modèle
    public static final RowMapper<ShopItem> ROW_MAPPER = new RowMapper<ShopItem>() {
        @Override
        public ShopItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ShopItem(
                    rs.getInt("ITEMID"),
                    rs.getString("NAME"),
                    rs.getString("DESCRIPTION"),
                    rs.getInt("PRICE")
            );
        }
    };

    private JdbcTemplate jdbcTemplate; // Objet servant à configurer le modèle des données

    @Autowired // Annotation pour la "relier" directement aux autres classes
    private DataSourceTransactionManager transactionManager; // Objet servant à creer des transaction avec la bdd

    @Autowired
    //Objet servant à définir la source des données et le modèle à utiliser pour les récupérer
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /**
     * Delete record
     *
     * @param id
     * @return boolean
     */

    // Fonction de suppression d'un item
    public boolean delete(int id) {
        //Create and execute the SQL statement
        String sqlOrder = "DELETE FROM SHOPPINGITEMS WHERE ITEMID = ?";

        int result = jdbcTemplate.update(sqlOrder, id); // Le résultat est un entier 1 ou 0 renvoyé grace à jdbc
        log.debug("Deleted Record with ID {} : {}", id, result);

        return (result == 1); // renvoie le resultat du test ==> transform result en bool
    }

    /**
     * Save record
     *
     * @param shopItem
     * @return id
     */
    public int save(ShopItem shopItem) {
        //Create and execute the SQL statement
        String sqlOrder = "INSERT INTO SHOPPINGITEMS (NAME, DESCRIPTION, PRICE) VALUES(?, ?, ?)";

        // Pas besoin ici de statment et preparedStatement pour les paramètre c'est l'objet jdbc qui s'en occupe

        //Déclaration d'une transaction (sert à garantir qu'en cas de connexion multiple on récupère bien l'id de la transaction en cours)
        TransactionDefinition def = new DefaultTransactionDefinition();

        // Sert à tracker l'instance de connextion pour savoir si tout c'est bien passé ou si un rollback est necessaire
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            //Objet servant à définir la requète suivie des paramètres dans l'odre
            jdbcTemplate.update(sqlOrder, shopItem.getName(), shopItem.getDescription(), shopItem.getPrice());

            // On récupère le dernier id créer ===> on utilise Integer plutot que int car il herite de la class object
            shopItem.setId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
            log.debug("Created Record with ID {}", shopItem.getId());

            // On effectue la transaction en fonction de la valeur de status
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            log.error("Error in creating record, rolling back");

            // Si la valeur de status indique une erreur on effectue un rollback
            transactionManager.rollback(status);

            //on renvoi l'exception au niveau supérieur
            throw e;
        }
        return shopItem.getId();
    }

    /**
     * Get all records
     *
     * @return List<shopItem>
     */
    public List<ShopItem> getAll() {
        // Execute the query and get the result back from the handler
        String sqlOrder = "SELECT ITEMID, NAME, DESCRIPTION, PRICE FROM SHOPPINGITEMS";


        // On récupère l'ensemble des item == Pas besoin de foreach l'objet jdbcTemplate le fait pour moi
        // ROW_MAPPER sert à définir la forme pour sauvegarder les données de la base dans un objet shopItem
        List result = jdbcTemplate.query(sqlOrder, ROW_MAPPER); // Pourquoi cet objet n'a pas besoin d'être instancié

        //On récupère le nombre d'enregistrement enregistés
        log.info("found {} record(s)", result.size());

        return result;
    }

    @Override // Pourquoi a t-on besoin de redefinir la méthode???
    public ShopItem getById(int id) {
        String sqlOrder = "SELECT ITEMID, NAME, DESCRIPTION, PRICE FROM SHOPPINGITEMS WHERE ITEMID = ?";


        // Pourquoi un tableau d'objet à 1 dimension, OK pour le cast en objet (int est primitif)
        ShopItem result = jdbcTemplate.queryForObject(sqlOrder, new Object[] { id }, ROW_MAPPER);
        log.info("found record with id {}", result.getId());

        return result;
    }

    /**
     * Update a record
     *
     * @param shopItem
     */
    public boolean update(ShopItem shopItem) {
        String sqlOrder = "UPDATE SHOPPINGITEMS SET NAME = ?, DESCRIPTION = ?, PRICE = ? WHERE ITEMID = ?";

        int result = jdbcTemplate.update(sqlOrder, shopItem.getName(), shopItem.getDescription(), shopItem.getPrice(), shopItem.getId());
        log.debug("Updated Record with ID {} : {}", shopItem.getId(), result);

        return (result == 1);
    }

}
