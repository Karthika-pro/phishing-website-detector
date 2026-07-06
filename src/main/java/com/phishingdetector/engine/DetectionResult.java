package com.phishingdetector.engine;

public class DetectionResult {

    private String url;

    private boolean validUrl;

    private boolean dnsAvailable;

    private boolean brandSpoof;

    private boolean keywordDetected;

    private double[] features;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isValidUrl() {
        return validUrl;
    }

    public void setValidUrl(boolean validUrl) {
        this.validUrl = validUrl;
    }

    public boolean isDnsAvailable() {
        return dnsAvailable;
    }

    public void setDnsAvailable(boolean dnsAvailable) {
        this.dnsAvailable = dnsAvailable;
    }

    public boolean isBrandSpoof() {
        return brandSpoof;
    }

    public void setBrandSpoof(boolean brandSpoof) {
        this.brandSpoof = brandSpoof;
    }

    public boolean isKeywordDetected() {
        return keywordDetected;
    }

    public void setKeywordDetected(boolean keywordDetected) {
        this.keywordDetected = keywordDetected;
    }

    public double[] getFeatures() {
        return features;
    }

    public void setFeatures(double[] features) {
        this.features = features;
    }

}