package travis.sutherland.lambda;

import org.jooby.Request;
import org.jooby.Response;
import org.jooby.Route;

import java.lang.reflect.Parameter;

public interface TwoArguments<T1, T2> extends Route.Filter, MethodFinder {
    @Override
    default void handle(final Request req, final Response rsp, final Route.Chain chain) throws Throwable {
        Parameter[] parameters = method().getParameters();
        Object first = ParameterMapper.map(req, parameters[0]);
        Object result;
        if (parameters.length != 1) {
            result = handle((T1) first, (T2) ParameterMapper.map(req, parameters[1]));
        } else {
            result = handle((T1) getContainingClass().newInstance(), (T2) first);
        }
        rsp.send(result);
        chain.next(req, rsp);
    }

    Object handle(T1 first, T2 second) throws Throwable;
}
