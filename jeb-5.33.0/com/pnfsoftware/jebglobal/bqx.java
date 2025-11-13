package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

public class bqx {
   private static final ILogger pC = GlobalLog.getLogger(bqx.class);
   private IDGlobalContext A;
   private IDexUnit kS;
   private IDMethodContext wS;
   private CFG UT;

   public bqx(IDMethodContext var1) {
      this.A = var1.getGlobalContext();
      this.kS = this.A.getDex();
      this.wS = var1;
   }

   public boolean pC(IDInstruction var1) {
      if (bpl.pC(this.A)) {
         return false;
      } else if (var1 != null && var1.isAssignToVar() && var1.getAssignSource() instanceof IDNewInfo var2) {
         IDVar var10 = var1.getAssignDestination().asVar();
         String var4 = var2.getMethodSignature();
         String var5 = JvmMethodSig.parse(var4).csig;
         IDexClass var6 = this.kS.getClass(var5);
         if (var6 == null || !var6.getSupertypeSignature(false).equals("Ljava/lang/Object;")) {
            return false;
         } else if (!pC(this.wS.getCfg(), var1, var10, false)) {
            return false;
         } else {
            IDMethodContext var7 = this.wS;
            this.wS = this.wS.copy();

            try {
               if (this.pC(var1.getOffset(), var6)) {
                  var7.load(this.wS);
                  return true;
               }
            } catch (Exception var9) {
               pC.catchingSilent(var9);
            }

            this.wS = var7;
            return false;
         }
      } else {
         return false;
      }
   }

   static boolean pC(CFG var0, IDInstruction var1, IDVar var2, boolean var3) {
      IDFA var4 = var0.doDataFlowAnalysis();
      HashSet var5 = new HashSet();
      ArrayDeque var6 = new ArrayDeque();
      var6.add(new Couple(var1, var2));

      while (!var6.isEmpty()) {
         Couple var7 = (Couple)var6.remove();
         IDInstruction var8 = (IDInstruction)var7.getFirst();
         IDVar var9 = (IDVar)var7.getSecond();
         if (var5.add(new bsj(var8.getOffset(), var9.getId()))) {
            for (long var11 : var4.getDefUses(var8.getOffset(), var9.getId())) {
               IDInstruction var13 = (IDInstruction)var0.getInstruction(var11);
               if (!var13.visitDepthPost(new bqy(var9, var6, var3))) {
                  Object[] var10000 = new Object[]{var13.getOffset(), var13};
                  return false;
               }
            }
         }
      }

      return true;
   }

   private boolean pC(long var1, IDexClass var3) {
      this.UT = this.wS.getCfg();
      IDInstruction var4 = (IDInstruction)this.UT.getInstructionAt(var1);
      IDNewInfo var5 = var4.getAssignSource().asNewInfo();
      brm var6 = new brm(this.wS);
      if (!var6.pC(var4, var5)) {
         return false;
      } else {
         IDInstruction var7 = var6.pC;
         IDVar var8 = var7.getAssignDestination().asVar();
         Object[] var10000 = new Object[]{var8};
         var6.pC();
         HashMap var9 = new HashMap();

         label53:
         while (true) {
            IDFA var10 = this.UT.doDataFlowAnalysis(true);
            HashSet var11 = new HashSet();
            ArrayDeque var12 = new ArrayDeque();
            var12.add(new Couple(var7, var8));

            while (!var12.isEmpty()) {
               Couple var13 = (Couple)var12.remove();
               IDInstruction var14 = (IDInstruction)var13.getFirst();
               IDVar var15 = (IDVar)var13.getSecond();
               if (var11.add(new bsj(var14.getOffset(), var15.getId()))) {
                  for (long var17 : var10.getDefUses(var14.getOffset(), var15.getId())) {
                     IDInstruction var19 = (IDInstruction)this.UT.getInstructionAt(var17);
                     if (var19.isAssignToVar() && var19.getAssignSource() == var15) {
                        var12.add(new Couple(var19, var19.getAssignDestination().asVar()));
                     } else {
                        bqx.Av var20 = new bqx.Av(var15);
                        var19.visitDepthPost(var20);
                        if (var20.A != null) {
                           if (!var6.pC(var19, var20.A)) {
                              break label53;
                           }
                           continue label53;
                        }

                        if (!var19.visitDepthPost(new bqz(this, var15, var9))) {
                           return false;
                        }
                     }
                  }
               }
            }

            if (!pC(this.wS.getCfg(), var7, var8, true)) {
               return false;
            }

            bpl.pC(this.A, var3);
            return true;
         }

         return false;
      }
   }

   class Av implements IDVisitor {
      IDExpression pC;
      IDCallInfo A;

      Av(IDExpression var2) {
         this.pC = var2;
      }

      // $VF: Unable to simplify switch on enum
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDCallInfo var4) {
            switch (bra.pC[var4.getInvokeType().ordinal()]) {
               case 1:
               case 2:
               case 3:
                  if (var4.getArgument(0).equals(this.pC)) {
                     this.A = var4;
                     var3.interrupt(true);
                  }
            }
         }
      }
   }
}
