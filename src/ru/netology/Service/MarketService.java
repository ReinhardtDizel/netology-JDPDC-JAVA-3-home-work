package ru.netology.Service;

import java.util.Scanner;

public class MarketService {

    private static final String PROGRAM_END = "end";
    private static final String MSG_LIST_OF_PRODUCT_IN_STOCK = "Список возможных товаров для покупки";
    private static final String MSG_SELECT_PRODUCT_AND_COUNT = "Выберите товар и количество или введите ";
    private static final String MSG_YOUR_PRODUCT_CART = "Ваша корзина:";
    private static final String ERROR_INCORRECT_ENTER = "Ошибка ввода";

    private static final String PRODUCT_CART_TABLE_FRAME = "+---------------------+------------+------------+-----------------+";
    private static final String PRODUCT_CART_TABLE_HEADER = "| Наименование товара | Количество | Цена/за.ед | Общая стоимость |";
    private static final String PRODUCTS_TABLE_FRAME = "+------+---------------------+------------+";
    private static final String PRODUCTS_TABLE_HEADER = "| ID   | Наименование товара | Цена/за.ед |";

    private static final String SPLITTER = " ";

    private String formattedProductCartTableBottomWithTotalPrice = "|                                    Итого:       %-15d |";
    private String formattedShowProductResult = "| %-19s   %-10d   %-10d   %-15d |";
    private String formattedProductStringRow = "| %-4d   %-19s   %d руб/шт |";

    private ProductService productService;
    private ShoppingCartService shoppingCartService;
    private Scanner scanner;
    private int totalCartPrice = 0;

    private boolean initMarket() {

        return initStock() && initIOService() && initIOService() && initCart();
    }

    private boolean initCart() {

        shoppingCartService = new ShoppingCartService();
        return true;
    }

    private boolean initIOService() {

        scanner = new Scanner(System.in);
        return true;
    }

    private boolean initStock() {

        productService = new ProductService(
                new int[]{1, 2, 3, 4},
                new String[]{"Сыр", "Хлеб", "Масло", "Яйца"},
                new int[]{350, 100, 150, 180}
        );
        return true;
    }

    public void run() {

        initMarket();
        System.out.println(TryToColorService.getBannerStarter(TryToColorService.Color.ANSI_GREEN));
        showProduct();
        while (true) {
            System.out.println(TryToColorService.paintThisString(MSG_SELECT_PRODUCT_AND_COUNT + PROGRAM_END, TryToColorService.Color.ANSI_YELLOW));
            String input = scanner.nextLine();

            if (input.equals(PROGRAM_END)) {
                break;
            }
            String[] splitInput = input.split(SPLITTER);

            if (splitInput.length <= 1) {
                System.out.println(TryToColorService.paintThisString(ERROR_INCORRECT_ENTER, TryToColorService.Color.ANSI_RED));
            } else {
                addProduct(splitInput);
            }

        }
        showCart();
    }

    public void showProduct() {
        System.out.println(TryToColorService.paintThisString(MSG_LIST_OF_PRODUCT_IN_STOCK, TryToColorService.Color.ANSI_CYAN));
        System.out.println(PRODUCTS_TABLE_FRAME);
        System.out.println(PRODUCTS_TABLE_HEADER);
        System.out.println(PRODUCTS_TABLE_FRAME);
        for (int i = 0; i < productService.length(); ++i) {

            String product = String.format((formattedProductStringRow),
                    productService.getId(i),
                    productService.getProduct(i),
                    productService.getPrice(i)
            );
            System.out.println(TryToColorService.paintThisString(product, TryToColorService.Color.ANSI_CYAN));

        }
        System.out.println(PRODUCTS_TABLE_FRAME);
    }

    public void showCart() {
        System.out.println();
        System.out.println(TryToColorService.paintThisString(MSG_YOUR_PRODUCT_CART, TryToColorService.Color.ANSI_CYAN));
        System.out.println(PRODUCT_CART_TABLE_FRAME);
        System.out.println(PRODUCT_CART_TABLE_HEADER);
        System.out.println(PRODUCT_CART_TABLE_FRAME);
        for (int i = 0; i < shoppingCartService.getCartSize(); ++i) {

            int productId = shoppingCartService.getProductsId()[i];
            int productPrice = productService.getProductPriceById(productId);
            int productCount = shoppingCartService.getProductCountById(productId);
            String productName = productService.getProductNameById(productId);
            int totalProductPrice = productPrice * productCount;
            totalCartPrice += totalProductPrice;

            String product = String.format((formattedShowProductResult),
                    productName,
                    productCount,
                    productPrice,
                    totalProductPrice);

            System.out.println(TryToColorService.paintThisString(product, TryToColorService.Color.ANSI_CYAN));
        }
        String resultString = String.format(formattedProductCartTableBottomWithTotalPrice, totalCartPrice);
        System.out.println(TryToColorService.paintThisString(resultString, TryToColorService.Color.ANSI_YELLOW));
        System.out.println(PRODUCT_CART_TABLE_FRAME);
    }

    public void addProduct(String[] input) {
        int id = Integer.parseInt(input[0]);
        int count = Integer.parseInt(input[1]);
        if (id > 0 && id <= productService.length() && count > 0) {
            shoppingCartService.addProductToCart(id, count);
        }
    }
}
