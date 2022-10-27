package com.example.eurder.domain;

import java.util.ArrayList;
import java.util.List;
import static com.google.common.collect.Lists.newArrayList;
public enum Role {
    USER(newArrayList(Feature.USER)), ADMIN(newArrayList());

    private List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature){
        return featureList.contains(feature);
    }
}
