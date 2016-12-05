package fr.leansys.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Get Setter
@NoArgsConstructor // Constructeur non paramétré
@AllArgsConstructor // Constructeur paramétré pour chacun des arguments
public class ShopItem {
    private int id = 0;
    private String name;
    private String description;
    private int price;
}
