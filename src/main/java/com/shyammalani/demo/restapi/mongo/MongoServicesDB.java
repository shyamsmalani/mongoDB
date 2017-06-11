package com.shyammalani.demo.restapi.mongo;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.shyammalani.demo.restapi.model.City;
import com.shyammalani.demo.restapi.model.Criteria;
import com.shyammalani.demo.restapi.model.User;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.mongodb.client.model.Filters.*;

@Component
public class MongoServicesDB {

    private MongoCollection<Document> userCollection;

    public MongoServicesDB() {
        final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost"));
        final MongoDatabase database = mongoClient.getDatabase("testapi");
        userCollection = database.getCollection("UserCollection");
    }

    public User addUser(User user) {
        Document document = getUserDocument(user);
        try {
            userCollection.insertOne(document);
            return user;
        } catch (MongoWriteException e) {
            if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("Username already in use: " + user.getId());
                return null;
            }
            throw e;
        }
    }


    public Boolean updateUser(Integer id, User newUser) {
        Document document = getUserDocument(newUser);
        try {
            UpdateResult updateResult = userCollection.updateOne(eq("_id", id), new Document("$set", document));
            if (updateResult.getModifiedCount() == 1) {
                return true;
            }
        } catch (MongoWriteException e) {
            throw e;
        }
        return false;
    }

    public User getUser(Integer id) {
        User user = null;
        FindIterable<Document> documents = userCollection.find(eq("_id", id));
        if (documents != null) {
            for (Document document : documents) {
                user = getUser(document);
            }
        }
        return user;
    }

    public boolean deleteUser(Integer id) {
        DeleteResult deleteResult = userCollection.deleteOne(eq("_id", id));
        if (deleteResult.getDeletedCount() == 1) {
            return true;
        }
        return false;
    }

    public List<User> filterUser(Criteria criteria) {
        List<User> users = new ArrayList<>();
        FindIterable<Document> documents = userCollection.find(and(gte("age", criteria.getAgeGT()),
                lte("age", criteria.getAgeLT()),
                in("favoriteColors", criteria.getFavoriteColors())));

        if (documents != null) {
            for (Document document : documents) {
                User user = getUser(document);
                users.add(user);
            }
        }
        return users;
    }

    private Document getUserDocument(User user) {
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
}
