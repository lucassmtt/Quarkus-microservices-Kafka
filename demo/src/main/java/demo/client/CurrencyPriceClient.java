package demo.client;

import demo.dto.CurrencyPriceDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.client.Client;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestPath;

import java.util.Set;

@RegisterClientHeaders
@RegisterRestClient(baseUri = "https://economia.awesomeapi.com.br")
@RestClient
@ApplicationScoped
public interface CurrencyPriceClient extends Client {

    @GET
    @Path("/{pair}")
    CurrencyPriceDTO getPriceByPair(@PathParam("pair") String pair);

    @GET
    Set<Extension> getById(@QueryParam("id") String id);

}
