package com.trustrace.redditClone_backEnd.Service;

import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.model.changestream.FullDocumentBeforeChange;
import com.trustrace.redditClone_backEnd.model.Post;
import com.trustrace.redditClone_backEnd.model.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.*;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChangeStreamAction {
    private final MongoTemplate mongoTemplate;

    @PostConstruct
    public void setMessageListenerContainer() {
      MessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer(mongoTemplate);
      messageListenerContainer.start();
        log.info("Start Listener");
        ChangeStreamRequest.ChangeStreamRequestOptions changeStreamRequestOptions = new ChangeStreamRequest.ChangeStreamRequestOptions("RedditCloneApp", "User", ChangeStreamOptions.builder().fullDocumentLookup(FullDocument.UPDATE_LOOKUP).build());

        MessageListener<ChangeStreamDocument<Document>, User> listener = message -> {
            System.out.println(message);
        };
        messageListenerContainer.register(new ChangeStreamRequest<>(listener,changeStreamRequestOptions), User.class);

        messageListenerContainer.stop();
    }
}
