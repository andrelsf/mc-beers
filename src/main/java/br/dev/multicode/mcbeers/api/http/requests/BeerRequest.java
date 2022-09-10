package br.dev.multicode.mcbeers.api.http.requests;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerRequest {

  @NotBlank
  private String name;

  @NotNull
  private Double alcoholContent;

  @NotBlank
  private String ingredients;

  @NotNull
  private BigDecimal price;

}
