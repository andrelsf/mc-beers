package br.dev.multicode.mcbeers.services;

import br.dev.multicode.mcbeers.api.http.requests.BeerRequest;
import br.dev.multicode.mcbeers.api.http.responses.BeerResponse;
import java.util.Set;
import org.springframework.data.domain.Page;

public interface BeerService {

  void delete(String beerId);
  String create(BeerRequest beerRequest);
  BeerResponse findBeerById(String beerId);
  void update(String beerId, BeerRequest beerRequest);
  Page<BeerResponse> findAll(Integer offset, Integer limit);

}
