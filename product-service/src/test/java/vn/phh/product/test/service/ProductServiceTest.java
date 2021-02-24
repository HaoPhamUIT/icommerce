package vn.phh.product.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import vn.phh.product.dto.ClientDTO;
import vn.phh.product.dto.ProductDTO;
import vn.phh.product.model.Product;
import vn.phh.product.repository.product.ProductRepository;
import vn.phh.product.service.ClientService;
import vn.phh.product.service.ProductService;
import vn.phh.product.service.impl.ClientServiceImpl;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @MockBean
    ProductRepository productRepository;

    @MockBean
    ClientServiceImpl clientService;

    @Autowired
    private ProductService productService;

    @Before
    public void setUp() {
        Mockito.when(productRepository.save(any(Product.class)))
                .thenReturn(new Product(
                        "userId-test",
                        "Giày thể thao nam",
                        200000,
                        "nike",
                        "xanh" ,
                        null,
                        10,
                        null,
                        null));

        Mockito.when(productRepository.findAll())
                .thenReturn(IntStream.range(0, 10)
                        .mapToObj(i -> new Product(
                                "id"+ i,
                                "name-" + i,
                                 i,
                                "brand-" + i,
                                "colour-" + i,
                                null,
                                i,
                                null,
                                null))
                        .collect(Collectors.toList()));

        Mockito.when(clientService.get()).thenReturn(
                new ClientDTO("userId-test", "haopham", "dev"));
    }

    @Test
    public void testSizeAddProduct() {
        ProductDTO productExpected = new ProductDTO(
                "userId-test",
                "Giày thể thao nam",
                200000,
                "nike",
                "xanh" ,
                null,
                10,
                 null,
                null);

        ProductDTO productActual = new ProductDTO(
                "userId-test",
                "Giày thể thao nam",
                200000,
                "nike",
                "xanh" ,
                null,
                10,
                null,
                null);

        Assert.assertEquals(productExpected, productService.add(productActual));
    }

    @Test
    public void testSizeFindAllProduct() {
        Assert.assertEquals(10, productService.findAll().size());
    }
}
