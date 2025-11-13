package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class avb extends auv implements INativeStringItem {
   @SerId(1)
   private StringEncoding kS;
   @SerId(2)
   private String wS;

   public avb(long var1, long var3, aye var5, StringEncoding var6, String var7) {
      super(var1, var3, var5);
      if (var6 == null) {
         throw new NullPointerException();
      } else {
         this.kS = var6;
         this.wS = var7;
      }
   }

   @Override
   public StringEncoding getStringType() {
      return this.kS;
   }

   @Override
   public long getIdentifier() {
      return this.getItemId();
   }

   @Override
   public String getValue() {
      return this.wS;
   }

   public void kS(String var1) {
      if ((this.wS != null || var1 != null) && (this.wS == null || !this.wS.equals(var1))) {
         try (ACLock var2 = this.gp().a()) {
            this.wS = var1;
            this.ah();
         }
      }
   }

   @Override
   public String toString() {
      String var1 = this.getValue() == null ? null : Formatter.escapeString(this.getValue());
      return Strings.ff("StringItem@%X(\"%s\")", this.getMemoryAddress(), var1);
   }
}
