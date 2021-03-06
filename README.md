# storymanagement

This is a story management application.You can create, update and delete stories here. Initially I have created rest api endpoint for create, update and delete.You can create and update a story from both 
json or plain text format. Third party users can also consume a story in plain text or json format. the list of stories is visible to everyone.But only registered and logged in users can create, update and delete stories. A user can only update and delete stories created by self.You can test the endpoints by using rest client.
Later I have added a client application with angularJs to consume the rest api. You can access stories through user interface. There is also registration and login options. Only a logged in user will be able to create, update and delete stories.

#Installation and setup instruction:
Clone down the repository. You will need jdk 1.8 or higher, maven and intellijIdea. I have used h2 in memory databse, so there is no need to install a database.
Go to intelliIdea file menu and select new project from existing source. Create the project as maven project. When the project is ready, open intellij terminal and run the following command:
mvn clean install

After successful completion of this command you will get a jar file in project target folder. Double click the jar and you are ready to use the story management application.

#To start using application:
Open you browser and type localhost:8080

A login page will appear with 'register' and 'stories' button on upper right side. Using login page you can login. I have two default user named 'admin' and 'user'. Password for 'admin' is 'admin' and password for 'user' is 'user'.
After successful login you will be taken to your home page.From home page you can explore the story listing.
You can register a new user by clicking 'register' button. After successful registration you will be redirected to login page.
By clicking stories button on upper right side you can view the story listing without logging in.

#Login Page:

![login page](img/login.jpg)

#Registration Page:

![registration page](img/registration.jpg)

#Home Page:

![home page](img/homepage.jpg)

#Story List Without Login:
Without login create, edit and delete buttons are not visible.

![story without login](img/storylist_without_login.jpg)

#Story List:
After login create button is visible for all user, but edit and delete button is available only for self created stories by user.

![story list 1](img/storylist1.jpg)

#Story List:
The list page has navigation and a page contain maximum five stories.

![story list 2](img/storylist2.jpg)

#Adding New Story:

![add new story](img/addstory.jpg)

#Updating Story:

![edit story](img/editstory.jpg)

# Searching story by title

![search story 1](img/storyByTitle1.jpg)


![search story 2](img/storyByTitle2.jpg)

#Follow the instructions below to test the endpoint using advanced rest client or postman:

#to register a new user
URL: /register

Method: POST

Request Body Content Type: application/json

Request Body Example: 

{"username":"dola","password":"123456"}

#to login a user
URL: /authenticate

Method: POST

Request Body Content Type: application/json

Request Body Example: 

{"username":"dola","password":"123456"}

Response Header: authorization:
                Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb2xhIiwiZXhwIjoxNTM4MTIzNjY5fQ.KUBubUL7enJy5dBgU88joFACKfvwFnVdwqruA4bvG75qo90hDvW6VWMWuN9VxTQ5pUexKvH3VRQonVxp3ffVAg

# to get a list of stories
URl: /api/stories

Method: GET


# to search a story by title
URL: /api/stories/search/{title}
Method: GET

# to create a new story from json format
URL: /api/story/

Method: POST

Request Header: authorization:
                Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb2xhIiwiZXhwIjoxNTM4MTIzNjY5fQ.KUBubUL7enJy5dBgU88joFACKfvwFnVdwqruA4bvG75qo90hDvW6VWMWuN9VxTQ5pUexKvH3VRQonVxp3ffVAg

Request Body Content Type: application/json

Request Body Example: 

{"title":"Coomet robot Philae phones home again",
 "storyBody":"Europe’s comet lander has again been in touch with Earth. The Philae probe made three short contacts of about 10 seconds each at roughly 2130 GMT on Sunday. Controllers at the European Space Agency said the contacts were briefer than they had hoped, but proved the little robot was in encouragingly good health after its seven-month slumber. Philae landed on Comet 67P in November and worked for 60 hours before its battery ran flat.",
 "publishedDate":"15-09-2017"}
 
#to create a new story from text/plain format
URL:/api/story/

Method: POST

Request Header: authorization:
                Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb2xhIiwiZXhwIjoxNTM4MTIzNjY5fQ.KUBubUL7enJy5dBgU88joFACKfvwFnVdwqruA4bvG75qo90hDvW6VWMWuN9VxTQ5pUexKvH3VRQonVxp3ffVAg

Request Body Content Type: text/plain

Request Body Example: 

title:Comet robot Philae phones home again

