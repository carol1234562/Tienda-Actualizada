package main;

import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.Sale;
import java.util.Scanner;
import model.Amount;
import model.Client;
import model.Person;
import model.Employee;

public class Shop {
//cambio de array a arraylist 

    private double cash = 100.00;
    private List<Product> inventory;
    private int numberProducts;
    private List<Sale> sales;
    static Scanner scanner = new Scanner(System.in);

    final static double TAX_RATE = 1.04;
    private Amount amount;

    public Shop() {
        //sin limite en inventario 
        inventory = new ArrayList<>();
        sales = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
   
    public boolean initSession() {

        Logable emp = new Employee(123, "test", "test");

        System.out.println("===========================");
        System.out.println("-----INICIO DE SESION-----");
        System.out.println("===========================");

        System.out.println("Ingrese ID de usuario: ");
        int i = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nombre de usuario: ");
        String user = scanner.nextLine();
        System.out.println("Ingrese contraseña: ");
        String contra = scanner.nextLine();

        if (!emp.login(i, user, contra)) {
            System.out.println("Datos incorrectos");
            return false;
        } else {
            System.out.println("Bienvenido " + user);
            return true;
        }
    }

    public static void main(String[] args) {
        //crear tienda
        Shop shop = new Shop();
        //uso de iniciar secion
//        if (!shop.initSession()) {
//            System.out.println("Acceso denegado");
//            return;
//        }
        //creación de bucle 
        boolean login = false;
        while (!login) {
            login = shop.initSession();
        }
        //cargar productos 
        shop.loadInventory();
        //scanner general 
        int opcion = 0;
        boolean exit = false;
        do {
            System.out.println("\n");
            System.out.println("===========================");
            System.out.println("Menu principal miTienda.com");
            System.out.println("===========================");
            System.out.println("1) Contar caja");
            System.out.println("2) A\u00f1adir producto");
            System.out.println("3) A\u00f1adir stock");
            System.out.println("4) Marcar producto proxima caducidad");
            System.out.println("5) Ver inventario");
            System.out.println("6) Venta");
            System.out.println("7) Ver ventas");
            System.out.println("8) Monto total de ventas");
            System.out.println("9) Eliminar producto");
            System.out.println("10) Salir programa");
            System.out.println("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    shop.showCash();
                    break;
                case 2:
                    shop.addProduct();
                    break;
                case 3:
                    shop.addStock();
                    break;
                case 4:
                    shop.setExpired();
                    break;
                case 5:
                    shop.showInventory();
                    break;
                case 6:
                    shop.sale();
                    break;
                case 7:
                    shop.showSales();
                    break;
                case 8:
                    shop.finalSales();
                    break;
                case 9:
                    shop.deleteProduct();
                    break;
                case 10:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    /**
     * load initial inventory to shop
     */
    public void loadInventory() {
        //inventario predeterminado
        inventory.add(new Product("Manzana", 10.00, true, 10));
        inventory.add(new Product("Pera", 20.00, true, 20));
        inventory.add(new Product("Hamburguesa", 30.00, true, 30));
        inventory.add(new Product("Fresa", 5.00, true, 20));
    }

    /**
     * show current total cash
     */
    private void showCash() {
        System.out.println("Dinero actual: " + cash);
    }

    /**
     * add a new product to inventory getting data from console
     */
    public void addProduct() {
        scanner.nextLine();
        System.out.print("Nombre: ");
        String name = scanner.nextLine();

        Product existingProduct = findProduct(name);

        if (existingProduct != null && existingProduct.getName().equalsIgnoreCase(name)) {
            System.out.println("El producto ya existe en el sistema.");
            return;
        }
        System.out.print("Precio mayorista: ");
        double wholesalerPrice = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();
        Product newProduct = new Product(name, wholesalerPrice, true, stock);
        inventory.add(newProduct);
        System.out.println("Producto agregado correctamente.");
    }

    /**
     * add stock for a specific product
     */
    public void addStock() {
        scanner.nextLine();
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.next();
        Product existingProduct = findProduct(name);

        if (existingProduct != null && existingProduct.getName().equalsIgnoreCase(name)) {
            // ask for stock
            System.out.print("Seleccione la cantidad a a\u00f1adir: ");
            int stockToAdd = scanner.nextInt();
            // update stock product
            existingProduct.setStock(existingProduct.getStock() + stockToAdd);
            System.out.println("El stock del producto " + name + " ha sido actualizado a " + existingProduct.getStock());
        } else {
            System.out.println("No se ha encontrado el producto con nombre " + name);
        }
    }

    /**
     * set a product as expired
     */
    private void setExpired() {
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.next();

        Product existingProduct = findProduct(name);
        if (existingProduct != null) {
            double precioFinal;
            precioFinal = existingProduct.expire();
            System.out.println("El precio del producto " + name + " ha sido actualizado a " + precioFinal);
            existingProduct.setPublicPrice(precioFinal);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    /**
     * show all inventory
     */
    public void showInventory() {
        System.out.println("Contenido actual de la tienda:");
        for (Product product : inventory) {
            if (product != null) {
                System.out.println(product);
            }
        }
    }

    /**
     * make a sale of products to a client
     */
    public void sale(){
        // ask for client name
        scanner.nextLine();
        System.out.println("Realizar venta, escribir nombre cliente");
        String client = scanner.nextLine();

        // sale product until input name is not 0
        double monto = 0.0;
        String name = "";
        while (!name.equals("0")) {
            System.out.println("Introduce el nombre del producto, escribir 0 para terminar:");
            name = scanner.nextLine();

            if (name.equals("0")) {
                break;
            }
            Product existingProduct = findProduct(name);
            if (existingProduct == null || !existingProduct.isAvailable() || existingProduct.getStock() <= 0) {
                System.out.println("Producto no encontrado o sin stock");
                continue;
            }

            existingProduct.setStock(existingProduct.getStock() - 1);
            monto += existingProduct.getPublicPrice();

            // if no more stock, set as not available to sale
            if (existingProduct.getStock() == 0) {
                existingProduct.setAvailable(false);
            }
            System.out.println("Producto a\u00f1adido con Ã©xito");
        }

        // show cost total
        monto = monto * TAX_RATE;
        cash += monto;
        System.out.println("Venta realizada con exito, total: " + monto);
        sales.add(new Sale(client, monto));

    }

    /**
     * show all sales
     */
    private void showSales() {
        System.out.println("Lista de ventas:");
        for (Sale sale : sales) {
            if (sale != null) {
                System.out.println(sale);
            }
        }
    }

    private void finalSales() {
        double total = 0;
        System.out.println("Total de ventas del día: ");
        for (Sale sale : sales) {
            if (sale != null) {
                total += sale.getAmount();
            }
        }
        System.out.println(total);
    }

    /**
     * add a product to inventory
     *
     * @param product
     */
    /**
     * find product by name
     *
     * @param name
     * @return product found by name
     */
    public Product findProduct(String name) {
        for (Product inventory1 : inventory) {
            if (inventory1 != null && inventory1.getName().equalsIgnoreCase(name)) {
                return inventory1;
            }
        }
        return null;
    }

    public Product deleteProduct() {
        //se agregara en los return ya sea si 
        //se ha eliminado un producto 
        //o un null en caso de no haberlo hecho
        //esto es debido a que el metodo ha sido declarado como product 
        System.out.println("Ingrese nombre del producto");
        String name = scanner.nextLine();
        //declarar
        Product productEliminado = findProduct(name);
        for (Product p : inventory) {
            if (p.getName().equalsIgnoreCase(name)) {
                productEliminado = p;
                break;
            }
        }
        if (productEliminado != null) {
            System.out.println("¿Seguro que desea eliminarlo? (YES/NO)");
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("YES")) {
                inventory.remove(productEliminado);
                System.out.println("Producto eliminado con éxito");
                //opcional
                return productEliminado;
            } else {
                System.out.println("Operación cancelada");
                //opcional 
                return null;
            }
        } else {
            System.out.println("No se encontró el producto, inténtelo nuevamente");
            //opcional
            return null;
        }
    }
}
