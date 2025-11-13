package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICContinue;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.reflect.ReflectionHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class agc {
   private static final StructuredLogger pC = aco.pC(agc.class);
   private List A;
   private List kS;
   private List wS = new ArrayList();

   public agc(ICMethod var1) {
      this.A = var1.toFlatList();
      this.kS = this.kS();
   }

   private List kS() {
      ArrayList var1 = new ArrayList();
      int var2 = 0;

      for (ICStatement var4 : this.A) {
         if (var4 instanceof afs) {
            var2++;
         }

         var1.add(new agc.Av(var2));
         if (var4 instanceof aft) {
            var2--;
         }
      }

      return var1;
   }

   public List pC() {
      return this.A;
   }

   public ICStatement pC(int var1) {
      return (ICStatement)this.A.get(var1);
   }

   public void pC(int var1, int var2) {
      if (var1 != var2) {
         if (var1 < var2) {
            var2--;
         }

         ICStatement var3 = (ICStatement)this.A.remove(var1);
         this.A.add(var2, var3);
         this.kS.remove(var1);
         this.kS.add(var2, ((agc.Av)this.kS.get(var2)).pC());
      }
   }

   public List A() {
      return this.wS;
   }

   public int pC(int var1, boolean var2, boolean var3, int var4, Collection var5) {
      this.wS.clear();
      if (var1 < 0) {
         throw new IllegalArgumentException();
      } else if (var1 >= this.A.size()) {
         return 0;
      } else {
         int var6 = var1;

         while (true) {
            if (var3) {
               var6++;
               var3 = false;
            } else {
               var6 = this.pC(var6, var2);
            }

            if (var6 > 0 && var6 < this.A.size()) {
               if (this.wS.contains(var6)) {
                  return var6;
               }

               this.wS.add(var6);
               if (var4 >= 1) {
                  ICStatement var7 = (ICStatement)this.A.get(var6);
                  if (var7 instanceof aft
                     || var7 instanceof agj
                     || var7 instanceof afx
                     || var7 instanceof agb
                     || var7 instanceof agh
                     || var4 >= 2 && (var5 == null || !ReflectionHelper.isInstance(var7, var5))) {
                     continue;
                  }
               }

               return var6;
            }

            if (!this.wS.isEmpty()) {
               return (Integer)this.wS.get(this.wS.size() - 1);
            }

            return var6;
         }
      }
   }

   int pC(int var1, boolean var2) {
      if (var1 >= this.A.size()) {
         return 0;
      } else {
         ICStatement var3 = (ICStatement)this.A.get(var1);
         if (var3 instanceof agd) {
            return var1 + 1;
         } else if (var3 instanceof afs) {
            return var1 + 1;
         } else if (!(var3 instanceof agj)
            && !(var3 instanceof afu)
            && !(var3 instanceof afx)
            && !(var3 instanceof aga)
            && !(var3 instanceof agb)
            && !(var3 instanceof agh)) {
            int var4 = ((agc.Av)this.kS.get(var1)).pC;
            if (!(var3 instanceof agk)) {
               if (var3 instanceof ICReturn) {
                  return var2 ? -1 : 0;
               } else if (var3 instanceof ICLabel) {
                  return var1 + 1;
               } else if (var3 instanceof ICBreak) {
                  ICLabel var15 = ((ICBreak)var3).getLabel();
                  return var15 != null ? -1 : this.pC(true, var4 - 1, var1) + 1;
               } else if (var3 instanceof ICContinue) {
                  ICLabel var14 = ((ICContinue)var3).getLabel();
                  return var14 != null ? -1 : this.pC(false, var4 - 1, var1);
               } else if (var3 instanceof ICGoto) {
                  ICLabel var13 = ((ICGoto)var3).getLabel();

                  for (int var17 = 0; var17 < this.A.size(); var17++) {
                     if (this.A.get(var17) == var13) {
                        return var17;
                     }
                  }

                  return -1;
               } else {
                  return var2 ? -1 : var1 + 1;
               }
            } else if (var3 instanceof afy || var3 instanceof afz) {
               ICPredicate var12 = ((afr)var3).pC();
               if (var12.isLitteralTrue()) {
                  return var1 + 1;
               } else if (var12.isLitteralFalse()) {
                  int var16 = this.pC(var1 + 1, aft.class, ((agc.Av)this.kS.get(var1 + 1)).pC);
                  return var16 + 1;
               } else {
                  return -1;
               }
            } else if (var3 instanceof age) {
               return -1;
            } else if (var3 instanceof agi) {
               ICPredicate var11 = ((agi)var3).pC();
               if (var11.isLitteralTrue()) {
                  return var1 + 1;
               } else {
                  return var11.isLitteralFalse() ? this.pC(var1, agj.class, var4) : -1;
               }
            } else if (var3 instanceof afv) {
               ICPredicate var10 = ((afv)var3).pC();
               if (var10.isLitteralTrue()) {
                  return this.A(var1, afu.class, var4);
               } else {
                  return var10.isLitteralFalse() ? var1 + 1 : -1;
               }
            } else if (var3 instanceof afw) {
               ICPredicate var9 = ((afw)var3).pC();
               if (var9.isLitteralTrue()) {
                  return var1 + 1;
               } else {
                  return var9.isLitteralFalse() ? this.pC(var1, afx.class, var4) : -1;
               }
            } else {
               if (var3 instanceof aft) {
                  if (var1 + 1 >= this.A.size()) {
                     return 0;
                  }

                  ICStatement var5 = (ICStatement)this.A.get(var1 + 1);
                  if (var5 instanceof afs || var5 instanceof aft) {
                     return var1 + 1;
                  }

                  if (!(var5 instanceof agk)) {
                     return var1 + 1;
                  }

                  if (((agk)var5).UT() == agk.Sv.pC) {
                     return var1 + 1;
                  }

                  int var6 = this.A(var1, afs.class, var4);
                  if (var6 > 0) {
                     ICStatement var7 = (ICStatement)this.A.get(var6 - 1);
                     if (var7 instanceof agi || var7 instanceof afw) {
                        return var6 - 1;
                     }

                     if (var7 instanceof afu) {
                        return var1 + 1;
                     }

                     if (var7 instanceof afy || var7 instanceof afz || var7 instanceof aga) {
                        return this.pC(var1, agb.class, ((agc.Av)this.kS.get(var6 - 1)).pC);
                     }

                     if (var7 instanceof age) {
                        throw new RuntimeException("Should not happen");
                     }

                     if (var7 instanceof agf || var7 instanceof agg) {
                        if (var5 instanceof agh) {
                           return var1 + 1;
                        }

                        if (var1 + 3 < this.A.size()) {
                           ICStatement var8 = (ICStatement)this.A.get(var1 + 2);
                           if ((var8 instanceof agf || var8 instanceof agg) && this.A.get(var1 + 3) instanceof afs) {
                              return var1 + 1 + 2;
                           }
                        }

                        return -1;
                     }
                  }
               }

               return -1;
            }
         } else {
            return var1 + 1;
         }
      }
   }

   int pC(int var1, Class var2, int var3) {
      while (var1 < this.A.size()) {
         if (var2.isAssignableFrom(((ICStatement)this.A.get(var1)).getClass()) && ((agc.Av)this.kS.get(var1)).pC == var3) {
            return var1;
         }

         var1++;
      }

      throw new RuntimeException("Pseudo-statement was not found");
   }

   int A(int var1, Class var2, int var3) {
      while (var1 >= 0) {
         if (var2.isAssignableFrom(((ICStatement)this.A.get(var1)).getClass()) && ((agc.Av)this.kS.get(var1)).pC == var3) {
            return var1;
         }

         var1--;
      }

      throw new RuntimeException("Pseudo-statement was not found (backward)");
   }

   int pC(int var1, agk.Av var2, agk.Sv var3, int var4) {
      while (var1 < this.A.size()) {
         ICStatement var5 = (ICStatement)this.A.get(var1);
         if (var5 instanceof agk && ((agk)var5).wS() == var2 && ((agk)var5).UT() == var3 && ((agc.Av)this.kS.get(var1)).pC == var4) {
            return var1;
         }

         var1++;
      }

      throw new RuntimeException("Pseudo-statement was not found");
   }

   int A(int var1, agk.Av var2, agk.Sv var3, int var4) {
      while (var1 >= 0) {
         ICStatement var5 = (ICStatement)this.A.get(var1);
         if (var5 instanceof agk && ((agk)var5).wS() == var2 && ((agk)var5).UT() == var3 && ((agc.Av)this.kS.get(var1)).pC == var4) {
            return var1;
         }

         var1--;
      }

      throw new RuntimeException("Pseudo-statement was not found (backward)");
   }

   public int A(int var1) {
      ICStatement var2 = (ICStatement)this.A.get(var1);
      int var3 = ((agc.Av)this.kS.get(var1)).pC;
      if (!(var2 instanceof agk)) {
         return -1;
      } else {
         agk.Av var4 = ((agk)var2).wS();
         agk.Sv var5 = ((agk)var2).UT();
         if (var5 == agk.Sv.pC) {
            return this.pC(var1, var4, agk.Sv.kS, var3);
         } else {
            return var5 == agk.Sv.kS ? this.A(var1, var4, agk.Sv.pC, var3) : -1;
         }
      }
   }

   int pC(boolean var1, int var2, int var3) {
      byte var4;
      int var5;
      if (var1) {
         var4 = 1;
         var5 = this.A.size() - 1;
      } else {
         var4 = -1;
         var5 = 0;
      }

      int var6;
      for (var6 = var3 + var4; var6 != var5; var6 += var4) {
         if (((agc.Av)this.kS.get(var6)).pC <= var2) {
            ICStatement var7 = (ICStatement)this.A.get(var6);
            if (var1
               ? var7 instanceof agh || var7 instanceof agj || var7 instanceof afv || var7 instanceof afx
               : var7 instanceof agi || var7 instanceof afu || var7 instanceof afw) {
               break;
            }
         }
      }

      return var6 == var5 ? -1 : var6;
   }

   public static String pC(List var0, List var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (ICStatement var5 : var0) {
         agc.Av var6 = (agc.Av)var1.get(var3);
         Strings.ff(var2, "%04d|%d: %s%s\n", var3, var6.A(), Strings.generate(' ', 2 * var6.A()), var5);
         var3++;
      }

      return var2.toString();
   }

   @Override
   public String toString() {
      return pC(this.A, this.kS);
   }

   public static class Av {
      int pC;
      List A;

      public Av(int var1, List var2) {
         this.pC = var1;
         this.A = var2;
      }

      public Av(int var1) {
         this(var1, null);
      }

      public agc.Av pC() {
         return new agc.Av(this.pC, this.A == null ? null : new ArrayList(this.A));
      }

      public int A() {
         return this.pC;
      }
   }
}
