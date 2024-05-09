package com.example.ygodeckbuilder;

import java.util.List;

public class CardData {
    private int id;
    private String name;
    private String type;
    private String desc;
    private int atk;
    private String race;
    private String attribute;
    private String archetype;
    private int linkval;
    private List<String> linkmarkers;
    private String ygoprodeck_url;
    private List<CardSet> card_sets;
    private List<CardImage> card_images;
    private List<CardPrice> card_prices;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public int getAtk() {
        return atk;
    }

    public String getRace() {
        return race;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getArchetype() {
        return archetype;
    }

    public int getLinkval() {
        return linkval;
    }

    public List<String> getLinkmarkers() {
        return linkmarkers;
    }

    public String getYgoprodeck_url() {
        return ygoprodeck_url;
    }

    public List<CardSet> getCard_sets() {
        return card_sets;
    }

    public List<CardImage> getCard_images() {
        return card_images;
    }

    public List<CardPrice> getCard_prices() {
        return card_prices;
    }
}

