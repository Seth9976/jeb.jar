package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeFeatureSignerID;
import com.pnfsoftware.jebglobal.a;
import com.pnfsoftware.jebglobal.abl;
import com.pnfsoftware.jebglobal.axi;
import com.pnfsoftware.jebglobal.axj;
import com.pnfsoftware.jebglobal.axk;
import com.pnfsoftware.jebglobal.axl;
import com.pnfsoftware.jebglobal.axm;
import com.pnfsoftware.jebglobal.axn;
import com.pnfsoftware.jebglobal.axp;
import com.pnfsoftware.jebglobal.axq;
import com.pnfsoftware.jebglobal.axr;
import com.pnfsoftware.jebglobal.axs;
import java.util.HashMap;
import java.util.Map;

public final class DH {
   public static Map pC(a var0) {
      HashMap var1 = new HashMap();
      NativeFeatureSignerID[] var2 = NativeFeatureSignerID.values();

      for (NativeFeatureSignerID var6 : var2) {
         axi var7 = pC(var6, var0);
         if (var7 != null) {
            var1.put(var6, var7);
         }
      }

      return var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static axi pC(NativeFeatureSignerID var0, a var1) {
      Object var2;
      switch (var0) {
         case ROUTINE_CODE_HASH:
            var2 = new axn(var1.getProcessor());
            break;
         case CALLED_ROUTINE_NAME_ONLY_EXTERN:
         case CALLED_ROUTINE_NAME:
            axl.Sv var3 = axl.Sv.pC;
            if (var1.wS() != null) {
               if (var1.getContainer() instanceof com.pnfsoftware.jeb.corei.parsers.wincoff.K) {
                  var3 = axl.Sv.A;
               } else if (var1.getContainer() instanceof com.pnfsoftware.jeb.corei.parsers.winpe.yt && var1.getDetectedCompiler() instanceof abl) {
                  var3 = axl.Sv.kS;
               }
            }

            boolean var4 = true;
            if (var1.getProcessor().getType().isMIPS()) {
               var4 = false;
            }

            if (var0 == NativeFeatureSignerID.CALLED_ROUTINE_NAME_ONLY_EXTERN) {
               var2 = new axl(var1, var3, true, var4);
            } else {
               var2 = new axl(var1, var3, false, var4);
            }
            break;
         case ROUTINE_SIZE:
            var2 = new axs();
            break;
         case ROUTINE_1B_CTE:
            var2 = new axq(8);
            break;
         case UNKNOWN:
            return null;
         default:
            throw new IllegalArgumentException("Unknown signer ID");
      }

      return (axi)var2;
   }

   public static NativeFeatureSignerID pC(INativeFeature var0) {
      if (var0 instanceof axm) {
         return NativeFeatureSignerID.ROUTINE_CODE_HASH;
      } else if (var0 instanceof axk) {
         return NativeFeatureSignerID.CALLED_ROUTINE_NAME;
      } else if (var0 instanceof axr) {
         return NativeFeatureSignerID.ROUTINE_SIZE;
      } else if (var0 instanceof axp) {
         return NativeFeatureSignerID.ROUTINE_1B_CTE;
      } else if (var0 instanceof axj) {
         return pC((INativeFeature)((axj)var0).pC().iterator().next());
      } else {
         throw new IllegalArgumentException("Unknown feature");
      }
   }
}
