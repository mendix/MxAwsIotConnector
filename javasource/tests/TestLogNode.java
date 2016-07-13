package tests;

import com.mendix.logging.ILogNode;
import com.mendix.logging.LogLevel;
import com.mendix.logging.LogSubscriber;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by ako on 9/19/2015.
 */
public class TestLogNode implements ILogNode {
    private Logger logger = null;

    public TestLogNode(String name) {
        logger = Logger.getLogger(name);

    }
    public TestLogNode(){
        logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public void log(LogLevel logLevel, Object o, Throwable throwable) {
    }

    @Override
    public void log(LogLevel logLevel, Object o) {

    }

    @Override
    public void log(LogLevel logLevel, Throwable throwable) {

    }

    @Override
    public void critical(Object o, Throwable throwable) {

    }

    @Override
    public void critical(Object o) {

    }

    @Override
    public void critical(Throwable throwable) {

    }

    @Override
    public void error(Object o, Throwable throwable) {

    }

    @Override
    public void error(Object o) {

    }

    @Override
    public void error(Throwable throwable) {

    }

    @Override
    public void warn(Object o, Throwable throwable) {

    }

    @Override
    public void warn(Object o) {

    }

    @Override
    public void warn(Throwable throwable) {

    }

    @Override
    public void info(Object o, Throwable throwable) {

    }

    @Override
    public void info(Object o) {
        logger.info(o.toString());
    }

    @Override
    public void info(Throwable throwable) {

    }

    @Override
    public void debug(Object o, Throwable throwable) {

    }

    @Override
    public void debug(Object o) {

    }

    @Override
    public void debug(Throwable throwable) {

    }

    @Override
    public void trace(Object o, Throwable throwable) {

    }

    @Override
    public void trace(Object o) {

    }

    @Override
    public void trace(Throwable throwable) {

    }

    @Override
    public void subscribe(LogSubscriber logSubscriber, LogLevel logLevel) {

    }

    @Override
    public void unsubscribe(LogSubscriber logSubscriber) {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public Map<LogSubscriber, LogLevel> getSubscribers() {
        return null;
    }
}
