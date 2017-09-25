package au.com.momenton.codechallenge.service;

import au.momenton.codechallenge.common.utils.CoverageIgnore;

/**
 * Created by WPerera on 9/23/2017.
 */
public class CodeChallengeServiceException extends Exception {
    @CoverageIgnore
    public CodeChallengeServiceException(String message) {
        super(message);
    }
}
