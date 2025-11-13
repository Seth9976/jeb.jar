package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DExecutionParameters;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class bww extends AbstractDOptimizer {
   private static final ILogger pC = GlobalLog.getLogger(bww.class);
   private int A;
   private bww.Av kS;
   private boolean wS;

   public bww() {
      this(1, bww.Av.pC);
   }

   public bww(int var1, bww.Av var2) {
      super(DOptimizerType.UNSAFE);
      this.getPluginInformation().setDescription(S.L("Code unvirtualization"));
      Assert.a(var2 != null);
      this.setPriority(-50.0);
      this.addTag("slow");
      this.addTag("deobfuscator");
      bpl.UT(this);
      this.pC(var1);
      this.pC(var2);
   }

   public void pC(int var1) {
      this.A = var1;
   }

   public void pC(bww.Av var1) {
      this.kS = var1;
   }

   static void pC(IDInstruction var0) {
      var0.setData("UNV2", "");
   }

   static void A(IDInstruction var0) {
      var0.removeData("UNV2");
   }

   static Couple pC(CFG var0, boolean var1) {
      for (BasicBlock var3 : var0) {
         for (int var4 = 0; var4 < var3.size(); var4++) {
            if (((IDInstruction)var3.get(var4)).getData("UNV2") != null) {
               if (var1) {
                  ((IDInstruction)var3.get(var4)).removeData("UNV2");
               }

               return new Couple(var3, var4);
            }
         }
      }

      return null;
   }

   public Set pC() {
      Object var1 = (Set)this.ctx.getData("UNVIRT2_PROCESSED_VARIDS_CACHE");
      if (var1 == null) {
         var1 = new HashSet();
         this.ctx.setData("UNVIRT2_PROCESSED_VARIDS_CACHE", var1);
      }

      return (Set)var1;
   }

   @Override
   public int perform() {
      if (this.A == 0) {
         Object[] var10000 = new Object[0];
         return 0;
      } else {
         Set var1 = this.pC();
         int var8 = 0;

         while (true) {
            Couple var3 = pC(this.cfg, var8, null, false);
            if (var3 == null) {
               return 0;
            }

            var8 = (Integer)var3.getFirst();
            int var4 = ((IDVar)var3.getSecond()).getId();
            if (var1.add(var4)) {
               bww.Sv var5 = new bww.Sv(this.ctx);
               var5.kS = this.kS;
               var5.wS = this.wS;
               int var6 = var5.pC(this.cfg.get(var8).getBase(), var4);
               if (var6 == 0) {
                  this.ctx.load(var5.UT);
                  this.ctx.setData("unvirtualized", true);
                  if (((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)this.decomp).sY() >= 1) {
                     String var9 = ckx.pC(
                        new byte[]{77, 60, 1, 26, 83, 77, 8, 17, 28, 7, 11, 68, 87, 22, 18, 83, 85, 27, 67, 91, 31, 27, 6, 1, 20, 13, 5, 19, 31, 1}, 1, 25
                     );
                     this.decomp.addSpecialComment(this.ctx.getMethodSignature(), var9);
                  }

                  return 1;
               }

               if (var6 == 1) {
                  String var7 = ckx.pC(
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
                        90,
                        73,
                        61,
                        6,
                        20,
                        2,
                        0,
                        12,
                        90,
                        4,
                        10,
                        72,
                        15,
                        13,
                        7,
                        84,
                        73,
                        13,
                        28,
                        85,
                        19,
                        70,
                        21,
                        6,
                        10,
                        29,
                        22,
                        21,
                        23,
                        22,
                        14,
                        76,
                        40,
                        15,
                        9,
                        78,
                        1,
                        12,
                        71,
                        0,
                        21,
                        0,
                        0,
                        30,
                        12,
                        30,
                        12,
                        6,
                        23,
                        17,
                        13,
                        65,
                        45,
                        28,
                        80,
                        13,
                        26,
                        8,
                        19,
                        72,
                        4,
                        82,
                        77,
                        21,
                        76,
                        78,
                        69,
                        92,
                        93,
                        19,
                        69,
                        84
                     },
                     2,
                     206
                  );
                  this.decomp.addSpecialComment(this.ctx.getMethodSignature(), var7);
                  return 0;
               }
            }

            var8++;
         }
      }
   }

   static Couple pC(CFG var0, int var1, Integer var2, boolean var3) {
      for (int var4 = var1; var4 < var0.size(); var4++) {
         BasicBlock var5 = var0.get(var4);
         IDInstruction var6 = (IDInstruction)var5.getLast();
         if ((var6.isJcond() || var6.isSwitchOnInt()) && (var3 || var5.insize() >= 3 || CFGUtil.countDeepInputs(var5, 2) >= 3) && CFGUtil.canReach(var5, var5)) {
            IDArrayElt var7 = pC(var0, var6);
            if (var7 != null) {
               IDExpression var8 = var7.getIndex();
               List var9 = DUtil.collectVars(var8);
               if (var9.size() == 1) {
                  IDVar var10 = (IDVar)var9.get(0);
                  if (var2 == null || var2 == var10.getId()) {
                     return new Couple(var4, var10);
                  }
               }
            }
         }
      }

      return null;
   }

   static IDArrayElt pC(CFG var0, IDInstruction var1) {
      if (!var1.isJcondOrSwitch()) {
         return null;
      } else {
         IDExpression var2 = (IDExpression)var1.getOperand2();
         int var3 = 5;

         while (var3-- >= 0 && var2 instanceof IDVar) {
            IDFA var4 = var0.doDataFlowAnalysis();
            int var5 = var2.asVar().getId();
            Long var6 = var4.checkSingleDef(var1.getOffset(), var5);
            if (var6 == null || var6 < 0L) {
               break;
            }

            IDInstruction var7 = (IDInstruction)var0.getInstructionAt(var6);
            if (!var7.isAssignToVar(var5)) {
               break;
            }

            var1 = var7;
            var2 = var7.getAssignSource();
         }

         IDArrayElt[] var8 = new IDArrayElt[1];
         var2.visitDepthPost(new bwx(var8));
         return var8[0];
      }
   }

   public static enum Av {
      pC,
      A,
      kS;
   }

   static class Sv {
      private bww.Av kS;
      private boolean wS;
      private IDMethodContext UT;
      private CFG E;
      final int pC = 10;
      final int A = 2000;

      Sv(IDMethodContext var1) {
         this.UT = var1;
      }

      int pC(long var1, int var3) {
         this.UT = this.UT.copy();
         this.E = this.UT.getCfg();
         int var4 = this.A(var1, var3);
         if (var4 != 0) {
            return var4;
         } else {
            return !Licensing.buildkey.startsWith(ckx.pC(new byte[]{(byte)114, (byte)15, (byte)7, (byte)79, (byte)93, (byte)2, (byte)29}, 1, 24)) ? 0 : 0;
         }
      }

      // $VF: Unable to simplify switch on enum
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      int A(long var1, int var3) {
         BasicBlock var4 = this.E.getBlockAt(var1);
         IDInstruction var5 = (IDInstruction)var4.getLast();
         IDExpression var6 = (IDExpression)var5.getOperand2();
         Object[] var10000 = new Object[]{var4};
         var10000 = new Object[]{var5};
         var10000 = new Object[]{var6};
         if (this.pC(var4) == null) {
            return 5;
         } else {
            var4 = this.E.getBlockFor(var5);
            if (var4 == null) {
               return 6;
            } else {
               var6 = (IDExpression)var5.getOperand2();
               if (var6 instanceof IDCallInfo) {
                  boolean var7 = false;
                  IDCallInfo var8 = (IDCallInfo)var6;
                  if (var8.getInvokeType() == DInvokeType.VIRTUAL) {
                     IDExpression var9 = var8.getArgument(0);
                     if (var9 instanceof IDVar) {
                        long var10 = var4.getLastAddress();
                        IDFA var12 = this.E.doDataFlowAnalysis();
                        Long var13 = var12.checkSingleDef(var10, var9.asVar().getId());
                        if (var13 != null && var13 >= 0L) {
                           IDInstruction var14 = (IDInstruction)this.E.getInstruction(var13);
                           if (var14.isAssignToVar() && var14.getAssignSource() instanceof IDNewInfo) {
                              bww.pC(var5);
                              var10000 = new Object[0];
                              long var15 = System.currentTimeMillis();
                              bqx var17 = new bqx(this.UT);
                              boolean var18 = var17.pC(var14);
                              var10000 = new Object[]{System.currentTimeMillis() - var15, var18};
                              if (!var18) {
                                 bww.A(var5);
                              } else {
                                 IDMasterOptimizer var19 = this.UT.getGlobalContext().createMasterOptimizer(this.UT);
                                 var19.setSafeMode(true);
                                 var19.setPolicyForOptimizerTag("inliner", false);
                                 var19.setPolicyForOptimizerTag("slow", false);
                                 var19.perform();
                                 this.E = this.UT.getCfg();
                                 Couple var20 = bww.pC(this.E, true);
                                 if (var20 != null) {
                                    var4 = (BasicBlock)var20.getFirst();
                                    IDInstruction var36 = (IDInstruction)var4.getLast();
                                    var7 = true;
                                 }
                              }
                           }
                        }
                     }
                  }

                  if (!var7) {
                     return 10;
                  }
               }

               var10000 = new Object[0];
               long var29 = System.currentTimeMillis();

               byte var11;
               try {
                  byte var30 = switch (bwy.pC[this.kS.ordinal()]) {
                     case 1 -> -1;
                     case 2 -> 0;
                     case 3 -> 100;
                     default -> throw new IncompatibleClassChangeError();
                  };
                  bqm var31 = new bqm(this.UT, var30);
                  if (var31.pC(var3) || this.wS) {
                     return 0;
                  }

                  var11 = 20;
               } finally {
                  long var22 = System.currentTimeMillis() - var29;
                  var10000 = new Object[]{com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var22)};
               }

               return var11;
            }
         }
      }

      private IDInstruction pC(BasicBlock var1) {
         IDInstruction var2 = (IDInstruction)var1.getLast();
         if (!var2.isJcondOrSwitch()) {
            return null;
         } else {
            IDArrayElt var3 = bww.pC(this.E, var2);
            if (var3 == null) {
               return null;
            } else {
               IDExpression var4 = var3.getArray();
               if (var4 instanceof IDNewArrayInfo var29) {
                  return var29.getCountOfInitialValues() > 0 ? var2 : null;
               } else if (!(var4 instanceof IDVar)) {
                  return null;
               } else {
                  IDVar var5 = var3.getArray().asVar();
                  CFGUtil.BlockGroup var6 = new CFGUtil.BlockGroup(this.E, 0L, var1.getBase());
                  var6.determine();
                  if (var6.getEntryPoints().size() != 1) {
                     return null;
                  } else if (var6.getExitPoints().size() != 1) {
                     return null;
                  } else {
                     long var7 = (Long)var6.getExitPoints().iterator().next();
                     BasicBlock var9 = this.E.getBlockAt(var7);
                     if (var9.outsize() != 1) {
                        return null;
                     } else {
                        IDInstruction var10 = (IDInstruction)var9.getLast();
                        if (!var10.isJump() && !var10.isJcond() && !var10.isSwitch()) {
                           TreeMap var11 = new TreeMap();
                           this.E.getInstructions().forEach(var1x -> var11.put((int)var1x.getOffset(), var1x));
                           IDFA var12 = this.E.doDataFlowAnalysis();

                           for (IDVar var14 : this.UT.getParameterVariables()) {
                              for (long var16 : var12.getInputMap(var14.getId())) {
                                 IDInstruction var18 = (IDInstruction)var11.get((int)var16);
                                 if (var18 != null && !var18.getBreakingFlow().isBroken()) {
                                    IDInstruction var19 = var18.duplicate();
                                    var19.transformToNop();
                                    var11.put((int)var16, var19);
                                 }
                              }
                           }

                           IDGlobalContext var30 = this.UT.getGlobalContext();
                           IDState var31 = var30.getEmulator();
                           if (!var31.canRun()) {
                              return null;
                           } else {
                              Watchdog var32 = var31.setWatchdog(this.UT.getWatchdog());
                              IDEmuContext var17 = var31.pushContext("UNV2CTX{" + this.UT.getMethodSignature() + "}");
                              var17.pushOriginInfo(this.UT.getMethodSignature());

                              IDNewArrayInfo var33;
                              try {
                                 var31.pushFrame(this.UT.getMethodSignature());
                                 DExecutionParameters var34 = new DExecutionParameters(var11);
                                 var34.pc = 0;
                                 var34.pcThresholdMin = var34.pc;
                                 var34.pcThresholdMax = (int)var2.getOffset();
                                 var34.pcExpectedTermination = var34.pcThresholdMax;

                                 for (IDVar var20 : this.UT.getParameterVariables()) {
                                    var34.addInitialValue(var20.getId(), var30.createConstant(0L, var20.getType()));
                                 }

                                 if (var31.getMaxIterationCount() < 1000000) {
                                    var31.setMaxIterationCount(1000000);
                                 }

                                 try {
                                    var31.execute(var34);
                                 } catch (DexDecEvaluationException var26) {
                                 }

                                 Object var38 = var31.getArrayObject(var5.evaluate(var31));
                                 if (var38 instanceof byte[] var40) {
                                    if (!this.pC(var40)) {
                                       return null;
                                    }

                                    var33 = var30.createByteArray(var40);
                                 } else if (var38 instanceof short[] var41) {
                                    if (!this.pC(var41)) {
                                       return null;
                                    }

                                    var33 = var30.createShortArray(var41);
                                 } else {
                                    if (!(var38 instanceof int[] var42)) {
                                       return null;
                                    }

                                    if (!this.pC(var42)) {
                                       return null;
                                    }

                                    var33 = var30.createIntArray(var42);
                                 }
                              } catch (Exception var27) {
                                 bww.pC.catching(var27);
                                 return null;
                              } finally {
                                 var31.setWatchdog(var32);
                              }

                              if (var33 == null) {
                                 return null;
                              } else {
                                 if (var10.getSize() == 1) {
                                    DUtil.modifyInstructionSizes(this.UT, var1x -> var1x == var10 ? 2 : null);
                                 }

                                 var10.adjustSize(-1);
                                 IDInstruction var35 = this.UT.createAssign(var5, var33).withOffset(var10.getOffsetEnd());
                                 var9.add(var35);
                                 this.E.invalidateDataFlowAnalysis();
                                 IDMasterOptimizer var39 = this.UT.getGlobalContext().createMasterOptimizer(this.UT);
                                 var39.setSafeMode(true);
                                 var39.setPolicyForOptimizerTag("inliner", false);
                                 var39.setPolicyForOptimizerTag("slow", false);
                                 var39.perform();
                                 this.E = this.UT.getCfg();
                                 return var35;
                              }
                           }
                        } else {
                           return null;
                        }
                     }
                  }
               }
            }
         }
      }

      boolean pC(byte[] var1) {
         if (var1 != null && var1.length >= 10 && var1.length <= 2000) {
            byte var2 = var1[0];

            for (int var3 = 1; var3 < var1.length; var3++) {
               if (var1[var3] != var2) {
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      }

      boolean pC(short[] var1) {
         if (var1 != null && var1.length >= 10 && var1.length <= 2000) {
            short var2 = var1[0];

            for (int var3 = 1; var3 < var1.length; var3++) {
               if (var1[var3] != var2) {
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      }

      boolean pC(int[] var1) {
         if (var1 != null && var1.length >= 10 && var1.length <= 2000) {
            int var2 = var1[0];

            for (int var3 = 1; var3 < var1.length; var3++) {
               if (var1[var3] != var2) {
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      }
   }
}
