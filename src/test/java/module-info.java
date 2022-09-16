module test.passwordmanager {

    requires com.d.passwordmanager;
    requires spring.test;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.engine;
    requires spring.core;
    requires spring.beans;

    exports test.passwordmanager.mapper;

    opens test.passwordmanager.mapper to spring.core, org.junit.platform.commons;

}