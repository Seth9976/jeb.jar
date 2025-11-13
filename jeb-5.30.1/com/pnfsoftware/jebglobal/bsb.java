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

public class bsb {
   private static final ILogger q = GlobalLog.getLogger(bsb.class);
   private List RF;
   private List xK;
   private int Dw;
   private List Uv = new ArrayList();

   public bsb(IJavaMethod var1) {
      this(var1.toFlatList());
   }

   public bsb(List var1) {
      this.RF = var1;
      this.xK = this.q(var1);
   }

   public int q() {
      return this.RF.size();
   }

   public void q(int var1) {
      this.RF.remove(var1);
      this.xK.remove(var1);
   }

   public void q(int var1, int var2) {
      if (var1 != var2) {
         if (var1 < var2) {
            var2--;
         }

         IJavaStatement var3 = (IJavaStatement)this.RF.remove(var1);
         this.RF.add(var2, var3);
         this.xK.remove(var1);
         this.xK.add(var2, ((bsb.eo)this.xK.get(var2)).q());
      }
   }

   public List q(List var1) {
      ArrayList var2 = new ArrayList(var1.size());
      int var3 = 0;
      int var5 = 0;
      ArrayList var6 = new ArrayList();

      for (IJavaStatement var8 : var1) {
         if (var8 instanceof brp) {
            var3++;
         } else if (var8 instanceof bsm) {
            var6 = new ArrayList(var6);
            var6.remove(var6.size() - 1);
            if (!var6.isEmpty()) {
               var6.get(var6.size() - 1);
            }
         }

         var2.add(new bsb.eo(var3, var6));
         if (var8 instanceof brq) {
            var3--;
         } else if (var8 instanceof bsj) {
            var6 = new ArrayList(var6);
            int var4 = var5++;
            var6.add(var4);
         }
      }

      return var2;
   }

   public List RF() {
      return this.RF;
   }

   public IJavaStatement RF(int var1) {
      return (IJavaStatement)this.RF.get(var1);
   }

   public List xK() {
      return this.xK;
   }

   public List Dw() {
      return this.Uv;
   }

   public void xK(int var1) {
      this.Dw = var1;
   }

   public int Uv() {
      return this.Dw;
   }

   public IJavaStatement oW() {
      int var1 = this.q(this.Dw, false, false, 1, null);
      if (var1 <= 0) {
         return null;
      } else {
         this.Dw = var1;
         return (IJavaStatement)this.RF.get(this.Dw);
      }
   }

   public int Dw(int var1) {
      return this.q(var1, false, false, 1, null);
   }

   public int q(int var1, boolean var2, boolean var3, int var4, Collection var5) {
      this.Uv.clear();
      if (var1 < 0) {
         throw new IllegalArgumentException();
      } else if (var1 >= this.RF.size()) {
         return 0;
      } else {
         int var6 = var1;

         while (true) {
            if (var3) {
               this.Uv.add(var6);
               var6++;
               var3 = false;
            } else {
               var6 = this.q(var6, var2);
            }

            if (var6 > 0 && var6 < this.RF.size()) {
               if (this.Uv.contains(var6)) {
                  return var6;
               }

               this.Uv.add(var6);
               if (var4 >= 1) {
                  IJavaStatement var7 = (IJavaStatement)this.RF.get(var6);
                  if (var7 instanceof brq
                     || var7 instanceof bsp
                     || var7 instanceof brw
                     || var7 instanceof brv
                     || var7 instanceof bsa
                     || var7 instanceof bsg
                     || var7 instanceof bsl
                     || var7 instanceof bsi
                     || var4 >= 2 && (var5 == null || !ReflectionHelper.isInstance(var7, var5))) {
                     continue;
                  }
               }

               return var6;
            }

            if (!this.Uv.isEmpty()) {
               return (Integer)this.Uv.get(this.Uv.size() - 1);
            }

            return var6;
         }
      }
   }

