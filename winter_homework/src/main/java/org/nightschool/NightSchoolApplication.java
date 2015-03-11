package org.nightschool;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.nightschool.controller.CartController;
import org.nightschool.controller.CommodityController;
import org.nightschool.controller.UserController;

import java.io.IOException;


public class NightSchoolApplication extends Application<NightSchoolConfiguration> {
    public static void main(String[] args) throws Exception {
        new NightSchoolApplication().run(args);
    }


    @Override
    public void initialize(Bootstrap<NightSchoolConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/webapp/html","/app/"));
    }

    @Override
    public void run(NightSchoolConfiguration configuration,
                    Environment environment) throws IOException {
        environment.jersey().register(new UserController());
        environment.jersey().register(new CartController());
        environment.jersey().register(new CommodityController());
    }
}