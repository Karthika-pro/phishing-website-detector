package com.phishingdetector.service;

import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.URI;
import java.util.regex.Pattern;

@Service
public class UrlValidationService {

    // URL must start with http:// or https://
	private static final Pattern URL_PATTERN = Pattern.compile(
	        "^(https?://)"
	      + "("
	      + "(([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,})"
	      + "|"
	      + "((\\d{1,3}\\.){3}\\d{1,3})"
	      + ")"
	      + "(:\\d+)?"
	      + "(/.*)?$"
	);

    /**
     * Checks whether the URL format is valid.
     */
    public boolean isValidFormat(String url) {

        if (url == null || url.isBlank()) {
            return false;
        }

        if (!URL_PATTERN.matcher(url.trim()).matches()) {
            return false;
        }

        try {

            URI uri = new URI(url);

            return uri.getScheme() != null &&
                    uri.getHost() != null;

        } catch (Exception e) {

            return false;

        }

    }

    /**
     * Checks whether the domain exists.
     */
    public boolean domainExists(String url) {

        try {

            URI uri = new URI(url);

            InetAddress.getByName(uri.getHost());

            return true;

        } catch (Exception e) {

            return false;

        }

    }

}