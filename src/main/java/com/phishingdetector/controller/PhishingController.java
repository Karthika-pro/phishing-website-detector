package com.phishingdetector.controller;

import com.phishingdetector.model.PredictionRequest;
import com.phishingdetector.model.PredictionResponse;
import com.phishingdetector.service.PredictionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST endpoints for phishing prediction.
 *
 * Python/Flask comparison:
 *   @app.route('/api/predict', methods=['POST'])
 *   def predict():
 *       url = request.json.get('url')
 *       ...
 *       return jsonify(result)
 *
 * becomes, in Spring:
 *   @RestController + @PostMapping, with the JSON body automatically
 *   deserialized into a PredictionRequest object (via @RequestBody) and
 *   the returned Java object automatically serialized back to JSON.
 *   No manual request.json parsing or jsonify() call needed.
 *
 * @RequiredArgsConstructor (Lombok) generates a constructor that takes all
 * `final` fields as parameters — here, just `predictionService`. Spring sees
 * this constructor and automatically supplies a PredictionService bean
 * (the StubPredictionService for now, a real one later) when it creates
 * this controller. This is "constructor-based dependency injection" — the
 * standard, recommended way to wire dependencies in Spring.
 */
@RestController
@RequestMapping("/api")
public class PhishingController {

    private final PredictionService predictionService;

    public PhishingController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }
    /**
     * POST /api/predict
     * Body:     { "url": "https://example.com" }
     * Response: PredictionResponse as JSON
     *
     * @Valid triggers the validation annotations on PredictionRequest
     * (e.g. @NotBlank) before this method body even runs. If validation
     * fails, Spring returns 400 Bad Request automatically — we never
     * see an invalid request here.
     */
    @PostMapping("/predict")
    public ResponseEntity<PredictionResponse> predict(@Valid @RequestBody PredictionRequest request) {
        PredictionResponse response = predictionService.predict(request.getUrl());
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/health
     * A simple liveness check — useful while developing, to confirm the
     * server is up before the real model is wired in.
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Phishing Detector API is running");
    }

}
