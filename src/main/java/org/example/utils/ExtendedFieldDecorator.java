package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.gui.components.AbstractComponent;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class ExtendedFieldDecorator extends DefaultFieldDecorator {

    private final Logger LOGGER = LogManager.getLogger(ExtendedFieldDecorator.class);

    private WebDriver driver;

    public ExtendedFieldDecorator(ElementLocatorFactory factory, WebDriver driver) {
        super(factory);
        this.driver = driver;
    }

    private List<ExtendedWebElement> proxyForListLocatorExtended(ClassLoader loader, ElementLocator locator, FindBy annotation) {
        InvocationHandler handler = new LocatingElementListHandler(locator);
        List<WebElement> proxy = (List) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return proxy.stream().map(el -> new ExtendedWebElement(el, locator, driver, annotation)).collect(Collectors.toList());
    }

    private List<AbstractComponent> proxyForListLocatorExtended(ClassLoader loader, ElementLocator locator, Class clazz) {
        InvocationHandler handler = new LocatingElementListHandler(locator);
        List<WebElement> proxy = (List) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return proxy.stream().map
                (el -> {
                    try {
                        return (AbstractComponent) clazz.getConstructor(new Class[]{SearchContext.class, WebDriver.class}).
                                newInstance(el, driver);
                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                             InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
    }


    @Override
    public Object decorate(ClassLoader loader, Field field) {
        ElementLocator locator = factory.createLocator(field);
        FindBy findByAnnotation = field.getAnnotation(FindBy.class);
        if (findByAnnotation == null) {
            return null;
        }

        if (field.getType().equals(ExtendedWebElement.class)) {
            if (locator == null) {
                return null;
            }
            return new ExtendedWebElement(locator, driver, findByAnnotation);
        }
        if (AbstractComponent.class.isAssignableFrom(field.getType())) {
            if (locator == null) {
                return null;
            }
            try {
                return field.getType().getConstructor(new Class[]{SearchContext.class, WebDriver.class}).
                        newInstance(proxyForLocator(loader, locator), driver);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        if (List.class.isAssignableFrom(field.getType())) {
            ParameterizedType pT = (ParameterizedType) field.getGenericType();
            Class<?> typeListClass = (Class<?>) pT.getActualTypeArguments()[0];
            if (AbstractComponent.class.isAssignableFrom(typeListClass)) {
                return proxyForListLocatorExtended(loader, locator, typeListClass);
            }
        }
        return List.class.isAssignableFrom(field.getType()) ?
                this.proxyForListLocatorExtended(loader, locator, findByAnnotation) : null;
    }
}