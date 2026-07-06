package com.phishingdetector.service;

import com.phishingdetector.model.PredictionResponse;

/**
 * The contract for "given a URL, tell me if it's phishing."
 *
 * We define this as an interface (not a class) so the controller can depend
 * on *what* it does, not *how*. In Module 4/5 we'll write the real
 * implementation — extracting features from the live URL and running them
 * through a trained Weka model. For now, StubPredictionService below
 * satisfies this contract so the app compiles and the endpoint is testable.
 *
 * Python comparison: Python's duck typing means you rarely need to declare
 * this kind of contract explicitly — any object with a matching method
 * "just works." Java's static typing means we spell the contract out, which
 * costs a little more upfront but makes it obvious, at compile time, what
 * a PredictionService is required to do, and lets us swap implementations
 * (real vs. stub vs. a future "test double" for unit tests) without
 * touching the controller at all.
 */
public interface PredictionService {

    PredictionResponse predict(String url);

}
