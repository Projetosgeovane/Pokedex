package com.werbflux.pokedex.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokemonRepository extends ReactiveMongoRepository<com.werbflux.pokedex.model.Pokemon, String> {
}
