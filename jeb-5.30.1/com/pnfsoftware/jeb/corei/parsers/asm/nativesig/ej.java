package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeAttributeSignerID;
import com.pnfsoftware.jebglobal.aae;
import com.pnfsoftware.jebglobal.azp;
import com.pnfsoftware.jebglobal.bab;
import com.pnfsoftware.jebglobal.bad;
import java.util.HashMap;
import java.util.Map;

public class ej {
   public static Map q(aae var0) {
      HashMap var1 = new HashMap();
      NativeAttributeSignerID[] var2 = NativeAttributeSignerID.values();

      for (NativeAttributeSignerID var6 : var2) {
         azp var7 = q(var6, var0);
         if (var7 != null) {
            var1.put(var6, var7);
         }
      }

      return var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static azp q(NativeAttributeSignerID var0, aae var1) {
      Object var2;
      switch (var0) {
         case COMMENT:
            var2 = new bab();
            break;
         case LABEL:
            var2 = new bad();
            break;
         case UNKNOWN:
            return null;
         default:
            throw new IllegalArgumentException("Unknown signer ID");
      }

      return (azp)var2;
   }
}
