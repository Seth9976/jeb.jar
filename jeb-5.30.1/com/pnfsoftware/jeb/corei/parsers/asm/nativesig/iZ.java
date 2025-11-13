package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeFeatureSignerID;
import com.pnfsoftware.jebglobal.aae;
import com.pnfsoftware.jebglobal.add;
import com.pnfsoftware.jebglobal.baf;
import com.pnfsoftware.jebglobal.bag;
import com.pnfsoftware.jebglobal.bah;
import com.pnfsoftware.jebglobal.bai;
import com.pnfsoftware.jebglobal.baj;
import com.pnfsoftware.jebglobal.bak;
import com.pnfsoftware.jebglobal.ban;
import com.pnfsoftware.jebglobal.bao;
import com.pnfsoftware.jebglobal.bap;
import com.pnfsoftware.jebglobal.baq;
import java.util.HashMap;
import java.util.Map;

public final class iZ {
   public static Map q(aae var0) {
      HashMap var1 = new HashMap();
      NativeFeatureSignerID[] var2 = NativeFeatureSignerID.values();

      for (NativeFeatureSignerID var6 : var2) {
         baf var7 = q(var6, var0);
         if (var7 != null) {
            var1.put(var6, var7);
         }
      }

      return var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static baf q(NativeFeatureSignerID var0, aae var1) {
      Object var2;
      switch (var0) {
         case ROUTINE_CODE_HASH:
            var2 = new bak(var1.getProcessor());
            break;
         case CALLED_ROUTINE_NAME_ONLY_EXTERN:
         case CALLED_ROUTINE_NAME:
            bai.CU var3 = bai.CU.q;
            if (var1.Dw() != null) {
               if (var1.getContainer() instanceof com.pnfsoftware.jeb.corei.parsers.wincoff.nI) {
                  var3 = bai.CU.RF;
               } else if (var1.getContainer() instanceof com.pnfsoftware.jeb.corei.parsers.winpe.vn && var1.getDetectedCompiler() instanceof add) {
                  var3 = bai.CU.xK;
               }
            }

            boolean var4 = true;
            if (var1.getProcessor().getType().isMIPS()) {
               var4 = false;
            }

            if (var0 == NativeFeatureSignerID.CALLED_ROUTINE_NAME_ONLY_EXTERN) {
               var2 = new bai(var1, var3, true, var4);
            } else {
               var2 = new bai(var1, var3, false, var4);
            }
            break;
         case ROUTINE_SIZE:
            var2 = new baq();
            break;
         case ROUTINE_1B_CTE:
            var2 = new bao(8);
            break;
         case UNKNOWN:
            return null;
         default:
            throw new IllegalArgumentException("Unknown signer ID");
      }

      return (baf)var2;
   }

   public static NativeFeatureSignerID q(INativeFeature var0) {
      if (var0 instanceof baj) {
         return NativeFeatureSignerID.ROUTINE_CODE_HASH;
      } else if (var0 instanceof bah) {
         return NativeFeatureSignerID.CALLED_ROUTINE_NAME;
      } else if (var0 instanceof bap) {
         return NativeFeatureSignerID.ROUTINE_SIZE;
      } else if (var0 instanceof ban) {
         return NativeFeatureSignerID.ROUTINE_1B_CTE;
      } else if (var0 instanceof bag) {
         return q((INativeFeature)((bag)var0).q().iterator().next());
      } else {
         throw new IllegalArgumentException("Unknown feature");
      }
   }
}
