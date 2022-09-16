module com.d.passwordmanager {
    requires druid;
    requires com.dlsc.formsfx;
    requires java.annotation;
    requires validatorfx;
//    requires org.apiguardian.api;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.mybatis;
    requires org.mybatis.spring;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
//    requires org.opentest4j;
//    requires org.junit.jupiter.api;
    requires spring.aop;
    requires spring.beans;
    requires spring.context;
    requires spring.core;
    requires spring.expression;
    requires spring.jcl;
    requires spring.jdbc;
    requires spring.test;
    requires spring.tx;
    requires org.xerial.sqlitejdbc;
//
//    exports com.d.passwordmanager.command.constant;
//    exports com.d.passwordmanager.pojo;
//    exports com.d.passwordmanager.controller;
//    exports com.d.passwordmanager.service;
//    exports com.d.passwordmanager.mapper;
    exports com.d.passwordmanager;

    opens com.d.passwordmanager.controller to javafx.fxml;
    opens com.d.passwordmanager to javafx.fxml;
}