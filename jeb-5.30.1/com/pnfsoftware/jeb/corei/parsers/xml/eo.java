package com.pnfsoftware.jeb.corei.parsers.xml;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class eo {
   static final String q = "itemId";
   private List RF = new ArrayList();

   static List q(Document var0) {
      return (new eo(var0)).RF;
   }

   public eo(Document var1) {
      this.RF.add(null);
      this.q((Node)var1);
   }

   private void q(Node var1) {
      if (!(var1 instanceof Document) && !(var1 instanceof Element)) {
         if (var1 instanceof CharacterData) {
            var1.setUserData("itemId", this.RF.size(), null);
            this.RF.add(var1);
         }
      } else {
         var1.setUserData("itemId", this.RF.size(), null);
         this.RF.add(var1);
         NodeList var2 = var1.getChildNodes();

         for (int var3 = 0; var3 < var2.getLength(); var3++) {
            Node var4 = var2.item(var3);
            this.q(var4);
         }
      }
   }
}
