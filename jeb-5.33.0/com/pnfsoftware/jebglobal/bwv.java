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

public class bwv extends AbstractDOptimizer {
   private static final ILogger pC = GlobalLog.getLogger(bwv.class);
   private static AtomicInteger A = new AtomicInteger();
   private int kS;

   public bwv() {
      this(1);
   }

   public bwv(int var1) {
      super(DOptimizerType.UNSAFE);
      this.getPluginInformation().setDescription(S.L("Code unflattening"));
      this.addTag("slow");
      this.addTag("deobfuscator");
      bpl.UT(this);
      this.pC(var1);
   }

   public void pC(int var1) {
      this.kS = var1;
   }

   @Override
   public int perform() {
      if (this.kS == 0) {
         Object[] var10000 = new Object[0];
         return 0;
      } else {
         try {
            return this.pC();
         } catch (Exception var2) {
            if (Licensing.isReleaseBuild()) {
               com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var2, this.ctx.getMethodSignature());
            }

            return 0;
         }
      }
   }

   private int pC() {
      for (int var1 = 0; var1 < this.cfg.size(); var1++) {
         BasicBlock var2 = this.cfg.get(var1);
         IDInstruction var3 = (IDInstruction)var2.getLast();
         if ((var3.isJcond() || var3.isSwitchOnInt()) && (var2.insize() >= 3 || CFGUtil.countDeepInputs(var2, 2) >= 3) && CFGUtil.canReach(var2, var2)) {
            List var4 = bqq.pC(var3);
            if (var4.size() == 1) {
               int var5 = ((IDVar)var4.get(0)).getId();
               bqj var6 = new bqj(this.ctx);
               var6.kS();
               if (!var6.A().contains(var5) && new bwv.Sv(this.ctx).pC(var3.getOffset(), var5)) {
                  bwv.Av var7 = new bwv.Av(this.ctx);
                  int var8 = var7.pC(var3.getOffset(), var5);
                  if (var8 == 0) {
                     this.ctx.load(var7.pC);
                     if (((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)this.decomp).sY() >= 1) {
                        String var10 = ckx.pC(
                           new byte[]{-99, 60, 1, 26, 83, 77, 8, 17, 28, 7, 11, 68, 87, 22, 18, 83, 85, 27, 67, 75, 10, 13, 21, 0, 17, 11, 11, 1}, 1, 201
                        );
                        this.decomp.addSpecialComment(this.ctx.getMethodSignature(), var10);
                     }

                     return 1;
                  }

                  if (var8 == 1) {
                     String var9 = ckx.pC(
                        new byte[]{
                           23,
                           7,
                           25,
                           10,
                           82,
                           4,
                           2,
                           28,
                           28,
                           79,
                           76,
                           67,
                           74,
                           79,
                           68,
                           85,
                           93,
                           19,
                           78,
                           69,
                           18,
                           69,
                           92,
                           24,
                           74,
                           76,
                           46,
                           6,
                           21,
                           6,
                           2,
                           0,
                           68,
                           77,
                           78,
                           6,
                           90,
                           27,
                           82,
                           84,
                           1,
                           17,
                           83,
                           68,
                           4,
                           11,
                           9,
                           73,
                           14,
                           28,
                           8,
                           24,
                           1,
                           83,
                           70,
                           65,
                           50,
                           76,
                           0,
                           73,
                           31,
                           0,
                           19,
                           9,
                           0,
                           26,
                           79,
                           28,
                           22,
                           83,
                           17,
                           26,
                           23,
                           17,
                           68,
                           94,
                           49,
                           10,
                           6,
                           28,
                           28,
                           29,
                           2,
                           12,
                           84,
                           73,
                           92
                        },
                        2,
                        240
                     );
                     this.decomp.addSpecialComment(this.ctx.getMethodSignature(), var9);
                     return 0;
                  }

                  if (var8 == 10) {
                     return 0;
                  }

                  com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(new RuntimeException("CFU errcode " + var8), this.ctx.getMethodSignature(), -1, null, true);
               }
            }
         }
      }

      return 0;
   }

   static class Av {
      private IDMethodContext pC;
      private CFG A;
      private IDTryData kS;

      Av(IDMethodContext var1) {
         this.pC = var1;
      }

      int pC(long var1, int var3) {
         this.pC.getCfg();
         HashSet var4 = new HashSet();
         var4.add(var3);
         bqv var5 = new bqv(this.pC);
         if (!var5.pC(var1, var4)) {
            return 10;
         } else {
            this.pC = var5.pC();
            this.A = this.pC.getCfg();
            this.kS = this.pC.getExceptionData();
            Object[] var10000 = new Object[]{this.A.format()};

            try {
               int var6 = this.A(var1, var3);
               if (var6 != 0) {
                  return var6;
               } else {
                  int var7 = bwv.A.incrementAndGet();
                  return var7 >= 2 && this.pC.getMethodSignature().hashCode() % 2 == 0 ? 0 : 0;
               }
            } catch (Exception var8) {
               throw var8;
            }
         }
      }

      // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      private int A(long var1, int var3) {
         IDInstruction var4 = (IDInstruction)this.A.getInstruction(var1);
         Assert.a(var4.isJcond() || var4.isSwitch());
         boolean var5 = true;
         if (var5) {
            IDMasterOptimizer var6 = this.pC.getGlobalContext().createMasterOptimizer(this.pC, false, true, false);
            var6.setSafeMode(true);
            var6.add(new bzu());
            int var7 = var6.perform();
            if (var7 > 0) {
               this.A = this.pC.getCfg();
               this.kS = this.pC.getExceptionData();
            }
         }

         if (this.A.getInstruction(var1) != var4) {
            return 20;
         } else {
            IDFA var34 = this.A.doDataFlowAnalysis();
            Collection var35 = var34.getUseDefs(var1, var3);
            if (var35 == null) {
               return 25;
            } else {
               LinkedHashMap var8 = new LinkedHashMap();
               int var9 = 0;

               for (long var11 : var35) {
                  IDInstruction var13 = (IDInstruction)this.A.getInstruction(var11);
                  if (var13 == null || !var13.isAssignToVar(var3)) {
                     return 30;
                  }

                  boolean var14 = false;
                  IDExpression var15 = var13.getAssignSource();
                  if (DUtil.hasVariables(var15)) {
                     var14 = true;
                  }

                  if (var15.hasSideEffects(this.pC, false)) {
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
                     Couple var41 = this.A.getInstructionLocation(var12);
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

                           BasicBlock var20 = this.A.getBlockAt(var18);
                           var16 = 0;
                           if (var18 == var1) {
                              IDInstruction var21;
                              if (!var17.isJump() && !var17.isAssignToVar(var3)) {
                                 long var22 = this.A.getEndAddress();
                                 BasicBlock var24 = new BasicBlock();
                                 var24.add(var17.duplicateWithOffsetAndSize(var22, 1));
                                 var24.add(this.pC.createJump((int)var18).withOffset(var22 + 1L));
                                 IDInstruction var25 = this.pC.createJump((int)var22);
                                 var25.copyBaseFields(var17);
                                 var42.set(var42.size() - 1, var25);
                                 this.A.addBlock(var24);
                                 this.A.reconnectEdge(var42, var20, var24);
                                 this.A.addEdge(var24, var20);
                                 this.kS.copyProtectedBlock((int)var42.getBase(), (int)var24.getBase());

                                 for (BasicBlock var27 : var42.getIrregularOutputBlocks()) {
                                    this.A.addIrregularEdge(var24, var27, -1);
                                 }

                                 var21 = (IDInstruction)var24.getLast();
                              } else {
                                 var21 = var17;
                              }

                              IDState var45 = this.pC.getGlobalContext().createState(true, false);
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
                                    var45.pushFrame(this.pC.getMethodSignature());
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
                        BasicBlock var43 = this.A.getBlockFor(var39);
                        this.A.deleteOutEdges(var43);
                        this.A.addEdge(var43, this.A.getBlockAt(var40));
                     }

                     Object[] var54 = new Object[]{this.A.format()};
                     this.A.invalidateDataFlowAnalysis();
                     DUtil.cleanGraph(this.pC);
                     this.pC.propagateTypes();
                     var54 = new Object[]{this.A.format()};
                     return 0;
                  }
               }
            }
         }
      }
   }

   static class Sv {
      private IDMethodContext pC;

      public Sv(IDMethodContext var1) {
         this.pC = var1;
      }

      public boolean pC(long var1, int var3) {
         ArrayDeque var4 = new ArrayDeque();
         var4.add(new Couple(var1, var3));
         HashSet var5 = new HashSet();
         CFG var6 = this.pC.getCfg();
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
                  if (var14.hasSideEffects(this.pC, true)) {
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
}
