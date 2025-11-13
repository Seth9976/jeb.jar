package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.StringInfo;
import com.pnfsoftware.jeb.core.units.code.java.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;
import java.util.Locale;

@Ser
public class bmu extends bmk implements IJavaConstant {
   @SerId(1)
   private IJavaType oW;
   @SerId(2)
   private boolean gO;
   @SerId(3)
   private byte nf;
   @SerId(4)
   private char gP;
   @SerId(5)
   private short za;
   @SerId(6)
   private int lm;
   @SerId(7)
   private long zz;
   @SerId(8)
   private float JY;
   @SerId(9)
   private double HF;
   @SerId(10)
   private String LK;
   @SerId(12)
   Integer Dw;
   @SerId(13)
   String Uv;

   bmu() {
      this.oW = null;
   }

   bmu(IJavaType var1, boolean var2) {
      this.oW = var1;
      this.gO = var2;
   }

   bmu(IJavaType var1, byte var2) {
      this.oW = var1;
      this.nf = var2;
   }

   bmu(IJavaType var1, char var2) {
      this.oW = var1;
      this.gP = var2;
   }

   bmu(IJavaType var1, short var2) {
      this.oW = var1;
      this.za = var2;
   }

   bmu(IJavaType var1, int var2) {
      this.oW = var1;
      this.lm = var2;
   }

   bmu(IJavaType var1, long var2) {
      this.oW = var1;
      this.zz = var2;
   }

   bmu(IJavaType var1, float var2) {
      this.oW = var1;
      this.JY = var2;
   }

   bmu(IJavaType var1, double var2) {
      this.oW = var1;
      this.HF = var2;
   }

   bmu(IJavaType var1, String var2) {
      this.oW = var1;
      this.LK = var2;
   }

   public Integer xK() {
      return this.Dw;
   }

   @Override
   public void setName(String var1) {
      this.Uv = var1;
   }

   @Override
   public String getName() {
      return this.Uv;
   }

   @Override
   public IJavaType getType() {
      return this.oW;
   }

   @Override
   public boolean getBoolean() {
      return this.gO;
   }

   @Override
   public byte getByte() {
      return this.nf;
   }

   @Override
   public char getChar() {
      return this.gP;
   }

   @Override
   public short getShort() {
      return this.za;
   }

   @Override
   public int getInt() {
      return this.lm;
   }

   @Override
   public long getLong() {
      return this.zz;
   }

   @Override
   public float getFloat() {
      return this.JY;
   }

   @Override
   public double getDouble() {
      return this.HF;
   }

   @Override
   public String getString() {
      return this.LK;
   }

   @Override
   public boolean isNull() {
      return this.oW == null;
   }

   @Override
   public boolean isString() {
      return this.LK != null;
   }

   @Override
   public boolean isTrue() {
      return this.oW != null && this.oW.isBoolean() && this.gO;
   }

   @Override
   public boolean isFalse() {
      return this.oW != null && this.oW.isBoolean() && !this.gO;
   }

   @Override
   public boolean isZero() {
      return this.oW != null
         && (
            this.oW.isByte() && this.nf == 0
               || this.oW.isChar() && this.gP == 0
               || this.oW.isShort() && this.za == 0
               || this.oW.isInt() && this.lm == 0
               || this.oW.isLong() && this.zz == 0L
               || this.oW.isFloat() && this.JY == 0.0F
               || this.oW.isDouble() && this.HF == 0.0
         );
   }

   @Override
   public boolean isOne() {
      return this.oW != null
         && (
            this.oW.isByte() && this.nf == 1
               || this.oW.isChar() && this.gP == 1
               || this.oW.isShort() && this.za == 1
               || this.oW.isInt() && this.lm == 1
               || this.oW.isLong() && this.zz == 1L
               || this.oW.isFloat() && this.JY == 1.0F
               || this.oW.isDouble() && this.HF == 1.0
         );
   }

