package com.pfe.Service;

import com.pfe.entites.Chat;
import com.pfe.entites.ChatResponse;
import com.pfe.repostories.ChatRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.List;
@Slf4j
@ApplicationScoped
public class ChatService {

    @Inject
    ChatRepository chatRepository;

    @Inject
    MessageService messageService;

    public Multi<Chat> findAll() {
        return chatRepository.streamAll();
    }

    public Uni<Chat> findOne(@PathParam("id") String id) {
        return chatRepository.findById(new ObjectId(id)).onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }
    public Uni<ChatResponse> findChatBetwenUserAndDestinateur(ChatResponse chat) {
        return chatRepository.findChatBetwenUserAndDestinateur(chat)
                .chain(chat1 ->
                {
                    if (chat1 != null) {
                        log.info("FOUNDED CHAT {}",chat1);
                        return messageService.findMessagesBetwenUserAndDestinateur(chat1)
                                .map(messages ->
                                {
                                    log.info("FOUNDED Messages of chat {}",messages);

                                    return ChatResponse.builder()
                                            .id(chat1.getId().toString())
                                            .expediteurId(chat1.getExpediteurId())
                                            .destinataireId(chat1.getDestinataireId())
                                            .messages(messages)
                                            .build();
                                });
                    } else {
                        return Uni.createFrom().nullItem();
                    }
                });
    }
    public Uni<Response> addChat(ChatResponse u) {
        return     chatRepository.findChatBetwenUserAndDestinateur(u)
                .chain(chat -> {
                    log.info(  "chat : {}", chat);
                    if(chat ==null ){
                    return  messageService.addMessage(u.getMessages().get(0)).chain(message -> {
                        return chatRepository.persist(Chat.builder().destinataireId(u.getDestinataireId()).expediteurId(u.getExpediteurId()).build());
                    });
                }
                else
                    {
                        log.info("chat before add Message {} ",u);

                        return  messageService.addMessage(u.getMessages().get(u.getMessages().size()-1)).invoke(message ->  log.info("Message added to chat {}",message));
                    }
                })
      .map(response -> Response.status(200).entity(response).build());
        }



    public Uni<Response> updateUser(Chat u) {
        return chatRepository
                .findById(u.getId())
                .call(product -> chatRepository.update(u))
                .map(it -> Response.status(Response.Status.OK).entity(it).build())
                .ifNoItem()
                .after(Duration.ofSeconds(7))
                .recoverWithUni(Uni.createFrom().item(Response.noContent().build()));

    }

    public Uni<Response> deleteChat(String id) {

        return chatRepository
                .deleteById(new ObjectId(id)).map(it -> Response.status(Response.Status.OK).entity("{\"message\": \" user Deleted successfully\"}").build());
    }



}