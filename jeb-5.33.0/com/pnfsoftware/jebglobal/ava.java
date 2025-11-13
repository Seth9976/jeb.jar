package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ava extends aum {
   @SerId(1)
   private aye kS;
   @SerId(2)
   private int wS;

   protected ava(long var1, long var3, aye var5, boolean var6) {
      super(var1, var3);
      if (var5 == null) {
         throw new NullPointerException();
      } else {
         this.kS = var5;
         if (var6) {
            this.kS.addListener(this);
         }

         aye var7 = (aye)TypeUtil.getNonAlias(var5);
         if (!(var7 instanceof ayq) && !(var7 instanceof ayt) && !(var7 instanceof aym)) {
            throw new IllegalArgumentException("Illegal type for simple data item");
         }
      }
   }

   @Override
   public aye UT() {
      return this.kS;
   }

   public boolean UO() {
      return this.wS != 0;
   }

   public void pC(int var1, int var2) {
      this.wS = var1 | var2 << 16;
   }

   @Override
   public int E() {
      return this.wS & 65535;
   }

   public int Ab() {
      return this.wS >>> 16;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("SimpleItem@").append(Long.toHexString(this.getMemoryAddress()).toUpperCase());
      var1.append("(type:").append(this.UT()).append(")");
      return var1.toString();
   }
}
