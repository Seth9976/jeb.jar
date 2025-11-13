package com.pnfsoftware.jebglobal;

import java.lang.invoke.StringConcatFactory;

public class bba extends bay {
   public String pC;

   bba(bbc var1) {
      super(var1);
      if (var1.pC().equals("kTypeCid")) {
         if (var1.kS) {
            this.pC = (String)var1.pC("name", String.class);
         } else {
            bbc var2 = var1.wS("typeClassId");
            if (var2 != null) {
               if (var2.kS("value") == null) {
                  return;
               }

               int var3 = ((Number)var2.pC("value", Number.class)).intValue();
               var2 = (bbc)var1.pC.sO.get(var3);
               if (var2 == null || var2.wS("name") == null) {
                  return;
               }

               this.pC = (String)var2.wS("name").pC("data", String.class);
            }
         }
      }
   }

   @Override
   void pC(bbb var1) {
   }

   @Override
   public String toString() {
      return StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.pC);
   }
}
