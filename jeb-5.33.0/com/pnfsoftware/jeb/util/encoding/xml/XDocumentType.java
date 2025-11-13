package com.pnfsoftware.jeb.util.encoding.xml;

import org.w3c.dom.DOMException;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;

public class XDocumentType extends XNode implements DocumentType {
   String name;
   String publicId;
   String systemId;

   XDocumentType(String var1, String var2, String var3) {
      this.name = var1;
      this.publicId = var2;
      this.systemId = var3;
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
   public short getNodeType() {
      return 10;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public String getPublicId() {
      return this.publicId;
   }

   @Override
   public String getSystemId() {
      return this.systemId;
   }

   @Override
   public NamedNodeMap getEntities() {
      return XNamedNodeMap.EMPTY;
   }

   @Override
   public NamedNodeMap getNotations() {
      return XNamedNodeMap.EMPTY;
   }

   @Override
   public String getInternalSubset() {
      return null;
   }
}
