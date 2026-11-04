import java.util.Scanner;

// Sistema de ventas e inventario de una fábrica de licores (consola).

public class FabricaLicores {

    // Datos simulados (4 licores)
    static final String[] NOMBRES = {"Aguardiente", "Ron", "Vodka", "Whisky"};
    static final double[] PRECIO = {180000, 220000, 200000, 260000}; // por caja
    static int[] stock = {120, 80, 60, 40};                          // cajas

    // Supuestos de la simulación
    static final int UMBRAL_DESCUENTO = 50;   // descuento si cajas > 50
    static final double DESCUENTO = 0.10;
    static final double IMPUESTO = 0.20;      // sobre el valor ya descontado

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int rol;
        do {
            System.out.println("\n=== FABRICA DE LICORES ===");
            System.out.println("1. Modulo Cliente");
            System.out.println("2. Modulo Administrador");
            System.out.println("3. Salir");
            rol = leerEntero("Seleccione una opcion: ");

            switch (rol) {
                case 1:
                    moduloCliente();
                    break;
                case 2:
                    moduloAdmin();
                    break;
                case 3:
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (rol != 3);
    }

    // Lee un entero, mientras el usuario escriba texto, repite la lectura.
    static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Dato invalido, escriba un numero entero: ");
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    // Módulo Cliente 
    static void moduloCliente() {
        int edad = leerEntero("Ingrese su edad: ");
        if (edad < 0) {
            System.out.println("Edad invalida. Acceso denegado.");
            return;
        }
        if (edad < 18) {
            System.out.println("Acceso denegado: solo se permite la venta a mayores de 18 años.");
            return;
        }

        int opcion;
        do {
            System.out.println("\n--- MENU CLIENTE ---");
            System.out.println("1. Ver catalogo");
            System.out.println("2. Realizar pedido");
            System.out.println("3. Regresar");
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    mostrarCatalogo();
                    break;
                case 2:
                    realizarPedido();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 3);
    }

    static void mostrarCatalogo() {
        System.out.println("\n#  Licor          Precio/caja    Stock (cajas)");
        for (int i = 0; i < NOMBRES.length; i++) {
            System.out.printf("%d  %-13s  $%,12.0f  %d%n", i + 1, NOMBRES[i], PRECIO[i], stock[i]);
        }
    }

    static void realizarPedido() {
        mostrarCatalogo();
        int producto = leerEntero("Seleccione el licor (1-4): ");
        if (producto < 1 || producto > NOMBRES.length) {
            System.out.println("Producto invalido.");
            return;
        }
        int i = producto - 1;

        int cajas = leerEntero("Cantidad de cajas: ");
        if (cajas <= 0) {
            System.out.println("Cantidad invalida: debe ser mayor a cero.");
            return;
        }
        if (cajas > stock[i]) {
            System.out.println("Stock insuficiente. Disponible: " + stock[i] + " cajas.");
            return;
        }

        double subtotal = cajas * PRECIO[i];
        double descuento = (cajas > UMBRAL_DESCUENTO) ? subtotal * DESCUENTO : 0;
        double baseGravable = subtotal - descuento;
        double impuesto = baseGravable * IMPUESTO;
        double total = baseGravable + impuesto;

        stock[i] -= cajas;

        System.out.println("\nFACTURA");
        System.out.println("Producto:  " + NOMBRES[i]);
        System.out.println("Cajas:     " + cajas);
        System.out.printf("Subtotal:  $%,.2f%n", subtotal);
        System.out.printf("Descuento: $%,.2f%n", descuento);
        System.out.printf("Impuesto (20%%): $%,.2f%n", impuesto);
        System.out.printf("TOTAL:     $%,.2f%n", total);
    }

    // Módulo Administrador
    static void moduloAdmin() {
        int opcion;
        do {
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("1. Consultar inventario");
            System.out.println("2. Reabastecer bodega");
            System.out.println("3. Regresar");
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    consultarInventario();
                    break;
                case 2:
                    reabastecer();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 3);
    }

    static void consultarInventario() {
        System.out.println("\nInventario en bodega:");
        for (int i = 0; i < NOMBRES.length; i++) {
            System.out.println((i + 1) + ". " + NOMBRES[i] + ": " + stock[i] + " cajas");
        }
    }

    static void reabastecer() {
        consultarInventario();
        int producto = leerEntero("Seleccione el licor (1-4): ");
        int cajas = leerEntero("Cajas recibidas: ");
        if (producto < 1 || producto > NOMBRES.length || cajas <= 0) {
            System.out.println("Datos invalidos.");
            return;
        }
        stock[producto - 1] += cajas;
        System.out.println("Reabastecimiento registrado. Nuevo stock de "
                + NOMBRES[producto - 1] + ": " + stock[producto - 1] + " cajas.");
    }
}