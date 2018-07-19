package ru.ok.qa.tests;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class ParallelTests {

    private static final Logger LOG = Logger.getLogger(ParallelTests.class.getName());

    @Ignore
    @Test
    public void runAllTestInParallel() {
        Class[] classes = {CorrectChangePersonalDataTest.class, IncorrectChangePersonalDataTest.class,
                IncorrectChangeNameSurnameTest.class};
        Result result = JUnitCore.runClasses(ParallelComputer.classes(), classes);
        result.getFailures().forEach(failure -> LOG.error(failure.toString()));
        Assert.assertTrue(result.wasSuccessful());
    }
}
