package com.pnfsoftware.jeb.util.encoding.xml;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public abstract class XNode implements Node {
   XNodeList children = XNodeList.EMPTY;
   XNode parent;
   volatile Map datamap;

   public void initChildren(List var1) {
      this.children = new XNodeList(var1);
   }

   @Override
   public void setNodeValue(String var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public Node getParentNode() {
      return this.parent;
   }

   @Override
   public NodeList getChildNodes() {
      return this.children;
   }

   @Override
   public Node getFirstChild() {
      return this.children.list.isEmpty() ? null : (Node)this.children.list.get(0);
   }

   @Override
   public Node getLastChild() {
      return this.children.list.isEmpty() ? null : (Node)this.children.list.get(this.children.list.size() - 1);
   }

   @Override
   public Node getPreviousSibling() {
      throw new UnsupportedOperationException();
   }

   @Override
   public Node getNextSibling() {
      throw new UnsupportedOperationException();
   }

   @Override
   public NamedNodeMap getAttributes() {
      return null;
   }

   @Override
   public Document getOwnerDocument() {
      Object var1 = this;

      while (var1 != null) {
         var1 = var1.getParentNode();
         if (var1 instanceof Document) {
            return (Document)var1;
         }
      }

      return null;
   }

   @Override
   public Node insertBefore(Node var1, Node var2) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public Node replaceChild(Node var1, Node var2) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public Node removeChild(Node var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public Node appendChild(Node var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean hasChildNodes() {
      return this.children.getLength() > 0;
   }

   @Override
   public Node cloneNode(boolean var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void normalize() {
   }

   @Override
   public boolean isSupported(String var1, String var2) {
      return false;
   }

   @Override
   public String getNamespaceURI() {
      return null;
   }

   @Override
   public String getPrefix() {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setPrefix(String var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public String getLocalName() {
      return null;
   }

   @Override
   public boolean hasAttributes() {
      return this.getAttributes() != null && this.getAttributes().getLength() > 0;
   }

   @Override
   public String getBaseURI() {
      return null;
   }

   @Override
   public short compareDocumentPosition(Node var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public String getTextContent() throws DOMException {
      if (this.ofType(1, 6, 5, 11)) {
         StringBuilder var1 = new StringBuilder();

         for (XNode var3 : this.children.list) {
            if (var3.getNodeType() != 8 && var3.getNodeType() != 7) {
               var1.append(var3.getTextContent());
            }
         }

         return var1.toString();
      } else if (this.ofType(2, 3, 4, 8, 7)) {
         return this.getNodeValue();
      } else {
         return this.ofType(9, 10, 12) ? null : null;
      }
   }

   @Override
   public void setTextContent(String var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean isSameNode(Node var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public String lookupPrefix(String var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean isDefaultNamespace(String var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public String lookupNamespaceURI(String var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean isEqualNode(Node var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Object getFeature(String var1, String var2) {
      return null;
   }

   @Override
   public Object setUserData(String var1, Object var2, UserDataHandler var3) {
      if (this.datamap == null) {
         if (var2 == null) {
            return null;
         }

         synchronized (this) {
            if (this.datamap == null) {
               this.datamap = new ConcurrentHashMap();
            }
         }
      }

      return var2 == null ? this.datamap.remove(var1) : this.datamap.put(var1, var2);
   }

   @Override
   public Object getUserData(String var1) {
      return this.datamap == null ? null : this.datamap.get(var1);
   }

   public boolean ofType(int... var1) {
      short var2 = this.getNodeType();

      for (int var6 : var1) {
         if (var6 == var2) {
            return true;
         }
      }

      return false;
   }

   public static XNode findNodeByName(Collection var0, String var1) {
      Optional var2 = var0.stream().filter(var1x -> var1x.getNodeName().equals(var1)).findFirst();
      return var2.isPresent() ? (XNode)var2.get() : null;
   }

   public static NodeList getDescendantElementsByTagName(XNode var0, String var1) {
      if (var1 == null) {
         return XNodeList.EMPTY;
      } else {
         ArrayDeque var2 = new ArrayDeque();
         var2.add(var0);
         boolean var3 = var1.equals("*");
         ArrayList var4 = new ArrayList();

         while (!var2.isEmpty()) {
            XNode var5 = (XNode)var2.remove();

            for (XNode var7 : var5.children.list) {
               if (var7 instanceof XElement var8) {
                  if (var3 || var1.equals(var8.getTagName())) {
                     var4.add(var8);
                  }

                  var2.add(var8);
               }
            }
         }

         return new XNodeList(var4);
      }
   }
}
