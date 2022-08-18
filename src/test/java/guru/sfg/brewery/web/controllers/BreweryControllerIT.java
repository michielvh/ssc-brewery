package guru.sfg.brewery.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BreweryControllerIT extends BaseIT {

    @Test
    void findBreweryAsAnonymous() throws Exception{
        mockMvc.perform(get("/brewery/breweries"))
                .andExpect((status().isUnauthorized()));
    }

    @Test
    void findBreweryAsCustomer() throws Exception{
        mockMvc.perform(get("/brewery/breweries")
                .with(httpBasic("scott", "tiger")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void findBreweryAsAdmin() throws Exception{
        mockMvc.perform(get("/brewery/breweries")
                        .with(httpBasic("spring", "guru")))
                .andExpect(status().isForbidden());
    }

    @Test
    void findBreweryAsUser() throws Exception{
        mockMvc.perform(get("/brewery/breweries")
                        .with(httpBasic("user", "password")))
                .andExpect(status().isForbidden());
    }



    @Test
    void findBreweryAsAnonymousAPI() throws Exception{
        mockMvc.perform(get("/brewery/api/v1/breweries"))
                .andExpect((status().isUnauthorized()));
    }

    @Test
    void findBreweryAsCustomerAPI() throws Exception{
        mockMvc.perform(get("/brewery/api/v1/breweries")
                        .with(httpBasic("scott", "tiger")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void findBreweryAsAdminAPI() throws Exception{
        mockMvc.perform(get("/brewery/api/v1/breweries")
                        .with(httpBasic("spring", "guru")))
                .andExpect(status().isForbidden());
    }

    @Test
    void findBreweryAsUserAPI() throws Exception{
        mockMvc.perform(get("/brewery/api/v1/breweries")
                        .with(httpBasic("user", "password")))
                .andExpect(status().isForbidden());
    }

}
