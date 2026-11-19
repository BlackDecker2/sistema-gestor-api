package com.santiago.inventario;

import com.santiago.inventario.model.Producto;
import com.santiago.inventario.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductoConsultaTest {

    @Autowired
    private ProductoService productoService;

    @Test
    void debeConsultarTodosLosProductos() {

        Producto producto = new Producto(
                "Producto consulta HU02",
                "Producto para verificar el listado",
                "Pruebas",
                new BigDecimal("35000.00"),
                12
        );

        Producto registrado = productoService.registrar(producto);

        List<Producto> productos = productoService.listar();

        assertNotNull(productos);
        assertFalse(productos.isEmpty());

        assertTrue(productos.stream()
                .anyMatch(p -> p.getId().equals(registrado.getId())));
    }

    @Test
    void debeConsultarProductoPorId() {

        Producto producto = new Producto(
                "Producto individual HU02",
                "Producto para consultar mediante identificador",
                "Pruebas",
                new BigDecimal("45000.00"),
                8
        );

        Producto registrado = productoService.registrar(producto);

        Producto resultado = productoService.buscarPorId(registrado.getId());

        assertNotNull(resultado);
        assertEquals(registrado.getId(), resultado.getId());
        assertEquals("Producto individual HU02", resultado.getNombre());
        assertEquals(8, resultado.getStock());
    }
}