   public int q(int var1, boolean var2) {
      if (var1 >= this.RF.size()) {
         return 0;
      } else {
         IJavaStatement var3 = (IJavaStatement)this.RF.get(var1);
         if (var3 instanceof bsc) {
            return var1 + 1;
         } else if (var3 instanceof brp) {
            return var1 + 1;
         } else if (!(var3 instanceof bsp)
            && !(var3 instanceof brr)
            && !(var3 instanceof brw)
            && !(var3 instanceof brv)
            && !(var3 instanceof brz)
            && !(var3 instanceof bsa)
            && !(var3 instanceof bsg)
            && !(var3 instanceof bsl)
            && !(var3 instanceof bsi)) {
            int var4 = ((bsb.eo)this.xK.get(var1)).q;
            if (!(var3 instanceof bsq)) {
               if (!(var3 instanceof IJavaReturn) && !(var3 instanceof IJavaThrow)) {
                  if (var3 instanceof IJavaLabel) {
                     return var1 + 1;
                  } else if (var3 instanceof IJavaBreak) {
                     IJavaLabel var15 = ((IJavaBreak)var3).getLabel();
                     return var15 != null ? -1 : this.q(true, var4 - 1, var1) + 1;
                  } else if (var3 instanceof IJavaContinue) {
                     IJavaLabel var14 = ((IJavaContinue)var3).getLabel();
                     return var14 != null ? -1 : this.q(false, var4 - 1, var1);
                  } else if (var3 instanceof IJavaGoto) {
                     IJavaLabel var13 = ((IJavaGoto)var3).getLabel();

                     for (int var17 = 0; var17 < this.RF.size(); var17++) {
                        if (this.RF.get(var17) == var13) {
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
            } else if (var3 instanceof brx || var3 instanceof bry) {
               IJavaPredicate var12 = ((bro)var3).q();
               if (var12.isLitteralTrue()) {
                  return var1 + 1;
               } else if (var12.isLitteralFalse()) {
                  int var16 = this.q(var1 + 1, brq.class, ((bsb.eo)this.xK.get(var1 + 1)).q);
                  return var16 + 1;
               } else {
                  return -1;
               }
            } else if (var3 instanceof bsd) {
               return -1;
            } else if (var3 instanceof bso) {
               IJavaPredicate var11 = ((bso)var3).q();
               if (var11.isLitteralTrue()) {
                  return var1 + 1;
               } else {
                  return var11.isLitteralFalse() ? this.q(var1, bsp.class, var4) : -1;
               }
            } else if (var3 instanceof brs) {
               IJavaPredicate var10 = ((brs)var3).q();
               if (var10.isLitteralTrue()) {
                  return this.RF(var1, brr.class, var4);
               } else {
                  return var10.isLitteralFalse() ? var1 + 1 : -1;
               }
            } else if (var3 instanceof brt) {
               IJavaPredicate var9 = ((brt)var3).q();
               if (var9.isLitteralTrue()) {
                  return var1 + 1;
               } else {
                  return var9.isLitteralFalse() ? this.q(var1, brw.class, var4) : -1;
               }
            } else if (var3 instanceof bru) {
               return -1;
            } else if (var3 instanceof bsh) {
               return var1 + 1;
            } else if (var3 instanceof bsj) {
               return var1 + 1;
            } else {
               if (var3 instanceof brq) {
                  if (var1 + 1 >= this.RF.size()) {
                     return 0;
                  }

                  IJavaStatement var5 = (IJavaStatement)this.RF.get(var1 + 1);
                  if (var5 instanceof brp || var5 instanceof brq) {
                     return var1 + 1;
                  }

                  if (!(var5 instanceof bsq)) {
                     return var1 + 1;
                  }

                  if (((bsq)var5).za() == bsq.CU.q) {
                     return var1 + 1;
                  }

                  int var6 = this.RF(var1, brp.class, var4);
                  if (var6 > 0) {
                     IJavaStatement var7 = (IJavaStatement)this.RF.get(var6 - 1);
                     if (var7 instanceof bso || var7 instanceof brt || var7 instanceof bru) {
                        return var6 - 1;
                     }

                     if (var7 instanceof brr) {
                        return var1 + 1;
                     }

                     if (var7 instanceof bsh) {
                        return var1 + 1;
                     }

                     if (var7 instanceof brx || var7 instanceof bry || var7 instanceof brz) {
                        return this.q(var1, bsa.class, ((bsb.eo)this.xK.get(var6 - 1)).q);
                     }

                     if (var7 instanceof bsd) {
                        throw new RuntimeException("Should not happen");
                     }

                     if (!(var7 instanceof bse) && !(var7 instanceof bsf)) {
                        if (!(var7 instanceof bsj) && !(var7 instanceof bsk) && !(var7 instanceof bsn)) {
                           return -1;
                        }

                        if (var7 instanceof bsn) {
                           return -1;
                        }

                        return this.q(var1, bsl.class, var4 - 1);
                     }

                     if (var5 instanceof bsg) {
                        return var1 + 1;
                     }

                     if (var1 + 3 < this.RF.size()) {
                        IJavaStatement var8 = (IJavaStatement)this.RF.get(var1 + 2);
                        if ((var8 instanceof bse || var8 instanceof bsf) && this.RF.get(var1 + 3) instanceof brp) {
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

   int q(int var1, List var2, int var3) {
      while (var1 >= 0) {
         if (this.RF.get(var1) instanceof brp && (Integer)var2.get(var1) == var3) {
            return var1;
         }

         var1--;
      }

      throw new RuntimeException("Block begin not found");
   }

   int q(int var1, Class var2, int var3) {
      while (var1 < this.RF.size()) {
         if (var2.isAssignableFrom(((IJavaStatement)this.RF.get(var1)).getClass()) && ((bsb.eo)this.xK.get(var1)).q == var3) {
            return var1;
         }

         var1++;
      }

      throw new RuntimeException("Pseudo-statement was not found");
   }

   int RF(int var1, Class var2, int var3) {
      while (var1 >= 0) {
         if (var2.isAssignableFrom(((IJavaStatement)this.RF.get(var1)).getClass()) && ((bsb.eo)this.xK.get(var1)).q == var3) {
            return var1;
         }

         var1--;
      }

      throw new RuntimeException("Pseudo-statement was not found (backward)");
   }

   int q(int var1, bsq.eo var2, bsq.CU var3, int var4) {
      while (var1 < this.RF.size()) {
         IJavaStatement var5 = (IJavaStatement)this.RF.get(var1);
         if (var5 instanceof bsq && ((bsq)var5).gP() == var2 && ((bsq)var5).za() == var3 && ((bsb.eo)this.xK.get(var1)).q == var4) {
            return var1;
         }

         var1++;
      }

      throw new RuntimeException("Pseudo-statement was not found");
   }

   int RF(int var1, bsq.eo var2, bsq.CU var3, int var4) {
      while (var1 >= 0) {
         IJavaStatement var5 = (IJavaStatement)this.RF.get(var1);
         if (var5 instanceof bsq && ((bsq)var5).gP() == var2 && ((bsq)var5).za() == var3 && ((bsb.eo)this.xK.get(var1)).q == var4) {
            return var1;
         }

         var1--;
      }

      throw new RuntimeException("Pseudo-statement was not found (backward)");
   }

   public int Uv(int var1) {
      IJavaStatement var2 = (IJavaStatement)this.RF.get(var1);
      int var3 = ((bsb.eo)this.xK.get(var1)).q;
      if (!(var2 instanceof bsq)) {
         return -1;
      } else {
         bsq.eo var4 = ((bsq)var2).gP();
         bsq.CU var5 = ((bsq)var2).za();
         if (var5 == bsq.CU.q) {
            return this.q(var1, var4, bsq.CU.xK, var3);
         } else {
            return var5 == bsq.CU.xK ? this.RF(var1, var4, bsq.CU.q, var3) : -1;
         }
      }
   }

   int q(boolean var1, int var2, int var3) {
      byte var4;
      int var5;
      if (var1) {
         var4 = 1;
         var5 = this.RF.size() - 1;
      } else {
         var4 = -1;
         var5 = 0;
      }

      int var6;
      for (var6 = var3 + var4; var6 != var5; var6 += var4) {
         if (((bsb.eo)this.xK.get(var6)).q <= var2) {
            IJavaStatement var7 = (IJavaStatement)this.RF.get(var6);
            if (var1
               ? var7 instanceof bsg || var7 instanceof bsp || var7 instanceof brs || var7 instanceof brw || var7 instanceof brv
               : var7 instanceof bso || var7 instanceof brr || var7 instanceof brt || var7 instanceof bru) {
               break;
            }
         }
      }

      return var6 == var5 ? -1 : var6;
   }

   public IJavaStatement q(IJavaStatement var1) {
      bsq.eo var2;
      if (var1 instanceof IJavaWhile) {
         var2 = bsq.eo.Dw;
      } else if (var1 instanceof IJavaDoWhile) {
         var2 = bsq.eo.xK;
      } else if (var1 instanceof IJavaFor) {
         var2 = bsq.eo.Uv;
      } else {
         if (!(var1 instanceof IJavaForEach)) {
            return null;
         }

         var2 = bsq.eo.oW;
      }

      int var3 = 0;
      int var4 = -1;

      label97:
      for (IJavaStatement var6 : this.RF) {
         if (var6 instanceof bsq && ((bsq)var6).gP() == var2 && ((bsq)var6).za() == bsq.CU.q && ((bsq)var6).za == var1) {
            var4 = var3 + 1;

            while (true) {
               IJavaStatement var7 = (IJavaStatement)this.RF.get(var4);
               if (var7 instanceof bsq
                  && ((bsq)var7).gP() == var2
                  && ((bsq)var7).za() == bsq.CU.xK
                  && ((bsb.eo)this.xK.get(var4)).q == ((bsb.eo)this.xK.get(var3)).q) {
                  break label97;
               }

               var4++;
            }
         }

         var3++;
      }

      if (var4 >= 0 && var4 < this.RF.size()) {
         int var9 = var4;

         while (true) {
            var9 = this.q(var9, false, true, 1, null);
            if (var9 <= 0) {
               return null;
            }

            IJavaStatement var10 = (IJavaStatement)this.RF.get(var9);
            if (var10 instanceof brr || var10 instanceof bsj) {
               var9++;
            } else if (!(var10 instanceof bso) || !((IJavaWhile)((bso)var10).za).getPredicate().isLitteralTrue()) {
               if (!(var10 instanceof brx) || !((IJavaIf)((brx)var10).za).getBranchPredicate(0).isLitteralTrue()) {
                  return var10 instanceof bsq ? null : var10;
               }

               var9++;
            } else {
               var9++;
            }
         }
      } else {
         Object[] var10000 = new Object[]{var1, q(this.RF, this.xK)};
         throw new RuntimeException();
      }
   }

   public static List RF(List var0) {
      ArrayList var1 = new ArrayList();
      int var2 = 0;

      for (IJavaStatement var4 : var0) {
         if (var4 instanceof brp) {
            var1.add(++var2);
         } else if (var4 instanceof brq) {
            var1.add(var2);
            var2--;
         } else {
            var1.add(var2);
         }
      }

      return var1;
   }

   public static String xK(List var0) {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (IJavaStatement var4 : var0) {
         Strings.ff(var1, "%04d: %s\n", var2, var4);
         var2++;
      }

      return var1.toString();
   }

   public static String q(List var0, List var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (IJavaStatement var5 : var0) {
         bsb.eo var6 = (bsb.eo)var1.get(var3);
         Strings.ff(var2, "%04d|%d|%d: %s%s\n", var3, var6.RF(), var6.Dw().size(), Strings.generate(' ', 2 * var6.RF()), var5);
         var3++;
      }

      return var2.toString();
   }

   @Override
   public String toString() {
      return q(this.RF, this.xK);
   }

   public void gO() {
      IJavaStatement var1 = (IJavaStatement)this.RF.get(0);
      int[] var2 = new int[1];
      ((brp)var1).q(this.RF, 0, var2);
      if (var2[0] != this.RF.size()) {
         throw new RuntimeException();
      }
   }

   public static class eo {
      int q;
      List RF;

      public eo(int var1, List var2) {
         this.q = var1;
         this.RF = var2;
      }

      public eo(int var1) {
         this(var1, null);
      }

      public bsb.eo q() {
         return new bsb.eo(this.q, this.RF == null ? null : new ArrayList(this.RF));
      }

      public int RF() {
         return this.q;
      }

      public boolean xK() {
         if (this.RF == null) {
            throw new IllegalStateException();
         } else {
            return !this.RF.isEmpty();
         }
      }

      public List Dw() {
         if (this.RF == null) {
            throw new IllegalStateException();
         } else {
            return this.RF;
         }
      }
   }
}
