package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.DUI;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class bse {
   private static final ILogger pC = GlobalLog.getLogger(bse.class);
   private IDMethodContext A;
   private CFG kS;
   private IDVar wS;
   private int UT;
   private int E;
   private Map sY;
   private Map ys;
   private int ld;

   public static boolean pC(IDVar var0) {
      IJavaType var1 = var0.getType();
      return var1.isInt() || var1.isLong();
   }

   public bse(IDMethodContext var1, IDVar var2) {
      this.A = var1;
      this.wS = var2;
      this.kS = var1.getCfg();
      this.UT = var2.getId();
      IJavaType var3 = var2.getType();
      if (var3.isInt()) {
         this.E = 32;
      } else {
         if (!var3.isLong()) {
            throw new IllegalArgumentException();
         }

         this.E = 64;
      }
   }

   public int pC(boolean var1) {
      IDFA var2 = this.kS.doDataFlowAnalysis();
      long var3 = this.kS.getEntryAddress();
      this.sY = new HashMap();
      this.sY.put(var3, new bsd(this.E, false));
      this.ys = new HashMap();
      ArrayDeque var5 = new ArrayDeque();
      var5.add(var3);
      int var6 = this.kS.size() * 5;
      boolean var7 = false;

      while (true) {
         if (var5.isEmpty()) {
            if (var7 || !var1) {
               if (this.ld > 0) {
                  DUtil.cleanGraph(this.A);
                  this.kS.invalidateDataFlowAnalysis();
               }

               return this.ld;
            }

            Assert.a(var5.isEmpty());
            var5.addAll(this.sY.keySet());
            var6 += var5.size();
            var7 = true;
         } else {
            if (var6-- <= 0) {
               return -2;
            }

            long var8 = (Long)var5.remove();
            BasicBlock var10 = this.kS.getBlockAt(var8);
            bsd var11 = (bsd)this.sY.get(var8);
            if (var11 == null) {
               Assert.a(!var7);
               var11 = new bsd(this.E);
               this.sY.put(var8, var11);
            }

            bsd var12;
            if (var7) {
               var12 = (bsd)this.ys.get(var8);
               Assert.a(var12 != null);
            } else {
               var12 = var11.pC();
               long var13 = var8;

               for (int var15 = 0; var15 < var10.size(); var15++) {
                  IDInstruction var16 = (IDInstruction)var10.get(var15);
                  if (var15 == var10.size() - 1 && var16.isOpcode(DOpcodeType.IR_JUMP, DOpcodeType.IR_JCOND, DOpcodeType.IR_SWITCH)) {
                     break;
                  }

                  DUI var17 = var2.getDUI(var13);
                  if (var17.getDef().contains(this.UT) || var17.getDefPot().contains(this.UT) || var17.getSpoiled().contains(this.UT)) {
                     var12.E();
                  }

                  if (var16.isAssignToVar(this.wS.getId()) && var16.getAssignSource() instanceof IDImm var18) {
                     var12.wS();
                     var12.pC(var18);
                  }

                  var13 += var16.getSize();
               }

               this.ys.put(var8, var12);
            }

            boolean var20 = false;
            IDInstruction var14 = (IDInstruction)var10.getLast();
            if (var14.isJcondOrSwitch()) {
               DUI var21 = var2.getDUI(var14.getOffset(), var14);
               if (var21.getUse().contains(this.UT)) {
                  if (var14.isJcond()) {
                     var20 = this.pC(var14, var12, var7 ? null : var5);
                  } else {
                     var20 = this.A(var14, var12, var7 ? null : var5);
                  }
               }
            }

            if (!var20) {
               this.pC(var10, var12, var7 ? null : var5);
            }
         }
      }
   }

   private boolean pC(IDInstruction var1, bsd var2, Collection var3) {
      IDExpression var4 = var1.getJcondCondition();
      if (var4 == null) {
         return false;
      } else {
         brr var5 = new brr(this.A.getGlobalContext(), var4, this.wS);
         if (!var5.pC()) {
            return false;
         } else {
            bsd var6 = var5.A();
            bsd var7 = var6.sY();
            var6.kS(var2);
            var7.kS(var2);
            long var8 = var1.getBranchTarget();
            long var10 = var1.getOffsetEnd();
            if (var3 != null) {
               boolean var12 = false;
               bsd var13 = (bsd)this.sY.get(var8);
               if (var13 == null) {
                  var12 = true;
                  var13 = new bsd(this.E);
                  this.sY.put(var8, var13);
               }

               if (var13.pC(var6) || var12) {
                  var3.add(var8);
               }

               var12 = false;
               bsd var14 = (bsd)this.sY.get(var10);
               if (var14 == null) {
                  var12 = true;
                  var14 = new bsd(this.E);
                  this.sY.put(var10, var14);
               }

               if (var14.pC(var7) || var12) {
                  var3.add(var10);
               }
            } else {
               BasicBlock var16 = this.kS.getBlockByLastAddress(var1.getOffset());
               if (var6.UT()) {
                  BasicBlock var17 = this.kS.getBlockAt(var8);
                  var1.transformToNop();
                  this.kS.deleteEdge(var16, var17);
                  this.ld++;
               } else if (var7.UT()) {
                  BasicBlock var18 = this.kS.getBlockAt(var10);
                  var1.transformJcondToJump();
                  this.kS.deleteEdge(var16, var18);
                  this.ld++;
               }
            }

            return true;
         }
      }
   }

   private boolean A(IDInstruction var1, bsd var2, Collection var3) {
      if (var1.isSwitchOnInt() && var1.getSwitchExpression() == this.wS) {
         boolean var4 = true;
         int var5 = 0;
         bsd var6 = var2.pC();
         IDSwitchData var7 = var1.getSwitchData();
         int var8 = 0;

         for (ArrayList var9 = new ArrayList(var7.getCases()); var8 < var9.size(); var8++) {
            Object var10 = var9.get(var8);
            IDTarget var11 = var7.getTargetForCase(var10);
            long var12 = var11.getOffset();
            bsd var14;
            if (var4 && var10 instanceof Integer) {
               IDImm var15 = this.wS.spawn(((Integer)var10).intValue());
               var14 = var2.pC().kS(var15);
               var6.wS(var15);
            } else {
               var14 = var2;
               var5++;
            }

            if (var3 != null) {
               boolean var19 = false;
               bsd var16 = (bsd)this.sY.get(var12);
               if (var16 == null) {
                  var19 = true;
                  var16 = new bsd(this.E);
                  this.sY.put(var12, var16);
               }

               if (var16.pC(var14) || var19) {
                  var3.add(var12);
               }
            } else if (var14.UT()) {
               var7.deleteCase(var10);
               if (var12 != var1.getOffsetEnd() && var7.getKeysForTargets(var11).isEmpty()) {
                  BasicBlock var20 = this.kS.getBlockByLastAddress(var1.getOffset());
                  BasicBlock var21 = this.kS.getBlockAt(var12);
                  this.kS.deleteEdge(var20, var21);
               }

               this.ld++;
            }
         }

         if (var3 != null) {
            if (!var4 || var5 > 0) {
               var6 = var2;
            }

            long var17 = var1.getOffsetEnd();
            boolean var18 = false;
            bsd var13 = (bsd)this.sY.get(var17);
            if (var13 == null) {
               var18 = true;
               var13 = new bsd(this.E);
               this.sY.put(var17, var13);
            }

            if (var13.pC(var6) || var18) {
               var3.add(var17);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private void pC(BasicBlock var1, bsd var2, Collection var3) {
      if (var3 != null) {
         for (long var5 : var1.getOutputOffsets()) {
            boolean var7 = false;
            bsd var8 = (bsd)this.sY.get(var5);
            if (var8 == null) {
               var7 = true;
               var8 = new bsd(this.E);
               this.sY.put(var5, var8);
            }

            if (var8.pC(var2) || var7) {
               var3.add(var5);
            }
         }
      }
   }
}
