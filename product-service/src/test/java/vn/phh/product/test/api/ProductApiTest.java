package vn.phh.product.test.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import vn.phh.product.api.ProductAPI;
import vn.phh.product.dto.ProductDTO;
import vn.phh.product.service.CartService;
import vn.phh.product.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductAPI.class)
public class ProductApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    ProductService productService;

    @MockBean
    CartService cartService;

    @MockBean
    KafkaTemplate kafkaTemplate;

    @MockBean
    KafkaProperties kafkaProperties;


    @Test
    public void testSizeFindAllProduct() throws Exception {
        List<ProductDTO> products = IntStream.range(0, 10)
                .mapToObj(i ->  new ProductDTO(
                        "id"+ i,
                        "name-" + i,
                        i,
                        "brand-" + i,
                        "colour-" + i,
                        null,
                        i,
                        null,
                        null))
                .collect(Collectors.toList());

        given(productService.findAll()).willReturn(products);
        mvc.perform( MockMvcRequestBuilders
                .get("/api/v1/product")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", hasSize(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].name", is("name-0")));
    }

    @Test
    public void testCreateProduct() throws Exception {
        ProductDTO productRequestBody = new ProductDTO(
                "userId-test",
                "Giày thể thao nam",
                200000,
                "nike",
                "xanh" ,
                null,
                10,
                null,
                null);

        ProductDTO productMock = new ProductDTO(
                "userId-test",
                "Giày thể thao nam",
                200000,
                "nike",
                "xanh" ,
                null,
                10,
                null,
                null);

        given(productService.add(any(ProductDTO.class))).willReturn(productMock);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonProductRequestBody = ow.writeValueAsString(productRequestBody);
        mvc.perform( MockMvcRequestBuilders
                .post("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonProductRequestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", is("Giày thể thao nam")));
    }
}
