package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMemoryItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;

@Ser
public class v extends Nx {
   public static final String RF = "loc_";
   public static final String xK = "sub_";
   public static final String Dw = "gap_";
   public static final String Uv = "gvar_";
   public static final String oW = "unk_lib_sub";
   @SerId(1)
   private aaf gO;

   public v(aaf var1) {
      super(var1, Arrays.asList("loc_", "sub_", "gap_", "gvar_"));
      this.gO = var1;
   }

   @Override
   public Long RF(String var1) {
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
   public String q(long var1, long var3, INativeMemoryItem var5, INativeContinuousItem var6) {
      String var7 = "loc_";
      boolean var8 = false;
      if (var5 == null) {
         var8 = this.gO.isRoutineHeader(var1);
      } else if (var5 instanceof axo) {
         var8 = true;
      }

      if (var8) {
         var7 = "sub_";
      } else {
         if (var6 == null) {
            this.gO.getItemAt(var1);
         }

         if (var5 instanceof axs) {
            var7 = "gap_";
         } else if (var5 instanceof axv) {
            var7 = "gvar_";
         } else if (var5 instanceof axq) {
            var7 = "gvar_";
         } else if (var5 instanceof axr) {
            var7 = "gvar_";
         }
      }

      return var7 + Long.toHexString(var3 + var1).toUpperCase();
   }

   public String q(long var1) {
      return Strings.ff("%s%X", "sub_", var1);
   }

   public static boolean xK(String var0) {
      for (int var1 = 0; var1 < var0.length(); var1++) {
         if (!var0.startsWith("→", var1) && !var0.startsWith("*", var1)) {
            return var0.startsWith("sub_", var1) || var0.startsWith("unk_lib_sub", var1);
         }
      }

      return false;
   }
}
