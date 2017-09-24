package au.momenton.codechallenge.db.entity;

/**
 * Created by WPerera on 9/23/2017.
 */
public abstract class BaseEntity<Key> {
    private Key id;

    public BaseEntity(Key id) {
        this.id = id;
    }

    public Key getId() {
        return id;
    }
}
