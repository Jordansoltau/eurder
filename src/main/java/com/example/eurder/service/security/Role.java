package com.example.eurder.service.security;

import com.google.common.collect.Lists;

import java.util.List;


public enum Role {
    MANAGER("manager",
           Feature.ADMIN),
    MEMBER("member",
            Feature.MEMBER);

    private final String label;
    private final List<Feature> featureList;

    Role(String label, Feature... featureList) {
        this.label = label;
        this.featureList = Lists.newArrayList(featureList);
    }

    public List<Feature> getFeatures() {
        return featureList;
    }

    public String getLabel() {
        return label;
    }
}
