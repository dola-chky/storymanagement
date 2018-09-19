angular.module('myApp')
// Creating the Angular Controller
    .controller('RegisterController', function ($http, $scope, $state, AuthService) {
        $scope.submit = function () {
            $http.post('register', $scope.appUser).success(function (res) {
                $scope.appUser = null;
                $scope.confirmPassword = null;
                $scope.register.$setPristine();
                $scope.message = "Registration successfull !";
                /*$rootScope.$broadcast('RegistrationSuccessful');*/
                $state.go('login');

            }).error(function (error) {
                $scope.message = error.message;
            });
        };
    });
