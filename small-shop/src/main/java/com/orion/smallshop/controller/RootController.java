package com.orion.smallshop.controller;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.orion.smallshop.util.RESTConstants;
import com.orion.smallshop.util.SmallShopResource;

@RestController
@RequestMapping({ "/rest", "/rest/" })
public class RootController {

	@RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SmallShopResource<String>> getRoot() {

		SmallShopResource<String> api = new SmallShopResource<String>("SmallShop APIs");
		api.add(new Link("/", Link.REL_SELF));
		api.add(new Link(RESTConstants.URI_ITEMS, "items"));
		api.add(new Link(RESTConstants.URI_ORDER, "orders"));
        return new ResponseEntity<SmallShopResource<String>>(api, HttpStatus.OK);
    }
}
