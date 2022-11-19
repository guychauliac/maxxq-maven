package org.maxxq.maven.repository;

import okhttp3.Request;

public class DefaultRemoteRepositoryRequestBuilder implements IRemoteRepositoryRequestBuilder {

    @Override
    public Request apply( String endPoint ) {
        return new Request.Builder()
            .url( endPoint )
            .build();
    }

}