   @Override
   public boolean isMinusOne() {
      return this.oW != null
         && (
            this.oW.isByte() && this.nf == -1
               || this.oW.isShort() && this.za == -1
               || this.oW.isInt() && this.lm == -1
               || this.oW.isLong() && this.zz == -1L
               || this.oW.isFloat() && this.JY == -1.0F
               || this.oW.isDouble() && this.HF == -1.0
         );
   }

   @Override
   public boolean isPositive() {
      return this.oW != null
         && (
            this.oW.isByte() && this.nf > 0
               || this.oW.isChar() && this.gP > 0
               || this.oW.isShort() && this.za > 0
               || this.oW.isInt() && this.lm > 0
               || this.oW.isLong() && this.zz > 0L
               || this.oW.isFloat() && this.JY > 0.0F
               || this.oW.isDouble() && this.HF > 0.0
         );
   }

   @Override
   public boolean isNegative() {
      return this.oW != null
         && (
            this.oW.isByte() && this.nf < 0
               || this.oW.isShort() && this.za < 0
               || this.oW.isInt() && this.lm < 0
               || this.oW.isLong() && this.zz < 0L
               || this.oW.isFloat() && this.JY < 0.0F
               || this.oW.isDouble() && this.HF < 0.0
         );
   }

   private long Uv() {
      if (this.oW.isBoolean()) {
         return this.gO ? 1L : 0L;
      } else if (this.oW.isByte()) {
         return this.nf;
      } else if (this.oW.isChar()) {
         return this.gP;
      } else if (this.oW.isShort()) {
         return this.za;
      } else if (this.oW.isInt()) {
         return this.lm;
      } else if (this.oW.isLong()) {
         return this.zz;
      } else if (this.oW.isFloat()) {
         return Float.floatToIntBits(this.JY);
      } else {
         return this.oW.isDouble() ? Double.doubleToLongBits(this.HF) : 0L;
      }
   }

   private int q(int var1, boolean var2) {
      if (var2) {
         return 10;
      } else {
         return var1 != 16 && var1 != 10 && var1 != 8 ? 10 : var1;
      }
   }

   private String q(int var1) {
      if (var1 == 16) {
         return "0x%X";
      } else if (var1 == 10) {
         return "%d";
      } else if (var1 == 8) {
         return "0%o";
      } else if (Licensing.isDebugBuild()) {
         throw new RuntimeException();
      } else {
         return "%d";
      }
   }

   private String q(boolean var1) {
      return this.q(var1 ? 10 : 16);
   }

   private boolean q(int var1, Object var2) {
      if (var1 == 10) {
         return false;
      } else if (var1 != 8 && var1 != 16) {
         if (Licensing.isDebugBuild()) {
            throw new RuntimeException();
         } else {
            return false;
         }
      } else if (var2 instanceof Byte) {
         return (Byte)var2 < 0;
      } else {
         return var2 instanceof Short ? (Short)var2 < 0 : false;
      }
   }

