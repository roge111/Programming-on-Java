package test.lab.common.Collection;

import java.util.HashSet;
import java.util.Random;

public final class IDGenerator {
    private static final HashSet<Integer> HASH_SET_ID = new HashSet<>();

    private IDGenerator() {
    }

    public static Integer gernerateID() {
        Integer id = new Random().nextInt(Integer.MAX_VALUE);

        if (HASH_SET_ID.contains(id)) {
            while (HASH_SET_ID.contains(id)) {
                id = new Random().nextInt(Integer.MAX_VALUE);
            }
        }

        HASH_SET_ID.add(id);
        return id;
    }

    static Integer generateID(Integer iD) {
        Integer id = iD;

        if (HASH_SET_ID.contains(id)) {
            while (HASH_SET_ID.contains(id)) {
                id = new Random().nextInt(Integer.MAX_VALUE);
            }
        }

        HASH_SET_ID.add(id);
        return id;
    }
}
