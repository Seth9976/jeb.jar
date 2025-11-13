package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class pe {
   private static final ILogger q = GlobalLog.getLogger(pe.class);
   private static final boolean RF = false;
   private final Ia.CU xK;
   private final Ia.eo Dw;
   private final gP Uv;
   private final bR oW;
   private final IEGlobalContext gO;

   public pe(Ia.CU var1, Ia.eo var2, gP var3, bR var4, IEGlobalContext var5) {
      this.xK = var1;
      this.Dw = var2;
      this.Uv = var3;
      this.oW = var4;
      this.gO = var5;
   }

   public boolean q(gP.nI var1, Map var2, LI var3, Ia.nI var4, gP.eo var5) {
      int var6 = 0;
      var1.q();
      IEGeneric var7 = var4.q().duplicate();
      HashMap var8 = new HashMap();
      if (this.xK.oW() != null) {
         var8.put(this.xK.RF, new Couple(this.xK.oW(), this.xK.RF.duplicate()));
      } else {
         var8.put(((Ia.iZ)this.xK.q).xK, new Couple(((Ia.iZ)this.xK.q).q, ((Ia.iZ)this.xK.q).xK.duplicate()));
         var8.put(((Ia.iZ)this.xK.q).Dw, new Couple(((Ia.iZ)this.xK.q).RF, ((Ia.iZ)this.xK.q).Dw.duplicate()));
      }

      for (; var1.xK >= -1; var1.q()) {
         if (var1.xK == -1) {
            gP.ej var9 = this.Uv.q(var1, var2, var3, var7, var5);
            if (!var9.q) {
               break;
            }
         } else {
            fA var16 = (fA)var1.RF.get(var1.xK);
            if (!var16.Uv().gO()) {
               var7 = this.oW.RF(var16, var1.q, var7);
               if (var7 == null) {
                  break;
               }

               List var10 = tB.q(this.gO, var7);

               for (Entry var12 : var8.entrySet()) {
                  ((Couple)var12.getValue()).setSecond(this.oW.RF(var16, var1.q, (IEGeneric)((Couple)var12.getValue()).getSecond()));
                  List var13 = tB.q(this.gO, (IEGeneric)((Couple)var12.getValue()).getSecond());
                  if (var13.size() == 1 && var10.contains(var13.get(0))) {
                     boolean var14 = this.q((IEGeneric)((Couple)var12.getValue()).getSecond(), var7);
                     if (var14) {
                        if (this.xK.oW() != null) {
                           return true;
                        }

                        Ia.iZ var15 = (Ia.iZ)this.xK.q;
                        if (((Couple)var12.getValue()).getFirst() == var15.q) {
                           this.xK.q = new Ia.Nt(var15.q, var15.Dw);
                           this.xK.RF = var15.xK;
                        } else {
                           this.xK.q = new Ia.Nt(var15.RF, var15.xK);
                           this.xK.RF = var15.Dw;
                        }

                        return true;
                     }
                  }
               }

               if (++var6 > 10) {
                  break;
               }
            }
         }
      }

      this.xK.q = null;
      this.xK.RF = null;
      this.xK.nf = false;
      this.xK.gO = false;
      this.Dw.q();
      return false;
   }

   private StringBuilder q(Map var1) {
      StringBuilder var2 = new StringBuilder();

      for (Entry var4 : var1.entrySet()) {
         var2.append("\n(").append(var4.getKey()).append(",").append(((Couple)var4.getValue()).getFirst()).append(") = ");
         var2.append(((Couple)var4.getValue()).getSecond());
      }

      return var2;
   }

   public boolean q(IEGeneric var1, IEGeneric var2) {
      if (var1.isVar()) {
         return true;
      } else {
         EVisitResults var3 = new EVisitResults();
         EUtil.replaceSubExpressionRecursive(var2, var1, this.oW.gO(), var3);
         if (!var3.isVisitedSuccessfully()) {
            return true;
         } else {
            IEGeneric var4 = this.xK.q(var1);
            return var4 != var1 ? this.q(var4, var2) : false;
         }
      }
   }
}
