package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.Bv;
import com.pnfsoftware.jebglobal.bfs;
import com.pnfsoftware.jebglobal.bft;
import com.pnfsoftware.jebglobal.bfu;
import com.pnfsoftware.jebglobal.bfx;
import com.pnfsoftware.jebglobal.bfy;
import java.util.List;

public class zp extends Bv {
   private static final ILogger pC = GlobalLog.getLogger(zp.class);

   public zp(vi var1) {
      super(var1, "DexContribution");
   }

   @Override
   public boolean isTarget(IUnit var1) {
      return super.isTarget(var1) ? true : DecompilerHelper.getRelatedCodeUnit(var1) == this.getPrimaryTarget();
   }

   @Override
   public TypedContent getItemInformation(IAddressableUnit var1, long var2, String var4, List var5) {
      Object[] var10000 = new Object[]{var1, var2, var4};
      if (var1 instanceof vi) {
         return null;
      } else {
         vi var6 = (vi)this.getPrimaryTarget();
         String var7 = "";
         Object var8 = var6.getObjectById(var2);
         if (var8 instanceof bfu || var8 instanceof bft || var8 instanceof bfy || var8 instanceof bfs) {
            String var9 = null;
            String var10 = null;
            String var11 = null;
            if (var8 instanceof bfu) {
               var9 = ((bfu)var8).getSignature();
               var10 = ((bfu)var8).getSignature(true, false, true);
               var11 = ((bfu)var8).getSignature(false);
            } else if (var8 instanceof bft) {
               var9 = ((bft)var8).getSignature();
               var10 = ((bft)var8).getSignature(true, false);
               var11 = ((bft)var8).getSignature(false);
            } else if (var8 instanceof bfy) {
               var9 = ((bfy)var8).getSignature();
               var10 = ((bfy)var8).getSignature(true, false);
               var11 = ((bfy)var8).getSignature(false);
            } else if (var8 instanceof bfs) {
               var9 = ((bfs)var8).getSignature();
               var10 = ((bfs)var8).getSignature(true, false);
               var11 = ((bfs)var8).getSignature(false);
            }

            String var12 = var6.getFullComment(var9);
            if (var12 != null && !var12.isEmpty()) {
               var7 = var7 + "// " + var12 + "\n";
            }

            var7 = var7 + var10 + "\n";
            var7 = var7 + S.L("Descriptor:") + " " + var11;
         } else if (var8 instanceof bfx && ((bfx)var8).isArtificial()) {
            var7 = S.L("This string was not present in the original binary.");
         }

         return var7.isEmpty() ? null : this.pC(var7, 70);
      }
   }
}
