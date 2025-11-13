package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICommentManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeAttribute;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class baa implements INativeAttribute {
   @SerId(1)
   private final long q;
   @SerId(2)
   private final String RF;

   public baa(long var1, String var3) {
      this.q = var1;
      this.RF = var3;
   }

   public Couple q() {
      return new Couple(this.q, this.RF);
   }

   @Override
   public String getType() {
      return "comment";
   }

   @Override
   public boolean importTo(INativeItem var1) {
      if (var1 instanceof axp var2) {
         ICommentManager var3 = var2.oW().gP().getCommentManager();
         long var4 = var2.oW().getCFG().getFirstAddress();
         var3.addComment(var4 + this.q, this.RF);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean isPrintable() {
      return false;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      return 31 * var1 + (int)(this.q ^ this.q >>> 32);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         baa var2 = (baa)var1;
         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!this.RF.equals(var2.RF)) {
            return false;
         }

         return this.q == var2.q;
      }
   }
}
