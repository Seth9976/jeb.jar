package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICommentManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeAttribute;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class axd implements INativeAttribute {
   @SerId(1)
   private final long pC;
   @SerId(2)
   private final String A;

   public axd(long var1, String var3) {
      this.pC = var1;
      this.A = var3;
   }

   public Couple pC() {
      return new Couple(this.pC, this.A);
   }

   @Override
   public String getType() {
      return "comment";
   }

   @Override
   public boolean importTo(INativeItem var1) {
      if (var1 instanceof auu var2) {
         ICommentManager var3 = var2.E().ld().getCommentManager();
         long var4 = var2.E().getCFG().getFirstAddress();
         var3.addComment(var4 + this.pC, this.A);
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
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      return 31 * var1 + (int)(this.pC ^ this.pC >>> 32);
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
         axd var2 = (axd)var1;
         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         return this.pC == var2.pC;
      }
   }
}
