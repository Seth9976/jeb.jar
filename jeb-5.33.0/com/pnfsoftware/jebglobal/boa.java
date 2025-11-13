package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBreak;
import com.pnfsoftware.jeb.core.units.code.java.IJavaContinue;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDoWhile;
import com.pnfsoftware.jeb.core.units.code.java.IJavaFor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaForEach;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaThrow;
import com.pnfsoftware.jeb.core.units.code.java.IJavaWhile;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.reflect.ReflectionHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class boa {
   private static final ILogger pC = GlobalLog.getLogger(boa.class);
   private List A;
   private List kS;
   private int wS;
   private List UT = new ArrayList();

   public boa(IJavaMethod var1) {
      this(var1.toFlatList());
   }

   public boa(List var1) {
      this.A = var1;
      this.kS = this.pC(var1);
   }

   public int pC() {
      return this.A.size();
   }

   public void pC(int var1) {
      this.A.remove(var1);
      this.kS.remove(var1);
   }

   public void pC(int var1, int var2) {
      if (var1 != var2) {
         if (var1 < var2) {
            var2--;
         }

         IJavaStatement var3 = (IJavaStatement)this.A.remove(var1);
         this.A.add(var2, var3);
         this.kS.remove(var1);
         this.kS.add(var2, ((boa.Av)this.kS.get(var2)).pC());
      }
   }

   public List pC(List var1) {
      ArrayList var2 = new ArrayList(var1.size());
      int var3 = 0;
      int var5 = 0;
      ArrayList var6 = new ArrayList();

      for (IJavaStatement var8 : var1) {
         if (var8 instanceof bno) {
            var3++;
         } else if (var8 instanceof bol) {
            var6 = new ArrayList(var6);
            var6.remove(var6.size() - 1);
            if (!var6.isEmpty()) {
               var6.get(var6.size() - 1);
            }
         }

         var2.add(new boa.Av(var3, var6));
         if (var8 instanceof bnp) {
            var3--;
         } else if (var8 instanceof boi) {
            var6 = new ArrayList(var6);
            int var4 = var5++;
            var6.add(var4);
         }
      }

      return var2;
   }

   public List A() {
      return this.A;
   }

   public IJavaStatement A(int var1) {
      return (IJavaStatement)this.A.get(var1);
   }

   public List kS() {
      return this.kS;
   }

   public List wS() {
      return this.UT;
   }

   public void kS(int var1) {
      this.wS = var1;
   }

   public int UT() {
      return this.wS;
   }

   public IJavaStatement E() {
      int var1 = this.pC(this.wS, false, false, 1, null);
      if (var1 <= 0) {
         return null;
      } else {
         this.wS = var1;
         return (IJavaStatement)this.A.get(this.wS);
      }
   }

   public int wS(int var1) {
      return this.pC(var1, false, false, 1, null);
   }

   public int pC(int var1, boolean var2, boolean var3, int var4, Collection var5) {
      this.UT.clear();
      if (var1 < 0) {
         throw new IllegalArgumentException();
      } else if (var1 >= this.A.size()) {
         return 0;
      } else {
         int var6 = var1;

         while (true) {
            if (var3) {
               this.UT.add(var6);
               var6++;
               var3 = false;
            } else {
               var6 = this.pC(var6, var2);
            }

            if (var6 > 0 && var6 < this.A.size()) {
               if (this.UT.contains(var6)) {
                  return var6;
               }

               this.UT.add(var6);
               if (var4 >= 1) {
                  IJavaStatement var7 = (IJavaStatement)this.A.get(var6);
                  if (var7 instanceof bnp
                     || var7 instanceof boo
                     || var7 instanceof bnv
                     || var7 instanceof bnu
                     || var7 instanceof bnz
                     || var7 instanceof bof
                     || var7 instanceof bok
                     || var7 instanceof boh
                     || var4 >= 2 && (var5 == null || !ReflectionHelper.isInstance(var7, var5))) {
                     continue;
                  }
               }

               return var6;
            }

            if (!this.UT.isEmpty()) {
               return (Integer)this.UT.get(this.UT.size() - 1);
            }

            return var6;
         }
      }
   }

   public int pC(int var1, boolean var2) {
      if (var1 >= this.A.size()) {
         return 0;
      } else {
         IJavaStatement var3 = (IJavaStatement)this.A.get(var1);
         if (var3 instanceof bob) {
            return var1 + 1;
         } else if (var3 instanceof bno) {
            return var1 + 1;
         } else if (!(var3 instanceof boo)
            && !(var3 instanceof bnq)
            && !(var3 instanceof bnv)
            && !(var3 instanceof bnu)
            && !(var3 instanceof bny)
            && !(var3 instanceof bnz)
            && !(var3 instanceof bof)
            && !(var3 instanceof bok)
            && !(var3 instanceof boh)) {
            int var4 = ((boa.Av)this.kS.get(var1)).pC;
            if (!(var3 instanceof bop)) {
               if (!(var3 instanceof IJavaReturn) && !(var3 instanceof IJavaThrow)) {
                  if (var3 instanceof IJavaLabel) {
                     return var1 + 1;
                  } else if (var3 instanceof IJavaBreak) {
                     IJavaLabel var15 = ((IJavaBreak)var3).getLabel();
                     return var15 != null ? -1 : this.pC(true, var4 - 1, var1) + 1;
                  } else if (var3 instanceof IJavaContinue) {
                     IJavaLabel var14 = ((IJavaContinue)var3).getLabel();
                     return var14 != null ? -1 : this.pC(false, var4 - 1, var1);
                  } else if (var3 instanceof IJavaGoto) {
                     IJavaLabel var13 = ((IJavaGoto)var3).getLabel();

                     for (int var17 = 0; var17 < this.A.size(); var17++) {
                        if (this.A.get(var17) == var13) {
                           return var17;
                        }
                     }

                     return -1;
                  } else {
                     return var2 ? -1 : var1 + 1;
                  }
               } else {
                  return var2 ? -1 : 0;
               }
            } else if (var3 instanceof bnw || var3 instanceof bnx) {
               IJavaPredicate var12 = ((bnn)var3).pC();
               if (var12.isLitteralTrue()) {
                  return var1 + 1;
               } else if (var12.isLitteralFalse()) {
                  int var16 = this.pC(var1 + 1, bnp.class, ((boa.Av)this.kS.get(var1 + 1)).pC);
                  return var16 + 1;
               } else {
                  return -1;
               }
            } else if (var3 instanceof boc) {
               return -1;
            } else if (var3 instanceof bon) {
               IJavaPredicate var11 = ((bon)var3).pC();
               if (var11.isLitteralTrue()) {
                  return var1 + 1;
               } else {
                  return var11.isLitteralFalse() ? this.pC(var1, boo.class, var4) : -1;
               }
            } else if (var3 instanceof bnr) {
               IJavaPredicate var10 = ((bnr)var3).pC();
               if (var10.isLitteralTrue()) {
                  return this.A(var1, bnq.class, var4);
               } else {
                  return var10.isLitteralFalse() ? var1 + 1 : -1;
               }
            } else if (var3 instanceof bns) {
               IJavaPredicate var9 = ((bns)var3).pC();
               if (var9.isLitteralTrue()) {
                  return var1 + 1;
               } else {
                  return var9.isLitteralFalse() ? this.pC(var1, bnv.class, var4) : -1;
               }
            } else if (var3 instanceof bnt) {
               return -1;
            } else if (var3 instanceof bog) {
               return var1 + 1;
            } else if (var3 instanceof boi) {
               return var1 + 1;
            } else {
               if (var3 instanceof bnp) {
                  if (var1 + 1 >= this.A.size()) {
                     return 0;
                  }

                  IJavaStatement var5 = (IJavaStatement)this.A.get(var1 + 1);
                  if (var5 instanceof bno || var5 instanceof bnp) {
                     return var1 + 1;
                  }

                  if (!(var5 instanceof bop)) {
                     return var1 + 1;
                  }

                  if (((bop)var5).sY() == bop.Sv.pC) {
                     return var1 + 1;
                  }

                  int var6 = this.A(var1, bno.class, var4);
                  if (var6 > 0) {
                     IJavaStatement var7 = (IJavaStatement)this.A.get(var6 - 1);
                     if (var7 instanceof bon || var7 instanceof bns || var7 instanceof bnt) {
                        return var6 - 1;
                     }

                     if (var7 instanceof bnq) {
                        return var1 + 1;
                     }

                     if (var7 instanceof bog) {
                        return var1 + 1;
                     }

                     if (var7 instanceof bnw || var7 instanceof bnx || var7 instanceof bny) {
                        return this.pC(var1, bnz.class, ((boa.Av)this.kS.get(var6 - 1)).pC);
                     }

                     if (var7 instanceof boc) {
                        throw new RuntimeException("Should not happen");
                     }

                     if (!(var7 instanceof bod) && !(var7 instanceof boe)) {
                        if (!(var7 instanceof boi) && !(var7 instanceof boj) && !(var7 instanceof bom)) {
                           return -1;
                        }

                        if (var7 instanceof bom) {
                           return -1;
                        }

                        return this.pC(var1, bok.class, var4 - 1);
                     }

                     if (var5 instanceof bof) {
                        return var1 + 1;
                     }

                     if (var1 + 3 < this.A.size()) {
                        IJavaStatement var8 = (IJavaStatement)this.A.get(var1 + 2);
                        if ((var8 instanceof bod || var8 instanceof boe) && this.A.get(var1 + 3) instanceof bno) {
                           return var1 + 1 + 2;
                        }
                     }

                     return -1;
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
         if (var2.isAssignableFrom(((IJavaStatement)this.A.get(var1)).getClass()) && ((boa.Av)this.kS.get(var1)).pC == var3) {
            return var1;
         }

         var1++;
      }

      throw new RuntimeException("Pseudo-statement was not found");
   }

   int A(int var1, Class var2, int var3) {
      while (var1 >= 0) {
         if (var2.isAssignableFrom(((IJavaStatement)this.A.get(var1)).getClass()) && ((boa.Av)this.kS.get(var1)).pC == var3) {
            return var1;
         }

         var1--;
      }

      throw new RuntimeException("Pseudo-statement was not found (backward)");
   }

   int pC(int var1, bop.Av var2, bop.Sv var3, int var4) {
      while (var1 < this.A.size()) {
         IJavaStatement var5 = (IJavaStatement)this.A.get(var1);
         if (var5 instanceof bop && ((bop)var5).E() == var2 && ((bop)var5).sY() == var3 && ((boa.Av)this.kS.get(var1)).pC == var4) {
            return var1;
         }

         var1++;
      }

      throw new RuntimeException("Pseudo-statement was not found");
   }

   int A(int var1, bop.Av var2, bop.Sv var3, int var4) {
      while (var1 >= 0) {
         IJavaStatement var5 = (IJavaStatement)this.A.get(var1);
         if (var5 instanceof bop && ((bop)var5).E() == var2 && ((bop)var5).sY() == var3 && ((boa.Av)this.kS.get(var1)).pC == var4) {
            return var1;
         }

         var1--;
      }

      throw new RuntimeException("Pseudo-statement was not found (backward)");
   }

   public int UT(int var1) {
      IJavaStatement var2 = (IJavaStatement)this.A.get(var1);
      int var3 = ((boa.Av)this.kS.get(var1)).pC;
      if (!(var2 instanceof bop)) {
         return -1;
      } else {
         bop.Av var4 = ((bop)var2).E();
         bop.Sv var5 = ((bop)var2).sY();
         if (var5 == bop.Sv.pC) {
            return this.pC(var1, var4, bop.Sv.kS, var3);
         } else {
            return var5 == bop.Sv.kS ? this.A(var1, var4, bop.Sv.pC, var3) : -1;
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
         if (((boa.Av)this.kS.get(var6)).pC <= var2) {
            IJavaStatement var7 = (IJavaStatement)this.A.get(var6);
            if (var1
               ? var7 instanceof bof || var7 instanceof boo || var7 instanceof bnr || var7 instanceof bnv || var7 instanceof bnu
               : var7 instanceof bon || var7 instanceof bnq || var7 instanceof bns || var7 instanceof bnt) {
               break;
            }
         }
      }

      return var6 == var5 ? -1 : var6;
   }

   public IJavaStatement pC(IJavaStatement var1) {
      bop.Av var2;
      if (var1 instanceof IJavaWhile) {
         var2 = bop.Av.wS;
      } else if (var1 instanceof IJavaDoWhile) {
         var2 = bop.Av.kS;
      } else if (var1 instanceof IJavaFor) {
         var2 = bop.Av.UT;
      } else {
         if (!(var1 instanceof IJavaForEach)) {
            return null;
         }

         var2 = bop.Av.E;
      }

      int var3 = 0;
      int var4 = -1;

      label97:
      for (IJavaStatement var6 : this.A) {
         if (var6 instanceof bop && ((bop)var6).E() == var2 && ((bop)var6).sY() == bop.Sv.pC && ((bop)var6).ys == var1) {
            var4 = var3 + 1;

            while (true) {
               IJavaStatement var7 = (IJavaStatement)this.A.get(var4);
               if (var7 instanceof bop
                  && ((bop)var7).E() == var2
                  && ((bop)var7).sY() == bop.Sv.kS
                  && ((boa.Av)this.kS.get(var4)).pC == ((boa.Av)this.kS.get(var3)).pC) {
                  break label97;
               }

               var4++;
            }
         }

         var3++;
      }

      if (var4 >= 0 && var4 < this.A.size()) {
         int var9 = var4;

         while (true) {
            var9 = this.pC(var9, false, true, 1, null);
            if (var9 <= 0) {
               return null;
            }

            IJavaStatement var10 = (IJavaStatement)this.A.get(var9);
            if (var10 instanceof bnq || var10 instanceof boi) {
               var9++;
            } else if (!(var10 instanceof bon) || !((IJavaWhile)((bon)var10).ys).getPredicate().isLitteralTrue()) {
               if (!(var10 instanceof bnw) || !((IJavaIf)((bnw)var10).ys).getBranchPredicate(0).isLitteralTrue()) {
                  return var10 instanceof bop ? null : var10;
               }

               var9++;
            } else {
               var9++;
            }
         }
      } else {
         Object[] var10000 = new Object[]{var1, pC(this.A, this.kS)};
         throw new RuntimeException();
      }
   }

   public static String A(List var0) {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (IJavaStatement var4 : var0) {
         Strings.ff(var1, "%04d: %s\n", var2, var4);
         var2++;
      }

      return var1.toString();
   }

   public static String pC(List var0, List var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (IJavaStatement var5 : var0) {
         boa.Av var6 = (boa.Av)var1.get(var3);
         Strings.ff(var2, "%04d|%d|%d: %s%s\n", var3, var6.A(), var6.kS().size(), Strings.generate(' ', 2 * var6.A()), var5);
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

      public boa.Av pC() {
         return new boa.Av(this.pC, this.A == null ? null : new ArrayList(this.A));
      }

      public int A() {
         return this.pC;
      }

      public List kS() {
         if (this.A == null) {
            throw new IllegalStateException();
         } else {
            return this.A;
         }
      }
   }
}
