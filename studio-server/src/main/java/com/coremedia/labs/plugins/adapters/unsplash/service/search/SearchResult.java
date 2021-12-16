package com.coremedia.labs.plugins.adapters.unsplash.service.search;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchResult<T> {

  @JsonProperty("total")
  private int total;

  @JsonProperty("total_pages")
  private int totalPages;

  @JsonProperty("results")
  private List<T> results;

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public List<T> getResults() {
    return results;
  }

  public void setResults(List<T> results) {
    this.results = results;
  }
}
