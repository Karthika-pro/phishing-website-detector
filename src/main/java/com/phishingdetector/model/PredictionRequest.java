package com.phishingdetector.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The JSON body the client sends to POST /api/predict, e.g.:
 *   { "url": "https://example.com" }
 *
 * Python comparison: in Flask you'd write `request.json.get("url")` and manually
 * check if it's missing/blank. Here, validation is declarative — the
 * annotations below do that checking for us before our code even runs,
 * and Spring returns a 400 Bad Request automatically if they fail.
 *
 * @Data (Lombok) generates getUrl()/setUrl(), equals(), hashCode(), and toString()
 * for us, so we don't have to hand-write that boilerplate.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredictionRequest {

    @NotBlank(message = "url must not be blank")
    
    private String url;

}
