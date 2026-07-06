package com.phishingdetector.service;

import org.springframework.stereotype.Service;

@Service
public class RiskScoringService {
	public java.util.List<String> getDetectedFeatures(
	        double[] features,
	        boolean brandSpoof,
	        boolean keywordDetected) {

	    java.util.List<String> list =
	            new java.util.ArrayList<>();

	    if (features[0] == 1)
	        list.add("IP Address URL");

	    if (features[1] == 1)
	        list.add("@ Symbol");

	    if (features[2] == 2)
	        list.add("Long URL");

	    if (features[4] == 1)
	        list.add("Redirect");

	    if (features[6] == 1)
	        list.add("TinyURL");

	    if (features[7] == 1)
	        list.add("Hyphen in Domain");

	    if (brandSpoof)
	        list.add("Brand Spoof");

	    if (keywordDetected)
	        list.add("Suspicious Keyword");

	    if (list.isEmpty())
	        list.add("No Suspicious Features");

	    return list;
	}

    public double calculateRisk(String url,
                                double[] features,
                                boolean brandSpoof,
                                boolean keywordDetected) {

        double score = 0;

        // Brand spoof
        if (brandSpoof)
            score += 40;

        // Suspicious keyword
        if (keywordDetected)
            score += 25;

        // IP Address
        if (features[0] == 1)
            score += 20;

        // @ symbol
        if (features[1] == 1)
            score += 15;

        // Long URL
        if (features[2] == 2)
            score += 10;

        // Redirect
        if (features[4] == 1)
            score += 10;

        // Tiny URL
        if (features[6] == 1)
            score += 15;

        // Hyphen
        if (features[7] == 1)
            score += 10;

        if (score > 100)
            score = 100;

        return score;
    }

    public double calculateConfidence(double riskScore,
                                      boolean modelPrediction) {

        if (riskScore >= 80)
            return 99;

        if (riskScore >= 60)
            return 95;

        if (riskScore >= 40)
            return 90;

        if (modelPrediction)
            return 90;

        return 98;
    }

}