package demo.client;

import demo.dto.CurrencyPriceDTO;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.UriBuilder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.net.URI;
import java.util.Map;
import java.util.Set;

public class CurrencyPriceClientImpl implements CurrencyPriceClient {

    @Override
    public CurrencyPriceDTO getPriceByPair(String pair) {
        return null;
    }

    @Override
    public Set<Extension> getById(String id) {
        return Set.of();
    }

    @Override
    public void close() {

    }

    @Override
    public WebTarget target(String s) {
        return null;
    }

    @Override
    public WebTarget target(URI uri) {
        return null;
    }

    @Override
    public WebTarget target(UriBuilder uriBuilder) {
        return null;
    }

    @Override
    public WebTarget target(Link link) {
        return null;
    }

    @Override
    public Invocation.Builder invocation(Link link) {
        return null;
    }

    @Override
    public SSLContext getSslContext() {
        return null;
    }

    @Override
    public HostnameVerifier getHostnameVerifier() {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public Client property(String s, Object o) {
        return null;
    }

    @Override
    public Client register(Class<?> aClass) {
        return null;
    }

    @Override
    public Client register(Class<?> aClass, int i) {
        return null;
    }

    @Override
    public Client register(Class<?> aClass, Class<?>... classes) {
        return null;
    }

    @Override
    public Client register(Class<?> aClass, Map<Class<?>, Integer> map) {
        return null;
    }

    @Override
    public Client register(Object o) {
        return null;
    }

    @Override
    public Client register(Object o, int i) {
        return null;
    }

    @Override
    public Client register(Object o, Class<?>... classes) {
        return null;
    }

    @Override
    public Client register(Object o, Map<Class<?>, Integer> map) {
        return null;
    }
}
