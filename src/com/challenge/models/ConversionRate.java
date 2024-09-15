package com.challenge.models;

import java.util.HashMap;

public record ConversionRate(HashMap<String, Double> conversion_rates, String result, String base_code) {
}
