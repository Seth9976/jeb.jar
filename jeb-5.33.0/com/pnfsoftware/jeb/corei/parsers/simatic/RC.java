package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.simatic.S7;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class RC implements IInstructionOperand {
   private static final String[] pC = new String[]{"P", "I", "Q", "M", "DB", "DI", "L", "V"};
   private static final String[] A = new String[]{"P", "I", "Q", "M", "DBX", "DIX", "L", "V"};
   @SerId(1)
   private int kS;
   @SerId(2)
   private int wS;
   @SerId(3)
   private int UT;

   public RC(int var1, int var2, int var3) {
      if (var1 == 0) {
         throw new IllegalArgumentException();
      } else {
         this.kS = var1;
         this.wS = var2;
         this.UT = var3;
      }
   }

   public int pC() {
      return this.kS & 3840;
   }

   public int A() {
      return this.kS & 0xFF;
   }

   public int kS() {
      return this.wS;
   }

   public boolean wS() {
      return this.pC() == 512;
   }

   public int UT() {
      return this.UT & 0xFF;
   }

   public boolean E() {
      return this.A() == 24;
   }

   public long pC(long var1) {
      if (this.pC() == 768) {
         return var1 + 2 * this.wS;
      } else {
         return this.pC() == 1024 ? var1 + 4 * (this.wS + 1) : 0L;
      }
   }

   public int[] sY() {
      Assert.a(this.pC() == 256);
      int var1 = (this.kS & 15728640) >>> 20;
      return new int[]{this.wS, var1};
   }

   public int ys() {
      switch (this.kS & 61440) {
         case 4096:
            return 8;
         case 8192:
            return 16;
         case 12288:
            return 32;
         case 16384:
            return 1;
         default:
            return 0;
      }
   }

   @Override
   public String format(IInstruction var1, long var2) {
      int var4 = this.kS & 3840;
      if (var4 == 1280) {
         return "0";
      } else if (var4 == 1536) {
         return "1";
      } else if (var4 != 768 && var4 != 1024) {
         boolean var9 = var4 == 1792;
         StringBuilder var6 = new StringBuilder();
         String var7 = var1 == null ? null : var1.getMnemonic();
         this.pC(this.kS, var7, var9, var6);
         this.A(this.kS, var6);
         if (var4 == 512) {
            this.pC(var6);
            var6.append("[");
            if (!this.pC(this.UT, var6)) {
               this.pC(this.UT, var7, false, var6);
            }

            this.A(this.UT, var6);
            int var8 = this.ld();
            this.pC(this.wS, var8, this.kS, var6);
            var6.append("]");
         } else {
            int var10 = this.ld();
            if (var10 > 0) {
               this.pC(this.wS, var10, this.kS, var6);
            }
         }

         return var6.toString();
      } else {
         long var5 = this.pC(var2);
         return Strings.ff("0x%X", var5);
      }
   }

   private void pC(int var1, String var2, boolean var3, StringBuilder var4) {
      int var5 = var1 & 0xFF;
      switch (var5) {
         case 0:
            break;
         case 1:
            var4.append("I");
            break;
         case 2:
            var4.append("Q");
            break;
         case 3:
            var4.append("M");
            break;
         case 4:
            var4.append("DB");
            break;
         case 5:
            var4.append("DI");
            break;
         case 6:
            var4.append("L");
            break;
         case 7:
            var4.append("V");
            break;
         case 8:
            var4.append("T");
            break;
         case 9:
            var4.append("C");
            break;
         case 10:
            var4.append("FC");
            break;
         case 11:
            var4.append("FB");
            break;
         case 12:
            var4.append("SFC");
            break;
         case 13:
            var4.append("SFB");
            break;
         case 14:
         case 17:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 29:
         case 30:
         case 31:
         case 38:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 44:
         case 45:
         case 46:
         case 47:
         case 48:
         case 55:
         case 56:
         case 57:
         case 58:
         case 59:
         case 60:
         case 61:
         case 62:
         case 63:
         case 69:
         case 70:
         case 71:
         case 72:
         case 73:
         default:
            throw new RuntimeException(Strings.ff("TBI: Format for operand: 0x%X", var5));
         case 15:
            var4.append("OB");
            break;
         case 16:
            var4.append(pC(var2));
            break;
         case 24:
            var4.append("STW");
            break;
         case 25:
            var4.append("OS");
            break;
         case 26:
            var4.append("OV");
            break;
         case 27:
            var4.append("UO");
            break;
         case 28:
            var4.append("BR");
            break;
         case 32:
            var4.append("AR1");
            break;
         case 33:
            var4.append("AR2");
            break;
         case 34:
            var4.append("DBLG");
            break;
         case 35:
            var4.append("DILG");
            break;
         case 36:
            if ("OPN".equals(var2)) {
               var4.append("DB");
            } else {
               var4.append("DBNO");
            }
            break;
         case 37:
            if ("OPN".equals(var2)) {
               var4.append("DI");
            } else {
               var4.append("DINO");
            }
            break;
         case 49:
            var4.append(">0");
            break;
         case 50:
            var4.append("<0");
            break;
         case 51:
            var4.append("<>0");
            break;
         case 52:
            var4.append("==0");
            break;
         case 53:
            var4.append(">=0");
            break;
         case 54:
            var4.append("<=0");
            break;
         case 64:
            var4.append("PARAM_UNKNOWN?");
            break;
         case 65:
            var4.append("PARAM_BOOL");
            break;
         case 66:
            var4.append("PARAM_BYTE");
            break;
         case 67:
            var4.append("PARAM_WORD");
            break;
         case 68:
            var4.append("PARAM_DWORD");
            break;
         case 74:
            var4.append("PARAM_REF");
      }
   }

   private boolean pC(int var1, StringBuilder var2) {
      switch (var1 & 0xFF) {
         case 32:
            var2.append("AR1, ");
            return true;
         case 33:
            var2.append("AR2, ");
            return true;
         default:
            return false;
      }
   }

   private void A(int var1, StringBuilder var2) {
      switch (var1 & 61440) {
         case 0:
            break;
         case 4096:
            var2.append("B");
            break;
         case 8192:
            var2.append("W");
            break;
         case 12288:
            var2.append("D");
            break;
         case 16384:
            int var3 = var1 & 0xFF;
            if (var3 == 4 || var3 == 5) {
               var2.append("X");
            }
            break;
         default:
            throw new RuntimeException();
      }
   }

   public int ld() {
      return this.pC(this.kS);
   }

   private int pC(int var1) {
      switch (var1 & 983040) {
         case 65536:
         case 524288:
            return 8;
         case 131072:
         case 393216:
            return 16;
         case 196608:
            return 32;
         case 262144:
            return 7;
         default:
            return 0;
      }
   }

   private void pC(int var1, int var2, int var3, StringBuilder var4) {
      this.pC(var4);
      if (this.pC() == 1792) {
         this.ld(var1, var2, var4);
      } else {
         switch (var3 & -268435456) {
            case Integer.MIN_VALUE:
               this.E(var1, var2, var4);
               break;
            case -1879048192:
               this.gp(var1, var2, var4);
               break;
            case -1610612736:
               this.fI(var1, var2, var4);
               break;
            case -1342177280:
               this.oT(var1, var2, var4);
               break;
            case -1073741824:
               this.WR(var1, var2, var4);
               break;
            case 268435456:
               this.pC(var1, var2, var4);
               break;
            case 536870912:
               this.A(var1, var2, var4);
               break;
            case 805306368:
               this.kS(var1, var2, var4);
               break;
            case 1073741824:
               this.sY(var1, var2, var4);
               break;
            case 1342177280:
               this.ys(var1, var2, var4);
               break;
            case 1610612736:
               this.wS(var1, var2, var4);
               break;
            case 1879048192:
               this.UT(var1, var2, var4);
               break;
            default:
               var4.append(var1);
         }

         if ((var3 & 3840) == 256) {
            int var5 = (var3 & 15728640) >>> 20;
            var4.append(".").append(var5);
         }
      }
   }

   private void pC(StringBuilder var1) {
      if (var1.length() != 0) {
         char var2 = var1.charAt(var1.length() - 1);
         if (var2 != ' ' && var2 != '[' && var2 != '#') {
            var1.append(' ');
         }
      }
   }

   private void pC(int var1, int var2, StringBuilder var3) {
      Assert.a(var2 == 32);
      Strings.ff(var3, "%.6f", Float.intBitsToFloat(var1));
   }

   private void A(int var1, int var2, StringBuilder var3) {
      var3.append("2#");
      if (var1 == 0) {
         var3.append('0');
      } else {
         StringBuilder var4;
         for (var4 = new StringBuilder(); var1 != 0; var1 >>>= 1) {
            int var5 = (var1 & 1) == 0 ? 48 : 49;
            var4.insert(0, (char)var5);
         }

         var3.append((CharSequence)var4);
      }
   }

   private void kS(int var1, int var2, StringBuilder var3) {
      if (var2 == 8) {
         var3.append(MathUtil.signExtend32(var1, 8));
      } else if (var2 == 16) {
         var3.append(MathUtil.signExtend32(var1, 16));
      } else if (var2 == 32) {
         var3.append("L#");
         var3.append(var1);
      } else {
         Assert.fail();
      }
   }

   private void wS(int var1, int var2, StringBuilder var3) {
      if (var2 == 16) {
         int var4 = var1 >> 8 & 0xFF;
         int var5 = var1 & 0xFF;
         Strings.ff(var3, "B#(%d, %d)", var4, var5);
      } else if (var2 == 32) {
         int var8 = var1 >> 24 & 0xFF;
         int var9 = var1 >> 16 & 0xFF;
         int var6 = var1 >> 8 & 0xFF;
         int var7 = var1 & 0xFF;
         Strings.ff(var3, "B#(%d, %d, %d, %d)", var8, var9, var6, var7);
      } else {
         Assert.fail();
      }
   }

   private void UT(int var1, int var2, StringBuilder var3) {
      if (var2 == 32) {
         var3.append("DW");
      } else if (var2 == 16) {
         var3.append('W');
      } else if (var2 == 8) {
         var3.append('B');
      }

      Strings.ff(var3, "#16#%X", var1);
   }

   private void E(int var1, int var2, StringBuilder var3) {
      Assert.a(var2 == 16);
      int var4 = var1 >> 8 & 15;
      int var5 = var1 >> 4 & 15;
      int var6 = var1 & 15;
      if (var4 == 0) {
         if (var5 == 0) {
            Strings.ff(var3, "C#%d", var6);
         } else {
            Strings.ff(var3, "C#%d%d", var5, var6);
         }
      } else {
         Strings.ff(var3, "C#%d%d%d", var4, var5, var6);
      }
   }

   private void sY(int var1, int var2, StringBuilder var3) {
      int var4 = var1 >>> 24;
      String var5;
      if (var4 == 0) {
         var5 = "";
      } else {
         Assert.a((var4 & 128) == 128);
         int var6 = var4 & 127;
         var5 = A[var6] + " ";
      }

      int var8 = var1 >>> 3 & 2097151;
      int var7 = var1 & 7;
      Strings.ff(var3, "P#%s%d.%d", var5, var8, var7);
   }

   private void ys(int var1, int var2, StringBuilder var3) {
      if (var2 == 8) {
         char var4 = (char)(var1 & 0xFF);
         Strings.ff(var3, "'%c'", var4);
      } else if (var2 == 16) {
         char var8 = (char)(var1 & 0xFF);
         char var5 = (char)(var1 >> 8 & 0xFF);
         if (var5 == 0) {
            Strings.ff(var3, "'%c'", var8);
         } else {
            Strings.ff(var3, "'%c%s'", var5, var8);
         }
      } else {
         if (var2 != 32) {
            throw new RuntimeException();
         }

         char var9 = (char)(var1 & 0xFF);
         char var10 = (char)(var1 >> 8 & 0xFF);
         char var6 = (char)(var1 >> 16 & 0xFF);
         char var7 = (char)(var1 >> 24 & 0xFF);
         if (var10 == 0) {
            Strings.ff(var3, "'%c'", var9);
         } else if (var6 == 0) {
            Strings.ff(var3, "'%c%c'", var10, var9);
         } else if (var7 == 0) {
            Strings.ff(var3, "'%c%c%c'", var6, var10, var9);
         } else {
            Strings.ff(var3, "'%c%c%c%c'", var7, var6, var10, var9);
         }
      }
   }

   private void ld(int var1, int var2, StringBuilder var3) {
      Strings.ff(var3, "Z#%d", var1);
   }

   private void gp(int var1, int var2, StringBuilder var3) {
      Assert.a(var2 == 32);
      var3.append(S7.Time.create(var1).toString());
   }

   private void oT(int var1, int var2, StringBuilder var3) {
      var3.append(S7.TimeOfDay.create(var1));
   }

   private void fI(int var1, int var2, StringBuilder var3) {
      var3.append(S7.Date.create(var1));
   }

   private void WR(int var1, int var2, StringBuilder var3) {
      var3.append(S7.S5Time.create(var1).toString());
   }

   private static String pC(String var0) {
      if (var0 != null) {
         switch (var0) {
            case "L":
               return "PI";
            case "T":
               return "PQ";
         }
      }

      return "E";
   }
}
