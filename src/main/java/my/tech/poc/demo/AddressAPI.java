package my.tech.poc.demo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-api", url = "https://api.postmon2.com.br", path = "/v1/cep/xxxxxx")
public interface AddressAPI {

    String BACKEND = "backendA";

    @CircuitBreaker(name = AddressAPI.BACKEND, fallbackMethod = "fallback")
    @GetMapping("/{cep}")
    AddressDTO findByCep(@PathVariable String cep);

    default AddressDTO fallback(String cep, Throwable throwable) {
        System.out.println("fallback");
        System.out.println(throwable.getMessage());
        return new AddressDTO();
    }
}