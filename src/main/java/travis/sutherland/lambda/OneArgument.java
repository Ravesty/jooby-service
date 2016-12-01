package travis.sutherland.lambda;

import org.jooby.Request;
import org.jooby.Response;
import org.jooby.Route;

import java.lang.reflect.Parameter;

public interface OneArgument<T1> extends Route.Filter, MethodFinder {
    @Override
    default void handle(final Request req, final Response rsp, final Route.Chain chain) throws Throwable {
        Parameter[] parameters = method().getParameters();
        Object result;
        if (parameters.length == 0) {
            result = handle((T1) getContainingClass().newInstance());
        } else {
            result = handle((T1) ParameterMapper.map(req, parameters[0]));
        }
        rsp.send(result);
        chain.next(req, rsp);
    }

    Object handle(T1 first) throws Throwable;
}
