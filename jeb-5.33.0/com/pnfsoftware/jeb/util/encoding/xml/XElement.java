package com.pnfsoftware.jeb.util.encoding.xml;

import java.util.List;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

public class XElement extends XNode implements Element {
   String name;
   XNamedNodeMap attrmap = XNamedNodeMap.EMPTY;

   public XElement(String var1) {
      this.name = var1;
   }

   public void initAttributes(List var1) {
      this.attrmap = new XNamedNodeMap(var1);
   }

   @Override
   public short getNodeType() {
      return 1;
   }

   @Override
   public String getNodeName() {
      return this.name;
   }

   @Override
   public String getNodeValue() throws DOMException {
      return null;
   }

   @Override
   public String getTagName() {
      return this.name;
   }

   @Override
   public NamedNodeMap getAttributes() {
      return this.attrmap;
   }

   @Override
   public String getAttribute(String var1) {
      Attr var2 = (Attr)this.attrmap.getNamedItem(var1);
      return var2 == null ? "" : var2.getValue();
   }

   @Override
   public void setAttribute(String var1, String var2) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public void removeAttribute(String var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public Attr getAttributeNode(String var1) {
      return (Attr)this.attrmap.getNamedItem(var1);
   }

   @Override
   public Attr setAttributeNode(Attr var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public Attr removeAttributeNode(Attr var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public NodeList getElementsByTagName(String var1) {
      return getDescendantElementsByTagName(this, var1);
   }

   @Override
   public String getAttributeNS(String var1, String var2) throws DOMException {
      Attr var3 = (Attr)this.attrmap.getNamedItemNS(var1, var2);
      return var3 == null ? "" : var3.getValue();
   }

   @Override
   public void setAttributeNS(String var1, String var2, String var3) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public void removeAttributeNS(String var1, String var2) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public Attr getAttributeNodeNS(String var1, String var2) throws DOMException {
      return (Attr)this.attrmap.getNamedItemNS(var1, var2);
   }

   @Override
   public Attr setAttributeNodeNS(Attr var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public NodeList getElementsByTagNameNS(String var1, String var2) throws DOMException {
      return XNodeList.EMPTY;
   }

   @Override
   public boolean hasAttribute(String var1) {
      return this.getAttributeNode(var1) != null;
   }

   @Override
   public boolean hasAttributeNS(String var1, String var2) throws DOMException {
      return this.getAttributeNodeNS(var1, var2) != null;
   }

   @Override
   public TypeInfo getSchemaTypeInfo() {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setIdAttribute(String var1, boolean var2) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setIdAttributeNS(String var1, String var2, boolean var3) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setIdAttributeNode(Attr var1, boolean var2) throws DOMException {
      throw new UnsupportedOperationException();
   }
}
