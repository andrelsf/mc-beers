package br.dev.multicode.mcbeers.entities;

import br.dev.multicode.mcbeers.api.http.requests.BeerRequest;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mcbeers")
public class Beer {

  @Id
  @Column(name = "beer_id", length = 37, nullable = false)
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private float alcoholContent;

  @Column(nullable = false)
  private String ingredients;

  @Column(nullable = false)
  private BigDecimal price;

  @CreationTimestamp
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  private ZonedDateTime updatedAt;

  public void fillWith(final BeerRequest beerRequest) {
    setName(beerRequest.getName());
    setAlcoholContent(beerRequest.getAlcoholContent());
    setIngredients(beerRequest.getIngredients());
    setPrice(beerRequest.getPrice());
  }
}
