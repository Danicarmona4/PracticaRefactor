package utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventarioAlmacenTest {

    @Test
    void testAjusteCategoriaPremium() {
        InventarioAlmacen inventario = new InventarioAlmacen();
        double valorBase = 1000.0;
        double resultado = inventario.calcularAjusteCategoria("premium", valorBase);
        assertEquals(200.0, resultado, "El ajuste premium debería ser el 20% del valor base");
    }

    @Test
    void testAjusteCategoriaBasica() {
        InventarioAlmacen inventario = new InventarioAlmacen();
        double valorBase = 1000.0;
        double resultado = inventario.calcularAjusteCategoria("basica", valorBase);
        assertEquals(-50.0, resultado);
    }

    @Test
    void testAjusteCategoriaDesconocida() {
        InventarioAlmacen inventario = new InventarioAlmacen();
        double valorBase = 1000.0;
        double resultado = inventario.calcularAjusteCategoria("estandar", valorBase);
        assertEquals(0.0, resultado, "Una categoría desconocida no debería tener ajuste");
    }
}