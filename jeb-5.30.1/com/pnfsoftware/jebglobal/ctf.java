package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.format.Strings;

public class ctf {
   private static String[] q = new String[]{"al", "cl", "dl", "bl", "spl", "bpl", "sil", "dil", "r8b", "r9b", "r10b", "r11b", "r12b", "r13b", "r14b", "r15b"};
   private static String[] RF = new String[]{"ah", "ch", "dh", "bh"};
   private static String[] xK = new String[]{"ax", "cx", "dx", "bx", "sp", "bp", "si", "di", "r8w", "r9w", "r10w", "r11w", "r12w", "r13w", "r14w", "r15w"};
   private static String[] Dw = new String[]{
      "eax", "ecx", "edx", "ebx", "esp", "ebp", "esi", "edi", "r8d", "r9d", "r10d", "r11d", "r12d", "r13d", "r14d", "r15d"
   };
   private static String[] Uv = new String[]{"rax", "rcx", "rdx", "rbx", "rsp", "rbp", "rsi", "rdi", "r8", "r9", "r10", "r11", "r12", "r13", "r14", "r15"};
   private static String[] oW = new String[]{"es", "cs", "ss", "ds", "fs", "gs"};

   public static long q(int var0, int var1, int var2, int var3) {
      return RegisterUtil.createRegisterId(var0, var1, var2, var3);
   }

   public static long q(int var0, int var1, int var2) {
      return q(var0, var1, var2, 0);
   }

   public static int q(long var0) {
      return RegisterUtil.getRegisterIndex(var0);
   }

   public static int RF(long var0) {
      return RegisterUtil.getRegisterGroup(var0);
   }

   public static int xK(long var0) {
      return RegisterUtil.getRegisterBitsize(var0);
   }

   public static int Dw(long var0) {
      return RegisterUtil.getRegisterBitstart(var0);
   }

   public static boolean q(long var0, long var2) {
      if (RF(var0) > 1 || RF(var2) > 1) {
         return false;
      } else if (q(var0) != q(var2)) {
         return false;
      } else {
         int var4 = xK(var0);
         int var5 = xK(var2);
         return var4 == 8 && var5 == 8 ? Dw(var0) == Dw(var2) : var4 <= var5;
      }
   }

   public static String Uv(long var0) {
      int var2 = q(var0);
      int var3 = RF(var0);
      int var4 = xK(var0);
      int var5 = Dw(var0);
      return RF(var2, var3, var4, var5);
   }

   public static String RF(int var0, int var1, int var2, int var3) {
      try {
         return xK(var0, var1, var2, var3);
      } catch (RuntimeException var4) {
         throw new IllegalArgumentException(Strings.ff("Illegal x86 register specification (%d,%d,%d)", var1, var2, var0));
      }
   }

   private static String xK(int var0, int var1, int var2, int var3) {
      switch (var1) {
         case 0:
            if (var3 == 0) {
               switch (var2) {
                  case 8:
                     return q[var0];
                  case 16:
                     return xK[var0];
                  case 32:
                     return Dw[var0];
                  case 64:
                     return Uv[var0];
                  default:
                     throw new RuntimeException();
               }
            } else {
               if (var3 == 8) {
                  switch (var2) {
                     case 8:
                        return RF[var0];
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
                  return oW[var0];
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

   static long q(ctc var0, cqj var1) {
      int var2 = 0;

      for (cqj var6 : var0.HF) {
         if (var6 == var1) {
            break;
         }

         var2++;
      }

      return var0.q(var2);
   }

   static CharSequence q(ctc var0, cqj var1, CharSequence var2) {
      if (var1.q()) {
         long var3 = q(var0, var1);
         String var5 = Uv(var3);
         var2 = var5 + ":" + var2;
      }

      return (CharSequence)var2;
   }

   static String RF(ctc var0, cqj var1) {
      if (var0 != null && var0.gO() && var1 == var0.HF[0]) {
         StringBuilder var2 = new StringBuilder();
         int var3 = var0.gP();
         if (var3 != 0) {
            Strings.ff(var2, " {k%d}", var3);
         }

         if (var0.nf()) {
            var2.append(" {z}");
         }

         return var2.toString();
      } else {
         return null;
      }
   }
}
