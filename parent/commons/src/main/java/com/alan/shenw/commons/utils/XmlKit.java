package com.alan.shenw.commons.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import com.alan.shenw.commons.interfaces.Constants;
import com.alan.shenw.commons.interfaces.Encoding;

/**
 * XML工具类
 * 
 * @author alan.shen
 * @since 1.0
 */
public class XmlKit {

	public static final String DEFAULT_ROOT_ELEMENT_NAME = "data";
	public static final String DEFAULT_FIELD_ELEMENT_NAME = "field";
	public static final String DEFAULT_KEY_ATTRIBUTE_NAME = "name";
	public static final String DEFAULT_VALUE_ATTRIBUTE_NAME = "value";
	public static final String DEFAULT_TYPE_ATTRIBUTE_NAME = "type";

	private boolean hideEmptyAttribute;
	private boolean indent;
	private boolean declaration;
	private String xmlEncoding;
	private String inputEncoding;
	private String rootElementName;
	private String mapElementName;
	private String listElementName;
	private String fieldElementName;
	private String listChildElementName;
	private String keyAttributeName;
	private String valueAttributeName;
	private String typeAttributeName;
	private String indexAttributeName;

	public XmlKit() {
		hideEmptyAttribute = true;
		indent = true;
		declaration = false;
		xmlEncoding = Encoding.UTF_8;
		inputEncoding = Encoding.UTF_8;
		rootElementName = DEFAULT_ROOT_ELEMENT_NAME;
		mapElementName = DEFAULT_FIELD_ELEMENT_NAME;
		listElementName = DEFAULT_FIELD_ELEMENT_NAME;
		fieldElementName = DEFAULT_FIELD_ELEMENT_NAME;
		listChildElementName = DEFAULT_FIELD_ELEMENT_NAME;
		keyAttributeName = DEFAULT_KEY_ATTRIBUTE_NAME;
		valueAttributeName = DEFAULT_VALUE_ATTRIBUTE_NAME;
		typeAttributeName = DEFAULT_TYPE_ATTRIBUTE_NAME;
		indexAttributeName = DEFAULT_KEY_ATTRIBUTE_NAME;
	}

	/**
	 * 对xml的字符串进行缩进格式化
	 * @param xml
	 * @throws DocumentException 
	 * @throws IOException 
	 */
	public static String format(String xml, String indentation) throws DocumentException, IOException {
		SAXReader reader = new SAXReader();
		Document document = (Document) reader.read(new StringReader(xml));
		String requestXml = null;
		XMLWriter writer = null;
		if (document != null) {
			try {
				StringWriter stringWriter = new StringWriter();
				OutputFormat format = new OutputFormat(indentation, true, Encoding.UTF_8);
				writer = new XMLWriter(stringWriter, format);
				writer.write(document);
				writer.flush();
				requestXml = stringWriter.getBuffer().toString();
			} finally {
				if (writer != null) {
					writer.close();
				}
			}
		}
		return requestXml;
	}

	/**
	 * 取得值为空的属性是否隐藏
	 * @return
	 */
	public boolean isHideEmptyAttribute() {
		return hideEmptyAttribute;
	}
	
	/**
	 * 设置值为空的属性是否隐藏，默认值为true
	 */
	public void setHideEmptyAttribute(boolean hideEmptyAttribute) {
		this.hideEmptyAttribute = hideEmptyAttribute;
	}

	/**
	 * 是否换行
	 * @return true:换行, false:不换行
	 */
	public boolean isIndent() {
		return indent;
	}

	/**
	 * 设置是否换行
	 * @param indent true:换行, false:不换行
	 */
	public void setIndent(boolean indent) {
		this.indent = indent;
	}

	public boolean isDeclaration() {
		return declaration;
	}

	public void setDeclaration(boolean declaration) {
		this.declaration = declaration;
	}

	public String getXmlEncoding() {
		return xmlEncoding;
	}

	public void setXmlEncoding(String xmlEncoding) {
		this.xmlEncoding = xmlEncoding;
	}

	public String getInputEncoding() {
		return inputEncoding;
	}

