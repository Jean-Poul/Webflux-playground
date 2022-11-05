package dk.jplm.reactive.webfluxdemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono() {
        Mono<?> monoString = Mono.just("Mono test")
                .then(Mono.error(new RuntimeException("Exception occured")))
                .log();
        monoString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFLux() {
        Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Hibernate", "Microserivce")
                .concatWithValues("AWES")
                .concatWith(Flux.error(new RuntimeException("Exception occured")))
                .concatWithValues("Kubernetes")
                .log();
        fluxString.subscribe(System.out::println);
    }
}
