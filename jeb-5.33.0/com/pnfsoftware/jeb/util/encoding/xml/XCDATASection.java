package com.pnfsoftware.jeb.util.encoding.xml;

import org.w3c.dom.CDATASection;

public class XCDATASection extends XText implements CDATASection {
   public XCDATASection(String var1) {
      super(var1);
   }

   @Override
   public short getNodeType() {
      return 4;
   }

   @Override
   public String getNodeName() {
      return "#cdata-section";
   }
}
