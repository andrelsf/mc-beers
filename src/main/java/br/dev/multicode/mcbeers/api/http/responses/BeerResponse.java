package br.dev.multicode.mcbeers.api.http.responses;


import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerResponse {

  private String id;
  private String name;
  private float alcoholContent;
  private String ingredients;
  private BigDecimal price;

}
