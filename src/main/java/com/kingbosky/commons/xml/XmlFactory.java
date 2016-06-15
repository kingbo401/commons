/**
 * $Id$
 * Copyright(C) 2011-2016 dreamingame.com. All rights reserved.
 */
package com.kingbosky.commons.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import com.kingbosky.commons.exception.GeneralException;
import com.kingbosky.commons.util.CloseUtil;
import com.kingbosky.commons.xml.core.XmlCreatorBase;
import com.kingbosky.commons.xml.core.XmlParserBase;

/**
 * 
 * @author <a href="mailto:shiyang.zhao@dreamingame.com">Rex Zhao</a>
 * @version 1.0
 * @since 1.0 Dec 20, 2011 1:55:48 AM
 */
public class XmlFactory {

	private XmlFactory() {}
	
	/**
     * 解析XML文件
     * @param xmlFile XML文件对象
     * @return 解析后的XML对象
     */
    public static XmlParser parseXmlFile(File xmlFile) {
        InputStream is = null;
        try {
            is = new FileInputStream(xmlFile);
            return parseXmlStream(is, false);
        } catch (FileNotFoundException e) {
        	throw new GeneralException("无法找到Xml文件:" + xmlFile, e);
        } finally {
            CloseUtil.close(is);
        }
    }

    /**
     * 根据输入流解析XML对象
     * @param is XML输入流
     * @return 解析后的XML对象
     * @see #parseXmlStream(InputStream, boolean)
     */
    public static XmlParser parseXmlStream(InputStream is) {
    	return parseXmlStream(is, false);
    }
    /**
     * 根据输入流解析XML对象
     * @param is XML输入流
     * @param forceClose 解析后是否关闭输入流
     * @return 解析后的XML对象
     */
    public static XmlParser parseXmlStream(InputStream is, boolean forceClose) {
        SAXReader saxReader = new SAXReader();
        try {
            return new XmlParserBase(saxReader.read(is));
        } catch (DocumentException e) {
        	throw new GeneralException("解析XML失败", e);
        } finally {
        	if (forceClose) CloseUtil.close(is);
        }
    }

    /**
     * 根据XML片段解析XML
     * @param xmlDocumentString XML字符串
     * @return 解析后的XML对象
     */
    public static XmlParser parseXmlString(String xmlDocumentString) {
        StringReader reader = new StringReader(xmlDocumentString);
        InputSource source = new InputSource(reader);
        return parseXmlInputSource(source);
    }

    /**
     * 根据输入对象解析XML
     * @param source 输入对象
     * @return 解析后的XML对象
     */
    public static XmlParser parseXmlInputSource(InputSource source) {
        try {
        	return new XmlParserBase(new SAXReader().read(source));
        } catch (DocumentException e) {
        	throw new GeneralException("无法解析XML", e);
        }
    }
    
    public static XmlCreator createXml() {
    	return new XmlCreatorBase();
    }
    
    public static XmlCreator createXml(File xmlFile) {
    	return new XmlCreatorBase(parseXmlFile(xmlFile));
    }
    
    public static XmlCreator createXml(XmlParser parser) {
    	return new XmlCreatorBase(parser);
    }
}
