package test.lab.common.Collection;

import java.util.HashSet;
import java.util.Random;

public class IDGenerator {
    private static HashSet<Integer> hashSetID = new HashSet<>();

    public static  Integer gernerateID(){
        Integer id = new Random().nextInt(Integer.MAX_VALUE);

        if (hashSetID.contains(id)) {
            while (hashSetID.contains(id)){
                id = new Random().nextInt(Integer.MAX_VALUE);
            }
        }

        hashSetID.add(id);
        return id;
    }

    static Integer generateID(Integer ID){
        Integer id= ID;

        if (hashSetID.contains(id)){
            while (hashSetID.contains(id)){
                id = new Random().nextInt(Integer.MAX_VALUE);
            }
        }

        hashSetID.add(id);
        return id;
    }
}
