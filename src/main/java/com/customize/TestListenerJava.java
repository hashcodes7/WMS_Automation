package com.customize;

import java.util.List;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

public class TestListenerJava implements IReporter {

public void generateReport(List<XmlSuite> xml, List<ISuite> suites, String outdir) {}
}