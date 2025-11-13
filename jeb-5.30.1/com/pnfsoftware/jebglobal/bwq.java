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

public class bwq {
   private static final ILogger q = GlobalLog.getLogger(bwq.class);
   private IDMethodContext RF;
   private CFG xK;
   private IDVar Dw;
   private int Uv;
   private int oW;
   private Map gO;
   private Map nf;
   private int gP;

   public static boolean q(IDVar var0) {
      IJavaType var1 = var0.getType();
      return var1.isInt() || var1.isLong();
   }

   public bwq(IDMethodContext var1, IDVar var2) {
      this.RF = var1;
      this.Dw = var2;
      this.xK = var1.getCfg();
      this.Uv = var2.getId();
      IJavaType var3 = var2.getType();
      if (var3.isInt()) {
         this.oW = 32;
      } else {
         if (!var3.isLong()) {
            throw new IllegalArgumentException();
         }

         this.oW = 64;
      }
   }

   public bwp q(long var1) {
      return this.gO == null ? null : (bwp)this.gO.get(var1);
   }

   public bwp RF(long var1) {
      return this.nf == null ? null : (bwp)this.nf.get(var1);
   }

   public int q() {
      return this.q(false);
   }

   public int q(boolean var1) {
      IDFA var2 = this.xK.doDataFlowAnalysis();
      long var3 = this.xK.getEntryAddress();
      this.gO = new HashMap();
      this.gO.put(var3, new bwp(this.oW, false));
      this.nf = new HashMap();
      ArrayDeque var5 = new ArrayDeque();
      var5.add(var3);
      int var6 = this.xK.size() * 5;
      boolean var7 = false;

      while (true) {
         if (var5.isEmpty()) {
            if (var7 || !var1) {
               if (this.gP > 0) {
                  DUtil.cleanGraph(this.RF);
                  this.xK.invalidateDataFlowAnalysis();
               }

               return this.gP;
            }

            Assert.a(var5.isEmpty());
            var5.addAll(this.gO.keySet());
            var6 += var5.size();
            var7 = true;
         } else {
            if (var6-- <= 0) {
               return -2;
            }

            long var8 = (Long)var5.remove();
            BasicBlock var10 = this.xK.getBlockAt(var8);
            bwp var11 = (bwp)this.gO.get(var8);
            if (var11 == null) {
               Assert.a(!var7);
               var11 = new bwp(this.oW);
               this.gO.put(var8, var11);
            }

            bwp var12;
            if (var7) {
               var12 = (bwp)this.nf.get(var8);
               Assert.a(var12 != null);
            } else {
               var12 = var11.RF();
               long var13 = var8;

               for (int var15 = 0; var15 < var10.size(); var15++) {
                  IDInstruction var16 = (IDInstruction)var10.get(var15);
                  if (var15 == var10.size() - 1 && var16.isOpcode(DOpcodeType.IR_JUMP, DOpcodeType.IR_JCOND, DOpcodeType.IR_SWITCH)) {
                     break;
                  }

                  DUI var17 = var2.getDUI(var13);
                  if (var17.getDef().contains(this.Uv) || var17.getDefPot().contains(this.Uv) || var17.getSpoiled().contains(this.Uv)) {
                     var12.za();
                  }

                  if (var16.isAssignToVar(this.Dw.getId()) && var16.getAssignSource() instanceof IDImm var18) {
                     var12.nf();
                     var12.q(var18);
                  }

                  var13 += var16.getSize();
               }

               this.nf.put(var8, var12);
            }

            boolean var20 = false;
            IDInstruction var14 = (IDInstruction)var10.getLast();
            if (var14.isJcondOrSwitch()) {
               DUI var21 = var2.getDUI(var14.getOffset(), var14);
               if (var21.getUse().contains(this.Uv)) {
                  if (var14.isJcond()) {
                     var20 = this.q(var14, var12, var7 ? null : var5);
                  } else {
                     var20 = this.RF(var14, var12, var7 ? null : var5);
                  }
               }
            }

            if (!var20) {
               this.q(var10, var12, var7 ? null : var5);
            }
         }
      }
   }

