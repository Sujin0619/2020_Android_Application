package edu.skku.map.myfirstswplab11;

import java.util.HashMap;
import java.util.Map;

public class FirebasePost {
    public String title;
    public String owner;
    public String contents;

    public FirebasePost(){

    }

    public FirebasePost(String title, String owner, String contents){
        this.title = title;
        this.owner=owner;
        this.contents=contents;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("owner", owner);
        result.put("contents", contents);

        return result;
    }
}
