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
class ProductoActualizacionTest {

    @Autowired
    private ProductoService productoService;

    @Test
    void debeActualizarProductoCorrectamente() {

        Producto producto = new Producto(
                "Producto original HU03",
                "Producto para prueba de actualización",
                "Pruebas",
                new BigDecimal("30000.00"),
                10
        );

        Producto registrado = productoService.registrar(producto);

        Producto datosActualizados = new Producto(
                "Producto actualizado HU03",
                "Descripción actualizada",
                "Tecnologia",
                new BigDecimal("45000.00"),
                25
        );

        Producto actualizado = productoService.actualizar(
                registrado.getId(),
                datosActualizados
        );

        assertEquals(registrado.getId(), actualizado.getId());
        assertEquals("Producto actualizado HU03", actualizado.getNombre());
        assertEquals("Descripción actualizada", actualizado.getDescripcion());
        assertEquals("Tecnologia", actualizado.getCategoria());
        assertEquals(0, new BigDecimal("45000.00")
                .compareTo(actualizado.getPrecio()));
        assertEquals(25, actualizado.getStock());
    }

    @Test
    void debeConservarLosCambiosAlConsultarProducto() {

        Producto producto = new Producto(
                "Producto persistencia HU03",
                "Prueba de persistencia",
                "Pruebas",
                new BigDecimal("20000.00"),
                5
        );

        Producto registrado = productoService.registrar(producto);

        Producto datosActualizados = new Producto(
                "Producto persistencia actualizado",
                "Información modificada",
                "Accesorios",
                new BigDecimal("28000.00"),
                18
        );

        productoService.actualizar(registrado.getId(), datosActualizados);

        Producto consultado = productoService.buscarPorId(registrado.getId());

        assertEquals("Producto persistencia actualizado", consultado.getNombre());
        assertEquals(18, consultado.getStock());
        assertEquals(0, new BigDecimal("28000.00")
                .compareTo(consultado.getPrecio()));
    }
}