package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class axv extends axh {
   @SerId(1)
   private bbd Dw;
   @SerId(2)
   private int Uv;

   protected axv(long var1, long var3, bbd var5) {
      this(var1, var3, var5, true);
   }

   protected axv(long var1, long var3, bbd var5, boolean var6) {
      super(var1, var3);
      if (var5 == null) {
         throw new NullPointerException();
      } else {
         this.Dw = var5;
         if (var6) {
            this.Dw.addListener(this);
         }

         bbd var7 = (bbd)TypeUtil.getNonAlias(var5);
         if (!(var7 instanceof bbq) && !(var7 instanceof bbt) && !(var7 instanceof bbm)) {
            throw new IllegalArgumentException("Illegal type for simple data item");
         }
      }
   }

   @Override
   public bbd Uv() {
      return this.Dw;
   }

   public boolean cC() {
      return this.Uv != 0;
   }

   public void q(int var1, int var2) {
      this.Uv = var1 | var2 << 16;
   }

   @Override
   public int oW() {
      return this.Uv & 65535;
   }

   public int sH() {
      return this.Uv >>> 16;
   }

   public int CE() {
      return this.oW() + this.sH();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("SimpleItem@").append(Long.toHexString(this.getMemoryAddress()).toUpperCase());
      var1.append("(type:").append(this.Uv()).append(")");
      return var1.toString();
   }
}
