package travis.sutherland.lambda;

import org.jooby.Mutant;
import org.jooby.Request;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParameterMapper {
    private static Map<Class<?>, Object> defaultValues = new HashMap<>();

    static {
        defaultValues.put(int.class, 0);
        defaultValues.put(Integer.class, 0);
        defaultValues.put(boolean.class, false);
        defaultValues.put(Boolean.class, false);
        defaultValues.put(byte.class, (byte) 0);
        defaultValues.put(Byte.class, 0);
        defaultValues.put(char.class, ' ');
        defaultValues.put(Character.class, ' ');
        defaultValues.put(short.class, (short) 0.0);
        defaultValues.put(Short.class, (short) 0.0);
        defaultValues.put(long.class, 0l);
        defaultValues.put(Long.class, 0L);
        defaultValues.put(float.class, 0.0f);
        defaultValues.put(Float.class, 0.0f);
        defaultValues.put(double.class, 0.0d);
        defaultValues.put(Double.class, 0.0d);
        defaultValues.put(Optional.class, null);
    }

    public static <T> T defaultValue(Class<?> type) {
        Object o = defaultValues.get(type);
        if (o == null) {
            try {
                return (T) type.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e); // Maybe just return null?
            }
        }
        return (T) o;
    }

    public static Object map(Request req, Parameter parameter) {
        Mutant mutant = req.param(parameter.getName());
        Class<?> type = parameter.getType();
        if (type == Optional.class) {
            return mutant.toOptional();
        } else {
            if (mutant.isSet()) {
                return mutant.to(type);
            } else {
                return defaultValue(type);
            }
        }
    }
}