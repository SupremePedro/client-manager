package com.example.clientmanager.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ClientRequest {

  List<String> clientCodes;
}
