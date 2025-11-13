package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.List;

public class aoq {
   private static final StructuredLogger oW = aeg.q(aoq.class);
   private IERoutineContext gO;
   private CFG nf;
   private IEGlobalContext gP;
   private IEConverter za;
   private INativeContext lm;
   private IWildcardTypeManager zz;
   Long q;
   INativeMethodItem RF;
   IWildcardPrototype xK;
   List Dw;
   List Uv;

   public aoq(IERoutineContext var1) {
      this.gO = var1;
      this.nf = var1.getCfg();
      this.gP = var1.getGlobalContext();
      this.za = (AbstractConverter)var1.getConverter();
      this.lm = this.za.getNativeContext();
      this.zz = this.za.getGlobalContext().getWildcardTypeManager();
   }

   public IWildcardPrototype q() {
      return this.RF != null && this.RF.getPrototype() != null ? this.zz.createPrototype(this.RF.getPrototype()) : this.xK;
   }

   public boolean q(BasicBlock var1, boolean var2) {
      if (!EUtil.isPCAssign((IEStatement)var1.getLast())) {
         return false;
      } else {
         IEAssign var3 = (IEAssign)var1.getLast();
         if (var2 && !var3.isRoutineCall()) {
            return false;
         } else {
            IEGeneric var4 = var3.getRightOperand();
            if (var4 instanceof IEImm) {
               this.q = ((IEImm)var4).getValueAsAddress();
               this.RF = this.lm.getRoutine(this.q);
               return true;
            } else {
               IEBranchDetails var5 = var3.getBranchDetails();
               if (var5 != null) {
                  List var6 = var5.getDynamicTargetCandidates();
                  if (var6 != null) {
                     this.Uv = new ArrayList();
                     this.Dw = new ArrayList();

                     for (IBranchTarget var8 : var6) {
                        INativeMethodItem var9;
                        if (var8.isInternal()) {
                           var9 = this.lm.getRoutine(var8.getInternalAddress().getAddress());
                        } else {
                           var9 = var8.getRoutine();
                        }

                        if (var9 != null) {
                           IWildcardPrototype var10 = this.gP.getCandidatePrototype(var9);
                           if (var10 != null) {
                              if (this.xK == null) {
                                 this.xK = var10;
                              } else if (!this.xK.equals(var10)) {
                                 oW.warn("Callsite has multiple candidate targets with inconsistent prototype");
                              }
                           }

                           this.Dw.add(var9);
                           this.Uv.add(this.gO.createSymbolForRoutine(var9));
                        }
                     }
                  }
               }

               if (this.Dw == null || this.Dw.isEmpty()) {
                  if (var5 != null && var5.getNativePrototypeHint() != null) {
                     this.xK = this.zz.createPrototype(var5.getNativePrototypeHint());
                  }

                  if (var4 instanceof IEMem) {
                     this.RF = this.q((IEMem)var4, var3);
                  } else if (var4 instanceof IEVar) {
                     IDFA var11 = this.nf.doDataFlowAnalysis();
                     IEVar var12 = (IEVar)var4;
                     Long var13 = var11.checkSingleDef(var1.getLastAddress(), var12.getId());
                     if (var13 != null && var13 != -1L) {
                        IEStatement var14 = (IEStatement)this.nf.getInstruction(var13);
                        if (var14 instanceof IEAssign && ((IEAssign)var14).getLeftOperand() == var12) {
                           IEGeneric var15 = ((IEAssign)var14).getRightOperand();
                           if (var15 instanceof IEMem) {
                              this.RF = this.q((IEMem)var15, var14);
                           } else if (var15 instanceof IEImm) {
                              this.q = ((IEImm)var15).getValueAsAddress();
                              this.RF = this.lm.getRoutine(this.q);
                           }
                        }
                     }
                  }
               }

               return true;
            }
         }
      }
   }

   private INativeMethodItem q(IEMem var1, IEStatement var2) {
      IEGeneric var3 = var1.getReference();
      long var4;
      if (var3 instanceof IEImm) {
         var4 = ((IEImm)var3).getValueAsAddress();
      } else {
         Couple var6;
         if ((var6 = this.q(var3)) == null) {
            return null;
         }

         IEVar var7 = (IEVar)var6.getFirst();
         IEImm var8 = (IEImm)var6.getSecond();
         IEImm var9 = this.q(var2, var7);
         if (var9 == null) {
            return null;
         }

         var4 = var9._add(var8).getValueAsAddress();
      }

      try {
         aoo var16 = new aoo(this.gO);
         var16.q(var16.RF() | 8);
         IEImm var18 = var16.q(var4, var1.getBitsize(), null);
         if (var18 != null) {
            INativeMethodItem var20 = this.lm.getRoutine(var18.getValueAsAddress());
            if (var20 != null) {
               return var20;
            }
         }
      } catch (Exception var15) {
      }

      EState var17 = this.gO.getGlobalContext().buildState();
      var17.setMemory(this.lm.getMemory());

      try {
         IEImm var19 = var3.evaluate(var17);
         if (var19 != null) {
            long var21 = var19.getValueAsAddress();
            if (this.lm.getNativeItemAt(var21) instanceof axu var12) {
               INativeMethodItem var13 = var12.cC();
               if (var13 != null) {
                  return var13;
               }
            }
         }
      } catch (Exception var14) {
      }

      return null;
   }

   Couple q(IEGeneric var1) {
      if (var1 instanceof IEVar) {
         return new Couple((IEVar)var1, EUtil.zero(var1.getBitsize()));
      } else {
         if (var1 instanceof IEOperation) {
            OperationType var2 = ((IEOperation)var1).getOperationType();
            if (var2 == OperationType.ADD || var2 == OperationType.SUB) {
               IEGeneric var3 = ((IEOperation)var1).getOperand1();
               IEGeneric var4 = ((IEOperation)var1).getOperand2();
               if (var3 instanceof IEVar && var4 instanceof IEImm) {
                  IEVar var5 = (IEVar)var3;
                  IEImm var6 = (IEImm)var4;
                  if (var2 == OperationType.SUB) {
                     var6 = var6._neg();
                  }

                  return new Couple(var5, var6);
               }
            }
         }

         return null;
      }
   }

   private IEImm q(IEStatement var1, IEVar var2) {
      Couple var3 = this.nf.getInstructionLocation(var1);
      return this.q((BasicBlock)var3.getFirst(), (Integer)var3.getSecond(), var2);
   }

   private IEImm q(BasicBlock var1, int var2, IEVar var3) {
      IDFA var4 = this.nf.doDataFlowAnalysis();
      Long var5 = var4.checkSingleDef(var1.getAddressOfInstruction(var2), var3.getId());
      if (var5 != null && var5 != -1L) {
         IEStatement var6 = (IEStatement)this.nf.getInstruction(var5);
         if (var6 instanceof IEAssign && ((IEAssign)var6).getLeftOperand() == var3) {
            IEGeneric var7 = ((IEAssign)var6).getRightOperand();
            if (var7 instanceof IEImm) {
               return (IEImm)var7;
            }
         }
      }

      return null;
   }
}
