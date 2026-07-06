package com.phishingdetector.service;

import org.springframework.stereotype.Service;

@Service
public class BrandSpoofDetectionService {

    private static final String[] BRANDS = {

            "google",
            "microsoft",
            "amazon",
            "paypal",
            "facebook",
            "instagram",
            "apple",
            "netflix",
            "jiohotstar",
            "whatsapp",
            "github",
            "linkedin"

    };
    private static final String[] SUSPICIOUS_WORDS = {

            "login",
            "verify",
            "secure",
            "account",
            "update",
            "bank",
            "signin",
            "wallet",
            "confirm",
            "password"

    };

    public boolean isSuspiciousBrand(String url) {

        String lower = url.toLowerCase();

        for (String brand : BRANDS) {

            if (lower.contains(brand)) {

                String official = brand + ".com";

                if (!lower.contains(official)) {

                    return true;

                }
            }
        }

        return false;
    }
    public boolean containsSuspiciousKeyword(String url) {

        String lower = url.toLowerCase();

        for (String word : SUSPICIOUS_WORDS) {

            if (lower.contains(word)) {

                return true;

            }

        }

        return false;

    }
}