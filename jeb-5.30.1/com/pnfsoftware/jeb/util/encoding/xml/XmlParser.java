package com.pnfsoftware.jeb.util.encoding.xml;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

public class XmlParser {
   private static final ILogger logger = GlobalLog.getLogger(XmlParser.class);
   private static final String DEFAULT_ENCODING = "utf8";
   private final List attributeNameSeparator = Arrays.asList(61);
   private final List elementEnd = Arrays.asList(62, 47);
   private boolean assignParentNodes;
   private boolean sortAttributes;
   private boolean handleBackslashAxmlStyle;
   private boolean allowUnclosedTags;
   private boolean allowMismatchedTags;
   private boolean allowNoXmlDeclaration;
   private String str;
   private int idx;
   private boolean declProcessed;
   private int lastEltClose;
   private Deque openedEltStack;

   private static final void log(String var0, Object... var1) {
   }

   public void setAssignParentNodes(boolean var1) {
      this.assignParentNodes = var1;
   }

   public boolean isAssignParentNodes() {
      return this.assignParentNodes;
   }

   public void setSortAttributes(boolean var1) {
      this.sortAttributes = var1;
   }

   public boolean isSortAttributes() {
      return this.sortAttributes;
   }

   public void setHandleBackslashAxmlStyle(boolean var1) {
      this.handleBackslashAxmlStyle = var1;
   }

   public boolean isHandleBackslashAxmlStyle() {
      return this.handleBackslashAxmlStyle;
   }

   public void setAllowUnclosedTags(boolean var1) {
      this.allowUnclosedTags = var1;
   }

   public boolean isAllowUnclosedTags() {
      return this.allowUnclosedTags;
   }

   public void setAllowMismatchedTags(boolean var1) {
      this.allowMismatchedTags = var1;
   }

   public boolean isAllowMismatchedTags() {
      return this.allowMismatchedTags;
   }

   public boolean isAllowNoXmlDeclaration() {
      return this.allowNoXmlDeclaration;
   }

   public void setAllowNoXmlDeclaration(boolean var1) {
      this.allowNoXmlDeclaration = var1;
   }

   private void reset(String var1) {
      this.str = var1;
      this.idx = 0;
      this.declProcessed = false;
      this.lastEltClose = 0;
      this.openedEltStack = new ArrayDeque();
   }

   public XDocument parse(String var1) throws ParseException {
      this.reset(var1);
      return this.parseInternal(var1);
   }

   public XDocument parse(byte[] var1) throws ParseException {
      this.reset(null);
      int var2 = ArrayUtil.find(var1, (byte)62);
      Assert.a(var2 >= 0, "Cannot find end of xml header");
      this.str = new String(var1, 0, var2 + 1, Charset.forName("ascii"));
      List var3 = this.parseXmlDeclaration();
      String var4 = "utf8";
      if (var3 != null) {
         XAttr var5 = (XAttr)this.findNodeByName(var3, "encoding");
         if (var5 != null) {
            var4 = var5.getValue();
         }
      }

      String var6 = new String(var1, Charset.forName(var4));
      this.reset(var6);
      return this.parseInternal(var6);
   }

   private List parseXmlDeclaration() {
      this.skipSpaces();
      boolean var1 = this.cmpskip("<?xml");
      if (!var1 && this.allowNoXmlDeclaration) {
         return null;
      } else {
         Assert.a(var1, "Expected xml marker");
         this.skipSomeSpaces();
         List var2 = this.readAttributes();
         XAttr var3 = (XAttr)this.findNodeByName(var2, "version");
         String var4 = var3.getValue();
         Assert.a(var4.startsWith("1."), "Expected 1.x version number");
         return var2;
      }
   }

   private XDocument parseInternal(String var1) {
      List var2 = this.parseXmlDeclaration();
      this.declProcessed = true;
      XDocumentType var3 = null;
      this.skipSpaces();

      ArrayList var4;
      for (var4 = new ArrayList(); this.idx < var1.length() - 1 && var1.charAt(this.idx) == '<'; this.skipSpaces()) {
         if (this.cmpskip("<!--")) {
            String var5 = this.readUntil("-->");
            var4.add(new XComment(var5));
         } else if (this.cmpskip("<?xml-")) {
            this.readUntil("?>");
         } else if (this.cmpskip("<!DOCTYPE")) {
            this.skipSomeSpaces();
            String var10 = this.readToken(null, false);
            this.skipSomeSpaces();
            if (this.cmpnoskip("[")) {
               this.readUntil("]>");
            } else {
               this.readUntil(">");
            }

            var3 = new XDocumentType(var10, null, null);
            var4.add(var3);
         } else {
            var4.add(this.readElement());
         }
      }

      Assert.a(this.openedEltStack.isEmpty(), "Expected empty element stack");
      this.skipSpaces();
      Assert.a(this.idx == var1.length(), "Expected no appended data");
      XDocument var11 = new XDocument(var2, var3);
      var11.initChildren(var4);
      if (this.assignParentNodes) {
         ArrayDeque var6 = new ArrayDeque();
         var6.add(var11);

         while (!var6.isEmpty()) {
            XNode var7 = (XNode)var6.remove();

            for (XNode var9 : var7.children.list) {
               var9.parent = var7;
            }

            if (var7 instanceof XElement) {
               for (XNode var13 : ((XElement)var7).attrmap.list) {
                  ((XAttr)var13).owner = (XElement)var7;
               }
            }

            var6.addAll(var7.children.list);
         }
      }

      return var11;
   }

   private int readCp() {
      Assert.a(this.idx < this.str.length(), "Expected more characters");
      int var1 = this.str.codePointAt(this.idx);
      this.idx = this.idx + Character.charCount(var1);
      return var1;
   }

