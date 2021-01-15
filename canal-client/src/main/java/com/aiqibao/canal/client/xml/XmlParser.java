package com.aiqibao.canal.client.xml;

import com.aiqibao.canal.client.xml.bean.Field;
import com.aiqibao.canal.client.xml.bean.Source;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:aiqibao
 * @Date:2020/12/10 10:43
 * Best wish!
 */
@Slf4j
public abstract class XmlParser {
    static final String FILE_NAME = "configure.xml";
    public static List<Source> parser() throws DocumentException {
        List<Source> sources = new ArrayList<Source>();
        SAXReader reader = new SAXReader();
        Document doc = reader.read(XmlParser.class.getClassLoader().getResourceAsStream(FILE_NAME));
        log.info("Read table descriptions from classpath: {}", FILE_NAME);
        Element root = doc.getRootElement();
        for (Element sourceNode:(List<Element>)root.elements("source")){
            Attribute nameAttr = sourceNode.attribute("name");
            String sourceName = (nameAttr!=null?nameAttr.getText():null) ;
            Attribute tableAttr = sourceNode.attribute("table") ;
            if (tableAttr == null){
                throw new RuntimeException("<source> tag not contain table attribute") ;
            }
            String table = tableAttr.getText();
            String target = table;

            Attribute targetAttr = sourceNode.attribute("target");
            if (targetAttr != null) {
                target = targetAttr.getText();
            }
            Attribute dbAttr = sourceNode.attribute("db");
            if (dbAttr == null) {
                throw new RuntimeException("<source> tag not contain db attribute");
            }
            Attribute allowDeteleAttr = sourceNode.attribute("allowDetele");
            Boolean allowDetele = false;
            if (allowDeteleAttr != null) {
                allowDetele = Boolean.valueOf(allowDeteleAttr.getText());
            }
            String db = dbAttr.getText();
            Element fieldsNode = sourceNode.element("fields");
            if (fieldsNode == null) {
                throw new RuntimeException("No <fields> found in <source> " + sourceName);
            }
            List<Field> fields = new ArrayList<Field>();
            for (Element fieldNode : (List<Element>) fieldsNode.elements("field")) {
                Attribute fieldNameAttr = fieldNode.attribute("name");
                if (fieldNameAttr == null) {
                    throw new RuntimeException("Parser field error " + fieldNode.asXML());
                }
                String fieldName = fieldNameAttr.getText();
                Attribute fieldTypeAttr = fieldNode.attribute("type");
                String type = (fieldTypeAttr != null ? fieldTypeAttr.getText() : String.class.getName());
                Attribute fieldAliasAttr = fieldNode.attribute("alias");
                String alias = (fieldAliasAttr != null ? fieldAliasAttr.getText() : fieldName);
                Attribute fieldPrimaryKeyAttr = fieldNode.attribute("primaryKey");
                Boolean primaryKey = (fieldPrimaryKeyAttr != null ? new Boolean(fieldPrimaryKeyAttr.getText()) : new Boolean(false));
                fields.add(new Field(fieldName, type, alias, primaryKey));
            }
            sources.add(new Source(sourceName, table, target, db, allowDetele, fields));
        }
        return sources;
    }
}
