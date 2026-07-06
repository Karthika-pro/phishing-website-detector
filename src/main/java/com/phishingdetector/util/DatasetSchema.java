package com.phishingdetector.util;

import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.Instances;

public class DatasetSchema {

    public static Instances createDataset() {

        ArrayList<Attribute> attributes = new ArrayList<>();

        // Features (must match training data order)
        attributes.add(new Attribute("Have_IP"));
        attributes.add(new Attribute("Have_At"));
        attributes.add(new Attribute("URL_Length"));
        attributes.add(new Attribute("URL_Depth"));
        attributes.add(new Attribute("Redirection"));
        attributes.add(new Attribute("https_Domain"));
        attributes.add(new Attribute("TinyURL"));
        attributes.add(new Attribute("Prefix/Suffix"));
        attributes.add(new Attribute("DNS_Record"));
        attributes.add(new Attribute("Web_Traffic"));
        attributes.add(new Attribute("Domain_Age"));
        attributes.add(new Attribute("Domain_End"));
        attributes.add(new Attribute("iFrame"));
        attributes.add(new Attribute("Mouse_Over"));
        attributes.add(new Attribute("Right_Click"));
        attributes.add(new Attribute("Web_Forwards"));

        // Class Attribute
        ArrayList<String> classValues = new ArrayList<>();
        classValues.add("0");
        classValues.add("1");

        attributes.add(new Attribute("Label", classValues));

        Instances dataset = new Instances("PredictionDataset", attributes, 0);

        // Last column is class
        dataset.setClassIndex(dataset.numAttributes() - 1);

        return dataset;
    }
}