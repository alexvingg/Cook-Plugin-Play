/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Controller;

/**
 *
 * @author alex
 */
public class DefaultController extends Controller {

    @Before
    public static void setTema() {
        String tema = Cache.get("tema", String.class);
        if (tema == null) {
            tema = "redmond";
            Cache.set("tema", tema, "30mn");
        }
        renderArgs.put("tema", tema);
    }

    public static void changeTema(String tema){
        Cache.set("tema", tema, "30mn");
        redirect(Cache.get("url", String.class));
    }

    @Before
    public static void changeController()
    {
        if(! request.controller.equals("DefaultController"))
        {
            Cache.set("url", request.url, "30mn");
        }
    }
}