   private boolean q(IDInstruction var1, bwp var2, Collection var3) {
      IDExpression var4 = var1.getJcondCondition();
      if (var4 == null) {
         return false;
      } else {
         bwb var5 = new bwb(this.RF.getGlobalContext(), var4, this.Dw);
         if (!var5.xK()) {
            return false;
         } else {
            bwp var6 = var5.Dw();
            bwp var7 = var6.JY();
            var6.xK(var2);
            var7.xK(var2);
            long var8 = var1.getBranchTarget();
            long var10 = var1.getOffsetEnd();
            if (var3 != null) {
               boolean var12 = false;
               bwp var13 = (bwp)this.gO.get(var8);
               if (var13 == null) {
                  var12 = true;
                  var13 = new bwp(this.oW);
                  this.gO.put(var8, var13);
               }

               if (var13.q(var6) || var12) {
                  var3.add(var8);
               }

               var12 = false;
               bwp var14 = (bwp)this.gO.get(var10);
               if (var14 == null) {
                  var12 = true;
                  var14 = new bwp(this.oW);
                  this.gO.put(var10, var14);
               }

               if (var14.q(var7) || var12) {
                  var3.add(var10);
               }
            } else {
               BasicBlock var16 = this.xK.getBlockByLastAddress(var1.getOffset());
               if (var6.gP()) {
                  BasicBlock var17 = this.xK.getBlockAt(var8);
                  var1.transformToNop();
                  this.xK.deleteEdge(var16, var17);
                  this.gP++;
               } else if (var7.gP()) {
                  BasicBlock var18 = this.xK.getBlockAt(var10);
                  var1.transformJcondToJump();
                  this.xK.deleteEdge(var16, var18);
                  this.gP++;
               }
            }

            return true;
         }
      }
   }

   private boolean RF(IDInstruction var1, bwp var2, Collection var3) {
      if (var1.isSwitchOnInt() && var1.getSwitchExpression() == this.Dw) {
         boolean var4 = true;
         int var5 = 0;
         bwp var6 = var2.RF();
         IDSwitchData var7 = var1.getSwitchData();
         int var8 = 0;

         for (ArrayList var9 = new ArrayList(var7.getCases()); var8 < var9.size(); var8++) {
            Object var10 = var9.get(var8);
            IDTarget var11 = var7.getTargetForCase(var10);
            long var12 = var11.getOffset();
            bwp var14;
            if (var4 && var10 instanceof Integer) {
               IDImm var15 = this.Dw.spawn(((Integer)var10).intValue());
               var14 = var2.RF().Dw(var15);
               var6.Uv(var15);
            } else {
               var14 = var2;
               var5++;
            }

            if (var3 != null) {
               boolean var19 = false;
               bwp var16 = (bwp)this.gO.get(var12);
               if (var16 == null) {
                  var19 = true;
                  var16 = new bwp(this.oW);
                  this.gO.put(var12, var16);
               }

               if (var16.q(var14) || var19) {
                  var3.add(var12);
               }
            } else if (var14.gP()) {
               var7.deleteCase(var10);
               if (var12 != var1.getOffsetEnd() && var7.getKeysForTargets(var11).isEmpty()) {
                  BasicBlock var20 = this.xK.getBlockByLastAddress(var1.getOffset());
                  BasicBlock var21 = this.xK.getBlockAt(var12);
                  this.xK.deleteEdge(var20, var21);
               }

               this.gP++;
            }
         }

         if (var3 != null) {
            if (!var4 || var5 > 0) {
               var6 = var2;
            }

            long var17 = var1.getOffsetEnd();
            boolean var18 = false;
            bwp var13 = (bwp)this.gO.get(var17);
            if (var13 == null) {
               var18 = true;
               var13 = new bwp(this.oW);
               this.gO.put(var17, var13);
            }

            if (var13.q(var6) || var18) {
               var3.add(var17);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private void q(BasicBlock var1, bwp var2, Collection var3) {
      if (var3 != null) {
         for (long var5 : var1.getOutputOffsets()) {
            boolean var7 = false;
            bwp var8 = (bwp)this.gO.get(var5);
            if (var8 == null) {
               var7 = true;
               var8 = new bwp(this.oW);
               this.gO.put(var5, var8);
            }

            if (var8.q(var2) || var7) {
               var3.add(var5);
            }
         }
      }
   }
}
