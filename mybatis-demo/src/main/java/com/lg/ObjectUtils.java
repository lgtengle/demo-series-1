package com.lg;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <b><code>ObjectUtils</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/18 16:27
 *
 * @author tangguzhao
 * @version 0.1.0
 * @since mintaka 0.1.0
 */
public class ObjectUtils {


    public static boolean isNull(Object object) {
        return object == null;
    }


    public static boolean arrayIsNull(Object[] array) {
        return array == null;
    }


    public static boolean arrayIsEmpty(Object[] array) {
        if (arrayIsNull(array)) {
            return true;
        } else {
            return array.length == 0;
        }
    }

    public static boolean listIsNull(List list) {
        return list == null;
    }

    public static boolean listIsEmpty(List list) {
        if (listIsNull(list)) {
            return true;
        } else {
            return list.isEmpty();
        }
    }

    public static boolean setIsNull(Set set) {
        return set == null;
    }

    public static boolean settIsEmpty(Set set) {
        if (setIsNull(set)) {
            return true;
        } else {
            return set.isEmpty();
        }
    }

    public static boolean mapIsNull(Map map) {
        return map == null;
    }

    public static boolean mapIsEmpty(Map map) {
        if (mapIsNull(map)) {
            return true;
        } else {
            return map.isEmpty();
        }
    }


    /**
     * 判断对象是否为空
     *
     * @param object
     * @return
     */
    public static boolean isNullAndEmpty(Object object) {
        if (isNull(object)) {
            return true;
        } else {
            if (object instanceof List) {
                return listIsEmpty((List) object);
            } else if (object instanceof Map) {
                return mapIsEmpty((Map) object);
            } else if (object.getClass().isArray()) {
                return arrayIsEmpty((Object[]) object);
            } else {
                return false;
            }
        }

    }
}
