package com.dinezen.www.dinezen.backendUtilities;

/**
 * Used when waiting on network requests to complete.
 */

public interface CompletedCallback {
    void complete();
    void error(String error);
}