   @Override
   public List getSubElements() {
      return blo.q();
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      return false;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);
      if (this.oW == null) {
         var1.appendKeyword(JavaKeyword.NULL);
      } else if (this.oW.isJavaLangString()) {
         if (this.LK == null) {
            var1.append("null");
         } else {
            long var2 = 0L;
            String var4 = this.LK;
            ItemClassIdentifiers var5 = ItemClassIdentifiers.STRING;
            int var6 = 0;
            IDynamicContentManager var7 = var1.getDynamicContentManager();
            if (var7 != null) {
               StringInfo var8 = var7.getStringInfo(this.LK);
               if (var8 == null) {
                  var5 = ItemClassIdentifiers.STRING_GENERATED;
               } else {
                  var2 = var8.id;
                  var4 = var8.effectiveValue;
                  if (var8.artificial || this.getOrigin() != null) {
                     var5 = ItemClassIdentifiers.STRING_GENERATED;
                     if (this.Dw != null) {
                        var2 = 1152921504606846976L | this.Dw.intValue() & 4294967295L;
                     }
                  }

                  var6 = var8.rendering;
               }
            }

            boolean var9 = var4.length() <= 500;
            String var24;
            if (var6 == 1 && var9) {
               var24 = "\"" + Formatter.escapeString(var4, -1, true, null) + "\"";
            } else if (var6 == 2) {
               var24 = "\"" + Formatter.escapeAllCharacters(var4) + "\"";
            } else {
               var24 = "\"" + Formatter.escapeString(var4, var9) + "\"";
            }

            var1.appendAndRecord(var24, var5, var2);
         }
      } else {
         boolean var21 = false;
         IDynamicContentManager var22 = var1.getDynamicContentManager();
         long var23 = this.Uv();
         long var25 = 0L;
         ItemClassIdentifiers var10 = ItemClassIdentifiers.NUMBER;
         int var11 = var22 == null ? 16 : var22.getImmediatePreferredBase(var23);
         String var15;
         if (this.oW.isBoolean()) {
            var10 = ItemClassIdentifiers.KEYWORD;
            var15 = this.gO ? JavaKeyword.TRUE.toString().toLowerCase() : JavaKeyword.FALSE.toString().toLowerCase();
         } else if (this.oW.isByte()) {
            if (var11 == -1 && this.nf >= 0) {
               var15 = Strings.ff("'%s'", Formatter.escapeString(Character.toString((char)this.nf)));
            } else {
               var11 = this.q(var11, this.nf >= 0 && this.nf <= 9);
               String var20 = this.q(var11);
               var15 = Strings.ff(var20, this.nf);
               var21 = this.q(var11, this.nf);
            }
         } else if (this.oW.isChar()) {
            var15 = Strings.ff("'%s'", Formatter.escapeCharacter(this.gP));
         } else if (this.oW.isShort()) {
            if (var11 == -1 && this.za >= 0) {
               var15 = Strings.ff("'%s'", Formatter.escapeString(Character.toString((char)this.za)));
            } else {
               var11 = this.q(var11, this.za >= 0 && this.za <= 9);
               String var19 = this.q(var11);
               var15 = Strings.ff(var19, this.za);
               var21 = this.q(var11, this.za);
            }
         } else if (this.oW.isInt()) {
            if (var22 != null && var1.getEolComment() == null) {
               String var31 = var22.retrieveImmediateHint(this.lm);
               if (var31 != null) {
                  var1.setEolComment(var31, true);
               }
            }

            if (var11 == -1 && this.lm >= 0 && this.lm <= 65535) {
               var15 = Strings.ff("'%s'", Formatter.escapeString(Character.toString((char)this.lm)));
            } else {
               var11 = this.q(var11, this.lm >= 0 && this.lm <= 9);
               String var18 = this.q(var11);
               var15 = Strings.ff(var18, this.lm);
            }
         } else if (this.oW.isLong()) {
            if (var11 == -1 && this.zz >= 0L && this.zz <= 65535L) {
               var15 = Strings.ff("'%s'", Formatter.escapeString(Character.toString((char)this.zz)));
            } else {
               var11 = this.q(var11, this.zz >= 0L && this.zz <= 9L);
               String var3 = this.q(var11);
               var15 = Strings.ff(var3, this.zz) + "L";
            }
         } else if (this.oW.isFloat()) {
            var15 = Strings.ff(Locale.US, "%.6f", this.JY);

            int var30;
            for (var30 = var15.length() - 1; var30 >= 0; var30--) {
               char var33 = var15.charAt(var30);
               if (var33 != '0') {
                  var30++;
                  if (var33 == '.') {
                     var30++;
                  }
                  break;
               }
            }

            var15 = var15.substring(0, var30);
            if (var15.equals("0.0") && Math.signum(this.JY) != 0.0F) {
               var15 = Strings.f("%E", this.JY);
            } else {
               int var34 = var15.indexOf(46);
               if (var34 == -1) {
                  var34 = var15.length();
               }

               if (var15.substring(0, var34).endsWith("00000000000000000000")) {
                  var15 = Strings.f("%E", this.JY);
               }
            }

            var15 = var15 + "f";
         } else {
            if (!this.oW.isDouble()) {
               throw new RuntimeException();
            }

            var15 = Strings.f("%.6f", this.HF);

            int var12;
            for (var12 = var15.length() - 1; var12 >= 0; var12--) {
               char var13 = var15.charAt(var12);
               if (var13 != '0') {
                  var12++;
                  if (var13 == '.') {
                     var12++;
                  }
                  break;
               }
            }

            var15 = var15.substring(0, var12);
            if (var15.equals("0.0") && Math.signum(this.HF) != 0.0) {
               var15 = Strings.f("%E", this.HF);
            } else {
               int var32 = var15.indexOf(46);
               if (var32 == -1) {
                  var32 = var15.length();
               }

               if (var15.substring(0, var32).endsWith("00000000000000000000")) {
                  var15 = Strings.f("%E", this.HF);
               }
            }
         }

         if (this.getOrigin() != null) {
            var10 = ItemClassIdentifiers.NUMBER_GENERATED;
            if (this.Dw != null) {
               var25 = 1152921504606846976L | this.Dw.intValue() & 4294967295L;
            }
         }

         if (this.Uv != null && this.Uv.length() > 0) {
            var15 = this.Uv;
         } else if (var21) {
            var1.paren();
            blk.q(var1, this.oW);
            var1.parenClose();
         }

         if (var25 == 0L && var22 != null) {
            var25 = var22.getImmediateId(var23);
         }

         var1.appendAndRecord(var15, var10, var25);
      }

      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Constant;
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1;
   }

   public bmu Dw() {
      return this;
   }

   @Override
   public String toString() {
      if (this.oW == null) {
         return "null";
      } else if (this.oW.isJavaLangString()) {
         return this.LK == null ? "null(String)" : Strings.ff("\"%s\"", Formatter.escapeString(this.LK));
      } else if (this.oW.isBoolean()) {
         return Strings.ff("%s", this.gO ? "true" : "false");
      } else {
         String var1;
         if (this.oW.isByte()) {
            String var2 = this.q(this.nf <= 9);
            var1 = Strings.ff(var2, this.nf);
         } else if (this.oW.isChar()) {
            var1 = Strings.ff("'%s'", Formatter.escapeCharacter(this.gP));
         } else if (this.oW.isShort()) {
            String var6 = this.q(this.za <= 9);
            var1 = Strings.ff(var6, this.za);
         } else if (this.oW.isInt()) {
            String var7 = this.q(this.lm >= 0 && this.lm <= 9);
            var1 = Strings.ff(var7, this.lm);
         } else if (this.oW.isLong()) {
            String var8 = this.q(this.zz <= 9L);
            var1 = Strings.ff(var8, this.zz);
            if (this.zz < -2147483648L || this.zz > 2147483647L) {
               var1 = var1 + "L";
            }
         } else {
            if (!this.oW.isFloat() && !this.oW.isDouble()) {
               throw new RuntimeException();
            }

            var1 = Strings.ff("%.6f", this.oW.isFloat() ? this.JY : this.HF);

            int var3;
            for (var3 = var1.length() - 1; var3 >= 0; var3--) {
               char var4 = var1.charAt(var3);
               if (var4 != '0') {
                  if (var4 != '.') {
                     var3++;
                  }
                  break;
               }
            }

            var1 = var1.substring(0, var3);
            if (this.oW.isFloat()) {
               var1 = var1 + "f";
            }
         }

         return Strings.ff("%s(%s)", var1, this.oW);
      }
   }

   @Ser
   public static enum eo {
      q,
      RF,
      xK;
   }
}
