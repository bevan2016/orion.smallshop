package com.orion.smallshop.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orion.smallshop.dto.InventoryItemDto;
import com.orion.smallshop.service.InventoryServiceInterface;
import com.orion.smallshop.util.RESTConstants;
import com.orion.smallshop.util.ResourceAssembler;
import com.orion.smallshop.util.ResourceCollection;
import com.orion.smallshop.util.SmallShopResource;

//we may use @io.swagger.annotations.Api to generate swagger document

@RestController
@RequestMapping(value = "/rest/items")
public class ItemController {
	 private static Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
    InventoryServiceInterface inventoryService;

    /**
     * Notes:
     * <li>we may define our own media type. For simplicity, I use APPLICATION_JSON_VALUE in all the APIs.
     * <li>the pagination parameters are not implemented
     *     
     * @param name
     * @return
     */
    @RequestMapping(value = {"/", ""}, method = {RequestMethod.GET, RequestMethod.HEAD}, 
    		produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResourceCollection<SmallShopResource<InventoryItemDto>>> getItems(
            Locale locale,
            @RequestParam(value = RESTConstants.REQUEST_PARAM_START, defaultValue = "0", required = false) long start,
            @RequestParam(value = RESTConstants.REQUEST_PARAM_LIMIT, defaultValue = "20", required = false) int limit,
            @RequestParam(value = RESTConstants.REQUEST_PARAM_SORTBY, required = false) String sortBy) {
    	List<InventoryItemDto> items = inventoryService.getSummary();
    	logger.debug("Read " + items.size() + " items");
    	ResourceCollection<SmallShopResource<InventoryItemDto>> rc = ResourceAssembler.assembleInventoryItems(items);
        return new ResponseEntity<ResourceCollection<SmallShopResource<InventoryItemDto>>> (rc, HttpStatus.OK);
    }

}
