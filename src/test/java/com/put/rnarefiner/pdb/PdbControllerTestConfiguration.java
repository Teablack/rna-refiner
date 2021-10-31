package com.put.rnarefiner.pdb;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;

@TestConfiguration
public class PdbControllerTestConfiguration {

    @LocalServerPort
    private int port;

    final String addressApiPdbTemplate;

    public PdbControllerTestConfiguration() {
        String addressTemplate = String.format("http://localhost:%d", port) + "%s";

        addressApiPdbTemplate = String.format(addressTemplate, "/api/pdb/{%s}");
    }

    @Bean
    public String addressApiPdbTemplate() {
        return addressApiPdbTemplate;
    }

    @Bean
    public MockMultipartFile pdb() {
        return null;    //TODO
    }

}
