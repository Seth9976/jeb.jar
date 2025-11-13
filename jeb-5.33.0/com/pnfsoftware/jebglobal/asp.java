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

public class asp extends AbstractEOptimizer {
   private static final ILogger pC = GlobalLog.getLogger(asp.class);
   private int A = 5;
   private IEVar kS;
   private asp.K wS;
   private TreeMap UT = new TreeMap();
   private Set E = new HashSet();
   private Deque sY = new ArrayDeque();
   private Map ys = new HashMap();
   private Integer ld;

   public asp() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
   }

   public void pC() {
      if (this.kS != null) {
         this.kS = null;
         this.UT.clear();
         this.wS = null;
         this.E.clear();
         this.sY.clear();
         this.ys.clear();
         this.ld = null;
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
               this.pC();
               if (this.pC(var6, (IEJump)var5, false, false)) {
                  if (var4.insize() == 1) {
                     BasicBlock var7 = var4.getInputBlock(0);
                     if (var7.size() == 1 && this.pC((int)var7.getBase(), 1, true, false)) {
                        continue;
                     }
                  }

                  while (!this.sY.isEmpty()) {
                     int var12 = (Integer)this.sY.pop();
                     this.pC(var12, 1, false, true);
                  }

                  var2.addAll(this.UT.keySet());
                  if (this.UT.size() >= this.A) {
                     HashMap var13 = new HashMap();

                     for (asp.K var9 : this.UT.values()) {
                        if (var13.containsKey(var9.kS)) {
                           if (this.UT.get(var9.kS) != null) {
                              continue label113;
                           }
                        } else {
                           var13.put(var9.kS, var9.pC);
                        }

                        if (var13.containsKey(var9.wS)) {
                           if (this.UT.get(var9.wS) != null) {
                              continue label113;
                           }
                        } else {
                           var13.put(var9.wS, var9.pC);
                        }
                     }

                     if (this.A()) {
                        TreeSet var14 = new TreeSet(this.ys.values());
                        if (this.ld != null) {
                           var14.add(this.ld);
                        }

                        for (int var10 : var14) {
                           BasicBlock var11 = this.cfg.getBlockAt((long)var10);
                           if (var11 == null) {
                              continue label113;
                           }
                        }

                        IESwitch var16 = this.ectx.createSwitch(this.kS, this.ld == null ? -1 : this.ld);

                        for (Entry var19 : this.ys.entrySet()) {
                           var16.addCase(((IEImm)var19.getKey()).duplicate(), (Integer)var19.getValue());
                        }

                        var16.copyProperties(var5);
                        var4.set(var4.size() - 1, var16);
                        this.cfg.deleteOutEdges(var4);

                        for (int var20 : var14) {
                           this.cfg.addEdge(var4, this.cfg.getBlockAt((long)var20));
                        }

                        akt.pC(this.cfg);
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

   boolean pC(int var1, int var2, boolean var3, boolean var4) {
      BasicBlock var5 = this.cfg.getBlockAt((long)var1);
      if (var5 == null) {
         return false;
      } else if (var2 > 0 && var5.size() != var2) {
         return false;
      } else {
         AddressableInstruction var6 = var5.getLast2();
         return !(var6.getInstruction() instanceof IEJump) ? false : this.pC((int)var6.getOffset(), (IEJump)var6.getInstruction(), var3, var4);
      }
   }

   boolean pC(int var1, IEJump var2, boolean var3, boolean var4) {
      IEGeneric var5 = var2.getCondition();
      int var6 = var2.getBranchAddress();
      if (var5 == null) {
         if (!var4) {
            return false;
         } else {
            if (!var3) {
               for (asp.K var13 : this.UT.values()) {
                  if (var13.kS == var1) {
                     var13.kS = var6;
                  }

                  if (var13.wS == var1) {
                     var13.wS = var6;
                  }
               }

               if (this.E.add(var6)) {
                  this.sY.add(var6);
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
                  if (this.kS != null && !this.kS.equals(var8)) {
                     return false;
                  }

                  if (!var3) {
                     asp.K var11 = new asp.K(var1, new asp.Av(var7, var10), var6, var1 + var2.getSize());
                     this.UT.put(var1, var11);
                     if (this.E.add(var11.kS)) {
                        this.sY.add(var11.kS);
                     }

                     if (this.E.add(var11.wS)) {
                        this.sY.add(var11.wS);
                     }

                     if (this.kS == null) {
                        this.kS = (IEVar)var8;
                        this.wS = var11;
                     }
                  }

                  return true;
               }
            default:
               return false;
         }
      }
   }

   boolean A() {
      return this.pC(this.wS.pC, new ArrayList());
   }

   boolean pC(int var1, List var2) {
      asp.K var3 = (asp.K)this.UT.get(var1);
      if (var3 == null) {
         asp.Sv var4 = new asp.Sv(var2);
         if (!var4.pC()) {
            return false;
         } else {
            if (var4.A == null) {
               if (this.ld != null && !this.ld.equals(var1)) {
                  IEStatement var6 = (IEStatement)this.cfg.getInstruction(this.ld.intValue());
                  if (!var6.isReturn() || !var6.equalsEx(this.cfg.getInstruction(var1), true, true, false)) {
                     return false;
                  }
               } else {
                  this.ld = var1;
               }
            } else {
               Integer var5 = (Integer)this.ys.get(var4.A);
               if (var5 != null && !var5.equals(var1)) {
                  return false;
               }

               this.ys.put(var4.A, var1);
            }

            return true;
         }
      } else {
         var2.add(var3.A);
         if (!this.pC(var3.kS, var2)) {
            return false;
         } else {
            var2.remove(var2.size() - 1);
            var2.add(var3.A.pC());
            if (!this.pC(var3.wS, var2)) {
               return false;
            } else {
               var2.remove(var2.size() - 1);
               return true;
            }
         }
      }
   }

   private static class Av {
      OperationType pC;
      IEImm A;

      public Av(OperationType var1, IEImm var2) {
         this.pC = var1;
         this.A = var2;
      }

      public asp.Av pC() {
         return new asp.Av(EUtil.getReverseOperation(this.pC), this.A);
      }

      @Override
      public String toString() {
         return Strings.ff("%s%s", this.pC, this.A);
      }
   }

   private static class K {
      int pC;
      asp.Av A;
      int kS;
      int wS;

      public K(int var1, asp.Av var2, int var3, int var4) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X: %s =>0x%X (else:0x%X)", this.pC, this.A, this.kS, this.wS);
      }
   }

   class Sv {
      List pC;
      IEImm A;

      Sv(List var2) {
         this.pC = var2;
      }

      boolean pC() {
         IEOperation var1 = null;

         for (asp.Av var3 : this.pC) {
            IEOperation var4 = asp.this.ectx.createOperation(var3.pC, asp.this.kS, var3.A);
            if (var1 == null) {
               var1 = var4;
            } else {
               var1 = asp.this.ectx.createOperation(OperationType.LOG_AND, var1, var4);
            }
         }

         amo var5 = new amo(var1, asp.this.kS);
         if (!var5.pC()) {
            return false;
         } else {
            anm var6 = var5.A();
            if (var6.ys()) {
               return false;
            } else {
               if (var6.wS() == 1) {
                  anm.Av var7 = (anm.Av)var6.kS().get(0);
                  if (var7.wS().equals(BigInteger.ONE)) {
                     this.A = EUtil.imm(var7.A(), asp.this.kS.getBitsize());
                  }
               }

               return true;
            }
         }
      }
   }
}
