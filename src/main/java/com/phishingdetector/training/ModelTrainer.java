package com.phishingdetector.training;

import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

public class ModelTrainer {

    public static void main(String[] args) throws Exception {

        DataSource source = new DataSource("dataset/5.urldata.csv");

        Instances data = source.getDataSet();

        data.setClassIndex(data.numAttributes() - 1);

        System.out.println("Rows : " + data.numInstances());
        System.out.println("Columns : " + data.numAttributes());

        RandomForest randomForest = new RandomForest();

        randomForest.setNumIterations(100);

        randomForest.buildClassifier(data);

        SerializationHelper.write(
                "model/phishing.model",
                randomForest
        );

        System.out.println("==================================");
        System.out.println("MODEL SAVED SUCCESSFULLY");
        System.out.println("==================================");
    }
}