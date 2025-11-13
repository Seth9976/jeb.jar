package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICContinue;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICForStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.reflect.ReflectionHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ahv {
   private static final StructuredLogger q = aeg.q(ahv.class);
   private List RF;
   private List xK;
   private List Dw = new ArrayList();

   public ahv(ICMethod var1) {
      this.RF = var1.toFlatList();
      this.xK = this.Uv();
   }

   private List Uv() {
      ArrayList var1 = new ArrayList();
      int var2 = 0;

      for (ICStatement var4 : this.RF) {
         if (var4 instanceof ahl) {
            var2++;
         }

         var1.add(new ahv.eo(var2));
         if (var4 instanceof ahm) {
            var2--;
         }
      }

      return var1;
   }

   public List q() {
      return this.RF;
   }

   public ICStatement q(int var1) {
      return (ICStatement)this.RF.get(var1);
   }

   public List RF() {
      return this.xK;
   }

   public void RF(int var1) {
      this.RF.remove(var1);
      this.xK.remove(var1);
   }

   public void q(int var1, int var2) {
      if (var1 != var2) {
         if (var1 < var2) {
            var2--;
         }

         ICStatement var3 = (ICStatement)this.RF.remove(var1);
         this.RF.add(var2, var3);
         this.xK.remove(var1);
         this.xK.add(var2, ((ahv.eo)this.xK.get(var2)).q());
      }
   }

   public List xK() {
      return this.Dw;
   }

   public int q(int var1, boolean var2, boolean var3, int var4, Collection var5) {
      this.Dw.clear();
      if (var1 < 0) {
         throw new IllegalArgumentException();
      } else if (var1 >= this.RF.size()) {
         return 0;
      } else {
         int var6 = var1;

         while (true) {
            if (var3) {
               var6++;
               var3 = false;
            } else {
               var6 = this.q(var6, var2);
            }

            if (var6 > 0 && var6 < this.RF.size()) {
               if (this.Dw.contains(var6)) {
                  return var6;
               }

               this.Dw.add(var6);
               if (var4 >= 1) {
                  ICStatement var7 = (ICStatement)this.RF.get(var6);
                  if (var7 instanceof ahm
                     || var7 instanceof aic
                     || var7 instanceof ahq
                     || var7 instanceof ahu
                     || var7 instanceof aia
                     || var4 >= 2 && (var5 == null || !ReflectionHelper.isInstance(var7, var5))) {
                     continue;
                  }
               }

               return var6;
            }

            if (!this.Dw.isEmpty()) {
               return (Integer)this.Dw.get(this.Dw.size() - 1);
            }

            return var6;
         }
      }
   }

   int q(int var1, boolean var2) {
      if (var1 >= this.RF.size()) {
         return 0;
      } else {
         ICStatement var3 = (ICStatement)this.RF.get(var1);
         if (var3 instanceof ahw) {
            return var1 + 1;
         } else if (var3 instanceof ahl) {
            return var1 + 1;
         } else if (!(var3 instanceof aic)
            && !(var3 instanceof ahn)
            && !(var3 instanceof ahq)
            && !(var3 instanceof aht)
            && !(var3 instanceof ahu)
            && !(var3 instanceof aia)) {
            int var4 = ((ahv.eo)this.xK.get(var1)).q;
            if (!(var3 instanceof aid)) {
               if (var3 instanceof ICReturn) {
                  return var2 ? -1 : 0;
               } else if (var3 instanceof ICLabel) {
                  return var1 + 1;
               } else if (var3 instanceof ICBreak) {
                  ICLabel var15 = ((ICBreak)var3).getLabel();
                  return var15 != null ? -1 : this.q(true, var4 - 1, var1) + 1;
               } else if (var3 instanceof ICContinue) {
                  ICLabel var14 = ((ICContinue)var3).getLabel();
                  return var14 != null ? -1 : this.q(false, var4 - 1, var1);
               } else if (var3 instanceof ICGoto) {
                  ICLabel var13 = ((ICGoto)var3).getLabel();

                  for (int var17 = 0; var17 < this.RF.size(); var17++) {
                     if (this.RF.get(var17) == var13) {
                        return var17;
                     }
                  }

                  return -1;
               } else {
                  return var2 ? -1 : var1 + 1;
               }
            } else if (var3 instanceof ahr || var3 instanceof ahs) {
               ICPredicate var12 = ((ahk)var3).q();
               if (var12.isLitteralTrue()) {
                  return var1 + 1;
               } else if (var12.isLitteralFalse()) {
                  int var16 = this.q(var1 + 1, ahm.class, ((ahv.eo)this.xK.get(var1 + 1)).q);
                  return var16 + 1;
               } else {
                  return -1;
               }
            } else if (var3 instanceof ahx) {
               return -1;
            } else if (var3 instanceof aib) {
               ICPredicate var11 = ((aib)var3).q();
               if (var11.isLitteralTrue()) {
                  return var1 + 1;
               } else {
                  return var11.isLitteralFalse() ? this.q(var1, aic.class, var4) : -1;
               }
            } else if (var3 instanceof aho) {
               ICPredicate var10 = ((aho)var3).q();
               if (var10.isLitteralTrue()) {
                  return this.RF(var1, ahn.class, var4);
               } else {
                  return var10.isLitteralFalse() ? var1 + 1 : -1;
               }
            } else if (var3 instanceof ahp) {
               ICPredicate var9 = ((ahp)var3).q();
               if (var9.isLitteralTrue()) {
                  return var1 + 1;
               } else {
                  return var9.isLitteralFalse() ? this.q(var1, ahq.class, var4) : -1;
               }
            } else {
               if (var3 instanceof ahm) {
                  if (var1 + 1 >= this.RF.size()) {
                     return 0;
                  }

                  ICStatement var5 = (ICStatement)this.RF.get(var1 + 1);
                  if (var5 instanceof ahl || var5 instanceof ahm) {
                     return var1 + 1;
                  }

                  if (!(var5 instanceof aid)) {
                     return var1 + 1;
                  }

                  if (((aid)var5).Uv() == aid.CU.q) {
                     return var1 + 1;
                  }

                  int var6 = this.RF(var1, ahl.class, var4);
                  if (var6 > 0) {
                     ICStatement var7 = (ICStatement)this.RF.get(var6 - 1);
                     if (var7 instanceof aib || var7 instanceof ahp) {
                        return var6 - 1;
                     }

                     if (var7 instanceof ahn) {
                        return var1 + 1;
                     }

                     if (var7 instanceof ahr || var7 instanceof ahs || var7 instanceof aht) {
                        return this.q(var1, ahu.class, ((ahv.eo)this.xK.get(var6 - 1)).q);
                     }

                     if (var7 instanceof ahx) {
                        throw new RuntimeException("Should not happen");
                     }

                     if (var7 instanceof ahy || var7 instanceof ahz) {
                        if (var5 instanceof aia) {
                           return var1 + 1;
                        }

                        if (var1 + 3 < this.RF.size()) {
                           ICStatement var8 = (ICStatement)this.RF.get(var1 + 2);
                           if ((var8 instanceof ahy || var8 instanceof ahz) && this.RF.get(var1 + 3) instanceof ahl) {
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

   int q(int var1, List var2, int var3) {
      while (var1 >= 0) {
         if (this.RF.get(var1) instanceof ahl && (Integer)var2.get(var1) == var3) {
            return var1;
         }

         var1--;
      }

      throw new RuntimeException("Block begin not found");
   }

   int q(int var1, Class var2, int var3) {
      while (var1 < this.RF.size()) {
         if (var2.isAssignableFrom(((ICStatement)this.RF.get(var1)).getClass()) && ((ahv.eo)this.xK.get(var1)).q == var3) {
            return var1;
         }

         var1++;
      }

      throw new RuntimeException("Pseudo-statement was not found");
   }

   int RF(int var1, Class var2, int var3) {
      while (var1 >= 0) {
         if (var2.isAssignableFrom(((ICStatement)this.RF.get(var1)).getClass()) && ((ahv.eo)this.xK.get(var1)).q == var3) {
            return var1;
         }

         var1--;
      }

      throw new RuntimeException("Pseudo-statement was not found (backward)");
   }

   int q(int var1, aid.eo var2, aid.CU var3, int var4) {
      while (var1 < this.RF.size()) {
         ICStatement var5 = (ICStatement)this.RF.get(var1);
         if (var5 instanceof aid && ((aid)var5).Dw() == var2 && ((aid)var5).Uv() == var3 && ((ahv.eo)this.xK.get(var1)).q == var4) {
            return var1;
         }

         var1++;
      }

      throw new RuntimeException("Pseudo-statement was not found");
   }

   int RF(int var1, aid.eo var2, aid.CU var3, int var4) {
      while (var1 >= 0) {
         ICStatement var5 = (ICStatement)this.RF.get(var1);
         if (var5 instanceof aid && ((aid)var5).Dw() == var2 && ((aid)var5).Uv() == var3 && ((ahv.eo)this.xK.get(var1)).q == var4) {
            return var1;
         }

         var1--;
      }

      throw new RuntimeException("Pseudo-statement was not found (backward)");
   }

   public int xK(int var1) {
      ICStatement var2 = (ICStatement)this.RF.get(var1);
      int var3 = ((ahv.eo)this.xK.get(var1)).q;
      if (!(var2 instanceof aid)) {
         return -1;
      } else {
         aid.eo var4 = ((aid)var2).Dw();
         aid.CU var5 = ((aid)var2).Uv();
         if (var5 == aid.CU.q) {
            return this.q(var1, var4, aid.CU.xK, var3);
         } else {
            return var5 == aid.CU.xK ? this.RF(var1, var4, aid.CU.q, var3) : -1;
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
         if (((ahv.eo)this.xK.get(var6)).q <= var2) {
            ICStatement var7 = (ICStatement)this.RF.get(var6);
            if (var1
               ? var7 instanceof aia || var7 instanceof aic || var7 instanceof aho || var7 instanceof ahq
               : var7 instanceof aib || var7 instanceof ahn || var7 instanceof ahp) {
               break;
            }
         }
      }

      return var6 == var5 ? -1 : var6;
   }

   public ICStatement q(ICStatement var1) {
      aid.eo var2;
      if (var1 instanceof ICWhileStm) {
         var2 = aid.eo.Dw;
      } else if (var1 instanceof ICDoWhileStm) {
         var2 = aid.eo.xK;
      } else {
         if (!(var1 instanceof ICForStm)) {
            return null;
         }

         var2 = aid.eo.Uv;
      }

      int var3 = 0;
      int var4 = -1;

      label92:
      for (ICStatement var6 : this.RF) {
         if (var6 instanceof aid && ((aid)var6).Dw() == var2 && ((aid)var6).Uv() == aid.CU.q && ((aid)var6).oW == var1) {
            var4 = var3 + 1;

            while (true) {
               ICStatement var7 = (ICStatement)this.RF.get(var4);
               if (var7 instanceof aid
                  && ((aid)var7).Dw() == var2
                  && ((aid)var7).Uv() == aid.CU.xK
                  && ((ahv.eo)this.xK.get(var4)).q == ((ahv.eo)this.xK.get(var3)).q) {
                  break label92;
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

            ICStatement var10 = (ICStatement)this.RF.get(var9);
            if (var10 instanceof ahn) {
               var9++;
            } else if (!(var10 instanceof aib) || !((ICWhileStm)((aib)var10).oW).getPredicate().isLitteralTrue()) {
               if (!(var10 instanceof ahr) || !((ICIfStm)((ahr)var10).oW).getBranchPredicate(0).isLitteralTrue()) {
                  return var10 instanceof aid ? null : var10;
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

   public static List q(List var0) {
      ArrayList var1 = new ArrayList();
      int var2 = 0;

      for (ICStatement var4 : var0) {
         if (var4 instanceof ahl) {
            var1.add(++var2);
         } else if (var4 instanceof ahm) {
            var1.add(var2);
            var2--;
         } else {
            var1.add(var2);
         }
      }

      return var1;
   }

   public static String RF(List var0) {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (ICStatement var4 : var0) {
         Strings.ff(var1, "%04d: %s\n", var2, var4);
         var2++;
      }

      return var1.toString();
   }

   public static String q(List var0, List var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (ICStatement var5 : var0) {
         ahv.eo var6 = (ahv.eo)var1.get(var3);
         Strings.ff(var2, "%04d|%d: %s%s\n", var3, var6.RF(), Strings.generate(' ', 2 * var6.RF()), var5);
         var3++;
      }

      return var2.toString();
   }

   @Override
   public String toString() {
      return q(this.RF, this.xK);
   }

   public void Dw() {
      ICStatement var1 = (ICStatement)this.RF.get(0);
      int[] var2 = new int[1];
      ((ahl)var1).q(this.RF, 0, var2);
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

      public ahv.eo q() {
         return new ahv.eo(this.q, this.RF == null ? null : new ArrayList(this.RF));
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
