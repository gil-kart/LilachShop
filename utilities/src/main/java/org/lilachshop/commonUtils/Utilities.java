package org.lilachshop.commonUtils;

import java.util.List;

public class Utilities {
    /**
     * Converts Object to it's type.
     * Note: Any type of list will be represented as a generic list... try it out.
     *
     * @param o Object to get it's type
     * @return String representing it's type
     */
    static public String getClassType(Object o) {
        String type = o.getClass().getTypeName();
        String sub_type = "";
        if (type.contains("List")) {
            try {
                var contained_object = ((List<?>) o).get(0);
                sub_type = contained_object.getClass().getSimpleName();
            } catch (ArrayIndexOutOfBoundsException e) {
                return null;
            }
            type = "List<" + sub_type + ">";
        } else {
            String[] full_subtype = type.split("\\.");
            type = full_subtype[full_subtype.length - 1];
        }
        return type;
    }
}
