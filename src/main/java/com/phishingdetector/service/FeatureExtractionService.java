package com.phishingdetector.service;

import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class FeatureExtractionService {

    public double[] extractFeatures(String url) {

        double[] features = new double[16];

        URI uri = null;

        try {
            uri = new URI(url);
        } catch (Exception e) {
            return features;
        }

        String host = uri.getHost();

        if (host == null) {
            host = "";
        }

        // 1. Have_IP
        features[0] = host.matches("\\d+\\.\\d+\\.\\d+\\.\\d+") ? 1 : 0;

        // 2. Have_At
        features[1] = url.contains("@") ? 1 : 0;

        // 3. URL Length
        int length = url.length();

        if (length < 54)
            features[2] = 0;
        else if (length <= 75)
            features[2] = 1;
        else
            features[2] = 2;

        // 4. URL Depth
        String path = uri.getPath();

        if (path == null || path.isBlank())
            features[3] = 0;
        else
            features[3] = path.split("/").length;

        // 5. Redirect
        features[4] = url.substring(8).contains("//") ? 1 : 0;

        // 6. HTTPS
        features[5] = url.startsWith("https://") ? 1 : 0;

        // 7. TinyURL
        String[] shorteners = {
                "bit.ly",
                "tinyurl.com",
                "goo.gl",
                "ow.ly",
                "t.co",
                "is.gd",
                "buff.ly",
                "cutt.ly"
        };

        features[6] = 0;

        for (String s : shorteners) {

            if (host.contains(s)) {

                features[6] = 1;
                break;

            }

        }

        // 8. Prefix/Suffix
        features[7] = host.contains("-") ? 1 : 0;

        // 9. DNS Record
        features[8] = 1;

        // 10. Web Traffic
        features[9] = 0;

        // 11. Domain Age
        features[10] = 0;

        // 12. Domain End
        features[11] = 0;

        // 13. iFrame
        features[12] = 0;

        // 14. Mouse Over
        features[13] = 0;

        // 15. Right Click
        features[14] = 0;

        // 16. Web Forwards
        features[15] = 0;

        return features;

    }

}