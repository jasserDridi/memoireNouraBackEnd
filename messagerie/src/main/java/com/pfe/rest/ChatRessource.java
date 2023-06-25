package com.pfe.rest;

import com.pfe.Service.ChatService;
import com.pfe.Service.MessageService;
import com.pfe.entites.Chat;
import com.pfe.entites.ChatResponse;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Slf4j
@Path("/api/v1/messagerie")
public class ChatRessource {
    @Inject
    ChatService chatService;

    @Inject
    MessageService messageService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Chat> getListUsers() {
        log.info("Received file %s with name %s");
        return chatService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Chat> findOne(@PathParam("id") String id) {

        return chatService.findOne(id);
    }
    @POST
    @Path("/bidirectionalChat")

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ChatResponse> getChatBidirectional(ChatResponse data)  {


        log.info("Received Request {} ",data);

        return chatService.findChatBetwenUserAndDestinateur(data);

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> addUser(ChatResponse data)  {


        log.info("Received Request {} ",data);

        return chatService.addChat(data);

    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteProduct(@PathParam("id") String id) throws NotFoundException {
        return messageService.deleteMessage(id);
    }

}