	public void setInputEncoding(String inputEncoding) {
		this.inputEncoding = inputEncoding;
	}

	public String getRootElementName() {
		return rootElementName;
	}

	public void setRootElementName(String rootElementName) {
		this.rootElementName = rootElementName;
	}

	public String getMapElementName() {
		return mapElementName;
	}

	public void setMapElementName(String mapElementName) {
		this.mapElementName = mapElementName;
	}

	public String getListElementName() {
		return listElementName;
	}

	public void setListElementName(String listElementName) {
		this.listElementName = listElementName;
	}

	public String getFieldElementName() {
		return fieldElementName;
	}

	public void setFieldElementName(String fieldElementName) {
		this.fieldElementName = fieldElementName;
	}

	public String getListChildElementName() {
		return listChildElementName;
	}

	public void setListChildElementName(String listChildElementName) {
		this.listChildElementName = listChildElementName;
	}

	public String getKeyAttributeName() {
		return keyAttributeName;
	}

	public void setKeyAttributeName(String keyAttributeName) {
		this.keyAttributeName = keyAttributeName;
	}

	public String getValueAttributeName() {
		return valueAttributeName;
	}

	public void setValueAttributeName(String valueAttributeName) {
		this.valueAttributeName = valueAttributeName;
	}

	public String getTypeAttributeName() {
		return typeAttributeName;
	}

	public void setTypeAttributeName(String typeAttributeName) {
		this.typeAttributeName = typeAttributeName;
	}

	public String getIndexAttributeName() {
		return indexAttributeName;
	}

	public void setIndexAttributeName(String indexAttributeName) {
		this.indexAttributeName = indexAttributeName;
	}

