package br.dev.multicode.mcbeers.repositories;

import br.dev.multicode.mcbeers.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, String> {

}
