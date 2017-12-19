/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017 WebFolder OÜ (support@webfolder.io)
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.type.network;

import io.webfolder.cdp.type.constant.ReferrerPolicy;
import io.webfolder.cdp.type.security.MixedContentType;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP request data
 */
public class Request {
    private String url;

    private String method;

    private Map<String, Object> headers = new HashMap<>();

    private String postData;

    private MixedContentType mixedContentType;

    private ResourcePriority initialPriority;

    private ReferrerPolicy referrerPolicy;

    private Boolean isLinkPreload;

    /**
     * Request URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Request URL.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * HTTP request method.
     */
    public String getMethod() {
        return method;
    }

    /**
     * HTTP request method.
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * HTTP request headers.
     */
    public Map<String, Object> getHeaders() {
        return headers;
    }

    /**
     * HTTP request headers.
     */
    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    /**
     * HTTP POST request data.
     */
    public String getPostData() {
        return postData;
    }

    /**
     * HTTP POST request data.
     */
    public void setPostData(String postData) {
        this.postData = postData;
    }

    /**
     * The mixed content type of the request.
     */
    public MixedContentType getMixedContentType() {
        return mixedContentType;
    }

    /**
     * The mixed content type of the request.
     */
    public void setMixedContentType(MixedContentType mixedContentType) {
        this.mixedContentType = mixedContentType;
    }

    /**
     * Priority of the resource request at the time request is sent.
     */
    public ResourcePriority getInitialPriority() {
        return initialPriority;
    }

    /**
     * Priority of the resource request at the time request is sent.
     */
    public void setInitialPriority(ResourcePriority initialPriority) {
        this.initialPriority = initialPriority;
    }

    /**
     * The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/
     */
    public ReferrerPolicy getReferrerPolicy() {
        return referrerPolicy;
    }

    /**
     * The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/
     */
    public void setReferrerPolicy(ReferrerPolicy referrerPolicy) {
        this.referrerPolicy = referrerPolicy;
    }

    /**
     * Whether is loaded via link preload.
     */
    public Boolean isIsLinkPreload() {
        return isLinkPreload;
    }

    /**
     * Whether is loaded via link preload.
     */
    public void setIsLinkPreload(Boolean isLinkPreload) {
        this.isLinkPreload = isLinkPreload;
    }
}
