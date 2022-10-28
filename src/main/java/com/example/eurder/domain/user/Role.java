package com.example.eurder.domain.user;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public enum Role {
    CUSTOMER(newArrayList(Feature.ORDER_ITEM)), ADMIN(newArrayList(Feature.ADDING_NEW_ITEM));

    private List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}
