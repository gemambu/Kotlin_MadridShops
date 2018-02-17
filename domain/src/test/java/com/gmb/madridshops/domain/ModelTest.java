package com.gmb.madridshops.domain;

import com.gmb.madridshops.domain.model.Activity;
import com.gmb.madridshops.domain.model.Entities;
import com.gmb.madridshops.domain.model.Entity;
import com.gmb.madridshops.domain.model.Shop;
import com.gmb.madridshops.domain.util.EntityType;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ModelTest {
    @Test
    public void create_shop_model_is_correct() throws Exception {
        Shop shop = new Shop(1,
                "myShop",
                "myAddress",
                "desc en",
                "desc es",
                10.5f,
                11.5f,
                "image.png",
                "logo.png",
                "10-11 en",
                "10-11 es",
                EntityType.SHOP);

        assertEquals(1, shop.getId());
        assertEquals("myShop", shop.getName());
        assertEquals("myAddress", shop.getAddress());
        assertEquals("desc en", shop.getDescription_en());
        assertEquals("desc es", shop.getDescription_es());
        assertEquals(10.5f, shop.getLatitude(),0f);
        assertEquals(11.5f, shop.getLongitude(), 0f);
        assertEquals("image.png", shop.getImage());
        assertEquals("logo.png", shop.getLogo());
        assertEquals("10-11 en", shop.getOpeningHours_en());
        assertEquals("10-11 es", shop.getOpeningHours_es());
        assertEquals(EntityType.SHOP, shop.getType());

    }

    @Test
    public void create_activity_model_is_correct() throws Exception {
        Activity activity = new Activity(1,
                "myActivity",
                "myAddress",
                "desc en",
                "desc es",
                10.5f,
                11.5f,
                "image.png",
                "logo.png",
                "10-11 en",
                "10-11 es",
                EntityType.ACTIVITY);

        assertEquals(1, activity.getId());
        assertEquals("myActivity", activity.getName());
        assertEquals("myAddress", activity.getAddress());
        assertEquals("desc en", activity.getDescription_en());
        assertEquals("desc es", activity.getDescription_es());
        assertEquals(10.5f, activity.getLatitude(),0f);
        assertEquals(11.5f, activity.getLongitude(), 0f);
        assertEquals("image.png", activity.getImage());
        assertEquals("logo.png", activity.getLogo());
        assertEquals("10-11 en", activity.getOpeningHours_en());
        assertEquals("10-11 es", activity.getOpeningHours_es());
        assertEquals(EntityType.ACTIVITY, activity.getType());

    }

    @Test
    public void create_entity_model_is_correct() throws Exception {
        Entity entity = new Entity(1,
                "ENTITY",
                "myAddress",
                "desc en",
                "desc es",
                10.5f,
                11.5f,
                "image.png",
                "logo.png",
                "10-11 en",
                "10-11 es");

        assertEquals(1, entity.getId());
        assertEquals("ENTITY", entity.getName());
        assertEquals("myAddress", entity.getAddress());
        assertEquals("desc en", entity.getDescription_en());
        assertEquals("desc es", entity.getDescription_es());
        assertEquals(10.5f, entity.getLatitude(),0f);
        assertEquals(11.5f, entity.getLongitude(), 0f);
        assertEquals("image.png", entity.getImage());
        assertEquals("logo.png", entity.getLogo());
        assertEquals("10-11 en", entity.getOpeningHours_en());
        assertEquals("10-11 es", entity.getOpeningHours_es());

    }

    @Test
    public void create_entities_model_is_correct() throws Exception {
        Entity entity = new Shop(1,
                "ENTITY",
                "myAddress",
                "desc en",
                "desc es",
                10.5f,
                11.5f,
                "image.png",
                "logo.png",
                "10-11 en",
                "10-11 es",
                EntityType.SHOP);

        Entity entity2 = new Activity(2,
                "ENTITY2",
                "myAddress",
                "desc en",
                "desc es",
                10.5f,
                11.5f,
                "image.png",
                "logo.png",
                "10-11 en",
                "10-11 es",
                EntityType.ACTIVITY);

        Entities list = new Entities(new ArrayList<Entity>());
        list.add(entity);
        list.add(entity2);

        assertEquals(2, list.count());
        assertEquals("ENTITY2", list.get(1).getName());

        list.delete(1);
        assertEquals(1, list.count());
        assertEquals("ENTITY", list.get(0).getName());

        list.add(entity2);
        assertEquals(2, list.count());

        assertEquals(2, list.all().size());

        list.delete(entity);
        assertEquals(1, list.count());
        assertEquals("ENTITY2", list.get(0).getName());

        assertEquals(1, list.getEntities().size());
    }
}