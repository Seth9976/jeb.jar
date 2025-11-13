package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jebglobal.amg;
import java.util.TreeMap;
import java.util.Map.Entry;

public class yt extends AbstractEOptimizer {
   int pC;
   int A;
   int kS;

   public yt(int var1) {
      if (var1 >= 70 && var1 < 90) {
         this.pC = 280;
         this.A = 352;
      } else if (var1 >= 90 && var1 < 100) {
         this.pC = 520;
         this.A = 528;
      } else {
         if (var1 < 100 || var1 >= 130) {
            throw new RuntimeException();
         }

         this.pC = 856;
         this.A = 896;
      }

      this.kS = this.A + 64;
   }

   @Override
   public int perform() {
      int var1 = 0;
      var1 += this.wS();
      var1 += this.pC();
      var1 += this.A();
      return var1 + this.kS();
   }

   int pC() {
      int var1 = 0;

      for (IEStatement var3 : this.cfg.instructions()) {
         if (var3 instanceof IEAssign var4
            && var4.getDstOperand() instanceof IEVar
            && var4.getSrcOperand() instanceof IEMem var5
            && var5.getBitsize() == this.ectx.getGlobalContext().getAddressBitsize()
            && var5.getReference() instanceof IEImm var6
            && var6.getValueAsAddress() == 268435456L + this.pC) {
            var4.replaceSubExpression(var5, EUtil.zero(var5.getBitsize()));
            var1++;
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   int A() {
      amg var1 = new amg(this.ectx, 32);
      yt.Av var2 = new yt.Av();
      var2.pC = var1;

      for (IEStatement var4 : this.cfg.instructions()) {
         var4.visitDepthPost(var2);
      }

      var2.A = var1.pC();

      for (Entry var8 : var2.A.entrySet()) {
         String var5 = "arg" + var8.getKey();
         this.ectx.createVirtualVar(var5, (Integer)var8.getValue());
      }

      var2.pC = null;

      for (IEStatement var9 : this.cfg.instructions()) {
         var9.visitDepthPost(var2);
      }

      if (var2.kS > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var2.kS;
   }

   int kS() {
      IWildcardPrototype var1 = this.ectx.getPrototype();
      if (var1 != null && var1.getReturnTypes().isEmpty()) {
         int var2 = 0;

         for (BasicBlock var4 : this.cfg) {
            IEStatement var5 = (IEStatement)var4.getLast();
            if (var5 instanceof IEUntranslatedInstruction var6 && var6.getNativeMnemonic().equals("EXIT") && var6.getParameterExpressions().isEmpty()) {
               IEReturn var7 = this.ectx.createReturn();
               var7.copyProperties(var6);
               var4.setLast(var7);
               var2++;
            }
         }

         return var2;
      } else {
         return 0;
      }
   }

   int wS() {
      int var1 = 0;
      IEGlobalContext var2 = this.ectx.getGlobalContext();
      IEVar var3 = var2.getVariableByName("R255");
      IEVar var4 = var2.getVariableByName("P7");

      for (Object var6 : this.cfg.instructions()) {
         if (var6 instanceof IEAssign var7) {
            IEGeneric var8 = var7.getDstOperand();
            if (var8 == var3 || var8 == var4) {
               IENop var9 = this.ectx.createNop((IEStatement)var6);
               if (this.cfg.replaceInstruction((IInstruction)var6, var9) != null) {
                  var6 = var9;
                  var1++;
               }
            }
         }

         var1 += ((IEStatement)var6).replaceUsedVar(var3, EUtil.zero(var3.getBitsize()));
         var1 += ((IEStatement)var6).replaceUsedVar(var4, EUtil.one(var4.getBitsize()));
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   class Av implements IEVisitor {
      amg pC;
      TreeMap A;
      int kS;

      public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
         if (var1 instanceof IEMem var4 && var4.getReference() instanceof IEImm var5 && var5.canReadAsAddress()) {
            long var13 = var5.getValueAsAddress();
            if (var13 >= 268435456L + yt.this.A && var13 < 268435456L + yt.this.kS) {
               long var8 = (int)(var13 - (268435456L + yt.this.A));
               if (this.pC != null) {
                  this.pC.pC(var8, var4.getBitsize());
                  return;
               }

               IEVar var10 = yt.this.ectx.getVariableByName("arg" + var8);
               if (var10 == null) {
                  return;
               }

               Object var11 = var10;
               if (var10.getBitsize() > var4.getBitsize()) {
                  var11 = var10.part(var4.getBitsize());
               } else if (var10.getBitsize() < var4.getBitsize() && var10.getBitsize() == 32 && var4.getBitsize() == 64) {
                  IEVar var12 = yt.this.ectx.getVariableByName("arg" + (var8 + 4L));
                  if (var12 != null && var12.getBitsize() == 32) {
                     var11 = yt.this.ectx.createCompose(var10, var12);
                  }
               }

               if (var11 == null) {
                  return;
               }

               if (var2.replaceSubExpression(var1, (IEGeneric)var11)) {
                  this.kS++;
               }
            }
         }
      }
   }
}
