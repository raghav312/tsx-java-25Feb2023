# tsx-java-25Feb2023

<h2>Github Webhook with Spring Boot</h2>
Steps to run the appication<br>

1. Clone and Build the repo. Run the application. <br>
2. Open cmd in NGROK download folder. Run the command 'ngrok http 8080'. This wil create a globally accessible tunnel to use the app running on localhost.<br>
3. Copy the https link generated.<br>
4. Goto to the github repo you want to hook. Goto the Setting -> Webhook -> Select 'Let me select individual events' -> Select the events -> Paste the  { coped link  + '\webhook' } in URL form -> Save changes. <br>
5. Check in cmd the initial ping from GitHub to the app. Should return status ok or code 200 generally.

<h3> Webhook API </h3>
1. Push to or Pull from the github repo.<br>
2. Check the spring boot application console logs. You should be able to see logs which include event info from github. <br>

<h3> Commit API </h3>
1. Open postman and make a POST request to http://localhost:8080/commits with request body <br> { "Owner": "repo_owner_name" ,"repo":"repo_name" } in JSON format.<br>
2. You should be able to see all the commits of the said repo in application console logs.<br>