storyBody:Europe’s comet lander has again been in touch with Earth. The Philae probe made three short contacts of about 10 seconds each at roughly 2130 GMT on Sunday. Controllers at the European Space Agency said the contacts were briefer than they had hoped, but proved the little robot was in encouragingly good health after its seven-month slumber. Philae landed on Comet 67P in November and worked for 60 hours before its battery ran flat.

publishedDate:15-09-2017

#to get a story in json format
URL: /api/story/1

Method: GET

Request Header: authorization:
                Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb2xhIiwiZXhwIjoxNTM4MTIzNjY5fQ.KUBubUL7enJy5dBgU88joFACKfvwFnVdwqruA4bvG75qo90hDvW6VWMWuN9VxTQ5pUexKvH3VRQonVxp3ffVAg

Request Header: Accept: application/json

Example Response:

{
"storyId": 1,
"title": "Coomet robot Philae phones home again",
"storyBody": "Europe’s comet lander has again been in touch with Earth. The Philae probe made three short contacts of about 10 seconds each at roughly 2130 GMT on Sunday. Controllers at the European Space Agency said the contacts were briefer than they had hoped, but proved the little robot was in encouragingly good health after its seven-month slumber. Philae landed on Comet 67P in November and worked for 60 hours before its battery ran flat.",
"publishedDate": "15-09-2017"
}

#to get a story in text format
URL: /api/story/1

Method: GET

Request Header: authorization:
                Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb2xhIiwiZXhwIjoxNTM4MTIzNjY5fQ.KUBubUL7enJy5dBgU88joFACKfvwFnVdwqruA4bvG75qo90hDvW6VWMWuN9VxTQ5pUexKvH3VRQonVxp3ffVAg

Request Header: Accept: text/plain

Example Response:

Title:Coomet robot Philae phones home again,

Body:Europe’s comet lander has again been in touch with Earth. The Philae probe made three short contacts of about 10 seconds each at roughly 2130 GMT on Sunday. Controllers at the European Space Agency said the contacts were briefer than they had hoped, but proved the little robot was in encouragingly good health after its seven-month slumber. Philae landed on Comet 67P in November and worked for 60 hours before its battery ran flat.,

Published Date:2017-09-15 00:00:00.0

#to update a story from json format
URL:/api/story/1

Method: PUT

Request Header: authorization:
                Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb2xhIiwiZXhwIjoxNTM4MTIzNjY5fQ.KUBubUL7enJy5dBgU88joFACKfvwFnVdwqruA4bvG75qo90hDvW6VWMWuN9VxTQ5pUexKvH3VRQonVxp3ffVAg

Request Body Content Type: application/json

Request Body Example:
{"title":"Coomet robot Philae phones home again","storyBody":"Europe’s comet lander has again been in touch with Earth. The Philae probe made three short contacts of about 10 seconds each at roughly 2130 GMT on Sunday. Controllers at the European Space Agency said the contacts were briefer than they had hoped, but proved the little robot was in encouragingly good health after its seven-month slumber. Philae landed on Comet 67P in November and worked for 60 hours before its battery ran flat.","publishedDate":"15-09-2017"}

#to update a story from text format
URL:/api/story/1

Method: PUT

Request Header: authorization:
                Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb2xhIiwiZXhwIjoxNTM4MTIzNjY5fQ.KUBubUL7enJy5dBgU88joFACKfvwFnVdwqruA4bvG75qo90hDvW6VWMWuN9VxTQ5pUexKvH3VRQonVxp3ffVAg

Request Body Content Type: text/plain

Request Body Example:

storyId:1

title:Comet robot Philae phones home again

storyBody:Europe’s comet lander has again been in touch with Earth. The Philae probe made three short contacts of about 10 seconds each at roughly 2130 GMT on Sunday. Controllers at the European Space Agency said the contacts were briefer than they had hoped, but proved the little robot was in encouragingly good health after its seven-month slumber. Philae landed on Comet 67P in November and worked for 60 hours before its battery ran flat.

publishedDate:15-09-2017

#to delete a story
URL: /api/story/1

Method: DELETE

Request Header: authorization:
                Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb2xhIiwiZXhwIjoxNTM4MTIzNjY5fQ.KUBubUL7enJy5dBgU88joFACKfvwFnVdwqruA4bvG75qo90hDvW6VWMWuN9VxTQ5pUexKvH3VRQonVxp3ffVAg

Response: 204 no content

