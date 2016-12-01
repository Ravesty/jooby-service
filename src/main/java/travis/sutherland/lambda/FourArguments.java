package travis.sutherland.lambda;

import org.jooby.Request;
import org.jooby.Response;
import org.jooby.Route;

import java.lang.reflect.Parameter;

public interface FourArguments<T1, T2, T3, T4> extends Route.Filter, MethodFinder {

    @Override
    default void handle(final Request req, final Response rsp, final Route.Chain chain) throws Throwable {
        Parameter[] parameters = method().getParameters();
        Object first = ParameterMapper.map(req, parameters[0]);
        Object second = ParameterMapper.map(req, parameters[1]);
        Object third = ParameterMapper.map(req, parameters[2]);
        Object result;
        if (parameters.length != 3) {
            result = handle((T1) first, (T2) second, (T3) third, (T4) ParameterMapper.map(req, parameters[3]));
        } else {
            result = handle((T1) getContainingClass().newInstance(), (T2) first, (T3) second, (T4) third);
        }
        rsp.send(result);
        chain.next(req, rsp);
    }

    Object handle(T1 first, T2 second, T3 third, T4 fourth) throws Throwable;
}
