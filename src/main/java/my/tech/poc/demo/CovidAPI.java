package my.tech.poc.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "covi-api", url = "https://covid19-brazil-api.vercel.app", path = "/api/report/v1/brazil/uf")
public interface CovidAPI {

    @GetMapping("/{uf}")
    CovidDTO getInfoByUF(@PathVariable String uf);

}