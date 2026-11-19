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
class ProductoEliminacionTest {

    @Autowired
    private ProductoService productoService;

    @Test
    void debeEliminarProductoCorrectamente() {

        Producto producto = new Producto(
                "Producto para eliminar HU04",
                "Producto creado para probar la eliminación",
                "Pruebas",
                new BigDecimal("15000.00"),
                6
        );

        Producto registrado = productoService.registrar(producto);

        assertNotNull(productoService.buscarPorId(registrado.getId()));

        productoService.eliminar(registrado.getId());

        assertFalse(
                productoService.listar().stream()
                        .anyMatch(p -> p.getId().equals(registrado.getId()))
        );
    }

    @Test
    void debeEliminarUnicamenteElProductoSeleccionado() {

        Producto producto1 = new Producto(
                "Producto A HU04",
                "Primer producto de prueba",
                "Pruebas",
                new BigDecimal("10000.00"),
                5
        );

        Producto producto2 = new Producto(
                "Producto B HU04",
                "Segundo producto de prueba",
                "Pruebas",
                new BigDecimal("20000.00"),
                10
        );

        Producto registrado1 = productoService.registrar(producto1);
        Producto registrado2 = productoService.registrar(producto2);

        productoService.eliminar(registrado1.getId());

        assertThrows(
                RuntimeException.class,
                () -> productoService.buscarPorId(registrado1.getId())
        );

        assertNotNull(
                productoService.buscarPorId(registrado2.getId())
        );
    }
}