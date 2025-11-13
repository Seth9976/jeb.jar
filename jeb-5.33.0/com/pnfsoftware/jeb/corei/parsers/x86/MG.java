package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.format.Strings;

public class MG {
   private static String[] pC = new String[]{"al", "cl", "dl", "bl", "spl", "bpl", "sil", "dil", "r8b", "r9b", "r10b", "r11b", "r12b", "r13b", "r14b", "r15b"};
   private static String[] A = new String[]{"ah", "ch", "dh", "bh"};
   private static String[] kS = new String[]{"ax", "cx", "dx", "bx", "sp", "bp", "si", "di", "r8w", "r9w", "r10w", "r11w", "r12w", "r13w", "r14w", "r15w"};
   private static String[] wS = new String[]{
      "eax", "ecx", "edx", "ebx", "esp", "ebp", "esi", "edi", "r8d", "r9d", "r10d", "r11d", "r12d", "r13d", "r14d", "r15d"
   };
   private static String[] UT = new String[]{"rax", "rcx", "rdx", "rbx", "rsp", "rbp", "rsi", "rdi", "r8", "r9", "r10", "r11", "r12", "r13", "r14", "r15"};
   private static String[] E = new String[]{"es", "cs", "ss", "ds", "fs", "gs"};

   public static long pC(int var0, int var1, int var2, int var3) {
      return RegisterUtil.createRegisterId(var0, var1, var2, var3);
   }

   public static long pC(int var0, int var1, int var2) {
      return pC(var0, var1, var2, 0);
   }

   public static int pC(long var0) {
      return RegisterUtil.getRegisterIndex(var0);
   }

   public static int A(long var0) {
      return RegisterUtil.getRegisterGroup(var0);
   }

   public static int kS(long var0) {
      return RegisterUtil.getRegisterBitsize(var0);
   }

   public static int wS(long var0) {
      return RegisterUtil.getRegisterBitstart(var0);
   }

   public static boolean pC(long var0, long var2) {
      if (A(var0) > 1 || A(var2) > 1) {
         return false;
      } else if (pC(var0) != pC(var2)) {
         return false;
      } else {
         int var4 = kS(var0);
         int var5 = kS(var2);
         return var4 == 8 && var5 == 8 ? wS(var0) == wS(var2) : var4 <= var5;
      }
   }

   public static String UT(long var0) {
      int var2 = pC(var0);
      int var3 = A(var0);
      int var4 = kS(var0);
      int var5 = wS(var0);
      return A(var2, var3, var4, var5);
   }

   public static String A(int var0, int var1, int var2, int var3) {
      try {
         return kS(var0, var1, var2, var3);
      } catch (RuntimeException var4) {
         throw new IllegalArgumentException(Strings.ff("Illegal x86 register specification (%d,%d,%d)", var1, var2, var0));
      }
   }

   private static String kS(int var0, int var1, int var2, int var3) {
      switch (var1) {
         case 0:
            if (var3 == 0) {
               switch (var2) {
                  case 8:
                     return pC[var0];
                  case 16:
                     return kS[var0];
                  case 32:
                     return wS[var0];
                  case 64:
                     return UT[var0];
                  default:
                     throw new RuntimeException();
               }
            } else {
               if (var3 == 8) {
                  switch (var2) {
                     case 8:
                        return A[var0];
                     default:
                        throw new RuntimeException();
                  }
               }

               throw new RuntimeException();
            }
         case 1:
         case 6:
         case 7:
         case 12:
         default:
            throw new RuntimeException();
         case 2:
            switch (var2) {
               case 16:
                  return E[var0];
               default:
                  throw new RuntimeException();
            }
         case 3:
            switch (var2) {
               case 80:
                  return "st(" + var0 + ")";
               default:
                  throw new RuntimeException();
            }
         case 4:
            switch (var2) {
               case 32:
               case 64:
                  return "mm" + var0;
               default:
                  throw new RuntimeException();
            }
         case 5:
            switch (var2) {
               case 32:
               case 64:
               case 128:
                  return "xmm" + var0;
               case 256:
                  return "ymm" + var0;
               case 512:
                  return "zmm" + var0;
               default:
                  throw new RuntimeException("Cannot access SIMD register of size " + var2);
            }
         case 8:
            return "cr" + var0;
         case 9:
            return "dr" + var0;
         case 10:
            switch (var2) {
               case 16:
                  return "ip";
               case 32:
                  return "eip";
               case 64:
                  return "rip";
               default:
                  throw new RuntimeException();
            }
         case 11:
            switch (var2) {
               case 16:
                  return "flags";
               case 32:
                  return "eflags";
               case 64:
                  return "rflags";
               default:
                  throw new RuntimeException();
            }
         case 13:
            return "bnd" + var0;
      }
   }

   static long pC(vh var0, Av var1) {
      int var2 = 0;

      for (Av var6 : var0.ys) {
         if (var6 == var1) {
            break;
         }

         var2++;
      }

      return var0.pC(var2);
   }

   static CharSequence pC(vh var0, Av var1, CharSequence var2) {
      if (var1.pC()) {
         long var3 = pC(var0, var1);
         String var5 = UT(var3);
         var2 = var5 + ":" + var2;
      }

      return (CharSequence)var2;
   }

   static String A(vh var0, Av var1) {
      if (var0 != null && var0.wS() && var1 == var0.ys[0]) {
         StringBuilder var2 = new StringBuilder();
         int var3 = var0.E();
         if (var3 != 0) {
            Strings.ff(var2, " {k%d}", var3);
         }

         if (var0.UT()) {
            var2.append(" {z}");
         }

         return var2.toString();
      } else {
         return null;
      }
   }
}
