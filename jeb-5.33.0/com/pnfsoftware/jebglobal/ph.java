package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMemoryItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;

@Ser
public class ph extends HM {
   @SerId(1)
   private Tw A;

   public ph(Tw var1) {
      super(var1, Arrays.asList("loc_", "sub_", "gap_", "gvar_"));
      this.A = var1;
   }

   @Override
   public Long A(String var1) {
      if (var1.startsWith("loc_") || var1.startsWith("sub_") || var1.startsWith("gap_")) {
         try {
            return Long.parseLong(var1.substring(4), 16);
         } catch (NumberFormatException var3) {
         }
      } else if (var1.startsWith("gvar_")) {
         try {
            return Long.parseLong(var1.substring(5), 16);
         } catch (NumberFormatException var2) {
         }
      }

      return null;
   }

   @Override
   public String pC(long var1, long var3, INativeMemoryItem var5, INativeContinuousItem var6) {
      String var7 = "loc_";
      boolean var8 = false;
      if (var5 == null) {
         var8 = this.A.isRoutineHeader(var1);
      } else if (var5 instanceof aut) {
         var8 = true;
      }

      if (var8) {
         var7 = "sub_";
      } else {
         if (var6 == null) {
            this.A.getItemAt(var1);
         }

         if (var5 instanceof auxx) {
            var7 = "gap_";
         } else if (var5 instanceof ava) {
            var7 = "gvar_";
         } else if (var5 instanceof auv) {
            var7 = "gvar_";
         } else if (var5 instanceof auw) {
            var7 = "gvar_";
         }
      }

      return var7 + Long.toHexString(var3 + var1).toUpperCase();
   }

   public String pC(long var1) {
      return Strings.ff("%s%X", "sub_", var1);
   }

   public static boolean kS(String var0) {
      for (int var1 = 0; var1 < var0.length(); var1++) {
         if (!var0.startsWith("→", var1) && !var0.startsWith("*", var1)) {
            return var0.startsWith("sub_", var1) || var0.startsWith("unk_lib_sub", var1);
         }
      }

      return false;
   }
}
