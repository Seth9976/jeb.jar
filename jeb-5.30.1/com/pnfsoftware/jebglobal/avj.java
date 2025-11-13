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

public class avj extends AbstractEOptimizer {
   private static final StructuredLogger q = aeg.q(avj.class);
   private static final INode RF = Util.N(O.ADD, Util.N(O.MUL, Util.LV(2), Util.LI(1)), Util.LT(0));
   private static final INode xK = Util.N(O.ADD, Util.LT(0), Util.N(O.SHL, Util.LV(2), Util.LI(1)));
   private static final INode Dw = Util.N(O.GT_U, Util.L(0), Util.LI(1));
   private static final INode Uv = Util.N(O.GE_U, Util.L(0), Util.LI(1));
   private static final INode oW = Util.N(O.LT_U, Util.L(0), Util.LI(1));
   private static final INode gO = Util.N(O.LE_U, Util.L(0), Util.LI(1));
   private INativeContext nf;
   private IVirtualMemory gP;
   private boolean za;

   public avj() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
   }

   public boolean q() {
      return this.za;
   }

   @Override
   protected int perform() {
      this.nf = this.ectx.getNativeContext();
      this.gP = this.nf.getMemory();
      this.za = false;
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         int var4 = this.q(var3);
         var1 += var4;
      }

      return this.postPerform(var1);
   }

   private int q(BasicBlock var1) {
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
      avj.eo var8 = new avj.eo(var1, var2);
      if (!this.q(var8, var4) && !this.RF(var8, var4)) {
         return 0;
      } else {
         boolean var9 = this.q(var8);
         if (!var9 && var8.JY == null && !this.RF(var8, var4)) {
            return 0;
         } else {
            int var11 = 0;
            ArrayList var10;
            if (var9 && var8.JY == null) {
               Assert.a(var8.za == 0L);
               if (var8.oW != 4) {
                  aeb.q(new RuntimeException("Potential well-formed switch not using 4-byte offsets"), this.ectx);
                  return 0;
               }

               int var30 = (int)(var8.gP + 1L);
               var10 = new ArrayList(var30);
               long var34 = var8.Uv;

               for (int var41 = 0; var41 < var30; var41++) {
                  try {
                     var10.add(this.gP.readInt(var34, this.nf.getProcessor().getEndianness()) + var8.Dw & 4294967295L);
                     var34 += var8.oW;
                     var11++;
                  } catch (MemoryException var21) {
                     var8.gP = var8.za + var41 - 1L;
                  }
               }
            } else {
               if (var8.JY.findByType(IEMem.class) == null) {
                  Object[] var10000 = new Object[]{var27};
               }

               if (var9) {
                  int var12 = (int)var8.za;
                  int var13 = (int)var8.gP;
                  var10 = new ArrayList(var13 - var12 + 1);

                  for (int var14 = var12; var14 <= var13; var14++) {
                     try {
                        EState var15 = this.q(var8.gO, var14);
                        var10.add(var8.JY.evaluateAddress(var15));
                        var11++;
                     } catch (Exception var24) {
                        var8.gP = var14 - 1L;
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
                        EState var39 = this.q(var8.gO, var36);
                        Long var16 = var8.JY.evaluateAddress(var39);
                        boolean var17 = false;
                        if (Longs.compareUnsigned(var16, this.nf.getVirtualImageBase()) >= 0
                           && Longs.compareUnsigned(var16, this.nf.getVirtualImageBase() + this.nf.getImageSize()) < 0) {
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
                  if (var11 == 0 && var8.nf != null && var8.nf == var8.gO) {
                     var10.clear();

                     for (int var37 = -1; var37 >= -10; var37--) {
                        try {
                           EState var40 = this.q(var8.gO, var37);
                           Long var43 = var8.JY.evaluateAddress(var40);
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
                     var8.lm = -1;
                     var8.za = var29;
                     var8.gP = var32;
                  }
               }
            }

            if (var11 == 0) {
               return 0;
            } else {
               int var31 = var8.gO.getBitsize();
               IESwitch var35 = this.ectx.createSwitch(var8.gO, var8.lm);
               var35.copyProperties(var3);
               boolean var38 = false;
               if (!var38) {
                  int var44 = (int)var8.za;

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
                  this.q(var2, var10, null);
                  this.za = true;
                  return 0;
               } else {
                  BasicBlock var45 = null;
                  if (var8.lm >= 0) {
                     var45 = this.cfg.getBlockAt((long)var8.lm);
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

                     if (var8.zz != null) {
                        IEJump var52 = var8.zz;
                        var52.setCondition(var8.HF);
                     }

                     this.cfg.invalidateDataFlowAnalysis();
                     return 1;
                  }
               }
            }
         }
      }
   }

   boolean q(avj.eo var1, IEGeneric var2) {
      if (var2 instanceof VQ) {
         var2 = ((VQ)var2).q();
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
            EExpressionMatcher var9 = new EExpressionMatcher(RF);
            EExpressionMatcher var10 = new EExpressionMatcher(xK);
            Long var12;
            Object var13;
            IEVar var14;
            if (var9.isMatch(var8)) {
               Map var11 = var9.getMatchMap();
               var12 = q((IEGeneric)var11.get(0));
               var13 = (IEImm)var11.get(1);
               var14 = (IEVar)var11.get(2);
            } else {
               if (!var10.isMatch(var8)) {
                  return false;
               }

               Map var19 = var10.getMatchMap();
               var12 = q((IEGeneric)var19.get(0));
               var13 = (IEImm)var19.get(1);
               long var15 = var13.getValueAsUnsignedLong();
               var13 = alu.q(BigInteger.valueOf(2L).pow((int)var15).toByteArray(), var13.getBitsize());
               var14 = (IEVar)var19.get(2);
            }

            if (var12 == null) {
               return false;
            } else if (var13.getValueAsLong() != var7) {
               return false;
            } else {
               var1.Dw = var3;
               var1.Uv = var12;
               var1.oW = var7;
               var1.nf = var14;
               var1.gO = var14;
               var1.JY = null;
               return true;
            }
         }
      }
   }

   public static Long q(IEGeneric var0) {
      if (var0 instanceof IEImm) {
         return ((IEImm)var0).getValueAsAddress();
      } else {
         return var0 instanceof IEVar && ((IEVar)var0).isGlobalReference() && ((IEVar)var0).getAddress() != null ? ((IEVar)var0).getAddress() : null;
      }
   }

   boolean RF(avj.eo var1, IEGeneric var2) {
      HashSet var3 = new HashSet();
      AtomicInteger var4 = new AtomicInteger();
      var2.visitDepthPost(new avk(this, var3, var4));
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
            var1.gO = var5;
            var1.JY = var2;
            return true;
         }
      }
   }

   boolean q(avj.eo var1) {
      BasicBlock var2 = var1.q;
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
               } else if (var7 != var1.gO) {
                  var6 = true;
               }
            }

            if (!var6 || --var4 < 0) {
               if (var4 >= 0) {
                  if (!(var5 instanceof IEAssign)) {
                     return false;
                  }

                  if (((IEAssign)var5).getDstOperand() == var1.gO) {
                     var3 = ((IEAssign)var5).getSrcOperand();
                  }
               }
               break;
            }

            var5 = (IEStatement)var2.get(var4);
         }
      }

      List var15 = var2.getInputBlocks();
      if (var15.size() != 1) {
         return false;
      } else {
         BasicBlock var16 = (BasicBlock)var15.get(0);
         IEStatement var17 = (IEStatement)var16.getLast();
         if (!(var17 instanceof IEJump var18)) {
            return false;
         } else if (var18.getCondition() == null) {
            return false;
         } else {
            IEGeneric var8 = var18.getCondition();
            boolean var9 = false;
            boolean var10 = true;
            EExpressionMatcher var11 = new EExpressionMatcher(Dw);
            if (!var11.isMatch(var8)) {
               var11 = new EExpressionMatcher(Uv);
               if (var11.isMatch(var8)) {
                  var9 = true;
               } else {
                  var10 = false;
                  var11 = new EExpressionMatcher(oW);
                  if (!var11.isMatch(var8)) {
                     var11 = new EExpressionMatcher(gO);
                     if (!var11.isMatch(var8)) {
                        return false;
                     }
                  } else {
                     var9 = true;
                  }
               }
            }

            Map var12 = var11.getMatchMap();
            IEGeneric var13 = (IEGeneric)var12.get(0);
            if (!this.q(var1, var13, var3)) {
               return false;
            } else if ((!var10 || var18.getBranchAddress() != var2.getFirstAddress()) && (var10 || var18.getBranchAddress() == var2.getFirstAddress())) {
               IEImm var14 = (IEImm)var12.get(1);
               var1.gP = var1.za + (var14.getValueAsLong() - (var9 ? 1 : 0));
               var1.lm = var10 ? var18.getBranchAddress() : (int)var16.getEndAddress();
               var1.zz = var18;
               var1.HF = var10 ? this.ectx.createImm(0L, 1) : this.ectx.createImm(1L, 1);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   boolean q(avj.eo var1, IEGeneric var2, IEGeneric var3) {
      if (var2 == var1.gO) {
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

               if (var6 == var1.gO) {
                  var1.za = var8;
                  return true;
               }
            }
         }

         IDFA var10 = this.cfg.doDataFlowAnalysis();
         Long var11 = var10.checkSingleDef(var1.RF, var1.gO.getId());
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

   boolean q(AddressableInstruction var1, List var2, Long var3) {
      if (var2.size() == 0) {
         return false;
      } else {
         Long var4 = ((IEStatement)var1.getInstruction()).getPrimaryLowerLevelAddress();
         if (var4 == null) {
            return false;
         } else {
            INativeContinuousItem var5 = this.nf.getNativeItemAt(var4);
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
                  this.nf.recordDynamicBranchTarget(var4, false, new BranchTarget(new CodePointer(var16)));
               }

               return true;
            }
         }
      }
   }

   private EState q(IEVar var1, int var2) {
      EState var3 = this.ectx.buildEmptyState();
      var3.setMemory(this.gP);
      if (var1 != null) {
         var3.setValue(var1, EUtil.imm(var2, var1.getBitsize()));
      }

      return var3;
   }

   static class eo {
      BasicBlock q;
      long RF;
      IEStatement xK;
      long Dw;
      long Uv;
      int oW;
      IEVar gO;
      IEVar nf;
      long gP;
      long za;
      int lm = -1;
      IEJump zz;
      IEGeneric JY;
      IEGeneric HF;

      eo(BasicBlock var1, AddressableInstruction var2) {
         this.q = var1;
         this.xK = (IEStatement)var2.getInstruction();
         this.RF = var2.getOffset();
      }
   }
}
