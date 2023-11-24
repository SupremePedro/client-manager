package com.example.clientmanager.service.impl;

import com.example.clientmanager.repository.ClientRepository;
import com.example.clientmanager.request.ClientRequest;
import com.example.clientmanager.response.ClientResponse;
import com.example.clientmanager.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

  public List<ClientResponse> getClientsDetails(ClientRequest clientRequest) {
    Map<String, ClientResponse> existingClients = clientRepository.findByCodeIn(clientRequest.getClientCodes())
        .stream()
        .collect(Collectors.toMap(ClientResponse::clientCode, Function.identity()));

    return clientRequest.getClientCodes().stream()
        .map(x -> existingClients.getOrDefault(x, buildUnknownClientResponse(x)))
        .collect(Collectors.toList());
  }

  private ClientResponse buildUnknownClientResponse(String clientCode) {
    return new ClientResponse(clientCode, false, false);
  }
}
