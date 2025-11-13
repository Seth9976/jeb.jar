package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IdRanges;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EExpressionMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.INode;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.O;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.Util;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class asr extends AbstractEOptimizer {
   private static final StructuredLogger pC = aco.pC(asr.class);
   private static final INode A = Util.N(O.ADD, Util.N(O.MUL, Util.LV(2), Util.LI(1)), Util.LT(0));
   private static final INode kS = Util.N(O.ADD, Util.LT(0), Util.N(O.SHL, Util.LV(2), Util.LI(1)));
   private static final INode wS = Util.N(O.GT_U, Util.L(0), Util.LI(1));
   private static final INode UT = Util.N(O.GE_U, Util.L(0), Util.LI(1));
   private static final INode E = Util.N(O.LT_U, Util.L(0), Util.LI(1));
   private static final INode sY = Util.N(O.LE_U, Util.L(0), Util.LI(1));
   private INativeContext ys;
   private IVirtualMemory ld;
   private boolean gp;

   public asr() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
   }

   public boolean pC() {
      return this.gp;
   }

   @Override
   protected int perform() {
      this.ys = this.ectx.getNativeContext();
      this.ld = this.ys.getMemory();
      this.gp = false;
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         int var4 = this.pC(var3);
         var1 += var4;
      }

      return this.postPerform(var1);
   }

   private int pC(BasicBlock var1) {
      AddressableInstruction var2 = var1.getLast2();
      IEStatement var3 = (IEStatement)var2.getInstruction();
      IEGeneric var4;
      List var5;
      if (var3 instanceof IEAssign var6) {
         IEGeneric var7 = var6.getDstOperand();
         if (!(var7 instanceof IEVar) || ((IEVar)var7).getId() != this.ectx.getProgramCounterId()) {
            return 0;
         }

         if (!var2.getBreakingFlow().isBroken()) {
            return 0;
         }

         var4 = var6.getSrcOperand();
         var5 = (List)var6.getBranchDetails(true)
            .getDynamicTargetCandidates()
            .stream()
            .filter(var0 -> var0.isInternal())
            .map(var0 -> var0.getInternalAddress().getAddress())
            .collect(Collectors.toList());
      } else {
         if (!(var3 instanceof IEJumpFar var25)) {
            return 0;
         }

         var4 = var25.getJumpsite();
         var5 = (List)var25.getPossibleTargets().stream().map(var0 -> var0.getAddress()).collect(Collectors.toList());
      }

      if (var4 instanceof IEVar) {
         if (var1.size() >= 2) {
            IEStatement var26 = (IEStatement)var1.get(var1.size() - 2);
            if (var26 instanceof IEAssign && ((IEAssign)var26).getDstOperand() == var4) {
               var4 = ((IEAssign)var26).getSrcOperand();
            }
         }

         if (var4 instanceof IEVar) {
            return 0;
         }
      }

      int var27 = (int)var2.getOffset();
      Long var28 = var3.getPrimaryLowerLevelAddress();
      asr.Av var8 = new asr.Av(var1, var2);
      if (!this.pC(var8, var4) && !this.A(var8, var4)) {
         return 0;
      } else {
         boolean var9 = this.pC(var8);
         if (!var9 && var8.WR == null && !this.A(var8, var4)) {
            return 0;
         } else {
            int var11 = 0;
            ArrayList var10;
            if (var9 && var8.WR == null) {
               Assert.a(var8.gp == 0L);
               if (var8.E != 4) {
                  acj.pC(new RuntimeException("Potential well-formed switch not using 4-byte offsets"), this.ectx);
                  return 0;
               }

               int var30 = (int)(var8.ld + 1L);
               var10 = new ArrayList(var30);
               long var34 = var8.UT;

               for (int var41 = 0; var41 < var30; var41++) {
                  try {
                     var10.add(this.ld.readInt(var34, this.ys.getProcessor().getEndianness()) + var8.wS & 4294967295L);
                     var34 += var8.E;
                     var11++;
                  } catch (MemoryException var21) {
                     var8.ld = var8.gp + var41 - 1L;
                  }
               }
            } else {
               if (var8.WR.findByType(IEMem.class) == null) {
                  Object[] var10000 = new Object[]{var27};
               }

               if (var9) {
                  int var12 = (int)var8.gp;
                  int var13 = (int)var8.ld;
                  var10 = new ArrayList(var13 - var12 + 1);

                  for (int var14 = var12; var14 <= var13; var14++) {
                     try {
                        EState var15 = this.pC(var8.sY, var14);
                        var10.add(var8.WR.evaluateAddress(var15));
                        var11++;
                     } catch (Exception var24) {
                        var8.ld = var14 - 1L;
                        break;
                     }
                  }
               } else {
                  if (var28 == null) {
                     return 0;
                  }

                  int var29 = 0;
                  int var32 = 15;
                  var10 = new ArrayList();

                  for (int var36 = var29; var36 <= var32; var36++) {
                     try {
                        EState var39 = this.pC(var8.sY, var36);
                        Long var16 = var8.WR.evaluateAddress(var39);
                        boolean var17 = false;
                        if (Longs.compareUnsigned(var16, this.ys.getVirtualImageBase()) >= 0
                           && Longs.compareUnsigned(var16, this.ys.getVirtualImageBase() + this.ys.getImageSize()) < 0) {
                           long var18 = Math.abs(var16 - var28);
                           if (var18 > 1024L && !var5.contains(var16)) {
                              var17 = true;
                           }
                        } else {
                           var17 = true;
                        }

                        if (var17) {
                           var16 = null;
                        } else {
                           var11++;
                        }

                        var10.add(var16);
                     } catch (Exception var23) {
                        break;
                     }
                  }

                  var32 = var10.size() - 1;
                  if (var11 == 0 && var8.ys != null && var8.ys == var8.sY) {
                     var10.clear();

                     for (int var37 = -1; var37 >= -10; var37--) {
                        try {
                           EState var40 = this.pC(var8.sY, var37);
                           Long var43 = var8.WR.evaluateAddress(var40);
                           long var46 = Math.abs(var43 - var28);
                           if (var46 > 1024L && !var5.contains(var43)) {
                              break;
                           }

                           var11++;
                           var10.add(var43);
                        } catch (Exception var22) {
                           break;
                        }
                     }

                     if (var11 > 0) {
                        var29 = -var10.size();
                        var32 = -1;
                     }
                  }

                  if (var11 > 0) {
                     var8.oT = -1;
                     var8.gp = var29;
                     var8.ld = var32;
                  }
               }
            }

            if (var11 == 0) {
               return 0;
            } else {
               int var31 = var8.sY.getBitsize();
               IESwitch var35 = this.ectx.createSwitch(var8.sY, var8.oT);
               var35.copyProperties(var3);
               boolean var38 = false;
               if (!var38) {
                  int var44 = (int)var8.gp;

                  for (Long var49 : var10) {
                     if (var49 != null) {
                        var49 = this.ectx.getGlobalContext().getNativeContext().getProcessor().createEntryPoint(var49).getAddress();
                        Long var42 = this.ectx.convertNativeAddress(var49);
                        if (var42 == null || this.cfg.getBlockAt((long)var42.intValue()) == null) {
                           var38 = true;
                           break;
                        }

                        if (!var38) {
                           var35.addCase(this.ectx.createImm(var44, var31), var42.intValue());
                        }
                     }

                     var44++;
                  }
               }

               if (var38) {
                  this.pC(var2, var10, null);
                  this.gp = true;
                  return 0;
               } else {
                  BasicBlock var45 = null;
                  if (var8.oT >= 0) {
                     var45 = this.cfg.getBlockAt((long)var8.oT);
                     if (var45 == null) {
                        return 0;
                     }
                  }

                  IFlowInformation var48 = var35.getBreakingFlow(var1.getLastAddress());
                  Set var51 = (Set)var48.getTargets().stream().map(var0 -> var0.getAddress()).collect(Collectors.toSet());
                  HashSet var19 = new HashSet(var1.getOutputOffsets());
                  if (var45 != null) {
                     var19.add(var45.getBase());
                  }

                  if (!var51.equals(var19)) {
                     return 0;
                  } else {
                     var1.set(var1.size() - 1, var35);
                     if (var45 != null) {
                        int var20 = var45.getBase() == var1.getEndAddress() ? 0 : -1;
                        this.cfg.deleteEdge(var1, var45);
                        this.cfg.addEdge(var1, var45, var20);
                     }

                     if (var8.fI != null) {
                        IEJump var52 = var8.fI;
                        var52.setCondition(var8.NS);
                     }

                     this.cfg.invalidateDataFlowAnalysis();
                     return 1;
                  }
               }
            }
         }
      }
   }

   boolean pC(asr.Av var1, IEGeneric var2) {
      if (var2 instanceof yP) {
         var2 = ((yP)var2).pC();
      }

      long var3;
      if (var2 instanceof IEMem) {
         var3 = 0L;
      } else {
         if (!((IEGeneric)var2).isOperation(OperationType.ADD)) {
            return false;
         }

         IEGeneric var5 = ((IEGeneric)var2).asOperation().getOperand1();
         IEGeneric var6 = ((IEGeneric)var2).asOperation().getOperand2();
         if (var5 instanceof IEMem) {
            var2 = var5;
         } else {
            if (!(var6 instanceof IEMem)) {
               return false;
            }

            var2 = var6;
         }

         if (var5 instanceof IEImm) {
            var3 = ((IEImm)var5).getValueAsAddress();
         } else {
            if (!(var6 instanceof IEImm)) {
               return false;
            }

            var3 = ((IEImm)var6).getValueAsAddress();
         }
      }

      IEMem var17 = (IEMem)var2;
      int var18 = this.ectx.getGlobalContext().getAddressBitsize();
      if (var17.getBitsize() != var18) {
         return false;
      } else {
         int var7 = var18 / 8;
         IEGeneric var8 = var17.getReference();
         if (!EUtil.isOperation(var8, OperationType.ADD)) {
            return false;
         } else {
            EExpressionMatcher var9 = new EExpressionMatcher(A);
            EExpressionMatcher var10 = new EExpressionMatcher(kS);
            Long var12;
            Object var13;
            IEVar var14;
            if (var9.isMatch(var8)) {
               Map var11 = var9.getMatchMap();
               var12 = pC((IEGeneric)var11.get(0));
               var13 = (IEImm)var11.get(1);
               var14 = (IEVar)var11.get(2);
            } else {
               if (!var10.isMatch(var8)) {
                  return false;
               }

               Map var19 = var10.getMatchMap();
               var12 = pC((IEGeneric)var19.get(0));
               var13 = (IEImm)var19.get(1);
               long var15 = var13.getValueAsUnsignedLong();
               var13 = ajr.pC(BigInteger.valueOf(2L).pow((int)var15).toByteArray(), var13.getBitsize());
               var14 = (IEVar)var19.get(2);
            }

            if (var12 == null) {
               return false;
            } else if (var13.getValueAsLong() != var7) {
               return false;
            } else {
               var1.wS = var3;
               var1.UT = var12;
               var1.E = var7;
               var1.ys = var14;
               var1.sY = var14;
               var1.WR = null;
               return true;
            }
         }
      }
   }

   public static Long pC(IEGeneric var0) {
      if (var0 instanceof IEImm) {
         return ((IEImm)var0).getValueAsAddress();
      } else {
         return var0 instanceof IEVar && ((IEVar)var0).isGlobalReference() && ((IEVar)var0).getAddress() != null ? ((IEVar)var0).getAddress() : null;
      }
   }

   boolean A(asr.Av var1, IEGeneric var2) {
      HashSet var3 = new HashSet();
      AtomicInteger var4 = new AtomicInteger();
      var2.visitDepthPost(new ass(this, var3, var4));
      if (var4.get() <= 0) {
         return false;
      } else {
         IEVar var5 = null;
         IdRanges var6 = var2.getUsed();

         for (int var8 : var6.getVarIds()) {
            IEVar var9 = this.ectx.getVariableById(var8);
            if (!var3.contains(var9) && !var9.isGlobalReference()) {
               if (var5 != null && var5 != var9) {
                  return false;
               }

               var5 = var9;
            }
         }

         if (var5 == null) {
            return false;
         } else {
            var1.sY = var5;
            var1.WR = var2;
            return true;
         }
      }
   }

   boolean pC(asr.Av var1) {
      BasicBlock var2 = var1.pC;
      IEGeneric var3 = null;
      if (var2.size() >= 2) {
         if (var2.size() >= 4) {
            return false;
         }

         int var4 = var2.size() - 2;
         IEStatement var5 = (IEStatement)var2.get(var4);

         while (true) {
            boolean var6 = false;
            if (var5 instanceof IENop) {
               var6 = true;
            } else if (var5 instanceof IEAssign) {
               IEGeneric var7 = ((IEAssign)var5).getDstOperand();
               if (var7.getBitsize() == 1) {
                  var6 = true;
               } else if (var7 != var1.sY) {
                  var6 = true;
               }
            }

            if (!var6 || --var4 < 0) {
               if (var4 >= 0) {
                  if (!(var5 instanceof IEAssign)) {
                     return false;
                  }

                  if (((IEAssign)var5).getDstOperand() == var1.sY) {
                     var3 = ((IEAssign)var5).getSrcOperand();
                  }
               }
               break;
            }

            var5 = (IEStatement)var2.get(var4);
         }
      }

      if (var2.insize() != 1) {
         return false;
      } else {
         BasicBlock var14 = var2.getInputBlock(0);
         IEStatement var15 = (IEStatement)var14.getLast();
         if (!(var15 instanceof IEJump var16)) {
            return false;
         } else if (var16.getCondition() == null) {
            return false;
         } else {
            IEGeneric var17 = var16.getCondition();
            boolean var8 = false;
            boolean var9 = true;
            EExpressionMatcher var10 = new EExpressionMatcher(wS);
            if (!var10.isMatch(var17)) {
               var10 = new EExpressionMatcher(UT);
               if (var10.isMatch(var17)) {
                  var8 = true;
               } else {
                  var9 = false;
                  var10 = new EExpressionMatcher(E);
                  if (!var10.isMatch(var17)) {
                     var10 = new EExpressionMatcher(sY);
                     if (!var10.isMatch(var17)) {
                        return false;
                     }
                  } else {
                     var8 = true;
                  }
               }
            }

            Map var11 = var10.getMatchMap();
            IEGeneric var12 = (IEGeneric)var11.get(0);
            if (!this.pC(var1, var12, var3)) {
               return false;
            } else if ((!var9 || var16.getBranchAddress() != var2.getFirstAddress()) && (var9 || var16.getBranchAddress() == var2.getFirstAddress())) {
               IEImm var13 = (IEImm)var11.get(1);
               var1.ld = var1.gp + (var13.getValueAsLong() - (var8 ? 1 : 0));
               var1.oT = var9 ? var16.getBranchAddress() : (int)var14.getEndAddress();
               var1.fI = var16;
               var1.NS = var9 ? this.ectx.createImm(0L, 1) : this.ectx.createImm(1L, 1);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   boolean pC(asr.Av var1, IEGeneric var2, IEGeneric var3) {
      if (var2 == var1.sY) {
         return true;
      } else if (var2.equals(var3)) {
         return true;
      } else {
         if (var2 instanceof IEOperation var4) {
            OperationType var5 = var4.getOperationType();
            if ((var5 == OperationType.ADD || var5 == OperationType.SUB) && var4.getOperand1() instanceof IEVar && var4.getOperand2() instanceof IEImm) {
               IEVar var6 = (IEVar)var4.getOperand1();
               IEImm var7 = (IEImm)var4.getOperand2();
               long var8 = var7.getValueAsUnsignedLong();
               if (var5 == OperationType.ADD) {
                  var8 = -var8;
               }

               if (var6 == var1.sY) {
                  var1.gp = var8;
                  return true;
               }
            }
         }

         IDFA var10 = this.cfg.doDataFlowAnalysis();
         Long var11 = var10.checkSingleDef(var1.A, var1.sY.getId());
         if (var11 == null) {
            return false;
         } else {
            IEStatement var12 = (IEStatement)this.cfg.getInstruction(var11);
            IEGeneric var13 = EUtil.getAssignmentSource(var12);
            if (var13 == null) {
               return false;
            } else if (var13.equalsEx(var2, false)) {
               return true;
            } else {
               if (var13.getBitsize() > var2.getBitsize()) {
                  IEGeneric var14 = var13.slice(0, var2.getBitsize());
                  if (var14.equalsEx(var2, false)) {
                     return true;
                  }
               }

               return false;
            }
         }
      }
   }

   boolean pC(AddressableInstruction var1, List var2, Long var3) {
      if (var2.size() == 0) {
         return false;
      } else {
         Long var4 = ((IEStatement)var1.getInstruction()).getPrimaryLowerLevelAddress();
         if (var4 == null) {
            return false;
         } else {
            INativeContinuousItem var5 = this.ys.getNativeItemAt(var4);
            if (!(var5 instanceof INativeInstructionItem)) {
               return false;
            } else {
               ArrayList var6 = new ArrayList();
               var2.forEach(var1x -> {
                  if (var1x != null) {
                     var6.add(var1x);
                  }
               });
               ArrayList var7 = new ArrayList(new TreeSet(var6));
               if (var3 != null) {
                  var7.add(var3);
               }

               int var8 = var7.size();
               IInstruction var9 = ((INativeInstructionItem)var5).getInstruction();
               long var10 = var4 + var9.getSize();
               int var12 = 0;

               for (long var14 : var7) {
                  if (var14 >= var10) {
                     break;
                  }

                  var12++;
               }

               FlowInformation var17 = new FlowInformation();
               int var18 = var12;

               do {
                  long var15 = (Long)var7.get(var18);
                  var17.addTarget(new CodePointer(var15));
                  var18 = (var18 + 1) % var8;
               } while (var18 != var12);

               for (ICodePointer var16 : var17.getTargets()) {
                  this.ys.recordDynamicBranchTarget(var4, false, new BranchTarget(new CodePointer(var16)));
               }

               return true;
            }
         }
      }
   }

   private EState pC(IEVar var1, int var2) {
      EState var3 = this.ectx.buildEmptyState();
      var3.setMemory(this.ld);
      if (var1 != null) {
         var3.setValue(var1, EUtil.imm(var2, var1.getBitsize()));
      }

      return var3;
   }

   static class Av {
      BasicBlock pC;
      long A;
      IEStatement kS;
      long wS;
      long UT;
      int E;
      IEVar sY;
      IEVar ys;
      long ld;
      long gp;
      int oT = -1;
      IEJump fI;
      IEGeneric WR;
      IEGeneric NS;

      Av(BasicBlock var1, AddressableInstruction var2) {
         this.pC = var1;
         this.kS = (IEStatement)var2.getInstruction();
         this.A = var2.getOffset();
      }
   }
}
