package uz.furor.template.utils;

import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Utils {

    /**
     * This method generates a map of objects.
     *
     * @param objects length should be even  like (2, 4, 6, 8, ...) not odd because first is key end second is value
     * @return new Map object.
     */
    public static Map<String, Object> generateMap(Object... objects) throws IllegalArgumentException {
        if (Objects.isNull(objects))
            return new HashMap<>();
        if (objects.length % 2 != 0)
            throw new IllegalArgumentException("Objects length should be even like (2, 4, 6, 8, ...) and length is " + objects.length);
        Map<String, Object> map = new HashMap<>();
        int i = 1;
        String key = null;
        for (Object o : objects)
            if (i++ % 2 == 1)
                key = (String) o;
            else map.put(key, o);
        return map;
    }

    public static RowBounds parseRowBounds(Map<String, Object> map) {
        try {
            int page = Integer.parseInt(map.get("page").toString());
            int per_page = Integer.parseInt(map.get("per_page").toString());
            return new RowBounds(page - 1, page * per_page);
        } catch (Exception e) {
            return RowBounds.DEFAULT;
        }
    }
}
