package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeAttributeSignerID;
import com.pnfsoftware.jebglobal.a;
import com.pnfsoftware.jebglobal.aws;
import com.pnfsoftware.jebglobal.axe;
import com.pnfsoftware.jebglobal.axg;
import java.util.HashMap;
import java.util.Map;

public class Ws {
   public static Map pC(a var0) {
      HashMap var1 = new HashMap();
      NativeAttributeSignerID[] var2 = NativeAttributeSignerID.values();

      for (NativeAttributeSignerID var6 : var2) {
         aws var7 = pC(var6, var0);
         if (var7 != null) {
            var1.put(var6, var7);
         }
      }

      return var1;
   }

   public static aws pC(NativeAttributeSignerID var0, a var1) {
      Object var2;
      switch (var0) {
         case COMMENT:
            var2 = new axe();
            break;
         case LABEL:
            var2 = new axg();
            break;
         case UNKNOWN:
            return null;
         default:
            throw new IllegalArgumentException("Unknown signer ID");
      }

      return (aws)var2;
   }
}
