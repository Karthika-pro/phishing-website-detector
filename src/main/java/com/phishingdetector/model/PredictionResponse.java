package com.phishingdetector.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredictionResponse {

    private String status;          // SUCCESS / INVALID_URL / WEBSITE_NOT_FOUND
    private String message;         // User-friendly message

    private String url;
    private PredictionLabel prediction;
    private double confidence;
    private double riskScore;

    private java.util.List<String> detectedFeatures;
    private String modelUsed;
    private Instant evaluatedAt;

}