package com.phishingdetector.engine;

import com.phishingdetector.service.BrandSpoofDetectionService;
import com.phishingdetector.service.FeatureExtractionService;
import com.phishingdetector.service.UrlValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetectionEngine {

    @Autowired
    private UrlValidationService urlValidationService;

    @Autowired
    private BrandSpoofDetectionService brandSpoofDetectionService;

    @Autowired
    private FeatureExtractionService featureExtractionService;

    public DetectionResult analyze(String url) {

        DetectionResult result = new DetectionResult();

        result.setUrl(url);

        //----------------------------------------
        // URL FORMAT
        //----------------------------------------

        result.setValidUrl(
                urlValidationService.isValidFormat(url)
        );

        //----------------------------------------
        // DNS
        //----------------------------------------

        result.setDnsAvailable(
                urlValidationService.domainExists(url)
        );

        //----------------------------------------
        // BRAND SPOOF
        //----------------------------------------

        result.setBrandSpoof(
                brandSpoofDetectionService
                        .isSuspiciousBrand(url)
        );

        //----------------------------------------
        // SUSPICIOUS KEYWORDS
        //----------------------------------------

        result.setKeywordDetected(
                brandSpoofDetectionService
                        .containsSuspiciousKeyword(url)
        );

        //----------------------------------------
        // FEATURES
        //----------------------------------------

        result.setFeatures(
                featureExtractionService.extractFeatures(url)
        );

        return result;

    }

}