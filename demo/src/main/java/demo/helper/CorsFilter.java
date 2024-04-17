package demo.helper;

import com.sun.net.httpserver.Headers;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import org.jboss.resteasy.reactive.server.core.ResteasyReactiveRequestContext;
import org.jboss.resteasy.reactive.server.jaxrs.ContainerResponseContextImpl;

import java.io.IOException;


public class CorsFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        ContainerRequestContext requestContext = containerRequestContext;
        MultivaluedMap<String, String> m = requestContext.getHeaders();
        String originString = requestContext.getHeaders().getFirst("Origin");

        requestContext.getHeaders().add("Access-Control-Allow-Origin", originString);
        requestContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        requestContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        requestContext.getHeaders().add(    "Access-Control-Expose-Headers", "Authorization");
        requestContext.getUriInfo();

    }
}
