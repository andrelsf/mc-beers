package br.dev.multicode.mcbeers.services.impl;

import br.dev.multicode.mcbeers.api.http.requests.BeerRequest;
import br.dev.multicode.mcbeers.api.http.responses.BeerResponse;
import br.dev.multicode.mcbeers.api.utils.Mapper;
import br.dev.multicode.mcbeers.entities.Beer;
import br.dev.multicode.mcbeers.repositories.BeerRepository;
import br.dev.multicode.mcbeers.services.BeerService;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

  private final BeerRepository beerRepository;

  private Beer findById(final String beerId) {
    return beerRepository.findById(beerId)
        .orElseThrow(() ->
            new RuntimeException("Beer not found by ID=".concat(beerId)));
  }

  @Override
  @Transactional(readOnly = true)
  public Set<BeerResponse> getAll() {
    return beerRepository.findAll()
      .stream()
      .map(Mapper::entity2Response)
      .collect(Collectors.toSet());
  }

  @Override
  @Transactional(readOnly = true)
  public BeerResponse getBeerById(String beerId) {
    final Beer beer = findById(beerId);
    return Mapper.entity2Response(beer);
  }

  @Override
  @Transactional
  public String create(BeerRequest beerRequest) {
    final Beer beer = Mapper.createNewEntity(beerRequest);
    beerRepository.save(beer);
    return beer.getId();
  }

  @Override
  @Transactional
  public void delete(final String beerId) {
     final Beer beer = findById(beerId);
     beerRepository.delete(beer);
  }

  @Override
  @Transactional
  public void update(final String beerId, final BeerRequest beerRequest) {
    Beer beer = findById(beerId);
    beer.fillWith(beerRequest);
  }
}
