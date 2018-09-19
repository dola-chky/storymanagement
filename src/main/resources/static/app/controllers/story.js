angular.module('myApp')
// Creating the Angular Controller
    .controller('StoryController', function ($http, $scope, $filter, AuthService) {
        var edit = false;
        $scope.buttonText = 'Create';
        var init = function () {
            $http.get('api/stories').success(function (res) {
                var stories = res;
                angular.forEach(stories,function (story) {
                    var publishedDate = $filter('date')(new Date(story.publishedDate),'MM/dd/yyyy');
                    story.publishedDate = publishedDate;
                });

                $scope.stories = stories;
                $scope.user = AuthService.user;
                $scope.storyForm.$setPristine();
                $scope.message = '';
                $scope.story = null;
                $scope.buttonText = 'Create';
                $scope.isNewMode = false;
                $scope.isEditMode = false;

            }).error(function (error) {
                $scope.message = error.message;
            });
        };
        $scope.initEdit = function (story) {
            edit = true;
            $scope.isNewMode = true;
            $scope.story = story;
            var publishedDate = new Date(story.publishedDate);
            console.log(publishedDate);
            //var dateStr = $filter('date')(publishedDate,'MM/dd/yyyy');
            $scope.story.publishedDate = publishedDate;
            $scope.message = '';
            $scope.buttonText = 'Update';
        };
        $scope.initAddStory = function () {
            edit = false;
            $scope.isNewMode = true;
            $scope.story = null;
            $scope.storyForm.$setPristine();
            $scope.message = '';
            $scope.buttonText = 'Create';
        };
        $scope.deleteStory = function (story) {
            $http.delete('api/story/' + story.storyId).success(function (res) {
                $scope.deleteMessage = "Success!";
                init();
            }).error(function (error) {
                $scope.deleteMessage = error.message;
            });
        };
        var editStory = function (story) {
            $http.put('api/story/'+ story.storyId, story).success(function (res) {
                $scope.story = null;
                $scope.storyForm.$setPristine();
                $scope.message = "Editing Success";
                init();
            }).error(function (error) {
                $scope.message = error.message;
            });
        };
        var addStory = function () {
            $http.post('api/story/', $scope.story).success(function (res) {
                $scope.story = null;
                $scope.storyForm.$setPristine();
                $scope.message = "Story Created";
                init();
            }).error(function (error) {
                $scope.message = error.message;
            });
        };
        $scope.submit = function () {
            if (edit) {
                editStory($scope.story);
            } else {
                addStory();
            }
        };
        init();

    });
