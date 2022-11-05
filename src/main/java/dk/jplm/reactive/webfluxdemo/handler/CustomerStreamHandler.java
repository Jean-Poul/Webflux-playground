package dk.jplm.reactive.webfluxdemo.handler;


import dk.jplm.reactive.webfluxdemo.dao.CustomerDao;
import dk.jplm.reactive.webfluxdemo.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    @Autowired
    private CustomerDao dao;

    public Mono<ServerResponse> getCustomers(ServerRequest request) {
        Flux<Customer> customerStream = dao.getCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerStream, Customer.class);
    }

}
