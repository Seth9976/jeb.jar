package com.pnfsoftware.jeb.util.encoding.xml;

import java.nio.charset.Charset;
import java.util.List;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class XDocument extends XNode implements Document {
   private List declAttributes;
   private XDocumentType doctype;

   public XDocument(List var1, XDocumentType var2) {
      this.declAttributes = var1;
      this.doctype = var2;
   }

   @Override
   public short getNodeType() {
      return 9;
   }

   @Override
   public String getNodeName() {
      return "#document";
   }

   @Override
   public String getNodeValue() throws DOMException {
      return null;
   }

   @Override
   public DocumentType getDoctype() {
      return this.doctype;
   }

   @Override
   public DOMImplementation getImplementation() {
      throw new UnsupportedOperationException();
   }

   @Override
   public Element getDocumentElement() {
      for (XNode var2 : this.children.list) {
         if (var2 instanceof XElement) {
            return (XElement)var2;
         }
      }

      return null;
   }

   @Override
   public Element createElement(String var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public DocumentFragment createDocumentFragment() {
      throw new UnsupportedOperationException();
   }

   @Override
   public Text createTextNode(String var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Comment createComment(String var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public CDATASection createCDATASection(String var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public ProcessingInstruction createProcessingInstruction(String var1, String var2) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public Attr createAttribute(String var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public EntityReference createEntityReference(String var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public NodeList getElementsByTagName(String var1) {
      return getDescendantElementsByTagName(this, var1);
   }

   @Override
   public Node importNode(Node var1, boolean var2) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public Element createElementNS(String var1, String var2) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public Attr createAttributeNS(String var1, String var2) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public NodeList getElementsByTagNameNS(String var1, String var2) {
      return XNodeList.EMPTY;
   }

   @Override
   public Element getElementById(String var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public String getInputEncoding() {
      return Charset.forName("UTF-8").toString();
   }

   @Override
   public String getXmlEncoding() {
      XAttr var1 = (XAttr)findNodeByName(this.declAttributes, "encoding");
      return var1 == null ? null : var1.getValue();
   }

   @Override
   public boolean getXmlStandalone() {
      XAttr var1 = (XAttr)findNodeByName(this.declAttributes, "standalone");
      return var1 == null ? false : "yes".equals(var1.getValue());
   }

   @Override
   public void setXmlStandalone(boolean var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public String getXmlVersion() {
      return ((XAttr)findNodeByName(this.declAttributes, "version")).getValue();
   }

   @Override
   public void setXmlVersion(String var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean getStrictErrorChecking() {
      return false;
   }

   @Override
   public void setStrictErrorChecking(boolean var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public String getDocumentURI() {
      return null;
   }

   @Override
   public void setDocumentURI(String var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Node adoptNode(Node var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public DOMConfiguration getDomConfig() {
      throw new UnsupportedOperationException();
   }

   @Override
   public void normalizeDocument() {
   }

   @Override
   public Node renameNode(Node var1, String var2, String var3) throws DOMException {
      throw new UnsupportedOperationException();
   }
}
