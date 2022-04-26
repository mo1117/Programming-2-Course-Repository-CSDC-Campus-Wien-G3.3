package at.ac.fhcampuswien.enums;

public enum EndpointEnum
{
    everything("everything"),
    topHeadlines("top-headlines");

    private String endpoint;

    EndpointEnum(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}