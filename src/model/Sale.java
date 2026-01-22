package model;


public class Sale {
	String client;
	Product[] products;
	double amount;

	public Sale(String client, Product[] products, double amount) {
		super();
		this.client = client;
		this.products = products;
		this.amount = amount;
	}
        public Sale(String client, double amount) {
		super();
		this.client = client;
		this.amount = amount;
	}


	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Product[] getProducts() {
		return products;
	}

	public void setProducts(Product[] products) {
		this.products = products;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Venta [Cliente = " + client + " , Monto = " + amount + " ]";
	}

}