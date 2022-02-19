package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    /**
     * Once the sell by date has passed, Quality degrades twice as fast
     */
    @Test
    void testQualityDegradation() {
        int qualitySellIn1d = 40;
        Item[] items = new Item[] { new Item("foo", 1, qualitySellIn1d) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        int qualitySellIn0d = app.items[0].quality;
        int dropBeforeSellIn = qualitySellIn1d - qualitySellIn0d;
        app.updateQuality();
        int qualitySellInPast1d = app.items[0].quality;
        int dropAfterSellIn = qualitySellIn0d - qualitySellInPast1d;
        assertEquals(dropAfterSellIn, dropBeforeSellIn * 2, "Quality should degrade twice as fast after sell by date");
    }

    @Test
    void testQualityisNeverNegative(){
        Item[] items = new Item[] { new Item("foo", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void testSellInDecreases(){
        Item[] items = new Item[] { new Item("foo", 2, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    void testAgedBrieQuality(){
        Item[] items = new Item[] { new Item("Aged Brie", 2, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(21, app.items[0].quality);
    }

    @Test
    void BackStageTestCaseQuality(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 2, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23,app.items[0].quality);
    }

    @Test
    void BackStageTestCaseQualityWithSellin7(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 7, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22,app.items[0].quality);
    }

    @Test
    void ConjuredTestItemQuality(){
        Item[] items = new Item[] { new Item("Conjured Aged Brie", 7, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(18,app.items[0].quality);
    }
}
