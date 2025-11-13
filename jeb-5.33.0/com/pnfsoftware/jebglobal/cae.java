package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
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

public class cae extends AbstractDOptimizer {
   private static final ILogger pC = GlobalLog.getLogger(cae.class, Integer.MAX_VALUE);
   private int A = 5;
   private IDVar kS;
   private cae.K wS;
   private TreeMap UT = new TreeMap();
   private Set E = new HashSet();
   private Deque sY = new ArrayDeque();
   private Map ys = new HashMap();
   private Integer ld;

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

      label160:
      for (int var3 = 0; var3 < this.cfg.size(); var3++) {
         BasicBlock var4 = this.cfg.get(var3);
         if (!var2.contains((int)var4.getBase())) {
            IDInstruction var5 = (IDInstruction)var4.getLast();
            if (var5.isJcond()) {
               this.pC();
               if (this.pC(var5, false, false)) {
                  if (var4.insize() == 1) {
                     BasicBlock var6 = var4.getInputBlock(0);
                     if (var6.size() == 1 && this.pC((int)var6.getBase(), 1, true, false)) {
                        continue;
                     }
                  }

                  while (!this.sY.isEmpty()) {
                     int var19 = (Integer)this.sY.pop();
                     this.pC(var19, 1, false, true);
                  }

                  var2.addAll(this.UT.keySet());
                  if (this.UT.size() >= this.A) {
                     HashMap var20 = new HashMap();

                     for (cae.K var8 : this.UT.values()) {
                        if (var20.containsKey(var8.kS)) {
                           if (this.UT.get(var8.kS) != null) {
                              continue label160;
                           }
                        } else {
                           var20.put(var8.kS, var8.pC);
                        }

                        if (var20.containsKey(var8.wS)) {
                           if (this.UT.get(var8.wS) != null) {
                              continue label160;
                           }
                        } else {
                           var20.put(var8.wS, var8.pC);
                        }
                     }

                     if (this.A()) {
                        TreeSet var21 = new TreeSet(this.ys.values());
                        if (this.ld != null) {
                           BasicBlock var22 = this.cfg.getBlockAt((long)this.ld.intValue());
                           if (var22 == null) {
                              continue;
                           }
                        }

                        for (int var9 : var21) {
                           BasicBlock var10 = this.cfg.getBlockAt((long)var9);
                           if (var10 == null) {
                              continue label160;
                           }
                        }

                        BasicBlock var24 = null;
                        if (((IDInstruction)var4.getLast()).isJcond()) {
                           BasicBlock var25 = this.cfg.getBlockAt(var4.getEndAddress());
                           if (var25.size() == 1 && var25.insize() == 1) {
                              IDInstruction var28 = (IDInstruction)var25.get(0);
                              if (var28.isJumpOrJcond() && this.ld.intValue() != var25.getBase()) {
                                 var24 = var25;
                              }
                           }
                        }

                        if (var24 != null) {
                           IDSwitchData var26 = this.g.createSwitchData();

                           for (Entry var11 : this.ys.entrySet()) {
                              var26.addCase((int)((IDImm)var11.getKey()).getValueAsLong(), this.g.createTarget((Integer)var11.getValue()), false);
                           }

                           IDInstruction var30 = this.ctx.createSwitch(this.kS, var26);
                           var30.copyBaseFields(var5);
                           var4.set(var4.size() - 1, var30);
                           IDInstruction var31 = (IDInstruction)var24.get(0);
                           IDInstruction var12;
                           if (this.ld == null) {
                              var12 = this.ctx.createThrow(this.ctx.createNull());
                           } else {
                              var12 = this.ctx.createJump(this.ld);
                           }

                           var12.copyBaseFields(var31);
                           var24.set(0, var12);
                           this.cfg.deleteOutEdges(var4);
                           this.cfg.deleteOutEdges(var24);
                           this.cfg.addEdge(var4, var24);

                           for (int var14 : var21) {
                              this.cfg.addEdge(var4, this.cfg.getBlockAt((long)var14));
                           }

                           if (this.ld != null) {
                              this.cfg.addEdge(var24, this.cfg.getBlockAt((long)this.ld.intValue()));
                           }
                        } else {
                           long var27 = this.cfg.getEndAddress();
                           BasicBlock var32 = new BasicBlock();
                           IDSwitchData var33 = this.g.createSwitchData();

                           for (Entry var36 : this.ys.entrySet()) {
                              var33.addCase((int)((IDImm)var36.getKey()).getValueAsLong(), this.g.createTarget((Integer)var36.getValue()), false);
                           }

                           IDInstruction var35 = this.ctx.createSwitch(this.kS, var33);
                           var35.setOffset(var27);
                           var32.add(var35);
                           this.cfg.addBlock(var32);
                           BasicBlock var37 = new BasicBlock();
                           IDInstruction var15;
                           if (this.ld == null) {
                              var15 = this.ctx.createThrow(this.ctx.createNull());
                           } else {
                              var15 = this.ctx.createJump(this.ld);
                           }

                           var15.setOffset(var27 + 1L);
                           var37.add(var15);
                           this.cfg.addBlock(var37);
                           IDInstruction var16 = this.ctx.createJump((int)var27);
                           var16.copyBaseFields(var5);
                           var4.set(var4.size() - 1, var16);
                           this.cfg.deleteOutEdges(var4);
                           this.cfg.addEdge(var4, var32);
                           this.cfg.addEdge(var32, var37);

                           for (int var18 : var21) {
                              this.cfg.addEdge(var32, this.cfg.getBlockAt((long)var18));
                           }

                           if (this.ld != null) {
                              this.cfg.addEdge(var37, this.cfg.getBlockAt((long)this.ld.intValue()));
                           }
                        }

                        this.cleanGraph();
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
      if (var2 > 0 && var5.size() != var2) {
         return false;
      } else {
         IDInstruction var6 = (IDInstruction)var5.getLast();
         return !var6.isJumpOrJcond() ? false : this.pC(var6, var3, var4);
      }
   }

   boolean pC(IDInstruction var1, boolean var2, boolean var3) {
      int var4 = (int)var1.getOffset();
      int var5 = var1.getBranchTarget();
      if (var1.isJump()) {
         if (!var3) {
            return false;
         } else {
            if (!var2) {
               for (cae.K var14 : this.UT.values()) {
                  if (var14.kS == var4) {
                     var14.kS = var5;
                  }

                  if (var14.wS == var4) {
                     var14.wS = var5;
                  }
               }

               if (this.E.add(var5)) {
                  this.sY.add(var5);
               }
            }

            return true;
         }
      } else {
         Assert.a(var1.isJcond());
         if (var1.getJcondCondition() instanceof IDOperation var6) {
            JavaOperatorType var13 = var6.getOperatorType();
            switch (var13) {
               case EQ:
               case NE:
               case GE:
               case GT:
               case LE:
               case LT:
                  IDExpression var8 = var6.getOperand1();
                  if (!(var8 instanceof IDVar)) {
                     return false;
                  } else {
                     if (var6.getOperand2() instanceof IDImm var10) {
                        if (this.kS != null && !this.kS.equals(var8)) {
                           return false;
                        }

                        if (!var2) {
                           cae.K var11 = new cae.K(var4, new cae.Av(var13, var10), var5, var4 + var1.getSize());
                           this.UT.put(var4, var11);
                           if (this.E.add(var11.kS)) {
                              this.sY.add(var11.kS);
                           }

                           if (this.E.add(var11.wS)) {
                              this.sY.add(var11.wS);
                           }

                           if (this.kS == null) {
                              this.kS = (IDVar)var8;
                              if (bpl.A(this.kS) != 32) {
                                 return false;
                              }

                              this.wS = var11;
                           }
                        }

                        return true;
                     }

                     return false;
                  }
               default:
                  return false;
            }
         } else {
            return false;
         }
      }
   }

   boolean A() {
      return this.pC(this.wS.pC, new ArrayList());
   }

   boolean pC(int var1, List var2) {
      cae.K var3 = (cae.K)this.UT.get(var1);
      if (var3 == null) {
         cae.Sv var4 = new cae.Sv(var2);
         if (!var4.pC()) {
            return false;
         } else {
            if (var4.A == null) {
               if (this.ld != null && !this.ld.equals(var1)) {
                  IDInstruction var6 = (IDInstruction)this.cfg.getInstruction((long)this.ld.intValue());
                  if (!var6.isReturn() || !((bpv)var6).pC((IDInstruction)this.cfg.getInstruction((long)var1))) {
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
      JavaOperatorType pC;
      IDImm A;

      public Av(JavaOperatorType var1, IDImm var2) {
         this.pC = var1;
         this.A = var2;
      }

      public cae.Av pC() {
         return new cae.Av(this.pC.getReverse(), this.A);
      }

      @Override
      public String toString() {
         return Strings.ff("%s %s", this.pC, this.A);
      }
   }

   private static class K {
      int pC;
      cae.Av A;
      int kS;
      int wS;

      public K(int var1, cae.Av var2, int var3, int var4) {
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
      IDImm A;

      Sv(List var2) {
         this.pC = var2;
      }

      boolean pC() {
         if (!brr.pC(cae.this.kS)) {
            return false;
         } else {
            IDOperation var1 = null;

            for (cae.Av var3 : this.pC) {
               IDOperation var4 = cae.this.g.createOperation(null, var3.pC, cae.this.kS, var3.A);
               if (var1 == null) {
                  var1 = var4;
               } else {
                  var1 = cae.this.g.createOperation(null, JavaOperatorType.LOG_AND, var1, var4);
               }
            }

            brr var5 = new brr(cae.this.g, var1, cae.this.kS);
            if (!var5.pC()) {
               return false;
            } else {
               bsd var6 = var5.A();
               if (var6.UT()) {
                  return false;
               } else {
                  if (var6.kS() == 1) {
                     bsd.Av var7 = (bsd.Av)var6.A().get(0);
                     if (var7.wS().equals(BigInteger.ONE)) {
                        this.A = cae.this.kS.spawn(var7.A().longValue());
                     }
                  }

                  return true;
               }
            }
         }
      }
   }
}
