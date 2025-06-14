package NikKha03.TaskService.service.builders;

import java.util.List;

public class UrlsBuilder {
    private List<String> urls;

    public UrlsBuilder() {
    }

    public UrlsBuilder(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}