(function () {
    'use strict';

    angular
        .module('app')
        .controller('SearchController', SearchController);

	function SearchController() {
        var vm = this;
        vm.gridOptions = {  enableFiltering: true,
                            columnDefs: [
                            { field: 'Item' },
                            { field: 'Category' },
                            { field: 'Price' },
                            { field: 'location' },
                            { field: 'date'}
                            ],
                            onRegisterApi: function (gridApi) {
                            vm.grid1Api = gridApi;
                            }
                        };
        vm.users = [
                    { Item: "Madhav Sai", Category: "ABB", Price: 10, location: "Nagpur", date: "11/12/2015" },
                    { Item: "Suresh Dasari", Category: "BBB", Price: 30, location: "Chennai", date: "11/12/2015"  },
                    { Item: "Rohini Alavala", Category: "CBB", Price: 29, location: "Chennai", date: "11/12/2015"  },
                    { Item: "Praveen Kumar", Category: "DBB", Price: 25, location: "Bangalore", date: "11/12/2015"  },
                    { Item: "Madhav Sai", Category: "ABB", Price: 10, location: "Nagpur", date: "11/12/2015" },
                    { Item: "Suresh Dasari", Category: "BBB", Price: 30, location: "Chennai", date: "11/12/2015"  },
                    { Item: "Rohini Alavala", Category: "CBB", Price: 29, location: "Chennai", date: "11/12/2015"  },
                    { Item: "Praveen Kumar", Category: "DBB", Price: 25, location: "Bangalore", date: "11/12/2015"  },
                    { Item: "Madhav Sai", Category: "ABB", Price: 10, location: "Nagpur", date: "11/12/2015" },
                    { Item: "Suresh Dasari", Category: "BBB", Price: 30, location: "Chennai", date: "11/12/2015"  },
                    { Item: "Rohini Alavala", Category: "CBB", Price: 29, location: "Chennai", date: "11/12/2015"  },
                    { Item: "Praveen Kumar", Category: "DBB", Price: 25, location: "Bangalore", date: "11/12/2015"  },
                    { Item: "Madhav Sai", Category: "ABB", Price: 10, location: "Nagpur", date: "11/12/2015" },
                    { Item: "Suresh Dasari", Category: "BBB", Price: 30, location: "Chennai", date: "11/12/2015"  },
                    { Item: "Rohini Alavala", Category: "CBB", Price: 29, location: "Chennai", date: "11/12/2015"  },
                    { Item: "Praveen Kumar", Category: "DBB", Price: 25, location: "Bangalore", date: "11/12/2015"  },

                    { Item: "Sateesh Chandra", Category: "EBB", Price: 27, location: "Vizag", date: "11/12/2015"  }
                    ];
        vm.gridOptions.data = vm.users;
		
    }

})();