package com.pfe.Service;

import com.pfe.entites.Chat;
import com.pfe.entites.ChatResponse;
import com.pfe.entites.Message;
import com.pfe.repostories.ChatRepository;
import com.pfe.repostories.MessageRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.List;

@ApplicationScoped
public class MessageService {

    @Inject
    MessageRepository messageRepository;


    public Multi<Message> findAll() {
        return messageRepository.streamAll();
    }

    public Uni<Message> findOne(@PathParam("id") String id) {
        return messageRepository.findById(new ObjectId(id)).onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }

    public Uni<Message> addMessage(Message u) {
        return messageRepository.persist(u);
    }


    public Uni<List<Message>> findMessagesBetwenUserAndDestinateur(Chat chat) {

        return messageRepository.findByChatId(chat);

    }

    public Uni<Response> deleteMessage(String id) {

        return messageRepository
                .deleteById(new ObjectId(id)).map(it -> Response.status(Response.Status.OK).entity("{\"message\": \" user Deleted successfully\"}").build());
    }


}
