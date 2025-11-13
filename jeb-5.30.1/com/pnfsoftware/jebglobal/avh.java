package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class avh extends AbstractEOptimizer {
   private static final ILogger RF = GlobalLog.getLogger(avh.class);
   public static final int q = 5;
   private int xK = 5;
   private IEVar Dw;
   private avh.nI Uv;
   private TreeMap oW = new TreeMap();
   private Set gO = new HashSet();
   private Deque nf = new ArrayDeque();
   private Map gP = new HashMap();
   private Integer za;

   public avh() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
   }

   public void q(int var1) {
      this.xK = var1;
   }

   public void q() {
      if (this.Dw != null) {
         this.Dw = null;
         this.oW.clear();
         this.Uv = null;
         this.gO.clear();
         this.nf.clear();
         this.gP.clear();
         this.za = null;
      }
   }

   @Override
   public int perform() {
      int var1 = 0;
      HashSet var2 = new HashSet();

      label113:
      for (int var3 = 0; var3 < this.cfg.size(); var3++) {
         BasicBlock var4 = this.cfg.get(var3);
         if (!var2.contains((int)var4.getBase())) {
            IEStatement var5 = (IEStatement)var4.get(var4.size() - 1);
            if (EUtil.isConditionalJump(var5)) {
               int var6 = (int)var4.getLastAddress();
               this.q();
               if (this.q(var6, (IEJump)var5, false, false)) {
                  if (var4.insize() == 1) {
                     BasicBlock var7 = var4.getInputBlock(0);
                     if (var7.size() == 1 && this.q((int)var7.getBase(), 1, true, false)) {
                        continue;
                     }
                  }

                  while (!this.nf.isEmpty()) {
                     int var12 = (Integer)this.nf.pop();
                     this.q(var12, 1, false, true);
                  }

                  var2.addAll(this.oW.keySet());
                  if (this.oW.size() >= this.xK) {
                     HashMap var13 = new HashMap();

                     for (avh.nI var9 : this.oW.values()) {
                        if (var13.containsKey(var9.xK)) {
                           if (this.oW.get(var9.xK) != null) {
                              continue label113;
                           }
                        } else {
                           var13.put(var9.xK, var9.q);
                        }

                        if (var13.containsKey(var9.Dw)) {
                           if (this.oW.get(var9.Dw) != null) {
                              continue label113;
                           }
                        } else {
                           var13.put(var9.Dw, var9.q);
                        }
                     }

                     if (this.RF()) {
                        TreeSet var14 = new TreeSet(this.gP.values());
                        if (this.za != null) {
                           var14.add(this.za);
                        }

                        for (int var10 : var14) {
                           BasicBlock var11 = this.cfg.getBlockAt((long)var10);
                           if (var11 == null) {
                              continue label113;
                           }
                        }

                        IESwitch var16 = this.ectx.createSwitch(this.Dw, this.za == null ? -1 : this.za);

                        for (Entry var19 : this.gP.entrySet()) {
                           var16.addCase(((IEImm)var19.getKey()).duplicate(), (Integer)var19.getValue());
                        }

                        var16.copyProperties(var5);
                        var4.set(var4.size() - 1, var16);
                        this.cfg.deleteOutEdges(var4);

                        for (int var20 : var14) {
                           this.cfg.addEdge(var4, this.cfg.getBlockAt((long)var20));
                        }

                        amw.q(this.cfg);
                        var1++;
                     }
                  }
               }
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   boolean q(int var1, int var2, boolean var3, boolean var4) {
      BasicBlock var5 = this.cfg.getBlockAt((long)var1);
      if (var5 == null) {
         return false;
      } else if (var2 > 0 && var5.size() != var2) {
         return false;
      } else {
         AddressableInstruction var6 = var5.getLast2();
         return !(var6.getInstruction() instanceof IEJump) ? false : this.q((int)var6.getOffset(), (IEJump)var6.getInstruction(), var3, var4);
      }
   }

   boolean q(int var1, IEJump var2, boolean var3, boolean var4) {
      IEGeneric var5 = var2.getCondition();
      int var6 = var2.getBranchAddress();
      if (var5 == null) {
         if (!var4) {
            return false;
         } else {
            if (!var3) {
               for (avh.nI var13 : this.oW.values()) {
                  if (var13.xK == var1) {
                     var13.xK = var6;
                  }

                  if (var13.Dw == var1) {
                     var13.Dw = var6;
                  }
               }

               if (this.gO.add(var6)) {
                  this.nf.add(var6);
               }
            }

            return true;
         }
      } else if (!(var5 instanceof IEOperation)) {
         return false;
      } else {
         OperationType var7 = ((IEOperation)var5).getOperationType();
         switch (var7) {
            case LOG_EQ:
            case LOG_NEQ:
            case GE_S:
            case GT_S:
            case LE_S:
            case LT_S:
            case GE_U:
            case GT_U:
            case LE_U:
            case LT_U:
               IEGeneric var8 = ((IEOperation)var5).getOperand1();
               if (!(var8 instanceof IEVar)) {
                  return false;
               } else if (!(((IEOperation)var5).getOperand2() instanceof IEImm var10)) {
                  return false;
               } else {
                  if (this.Dw != null && !this.Dw.equals(var8)) {
                     return false;
                  }

                  if (!var3) {
                     avh.nI var11 = new avh.nI(var1, new avh.eo(var7, var10), var6, var1 + var2.getSize());
                     this.oW.put(var1, var11);
                     if (this.gO.add(var11.xK)) {
                        this.nf.add(var11.xK);
                     }

                     if (this.gO.add(var11.Dw)) {
                        this.nf.add(var11.Dw);
                     }

                     if (this.Dw == null) {
                        this.Dw = (IEVar)var8;
                        this.Uv = var11;
                     }
                  }

                  return true;
               }
            default:
               return false;
         }
      }
   }

   boolean RF() {
      return this.q(this.Uv.q, new ArrayList());
   }

   boolean q(int var1, List var2) {
      avh.nI var3 = (avh.nI)this.oW.get(var1);
      if (var3 == null) {
         avh.CU var4 = new avh.CU(var2);
         if (!var4.q()) {
            return false;
         } else {
            if (var4.RF == null) {
               if (this.za != null && !this.za.equals(var1)) {
                  IEStatement var6 = (IEStatement)this.cfg.getInstruction(this.za.intValue());
                  if (!var6.isReturn() || !var6.equalsEx(this.cfg.getInstruction(var1), true, true, false)) {
                     return false;
                  }
               } else {
                  this.za = var1;
               }
            } else {
               Integer var5 = (Integer)this.gP.get(var4.RF);
               if (var5 != null && !var5.equals(var1)) {
                  return false;
               }

               this.gP.put(var4.RF, var1);
            }

            return true;
         }
      } else {
         var2.add(var3.RF);
         if (!this.q(var3.xK, var2)) {
            return false;
         } else {
            var2.remove(var2.size() - 1);
            var2.add(var3.RF.q());
            if (!this.q(var3.Dw, var2)) {
               return false;
            } else {
               var2.remove(var2.size() - 1);
               return true;
            }
         }
      }
   }

   class CU {
      List q;
      IEImm RF;

      CU(List var2) {
         this.q = var2;
      }

      boolean q() {
         IEOperation var1 = null;

         for (avh.eo var3 : this.q) {
            IEOperation var4 = avh.this.ectx.createOperation(var3.q, avh.this.Dw, var3.RF);
            if (var1 == null) {
               var1 = var4;
            } else {
               var1 = avh.this.ectx.createOperation(OperationType.LOG_AND, var1, var4);
            }
         }

         aov var5 = new aov(var1, avh.this.Dw);
         if (!var5.xK()) {
            return false;
         } else {
            apw var6 = var5.Dw();
            if (var6.gP()) {
               return false;
            } else {
               if (var6.Uv() == 1) {
                  apw.eo var7 = (apw.eo)var6.Dw().get(0);
                  if (var7.Dw().equals(BigInteger.ONE)) {
                     this.RF = EUtil.imm(var7.RF(), avh.this.Dw.getBitsize());
                  }
               }

               return true;
            }
         }
      }
   }

   private static class eo {
      OperationType q;
      IEImm RF;

      public eo(OperationType var1, IEImm var2) {
         this.q = var1;
         this.RF = var2;
      }

      public avh.eo q() {
         return new avh.eo(EUtil.getReverseOperation(this.q), this.RF);
      }

      @Override
      public String toString() {
         return Strings.ff("%s%s", this.q, this.RF);
      }
   }

   private static class nI {
      int q;
      avh.eo RF;
      int xK;
      int Dw;

      public nI(int var1, avh.eo var2, int var3, int var4) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X: %s =>0x%X (else:0x%X)", this.q, this.RF, this.xK, this.Dw);
      }
   }
}
