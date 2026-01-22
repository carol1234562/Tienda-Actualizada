package main;

import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.Sale;
import java.util.Scanner;

public class Shop {

    private double cash = 100.00;
    private List<Product> inventory;        
    private int numberProducts;
    private List<Sale> sales;
    

    final static double TAX_RATE = 1.04;

    public Shop() {
        inventory = new ArrayList<>();
        sales = new ArrayList<>();
    }

    public static void main(String[] args) {
        Shop shop = new Shop();

        shop.loadInventory();

        Scanner scanner = new Scanner(System.in);
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
            System.out.println("10) Salir programa");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

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
        //
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
        if (isInventoryFull()) {
            System.out.println("No se pueden a\u00f1adir mas productos");
            return;
        }
        Scanner scanner = new Scanner(System.in);
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
        Product newProduct = new Product(name, wholesalerPrice, true, stock);
        inventory.add(newProduct);
        System.out.println("Producto agregado correctamente.");
    }

    /**
     * add stock for a specific product
     */
    public void addStock() {
        Scanner scanner = new Scanner(System.in);
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
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.next();

        Product existingProduct = findProduct(name);
        double porcentaje = 40.0;

        if (existingProduct != null) {
            double precioFinal = existingProduct.getPublicPrice() * (porcentaje/100);
            System.out.println("El stock del producto " + name + " ha sido actualizado a " + precioFinal);
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
    public void sale() {
        // ask for client name
        Scanner sc = new Scanner(System.in);
        System.out.println("Realizar venta, escribir nombre cliente");
        String client = sc.nextLine();
        
        // sale product until input name is not 0
        double amount = 0.0;
        String name = "";
        while (!name.equals("0")) {
            System.out.println("Introduce el nombre del producto, escribir 0 para terminar:");
            name = sc.nextLine();

            if (name.equals("0")) {
                break;
            }
            Product existingProduct = findProduct(name);
            if (existingProduct == null || !existingProduct.isAvailable() || existingProduct.getStock() <= 0) {
                System.out.println("Producto no encontrado o sin stock");
                continue;
            }

            existingProduct.setStock(existingProduct.getStock() - 1);
            amount += existingProduct.getPublicPrice();

            // if no more stock, set as not available to sale
            if (existingProduct.getStock() == 0) {
                existingProduct.setAvailable(false);
            }
            System.out.println("Producto a\u00f1adido con Ã©xito");
        }
        
        // show cost total
        amount = amount * TAX_RATE;
        cash += amount;
        System.out.println("Venta realizada con exito, total: " + amount);
        sales.add(new Sale(client, amount));

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
    private void finalSales (){
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
     * check if inventory is full or not
     *
     * @return true if inventory is full
     */
    public boolean isInventoryFull() {
        if (numberProducts == 10) {
            return true;
        } else {
            return false;
        }
    }

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

}
