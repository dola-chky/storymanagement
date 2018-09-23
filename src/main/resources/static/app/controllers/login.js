angular.module('myApp')
    .controller('LoginController', function ($http, $scope, $state, AuthService, $rootScope) {
        $scope.login = function () {

            var data = {
                username: $scope.username,
                password: $scope.password
            };

            $http.post('authenticate', data).success(function (res) {
                $scope.password = null;
                if (res.token) {
                    $scope.message = '';
                    $http.defaults.headers.common['Authorization'] = 'Bearer ' + res.token;
                    AuthService.user = res.user;
                    $rootScope.$broadcast('LoginSuccessful');
                    $state.go('home');
                } else {
                    $scope.message = 'Please provide security token !';
                }
            }).error(function (error) {
                $scope.message = 'Authetication Failed !';
            });
        };
    });
