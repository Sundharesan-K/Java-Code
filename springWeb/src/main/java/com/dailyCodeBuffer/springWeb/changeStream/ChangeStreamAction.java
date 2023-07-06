package com.dailyCodeBuffer.springWeb.changeStream;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.bson.BsonDocument;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChangeStreamAction {
    @PostConstruct
    public void changeStream(){
        String connecting = "mongodb://localhost:27017";

        String databaseName = "person";
        String collectionName = "person";

        MongoClientSettings clientSettings = MongoClientSettings
                .builder()
                .applyConnectionString(new ConnectionString(connecting))
                .build();

        MongoClient client = MongoClients.create(clientSettings);

        MongoDatabase database = client.getDatabase(databaseName);

        MongoCollection<Document> collection = database.getCollection(collectionName);

        ChangeStreamIterable<Document> changeStream = collection.watch();
        changeStream.fullDocument(FullDocument.UPDATE_LOOKUP);

        try (MongoCursor<ChangeStreamDocument<Document>> cursor = changeStream.iterator()){
            while (cursor.hasNext()){
                ChangeStreamDocument<Document> change = cursor.next();
                Document fullDocumentBeforeChange = change.getFullDocument();
                List<String> removedFields = change.getUpdateDescription().getRemovedFields();
                BsonDocument updateFields = change.getUpdateDescription().getUpdatedFields();
                String type = change.getOperationType().name();
                System.out.println("Received full document before change "+fullDocumentBeforeChange+" operation type "+type+" removeFields "+removedFields
                +" update fields "+updateFields);
            }
        }catch (MongoException e){
            e.printStackTrace();
        }
        client.close();
    }
}
