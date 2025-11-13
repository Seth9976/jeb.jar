package com.pnfsoftware.jeb.util.encoding.xml;

import org.w3c.dom.Comment;

public class XComment extends XCharacterData implements Comment {
   public XComment(String var1) {
      super(var1);
   }

   @Override
   public short getNodeType() {
      return 8;
   }

   @Override
   public String getNodeName() {
      return "#comment";
   }
}
