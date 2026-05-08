package utils;

public class Inventario {

    // Constantes extraídas para eliminar números mágicos
    public static  int LIMITE_MESES_ANTIGUEDAD = 12;
    public static  double PORCENTAJE_DESCUENTO_ANTIGUEDAD = 0.15;

    public static  int LIMITE_DIAS_ROTACION = 60;
    public static  double PORCENTAJE_PENALIZACION_ROTACION = 0.10;

    public static  int LIMITE_STOCK_ALTO = 100;
    public static  double PORCENTAJE_BONIFICACION_STOCK = 0.05;

    public static  double PORCENTAJE_AJUSTE_PREMIUM = 0.20;
    public static  double PORCENTAJE_AJUSTE_BASICA = -0.05;

    public double calcularTotalInventario(int numeroProductos, double precioUnitario,
                                          int mesCatalogo, int diasDesdeUltimaVenta,
                                          int stockActual, String tipoCategoria) {

        double valorBase = numeroProductos * precioUnitario;

        double descuentoAntiguedad = calcularDescuentoAntiguedad(mesCatalogo, valorBase);
        double penalRot = calcularPenalizacionRotacion(diasDesdeUltimaVenta, valorBase);
        double bonifStock = calcularBonificacionStock(stockActual, valorBase);
        double ajusteCat = calcularAjusteCategoria(tipoCategoria, valorBase);

        return valorBase - descuentoAntiguedad - penalRot + bonifStock + ajusteCat;
    }

    private static double calcularAjusteCategoria(String tipoCategoria, double valorBase) {
        double ajusteCat = 0;
        if (tipoCategoria.equals("premium")) {
            ajusteCat = valorBase * PORCENTAJE_AJUSTE_PREMIUM;
        } else if (tipoCategoria.equals("basica")) {
            // Se usa el valor de la constante que ya incluye el signo negativo
            ajusteCat = valorBase * PORCENTAJE_AJUSTE_BASICA;
        }
        return ajusteCat;
    }

    private static double calcularBonificacionStock(int stockActual, double valorBase) {
        double bonifStock = 0;
        if (stockActual > LIMITE_STOCK_ALTO) {
            bonifStock = valorBase * PORCENTAJE_BONIFICACION_STOCK;
        }
        return bonifStock;
    }

    private static double calcularPenalizacionRotacion(int diasDesdeUltimaVenta, double valorBase) {
        double penalRot = 0;
        if (diasDesdeUltimaVenta > LIMITE_DIAS_ROTACION) {
            penalRot = valorBase * PORCENTAJE_PENALIZACION_ROTACION;
        }
        return penalRot;
    }

    private static double calcularDescuentoAntiguedad(int mesCatalogo, double valorBase) {
        double descuentoAntiguedad = 0;
        if (mesCatalogo > LIMITE_MESES_ANTIGUEDAD) {
            descuentoAntiguedad = valorBase * PORCENTAJE_DESCUENTO_ANTIGUEDAD;
        }
        return descuentoAntiguedad;
    }
}