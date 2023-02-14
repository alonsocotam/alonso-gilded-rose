package com.gildedrose;

import java.util.Objects;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured Mana Cake";

    public static final String QUALITY_CHECK = "Quality Check";
    public static final String EXPIRATION_CHECK = "Expiration Check";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        qualityCheck(item, QUALITY_CHECK);
        itemExpirationAdjustment(item);
        if (item.sellIn < 0) qualityCheck(item, EXPIRATION_CHECK);
    }

    private void qualityCheck(Item item, String checkType) {
        if (Objects.equals(item.name, AGED_BRIE)) {
            increaseItemQuality(item);
        } else if (Objects.equals(item.name, BACKSTAGE_PASSES)) {
            backstageQualityAdjustment(item, checkType);
        } else if (!Objects.equals(item.name, SULFURAS)) {
            decreaseItemQuality(item, getDecreaseRate(item));
        }
    }

    private void itemExpirationAdjustment(Item item) {
        if (!Objects.equals(item.name, SULFURAS)) {
            item.sellIn--;
        }
    }
    private void backstageQualityAdjustment(Item item, String checkType) {
        if (checkType == QUALITY_CHECK) {
            increaseItemQuality(item);

            if (item.sellIn < 11) {
                increaseItemQuality(item);
            }

            if (item.sellIn < 6) {
                increaseItemQuality(item);
            }
        } else {
            item.quality = 0;
        }
    }

    private void increaseItemQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    private void decreaseItemQuality(Item item, int decreaseRate) {
        if (item.quality > 0) {
            item.quality = Math.max(0, item.quality + decreaseRate);
        }
    }
    private int getDecreaseRate(Item item) {
        return Objects.equals(item.name, CONJURED) ? -2 : -1;
    }
}
