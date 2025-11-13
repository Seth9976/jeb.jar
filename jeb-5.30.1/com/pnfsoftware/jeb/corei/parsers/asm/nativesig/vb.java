package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledRoutine;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.UnmanglerService;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashSet;
import java.util.Set;

public class vb {
   private static final ILogger q = GlobalLog.getLogger(vb.class);

   public static String q(INativeSignature var0, UnmanglerService var1, String var2) {
      Set var3 = q(var0, var1);
      if (var3.isEmpty()) {
         return "unk_lib_sub";
      } else if (!var2.startsWith("sub_")) {
         if (var3.contains(var2)) {
            return null;
         } else {
            String var4 = null;

            for (String var6 : var3) {
               if (var2.startsWith(var6)) {
                  return null;
               }

               if (var6.startsWith(var2) && var6.charAt(var2.length()) == '(') {
                  if (var4 != null) {
                     return null;
                  }

                  var4 = var6;
               }
            }

            if (var4 == null) {
               return null;
            } else {
               return var4.length() == var2.length() + 2 && var4.endsWith("()") ? null : var4;
            }
         }
      } else {
         return var3.size() == 1 ? (String)var3.iterator().next() : q(var3);
      }
   }

   private static Set q(INativeSignature var0, UnmanglerService var1) {
      HashSet var2 = new HashSet();

      for (String var4 : var0.getPossibleNames()) {
         IUnmangledData var5 = var1.unmangle(var4, false);
         if (var5 instanceof IUnmangledRoutine) {
            var4 = ((IUnmangledRoutine)var5).getNameWithParameters(true);
            var2.add(var4);
         } else if (var5 != null) {
            var2.add(var5.getFull());
         } else {
            var2.add(var4);
         }
      }

      return var2;
   }

   public static String q(Set var0) {
      return "unk_lib_sub";
   }
}
