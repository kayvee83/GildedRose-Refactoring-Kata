package com.gildedrose;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GildedRoseTest {

    @Test
    public void foo() {
        List<Item> items = Arrays.asList( new Item("foo", 0, 0) );
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", items.get(0).name);
    }

    @Test
    public void onceSellByDateIsPassedQualityDegradesTwiceAsFast(){
        List<Item> items = Arrays.asList( new Item("foo", -1, 2) );
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Quality should degrade twice as fast", 0, items.get(0).quality);
        assertEquals("Sell In date should got decreased",-2, items.get(0).sellIn);
    }

    @Test
    public void qualityOfItemWillNeverBeNegative() {
        List<Item> items = Arrays.asList(new Item("foo", -1, 1));
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertFalse("Quality of items should never be negative", items.get(0).quality < 0);
    }

    @Test
    public void agedBrieIncreasesInQualityWhenGetsOlder(){
        List<Item> items = Arrays.asList(new Item("Aged Brie", -1, 3));
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("Aged Brie should increase in quality when ages", items.get(0).quality > 4);
    }

    @Test
    public void qualityOfItemShouldBeNeverMoreThan50(){
        List<Item> items = Arrays.asList(new Item("foo", 2, 51));
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("Quality should be never more than 50", items.get(0).quality <= 50);
    }

    @Test
    public void sulfurasIsLegendaryAndCanHaveMoreThan50AsQuality(){
        List<Item> items = Arrays.asList(new Item("Sulfuras, Hand of Ragnaros", 2, 70));
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("Quality of sulfuras can be more than 50", items.get(0).quality == 70);
    }

    @Test
    public void sulfurasNeverToBeSoldOrDecreaseInQuality(){
        List<Item> items = Arrays.asList(new Item("Sulfuras, Hand of Ragnaros", 2, 20));
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("Quality should never decrease for Sulfuras", items.get(0).quality == 20);
        assertTrue("Sulfuras should never be sold", items.get(0).sellIn == 2);
    }

    @Test
    public void backStagePassesIncreasesInQualityByOneenSellInGreaterThan10(){
        List<Item> items = Arrays.asList(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20));
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("Quality should never increase by one for Backstage pass", items.get(0).quality == 21);
    }

    @Test
    public void backStagePassesIncreasesInQualityByOneTwoWhenSellInBetween5And11(){
        List<Item> items = Arrays.asList(new Item("Backstage passes to a TAFKAL80ETC concert", 6, 20));
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("Quality should never increase by two for Backstage pass", items.get(0).quality == 22);
    }

    @Test
    public void backStagePassesIncreasesInQualityByOneThreeWhenSellInLessThan6(){
        List<Item> items = Arrays.asList(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20));
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("Quality should never increase by three for Backstage pass", items.get(0).quality == 23);
    }

    @Test
    public void backStagePassesQualityDropsToZeroThreeWhenSellInAfterConcert(){
        List<Item> items = Arrays.asList(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("Quality should be zero for backstage pass after concert", items.get(0).quality == 0);
    }

    @Test
    public void conjuredShouldDecreaseQualityTwiceAsNormalItems(){
        List<Item> items = Arrays.asList(new Item("Conjured", 5, 20));
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("Quality should degrade twice as normal items for Conjured", items.get(0).quality == 18);
    }

}
