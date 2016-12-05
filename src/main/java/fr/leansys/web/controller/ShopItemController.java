package fr.leansys.web.controller;

import fr.leansys.business.model.ShopItem;
import fr.leansys.business.services.ShopItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Sert à faire le lien entre la web app et le traitement et l'enregistrement des données
 */



@Controller // Définie la classe comme un controller
public class ShopItemController {
    private static final Logger log = LoggerFactory.getLogger(ShopItemController.class);
    public static final String ITEM_FORM = "itemForm";

    @Autowired
    private ShopItemService shopItemService;


    //RequestMapping sert à faire le lien entre l'action html et la méthode java
    //ici à chaque fois que la valeur du path context contiendra /, "" ou /index la page sera rediriger en utilisant la méthode get
    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        log.debug("index");
        return "redirect:/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit() {
        log.debug("/edit");
        //doc :
        //ModelAndView(View view, String modelName, Object modelObject)
        //Convenient constructor to take a single model object.
        //Sert à construire un nouvel objet dans la jsp "edit" de type item_form qui répond au modèle de ShopItem

        return new ModelAndView("edit", ITEM_FORM, new ShopItem());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)

    //@ModelAttribute sert à servir à ShopItemService un objet répondant au caractéristique de ShopItem à partir des
    //données entrées dans le formulaire
    public String add(@ModelAttribute("shopItemForm") ShopItem shopItem, ModelMap model) {
        log.debug("/add");

        // process
        if (shopItem.getId() != 0) {
            shopItemService.update(shopItem);
        } else {
            shopItemService.save(shopItem);
        }

        // show results
        model.addAttribute("name", shopItem.getName());
        model.addAttribute("description", shopItem.getDescription());
        model.addAttribute("price", shopItem.getPrice());

        return "show";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    //@PathVariable sert à récupérer des données de l'url
    public String add(@PathVariable("id") int id, ModelMap model) {
        log.debug("/show");

        // process
        ShopItem shopItem = shopItemService.getById(id);

        // show results
        model.addAttribute("name", shopItem.getName());
        model.addAttribute("description", shopItem.getDescription());
        model.addAttribute("price", shopItem.getPrice());

        return "show";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("/list");
        // list items
        model.addAttribute("items", shopItemService.getAll());
        return "list";
    }

    // show update form
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") int id) {

        log.debug("/update : {}", id);

        ShopItem shopItem = shopItemService.getById(id);

        return new ModelAndView("edit", ITEM_FORM, shopItem);
    }

    // delete user
    // {id} sert à passer des information par l'url pour pouvoir les recupérer cf rest et soap
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {

        log.debug("/delete : {}", id);

        shopItemService.delete(id);

        return "redirect:/list";

    }
}
