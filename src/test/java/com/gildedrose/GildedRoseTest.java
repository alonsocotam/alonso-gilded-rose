package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class GildedRoseTest {

    private GildedRose app;
    private Item item;

    @Test
    @DisplayName("Once the sell by date has passed, Quality degrades twice as fast")
    void test_item_quality_degrades_twice_as_fast_past_sellin_date() {
        item = new Item("+5 Dexterity Vest", -1, 10);
        app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertThat(item.quality).isEqualTo(8);
    }

    @Test
    @DisplayName("The Quality of an item is never negative")
    void test_item_quality_never_negative() {
        item = new Item("+5 Dexterity Vest", 10, 0);
        app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertThat(item.quality).isEqualTo(0);
    }

    @Test
    @DisplayName("The Quality of an item is never more than 50")
    void test_quality_is_never_greater_than_50() {
        item = new Item("Aged Brie", 10, 50);
        app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertThat(item.quality).isEqualTo(50);
    }

    @Test
    @DisplayName("After date of sale, Aged Brie quality increases twice")
    void test_aged_brie_quality_increases_twice_after_sellin_date() {
        item = new Item("Aged Brie", 0, 10);
        app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertThat(item.quality).isEqualTo(12);
    }

    @Test
    @DisplayName("Aged Brie actually increases in Quality the older it gets")
    void test_aged_brie_increase_quality_over_time() {
        item = new Item("Aged Brie", 10, 10);
        app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertThat(item.quality).isEqualTo(11);
    }

    @Test
    @DisplayName("Sulfura never has to be sold or decreases in Quality")
    void test_sulfura_never_decrease_in_quality() {
        item = new Item("Sulfuras, Hand of Ragnaros", -1, 80);
        app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertThat(item.quality).isEqualTo(80);
    }

    @Test
    @DisplayName("Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less")
    void test_backstage_passes_increase_quality_as_sellIn_date_approaches() {
        item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertThat(item.quality).isEqualTo(21);
    }

    @Test
    @DisplayName("Quality drops to 0 after the concert")
    void test_backstage_passes_quality_is_0_after_expiration() {
        item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10);
        app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertThat(item.quality).isEqualTo(0);
    }

    @Test
    @DisplayName("Conjured items degrade in Quality twice as fast as normal items")
    void test_conjured_items_degrade_two_in_quality() {
        item = new Item("Conjured Mana Cake", 10, 10);
        app = new GildedRose(new Item[] { item });

        app.updateQuality();
        assertThat(item.quality).isEqualTo(8);
        assertThat(item.sellIn).isEqualTo(9);
    }

    @Test
    @DisplayName("Conjured items degrade in Quality by four after expired")
    void test_conjured_items_degrade_in_quality_four_if_expired() {
        item = new Item("Conjured Mana Cake", 0, 30);
        app = new GildedRose(new Item[] { item });

        app.updateQuality();
        assertThat(item.quality).isEqualTo(26);
    }

    @Test
    @DisplayName("Conjured item quality is never negative")
    void test_conjured_items_quality_never_negative() {
        item = new Item("Conjured Mana Cake", 10, 0);
        app = new GildedRose(new Item[] { item });

        app.updateQuality();
        assertThat(item.quality).isEqualTo(0);
    }
}
