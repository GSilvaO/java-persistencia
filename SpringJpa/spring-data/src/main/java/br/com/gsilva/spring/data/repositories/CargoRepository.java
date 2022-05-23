package br.com.gsilva.spring.data.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.gsilva.spring.data.models.Cargo;

public interface CargoRepository extends CrudRepository<Cargo, Integer> {

}
