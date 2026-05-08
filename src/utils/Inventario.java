package utils;
public class Inventario {
    public double calcularTotalInventario(int numeroProductos, double precioUnitario,
                                          int mesCatalogo, int diasDesdeUltimaVenta, int stockActual, String tipoCategoria) {
// Cálculo del valor base del inventario
        double valorBase = numeroProductos * precioUnitario;
// Descuento por antigüedad (si llevás de 12 meses
// en catálogo)
        double descuentoAntiguedad = calcularDescuentoAntiguedad(mesCatalogo, valorBase);
// Penalización por baja rotación (más de 60 días sin vender)
        double penalRot = calcularPenalizacionRotacion(diasDesdeUltimaVenta, valorBase);
// Bonificación por stock alto (más de 100 unidades)
        double bonifStock = calcularBonificacionStock(stockActual, valorBase);
// Ajuste por tipo de categoría
        double ajusteCat = calcularAjusteCategoria(tipoCategoria, valorBase);
// Cálculo final
        return valorBase - descuentoAntiguedad - penalRot + bonifStock + ajusteCat;
    }

    private static double calcularAjusteCategoria(String tipoCategoria, double valorBase) {
        double ajusteCat = 0;
        if (tipoCategoria.equals("premium")) {
            ajusteCat = valorBase * 0.20;
        } else if (tipoCategoria.equals("basica")) {
            ajusteCat = -valorBase * 0.05;
        }
        return ajusteCat;
    }

    private static double calcularBonificacionStock(int stockActual, double valorBase) {
        double bonifStock = 0;
        if (stockActual > 100) {
            bonifStock = valorBase * 0.05;
        }
        return bonifStock;
    }

    private static double calcularPenalizacionRotacion(int diasDesdeUltimaVenta, double valorBase) {
        double penalRot = 0;
        if (diasDesdeUltimaVenta > 60) {
            penalRot = valorBase * 0.10;
        }
        return penalRot;
    }

    private static double calcularDescuentoAntiguedad(int mesCatalogo, double valorBase) {
        double descuentoAntiguedad = 0;
        if (mesCatalogo > 12) {
            descuentoAntiguedad = valorBase * 0.15;
        }
        return descuentoAntiguedad;
    }
}