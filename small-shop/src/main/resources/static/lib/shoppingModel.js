angular.module('SmallShop.Shopping').service('ShoppingModel', function($http, $log) {
    var service = this;

	items = [
            {
                name: 'Item A',
				id: 'I000A',
                count: 20
            },
            {
                name: 'Item B',
				id: 'I000B',
				count: 10
            }
        ];
    orders = [
			{ 
				id: 'Order_0001',
				items: [
					{
						name: 'Item A',
						count: 2
					},
					{
						name: 'Item B',
						count: 1
					}
				]
			},
			{ 
				id: 'Order_0002',
				items: [
					{
						name: 'Item A',
						count: 2
					},
					{
						name: 'Item B',
						count: 1
					}
				]
			},
        ];

	service.getItems = function () {
		$log.debug("Loading items...");
        return $http.get('/rest/items').then( function(result) { return result;});
	};

	service.getOrders = function () {
		$log.debug("Loading orders...");
        return $http.get('/rest/orders').then( function(result) { return result;});
	};

	service.placeOrder = function (order) {
		$log.debug("Processing orders...");
        return $http.post('/rest/orders', order).then( function(result) { return result;});
	};
});
