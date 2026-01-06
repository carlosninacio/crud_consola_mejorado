import entidad.Producto;

import java.util.ArrayList;
import java.util.List;

public class Productos {
    private static final List<Producto> productos;

    static {
        productos = new ArrayList<>();
        productos.add(new Producto("Lapicero", 2000));
        productos.add(new Producto("Cuaderno", 8500));
        productos.add(new Producto("Cartuchera", 15000));
    }

    public static void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public static void eliminarProducto(int idProducto) {
        productos.removeIf(p -> p.getIdProducto() == idProducto);
    }

    public static void mostrarProductos() {
        var inventarioProductos = "";
        for(var producto: productos) {
            inventarioProductos += producto.toString() + "\n";
        }
        System.out.println("--- Productos en Inventario ----");
        System.out.println(inventarioProductos);
    }

    public static List<Producto> getProductos() {
        return productos;
    }
}