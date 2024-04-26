package com.cifrazia.vision.connection.data.element.shop;

import java.util.List;

public class ShopCategoryItems {
    private List<ShopTradeOffer> shopTradeOffers;
    private long updatedTime;

    public ShopCategoryItems(List<ShopTradeOffer> shopTradeOffers, long updatedTime) {
        this.shopTradeOffers = shopTradeOffers;
        this.updatedTime = updatedTime;
    }

    public void update(List<ShopTradeOffer> shopTradeOffers, long updatedTime){
        this.shopTradeOffers = shopTradeOffers;
        this.updatedTime = updatedTime;
    }

    public List<ShopTradeOffer> getShopTradeOffers() {
        return shopTradeOffers;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }
}
