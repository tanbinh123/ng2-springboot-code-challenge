package au.momenton.codechallenge.domain;

import au.momenton.codechallenge.common.utils.CoverageIgnore;

/**
 * Created by WPerera on 9/23/2017.
 */
public abstract class DomainObject<Key> {
    private Key id;

    @CoverageIgnore
    public Key getId() {
        return id;
    }

    @CoverageIgnore
    public void setId(Key id) {
        this.id = id;
    }
}
