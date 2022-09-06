package br.dev.multicode.mcbeers.services;

import br.dev.multicode.mcbeers.api.http.requests.BeerRequest;
import br.dev.multicode.mcbeers.api.http.responses.BeerResponse;
import java.util.Set;

public interface BeerService {

  Set<BeerResponse> getAll();
  void delete(String beerId);
  void update(String beerId, BeerRequest beerRequest);
  String create(BeerRequest beerRequest);
  BeerResponse getBeerById(String beerId);

}
