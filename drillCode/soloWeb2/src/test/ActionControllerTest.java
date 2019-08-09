package test;

import org.junit.jupiter.api.Test;

import biz.controller.ActionController;

class ActionControllerTest extends ActionController
{
    @Test
    void test()
    {
        String test = System.getProperty("DEFAULT_JNDINAME");
        System.out.println(test);
    }
    
}
