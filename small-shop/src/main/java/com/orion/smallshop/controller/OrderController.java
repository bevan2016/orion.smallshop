package com.orion.smallshop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orion.smallshop.dto.OrderDto;
import com.orion.smallshop.errors.InsufficientInventoryException;
import com.orion.smallshop.service.OrderServiceInterface;
import com.orion.smallshop.util.RESTConstants;
import com.orion.smallshop.util.ResourceAssembler;
import com.orion.smallshop.util.ResourceCollection;
import com.orion.smallshop.util.SmallShopResource;

@RestController
@RequestMapping(value = "/rest/orders")
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderServiceInterface orderService;
    
    @RequestMapping(value = {"/", ""}, method = {RequestMethod.GET, RequestMethod.HEAD}, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceCollection<SmallShopResource<OrderDto>>> getOrders(
            @RequestParam(value = RESTConstants.REQUEST_PARAM_START, defaultValue = "0", required = false) long start,
            @RequestParam(value = RESTConstants.REQUEST_PARAM_LIMIT, defaultValue = "20", required = false) int limit,
            @RequestParam(value = RESTConstants.REQUEST_PARAM_SORTBY, required = false) String sortBy) {
    	List<OrderDto> orders = orderService.getAll();
    	logger.debug("Read " + orders.size() + " orders.");
    	ResourceCollection<SmallShopResource<OrderDto>> rc = ResourceAssembler.assembleOrders(orders);
        return new ResponseEntity<ResourceCollection<SmallShopResource<OrderDto>>> (rc, HttpStatus.OK);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SmallShopResource<OrderDto>> placeOrder(
    		@RequestBody OrderDto order) throws InsufficientInventoryException{
    	OrderDto o = orderService.placeOrder(order);
    	
		logger.info("Order is placed: id=" + o.getId());
    	SmallShopResource<OrderDto> rc = ResourceAssembler.assembleOrder(o);
        return new ResponseEntity<SmallShopResource<OrderDto>> (rc, HttpStatus.OK);
    }
}
