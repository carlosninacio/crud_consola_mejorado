import entidad.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Papeleria {
    public static void main(String[] args) {
        papeleria();
    }

    public static void papeleria() {
        var salir = false;
        var consola = new Scanner(System.in);
        List<Producto> carrito = new ArrayList<>();
        System.out.println("**** Bienvenid@ a la Papeleria ****");
        Productos.mostrarProductos();
        while(!salir) {
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, carrito);
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    private static int mostrarMenu(Scanner consola) {
        System.out.println("""
                Menú:
                1. Ver Productos
                2. Agregar Producto al Inventario
                3. Eliminar Producto del Inventario
                4. Comprar Producto
                5. Ver Carrito
                6. Facturar
                7. Salir
                
                Elige una opción:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola, List<Producto> carrito) {
        var salir = false;
        switch (opcion) {
            case 1 -> Productos.mostrarProductos();
            case 2 -> agregarProducto(consola);
            case 3 -> eliminarProductoInventario(consola);
            case 4 -> comprarProducto(consola, carrito);
            case 5 -> verCarrito(carrito);
            case 6 -> facturar(carrito);
            case 7 -> {
                System.out.println("Regresa pronto!");
                salir = true;
            }
            default -> System.out.println("Opción Inválida: " + opcion);
        }
        return salir;
    }

    public static void agregarProducto(Scanner consola) {
        System.out.print("Nombre del producto: ");
        var nombre = consola.nextLine();
        System.out.print("Precio del producto: ");
        var precio = Double.parseDouble(consola.nextLine());
        Productos.agregarProducto(new Producto(nombre, precio));
        System.out.println("Producto agregado correctamente");
    }

    public static void eliminarProductoInventario(Scanner consola) {
        System.out.print("Qué producto deseas eliminar del inventario (Id)?: ");
        var idProducto = Integer.parseInt(consola.nextLine());
        var productoEncontrado = false;
        for(var producto: Productos.getProductos()) {
            if(idProducto == producto.getIdProducto()) {
                Productos.eliminarProducto(idProducto);
                System.out.println("Producto eliminado: " + producto);
                productoEncontrado = true;
                break;
            }
        }
        if(!productoEncontrado) {
            System.out.println("Id del producto no encontrado: " + idProducto);
        }
    }

    public static void comprarProducto(Scanner consola, List<Producto> carrito) {
        System.out.print("Qué producto deseas comprar (Id)?: ");
        var idProducto = Integer.parseInt(consola.nextLine());
        var productoEncontrado = false;
        for(var producto: Productos.getProductos()) {
            if(idProducto == producto.getIdProducto()) {
                carrito.add(producto);
                System.out.println("Producto agregado: " + producto);
                productoEncontrado = true;
                break;
            }
        }
        if(!productoEncontrado) {
            System.out.println("Id del producto no encontrado: " + idProducto);
        }
    }

    public static void verCarrito(List<Producto> carrito) {
        var carritoProductos = "";
        for(var producto: carrito) {
            carritoProductos += producto.toString() + "\n";
        }
        System.out.println("--- Productos en el Carrito ----");
        System.out.println(carritoProductos);
    }

    public static void facturar(List<Producto> carrito) {
        var factura = "*** Factura de Venta ***";
        var total = 0.0;
        for(var producto: carrito) {
            factura += "\n\t- " + producto.getNombre() + " - $" + producto.getPrecio();
            total += producto.getPrecio();
        }
        factura += "\nTotal -> $" + total;
        System.out.println(factura);
    }

}