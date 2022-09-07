package br.dev.multicode.mcbeers.api.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.springframework.data.domain.PageImpl;

public class CacheablePageImpl<T> extends PageImpl<T> {

  @JsonCreator(mode = Mode.PROPERTIES)
  public CacheablePageImpl(@JsonProperty("content") List<T> content) {
    super(content);
  }
}
