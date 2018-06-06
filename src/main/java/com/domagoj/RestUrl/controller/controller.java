package com.domagoj.RestUrl.controller;

import com.domagoj.RestUrl.entity.Url;
import com.domagoj.RestUrl.entity.User;
import com.domagoj.RestUrl.service.UrlService;
import com.domagoj.RestUrl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


@RestController
public class controller {
    private static SecureRandom random = new SecureRandom();

    /** different dictionaries used */
    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";

    private static String generatePassword(int len, String dic) {
        String result = "";
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(dic.length());
            result += dic.charAt(index);
        }
        return result;
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UrlService urlService;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public ArrayList<User> getAllStudents(){
        return userService.getAllUsers();
    }

    @RequestMapping(value="/account", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createUser(@RequestBody User userd) throws JSONException {

        boolean alreadyExists = false;
        JSONObject msgJSON = new JSONObject();
        //generates random password, saves user to db
        String password = generatePassword(8, ALPHA + ALPHA_CAPS + NUMERIC);

        ArrayList<User> users = userService.getAllUsers();
        for(User us : users)
            if (us.getUsername().equals(userd.getUsername())) {
                alreadyExists = true;
            }

        if(userd.getUsername() == null || userd.getUsername() == ""){
            msgJSON.put("success", "false");
            msgJSON.put("description", "Invalid username");
        } else if(alreadyExists){
            msgJSON.put("success", "false");
            msgJSON.put("description", "Username already exists");
        } else{
            msgJSON.put("success", "true");
            msgJSON.put("description", "Your account is opened");
            msgJSON.put("password", password);

            userService.addUser(new User(userd.getUsername(), password));
        }


        return msgJSON.toString();
    }

    //============ Url control part =============
    //get all urls
    @RequestMapping(value="/links", method = RequestMethod.GET)
    public ArrayList<Url> getAllUrls(){
        return urlService.getAllUrls();
    }

    //load short url and redirect to longUrl
    @RequestMapping(value="/{shortUrl}", method = RequestMethod.GET)
    public void redirectUrl(@PathVariable("shortUrl") String shortUrl,HttpServletResponse httpServletResponse) throws IOException {
        Url url = urlService.getUrlByShort(shortUrl);
        String longUrl = url.getLongUrl();
        if(longUrl != null) {
            httpServletResponse.sendRedirect(longUrl);
        }else{
            httpServletResponse.sendRedirect("/register");
        }
    }

    //register a URL
    @RequestMapping(value="/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addUrl(@RequestBody Url url) throws JSONException {
        JSONObject msgJSON = new JSONObject();
        String shortUrl = generatePassword(6, ALPHA + ALPHA_CAPS + NUMERIC);

        msgJSON.put("shortUrl", shortUrl);

        urlService.addNewUrl(new Url(url.getLongUrl(), shortUrl, 0));

        return msgJSON.toString();
    }

    //visit statistic
    @RequestMapping(value="/statistic/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllStatistic(@PathVariable("username") String username) throws JSONException {
        ArrayList<Url> urls =  urlService.getAllUrls();
        JSONObject msgJSON = new JSONObject();
        JSONArray msgArray = new JSONArray();

        for(int i = 0; i < urls.size(); ++i){
            Url url =  urls.get(i);
            msgJSON.put(url.getLongUrl(), url.getCounter());
            msgArray.put(msgJSON);
        }

        return msgArray.toString();
    }
}
