package com.phishingdetector.service;

import com.phishingdetector.model.PredictionLabel;
import com.phishingdetector.model.PredictionResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * TEMPORARY placeholder implementation.
 *
 * This will be DELETED and replaced in Module 5 with a real implementation
 * that:
 *   1. Calls a FeatureExtractionService (Module 4) to turn the raw URL into
 *      a 30-value feature vector, mirroring your notebook's CSV columns
 *      (UsingIP, HTTPS, AgeofDomain, etc.)
 *   2. Feeds that vector into a Weka model loaded at startup (Module 3)
 *   3. Returns the real prediction + confidence
 *
 * For now it always returns a fixed, obviously-fake result, purely so we
 * can verify the controller/routing/validation layers work correctly in
 * isolation before the real ML logic exists.
 *
 * @Service marks this as a Spring-managed bean — Spring will automatically
 * create one instance of this class and hand it to anything that asks for
 * a PredictionService (that's "dependency injection": the controller
 * doesn't call `new StubPredictionService()` itself, Spring wires it in).
 */

public class StubPredictionService implements PredictionService {

    @Override
    public PredictionResponse predict(String url) {
        return PredictionResponse.builder()
                .url(url)
                .prediction(PredictionLabel.LEGITIMATE)
                .confidence(0.0) // 0.0 signals "not a real prediction yet"
                .modelUsed("STUB - not yet implemented (see Module 5)")
                .evaluatedAt(Instant.now())
                .build();
    }

}
