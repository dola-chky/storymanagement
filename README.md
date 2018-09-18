# storymanagement

This is a story management application.You can create, update and delete stories here. Initially I have created rest api endpoint for create, update and delete.You can create and update a story from both 
json or plain text format. Third party users can also consume a story in plain text or json format. As the applicationUser interface is still in progress you can test the endpoints by using rest client.
Follow the instructions below to test the endpoint:

# to get a list of stories
URl: /api/stories

Method: GET



# to create a new story from json format
URL: /api/story/

Method: POST

Request Body Content Type: application/json

Request Body Example: 

{"title":"Coomet robot Philae phones home again",
 "storyBody":"Europe’s comet lander has again been in touch with Earth. The Philae probe made three short contacts of about 10 seconds each at roughly 2130 GMT on Sunday. Controllers at the European Space Agency said the contacts were briefer than they had hoped, but proved the little robot was in encouragingly good health after its seven-month slumber. Philae landed on Comet 67P in November and worked for 60 hours before its battery ran flat.",
 "publishedDate":"15-09-2017"}
 
#to create a new story from text/plain format
URL:/api/story/

Method: POST

Request Body Content Type: text/plain

Request Body Example: 

title:Comet robot Philae phones home again

storyBody:Europe’s comet lander has again been in touch with Earth. The Philae probe made three short contacts of about 10 seconds each at roughly 2130 GMT on Sunday. Controllers at the European Space Agency said the contacts were briefer than they had hoped, but proved the little robot was in encouragingly good health after its seven-month slumber. Philae landed on Comet 67P in November and worked for 60 hours before its battery ran flat.

publishedDate:15-09-2017

#to get a story in json format
URL: /api/story/1

Method: GET

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

Request Header: Accept: text/plain

Example Response:

Title:Coomet robot Philae phones home again,

Body:Europe’s comet lander has again been in touch with Earth. The Philae probe made three short contacts of about 10 seconds each at roughly 2130 GMT on Sunday. Controllers at the European Space Agency said the contacts were briefer than they had hoped, but proved the little robot was in encouragingly good health after its seven-month slumber. Philae landed on Comet 67P in November and worked for 60 hours before its battery ran flat.,

Published Date:2017-09-15 00:00:00.0

#to update a story from json format
URL:/api/story/1

Method: PUT

Request Body Content Type: application/json

Request Body Example:
{"title":"Coomet robot Philae phones home again","storyBody":"Europe’s comet lander has again been in touch with Earth. The Philae probe made three short contacts of about 10 seconds each at roughly 2130 GMT on Sunday. Controllers at the European Space Agency said the contacts were briefer than they had hoped, but proved the little robot was in encouragingly good health after its seven-month slumber. Philae landed on Comet 67P in November and worked for 60 hours before its battery ran flat.","publishedDate":"15-09-2017"}

#to update a story from text format
URL:/api/story/1

Method: PUT

Request Body Content Type: text/plain

Request Body Example:

storyId:1

title:Comet robot Philae phones home again

storyBody:Europe’s comet lander has again been in touch with Earth. The Philae probe made three short contacts of about 10 seconds each at roughly 2130 GMT on Sunday. Controllers at the European Space Agency said the contacts were briefer than they had hoped, but proved the little robot was in encouragingly good health after its seven-month slumber. Philae landed on Comet 67P in November and worked for 60 hours before its battery ran flat.

publishedDate:15-09-2017

#to delete a story
URL: /api/story/1

Method: DELETE

Response: 204 no content

