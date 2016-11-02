var orderModule = angular.module('SmallShop', []);

orderModule.service('OrderModel', function() {
    var service = this,
         items = [
            {
                title: 'Item A',
                count: 20
            },
            {
                title: 'Item B',
                count: 10
            }
        ];
    service.getItems = function () {
        return items;
    };
});

orderModule.controller('OrderController', function(OrderModel) {
    var ctrl = this;
	ctrl.items = OrderModel.getItems();
	ctrl.cart = {};
});

orderModule.directive('item', function() {
    return {
        scope: true,
        replace: true,
        template: '<div>{{::item.title}}: {{::item.count}} left</div>'
    }
});

orderModule.directive('shopping', function() {
    return {
        scope: true,
        replace: true,
        template: '<div>{{shopping.title}}: {{item.count}} left</div>'
    }
});
