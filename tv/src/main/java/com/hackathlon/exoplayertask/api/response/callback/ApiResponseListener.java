package com.hackathlon.exoplayertask.api.response.callback;

import com.hackathlon.exoplayertask.api.response.ApiError;

public interface ApiResponseListener<T> {

    void success(T t);

    void authFailure();

    void error(ApiError error);
}
