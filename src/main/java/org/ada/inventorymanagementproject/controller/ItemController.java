package org.ada.inventorymanagementproject.controller;

import org.ada.inventorymanagementproject.dto.ItemDTO;
import org.ada.inventorymanagementproject.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/suppliers/{supplierId}/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity create(@PathVariable String supplierId,
                                 @RequestBody ItemDTO itemDTO){

        itemService.create(itemDTO, supplierId);

        return new ResponseEntity(itemDTO.getCode(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieve(){

        return new ResponseEntity(itemService.retrieveAll(), HttpStatus.OK);
    }

    @GetMapping("/{itemCode}")
    public ResponseEntity retrieveById(@PathVariable String supplierId,
                                       @PathVariable String itemCode){

        ItemDTO itemDTO = itemService.retrieveByCode(itemCode);

        return new ResponseEntity(itemDTO, HttpStatus.OK);

    }

    @DeleteMapping("/{itemCode}")
    public ResponseEntity delete(@PathVariable String itemCode){
        itemService.delete(itemCode);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{itemCode}")
    public ResponseEntity replace(@PathVariable String itemCode,
                                  @RequestBody ItemDTO personDTO) {
        itemService.replace(itemCode, personDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{itemCode}")
    public ResponseEntity modify(@PathVariable String itemCode,
                                 @RequestBody Map<String, Object> fieldsToModify) {
        itemService.modify(itemCode, fieldsToModify);

        return new ResponseEntity(HttpStatus.OK);
    }

}
