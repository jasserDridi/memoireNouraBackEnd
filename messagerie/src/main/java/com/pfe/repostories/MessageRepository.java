package com.pfe.repostories;

import com.pfe.entites.Chat;
import com.pfe.entites.Message;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MessageRepository implements ReactivePanacheMongoRepository<Message> {

    public Uni<List<Message>> findByChatId(Chat chat) {

        return find("chatId = :chatId", Parameters.with("chatId", chat.getId().toString())).list();


    }
}
