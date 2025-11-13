package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class axw extends axq implements INativeStringItem {
   @SerId(1)
   private StringEncoding Dw;
   @SerId(2)
   private String Uv;

   public axw(long var1, long var3, bbd var5, StringEncoding var6, String var7) {
      super(var1, var3, var5);
      if (var6 == null) {
         throw new NullPointerException();
      } else {
         this.Dw = var6;
         this.Uv = var7;
      }
   }

   @Override
   public StringEncoding getStringType() {
      return this.Dw;
   }

   @Override
   public long getIdentifier() {
      return this.getItemId();
   }

   @Override
   public String getValue() {
      return this.Uv;
   }

   public void xK(String var1) {
      if ((this.Uv != null || var1 != null) && (this.Uv == null || !this.Uv.equals(var1))) {
         try (ACLock var2 = this.za().a()) {
            this.Uv = var1;
            this.Gf();
         }
      }
   }

   @Override
   public String toString() {
      String var1 = this.getValue() == null ? null : Formatter.escapeString(this.getValue());
      return Strings.ff("StringItem@%X(\"%s\")", this.getMemoryAddress(), var1);
   }
}
