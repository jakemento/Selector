package sociopathycheck.selector.service;

import sociopathycheck.selector.data.Channel;

/**
 * Created by JS on 11/19/16.
 */

public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);
}
