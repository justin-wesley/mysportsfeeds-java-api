package com.wesleyhome.stats.feed.request.impl;

import java.util.List;
import java.util.function.Supplier;

public abstract class RequestBuilder {

  protected <R> void applyListParameters(DefaultApiRequest<R> request, List<String> list,
      String listName) {
    if (!list.isEmpty()) {
      String teamValue = String.join(",", list);
      request.addParameter(listName, teamValue);
    }
  }

  protected <R> void applyParameter(DefaultApiRequest<R> request, String name, Object value) {
    if (value != null) {
      request.addParameter(name, value.toString());
    }
  }

  protected <R> void applyParameter(DefaultApiRequest<R> request, String name,
      Supplier<?> supplier) {
    applyParameter(request, name, supplier.get());
  }
}
