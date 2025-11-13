package com.pnfsoftware.jebglobal;

import java.lang.invoke.StringConcatFactory;

public class beu extends ber {
   public String q;

   beu(bew var1) {
      super(var1);
      if (var1.q().equals("kTypeCid")) {
         if (var1.xK) {
            this.q = (String)var1.q("name", String.class);
         } else {
            bew var2 = var1.Dw("typeClassId");
            if (var2 != null) {
               if (var2.xK("value") == null) {
                  return;
               }

               int var3 = ((Number)var2.q("value", Number.class)).intValue();
               var2 = (bew)var1.q.ui.get(var3);
               if (var2 == null || var2.Dw("name") == null) {
                  return;
               }

               this.q = (String)var2.Dw("name").q("data", String.class);
            }
         }
      }
   }

   @Override
   void q(bev var1) {
   }

   @Override
   public String toString() {
      return StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.q);
   }
}
