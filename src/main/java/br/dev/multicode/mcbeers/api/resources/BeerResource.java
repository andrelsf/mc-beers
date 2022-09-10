package br.dev.multicode.mcbeers.api.resources;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.dev.multicode.mcbeers.api.http.requests.BeerRequest;
import br.dev.multicode.mcbeers.api.http.responses.BeerResponse;
import br.dev.multicode.mcbeers.services.BeerService;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(
  value = "/beers",
  consumes = APPLICATION_JSON_VALUE,
  produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BeerResource {

  private final BeerService beerService;

  @GetMapping
  public ResponseEntity<List<BeerResponse>> getAll(
    @RequestParam("offset") @PositiveOrZero @NotNull final Integer offset,
    @RequestParam("limit") @Positive @NotNull @Max(50) final Integer limit)
  {
    final Page<BeerResponse> beers = beerService.findAll(offset, limit);
    return ResponseEntity.ok(beers.getContent());
  }

  @GetMapping("/{beerId}")
  public ResponseEntity<BeerResponse> getById(@PathVariable final UUID beerId) {
    final BeerResponse beerResponse = beerService.findBeerById(beerId.toString());
    return ResponseEntity.ok(beerResponse);
  }

  @PostMapping
  public ResponseEntity<Void> postBeer(@RequestBody @Valid final BeerRequest beerRequest) {
    final String beerId = beerService.create(beerRequest);
    final URI uriLocation = UriComponentsBuilder.fromUriString("/api/v1/beers/{beerId}")
      .buildAndExpand(beerId)
      .toUri();
    return ResponseEntity.status(HttpStatus.CREATED)
      .header("resourceId", beerId)
      .location(uriLocation)
      .build();
  }

  @PutMapping("/{beerId}")
  public ResponseEntity<Void> putBeer(@PathVariable final UUID beerId, @RequestBody @Valid final BeerRequest putBeerRequest) {
    beerService.update(beerId.toString(), putBeerRequest);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping("/{beerId}")
  public ResponseEntity<Void> deleteBeer(@PathVariable final UUID beerId) {
    beerService.delete(beerId.toString());
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
