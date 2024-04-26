package com.cifrazia.vision.connection.data;

import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.connection.data.element.shop.ShopCategory;
import com.cifrazia.vision.connection.data.element.shop.ShopCategoryItems;
import com.cifrazia.vision.connection.data.element.shop.ShopItem;
import com.cifrazia.vision.connection.data.element.shop.ShopTradeOffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopData {
    private static final long interval = 5 * 60000;// 5 min
    private final AuthorizedClient service;
    private List<ShopCategory> categories;
    private HashMap<ShopCategory, ShopCategoryItems> itemsByCategory;
    private ShopCategory currentCategory;
    private final ShopCategory all;
    private long updatedCategoryTime;


    public ShopData(AuthorizedClient service) {
        this.service = service;
        all = new ShopCategory(-1, "All", 0);
    }

    public List<ShopCategory> getCategories() {
        if (categories == null || updatedCategoryTime < System.currentTimeMillis() - interval) {
            categories = service.getShopCategory();
            itemsByCategory = new HashMap<>(categories.size());
            updatedCategoryTime = System.currentTimeMillis();
        }
        return categories;
    }

    public void setCategory(ShopCategory category) {
        if (!itemsByCategory.containsKey(category)) {
            itemsByCategory.put(category, new ShopCategoryItems(loadItems(category), System.currentTimeMillis()));
        }
        currentCategory = category;
    }

    public List<ShopTradeOffer> getItems() {
        ShopCategoryItems shopCategoryItems = itemsByCategory.get(currentCategory);
        if (shopCategoryItems.getUpdatedTime() < System.currentTimeMillis() - interval) {
            shopCategoryItems.update(loadItems(currentCategory), System.currentTimeMillis());
        }
        return shopCategoryItems.getShopTradeOffers();
    }

    private List<ShopTradeOffer> loadItems(ShopCategory category) {
        List<ShopItem> shopItems;

        if (category == all)
            shopItems = service.getAllShopList();
        else
            shopItems = service.getCategoryShopList(category);

        List<ShopTradeOffer> items = new ArrayList<>(shopItems.size());

        for (ShopItem item : shopItems) {
            items.add(new ShopTradeOffer(item));
        }

        return items;
    }

    public ShopCategory getAllCategory() {
        return all;
    }
}
