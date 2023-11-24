package com.example.clientmanager.service;

import com.example.clientmanager.request.ClientRequest;
import com.example.clientmanager.response.ClientResponse;

import java.util.List;

public interface ClientService {

  List<ClientResponse> getClientsDetails(ClientRequest clientRequest);
}
