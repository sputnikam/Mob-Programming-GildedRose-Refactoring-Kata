package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
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
}
