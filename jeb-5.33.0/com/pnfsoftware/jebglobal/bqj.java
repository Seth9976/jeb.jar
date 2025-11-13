package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.collect.DisjointSets;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class bqj {
   private static final Set pC = new HashSet();
   private static final Set A = new HashSet();
   private IDMethodContext kS;
   private Set wS;
   private Set UT;
   private Set E;
   private Set sY;

   public bqj(IDMethodContext var1) {
      this.kS = var1;
   }

   public Set pC() {
      if (this.E == null) {
         throw new IllegalStateException();
      } else {
         return Collections.unmodifiableSet(this.E);
      }
   }

   public Set A() {
      if (this.sY == null) {
         throw new IllegalStateException();
      } else {
         return Collections.unmodifiableSet(this.sY);
      }
   }

   public boolean kS() {
      CFG var1 = this.kS.getCfg();
      IDFA var2 = var1.doDataFlowAnalysis();
      HashSet var3 = new HashSet();
      HashSet var4 = new HashSet();
      HashSet var5 = new HashSet();
      HashSet var6 = new HashSet();
      HashSet var7 = new HashSet();
      ArrayList var8 = new ArrayList();

      for (IDInstruction var10 : var1.instructions()) {
         var10.visitInstruction(new bqk(this, var8, var10, var4, var5));
      }

      DisjointSets var22 = new DisjointSets();
      MultiMap var23 = new MultiMap();
      HashSet var11 = new HashSet();

      while (!var8.isEmpty()) {
         bqj.Av var12 = (bqj.Av)var8.remove(0);
         if (var11.add(var12)) {
            int var13 = var12.wS();
            long var14 = var12.kS();
            var3.add(var13);
            if (var12.UT()) {
               if (var12.A()) {
                  var4.add(var13);
               } else if (var12.pC()) {
                  var5.add(var13);
               }

               for (long var35 : var2.getUseDefs(var14, var13)) {
                  if (var35 < 0L) {
                     var6.add(var13);
                  } else {
                     IDInstruction var39 = (IDInstruction)var1.getInstruction(var35);
                     if (!var39.isAssignToVar(var13)) {
                        Assert.debugFail();
                        return false;
                     }

                     IDExpression var40 = var39.getAssignSource();
                     if (var40 instanceof IDVar) {
                        int var42 = var40.asVar().getId();
                        var8.add(new bqj.Av(var35, var42, true));
                        var22.add(var42, var13);
                     } else if (!(var40 instanceof IDNewArrayInfo)) {
                        if (var40 instanceof IDCallInfo) {
                           String var43 = var40.asCallInfo().getMethodSignature();
                           if (!pC.contains(var43)) {
                              var6.add(var13);
                           }
                        } else if (var40 instanceof IDArrayElt) {
                           IDExpression var44 = var40.asArrayElt().getArray();
                           if (var44 instanceof IDVar) {
                              var23.put(var13, var44.asVar().getId());
                           } else {
                              var6.add(var13);
                           }
                        } else {
                           var6.add(var13);
                        }
                     }

                     var8.add(new bqj.Av(var35, var13, false));
                  }
               }
            } else {
               for (long var17 : var2.getDefUses(var14, var13)) {
                  IDInstruction var19 = (IDInstruction)var1.getInstruction(var17);
                  if (var19.isAssignFromVar(var13)) {
                     IDExpression var20 = var19.getAssignDestination();
                     if (var20 instanceof IDVar) {
                        int var21 = var20.asVar().getId();
                        var8.add(new bqj.Av(var17, var21, false));
                        var22.add(var13, var21);
                     } else if (var20 instanceof IDArrayElt) {
                        IDExpression var41 = var20.asArrayElt().getArray();
                        if (!(var41 instanceof IDNewArrayInfo)) {
                           if (var41 instanceof IDVar) {
                              var23.put(var13, var41.asVar().getId());
                           } else {
                              var7.add(var13);
                           }
                        }
                     } else {
                        var7.add(var13);
                     }
                  } else if (!var19.visitInstruction(new bql(this, var13, var7), true)) {
                     Assert.debugFail();
                     return false;
                  }
               }
            }
         }
      }

      for (int var26 : var6) {
         for (int var15 : var7) {
            var22.add(var15, var26);
         }
      }

      HashSet var25 = new HashSet(var6.size() + var7.size());
      var25.addAll(var6);
      var25.addAll(var7);
      ArrayList var27 = new ArrayList(Arrays.asList(var3, var4, var5, var25));

      int var29;
      do {
         var29 = 0;
         var3.addAll(var4);
         var3.addAll(var5);
         var3.addAll(var25);
         var23.removeAll(var25);

         for (int var33 : var23.keySet()) {
            if (!var25.contains(var33)) {
               for (int var18 : var23.get(var33)) {
                  if (var25.contains(var18)) {
                     if (var25.add(var33)) {
                        var29++;
                     }
                     break;
                  }
               }
            }
         }

         for (Set var34 : var22.getSets()) {
            for (Set var38 : var27) {
               if (CollectionUtil.hasIntersection(var38, var34) && var38.addAll(var34)) {
                  var29++;
               }
            }
         }
      } while (var29 > 0);

      this.wS = var3;
      this.UT = var4;
      this.E = var5;
      this.sY = var25;
      return true;
   }

   static {
      pC.add("Ljava/lang/String;->getBytes()[B");
      pC.add("Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B");
      A.add("Ljava/lang/String;-><init>([C)V");
      A.add("Ljava/lang/String;-><init>([B)V");
      A.add("Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V");
      A.add("Ljava/lang/String;-><init>([BI)V");
   }

   static class Av extends bsj {
      private Boolean pC;

      public Av(long var1, int var3, boolean var4) {
         this(var1, var3, var4, null);
      }

      public Av(long var1, int var3, boolean var4, Boolean var5) {
         super(var1, var3, var4);
         if (var5 != null && !var4) {
            throw new IllegalArgumentException();
         } else {
            this.pC = var5;
         }
      }

      public boolean pC() {
         return Boolean.TRUE.equals(this.pC);
      }

      public boolean A() {
         return Boolean.FALSE.equals(this.pC);
      }

      @Override
      public int hashCode() {
         int var1 = super.hashCode();
         return 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!super.equals(var1)) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            bqj.Av var2 = (bqj.Av)var1;
            if (this.pC == null) {
               if (var2.pC != null) {
                  return false;
               }
            } else if (!this.pC.equals(var2.pC)) {
               return false;
            }

            return true;
         }
      }
   }
}
