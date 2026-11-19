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
class ProductoBusquedaTest {

    @Autowired
    private ProductoService productoService;

    @Test
    void debeBuscarProductosPorNombreSinDistinguirMayusculas() {

        Producto producto = new Producto(
                "Teclado Mecanico HU05",
                "Teclado para prueba de búsqueda",
                "Accesorios",
                new BigDecimal("85000.00"),
                12
        );

        productoService.registrar(producto);

        List<Producto> resultados =
                productoService.buscarPorNombre("teclado mecanico");

        assertFalse(resultados.isEmpty());

        assertTrue(resultados.stream()
                .anyMatch(p -> p.getNombre().equals("Teclado Mecanico HU05")));
    }

    @Test
    void debeBuscarProductosPorCategoriaSinDistinguirMayusculas() {

        Producto producto = new Producto(
                "Mouse HU05",
                "Mouse para prueba de categoría",
                "Perifericos",
                new BigDecimal("45000.00"),
                20
        );

        productoService.registrar(producto);

        List<Producto> resultados =
                productoService.buscarPorCategoria("perifericos");

        assertFalse(resultados.isEmpty());

        assertTrue(resultados.stream()
                .anyMatch(p -> p.getNombre().equals("Mouse HU05")));
    }

    @Test
    void noDebeEncontrarProductosConNombreInexistente() {

        List<Producto> resultados =
                productoService.buscarPorNombre("ProductoInexistenteHU05");

        assertTrue(resultados.isEmpty());
    }
}