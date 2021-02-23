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
import vn.phh.product.model.Product;
import vn.phh.product.repository.product.ProductRepository;
import vn.phh.product.service.ProductService;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @MockBean
    ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Before
    public void setUp() {
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

    }

    @Test
    public void testSizeFindAllProduct() {
        Assert.assertEquals(10, productService.findAll().size());
    }
}
