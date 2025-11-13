package com.pnfsoftware.jeb.corei.parsers.xml;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Av {
   private List pC = new ArrayList();

   static List pC(Document var0) {
      return (new Av(var0)).pC;
   }

   public Av(Document var1) {
      this.pC.add(null);
      this.pC((Node)var1);
   }

   private void pC(Node var1) {
      if (!(var1 instanceof Document) && !(var1 instanceof Element)) {
         if (var1 instanceof CharacterData) {
            var1.setUserData("itemId", this.pC.size(), null);
            this.pC.add(var1);
         }
      } else {
         var1.setUserData("itemId", this.pC.size(), null);
         this.pC.add(var1);
         NodeList var2 = var1.getChildNodes();

         for (int var3 = 0; var3 < var2.getLength(); var3++) {
            Node var4 = var2.item(var3);
            this.pC(var4);
         }
      }
   }
}
