package ru.netology.Service;


import java.util.Arrays;

public class ShoppingCartService {

    private int[] productId;
    private int[] productCount;

    public ShoppingCartService() {
        productId = new int[]{};
        productCount = new int[]{};
    }

    public int getCartSize() {
        return productId.length;
    }

    public int[] getProductsId() {
        return productId;
    }

    public int getProductCountById(int id) {
        int count = 0;
        for (int prId : productId) {
            if (prId == id) {
                return productCount[count];
            }
            ++count;
        }
        return -1;
    }

    private void updateProductCount(int id, int count) {
        int length = productId.length;
        for (int i = 0; i < length; ++i) {
            if (productId[i] == id) {
                productCount[i] = productCount[i] + count;
            }
        }
    }

    private boolean findExistId(int id) {
        for (int j : productId) {
            if (j == id) {
                return true;
            }
        }
        return false;
    }

    public void addProductToCart(int id, int count) {

        if (!findExistId(id)) {
            productId = Arrays.copyOf(productId, productId.length + 1);
            productCount = Arrays.copyOf(productCount, productCount.length + 1);

            productId[productId.length - 1] = id;
            productCount[productCount.length - 1] = count;
        } else {
            updateProductCount(id, count);
        }
    }
}