	public TransformerHandler getTransformerHandler() throws TransformerConfigurationException {
		SAXTransformerFactory fac = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		TransformerHandler handler = fac.newTransformerHandler();
		Transformer transformer = handler.getTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, xmlEncoding);
		transformer.setOutputProperty(OutputKeys.INDENT, indent ? Constants.YES : Constants.NO);
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, declaration ? Constants.YES : Constants.NO);
		return handler;
	}

	public void writeXml(Object obj, String filePath) throws FileNotFoundException, TransformerConfigurationException,
			UnsupportedEncodingException, SAXException {
		File targetFile = new File(filePath);
		targetFile.getParentFile().mkdirs();
		FileOutputStream out = new FileOutputStream(targetFile);
		try {
			toXml(obj, out);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	public String toXml(Object obj) throws TransformerConfigurationException, UnsupportedEncodingException,
			SAXException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			toXml(obj, out);
		} finally {
			IOUtils.closeQuietly(out);
		}
		return new String(out.toByteArray());
	}

	public void toXml(Object obj, OutputStream out) throws TransformerConfigurationException,
			UnsupportedEncodingException, SAXException {
		TransformerHandler handler = getTransformerHandler();
		OutputStreamWriter writer = new OutputStreamWriter(out, inputEncoding);
		Result resultXml = new StreamResult(writer);
		handler.setResult(resultXml);
		// start document
		handler.startDocument();
		handler.startElement(Constants.EMPTY, Constants.EMPTY, rootElementName, null);
		// convert object to xml
		toXml(null, obj, handler);
		// end document
		handler.endElement(Constants.EMPTY, Constants.EMPTY, rootElementName);
		handler.endDocument();
	}

	@SuppressWarnings("unchecked")
	public void toXml(String key, Object obj, TransformerHandler handler) throws SAXException {
		if (obj instanceof Map) {
			Attributes attr = getAttribute(keyAttributeName, key, typeAttributeName, Constants.MAP);
			startElement(handler, mapElementName, attr);
			Map<String, ?> map = (Map<String, ?>) obj;
			mapToXml(map, handler);
			handler.endElement(Constants.EMPTY, Constants.EMPTY, mapElementName);
		} else if (obj instanceof List) {
			Attributes attr = getAttribute(keyAttributeName, key, typeAttributeName, Constants.LIST);
			handler.startElement(Constants.EMPTY, Constants.EMPTY, listElementName, attr);
			List<?> list = (List<?>) obj;
			listToXml(list, handler);
			handler.endElement(Constants.EMPTY, Constants.EMPTY, listElementName);
		} else {
			String value;
			String type;
			if (obj == null) {
				value = Constants.EMPTY;
				type = Constants.NULL;
			} else if (obj instanceof String) {
				value = (String) obj;
				type = Constants.STRING;
			} else if (obj instanceof Boolean) {
				Boolean b = (Boolean) obj;
				value = b ? Constants.TRUE : Constants.FALSE;
				type = Constants.BOOLEAN;
			} else if (obj instanceof Integer) {
				value = obj.toString();
				type = Constants.INT;
			} else if (obj instanceof Long) {
				value = obj.toString();
				type = Constants.LONG;
			} else if (obj instanceof Float) {
				value = obj.toString();
				type = Constants.FLOAT;
			} else if (obj instanceof Double) {
				value = obj.toString();
				type = Constants.DOUBLE;
			} else if (obj instanceof BigDecimal) {
				value = obj.toString();
				type = Constants.BIGDECIMAL;
			} else if (obj instanceof Date) {
				Date d = (Date) obj;
				value = String.valueOf(d.getTime());
				type = Constants.TIMESTAMP;
			} else if (obj instanceof Character) {
				Character c = (Character) obj;
				value = c.toString();
				type = Constants.CHAR;
			} else {
				value = obj.toString();
				type = Constants.OBJECT;
			}
			Attributes attr = getFieldAttributes(key, value, type);
			handler.startElement(Constants.EMPTY, Constants.EMPTY, fieldElementName, attr);
			handler.endElement(Constants.EMPTY, Constants.EMPTY, fieldElementName);
		}
	}

	public void mapToXml(Map<String, ?> map, TransformerHandler handler) throws SAXException {
		Set<String> keys = map.keySet();
		Iterator<String> keyIterator = keys.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			Object value = map.get(key);
			toXml(key, value, handler);
		}
	}

	public void listToXml(List<?> list, TransformerHandler handler) throws SAXException {
		for (Integer i = 0; i < list.size(); i++) {
			Attributes attr = getAttribute(indexAttributeName, i.toString());
			handler.startElement(Constants.EMPTY, Constants.EMPTY, listChildElementName, attr);
			Object obj = list.get(i);
			toXml(null, obj, handler);
			handler.endElement(Constants.EMPTY, Constants.EMPTY, listChildElementName);
		}
	}

	/**
	 * 传入的字符串数组会以[key, value, key, value, ... ]形式赋值到Attributes中。 输入参数的长度需要为偶数，如果为基数会抛IllegalArgumentException。
	 * 
	 * @param kvs
	 *            String[]
	 * @return Attributes
	 */
	public Attributes getAttribute(String... kvs) {
		if (kvs.length % 2 != 0) {
			throw new IllegalArgumentException("Input String[] length is not even.");
		}
		AttributesImpl attr = new AttributesImpl();
		for (int i = 0; i < kvs.length; i += 2) {
			if (!StringUtils.isEmpty(kvs[i + 1]) || !hideEmptyAttribute) {
				attr.addAttribute(Constants.EMPTY, Constants.EMPTY, kvs[i], Constants.EMPTY, kvs[i + 1]);
			}
		}
		return attr;
	}

	/**
	 * 开始一个元素
	 * 
	 * @param handler
	 * @param elementName
	 * @param atts
	 * @throws SAXException
	 */
	public void startElement(TransformerHandler handler, String elementName, Attributes atts) throws SAXException {
		handler.startElement(Constants.EMPTY, Constants.EMPTY, elementName, atts);
	}

	/**
	 * 结束一个元素
	 * 
	 * @param handler
	 * @param elementName
	 * @throws SAXException
	 */
	public void endElement(TransformerHandler handler, String elementName) throws SAXException {
		handler.endElement(Constants.EMPTY, Constants.EMPTY, elementName);
	}

	private Attributes getFieldAttributes(String name, String value, String type) {
		return getAttribute(keyAttributeName, name, valueAttributeName, value, typeAttributeName, type);
	}
}