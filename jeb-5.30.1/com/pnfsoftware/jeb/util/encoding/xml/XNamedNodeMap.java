package com.pnfsoftware.jeb.util.encoding.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class XNamedNodeMap implements NamedNodeMap {
   static final XNamedNodeMap EMPTY = new XNamedNodeMap(Collections.emptyList());
   List list = new ArrayList();
   Map map = new HashMap();

   XNamedNodeMap(List var1) {
      this.list = var1;
      this.map = new HashMap(var1.size());
      var1.forEach(var1x -> this.map.put(var1x.getNodeName(), var1x));
   }

   @Override
   public Node getNamedItem(String var1) {
      return (Node)this.map.get(var1);
   }

   @Override
   public Node setNamedItem(Node var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public Node removeNamedItem(String var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public Node item(int var1) {
      return var1 >= 0 && var1 < this.list.size() ? (Node)this.list.get(var1) : null;
   }

   @Override
   public int getLength() {
      return this.map.size();
   }

   @Override
   public Node getNamedItemNS(String var1, String var2) throws DOMException {
      return var1 == null ? (Node)this.map.get(var2) : null;
   }

   @Override
   public Node setNamedItemNS(Node var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public Node removeNamedItemNS(String var1, String var2) throws DOMException {
      throw new UnsupportedOperationException();
   }
}
