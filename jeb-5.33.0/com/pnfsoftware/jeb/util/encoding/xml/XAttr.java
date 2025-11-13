package com.pnfsoftware.jeb.util.encoding.xml;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.TypeInfo;

public class XAttr extends XNode implements Attr {
   final String name;
   final String value;
   XElement owner;

   public XAttr(String var1, String var2) {
      this.name = var1;
      this.value = var2;
   }

   @Override
   public short getNodeType() {
      return 2;
   }

   @Override
   public String getNodeName() {
      return this.name;
   }

   @Override
   public String getNodeValue() {
      return this.value;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean getSpecified() {
      throw new UnsupportedOperationException();
   }

   @Override
   public String getValue() {
      return this.value;
   }

   @Override
   public void setValue(String var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public Element getOwnerElement() {
      return this.owner;
   }

   @Override
   public TypeInfo getSchemaTypeInfo() {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean isId() {
      throw new UnsupportedOperationException();
   }

   @Override
   public Document getOwnerDocument() {
      return this.owner == null ? null : this.owner.getOwnerDocument();
   }

   @Override
   public String toString() {
      return Strings.ff("%s=%s", this.name, Formatter.escapeString(this.value));
   }
}
