package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.DUI;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class apx {
   private static final ILogger q = GlobalLog.getLogger(apx.class);
   private IERoutineContext RF;
   private CFG xK;
   private IEVar Dw;
   private int Uv;
   private Map oW;
   private Map gO;
   private int nf;

   public apx(IERoutineContext var1) {
      this.RF = var1;
   }

   public apw q(long var1) {
      return this.oW == null ? null : (apw)this.oW.get(var1);
   }

   public apw RF(long var1) {
      return this.gO == null ? null : (apw)this.gO.get(var1);
   }

   public int q(IEVar var1, boolean var2) {
      this.xK = this.RF.getCfg();
      IDFA var3 = this.xK.doDataFlowAnalysis();
      this.Dw = var1;
      this.Uv = this.Dw.getId();
      this.oW = new HashMap();
      this.gO = new HashMap();

      for (BasicBlock var5 : this.xK) {
         this.oW.put(var5.getBase(), new apw(this.Dw.getBitsize()));
         this.gO.put(var5.getBase(), new apw(this.Dw.getBitsize()));
      }

      long var21 = this.xK.getEntryAddress();
      apw var6 = (apw)this.oW.get(var21);
      var6.za();
      ArrayDeque var7 = new ArrayDeque();
      var7.add(var21);
      int var8 = this.xK.size() * 5;
      boolean var9 = false;

      while (true) {
         while (!var7.isEmpty()) {
            if (var8-- <= 0) {
               return -2;
            }

            long var10 = (Long)var7.remove();
            BasicBlock var12 = this.xK.getBlockAt(var10);
            apw var13 = (apw)this.oW.get(var10);
            apw var14;
            if (var9) {
               var14 = (apw)this.gO.get(var12.getBase());
            } else {
               var14 = var13.RF();
               long var15 = var10;

               for (int var17 = 0; var17 < var12.size(); var17++) {
                  IEStatement var18 = (IEStatement)var12.get(var17);
                  if (var17 == var12.size() - 1 && (var18 instanceof IEJump || var18 instanceof IESwitch)) {
                     break;
                  }

                  DUI var19 = var3.getDUI(var15);
                  if (var19.getDef().contains(this.Uv) || var19.getDefPot().contains(this.Uv)) {
                     var14.za();
                  }

                  if (var19.getSpoiled().contains(this.Uv)) {
                     var14.za();
                  }

                  if (var18.isAssignTo(this.Dw) && var18.asAssign().getSrcOperand() instanceof IEImm) {
                     IEImm var20 = var18.asAssign().getSrcOperand().asImm();
                     var14.nf();
                     var14.q(var20);
                  }

                  var15 += var18.getSize();
               }

               this.gO.put(var12.getBase(), var14);
            }

            boolean var22 = false;
            IEStatement var16 = (IEStatement)var12.getLast();
            if (var16.isConditionalJump() || var16.isSwitch()) {
               long var23 = var12.getLastAddress();
               DUI var24 = var3.getDUI(var23);
               if (var24.getUse().contains(this.Uv)) {
                  if (var16.isConditionalJump()) {
                     IEJump var25 = var16.asJump();
                     var22 = this.q(var23, var25, var14, var9 ? null : var7);
                  } else {
                     IESwitch var26 = var16.asSwitch();
                     var22 = this.q(var23, var26, var14, var9 ? null : var7);
                  }
               }
            }

            if (!var22) {
               this.q(var12, var14, var9 ? null : var7);
            }
         }

         if (var9 || !var2) {
            if (this.nf > 0) {
               amw.q(this.xK);
               this.xK.invalidateDataFlowAnalysis();
            }

            return this.nf;
         }

         Assert.a(var7.isEmpty());
         this.xK.getBlocks().forEach(var1x -> var7.add(var1x.getBase()));
         var8 += var7.size();
         var9 = true;
      }
   }

   private boolean q(long var1, IEJump var3, apw var4, Collection var5) {
      IEGeneric var6 = var3.getCondition();
      if (var6 == null) {
         return false;
      } else {
         aov var7 = new aov(var6, this.Dw);
         if (!var7.xK()) {
            return false;
         } else {
            apw var8 = var7.Dw();
            apw var9 = var8.JY();
            var8.xK(var4);
            var9.xK(var4);
            long var10 = var3.getBranchAddress();
            long var12 = var1 + var3.getSize();
            if (var5 != null) {
               apw var14 = (apw)this.oW.get(var10);
               if (var14.q(var8)) {
                  var5.add(var10);
               }

               apw var15 = (apw)this.oW.get(var12);
               if (var15.q(var9)) {
                  var5.add(var12);
               }
            } else if (var8.gP()) {
               BasicBlock var17 = this.xK.getBlockByLastAddress(var1);
               BasicBlock var19 = this.xK.getBlockAt(var10);
               IENop var16 = this.RF.createNop(var3);
               var17.setLast(var16);
               this.xK.deleteEdge(var17, var19);
               this.nf++;
            } else if (var9.gP()) {
               BasicBlock var18 = this.xK.getBlockByLastAddress(var1);
               BasicBlock var20 = this.xK.getBlockAt(var12);
               var3.setCondition(null);
               this.xK.deleteEdge(var18, var20);
               this.nf++;
            }

            return true;
         }
      }
   }

   private boolean q(long var1, IESwitch var3, apw var4, Collection var5) {
      if (var3.getControlExpression() != this.Dw) {
         return false;
      } else {
         boolean var6 = true;
         int var7 = 0;
         apw var8 = var4.RF();
         int var9 = 0;
         List var10 = var3.getCases();

         while (var9 < var10.size()) {
            Couple var11 = (Couple)var10.get(var9);
            IEGeneric var12 = (IEGeneric)var11.getFirst();
            long var13 = ((Integer)var11.getSecond()).intValue();
            apw var15;
            if (var6 && var12 instanceof IEImm) {
               IEImm var16 = var12.asImm();
               var15 = var4.RF().Dw(var16);
               var8.Uv(var16);
            } else {
               var15 = var4;
               var7++;
            }

            if (var5 != null) {
               apw var20 = (apw)this.oW.get(var13);
               if (var20.q(var15)) {
                  var5.add(var13);
               }
            } else if (var15.gP()) {
               var10.remove(var9);
               if (var3.getCountsToTarget((int)var13, true) == 0) {
                  BasicBlock var21 = this.xK.getBlockByLastAddress(var1);
                  BasicBlock var17 = this.xK.getBlockAt(var13);
                  this.xK.deleteEdge(var21, var17);
               }

               this.nf++;
               continue;
            }

            var9++;
         }

         if (var5 != null) {
            if (!var6 || var7 > 0) {
               var8 = var4;
            }

            long var18 = var3.getDefaultAddress();
            apw var19 = (apw)this.oW.get(var18);
            if (var19.q(var8)) {
               var5.add(var18);
            }
         }

         return true;
      }
   }

   void q(BasicBlock var1, apw var2, Collection var3) {
      if (var3 != null) {
         for (long var5 : var1.getOutputOffsets()) {
            apw var7 = (apw)this.oW.get(var5);
            if (var7.q(var2)) {
               var3.add(var5);
            }
         }
      }
   }
}
