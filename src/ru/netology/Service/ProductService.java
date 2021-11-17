package ru.netology.Service;

import java.util.Arrays;

public class ProductService {

    private String[] products;
    private int[] id;
    private int[] prices;
    private int length;

    public ProductService(int[] id, String[] product, int[] price) {

        this.id =  Arrays.copyOf(id, id.length);
        products = Arrays.copyOf(product, product.length);
        prices = Arrays.copyOf(price, product.length);
        length = product.length;
    }

    public int getId(int index) {
        return id[index];
    }

    public int[] getId() {
        return id;
    }

    public String[] getProducts() {
        return products;
    }

    public int[] getPrices() {
        return prices;
    }

    public String getProductNameById(int id) {
        int count = 0;
        for (String product : products) {
            if (this.id[count] == id) {
                return product;
            }
            ++count;
        }
        return null;
    }

    public int getProductPriceById(int id) {
        int count = 0;
        for (int price : prices) {
            if (this.id[count] == id) {
                return price;
            }
            ++count;
        }
        return -1;
    }

    public String getProduct(int index) {
        return products[index];
    }

    public int getPrice(int index) {
        return prices[index];
    }

    public int length() {
        return length;
    }
}
