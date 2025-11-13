package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.util.collect.SynchronizedLinkedMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import javax.annotation.concurrent.Immutable;

@Ser
public class ou {
   private static final ILogger q = GlobalLog.getLogger(ou.class);
   @SerId(1)
   private SynchronizedLinkedMap RF = new SynchronizedLinkedMap();

   public boolean q(Pointer var1, int var2, int var3) {
      long var4 = var1.getAddress();
      int var6 = var1.getType();
      int var7 = var1 instanceof CodePointer ? ((CodePointer)var1).getMode() : -1;
      ou.CU var8 = new ou.CU(var4, var6, var7);
      ou.eo var9 = (ou.eo)this.RF.get(var8);
      if (var9 != null) {
         if (var9.q.equals(var1)) {
            if (var2 > var9.RF) {
               var9.RF = var2;
            }

            var9.xK |= var3;
            return true;
         } else {
            if (var9.q.getType() == 2) {
               int var10 = var9.q.getSize();
               int var11 = var1.getSize();
               if (var10 != var11 && (var11 == 0 || var10 != 0 && var10 < var11)) {
                  var9.q.setSize(var11);
                  return true;
               }
            }

            new Object[]{var4, var9, null}[2] = new ou.eo(var1, var2, var3);
            return false;
         }
      } else {
         this.RF.put(var8, new ou.eo(var1, var2, var3));
         return true;
      }
   }

   public int q() {
      return this.RF.size();
   }

   public boolean RF() {
      return this.RF.isEmpty();
   }

   public ou.eo q(ou.CU var1) {
      return (ou.eo)this.RF.get(var1);
   }

   public ou.eo xK() {
      if (this.RF.isEmpty()) {
         return null;
      } else {
         ou.CU var1 = (ou.CU)this.RF.firstKey();
         return (ou.eo)this.RF.remove(var1);
      }
   }

   public ou.eo Dw() {
      if (this.RF.isEmpty()) {
         return null;
      } else {
         ou.CU var1 = (ou.CU)this.RF.firstKey();
         return (ou.eo)this.RF.get(var1);
      }
   }

   public void Uv() {
      this.RF.clear();
   }

   @Override
   public String toString() {
      return this.RF.toString();
   }

   @Ser
   @Immutable
   public static class CU {
      @SerId(1)
      long q;
      @SerId(2)
      int RF;
      @SerId(3)
      int xK = -1;

      public CU(long var1, int var3, int var4) {
         this.q = var1;
         this.RF = var3;
         this.xK = var4;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (int)(this.q ^ this.q >>> 32);
         var1 = 31 * var1 + this.xK;
         return 31 * var1 + this.RF;
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
            ou.CU var2 = (ou.CU)var1;
            if (this.q != var2.q) {
               return false;
            } else {
               return this.xK != var2.xK ? false : this.RF == var2.RF;
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X[type=%d][mode=%d]", this.q, this.RF, this.xK);
      }
   }

   @Ser
   public static class eo {
      @SerId(1)
      private Pointer q;
      @SerId(2)
      private int RF;
      @SerId(3)
      private int xK;

      public eo(Pointer var1, int var2, int var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      public eo(Pointer var1) {
         this(var1, 0, 0);
      }

      public Pointer q() {
         return this.q;
      }

      public int RF() {
         return this.RF;
      }

      public int xK() {
         return this.xK;
      }

      @Override
      public String toString() {
         return Strings.ff("%s[perm=%d][flags=%d]", this.q, this.RF, this.xK);
      }
   }
}
