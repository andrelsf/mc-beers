package br.dev.multicode.mcbeers.api.utils;

import br.dev.multicode.mcbeers.api.http.requests.BeerRequest;
import br.dev.multicode.mcbeers.api.http.responses.BeerResponse;
import br.dev.multicode.mcbeers.entities.Beer;
import java.util.UUID;

public class Mapper {

  public Mapper() {
    throw new RuntimeException("Class only static methods");
  }

  public static BeerResponse entity2Response(Beer beer) {
    return BeerResponse.builder()
      .id(beer.getId())
      .name(beer.getName())
      .alcoholContent(beer.getAlcoholContent())
      .ingredients(beer.getIngredients())
      .price(beer.getPrice())
      .build();
  }

  public static Beer createNewEntity(final BeerRequest beerRequest) {
    return Beer.builder()
      .id(UUID.randomUUID().toString())
      .name(beerRequest.getName())
      .alcoholContent(beerRequest.getAlcoholContent())
      .ingredients(beerRequest.getIngredients())
      .price(beerRequest.getPrice())
      .build();
  }
}
