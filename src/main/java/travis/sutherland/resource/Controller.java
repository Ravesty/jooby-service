package travis.sutherland.resource;

import java.util.Optional;

/**
 * @author Travis on 11/29/2016.
 */
public class Controller {

    public Object doSomething(Integer id, String name) {
        return "Do Something id=" + id + " name=" + name;
    }

    public Object oneArgumentPrimitiveInt(int id) {
        return "One Argument " + id;
    }

    public static Object twoStaticArgumentsIntegerString(Integer id, String name) {
        return "Two static name=" + name + " id=" + id;
    }

    public String noArguments() {
        return "No Arguments ";
    }

    public String optional(Optional<String> op) {
        if(op.isPresent()){
            return "Optional is " + op.get();
        }
        return "Optional is not present";
    }

    public String staticOptional(Optional<String> op) {
        if(op.isPresent()){
            return "Static Optional is " + op.get();
        }
        return "Static Optional is not present";
    }
}
