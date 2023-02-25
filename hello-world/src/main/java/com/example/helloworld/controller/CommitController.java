package com.example.helloworld.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.helloworld.models.User;

@RestController

public class CommitController {
    //global Logger
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    //Make a Post request on http://localhost:8080/commits with input = > {
    //    "Owner":"adfsdf",
    //    "repo": "adafsdf"
    // }
    @PostMapping ("/commits")//This function will get the owner and repo and pass to another method
    public void viewCommits(@RequestBody User res) throws IOException {
        try {
            LOGGER.log(Level.INFO, "New Log from commits:");
            LOGGER.log(Level.INFO, res.Owner.toString() + res.repo.toString());
            getCommit(res.Owner, res.repo);
        }catch (Exception e){
            LOGGER.log(Level.FINEST, e.getMessage());
        }
    }


    // make the get request to GitHub to get the commit list and LOG it.
    public void getCommit(String owner , String repo) {
        try {
            URL url = new URL("https://api.github.com/repos/" + owner + "/" + repo + "/commits");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            LOGGER.log(Level.INFO,"Response code :-> " + status );
            if (status < 299) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                con.disconnect();
                LOGGER.log(Level.INFO,"Response Message for commit request: " + content.toString());
            } else {
                throw new Exception("Error with code" + status);
            }
        }catch (Exception e){
            System.out.println("Error Occured" + e.getMessage());
            LOGGER.log(Level.INFO,e.getMessage());
        }
    }
}
