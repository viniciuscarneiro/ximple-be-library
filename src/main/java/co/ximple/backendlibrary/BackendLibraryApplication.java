package co.ximple.backendlibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BackendLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendLibraryApplication.class, args);
    }

}
