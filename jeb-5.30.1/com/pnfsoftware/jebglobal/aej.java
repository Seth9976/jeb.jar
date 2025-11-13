package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class aej {
   @SerId(1)
   private INativeItem q;
   @SerId(2)
   private Integer RF;
   @SerId(3)
   private List xK = new ArrayList();

   public aej(INativeItem var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.q = var1;
      }
   }

   public INativeItem q() {
      return this.q;
   }

   public void q(int var1) {
      this.RF = var1;
   }

   public Integer RF() {
      return this.RF;
   }

   public boolean q(INativeMethodItem var1) {
      for (aej.eo var3 : this.xK) {
         if (var3.q == var1) {
            if (var1 != null) {
               var3.RF++;
            }

            return true;
         }
      }

      this.xK.add(new aej.eo(var1));
      return true;
   }

   public boolean q(INativeMethodItem var1, boolean var2) {
      for (int var3 = 0; var3 < this.xK.size(); var3++) {
         aej.eo var4 = (aej.eo)this.xK.get(var3);
         if (var4.q == var1) {
            if (var1 == null) {
               var4.RF = 0;
            } else {
               var4.RF--;
            }

            if (var4.RF == 0 || var2) {
               this.xK.remove(var3);
            }

            return true;
         }
      }

      return false;
   }

   public boolean xK() {
      return !this.xK.isEmpty();
   }

   public List Dw() {
      return this.xK;
   }

   @Override
   public String toString() {
      return Strings.ff("item=%s;symbolId=%s;users=%s", this.q.getName(), this.RF == null ? "X" : "0x" + Integer.toHexString(this.RF), this.xK);
   }

   @Ser
   public static class eo {
      @SerId(1)
      INativeMethodItem q;
      @SerId(2)
      int RF;

      public eo(INativeMethodItem var1) {
         this.q = var1;
         if (var1 == null) {
            this.RF = -1;
         } else {
            this.RF = 1;
         }
      }

      public INativeMethodItem q() {
         return this.q;
      }

      public int RF() {
         return this.RF;
      }

      @Override
      public String toString() {
         return this.q == null ? "GLOBAL" : Strings.ff("%s[%dx]", this.q.getName(), this.RF);
      }
   }
}
