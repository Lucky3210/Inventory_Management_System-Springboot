package net.sourcecode.springboot.controller;

import net.sourcecode.springboot.model.Item;
import org.springframework.web.bind.annotation.ModelAttribute;
import net.sourcecode.springboot.repository.ItemRepository;
import net.sourcecode.springboot.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("")
public class ItemController {

//    to enable usage of the defined function in the repository package we use the following
//    @Autowired
//    ItemRepository itemRepository;

    @Autowired
    ItemService itemService; // with this we are able to use the various method specified in the ItemService

//    CONSTRUCTOR DEPENDENCY INJECTION
//    public ItemController(ItemService itemService) {
//        super();
//        this.itemService = itemService;
//    }

//    BUILD CREATE ITEM REST API
//    we use the response-entity class as our return type because we would add some http
//    value and headers like response status etc.
//    request-body annotation allows to retrieve the request-body and convert it to java object
//    @PostMapping()
//    public ResponseEntity<Item> saveItem(@RequestBody Item item) {
//        return new ResponseEntity<Item>(itemService.saveItem(item), HttpStatus.CREATED); // this returns the function specified in the service package, together with the http-status code
//    }

//    render add item form
    @GetMapping("/item/new")
    public String addItem(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);
        return "addItem";
    }

//    save added item
    @PostMapping("/item")
    public String saveItem(@ModelAttribute("item") Item item) {
        itemService.saveItem(item);
        return "redirect:/item";
    }

// render item
    @GetMapping("/item")
    public String listItem(Model model) {
        model.addAttribute("item", itemService.getAllItems());
        return "indexItem";
    }

//    render update item form
    @GetMapping("/item/updateForm/{id}")
    public String itemForm(@PathVariable (value = "id") long id, Model model) {
//        get item form the service
        Item item = itemService.getItemById(id);

//        set item as  model attribute to pre-populate the form
        model.addAttribute("item", item);
        return "updateItem";
    }

    @PostMapping("/item/{id}")
    public String updateItem(@PathVariable Long id,
                             @ModelAttribute("item") Item item,
                             Model model) {

//       get item from the db by id through the service implementation
        itemService.updateItem(item, id);
        return "redirect:/item";
    }

//    handler method to delete item
    @GetMapping("/item/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return "redirect:/item";
    }



//    BUILD GET ALL ITEM REST API
//    @GetMapping()
//    public List<Item> getAllItem(){
//        return itemService.getAllItems();
//    }



//    BUILD GET ITEM BY ID REST API
//    http://localhost:8080/api/item/1
//    @GetMapping("{id}")
//    public ResponseEntity<Item> getItemById(@PathVariable("id") long itemId) {
//        return new ResponseEntity<Item>(itemService.getItemById(itemId), HttpStatus.OK);
//    }



//    BUILD GET ITEM BY CATEGORY REST API
//    http://localhost:8080/api/item/category?category=smartphones
//    @GetMapping("/category")
//    public ResponseEntity<List<Item>> getItemByCategory(@RequestParam String category) {
//        return new ResponseEntity<List<Item>>(itemRepository.findByCategory(category), HttpStatus.OK);
//    }

//    BUILD GET ITEM BY BRAND REST API
//    http://loaclhost:8080/api/item/brand?brand=Apple
//    @GetMapping("/brand")
//    public ResponseEntity<List<Item>> getItemByBrand(@RequestParam String brand) {
//        return new ResponseEntity<List<Item>>(itemRepository.findByBrand(brand), HttpStatus.OK);
//    }

//    BUILD UPDATE ITEM REST API
//    http://localhost:8080/api/item/1
//    @PutMapping("{id}")
//    public ResponseEntity<Item> updateItem(@PathVariable("id") long id, @RequestBody Item item) {
//        return new ResponseEntity<Item>(itemService.updateItem(item, id), HttpStatus.OK);
//    }
}

