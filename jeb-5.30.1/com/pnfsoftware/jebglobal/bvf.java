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

public class bvf {
   private static final ILogger q = GlobalLog.getLogger(bvf.class);
   private static final boolean RF = false;
   private IDGlobalContext xK;
   private IDexUnit Dw;
   private IDMethodContext Uv;
   private CFG oW;

   private static void q(String var0, Object... var1) {
   }

   private static void q(CFG var0, String var1, Object... var2) {
   }

   public bvf(IDMethodContext var1) {
      this.xK = var1.getGlobalContext();
      this.Dw = this.xK.getDex();
      this.Uv = var1;
   }

   public boolean q(IDInstruction var1) {
      if (bto.q(this.xK)) {
         return false;
      } else if (var1 != null && var1.isAssignToVar() && var1.getAssignSource() instanceof IDNewInfo var2) {
         IDVar var10 = var1.getAssignDestination().asVar();
         String var4 = var2.getMethodSignature();
         String var5 = JvmMethodSig.parse(var4).csig;
         IDexClass var6 = this.Dw.getClass(var5);
         if (var6 == null || !var6.getSupertypeSignature(false).equals("Ljava/lang/Object;")) {
            return false;
         } else if (!q(this.Uv.getCfg(), var1, var10, false)) {
            return false;
         } else {
            IDMethodContext var7 = this.Uv;
            this.Uv = this.Uv.copy();

            try {
               if (this.q(var1.getOffset(), var6)) {
                  var7.load(this.Uv);
                  return true;
               }
            } catch (Exception var9) {
               q.catchingSilent(var9);
            }

            this.Uv = var7;
            return false;
         }
      } else {
         return false;
      }
   }

   static boolean q(CFG var0, IDInstruction var1, IDVar var2, boolean var3) {
      IDFA var4 = var0.doDataFlowAnalysis();
      HashSet var5 = new HashSet();
      ArrayDeque var6 = new ArrayDeque();
      var6.add(new Couple(var1, var2));

      while (!var6.isEmpty()) {
         Couple var7 = (Couple)var6.remove();
         IDInstruction var8 = (IDInstruction)var7.getFirst();
         IDVar var9 = (IDVar)var7.getSecond();
         if (var5.add(new bww(var8.getOffset(), var9.getId()))) {
            for (long var11 : var4.getDefUses(var8.getOffset(), var9.getId())) {
               IDInstruction var13 = (IDInstruction)var0.getInstruction(var11);
               if (!var13.visitDepthPost(new bvg(var9, var6, var3))) {
                  Object[] var10000 = new Object[]{var13.getOffset(), var13};
                  return false;
               }
            }
         }
      }

      return true;
   }

   private boolean q(long var1, IDexClass var3) {
      this.oW = this.Uv.getCfg();
      IDInstruction var4 = (IDInstruction)this.oW.getInstructionAt(var1);
      IDNewInfo var5 = var4.getAssignSource().asNewInfo();
      bvv var6 = new bvv(this.Uv);
      if (!var6.q(var4, var5)) {
         return false;
      } else {
         IDInstruction var7 = var6.Dw;
         IDVar var8 = var7.getAssignDestination().asVar();
         Object[] var10000 = new Object[]{var8};
         var6.Dw();
         HashMap var9 = new HashMap();

         label53:
         while (true) {
            IDFA var10 = this.oW.doDataFlowAnalysis(true);
            HashSet var11 = new HashSet();
            ArrayDeque var12 = new ArrayDeque();
            var12.add(new Couple(var7, var8));

            while (!var12.isEmpty()) {
               Couple var13 = (Couple)var12.remove();
               IDInstruction var14 = (IDInstruction)var13.getFirst();
               IDVar var15 = (IDVar)var13.getSecond();
               if (var11.add(new bww(var14.getOffset(), var15.getId()))) {
                  for (long var17 : var10.getDefUses(var14.getOffset(), var15.getId())) {
                     IDInstruction var19 = (IDInstruction)this.oW.getInstructionAt(var17);
                     if (var19.isAssignToVar() && var19.getAssignSource() == var15) {
                        var12.add(new Couple(var19, var19.getAssignDestination().asVar()));
                     } else {
                        bvf.eo var20 = new bvf.eo(var15);
                        var19.visitDepthPost(var20);
                        if (var20.RF != null) {
                           if (!var6.q(var19, var20.RF)) {
                              break label53;
                           }
                           continue label53;
                        }

                        if (!var19.visitDepthPost(new bvh(this, var15, var9))) {
                           return false;
                        }
                     }
                  }
               }
            }

            if (!q(this.Uv.getCfg(), var7, var8, true)) {
               return false;
            }

            bto.q(this.xK, var3);
            return true;
         }

         return false;
      }
   }

   class eo implements IDVisitor {
      IDExpression q;
      IDCallInfo RF;

      eo(IDExpression var2) {
         this.q = var2;
      }

      // $VF: Unable to simplify switch on enum
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDCallInfo var4) {
            switch (bvi.q[var4.getInvokeType().ordinal()]) {
               case 1:
               case 2:
               case 3:
                  if (var4.getArgument(0).equals(this.q)) {
                     this.RF = var4;
                     var3.interrupt(true);
                  }
            }
         }
      }
   }
}
