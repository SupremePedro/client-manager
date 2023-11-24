package com.example.clientmanager.repository.impl;

import com.example.clientmanager.repository.ClientRepository;
import com.example.clientmanager.response.ClientResponse;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class InMemoryClientRepository implements ClientRepository {

  private static final String CSV_FILE_PATH = "file.csv";
  public static final String CAN_SHARE_NAME_CODE = "1";
  private final Map<String, ClientResponse> clients = new HashMap<>();

  public InMemoryClientRepository() {
    populateClientsFromCsv();
  }

  @Override
  public List<ClientResponse> findByCodeIn(List<String> clientCodes) {
    return clients.entrySet()
        .stream()
        .filter(x -> clientCodes.contains(x.getKey()))
        .map(Map.Entry::getValue)
        .toList();
  }

  private void populateClientsFromCsv() {
    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CSV_FILE_PATH);
         CSVReader reader = new CSVReaderBuilder(new InputStreamReader(inputStream))
             .withSkipLines(1)
             .build()) {
      List<String[]> rows = reader.readAll();

      for (String[] row : rows) {
        String clientCode = row[0];
        boolean shareName = CAN_SHARE_NAME_CODE.equals(row[1]);
        ClientResponse clientResponse = new ClientResponse(clientCode, true, shareName);

        clients.put(clientCode, clientResponse);
      }
    } catch (IOException | CsvException e) {
      log.error("Exception during population from CSV file ...", e);
    }
  }
}
