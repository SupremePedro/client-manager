package com.example.clientmanager.controller;

import com.example.clientmanager.request.ClientRequest;
import com.example.clientmanager.response.ClientResponse;
import com.example.clientmanager.service.impl.ClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {

  private final ClientServiceImpl clientService;

  @PostMapping("/search-clients")
  public List<ClientResponse> getClientsDetails(@RequestBody ClientRequest clientRequest) {
    return clientService.getClientsDetails(clientRequest);
  }
}
