package travis.sutherland;

import org.jooby.Jooby;
import org.jooby.Request;
import org.jooby.Response;
import org.jooby.json.Jackson;
import travis.sutherland.model.Example;
import travis.sutherland.resource.Controller;

import java.util.Optional;

/**
 * @author Travis Sutherland
 */
public class App extends Jooby {

    {
        use(new Jackson());
        get("/NormalRequestResponse", (Request req, Response resp) -> resp.send(req.param("id").to(String.class)));
        get("/normalRequestResponse/{id:\\d+}", (Request req, Response resp) -> resp.send(req.param("id").toString()));
        get("/fourArgumentsWithDifferentNames", (Integer integer, String someString, Integer anotherInteger, String anotherString) -> new Example(integer, someString + anotherString, anotherInteger));
        get("/fourArguments", (Integer first, String second, Integer third, String fourth) -> new Example(first, second + fourth, third));
        get("/twoStatic", Controller::twoStaticArgumentsIntegerString);
        get("/one", Controller::oneArgumentPrimitiveInt);
        get("/none", Controller::noArguments);
        get("/optional", Controller::optional);
        get("/staticOptional", Controller::staticOptional);
        get("/", Controller::doSomething);
        get("/killer", (Optional<String> foo) -> {
            String test;
            if (foo.isPresent()) {
                test = foo.get();
            } else {
                test = " no foo.";
            }
            return "Foo this! foo=" + test;
        });
    }

    public static void main(final String[] args) {
        run(App::new, args);
    }

}



