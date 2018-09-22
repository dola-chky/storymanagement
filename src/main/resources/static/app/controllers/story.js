angular.module('myApp')
    .controller('StoryController', function ($http, $scope, $filter, AuthService) {
        var edit = false;
        $scope.buttonText = 'Create';
        $scope.stories = [];
        $scope.searchTitle = "";
        var init = function () {
            $http.get('api/stories').success(function (res) {
                var stories = res;
                angular.forEach(stories,function (story) {
                    var publishedDate = $filter('date')(new Date(story.publishedDate),'MM/dd/yyyy');
                    story.publishedDate = publishedDate;
                });

                $scope.stories = stories;
                $scope.filteredStories = [];
                $scope.currentPage = 1;
                $scope.maxSize = Math.ceil(($scope.stories.length)/5);
                $scope.figureOutStoriesToDisplay();
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

        $scope.figureOutStoriesToDisplay = function() {
            var begin = (($scope.currentPage - 1) * 5);
            var end = begin + 5;
            $scope.filteredStories = $scope.stories.slice(begin, end);
        };

        $scope.pageChanged = function() {
            $scope.figureOutStoriesToDisplay();
        };

        $scope.initEdit = function (story) {
            edit = true;
            $scope.isNewMode = true;
            $scope.story = story;
            var publishedDate = new Date(story.publishedDate);
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
        $scope.search = function (searchTitle) {
            if(searchTitle == null || searchTitle == ""){
                $scope.message = "please enter a title";
                alert("Please enter a title")
                return;
            }
            $http.get('api/stories/search/'+searchTitle).success(function (res) {
                var stories = res;
                $scope.stories = stories;
                $scope.filteredStories = [];
                $scope.currentPage = 1;
                $scope.maxSize = Math.ceil(($scope.stories.length)/5);
                $scope.figureOutStoriesToDisplay();
            })
        };

        $scope.submit = function () {
            if (edit) {
                editStory($scope.story);
            } else {
                addStory();
            }
        };

        $scope.cancel = function () {
            $scope.isNewMode = false;
        };
        init();

    });
