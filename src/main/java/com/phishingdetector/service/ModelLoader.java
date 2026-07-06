package com.phishingdetector.service;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.core.SerializationHelper;

@Service
public class ModelLoader {

    private Classifier classifier;

    @PostConstruct
    public void loadModel() throws Exception {

        ClassPathResource resource = new ClassPathResource("model/phishing.model");

        classifier = (Classifier) SerializationHelper.read(
                resource.getInputStream()
        );

        System.out.println("=================================");
        System.out.println("Phishing Model Loaded Successfully");
        System.out.println("=================================");
    }
    public Classifier getClassifier() {
        return classifier;
    }
}