angular.module('myApp', ['ui.router','ui.bootstrap'])
    .run(function (AuthService, $rootScope, $state) {
        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
            // checking the user is logged in or not
            if (!AuthService.user) {
                // To avoiding the infinite looping of state change we have to add a
                // if condition.
                if (toState.name != 'login' && toState.name != 'register' && toState.name != 'story') {
                    event.preventDefault();
                    $state.go('login');
                }
            }
        });
    });