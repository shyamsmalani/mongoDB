package com.shyammalani.demo.restapi.mongo;

import com.shyammalani.demo.restapi.model.Criteria;
import com.shyammalani.demo.restapi.model.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MongoServices {
    private Map<Integer, User> userMap = new ConcurrentHashMap<>();

    public User addUser(User user) {
        if (userMap.containsKey(user.getId()))
            return null;
        userMap.put(user.getId(), user);
        return userMap.get(user.getId());
    }

    public boolean updateUser(Integer id, User newUser) {
        userMap.put(id, newUser);
        if (userMap.get(id) != null)
            return true;
        return false;
    }

    public User getUser(Integer id) {
        return userMap.get(id);
    }

    public boolean deleteUser(Integer id) {
        userMap.remove(id);
        return !userMap.containsKey(id);
    }

    public List<User> filterUser(Criteria criteria) {
        Set<Map.Entry<Integer, User>> entries = userMap.entrySet();
        List<User> users = new ArrayList<>();
        boolean addInList = true;

        for (Map.Entry entry : entries) {
            User value = (User) entry.getValue();
            if (!(value.getAge() > criteria.getAgeGT()) ||
                    !(value.getAge() < criteria.getAgeLT())) {
                addInList = false;
            }
            for (String color : criteria.getFavoriteColors()) {
                if (!Arrays.asList(value.getFavoriteColors()).contains(color)) {
                    addInList = false;
                }
            }
            if (addInList)
                users.add(value);
        }
        return users;
    }

    /*private Document getUserDocument(User user) {
        Document document = new Document();
        Document city = new Document();
        city.append("name", user.getCity().getName()).append("pin", user.getCity().getPin());
        document.append("_id", user.getId()).
                append("email", user.getEmail()).
                append("mobileNum", user.getMobileNum()).
                append("firstName", user.getFirstName()).
                append("age", user.getAge()).append("city", city).
                append("favoriteColors", Arrays.asList(user.getFavoriteColors()));
        return document;
    }

    private User getUser(Document document) {
        User user = new User();
        user.setId(document.getInteger("_id"));
        user.setAge(document.getInteger("age"));
        user.setEmail(document.getString("email"));
        user.setFirstName(document.getString("firstName"));
        user.setMobileNum(document.getString("mobileNum"));

        ArrayList<String> colors = (ArrayList<String>) document.get("favoriteColors");
        user.setFavoriteColors(colors.toArray(new String[]{}));

        Document cityDoc = (Document) document.get("city");
        City city = new City();
        city.setName(cityDoc.getString("name"));
        city.setPin(cityDoc.getInteger("pin"));

        user.setCity(city);

        return user;
    }
*/
}
