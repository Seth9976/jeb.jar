package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class aoh extends AbstractEOptimizer {
   private IdentityHashMap pC = new IdentityHashMap();

   public aoh() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY, OptimizerType.UNSAFE);
      this.setRequiredModeThreshold(OptimizerMode.UNFRIENDLY);
      this.addTag("deobfuscator");
      this.getPluginInformation()
         .setDescription(
            ckx.pC(
               new byte[]{
                  93,
                  53,
                  0,
                  17,
                  8,
                  29,
                  4,
                  84,
                  84,
                  27,
                  79,
                  82,
                  23,
                  22,
                  7,
                  27,
                  29,
                  23,
                  69,
                  65,
                  65,
                  82,
                  29,
                  26,
                  1,
                  29,
                  7,
                  11,
                  69,
                  87,
                  31,
                  7,
                  28,
                  22,
                  69,
                  67,
                  12,
                  1,
                  26,
                  6,
                  29,
                  3,
                  65,
                  75,
                  10,
                  3,
                  24,
                  87,
                  87,
                  22,
                  18,
                  83,
                  70,
                  10,
                  13,
                  21,
                  0,
                  17,
                  11,
                  11,
                  1,
                  68,
                  66,
                  27,
                  89,
                  65,
                  15,
                  78,
                  79,
                  13,
                  4,
                  19,
                  6,
                  16,
                  2,
                  21,
                  27,
                  29
               },
               1,
               28
            )
         );
   }

   @Override
   public int perform() {
      try {
         return this.pC();
      } catch (Exception var2) {
         if (Licensing.isDebugBuild()) {
            throw var2;
         } else {
            acj.pC(var2, this.ectx);
            return 0;
         }
      }
   }

   private int pC() {
      asl var1 = new asl(this.cfg);

      for (int var2 = 0; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         if (var3.insize() >= 3) {
            IEStatement var4 = (IEStatement)var3.getLast();
            if (var4.isConditionalJump() || var4.isSwitch()) {
               List var5 = this.pC(var4);
               if (var5.size() == 1) {
                  int var6 = (Integer)var5.get(0);
                  IEVar var7 = this.ectx.getVariableById(var6);
                  asl.Av var8 = var1.pC(var3.getLastAddress(), var7);
                  if (var8.A()) {
                     Collection var9 = var8.pC();
                     if (var9.size() >= 3) {
                        ArrayList var10 = new ArrayList();
                        int var11 = this.pC(var3, var6, var9, var10);
                        if (var11 == 0) {
                           aoh.Ws var12 = new aoh.Ws(this.ectx, this.cfg);
                           var11 = var12.pC(var3, var6, var10);
                           if (var11 == 0) {
                              this.ectx
                                 .addNote(
                                    ckx.pC(
                                       new byte[]{
                                          23,
                                          7,
                                          21,
                                          89,
                                          0,
                                          6,
                                          18,
                                          28,
                                          29,
                                          78,
                                          77,
                                          67,
                                          94,
                                          65,
                                          66,
                                          25,
                                          76,
                                          93,
                                          74,
                                          76,
                                          83,
                                          68,
                                          70,
                                          80,
                                          66,
                                          69,
                                          43,
                                          82,
                                          73,
                                          22,
                                          2,
                                          22,
                                          65,
                                          7,
                                          11,
                                          68,
                                          75,
                                          10,
                                          29,
                                          66,
                                          15,
                                          1,
                                          0,
                                          67,
                                          0,
                                          18,
                                          15,
                                          6,
                                          2,
                                          64,
                                          79,
                                          126,
                                          49,
                                          28,
                                          14,
                                          68,
                                          40,
                                          31,
                                          13,
                                          66,
                                          30,
                                          12,
                                          71,
                                          28,
                                          28,
                                          26,
                                          83,
                                          82,
                                          10,
                                          3,
                                          17,
                                          27,
                                          27,
                                          12,
                                          30,
                                          75,
                                          49,
                                          67,
                                          80,
                                          11,
                                          23,
                                          15,
                                          2,
                                          26,
                                          84,
                                          84,
                                          71,
                                          67,
                                          11,
                                          101,
                                          95,
                                          88,
                                          91,
                                          95,
                                          73,
                                          117,
                                          92,
                                          67,
                                          83,
                                          83,
                                          73,
                                          111,
                                          63,
                                          6,
                                          8,
                                          14,
                                          5,
                                          31,
                                          69,
                                          19,
                                          29,
                                          70,
                                          15,
                                          6,
                                          28,
                                          0,
                                          29,
                                          28,
                                          22,
                                          0,
                                          14,
                                          22,
                                          18,
                                          0,
                                          3,
                                          7,
                                          18,
                                          90
                                       },
                                       2,
                                       177
                                    )
                                 );
                              return 1;
                           }
                        }

                        acj.pC(new RuntimeException("CFU errcode " + var11), this.ectx);
                     }
                  }
               }
            }
         }
      }

      return 0;
   }

   int pC(BasicBlock var1, int var2, Collection var3, List var4) {
      IEVar var5 = this.ectx.getVariableById(var2);

      label200:
      for (BigInteger var7 : var3) {
         EState var9 = this.ectx.buildEmptyState();
         var9.setExecuteSubRoutines(false);
         var9.setVirtualPC((int)var1.getBase());
         var9.setValue(var5, ajr.pC(var7, var5.getBitsize()));
         Object[] var10000 = new Object[]{var1.getBase(), var5, var7.toString(16)};
         aoh.K var10 = new aoh.K(var1, var2, var7);
         Boolean var11 = null;
         ArrayDeque var12 = new ArrayDeque();
         var12.add(new aoh.Sv(var1, var9));
         HashSet var13 = new HashSet();

         while (true) {
            BasicBlock var8;
            int var15;
            long var16;
            int var29;
            boolean var30;
            while (true) {
               if (var12.isEmpty()) {
                  var10000 = new Object[]{var10};
                  var4.add(var10);
                  continue label200;
               }

               aoh.Sv var14 = (aoh.Sv)var12.remove();
               var8 = var14.pC;
               var9 = var14.A;
               if (var8 != var1) {
                  var15 = 0;
                  var16 = var8.getBase();
               } else {
                  for (int var18 = var8.size() - 2; var18 >= 0; var18--) {
                     IEStatement var19 = (IEStatement)var8.get(var18);
                     if (!(var19 instanceof IEAssign)
                        || !(((IEAssign)var19).getDstOperand() instanceof IEVar)
                        || !(((IEAssign)var19).getSrcOperand() instanceof IEVar)) {
                        return 10;
                     }

                     IEVar var20 = ((IEAssign)var19).getDstOperand().asVar();
                     IEVar var21 = ((IEAssign)var19).getSrcOperand().asVar();
                     if (var20 != var5 && var21 != var5) {
                        return 10;
                     }

                     IEVar var22 = var20 == var5 ? var21 : var20;
                     var9.setValue(var22, var9.getValue(var5));
                  }

                  var15 = var8.size() - 1;
                  var16 = var8.getLastAddress();
               }

               var29 = -1;
               var30 = false;
               if (var13.add(var16)) {
                  break;
               }

               aoh.K.Av var31 = var10.pC(var8);
               if (var31 != null) {
                  Assert.a(var31.A >= 0 && var31.pC.outsize() >= 2);
                  var10.pC(var31);
                  var30 = true;
                  break;
               }
            }

            while (var15 < var8.size()) {
               IEStatement var32 = (IEStatement)var8.get(var15);
               var10000 = new Object[]{var16, var32};
               var9.setVirtualPC((int)var16);
               Long var34 = var32.getPrimaryLowerLevelAddress();
               if (var34 == null) {
                  var9.removeValue(this.ectx.getProgramCounterId());
               } else {
                  var9.setValue(this.ectx.getProgramCounterId(), var34);
               }

               var29 = -1;
               List var37 = this.pC(var32);
               if (var9.getVariables().keySet().containsAll(var37)) {
                  try {
                     if (var32.evaluate(var9) != null) {
                        var29 = var9.getVirtualPC();
                     }
                  } catch (Exception var27) {
                  }
               }

               if (var29 == -1) {
                  List var40 = this.A(var32);
                  if (var40.contains(var2)) {
                     var11 = false;
                     if (var32 instanceof IEAssign && ((IEAssign)var32).getDstOperand() == var5 && ((IEAssign)var32).getSrcOperand() instanceof IECond) {
                        IECond var24 = ((IEAssign)var32).getSrcOperand().asCond();
                        IEGeneric var25 = var24.getExpressionTrue();
                        IEGeneric var26 = var24.getExpressionFalse();
                        if (var25 instanceof IEImm
                           && var26 instanceof IEImm
                           && var3.contains(var25.asImm().getValue())
                           && var3.contains(var26.asImm().getValue())) {
                           var11 = true;
                        }
                     }
                  }

                  for (int var48 : var40) {
                     var9.removeValue(var48);
                  }
               } else {
                  IEImm var23 = var9.getValueSafe(var5);
                  if (var23 != null && !var3.contains(var23.getValue())) {
                     return 20;
                  }
               }

               var16 += var32.getSize();
               var15++;
            }

            if (var8.outsize() == 1) {
               var29 = (int)var8.getOutputBlock(0).getBase();
            } else if (var30) {
               var29 = -1;
            }

            var10.pC(var8, var29, var9);
            List var33 = var8.getOutputBlocks();
            if (!var33.isEmpty() && var29 >= 0) {
               Iterator var35 = var33.iterator();

               while (var35.hasNext()) {
                  BasicBlock var38 = (BasicBlock)var35.next();
                  if (var38.getBase() != var29) {
                     var35.remove();
                  }
               }
            }

            label196:
            for (BasicBlock var39 : var33) {
               if (var39 == var1) {
                  IEImm var44 = var9.getValueSafe(var5);
                  if ((var44 == null || !var3.contains(var44.getValue())) && !var11) {
                     return 30;
                  }
               } else {
                  for (aoh.Sv var46 : var12) {
                     if (var46.pC == var39) {
                        continue label196;
                     }
                  }

                  if (var13.contains(var39.getBase())) {
                     aoh.K.Av var42 = var10.pC(var39);
                     if (var42.A >= 0 && var42.pC.outsize() >= 2) {
                        EState var47 = new EState(var9, false);
                        var12.add(new aoh.Sv(var42.pC, var47));
                     }
                  } else {
                     EState var43 = new EState(var9, false);
                     var12.add(new aoh.Sv(var39, var43));
                  }
               }
            }
         }
      }

      return 0;
   }

   private List pC(IEStatement var1) {
      return this.pC(var1, false);
   }

   private List A(IEStatement var1) {
      return this.pC(var1, true);
   }

   private List pC(IEStatement var1, boolean var2) {
      Couple var5 = (Couple)this.pC.get(var1);
      List var3;
      List var4;
      if (var5 != null) {
         var3 = (List)var5.getFirst();
         var4 = (List)var5.getSecond();
      } else {
         EDefUseInfo var6 = new EDefUseInfo();
         var1.getDefUse(var6);
         var3 = var6.getDefinedVarIds();
         var4 = var6.getUsedVarIds();
         this.pC.put(var1, new Couple(var3, var4));
      }

      return var2 ? var3 : var4;
   }

   static class Av {
      IERoutineContext pC;
      CFG A;
      BasicBlock kS;
      int wS;
      Map UT;
      List E = new ArrayList();
      Map sY = new HashMap();
      Map ys = new IdentityHashMap();

      Av(IERoutineContext var1, CFG var2, BasicBlock var3, int var4, Map var5) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
         this.UT = var5;
      }

      int pC() {
         for (BasicBlock var2 : this.kS.getInputs()) {
            int var3 = this.pC(var2);
            if (var3 != 0) {
               return var3;
            }
         }

         for (BasicBlock var8 : this.A) {
            this.sY.put((int)var8.getBase(), this.E.size());

            for (int var9 = 0; var9 < var8.size(); var9++) {
               IEStatement var4 = (IEStatement)var8.get(var9);
               var4.setSize(1);
               List var5 = (List)this.ys.get(var4);
               if (var5 == null) {
                  this.E.add(var4);
               } else {
                  this.E.addAll(var5);
               }
            }
         }

         this.A();
         CFG var7 = this.pC.buildCfg(this.E, false, false);
         var7.setVariableInformationProvider((aki)this.pC);
         var7.format();
         Object[] var10000 = new Object[0];
         this.pC.setCfg(var7);
         return 0;
      }

      private int pC(BasicBlock var1) {
         int var5 = this.wS;
         BasicBlock var6 = this.kS;

         while (true) {
            int var7;
            if (var6 == this.kS) {
               var7 = var6.size() - 2;
            } else {
               var7 = var6.size() - 1;
            }

            for (; var7 >= 0; var7--) {
               IEStatement var8 = (IEStatement)var6.get(var7);
               if (var8.isAssignTo(var5)) {
                  aoh.Av.Av var9 = this.pC(var8.asAssign());
                  if (var9 != null) {
                     IEGeneric var2 = var9.A;
                     BigInteger var3 = var9.pC;
                     BigInteger var4 = var9.kS;
                     var7 = (int)this.A.findInstruction((IEStatement)this.UT.get(var3)).getOffset();
                     int var13 = var4 == null ? -1 : (int)this.A.findInstruction((IEStatement)this.UT.get(var4)).getOffset();
                     ArrayList var14 = new ArrayList();

                     for (int var15 = 0; var15 < this.kS.size() - 1; var15++) {
                        IEStatement var17 = (IEStatement)((IEStatement)this.kS.get(var15)).duplicate();
                        var14.add(var17);
                     }

                     IEStatement var16 = (IEStatement)var1.getLast();
                     if (var13 < 0) {
                        IEJump var18 = this.pC.createJump(var7);
                        var18.addLowerLevelAddress(var16.getPrimaryLowerLevelAddress());
                        var14.add(var18);
                     } else {
                        IEJump var19 = this.pC.createJump(var7, var2.duplicate());
                        var19.addLowerLevelAddress(var16.getPrimaryLowerLevelAddress());
                        var14.add(var19);
                        var19 = this.pC.createJump(var13);
                        var19.addLowerLevelAddress(var16.getPrimaryLowerLevelAddress());
                        var14.add(var19);
                     }

                     if (var16 instanceof IEJump) {
                        this.A(var16, var14);
                     } else {
                        if (var16 instanceof IESwitch) {
                           return 80;
                        }

                        this.pC(var16, var14);
                     }

                     return 0;
                  }

                  IEGeneric var10 = var8.asAssign().getSrcOperand();
                  if (!(var10 instanceof IEVar)) {
                     return 40;
                  }

                  int var11 = var10.asVar().getId();
                  var5 = var11;
               }
            }

            if (var6 != this.kS) {
               if (var6.insize() != 1 || var6.getInputBlock(0).outsize() != 1) {
                  return 50;
               }

               var6 = var6.getInputBlock(0);
            } else {
               var6 = var1;
            }
         }
      }

      aoh.Av.Av pC(IEAssign var1) {
         IEGeneric var2 = var1.getSrcOperand();
         if (var2 instanceof IEImm) {
            BigInteger var3 = var2.asImm().getValue();
            if (this.UT.containsKey(var3)) {
               return new aoh.Av.Av(var3);
            }
         } else if (var2 instanceof IECond) {
            IECond var7 = var2.asCond();
            if (var7.getExpressionTrue() instanceof IEImm && var7.getExpressionFalse() instanceof IEImm) {
               IEGeneric var4 = var7.getCondition();
               BigInteger var5 = var7.getExpressionTrue().asImm().getValue();
               BigInteger var6 = var7.getExpressionFalse().asImm().getValue();
               if (this.UT.containsKey(var5) && this.UT.containsKey(var6)) {
                  return new aoh.Av.Av(var4, var5, var6);
               }
            }
         }

         return null;
      }

      private void pC(IEStatement var1, Collection var2) {
         Object var3 = (List)this.ys.get(var1);
         if (var3 == null) {
            var3 = new ArrayList();
            this.ys.put(var1, var3);
            var3.add(var1);
         }

         var3.addAll(var2);
      }

      private void A(IEStatement var1, Collection var2) {
         Object var3 = (List)this.ys.get(var1);
         if (var3 == null) {
            var3 = new ArrayList();
            this.ys.put(var1, var3);
         }

         var3.addAll(var2);
      }

      private void A() {
         int var1 = 0;

         for (IEStatement var3 : this.E) {
            Assert.a(var3.getSize() == 1);
            if (var3 instanceof IEJump) {
               IEJump var4 = var3.asJump();
               int var5 = var4.getBranchAddress();
               if (var4.hasFlags(32768)) {
                  var5 = var1 + 1 + var5;
                  var4.setFlags(0);
               } else {
                  var5 = (Integer)this.sY.get(var5);
               }

               var4.setBranchAddress(var5);
            } else if (var3 instanceof IESwitch) {
               throw new RuntimeException("TBI");
            }

            var1++;
         }
      }

      static class Av {
         BigInteger pC;
         IEGeneric A;
         BigInteger kS;

         Av(BigInteger var1) {
            if (var1 == null) {
               throw new IllegalArgumentException();
            } else {
               this.pC = var1;
            }
         }

         Av(IEGeneric var1, BigInteger var2, BigInteger var3) {
            if (var1 != null && var2 != null && var3 != null) {
               this.A = var1;
               this.pC = var2;
               this.kS = var3;
            } else {
               throw new IllegalArgumentException();
            }
         }
      }
   }

   static class K {
      BasicBlock pC;
      int A;
      BigInteger kS;
      List wS = new ArrayList();

      K(BasicBlock var1, int var2, BigInteger var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }

      aoh.K.Av pC(BasicBlock var1) {
         for (aoh.K.Av var3 : this.wS) {
            if (var3.pC == var1) {
               return var3;
            }
         }

         return null;
      }

      aoh.K.Av pC(BasicBlock var1, int var2, EState var3) {
         aoh.K.Av var4 = new aoh.K.Av();
         var4.pC = var1;
         var4.A = var2;
         var4.kS = var3;
         this.wS.add(var4);
         return var4;
      }

      boolean pC(aoh.K.Av var1) {
         return this.wS.remove(var1);
      }

      @Override
      public String toString() {
         return Strings.ff("%s", this.wS);
      }

      static class Av {
         BasicBlock pC;
         int A = -1;
         EState kS;

         @Override
         public String toString() {
            return Strings.ff("%X%c", this.pC.getBase(), Character.valueOf((char)(this.A < 0 ? '?' : '>')));
         }
      }
   }

   static class Sv {
      BasicBlock pC;
      EState A;

      Sv(BasicBlock var1, EState var2) {
         this.pC = var1;
         this.A = var2;
      }

      @Override
      public String toString() {
         return Strings.ff("%X", this.pC.getBase());
      }
   }

   static class Ws {
      IERoutineContext pC;
      CFG A;
      List kS = new ArrayList();
      Map wS = new HashMap();

      Ws(IERoutineContext var1, CFG var2) {
         this.pC = var1;
         this.A = var2;
      }

      int pC(BasicBlock var1, int var2, List var3) {
         for (aoh.K var5 : var3) {
            if (var5.pC != var1 || var5.A != var2) {
               throw new RuntimeException("Branch unexpectly does not start on the header block");
            }
         }

         int var28 = (int)var1.getBase();
         IEVar var29 = this.pC.getVariableById(var2);
         HashMap var6 = new HashMap();

         for (int var7 = 0; var7 < this.A.size(); var7++) {
            BasicBlock var8 = this.A.get(var7);
            int var9 = (int)var8.getBase();
            this.wS.put(var9, this.kS.size());
            Long var10 = ((IEStatement)var8.get(0)).getPrimaryLowerLevelAddress();

            for (int var11 = 0; var11 < var8.size() - 1; var11++) {
               IEStatement var12 = (IEStatement)var8.get(var11);
               IEStatement var13 = (IEStatement)var12.duplicate();
               var13.setSize(1);
               this.pC(var13);
               this.kS.add(var13);
            }

            if (var8 == var1) {
               for (aoh.K var43 : var3) {
                  Object[] var10000 = new Object[]{var43};
                  IEImm var46 = this.pC.createImm(var43.kS, var29.getBitsize());
                  IEJump var14 = this.pC.createJump(-1, EUtil.ne(var29, var46));
                  var14.addLowerLevelAddress(var10);
                  this.kS.add(var14);
                  HashMap var15 = new HashMap();
                  ArrayList var16 = new ArrayList();
                  var6.put(var43.kS, this.kS.size());

                  for (aoh.K.Av var18 : var43.wS) {
                     int var19 = var18.A;
                     boolean var20 = var18.pC.outsize() == 0;
                     int var21 = var20 ? var18.pC.size() : var18.pC.size() - 1;
                     var15.put((int)var18.pC.getBase(), this.kS.size());

                     for (int var22 = 0; var22 < var21; var22++) {
                        IEStatement var23 = (IEStatement)var18.pC.get(var22);
                        IEStatement var24 = (IEStatement)var23.duplicate();
                        var24.setSize(1);
                        this.kS.add(var24);
                        var10 = var23.getPrimaryLowerLevelAddress();
                     }

                     if (!var20) {
                        AddressableInstruction var53 = var18.pC.getLast2();
                        int var54 = (int)var53.getOffset() + var53.getSize();
                        IEStatement var55 = (IEStatement)var53.getInstruction();
                        IEStatement var25 = (IEStatement)var55.duplicate();
                        var25.setSize(1);
                        var10 = var55.getPrimaryLowerLevelAddress();
                        if (var55 instanceof IESwitch) {
                           if (var19 < 0) {
                              return 60;
                           }

                           if (var19 == var28) {
                              IEJump var26 = this.pC.createJump(-var28);
                              var26.addLowerLevelAddress(var10);
                              this.kS.add(var26);
                           } else {
                              IEJump var56 = this.pC.createJump(var19);
                              var56.addLowerLevelAddress(var10);
                              var16.add(this.kS.size());
                              this.kS.add(var56);
                           }
                        } else if (var55 instanceof IEJump) {
                           IEJump var57 = (IEJump)var25;
                           if (var57.getCondition() == null) {
                              var19 = var57.getBranchAddress();
                           }

                           if (var19 >= 0) {
                              if (var19 == var28) {
                                 IEJump var27 = this.pC.createJump(-var28);
                                 var27.addLowerLevelAddress(var10);
                                 this.kS.add(var27);
                              } else {
                                 IEJump var60 = this.pC.createJump(var19);
                                 var60.addLowerLevelAddress(var10);
                                 var16.add(this.kS.size());
                                 this.kS.add(var60);
                              }
                           } else {
                              if (var54 == var28 && var57.getBranchAddress() == var28) {
                                 throw new RuntimeException();
                              }

                              if (var54 == var28) {
                                 var16.add(this.kS.size());
                                 this.kS.add(var57);
                                 IEJump var61 = this.pC.createJump(var54);
                                 var61.addLowerLevelAddress(var10);
                                 this.kS.add(var61);
                              } else if (var57.getBranchAddress() == var28) {
                                 this.kS.add(var57);
                                 IEJump var62 = this.pC.createJump(var54);
                                 var62.addLowerLevelAddress(var10);
                                 var16.add(this.kS.size());
                                 this.kS.add(var62);
                              } else {
                                 var16.add(this.kS.size());
                                 this.kS.add(var57);
                                 IEJump var63 = this.pC.createJump(var54);
                                 var63.addLowerLevelAddress(var10);
                                 var16.add(this.kS.size());
                                 this.kS.add(var63);
                              }
                           }
                        } else {
                           this.kS.add(var25);
                           if (var54 == var28) {
                              IEJump var58 = this.pC.createJump(-var28);
                              var58.addLowerLevelAddress(var10);
                              this.kS.add(var58);
                           } else {
                              IEJump var59 = this.pC.createJump(var54);
                              var59.addLowerLevelAddress(var10);
                              var16.add(this.kS.size());
                              this.kS.add(var59);
                           }
                        }
                     }
                  }

                  for (int var49 : var16) {
                     IEStatement var50 = (IEStatement)this.kS.get(var49);
                     if (var50 instanceof IESwitch) {
                        return 70;
                     }

                     if (!(var50 instanceof IEJump)) {
                        throw new RuntimeException();
                     }

                     int var51 = var50.asJump().getBranchAddress();
                     var51 = (Integer)var15.get(var51);
                     var50.asJump().setBranchAddress(var51);
                  }

                  var14.setBranchAddress(this.kS.size());
               }

               IEJump var40 = this.pC.createJump(this.kS.size());
               var40.addLowerLevelAddress(var10);
               this.kS.add(var40);
            }

            for (int var41 = var8.size() - 1; var41 < var8.size(); var41++) {
               IEStatement var44 = (IEStatement)var8.get(var41);
               IEStatement var47 = (IEStatement)var44.duplicate();
               var47.setSize(1);
               this.pC(var47);
               this.kS.add(var47);
            }
         }

         for (IEStatement var32 : this.kS) {
            this.A(var32);
         }

         HashMap var31 = new HashMap();

         for (BigInteger var35 : var6.keySet()) {
            IEStatement var37 = (IEStatement)this.kS.get((Integer)var6.get(var35));
            var31.put(var35, var37);
         }

         CFG var34 = this.pC.buildCfg(this.kS, false, false);
         var34.setVariableInformationProvider((aki)this.pC);
         var34.format();
         Object[] var64 = new Object[0];
         int var36 = (Integer)this.wS.get(var28);
         BasicBlock var38 = var34.getBlockAt((long)var36);
         aoh.Av var42 = new aoh.Av(this.pC, var34, var38, var2, var31);
         int var45 = var42.pC();
         return var45 != 0 ? var45 : 0;
      }

      private void pC(IEStatement var1) {
         if (var1 instanceof IEJump) {
            IEJump var2 = var1.asJump();
            var2.setBranchAddress(-var2.getBranchAddress());
         } else if (var1 instanceof IESwitch) {
            IESwitch var5 = var1.asSwitch();
            var5.setDefaultAddress(-var5.getDefaultAddress());

            for (Couple var4 : var5.getCases()) {
               var4.setSecond(-(Integer)var4.getSecond());
            }
         }
      }

      private void A(IEStatement var1) {
         if (var1 instanceof IEJump) {
            IEJump var2 = var1.asJump();
            int var3 = var2.getBranchAddress();
            if (var3 < 0) {
               var2.setBranchAddress((Integer)this.wS.get(-var3));
            }
         } else if (var1 instanceof IESwitch) {
            IESwitch var6 = var1.asSwitch();
            int var7 = var6.getDefaultAddress();
            if (var7 < 0) {
               var6.setDefaultAddress((Integer)this.wS.get(-var7));
            }

            for (Couple var5 : var6.getCases()) {
               var7 = (Integer)var5.getSecond();
               if (var7 < 0) {
                  var5.setSecond((Integer)this.wS.get(-var7));
               }
            }
         }
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();

         for (int var2 = 0; var2 < this.kS.size(); var2++) {
            IEStatement var3 = (IEStatement)this.kS.get(var2);
            Strings.ff(var1, "%04X: %s\n", var2, var3);
         }

         return var1.toString();
      }
   }
}
