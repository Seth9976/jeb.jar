package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.bjn;
import com.pnfsoftware.jebglobal.bjo;
import com.pnfsoftware.jebglobal.bjp;
import com.pnfsoftware.jebglobal.bjs;
import com.pnfsoftware.jebglobal.bjt;
import com.pnfsoftware.jebglobal.ct;
import java.util.List;

public class EE extends ct {
   private static final ILogger q = GlobalLog.getLogger(EE.class);

   public EE(bK var1) {
      super(var1, "DexContribution");
   }

   @Override
   public boolean isTarget(IUnit var1) {
      return super.isTarget(var1) ? true : DecompilerHelper.getRelatedCodeUnit(var1) == this.getPrimaryTarget();
   }

   @Override
   public TypedContent getItemInformation(IAddressableUnit var1, long var2, String var4, List var5) {
      Object[] var10000 = new Object[]{var1, var2, var4};
      if (var1 instanceof bK) {
         return null;
      } else {
         bK var6 = (bK)this.getPrimaryTarget();
         String var7 = "";
         Object var8 = var6.getObjectById(var2);
         if (var8 instanceof bjp || var8 instanceof bjo || var8 instanceof bjt || var8 instanceof bjn) {
            String var9 = null;
            String var10 = null;
            String var11 = null;
            if (var8 instanceof bjp) {
               var9 = ((bjp)var8).getSignature();
               var10 = ((bjp)var8).getSignature(true, false, true);
               var11 = ((bjp)var8).getSignature(false);
            } else if (var8 instanceof bjo) {
               var9 = ((bjo)var8).getSignature();
               var10 = ((bjo)var8).getSignature(true, false);
               var11 = ((bjo)var8).getSignature(false);
            } else if (var8 instanceof bjt) {
               var9 = ((bjt)var8).getSignature();
               var10 = ((bjt)var8).getSignature(true, false);
               var11 = ((bjt)var8).getSignature(false);
            } else if (var8 instanceof bjn) {
               var9 = ((bjn)var8).getSignature();
               var10 = ((bjn)var8).getSignature(true, false);
               var11 = ((bjn)var8).getSignature(false);
            }

            String var12 = var6.getFullComment(var9);
            if (var12 != null && !var12.isEmpty()) {
               var7 = var7 + "// " + var12 + "\n";
            }

            var7 = var7 + var10 + "\n";
            var7 = var7 + S.L("Descriptor:") + " " + var11;
         } else if (var8 instanceof bjs && ((bjs)var8).isArtificial()) {
            var7 = S.L("This string was not present in the original binary.");
         }

         return var7.isEmpty() ? null : this.q(var7, 70);
      }
   }
}
