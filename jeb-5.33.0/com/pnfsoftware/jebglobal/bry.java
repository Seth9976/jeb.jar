package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class bry {
   private IDMethodContext A;
   private Set kS;
   private BasicBlock wS;
   private int UT;
   private BasicBlock E;
   private int sY;
   private BasicBlock ys;
   private int ld;
   boolean pC;
   private brc gp;
   private bry.Av oT;

   public bry(IDMethodContext var1, Set var2, BasicBlock var3, int var4, BasicBlock var5, int var6, BasicBlock var7, int var8) {
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
      this.UT = var4;
      this.E = var5;
      this.sY = var6;
      this.ys = var7;
      this.ld = var8;
   }

   public void pC(boolean var1) {
      this.pC = var1;
   }

   public bry.Av pC() {
      if (!this.kS.contains(this.wS) || this.UT < 0 || !this.kS.contains(this.E) || this.sY < 0 || this.kS.contains(this.ys) || this.ld < 0) {
         return null;
      } else if (!this.A()) {
         return null;
      } else {
         Assert.a(this.oT != null);
         return this.oT;
      }
   }

   private boolean A() {
      CFG var1 = this.A.getCfg();
      IDTryData var2 = this.A.getExceptionData();
      ArrayDeque var3 = new ArrayDeque();
      var3.add(new Couple(this.wS.getBase(), this.ys.getBase()));
      HashSet var4 = new HashSet();
      HashSet var5 = new HashSet();
      IdentityHashMap var6 = new IdentityHashMap();
      HashSet var7 = new HashSet();
      HashSet var8 = new HashSet();
      HashSet var9 = new HashSet();
      HashSet var10 = new HashSet();
      BasicBlock var11 = null;
      int var12 = -1;
      HashSet var13 = new HashSet();
      HashSet var14 = new HashSet();

      label296:
      while (!var3.isEmpty()) {
         Couple var15 = (Couple)var3.remove();
         if (var4.add(var15)) {
            long var16 = (Long)var15.getFirst();
            long var18 = (Long)var15.getSecond();
            BasicBlock var20 = var1.getBlockAt(var16);
            BasicBlock var21 = var1.getBlockAt(var18);
            int var22 = var20 == this.wS ? this.UT : 0;

            for (int var23 = var21 == this.ys ? this.ld : 0; var22 >= 0 && var22 <= var20.size(); var23++) {
               var8.add(var20);
               if (var22 == var20.size()) {
                  if (var20.outsize() == 0) {
                     return false;
                  }

                  var20 = var20.getOutputBlock(0);
                  var22 = 0;
               }

               boolean var24 = false;
               var13.clear();

               IDInstruction var25;
               while (true) {
                  var8.add(var20);
                  if (var20 == this.E && var22 == this.sY) {
                     var24 = true;
                     var25 = null;
                     break;
                  }

                  var25 = (IDInstruction)var20.get(var22);
                  if (var25.isJump()) {
                     var20 = var20.getOutputBlock(0);
                     var22 = 0;
                  } else {
                     if (!var25.isNop()) {
                        break;
                     }

                     if (++var22 == var20.size()) {
                        var20 = var20.getOutputBlock(0);
                        var22 = 0;
                     }
                  }

                  if (!var13.add(var25)) {
                     return false;
                  }
               }

               if (var23 < 0 || var23 > var21.size()) {
                  return false;
               }

               if (var23 == var21.size()) {
                  if (var21.outsize() == 0) {
                     return false;
                  }

                  var21 = var21.getOutputBlock(0);
                  var23 = 0;
               }

               var14.clear();

               IDInstruction var26;
               while (true) {
                  var26 = (IDInstruction)var21.get(var23);
                  boolean var27 = var21.size() == 1 && ((IDInstruction)var21.get(0)).isJump() && var21.getOutputBlock(0) != var21;
                  boolean var28 = !var24 || var27 || var23 != 0;
                  if (!var28) {
                     break;
                  }

                  var9.add(var21);
                  if (var21 != this.ys) {
                     var10.addAll(var21.getInputs());
                     var10.addAll(var21.getIrregularInputs());
                  }

                  var7.add(var26);
                  if (var26.isJump()) {
                     var21 = var21.getOutputBlock(0);
                     var23 = 0;
                  } else {
                     if (!var26.isNop()) {
                        break;
                     }

                     var23++;
                     if (var22 == var21.size()) {
                        var21 = var21.getOutputBlock(0);
                        var23 = 0;
                     }
                  }

                  if (!var14.add(var25)) {
                     return false;
                  }
               }

               if (var24) {
                  if (var11 != null) {
                     if (var11 == var21 && var12 == var23) {
                        continue label296;
                     }

                     if (var26.isReturnOrThrow() && !var26.canThrow()) {
                        IDInstruction var46 = (IDInstruction)var11.get(var12);
                        if (((bpv)var46).pC(var26)) {
                           if (CFGUtil.canReach(var21, var11, true)) {
                              if (CFGUtil.canReach(var11, var21, true)) {
                                 return false;
                              }
                           } else {
                              if (!CFGUtil.canReach(var11, var21, true)) {
                                 return false;
                              }

                              var11 = var21;
                              var12 = var23;
                           }
                           continue label296;
                        }
                     }

                     return false;
                  }

                  var11 = var21;
                  var12 = var23;
                  continue label296;
               }

               IDInstruction var45 = (IDInstruction)var6.get(var25);
               if (var45 != null) {
                  if (var45 != var26) {
                     return false;
                  }
                  continue label296;
               }

               if (this.gp == null) {
                  this.gp = new brc(true);
               }

               if (!this.gp.pC(var25, var26)) {
                  this.gp.pC(var25, var26);
                  return false;
               }

               if (var25.getBreakingFlow().isBroken()) {
                  if (!var25.isJcond()) {
                     return false;
                  }

                  var3.add(new Couple((long)var25.getBranchTarget(), (long)var26.getBranchTarget()));
               }

               var6.put(var25, var26);
               if (var25.canThrow()) {
                  Couple var47 = new Couple(var20.getBase(), var21.getBase());
                  if (var5.add(var47)) {
                     List var29 = var2.getBlockHandlers((int)var20.getBase());
                     List var30 = var2.getBlockHandlers((int)var21.getBase());
                     if (var29.size() != var30.size()) {
                        return false;
                     }

                     for (int var31 = 0; var31 < var29.size(); var31++) {
                        IDExceptionHandler var32 = (IDExceptionHandler)var29.get(var31);
                        IDExceptionHandler var33 = (IDExceptionHandler)var30.get(var31);
                        if (var32.getTypeIndex() != var33.getTypeIndex()) {
                           return false;
                        }

                        BasicBlock var34 = var1.getBlockAt((long)var32.getAddress());
                        BasicBlock var35 = var1.getBlockAt((long)var33.getAddress());
                        if (this.kS.contains(var34)) {
                           var3.add(new Couple(var34.getBase(), var35.getBase()));
                        } else if (var34 != var35) {
                           return false;
                        }
                     }
                  }
               }

               var22++;
            }

            return false;
         }
      }

      if (var12 < 0) {
         return false;
      } else if (var7.isEmpty()) {
         return false;
      } else {
         if (var12 == 0 && this.pC) {
            int var36 = 0;
            int var39 = 0;
            IDInstruction var17 = null;

            for (BasicBlock var19 : var11.getInputs()) {
               IDInstruction var44 = (IDInstruction)var19.getLast();
               if (var7.contains(var44)) {
                  var17 = var44;
                  var36++;
               } else {
                  var39++;
               }
            }

            if (var36 == 0) {
               return false;
            }

            if (var36 != 1) {
               if (var39 != 0) {
                  return false;
               }
            } else if (!var17.getBreakingFlow().isBroken()) {
               var11 = var1.getBlockFor(var17);
               var12 = var11.size();
            } else if (var17.isJump()) {
               var11 = var1.getBlockFor(var17);
               var12 = var11.size() - 1;
            } else if (var39 != 0) {
               return false;
            }
         }

         if (!var8.equals(this.kS)) {
            return false;
         } else if (!var9.containsAll(var10)) {
            return false;
         } else if (CollectionUtil.hasIntersection(var8, var9)) {
            return false;
         } else {
            for (BasicBlock var40 : this.ys.getIrregularInputs()) {
               if (var7.contains(var40.getLast())) {
                  return false;
               }
            }

            ArrayList var38 = new ArrayList();

            for (BasicBlock var42 : this.ys.getInputs()) {
               if (var7.contains(var42.getLast())) {
                  if (this.ld != 0) {
                     return false;
                  }
               } else {
                  var38.add(var42);
               }
            }

            this.oT = new bry.Av(this.ys, this.ld, var11, var12, var7, var6, var38, this.gp == null ? Collections.emptyMap() : this.gp.pC());
            return true;
         }
      }
   }

   public static class Av {
      public BasicBlock pC;
      public int A;
      public BasicBlock kS;
      public int wS;
      public Set UT;
      public Map E;
      public List sY = new ArrayList();
      public Map ys;

      Av(BasicBlock var1, int var2, BasicBlock var3, int var4, Set var5, Map var6, List var7, Map var8) {
         Assert.a(var1 != null && var2 >= 0 && var3 != null && var4 >= 0 && var5 != null && var6 != null);
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
         this.UT = var5;
         this.E = var6;
         this.sY = var7;
         this.ys = var8;
      }
   }
}
