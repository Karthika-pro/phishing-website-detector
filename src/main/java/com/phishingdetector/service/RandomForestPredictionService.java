package com.phishingdetector.service;
import com.phishingdetector.service.RiskScoringService;
import com.phishingdetector.model.PredictionLabel;
import com.phishingdetector.model.PredictionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Attribute;

import java.time.Instant;
import java.util.ArrayList;
import com.phishingdetector.util.DatasetSchema;
@Service
public class RandomForestPredictionService implements PredictionService {

    @Autowired
    private ModelLoader modelLoader;
    @Autowired
    private UrlValidationService urlValidationService;
    @Autowired
    private BrandSpoofDetectionService brandSpoofDetectionService;
    @Autowired
    private FeatureExtractionService featureExtractionService;
    @Autowired
    private RiskScoringService riskScoringService;

    @Override
    public PredictionResponse predict(String url) {

        try {
        	if (!urlValidationService.isValidFormat(url)) {

        	    return PredictionResponse.builder()
        	            .status("INVALID_URL")
        	            .message("Please enter a valid URL. Example: https://www.google.com")
        	            .url(url)
        	            .confidence(0.0)
        	            .modelUsed("Input Validation")
        	            .evaluatedAt(Instant.now())
        	            .build();
        	}

        	if (!urlValidationService.domainExists(url)) {

        	    return PredictionResponse.builder()
        	            .status("WEBSITE_NOT_FOUND")
        	            .message("The website does not exist or cannot be reached.")
        	            .url(url)
        	            .confidence(0.0)
        	            .modelUsed("DNS Validation")
        	            .evaluatedAt(Instant.now())
        	            .build();
        	}
        	if (brandSpoofDetectionService.isSuspiciousBrand(url)) {

        	    return PredictionResponse.builder()
        	            .status("SUCCESS")
        	            .message("Brand impersonation detected.")
        	            .url(url)
        	            .prediction(PredictionLabel.PHISHING)
        	            .confidence(95.0)
        	            .modelUsed("Brand Spoof Detection")
        	            .evaluatedAt(Instant.now())
        	            .build();

        	}
        	if (brandSpoofDetectionService.containsSuspiciousKeyword(url)) {

        	    return PredictionResponse.builder()
        	            .status("SUCCESS")
        	            .message("Suspicious keyword detected.")
        	            .url(url)
        	            .prediction(PredictionLabel.PHISHING)
        	            .confidence(90.0)
        	            .modelUsed("Keyword Analysis")
        	            .evaluatedAt(Instant.now())
        	            .build();

        	}
      
            double[] features = featureExtractionService.extractFeatures(url);
            boolean brandSpoof =
                    brandSpoofDetectionService.isSuspiciousBrand(url);

            boolean keywordDetected =
                    brandSpoofDetectionService.containsSuspiciousKeyword(url);

            double riskScore =
                    riskScoringService.calculateRisk(
                            url,
                            features,
                            brandSpoof,
                            keywordDetected
                    );
            Instances instances = DatasetSchema.createDataset();

            Instance instance = new DenseInstance(instances.numAttributes());

            instance.setDataset(instances);

            for (int i = 0; i < features.length; i++) {
                instance.setValue(i, features[i]);
            }

            Classifier classifier = modelLoader.getClassifier();

            double prediction = classifier.classifyInstance(instance);

            PredictionLabel label;

            if (riskScore >= 60) {

                label = PredictionLabel.PHISHING;

            }
            else {

                label = prediction == 1
                        ? PredictionLabel.PHISHING
                        : PredictionLabel.LEGITIMATE;

            }
            java.util.List<String> detectedFeatures =
                    riskScoringService.getDetectedFeatures(
                            features,
                            brandSpoof,
                            keywordDetected
                    );
            return PredictionResponse.builder()
                    .status("SUCCESS")
                    .message("Prediction completed successfully.")
                    .url(url)
                    .prediction(label)
                    .confidence(
                            riskScoringService.calculateConfidence(
                                    riskScore,
                                    label == PredictionLabel.PHISHING
                            )
                    )
                    .riskScore(riskScore)
                    .detectedFeatures(detectedFeatures)
                    .modelUsed(
                            riskScore >= 60
                                    ? "Risk Engine + Random Forest"
                                    : "Random Forest"
                    )
                    .evaluatedAt(Instant.now())
                    .build();
                 
           
              

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

}