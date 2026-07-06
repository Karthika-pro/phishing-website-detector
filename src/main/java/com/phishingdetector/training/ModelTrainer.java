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

     // Number of trees
     randomForest.setNumIterations(20);

     // Limit tree depth
     randomForest.setMaxDepth(10);

     // Use 4 CPU cores while training
     randomForest.setNumExecutionSlots(4);

     // Train the model
     randomForest.buildClassifier(data);

        SerializationHelper.write(
        		 "src/main/resources/model/phishing.model",
                randomForest
        );

        System.out.println("==================================");
        System.out.println("MODEL SAVED SUCCESSFULLY");
        System.out.println("==================================");
    }
}