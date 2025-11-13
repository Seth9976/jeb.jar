package com.pnfsoftware.jeb.util.encoding.xml;

import java.util.Collections;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XNodeList implements NodeList {
   static final XNodeList EMPTY = new XNodeList(Collections.emptyList());
   List list;

   public XNodeList(List var1) {
      this.list = var1;
   }

   @Override
   public Node item(int var1) {
      return var1 >= 0 && var1 < this.list.size() ? (Node)this.list.get(var1) : null;
   }

   @Override
   public int getLength() {
      return this.list.size();
   }
}
