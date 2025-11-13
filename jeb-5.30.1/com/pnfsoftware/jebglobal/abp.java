package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMemoryItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;

@Ser
public class abp extends Nx {
   public static final String RF = "var";
   public static final String xK = "par";
   @SerId(1)
   private abr Dw;

   public abp(abr var1) {
      super(var1, Arrays.asList("var", "par"));
      this.Dw = var1;
   }

   @Override
   protected Long RF(String var1) {
      if (var1.startsWith("par")) {
         try {
            return Long.parseLong(var1.substring(4), 16);
         } catch (NumberFormatException var3) {
         }
      } else if (var1.startsWith("var")) {
         try {
            return -Long.parseLong(var1.substring(4), 16);
         } catch (NumberFormatException var2) {
         }
      }

      return null;
   }

   @Override
   protected String q(long var1, long var3, INativeMemoryItem var5, INativeContinuousItem var6) {
      String var7;
      if (var1 < 0L) {
         var7 = "var";
         var1 = -var1;
      } else {
         var7 = "par";
      }

      return Strings.ff("%s%02X", var7, var1);
   }
}
