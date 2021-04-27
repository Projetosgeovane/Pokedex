package com.werbflux.pokedex;

import com.werbflux.pokedex.model.Pokemon;
import com.werbflux.pokedex.repository.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args);
		System.out.println("Iniciado com sucesso !!!");

	}

		@Bean
	CommandLineRunner init (ReactiveMongoOperations operations, PokemonRepository repository){
		return args -> {
			Flux<Pokemon> pokemonFlux = Flux.just(
					new Pokemon(null, "Pikachu", "Eletríco", "Choque do trovão",9.0),
					new Pokemon(null, "bulbasauro", "Semente", "Grandeza", 8.0),
					new Pokemon(null, "Blastoise", "Marisco", "Torrente", 2.08))
					.flatMap(repository::save);


			pokemonFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);
		};
		}

}
