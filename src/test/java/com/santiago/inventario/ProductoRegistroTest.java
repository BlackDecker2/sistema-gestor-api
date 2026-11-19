package com.santiago.inventario;

import com.santiago.inventario.model.Producto;
import com.santiago.inventario.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductoRegistroTest {

    @Autowired
    private ProductoService productoService;

    @Test
    void debeRegistrarProductoCorrectamente() {

        Producto producto = new Producto(
                "Producto de prueba HU01",
                "Producto creado mediante prueba automatizada",
                "Pruebas",
                new BigDecimal("25000.00"),
                15
        );

        Producto resultado = productoService.registrar(producto);

        assertNotNull(resultado.getId());
        assertEquals("Producto de prueba HU01", resultado.getNombre());
        assertEquals("Pruebas", resultado.getCategoria());
        assertEquals(0, new BigDecimal("25000.00")
                .compareTo(resultado.getPrecio()));
        assertEquals(15, resultado.getStock());
    }
}