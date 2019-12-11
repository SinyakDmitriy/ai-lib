package web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

import static io.vertx.core.http.HttpMethod.GET;

public class Server extends AbstractVerticle {

    @Override
    public void start(){
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        StaticHandler staticHandler = StaticHandler.create();
        staticHandler.setWebRoot("src/main/java/web/");
        staticHandler.setIndexPage("index.html");
//        staticHandler.setDirectoryTemplate("static");

        router.route("/*").method(GET).handler(staticHandler);

        server.requestHandler(router);
        server.listen(3131);
    }
}
