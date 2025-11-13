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

class ig {
   private static final ILogger pC = GlobalLog.getLogger(ig.class);
   private final Ro.Sv A;
   private final Ro.Av kS;
   private final DM wS;
   private final Sp UT;
   private final IEGlobalContext E;

   public ig(Ro.Sv var1, Ro.Av var2, DM var3, Sp var4, IEGlobalContext var5) {
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
      this.UT = var4;
      this.E = var5;
   }

   public boolean pC(DM.K var1, Map var2, os var3, Ro.K var4, DM.Av var5) {
      int var6 = 0;
      var1.pC();
      IEGeneric var7 = var4.pC().duplicate();
      HashMap var8 = new HashMap();
      if (this.A.UT() != null) {
         var8.put(this.A.A, new Couple(this.A.UT(), this.A.A.duplicate()));
      } else {
         var8.put(((Ro.DH)this.A.pC).kS, new Couple(((Ro.DH)this.A.pC).pC, ((Ro.DH)this.A.pC).kS.duplicate()));
         var8.put(((Ro.DH)this.A.pC).wS, new Couple(((Ro.DH)this.A.pC).A, ((Ro.DH)this.A.pC).wS.duplicate()));
      }

      for (; var1.kS >= -1; var1.pC()) {
         if (var1.kS == -1) {
            DM.Ws var9 = this.wS.pC(var1, var2, var3, var7, var5);
            if (!var9.pC) {
               break;
            }
         } else {
            com.pnfsoftware.jeb.corei.parsers.arm.rQ var16 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.A.get(var1.kS);
            if (!var16.UT().sY()) {
               var7 = this.UT.A(var16, var1.pC, var7);
               if (var7 == null) {
                  break;
               }

               List var10 = Td.pC(this.E, var7);

               for (Entry var12 : var8.entrySet()) {
                  ((Couple)var12.getValue()).setSecond(this.UT.A(var16, var1.pC, (IEGeneric)((Couple)var12.getValue()).getSecond()));
                  List var13 = Td.pC(this.E, (IEGeneric)((Couple)var12.getValue()).getSecond());
                  if (var13.size() == 1 && var10.contains(var13.get(0))) {
                     boolean var14 = this.pC((IEGeneric)((Couple)var12.getValue()).getSecond(), var7);
                     if (var14) {
                        if (this.A.UT() != null) {
                           return true;
                        }

                        Ro.DH var15 = (Ro.DH)this.A.pC;
                        if (((Couple)var12.getValue()).getFirst() == var15.pC) {
                           this.A.pC = new Ro.cq(var15.pC, var15.wS);
                           this.A.A = var15.kS;
                        } else {
                           this.A.pC = new Ro.cq(var15.A, var15.kS);
                           this.A.A = var15.wS;
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

      this.A.pC = null;
      this.A.A = null;
      this.A.ys = false;
      this.A.sY = false;
      this.kS.pC();
      return false;
   }

   public boolean pC(IEGeneric var1, IEGeneric var2) {
      if (var1.isVar()) {
         return true;
      } else {
         EVisitResults var3 = new EVisitResults();
         EUtil.replaceSubExpressionRecursive(var2, var1, this.UT.sY(), var3);
         if (!var3.isVisitedSuccessfully()) {
            return true;
         } else {
            IEGeneric var4 = this.A.pC(var1);
            return var4 != var1 ? this.pC(var4, var2) : false;
         }
      }
   }
}
