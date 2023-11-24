package com.example.clientmanager.repository;

import com.example.clientmanager.response.ClientResponse;

import java.util.List;

public interface ClientRepository {
  List<ClientResponse> findByCodeIn(List<String> clientCodes);
}
