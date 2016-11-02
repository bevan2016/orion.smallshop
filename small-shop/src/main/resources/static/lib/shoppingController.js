angular.module('SmallShop.Shopping').controller('ShoppingController', function($scope, $log, ShoppingModel) {
    var ctrl = this;
	ctrl.items = null;
	ctrl.orders = [];
	ctrl.cart = { };
	
	//items
	ctrl.allItems = function(){
		ShoppingModel.getItems().then(
			function onSucces(result) {
		        data = (result !== null) ? result.data:null;
				if (data !== null && data._embedded  !== undefined)
					ctrl.items = data._embedded.inventoryItemDtoList;
		    }, function onError(response) {
				$log.error("Failed to load items: ", response.statusText);
		    }
		);
	};
	ctrl.allItems ();

	//orders
	ctrl.allOrders = function(){
		ShoppingModel.getOrders().then(
			function onSucces(result) {
		        data = (result !== null) ? result.data:null;
				if (data !== null && data._embedded  !== undefined)
					ctrl.orders = data._embedded.orderDtoList;
		    }, function onError(response) {
				$log.error("Failed to load orders: ", response.statusText);
		    }
		);
	};
	ctrl.allOrders ();

	ctrl.placeOrder = function() {
		var newOrder = ctrl.getNewOrder();
		if (newOrder === null)
		{
			$log.debug("No items in the shopping cart. ");
			return;
		}

		ShoppingModel.placeOrder(newOrder).then(
			function onSucces(result) {
				alert("You are order is being processed.");
		        data = (result !== null) ? result.data:null;
				if (data !== null )
				{
					ctrl.orders.push(data);
					ctrl.allItems ();
				}
		    }, function onError(response) {
				$log.error("Failed to process order: ", response.statusText);
		    }
		);
	};
	
	ctrl.getNewOrder = function()
	{
		if (ctrl.itemCountInCart() === 0)
			return null;

		var order = {};
		order.id = "";
		order.items = [];
		for (var id in ctrl.cart)
		{
			item = ctrl.cart[id];
			var oi = {};
			oi.id = "";
			oi.count = item.toBuy;
			oi.item = item.item;
			order.items.push(oi);
		}
		return order;
	};

	ctrl.countChange = function(inventoryItem){
		item = inventoryItem.item;
		if (inventoryItem.toBuy > 0)
		{
			ctrl.cart[item.id] = inventoryItem;
			$log.debug("Add " + inventoryItem.toBuy +" "+ item.name + " to shopping cart.");
		}
		else
		{
			if (ctrl.cart[item.id] !== 'undefined')
			{
				delete ctrl.cart[item.id];
				$log.debug("Remove " + item.name + " from shopping cart.");
			}
		}
	};

	ctrl.itemCountInCart = function(){
		var count = 0;
		for (var i in ctrl.cart)
			count++;
		return count;
	};
});

//directives
angular.module('SmallShop.Shopping').directive('item', function() {
    return {
        scope: true,
        replace: true,
        template: '<div>{{::item.item.name}}: {{::item.count}} left</div>'
    }
});

angular.module('SmallShop.Shopping').directive('shopping', function() {
    return {
        scope: true,
        replace: true,
        template: '<div><label>{{::item.item.name}}: </label>'
					+'        <input type="number" min="0" max="{{::item.count}}"  ng-model="item.toBuy" ng-change="mainCtrl.countChange(item)"  />'
					+'</div>'
    }
});
