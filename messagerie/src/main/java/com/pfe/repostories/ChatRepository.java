package com.pfe.repostories;

import com.pfe.entites.Chat;
import com.pfe.entites.ChatResponse;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped

public class ChatRepository  implements ReactivePanacheMongoRepository<Chat> {


    public Uni<Chat> findChatBetwenUserAndDestinateur(ChatResponse chat) {

            return find("expediteurId = ?1 and destinataireId = ?2 ",chat.getExpediteurId(),chat.getDestinataireId()).firstResult() ;


    }
}
