package com.alan.shenw.commons.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.alan.shenw.commons.interfaces.Constants;
import com.alan.shenw.commons.interfaces.Encoding;
import com.alan.shenw.commons.test.TestKit;
import com.alan.shenw.commons.test.annotation.CheckMethodWithoutTest;

@CheckMethodWithoutTest(src = XmlKit.class)
public class XmlKitTest extends TestKit {

	private Map<String, Object> getTestMapData() {
		HashMap<String, Object> smap1 = new HashMap<String, Object>();
		smap1.put("a1", "aa1");
		smap1.put("a2", "aa2");

		HashMap<String, Object> smap2 = new HashMap<String, Object>();
		smap2.put("a3", "aa3");
		smap2.put("a4", "aa4");

		HashMap<String, Object> smap3 = new HashMap<String, Object>();
		smap3.put("a5", "aa5");
		smap3.put("a6", "aa6");

		HashMap<String, Object> smap4 = new HashMap<String, Object>();
		smap4.put("a7", "aa7");
		smap4.put("a8", "aa8");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(smap2);
		list.add(smap4);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("a", "100");
		map.put("d", 200);
		map.put("c", 300L);
		map.put("d", 3.14);
		map.put("f", true);
		map.put("h", 'x');
		map.put("i", null);
		map.put("j", DateKit.get(2012, 10, 1));
		map.put("k", new BigDecimal("1234567890.0123456789"));
		map.put("cn", "系统异常");
		map.put("smap1", smap1);
		map.put("slist", list);
		map.put("smap3", smap3);

		return map;
	}

	@Test
	public void testToXml() throws TransformerConfigurationException, SAXException, IOException {
		Map<String, Object> map = getTestMapData();

		String output = new XmlKit().toXml(map);

		String expected = FileUtils.readFileToString(new File("src/test/resources/toxml.xml"), Encoding.UTF_8);

		assertEquals(expected, output);
	}

	@Test
	public void testToXmlWithOutputStream() throws IOException, TransformerConfigurationException, SAXException {
		File temp = File.createTempFile("test-", ".xml");
		try {
			Map<String, Object> map = getTestMapData();

			FileOutputStream out = new FileOutputStream(temp);

			new XmlKit().toXml(map, out);

			out.flush();
			out.close();

			String context1 = FileUtils.readFileToString(new File("src/test/resources/toxml.xml"), Encoding.UTF_8);
			String context2 = FileUtils.readFileToString(temp, Encoding.UTF_8);
			assertEquals(context1, context2);
		} finally {
			FileUtils.deleteQuietly(temp);
		}
	}

	@Test
	public void testToXmlWithTransformerHandler() throws TransformerConfigurationException, IOException, SAXException {
		File temp = File.createTempFile("test-", ".xml");
		OutputStream out = new FileOutputStream(temp);
		try {
			try {
				XmlKit xmlKit = new XmlKit();
				TransformerHandler handler = xmlKit.getTransformerHandler();

				OutputStreamWriter writer = new OutputStreamWriter(out, Encoding.UTF_8);
				Result resultXml = new StreamResult(writer);
				handler.setResult(resultXml);

				Map<String, Object> map = getTestMapData();

				handler.startElement(Constants.EMPTY, Constants.EMPTY, "data", null);
				xmlKit.toXml(Constants.EMPTY, map, handler);
				handler.endElement(Constants.EMPTY, Constants.EMPTY, "data");

				handler.endDocument();
			} finally {
				IOUtils.closeQuietly(out);
			}

			String context1 = FileUtils.readFileToString(new File("src/test/resources/toxml.xml"), Encoding.UTF_8);
			String context2 = FileUtils.readFileToString(temp, Encoding.UTF_8);
			assertEquals(context1, context2);
		} finally {
			FileUtils.deleteQuietly(temp);
		}
	}

	@Test
	public void testFormat() throws IOException, DocumentException {
		String xml = FileUtils.readFileToString(new File("src/test/resources/toxml.xml"), Encoding.UTF_8);
		xml = XmlKit.format(xml, "    ");

		String expected = FileUtils.readFileToString(new File("src/test/resources/toxml_formated.xml"), Encoding.UTF_8);

		assertEquals(expected, xml);
	}

	@Test
	public void testWriteXml() throws TransformerConfigurationException, SAXException, IOException {
		File temp = File.createTempFile("test-", ".xml");
		try {
			Map<String, Object> map = getTestMapData();
			new XmlKit().writeXml(map, temp.getAbsolutePath());

			String context1 = FileUtils.readFileToString(new File("src/test/resources/toxml.xml"), Encoding.UTF_8);
			String context2 = FileUtils.readFileToString(temp, Encoding.UTF_8);
			assertEquals(context1, context2);
		} finally {
			FileUtils.deleteQuietly(temp);
		}
	}

	@Test
	public void testStartElement() throws UnsupportedEncodingException, TransformerConfigurationException, SAXException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		XmlKit xmlKit = new XmlKit();
		xmlKit.setIndent(false);
		TransformerHandler handler = xmlKit.getTransformerHandler();

		OutputStreamWriter writer = new OutputStreamWriter(out, Encoding.UTF_8);
		Result resultXml = new StreamResult(writer);

		handler.setResult(resultXml);

		xmlKit.startElement(handler, "test", null);
		xmlKit.endElement(handler, "test");

		handler.endDocument();

		String context = new String(out.toByteArray());
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><test/>", context);
	}
	
	@Test
	public void testEndElement() throws UnsupportedEncodingException, TransformerConfigurationException, SAXException {
		testStartElement();
	}

	@Test
	public void testMapToXml() throws TransformerConfigurationException, UnsupportedEncodingException, SAXException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		XmlKit xmlKit = new XmlKit();
		xmlKit.setIndent(false);
		TransformerHandler handler = xmlKit.getTransformerHandler();

		OutputStreamWriter writer = new OutputStreamWriter(out, Encoding.UTF_8);
		Result resultXml = new StreamResult(writer);

		handler.setResult(resultXml);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("age", 28);

		xmlKit.mapToXml(map, handler);

		handler.endDocument();

		String context = new String(out.toByteArray());
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><field name=\"age\" value=\"28\" type=\"int\"/>", context);
	}

	@Test
	public void testListToXml() throws UnsupportedEncodingException, TransformerConfigurationException, SAXException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XmlKit xmlKit = new XmlKit();
		xmlKit.setIndent(false);
		TransformerHandler handler = xmlKit.getTransformerHandler();

		OutputStreamWriter writer = new OutputStreamWriter(out, Encoding.UTF_8);
		Result resultXml = new StreamResult(writer);

		handler.setResult(resultXml);

		List<Object> list = new ArrayList<Object>();
		list.add(5);
		list.add("abc");

		xmlKit.listToXml(list, handler);

		handler.endDocument();

		String context = new String(out.toByteArray());
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><field name=\"0\"><field value=\"5\" type=\"int\"/></field><field name=\"1\"><field value=\"abc\" type=\"string\"/></field>", context);
	}
}
