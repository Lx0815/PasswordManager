module com.d.passwordmanager {
    requires java.base;
    requires java.compiler;
    requires java.sql;
    requires druid;
    requires com.dlsc.formsfx;
    requires java.annotation;
    requires validatorfx;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.mybatis;
    requires org.mybatis.spring;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires slf4j.api;
    requires logback.core;
    requires logback.classic;
    requires org.aspectj.weaver;
    requires org.apache.commons.lang3;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.codec;
    requires org.apache.commons.text;

    requires spring.aop;
    requires spring.beans;
    requires spring.context;
    requires spring.core;
    requires spring.expression;
    requires spring.jdbc;

    requires spring.tx;
    requires org.xerial.sqlitejdbc;


    exports com.d.passwordmanager.command.constant;
    exports com.d.passwordmanager.command.utils;
    exports com.d.passwordmanager.pojo;
    exports com.d.passwordmanager.controller;
    exports com.d.passwordmanager.service;
    exports com.d.passwordmanager.service.impl;
    exports com.d.passwordmanager.mapper;
    exports com.d.passwordmanager.views;


    opens com.d.passwordmanager.controller to javafx.fxml, spring.core;
    opens com.d.passwordmanager.views to javafx.fxml;
    opens com.d.passwordmanager.service to javafx.fxml, spring.core;
    opens com.d.passwordmanager.service.impl to spring.core;
    opens com.d.passwordmanager.service.aop to spring.core, spring.beans, spring.aop;

    opens com.d.passwordmanager.mapper to spring.core, spring.context, spring.test, spring.jdbc, spring.beans;
    exports com.d.passwordmanager;
    opens com.d.passwordmanager to javafx.fxml;
    exports com.d.passwordmanager.command.csv;
}