(function() {
  
  'use strict';

  angular
    .module( 'formApp' )
    .controller( 'FormController', FormController );

  FormController.$inject = [ '$scope' ];

  function FormController( $scope ) {

    var vm = this;

    ///////////// Properties
    vm.customer = {};

    ///////////// Public Methods
    vm.save = save;

    ///////////// Private Methods

    /////////////
    function save( data ) {

      alert( 'Sucesso no envio! '+data.name );
      //alert( JSON.stringify( data, null, 2 ));
      
      vm.customer = {};

      // we need $scope to use this method
      $scope.customerForm.$setPristine();

    }

  }

}());