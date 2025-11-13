package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class cbk extends AbstractDOptimizer {
   private static final ILogger q = GlobalLog.getLogger(cbk.class);
   private static final boolean RF = false;
   private static final int xK = 1;
   private static final int Dw = 10;
   private static final int Uv = 20;
   private static final int oW = 25;
   private static final int gO = 30;
   private static final int nf = 31;
   private static final int gP = 32;
   private static final int za = 33;
   private static final int lm = 40;
   private static final int zz = 50;
   private static final int JY = 60;
   private static final int HF = 70;
   private static final int LK = 80;
   private static final int io = 81;
   private static final int qa = 90;
   private static AtomicInteger Hk = new AtomicInteger();
   private int Me;

   private static void q(String var0, Object... var1) {
   }

   private static void q(CFG var0, String var1) {
   }

   private static void q(IDMethodContext var0) {
   }

   public cbk() {
      this(1);
   }

   public cbk(int var1) {
      super(DOptimizerType.UNSAFE);
      this.getPluginInformation().setDescription(S.L("Code unflattening"));
      this.addTag("slow");
      this.addTag("deobfuscator");
      bto.oW(this);
      this.q(var1);
   }

   public int q() {
      return this.Me;
   }

   public void q(int var1) {
      this.Me = var1;
   }

   @Override
   public int perform() {
      if (this.Me == 0) {
         Object[] var10000 = new Object[0];
         return 0;
      } else {
         try {
            return this.RF();
         } catch (Exception var2) {
            if (Licensing.isReleaseBuild()) {
               com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var2, this.ctx.getMethodSignature());
            }

            return 0;
         }
      }
   }

   private int RF() {
      for (int var1 = 0; var1 < this.cfg.size(); var1++) {
         BasicBlock var2 = this.cfg.get(var1);
         IDInstruction var3 = (IDInstruction)var2.getLast();
         if ((var3.isJcond() || var3.isSwitchOnInt()) && (var2.insize() >= 3 || CFGUtil.countDeepInputs(var2, 2) >= 3) && CFGUtil.canReach(var2, var2)) {
            List var4 = buw.q(var3);
            if (var4.size() == 1) {
               int var5 = ((IDVar)var4.get(0)).getId();
               bup var6 = new bup(this.ctx);
               var6.Uv();
               if (!var6.Dw().contains(var5) && new cbk.CU(this.ctx).q(var3.getOffset(), var5)) {
                  cbk.eo var7 = new cbk.eo(this.ctx);
                  int var8 = var7.q(var3.getOffset(), var5);
                  if (var8 == 0) {
                     this.ctx.load(var7.q);
                     if (((com.pnfsoftware.jeb.corei.parsers.dexdec.ej)this.decomp).PV() >= 1) {
                        String var10 = cvm.q(
                           new byte[]{23, 7, 25, 10, 82, 4, 2, 28, 28, 79, 76, 67, 94, 65, 66, 25, 76, 93, 1, 70, 94, 81, 70, 64, 73, 78, 42, 22}, 2, 123
                        );
                        this.decomp.addSpecialComment(this.ctx.getMethodSignature(), var10);
                     }

                     return 1;
                  }

                  if (var8 == 1) {
                     String var9 = cvm.q(
                        new byte[]{
                           -53,
                           60,
                           1,
                           26,
                           83,
                           77,
                           8,
                           17,
                           28,
                           7,
                           11,
                           68,
                           67,
                           12,
                           26,
                           25,
                           8,
                           68,
                           66,
                           7,
                           69,
                           85,
                           27,
                           67,
                           75,
                           10,
                           13,
                           21,
                           0,
                           17,
                           11,
                           11,
                           1,
                           72,
                           12,
                           66,
                           23,
                           1,
                           84,
                           84,
                           28,
                           13,
                           69,
                           68,
                           1,
                           8,
                           2,
                           79,
                           66,
                           23,
                           28,
                           5,
                           8,
                           68,
                           72,
                           9,
                           18,
                           83,
                           76,
                           5,
                           4,
                           4,
                           29,
                           21,
                           21,
                           29,
                           6,
                           1,
                           29,
                           83,
                           84,
                           28,
                           9,
                           21,
                           84,
                           80,
                           2,
                           23,
                           19,
                           19,
                           11,
                           26,
                           17,
                           1,
                           68,
                           73,
                           29
                        },
                        1,
                        159
                     );
                     this.decomp.addSpecialComment(this.ctx.getMethodSignature(), var9);
                     return 0;
                  }

                  if (var8 == 10) {
                     return 0;
                  }

                  com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException("CFU errcode " + var8), this.ctx.getMethodSignature(), -1, null, true);
               }
            }
         }
      }

      return 0;
   }

   static class CU {
      private IDMethodContext q;

      public CU(IDMethodContext var1) {
         this.q = var1;
      }

      public boolean q(long var1, int var3) {
         ArrayDeque var4 = new ArrayDeque();
         var4.add(new Couple(var1, var3));
         HashSet var5 = new HashSet();
         CFG var6 = this.q.getCfg();
         IDFA var7 = var6.doDataFlowAnalysis();

         while (!var4.isEmpty()) {
            Couple var8 = (Couple)var4.remove();
            if (var5.add(var8)) {
               var1 = (Long)var8.getFirst();
               var3 = (Integer)var8.getSecond();
               Collection var9 = var7.getUseDefs(var1, var3);
               if (var9 == null) {
                  return false;
               }

               for (long var11 : var9) {
                  if (var11 < 0L) {
                     return false;
                  }

                  IDInstruction var13 = (IDInstruction)var6.getInstruction(var11);
                  if (!var13.isAssignToVar(var3)) {
                     return false;
                  }

                  IDExpression var14 = var13.getAssignSource();
                  if (var14.hasSideEffects(this.q, true)) {
                     return false;
                  }

                  for (IDVar var17 : DUtil.collectVars(var14)) {
                     var4.add(new Couple(var11, var17.getId()));
                  }
               }
            }
         }

         return true;
      }
   }

   static class eo {
      private IDMethodContext q;
      private CFG RF;
      private IDTryData xK;

      eo(IDMethodContext var1) {
         this.q = var1;
      }

      int q(long var1, int var3) {
         this.q.getCfg();
         HashSet var4 = new HashSet();
         var4.add(var3);
         bvd var5 = new bvd(this.q);
         if (!var5.q(var1, var4)) {
            return 10;
         } else {
            this.q = var5.xK();
            this.RF = this.q.getCfg();
            this.xK = this.q.getExceptionData();
            Object[] var10000 = new Object[]{this.RF.format()};

            try {
               int var6 = this.RF(var1, var3);
               if (var6 != 0) {
                  return var6;
               } else {
                  int var7 = cbk.Hk.incrementAndGet();
                  return var7 >= 2 && this.q.getMethodSignature().hashCode() % 2 == 0 ? 0 : 0;
               }
            } catch (Exception var8) {
               throw var8;
            }
         }
      }

      // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      private int RF(long var1, int var3) {
         IDInstruction var4 = (IDInstruction)this.RF.getInstruction(var1);
         Assert.a(var4.isJcond() || var4.isSwitch());
         boolean var5 = true;
         if (var5) {
            IDMasterOptimizer var6 = this.q.getGlobalContext().createMasterOptimizer(this.q, false, true, false);
            var6.setSafeMode(true);
            var6.add(new cen());
            int var7 = var6.perform();
            if (var7 > 0) {
               this.RF = this.q.getCfg();
               this.xK = this.q.getExceptionData();
            }
         }

         if (this.RF.getInstruction(var1) != var4) {
            return 20;
         } else {
            IDFA var34 = this.RF.doDataFlowAnalysis();
            Collection var35 = var34.getUseDefs(var1, var3);
            if (var35 == null) {
               return 25;
            } else {
               LinkedHashMap var8 = new LinkedHashMap();
               int var9 = 0;

               for (long var11 : var35) {
                  IDInstruction var13 = (IDInstruction)this.RF.getInstruction(var11);
                  if (var13 == null || !var13.isAssignToVar(var3)) {
                     return 30;
                  }

                  boolean var14 = false;
                  IDExpression var15 = var13.getAssignSource();
                  if (DUtil.hasVariables(var15)) {
                     var14 = true;
                  }

                  if (var15.hasSideEffects(this.q, false)) {
                     return 32;
                  }

                  if (var14) {
                     var9++;
                  } else {
                     Object[] var10000 = new Object[]{var11, var15};
                     var8.put(var11, var15);
                  }
               }

               if (var9 > 0 && var9 > var8.size() / 2) {
                  return 33;
               } else {
                  HashMap var36 = new HashMap();

                  for (long var12 : var8.keySet()) {
                     Object[] var52 = new Object[]{var12};
                     Couple var41 = this.RF.getInstructionLocation(var12);
                     BasicBlock var42 = (BasicBlock)var41.getFirst();
                     int var16 = (Integer)var41.getSecond();

                     while (true) {
                        IDInstruction var17 = (IDInstruction)var42.get(var16);
                        var52 = new Object[]{var17.getOffset(), var17};
                        if (++var16 >= var42.size()) {
                           long var18;
                           if (var17.isJump()) {
                              var18 = var17.getBranchTarget();
                           } else {
                              if (var17.isJcond() || var17.isSwitch() || var17.isThrow() || var17.isReturn()) {
                                 return 40;
                              }

                              var18 = var17.getOffsetEnd();
                           }

                           BasicBlock var20 = this.RF.getBlockAt(var18);
                           var16 = 0;
                           if (var18 == var1) {
                              IDInstruction var21;
                              if (!var17.isJump() && !var17.isAssignToVar(var3)) {
                                 long var22 = this.RF.getEndAddress();
                                 BasicBlock var24 = new BasicBlock();
                                 var24.add(var17.duplicateWithOffsetAndSize(var22, 1));
                                 var24.add(this.q.createJump((int)var18).withOffset(var22 + 1L));
                                 IDInstruction var25 = this.q.createJump((int)var22);
                                 var25.copyBaseFields(var17);
                                 var42.set(var42.size() - 1, var25);
                                 this.RF.addBlock(var24);
                                 this.RF.reconnectEdge(var42, var20, var24);
                                 this.RF.addEdge(var24, var20);
                                 this.xK.copyProtectedBlock((int)var42.getBase(), (int)var24.getBase());

                                 for (BasicBlock var27 : var42.getIrregularOutputBlocks()) {
                                    this.RF.addIrregularEdge(var24, var27, -1);
                                 }

                                 var21 = (IDInstruction)var24.getLast();
                              } else {
                                 var21 = var17;
                              }

                              IDState var45 = this.q.getGlobalContext().createState(true, false);
                              boolean var31 = false /* VF: Semaphore variable */;

                              long var44;
                              label213: {
                                 try {
                                    var31 = true;
                                    var45.setMaxIterationCount(1000);
                                    var45.enableEmulator(true);
                                    var45.enableSandbox(true);
                                    var45.setSubRoutineInvocationPolicy(255);
                                    var45.setFieldAccessPolicy(255);
                                    var45.pushContext("cfu3_context");
                                    var45.pushFrame(this.q.getMethodSignature());
                                    IDExpression var46 = (IDExpression)var8.get(var12);
                                    IDImm var48 = var46.evaluate(var45);
                                    var45.setPC((int)var1);
                                    var45.setVariable(var3, var48);
                                    var4.evaluate(var45);
                                    var44 = var45.getCurrentFrame().getNextPC().intValue();
                                    var31 = false;
                                    break label213;
                                 } catch (DexDecEvaluationException var32) {
                                    var31 = false;
                                 } finally {
                                    if (var31) {
                                       var45.deleteTopContext();
                                       var45.dispose();
                                    }
                                 }

                                 byte var47 = 70;
                                 var45.deleteTopContext();
                                 var45.dispose();
                                 return var47;
                              }

                              var45.deleteTopContext();
                              var45.dispose();
                              var36.put(var21, var44);
                              break;
                           }

                           if (var20.insize() != 1) {
                              return 50;
                           }

                           var42 = var20;
                        }
                     }
                  }

                  if (var36.isEmpty()) {
                     return 80;
                  } else if (var36.size() < 2) {
                     return 81;
                  } else {
                     for (IDInstruction var39 : var36.keySet()) {
                        long var40 = (Long)var36.get(var39);
                        var39.transformToJump((int)var40);
                        BasicBlock var43 = this.RF.getBlockFor(var39);
                        this.RF.deleteOutEdges(var43);
                        this.RF.addEdge(var43, this.RF.getBlockAt(var40));
                     }

                     Object[] var54 = new Object[]{this.RF.format()};
                     this.RF.invalidateDataFlowAnalysis();
                     DUtil.cleanGraph(this.q);
                     this.q.propagateTypes();
                     var54 = new Object[]{this.RF.format()};
                     return 0;
                  }
               }
            }
         }
      }
   }
}