   private void skipSpaces() {
      this.idx = XmlUtil.skipSpaces(this.str, this.idx);
   }

   private void skipSomeSpaces() {
      this.idx = XmlUtil.skipSomeSpaces(this.str, this.idx);
   }

   private String readToken(Collection var1, boolean var2) {
      String[] var3 = new String[1];
      this.idx = XmlUtil.readToken(this.str, this.idx, var1, var3, var2, this.handleBackslashAxmlStyle);
      return var3[0];
   }

   private XElement readElement() {
      int var1 = this.readCp();
      Assert.a(var1 == 60, "Expected opening angle bracket");
      String var2 = this.readToken(this.elementEnd, false);
      if (this.declProcessed && var2.equals("?xml")) {
         throw new RuntimeException("Multiple xml declarators detected");
      } else {
         XElement var3 = new XElement(var2);
         this.openedEltStack.push(var2);
         log("Elt: %s", var2);
         this.skipSpaces();
         var3.initAttributes(this.readAttributes());
         if (this.lastEltClose == 1) {
            int var4 = this.idx;

            try {
               this.readInner(var3);
            } catch (RuntimeException var8) {
               if (!this.allowUnclosedTags || !this.cmpskip("</" + var2)) {
                  throw var8;
               }

               this.idx = var4;
               String var6 = this.readUntil("</" + var2, false);
               ArrayList var7 = new ArrayList();
               var7.add(new XText(var6));
               var3.initChildren(var7);

               while (!this.openedEltStack.isEmpty() && !((String)this.openedEltStack.getFirst()).equals(var2)) {
                  this.openedEltStack.pop();
               }

               if (this.openedEltStack.isEmpty()) {
                  throw var8;
               }
            }

            this.skipSpaces();
            Assert.a(this.cmpskip("</" + var2, this.allowMismatchedTags), "Expected matching closing element");
            this.skipSpaces();
            Assert.a(this.cmpskip(">"), "Expected closing angle bracket");
         } else {
            Assert.a(this.lastEltClose == 2, "Expected self-closing element");
         }

         this.openedEltStack.pop();
         return var3;
      }
   }

   private void readInner(XElement var1) {
      ArrayList var2 = new ArrayList();
      StringBuilder var3 = new StringBuilder();

      while (true) {
         int var4 = this.readCp();
         if (var4 != 60) {
            var3.appendCodePoint(var4);
         } else {
            if (var3.length() > 0) {
               var2.add(new XText(var3.toString()));
               var3.setLength(0);
            }

            this.idx--;
            if (this.cmpskip("<!--")) {
               String var5 = this.readUntil("-->");
               var2.add(new XComment(var5));
            } else if (this.cmpskip("<![CDATA[")) {
               String var6 = this.readUntil("]]>");
               var2.add(new XCDATASection(var6));
            } else {
               if (this.cmpnoskip("</")) {
                  var1.initChildren(var2);
                  return;
               }

               var2.add(this.readElement());
            }
         }
      }
   }

   private String readUntil(String var1) {
      return this.readUntil(var1, true);
   }

   private String readUntil(String var1, boolean var2) {
      int var3 = this.str.indexOf(var1, this.idx);
      String var4 = this.str.substring(this.idx, var3);
      this.idx = var2 ? var3 + var1.length() : var3;
      return var4;
   }

   private List readAttributes() {
      ArrayList var1 = new ArrayList();

      while (true) {
         this.skipSpaces();
         if (this.cmpskip(">")) {
            this.lastEltClose = 1;
            break;
         }

         if (this.cmpskip("/>")) {
            this.lastEltClose = 2;
            break;
         }

         if (this.cmpskip("?>")) {
            Assert.a(!this.declProcessed, "Expected unprocessed xml declarator");
            this.lastEltClose = 3;
            break;
         }

         XAttr var2 = this.readAttribute();
         log("  Attr: %s", var2);
         var1.add(var2);
      }

      if (this.sortAttributes) {
         var1.sort((var0, var1x) -> var0.name.compareTo(var1x.name));
      }

      return var1;
   }

   private XAttr readAttribute() {
      String var1 = this.readToken(this.attributeNameSeparator, false);
      this.skipSpaces();
      Assert.a(this.readCp() == 61, "Expected equal character");
      this.skipSpaces();
      String var2 = this.readToken(this.elementEnd, false);
      return new XAttr(var1, var2);
   }

   private boolean cmpskip(String var1) {
      return this.cmp(var1, true, false);
   }

   private boolean cmpnoskip(String var1) {
      return this.cmp(var1, false, false);
   }

   private boolean cmpskip(String var1, boolean var2) {
      return this.cmp(var1, true, var2);
   }

   private boolean cmpnoskip(String var1, boolean var2) {
      return this.cmp(var1, false, var2);
   }

   private boolean cmp(String var1, boolean var2, boolean var3) {
      int var4 = var1.length();
      if (this.idx + var4 > this.str.length()) {
         return false;
      } else {
         if (!var3) {
            for (int var5 = 0; var5 < var4; var5++) {
               if (this.str.charAt(this.idx + var5) != var1.charAt(var5)) {
                  return false;
               }
            }
         } else {
            for (int var6 = 0; var6 < var4; var6++) {
               if (Character.toLowerCase(this.str.charAt(this.idx + var6)) != Character.toLowerCase(var1.charAt(var6))) {
                  return false;
               }
            }
         }

         if (var2) {
            this.idx += var4;
         }

         return true;
      }
   }

   XNode findNodeByName(Collection var1, String var2) {
      Optional var3 = var1.stream().filter(var1x -> var1x.getNodeName().equals(var2)).findFirst();
      return var3.isPresent() ? (XNode)var3.get() : null;
   }
}
