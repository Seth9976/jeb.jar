package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.IXmlUnit;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class XmlResourceHelper {
   protected Document doc;
   protected Element rootElt;

   public XmlResourceHelper(IXmlUnit var1) {
      this.prepare(var1);
   }

   public XmlResourceHelper(Document var1) {
      this.prepare(var1);
   }

   private void prepare(IXmlUnit var1) {
      if (!var1.isProcessed() && !var1.process()) {
         throw new JebRuntimeException("Cannot process the xml unit");
      } else {
         Document var2 = var1.getDocument();
         if (var2 != null) {
            this.prepare(var2);
         }
      }
   }

   protected void prepare(Document var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.doc = var1;
         this.rootElt = var1.getDocumentElement();
      }
   }

   public Element getRootElement() {
      return this.rootElt;
   }

   protected String buildAttributeName(String var1) {
      return var1;
   }

   protected List convertNodelist(NodeList var1) {
      int var2 = var1.getLength();
      ArrayList var3 = new ArrayList(var2);

      for (int var4 = 0; var4 < var2; var4++) {
         var3.add(var1.item(var4));
      }

      return var3;
   }

   protected List convertEltlist(NodeList var1) {
      int var2 = var1.getLength();
      ArrayList var3 = new ArrayList(var2);

      for (int var4 = 0; var4 < var2; var4++) {
         var3.add((Element)var1.item(var4));
      }

      return var3;
   }

   public String readAttribute(String var1, String var2) {
      List var3 = this.readAttributes(var1, var2, 1);
      return var3.isEmpty() ? null : (String)var3.get(0);
   }

   public List readAttributes(String var1, String var2) {
      return this.readAttributes(var1, var2, -1);
   }

   public boolean hasAttributeValue(String var1, String var2, String var3) {
      return this.readAttributes(var1, var2).contains(var3);
   }

   private List readAttributes(String var1, String var2, int var3) {
      if (var2 != null && !Strings.isBlank(var2)) {
         Object var4;
         if (var1 != null && !var1.equals("manifest")) {
            NodeList var5 = this.rootElt.getElementsByTagName(var1);
            var4 = this.convertEltlist(var5);
            if (var3 >= 0 && var4.size() > var3) {
               var4 = var4.subList(0, var3);
            }
         } else {
            var4 = Lists.createArrayList(this.rootElt);
         }

         if (var4.isEmpty()) {
            return Collections.emptyList();
         } else {
            String var10 = this.buildAttributeName(var2);
            ArrayList var6 = new ArrayList(var4.size());

            for (Element var8 : var4) {
               String var9 = this.getAttr(var8, var10);
               var6.add(var9);
            }

            return var6;
         }
      } else {
         throw new IllegalArgumentException("Null attribute");
      }
   }

   public List readMultiAttributes(String var1, String... var2) {
      if (var2 != null && var2.length != 0) {
         Object var3;
         if (var1 != null && !var1.equals("manifest")) {
            NodeList var4 = this.rootElt.getElementsByTagName(var1);
            var3 = this.convertEltlist(var4);
         } else {
            var3 = Lists.createArrayList(this.rootElt);
         }

         if (var3.isEmpty()) {
            return Collections.emptyList();
         } else {
            ArrayList var12 = new ArrayList(var2.length);

            for (String var8 : var2) {
               var12.add(this.buildAttributeName(var8));
            }

            ArrayList var13 = new ArrayList(var3.size());

            for (Element var15 : var3) {
               ArrayList var16 = new ArrayList(var12.size());

               for (String var10 : var12) {
                  String var11 = this.getAttr(var15, var10);
                  var16.add(var11);
               }

               var13.add(var16);
            }

            return var13;
         }
      } else {
         throw new IllegalArgumentException("No attributes");
      }
   }

   protected String getAttr(Element var1, String var2) {
      Attr var3 = var1.getAttributeNode(var2);
      if (var3 != null) {
         return var3.getNodeValue();
      } else if (this.isFullyQualifiedName(var2)) {
         return "";
      } else {
         NamedNodeMap var4 = var1.getAttributes();
         int var5 = var4.getLength();

         for (int var6 = 0; var6 < var5; var6++) {
            var3 = (Attr)var4.item(var6);
            String var7 = var3.getName();
            var7 = this.getSimpleName(var7);
            if (var7.equals(var2)) {
               return var3.getNodeValue();
            }
         }

         return "";
      }
   }

   protected boolean isFullyQualifiedName(String var1) {
      return var1.indexOf(58) >= 0;
   }

   protected boolean isSimpleName(String var1) {
      return var1.indexOf(58) < 0;
   }

   protected String getSimpleName(String var1) {
      int var2 = var1.lastIndexOf(58);
      return var2 < 0 ? var1 : var1.substring(var2 + 1);
   }
}
