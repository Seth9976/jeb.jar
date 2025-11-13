package com.pnfsoftware.jeb.util.encoding.xml;

import org.w3c.dom.DOMException;
import org.w3c.dom.Text;

public class XText extends XCharacterData implements Text {
   public XText(String var1) {
      super(var1);
   }

   @Override
   public short getNodeType() {
      return 3;
   }

   @Override
   public String getNodeName() {
      return "#text";
   }

   @Override
   public Text splitText(int var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean isElementContentWhitespace() {
      return false;
   }

   @Override
   public String getWholeText() {
      return this.value;
   }

   @Override
   public Text replaceWholeText(String var1) throws DOMException {
      throw new UnsupportedOperationException();
   }
}
