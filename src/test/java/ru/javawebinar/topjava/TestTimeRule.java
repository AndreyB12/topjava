package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by butkoav on 25.04.2017.
 */
public class TestTimeRule implements TestRule {
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Logger logger = getLogger("ru.javawebinar.topjava");
                logger.info("========start======="+description.getMethodName()+"==============================");
                long s = System.currentTimeMillis();
                long e = 0;
                try {
                    base.evaluate();
                } finally {
                    e = System.currentTimeMillis();
                }
                logger.info(description.getMethodName() + "duration millis = " + (e - s));
                logger.info("========end======="+description.getMethodName()+"==============================");

            }
        };
    }
}
