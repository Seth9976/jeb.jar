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

public class ann {
   private static final ILogger pC = GlobalLog.getLogger(ann.class);
   private IERoutineContext A;
   private CFG kS;
   private IEVar wS;
   private int UT;
   private Map E;
   private Map sY;
   private int ys;

   public ann(IERoutineContext var1) {
      this.A = var1;
   }

   public int pC(IEVar var1, boolean var2) {
      this.kS = this.A.getCfg();
      IDFA var3 = this.kS.doDataFlowAnalysis();
      this.wS = var1;
      this.UT = this.wS.getId();
      this.E = new HashMap();
      this.sY = new HashMap();

      for (BasicBlock var5 : this.kS) {
         this.E.put(var5.getBase(), new anm(this.wS.getBitsize()));
         this.sY.put(var5.getBase(), new anm(this.wS.getBitsize()));
      }

      long var21 = this.kS.getEntryAddress();
      anm var6 = (anm)this.E.get(var21);
      var6.ld();
      ArrayDeque var7 = new ArrayDeque();
      var7.add(var21);
      int var8 = this.kS.size() * 5;
      boolean var9 = false;

      while (true) {
         while (!var7.isEmpty()) {
            if (var8-- <= 0) {
               return -2;
            }

            long var10 = (Long)var7.remove();
            BasicBlock var12 = this.kS.getBlockAt(var10);
            anm var13 = (anm)this.E.get(var10);
            anm var14;
            if (var9) {
               var14 = (anm)this.sY.get(var12.getBase());
            } else {
               var14 = var13.A();
               long var15 = var10;

               for (int var17 = 0; var17 < var12.size(); var17++) {
                  IEStatement var18 = (IEStatement)var12.get(var17);
                  if (var17 == var12.size() - 1 && (var18 instanceof IEJump || var18 instanceof IESwitch)) {
                     break;
                  }

                  DUI var19 = var3.getDUI(var15);
                  if (var19.getDef().contains(this.UT) || var19.getDefPot().contains(this.UT)) {
                     var14.ld();
                  }

                  if (var19.getSpoiled().contains(this.UT)) {
                     var14.ld();
                  }

                  if (var18.isAssignTo(this.wS) && var18.asAssign().getSrcOperand() instanceof IEImm) {
                     IEImm var20 = var18.asAssign().getSrcOperand().asImm();
                     var14.sY();
                     var14.pC(var20);
                  }

                  var15 += var18.getSize();
               }

               this.sY.put(var12.getBase(), var14);
            }

            boolean var22 = false;
            IEStatement var16 = (IEStatement)var12.getLast();
            if (var16.isConditionalJump() || var16.isSwitch()) {
               long var23 = var12.getLastAddress();
               DUI var24 = var3.getDUI(var23);
               if (var24.getUse().contains(this.UT)) {
                  if (var16.isConditionalJump()) {
                     IEJump var25 = var16.asJump();
                     var22 = this.pC(var23, var25, var14, var9 ? null : var7);
                  } else {
                     IESwitch var26 = var16.asSwitch();
                     var22 = this.pC(var23, var26, var14, var9 ? null : var7);
                  }
               }
            }

            if (!var22) {
               this.pC(var12, var14, var9 ? null : var7);
            }
         }

         if (var9 || !var2) {
            if (this.ys > 0) {
               akt.pC(this.kS);
               this.kS.invalidateDataFlowAnalysis();
            }

            return this.ys;
         }

         Assert.a(var7.isEmpty());
         this.kS.getBlocks().forEach(var1x -> var7.add(var1x.getBase()));
         var8 += var7.size();
         var9 = true;
      }
   }

   private boolean pC(long var1, IEJump var3, anm var4, Collection var5) {
      IEGeneric var6 = var3.getCondition();
      if (var6 == null) {
         return false;
      } else {
         amo var7 = new amo(var6, this.wS);
         if (!var7.pC()) {
            return false;
         } else {
            anm var8 = var7.A();
            anm var9 = var8.oT();
            var8.kS(var4);
            var9.kS(var4);
            long var10 = var3.getBranchAddress();
            long var12 = var1 + var3.getSize();
            if (var5 != null) {
               anm var14 = (anm)this.E.get(var10);
               if (var14.pC(var8)) {
                  var5.add(var10);
               }

               anm var15 = (anm)this.E.get(var12);
               if (var15.pC(var9)) {
                  var5.add(var12);
               }
            } else if (var8.ys()) {
               BasicBlock var17 = this.kS.getBlockByLastAddress(var1);
               BasicBlock var19 = this.kS.getBlockAt(var10);
               IENop var16 = this.A.createNop(var3);
               var17.setLast(var16);
               this.kS.deleteEdge(var17, var19);
               this.ys++;
            } else if (var9.ys()) {
               BasicBlock var18 = this.kS.getBlockByLastAddress(var1);
               BasicBlock var20 = this.kS.getBlockAt(var12);
               var3.setCondition(null);
               this.kS.deleteEdge(var18, var20);
               this.ys++;
            }

            return true;
         }
      }
   }

   private boolean pC(long var1, IESwitch var3, anm var4, Collection var5) {
      if (var3.getControlExpression() != this.wS) {
         return false;
      } else {
         boolean var6 = true;
         int var7 = 0;
         anm var8 = var4.A();
         int var9 = 0;
         List var10 = var3.getCases();

         while (var9 < var10.size()) {
            Couple var11 = (Couple)var10.get(var9);
            IEGeneric var12 = (IEGeneric)var11.getFirst();
            long var13 = ((Integer)var11.getSecond()).intValue();
            anm var15;
            if (var6 && var12 instanceof IEImm) {
               IEImm var16 = var12.asImm();
               var15 = var4.A().kS(var16);
               var8.wS(var16);
            } else {
               var15 = var4;
               var7++;
            }

            if (var5 != null) {
               anm var20 = (anm)this.E.get(var13);
               if (var20.pC(var15)) {
                  var5.add(var13);
               }
            } else if (var15.ys()) {
               var10.remove(var9);
               if (var3.getCountsToTarget((int)var13, true) == 0) {
                  BasicBlock var21 = this.kS.getBlockByLastAddress(var1);
                  BasicBlock var17 = this.kS.getBlockAt(var13);
                  this.kS.deleteEdge(var21, var17);
               }

               this.ys++;
               continue;
            }

            var9++;
         }

         if (var5 != null) {
            if (!var6 || var7 > 0) {
               var8 = var4;
            }

            if (var3.hasDefaultAddress()) {
               long var18 = var3.getDefaultAddress();
               anm var19 = (anm)this.E.get(var18);
               if (var19.pC(var8)) {
                  var5.add(var18);
               }
            }
         }

         return true;
      }
   }

   void pC(BasicBlock var1, anm var2, Collection var3) {
      if (var3 != null) {
         for (long var5 : var1.getOutputOffsets()) {
            anm var7 = (anm)this.E.get(var5);
            if (var7.pC(var2)) {
               var3.add(var5);
            }
         }
      }
   }
}
