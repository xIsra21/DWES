package goya.daw2.H2Tuto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Quote(int id, String type, String setup, String punchline) { }