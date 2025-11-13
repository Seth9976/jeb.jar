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

public class cex extends AbstractDOptimizer {
   private static final ILogger RF = GlobalLog.getLogger(cex.class, Integer.MAX_VALUE);
   public static final int q = 5;
   private int xK = 5;
   private IDVar Dw;
   private cex.nI Uv;
   private TreeMap oW = new TreeMap();
   private Set gO = new HashSet();
   private Deque nf = new ArrayDeque();
   private Map gP = new HashMap();
   private Integer za;

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

      label160:
      for (int var3 = 0; var3 < this.cfg.size(); var3++) {
         BasicBlock var4 = this.cfg.get(var3);
         if (!var2.contains((int)var4.getBase())) {
            IDInstruction var5 = (IDInstruction)var4.getLast();
            if (var5.isJcond()) {
               this.q();
               if (this.q(var5, false, false)) {
                  if (var4.insize() == 1) {
                     BasicBlock var6 = var4.getInputBlock(0);
                     if (var6.size() == 1 && this.q((int)var6.getBase(), 1, true, false)) {
                        continue;
                     }
                  }

                  while (!this.nf.isEmpty()) {
                     int var19 = (Integer)this.nf.pop();
                     this.q(var19, 1, false, true);
                  }

                  var2.addAll(this.oW.keySet());
                  if (this.oW.size() >= this.xK) {
                     HashMap var20 = new HashMap();

                     for (cex.nI var8 : this.oW.values()) {
                        if (var20.containsKey(var8.xK)) {
                           if (this.oW.get(var8.xK) != null) {
                              continue label160;
                           }
                        } else {
                           var20.put(var8.xK, var8.q);
                        }

                        if (var20.containsKey(var8.Dw)) {
                           if (this.oW.get(var8.Dw) != null) {
                              continue label160;
                           }
                        } else {
                           var20.put(var8.Dw, var8.q);
                        }
                     }

                     if (this.RF()) {
                        TreeSet var21 = new TreeSet(this.gP.values());
                        if (this.za != null) {
                           BasicBlock var22 = this.cfg.getBlockAt((long)this.za.intValue());
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
                              if (var28.isJumpOrJcond() && this.za.intValue() != var25.getBase()) {
                                 var24 = var25;
                              }
                           }
                        }

                        if (var24 != null) {
                           IDSwitchData var26 = this.g.createSwitchData();

                           for (Entry var11 : this.gP.entrySet()) {
                              var26.addCase((int)((IDImm)var11.getKey()).getValueAsLong(), this.g.createTarget((Integer)var11.getValue()), false);
                           }

                           IDInstruction var30 = this.ctx.createSwitch(this.Dw, var26);
                           var30.copyBaseFields(var5);
                           var4.set(var4.size() - 1, var30);
                           IDInstruction var31 = (IDInstruction)var24.get(0);
                           IDInstruction var12;
                           if (this.za == null) {
                              var12 = this.ctx.createThrow(this.ctx.createNull());
                           } else {
                              var12 = this.ctx.createJump(this.za);
                           }

                           var12.copyBaseFields(var31);
                           var24.set(0, var12);
                           this.cfg.deleteOutEdges(var4);
                           this.cfg.deleteOutEdges(var24);
                           this.cfg.addEdge(var4, var24);

                           for (int var14 : var21) {
                              this.cfg.addEdge(var4, this.cfg.getBlockAt((long)var14));
                           }

                           if (this.za != null) {
                              this.cfg.addEdge(var24, this.cfg.getBlockAt((long)this.za.intValue()));
                           }
                        } else {
                           long var27 = this.cfg.getEndAddress();
                           BasicBlock var32 = new BasicBlock();
                           IDSwitchData var33 = this.g.createSwitchData();

                           for (Entry var36 : this.gP.entrySet()) {
                              var33.addCase((int)((IDImm)var36.getKey()).getValueAsLong(), this.g.createTarget((Integer)var36.getValue()), false);
                           }

                           IDInstruction var35 = this.ctx.createSwitch(this.Dw, var33);
                           var35.setOffset(var27);
                           var32.add(var35);
                           this.cfg.addBlock(var32);
                           BasicBlock var37 = new BasicBlock();
                           IDInstruction var15;
                           if (this.za == null) {
                              var15 = this.ctx.createThrow(this.ctx.createNull());
                           } else {
                              var15 = this.ctx.createJump(this.za);
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

                           if (this.za != null) {
                              this.cfg.addEdge(var37, this.cfg.getBlockAt((long)this.za.intValue()));
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

   boolean q(int var1, int var2, boolean var3, boolean var4) {
      BasicBlock var5 = this.cfg.getBlockAt((long)var1);
      if (var2 > 0 && var5.size() != var2) {
         return false;
      } else {
         IDInstruction var6 = (IDInstruction)var5.getLast();
         return !var6.isJumpOrJcond() ? false : this.q(var6, var3, var4);
      }
   }

   boolean q(IDInstruction var1, boolean var2, boolean var3) {
      int var4 = (int)var1.getOffset();
      int var5 = var1.getBranchTarget();
      if (var1.isJump()) {
         if (!var3) {
            return false;
         } else {
            if (!var2) {
               for (cex.nI var14 : this.oW.values()) {
                  if (var14.xK == var4) {
                     var14.xK = var5;
                  }

                  if (var14.Dw == var4) {
                     var14.Dw = var5;
                  }
               }

               if (this.gO.add(var5)) {
                  this.nf.add(var5);
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
                        if (this.Dw != null && !this.Dw.equals(var8)) {
                           return false;
                        }

                        if (!var2) {
                           cex.nI var11 = new cex.nI(var4, new cex.eo(var13, var10), var5, var4 + var1.getSize());
                           this.oW.put(var4, var11);
                           if (this.gO.add(var11.xK)) {
                              this.nf.add(var11.xK);
                           }

                           if (this.gO.add(var11.Dw)) {
                              this.nf.add(var11.Dw);
                           }

                           if (this.Dw == null) {
                              this.Dw = (IDVar)var8;
                              if (bto.RF(this.Dw) != 32) {
                                 return false;
                              }

                              this.Uv = var11;
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

   boolean RF() {
      return this.q(this.Uv.q, new ArrayList());
   }

   boolean q(int var1, List var2) {
      cex.nI var3 = (cex.nI)this.oW.get(var1);
      if (var3 == null) {
         cex.CU var4 = new cex.CU(var2);
         if (!var4.q()) {
            return false;
         } else {
            if (var4.RF == null) {
               if (this.za != null && !this.za.equals(var1)) {
                  IDInstruction var6 = (IDInstruction)this.cfg.getInstruction((long)this.za.intValue());
                  if (!var6.isReturn() || !((bub)var6).q((IDInstruction)this.cfg.getInstruction((long)var1))) {
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
      IDImm RF;

      CU(List var2) {
         this.q = var2;
      }

      boolean q() {
         if (!bwb.q(cex.this.Dw)) {
            return false;
         } else {
            IDOperation var1 = null;

            for (cex.eo var3 : this.q) {
               IDOperation var4 = cex.this.g.createOperation(null, var3.q, cex.this.Dw, var3.RF);
               if (var1 == null) {
                  var1 = var4;
               } else {
                  var1 = cex.this.g.createOperation(null, JavaOperatorType.LOG_AND, var1, var4);
               }
            }

            bwb var5 = new bwb(cex.this.g, var1, cex.this.Dw);
            if (!var5.xK()) {
               return false;
            } else {
               bwp var6 = var5.Dw();
               if (var6.gP()) {
                  return false;
               } else {
                  if (var6.Uv() == 1) {
                     bwp.eo var7 = (bwp.eo)var6.Dw().get(0);
                     if (var7.Dw().equals(BigInteger.ONE)) {
                        this.RF = cex.this.Dw.spawn(var7.RF().longValue());
                     }
                  }

                  return true;
               }
            }
         }
      }
   }

   private static class eo {
      JavaOperatorType q;
      IDImm RF;

      public eo(JavaOperatorType var1, IDImm var2) {
         this.q = var1;
         this.RF = var2;
      }

      public cex.eo q() {
         return new cex.eo(this.q.getReverse(), this.RF);
      }

      @Override
      public String toString() {
         return Strings.ff("%s %s", this.q, this.RF);
      }
   }

   private static class nI {
      int q;
      cex.eo RF;
      int xK;
      int Dw;

      public nI(int var1, cex.eo var2, int var3, int var4) {
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
