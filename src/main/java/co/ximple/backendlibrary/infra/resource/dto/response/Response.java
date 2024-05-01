package co.ximple.backendlibrary.infra.resource.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;

public class Response<T> extends ResponseEntity<T> implements Serializable {
    public Response(HttpStatusCode status) {
        super(status);
    }

    public Response(T body, HttpStatusCode status) {
        super(body, status);
    }

    public Response(MultiValueMap<String, String> headers,
                    HttpStatusCode status) {
        super(headers, status);
    }

    public Response(T body, MultiValueMap<String, String> headers, int rawStatus) {
        super(body, headers, rawStatus);
    }

    public Response(T body, MultiValueMap<String, String> headers, HttpStatusCode statusCode) {
        super(body, headers, statusCode);
    }

    public static <T> Response<T> ok(T body) {
        return new Response<>(body, HttpStatus.OK);
    }
}
