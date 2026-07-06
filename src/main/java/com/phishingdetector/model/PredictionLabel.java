package com.phishingdetector.model;

/**
 * Python comparison: your notebook used raw -1 / 1 integers for the class
 * column (a common scikit-learn convention). That's fine for a model that
 * only ever talks to itself, but it's a poor fit for an API response that
 * a human or a frontend will read.
 *
 * An enum gives us a fixed, named set of possible values instead of a magic
 * number — the compiler will catch typos like "phising" that a raw String
 * or int would silently allow through.
 */
public enum PredictionLabel {
    PHISHING,
    LEGITIMATE
}
