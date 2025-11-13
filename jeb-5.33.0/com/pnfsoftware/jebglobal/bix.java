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
public class bix extends bin implements IJavaConstant {
   @SerId(1)
   private IJavaType wS;
   @SerId(2)
   private boolean UT;
   @SerId(3)
   private byte E;
   @SerId(4)
   private char sY;
   @SerId(5)
   private short ys;
   @SerId(6)
   private int ld;
   @SerId(7)
   private long gp;
   @SerId(8)
   private float oT;
   @SerId(9)
   private double fI;
   @SerId(10)
   private String WR;
   @SerId(12)
   Integer A;
   @SerId(13)
   String kS;

   bix() {
      this.wS = null;
   }

   bix(IJavaType var1, boolean var2) {
      this.wS = var1;
      this.UT = var2;
   }

   bix(IJavaType var1, byte var2) {
      this.wS = var1;
      this.E = var2;
   }

   bix(IJavaType var1, char var2) {
      this.wS = var1;
      this.sY = var2;
   }

   bix(IJavaType var1, short var2) {
      this.wS = var1;
      this.ys = var2;
   }

   bix(IJavaType var1, int var2) {
      this.wS = var1;
      this.ld = var2;
   }

   bix(IJavaType var1, long var2) {
      this.wS = var1;
      this.gp = var2;
   }

   bix(IJavaType var1, float var2) {
      this.wS = var1;
      this.oT = var2;
   }

   bix(IJavaType var1, double var2) {
      this.wS = var1;
      this.fI = var2;
   }

   bix(IJavaType var1, String var2) {
      this.wS = var1;
      this.WR = var2;
   }

   @Override
   public void setName(String var1) {
      this.kS = var1;
   }

   @Override
   public String getName() {
      return this.kS;
   }

   @Override
   public IJavaType getType() {
      return this.wS;
   }

   @Override
   public boolean getBoolean() {
      return this.UT;
   }

   @Override
   public byte getByte() {
      return this.E;
   }

   @Override
   public char getChar() {
      return this.sY;
   }

   @Override
   public short getShort() {
      return this.ys;
   }

   @Override
   public int getInt() {
      return this.ld;
   }

   @Override
   public long getLong() {
      return this.gp;
   }

   @Override
   public float getFloat() {
      return this.oT;
   }

   @Override
   public double getDouble() {
      return this.fI;
   }

   @Override
   public String getString() {
      return this.WR;
   }

   @Override
   public boolean isNull() {
      return this.wS == null;
   }

   @Override
   public boolean isString() {
      return this.WR != null;
   }

   @Override
   public boolean isTrue() {
      return this.wS != null && this.wS.isBoolean() && this.UT;
   }

   @Override
   public boolean isFalse() {
      return this.wS != null && this.wS.isBoolean() && !this.UT;
   }

   @Override
   public boolean isZero() {
      return this.wS != null
         && (
            this.wS.isByte() && this.E == 0
               || this.wS.isChar() && this.sY == 0
               || this.wS.isShort() && this.ys == 0
               || this.wS.isInt() && this.ld == 0
               || this.wS.isLong() && this.gp == 0L
               || this.wS.isFloat() && this.oT == 0.0F
               || this.wS.isDouble() && this.fI == 0.0
         );
   }

   @Override
   public boolean isOne() {
      return this.wS != null
         && (
            this.wS.isByte() && this.E == 1
               || this.wS.isChar() && this.sY == 1
               || this.wS.isShort() && this.ys == 1
               || this.wS.isInt() && this.ld == 1
               || this.wS.isLong() && this.gp == 1L
               || this.wS.isFloat() && this.oT == 1.0F
               || this.wS.isDouble() && this.fI == 1.0
         );
   }

   @Override
   public boolean isMinusOne() {
      return this.wS != null
         && (
            this.wS.isByte() && this.E == -1
               || this.wS.isShort() && this.ys == -1
               || this.wS.isInt() && this.ld == -1
               || this.wS.isLong() && this.gp == -1L
               || this.wS.isFloat() && this.oT == -1.0F
               || this.wS.isDouble() && this.fI == -1.0
         );
   }

   @Override
   public boolean isPositive() {
      return this.wS != null
         && (
            this.wS.isByte() && this.E > 0
               || this.wS.isChar() && this.sY > 0
               || this.wS.isShort() && this.ys > 0
               || this.wS.isInt() && this.ld > 0
               || this.wS.isLong() && this.gp > 0L
               || this.wS.isFloat() && this.oT > 0.0F
               || this.wS.isDouble() && this.fI > 0.0
         );
   }

   @Override
   public boolean isNegative() {
      return this.wS != null
         && (
            this.wS.isByte() && this.E < 0
               || this.wS.isShort() && this.ys < 0
               || this.wS.isInt() && this.ld < 0
               || this.wS.isLong() && this.gp < 0L
               || this.wS.isFloat() && this.oT < 0.0F
               || this.wS.isDouble() && this.fI < 0.0
         );
   }

   private long kS() {
      if (this.wS.isBoolean()) {
         return this.UT ? 1L : 0L;
      } else if (this.wS.isByte()) {
         return this.E;
      } else if (this.wS.isChar()) {
         return this.sY;
      } else if (this.wS.isShort()) {
         return this.ys;
      } else if (this.wS.isInt()) {
         return this.ld;
      } else if (this.wS.isLong()) {
         return this.gp;
      } else if (this.wS.isFloat()) {
         return Float.floatToIntBits(this.oT);
      } else {
         return this.wS.isDouble() ? Double.doubleToLongBits(this.fI) : 0L;
      }
   }

   private int pC(int var1, boolean var2) {
      if (var2) {
         return 10;
      } else {
         return var1 != 16 && var1 != 10 && var1 != 8 ? 10 : var1;
      }
   }

   private String pC(int var1) {
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

   private String pC(boolean var1) {
      return this.pC(var1 ? 10 : 16);
   }

   private boolean pC(int var1, Object var2) {
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
      return bhr.pC();
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      return false;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);
      if (this.wS == null) {
         var1.appendKeyword(JavaKeyword.NULL);
      } else if (this.wS.isJavaLangString()) {
         if (this.WR == null) {
            var1.append("null");
         } else {
            long var2 = 0L;
            String var4 = this.WR;
            ItemClassIdentifiers var5 = ItemClassIdentifiers.STRING;
            int var6 = 0;
            IDynamicContentManager var7 = var1.getDynamicContentManager();
            if (var7 != null) {
               StringInfo var8 = var7.getStringInfo(this.WR);
               if (var8 == null) {
                  var5 = ItemClassIdentifiers.STRING_GENERATED;
               } else {
                  var2 = var8.id;
                  var4 = var8.effectiveValue;
                  if (var8.artificial || this.getOrigin() != null) {
                     var5 = ItemClassIdentifiers.STRING_GENERATED;
                     if (this.A != null) {
                        var2 = 1152921504606846976L | this.A.intValue() & 4294967295L;
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
         long var23 = this.kS();
         long var25 = 0L;
         ItemClassIdentifiers var10 = ItemClassIdentifiers.NUMBER;
         int var11 = var22 == null ? 16 : var22.getImmediatePreferredBase(var23);
         String var15;
         if (this.wS.isBoolean()) {
            var10 = ItemClassIdentifiers.KEYWORD;
            var15 = this.UT ? JavaKeyword.TRUE.toString().toLowerCase() : JavaKeyword.FALSE.toString().toLowerCase();
         } else if (this.wS.isByte()) {
            if (var11 == -1 && this.E >= 0) {
               var15 = Strings.ff("'%s'", Formatter.escapeString(Character.toString((char)this.E)));
            } else {
               var11 = this.pC(var11, this.E >= 0 && this.E <= 9);
               String var20 = this.pC(var11);
               var15 = Strings.ff(var20, this.E);
               var21 = this.pC(var11, this.E);
            }
         } else if (this.wS.isChar()) {
            var15 = Strings.ff("'%s'", Formatter.escapeCharacter(this.sY));
         } else if (this.wS.isShort()) {
            if (var11 == -1 && this.ys >= 0) {
               var15 = Strings.ff("'%s'", Formatter.escapeString(Character.toString((char)this.ys)));
            } else {
               var11 = this.pC(var11, this.ys >= 0 && this.ys <= 9);
               String var19 = this.pC(var11);
               var15 = Strings.ff(var19, this.ys);
               var21 = this.pC(var11, this.ys);
            }
         } else if (this.wS.isInt()) {
            if (var22 != null && var1.getEolComment() == null) {
               String var31 = var22.retrieveImmediateHint(this.ld);
               if (var31 != null) {
                  var1.setEolComment(var31, true);
               }
            }

            if (var11 == -1 && this.ld >= 0 && this.ld <= 65535) {
               var15 = Strings.ff("'%s'", Formatter.escapeString(Character.toString((char)this.ld)));
            } else {
               var11 = this.pC(var11, this.ld >= 0 && this.ld <= 9);
               String var18 = this.pC(var11);
               var15 = Strings.ff(var18, this.ld);
            }
         } else if (this.wS.isLong()) {
            if (var11 == -1 && this.gp >= 0L && this.gp <= 65535L) {
               var15 = Strings.ff("'%s'", Formatter.escapeString(Character.toString((char)this.gp)));
            } else {
               var11 = this.pC(var11, this.gp >= 0L && this.gp <= 9L);
               String var3 = this.pC(var11);
               var15 = Strings.ff(var3, this.gp) + "L";
            }
         } else if (this.wS.isFloat()) {
            var15 = Strings.ff(Locale.US, "%.6f", this.oT);

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
            if (var15.equals("0.0") && Math.signum(this.oT) != 0.0F) {
               var15 = Strings.f("%E", this.oT);
            } else {
               int var34 = var15.indexOf(46);
               if (var34 == -1) {
                  var34 = var15.length();
               }

               if (var15.substring(0, var34).endsWith("00000000000000000000")) {
                  var15 = Strings.f("%E", this.oT);
               }
            }

            var15 = var15 + "f";
         } else {
            if (!this.wS.isDouble()) {
               throw new RuntimeException();
            }

            var15 = Strings.f("%.6f", this.fI);

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
            if (var15.equals("0.0") && Math.signum(this.fI) != 0.0) {
               var15 = Strings.f("%E", this.fI);
            } else {
               int var32 = var15.indexOf(46);
               if (var32 == -1) {
                  var32 = var15.length();
               }

               if (var15.substring(0, var32).endsWith("00000000000000000000")) {
                  var15 = Strings.f("%E", this.fI);
               }
            }
         }

         if (this.getOrigin() != null) {
            var10 = ItemClassIdentifiers.NUMBER_GENERATED;
            if (this.A != null) {
               var25 = 1152921504606846976L | this.A.intValue() & 4294967295L;
            }
         }

         if (this.kS != null && this.kS.length() > 0) {
            var15 = this.kS;
         } else if (var21) {
            var1.paren();
            bhn.pC(var1, this.wS);
            var1.parenClose();
         }

         if (var25 == 0L && var22 != null) {
            var25 = var22.getImmediateId(var23);
         }

         var1.appendAndRecord(var15, var10, var25);
      }

      this.A(var1);
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

   public bix A() {
      return this;
   }

   @Override
   public String toString() {
      if (this.wS == null) {
         return "null";
      } else if (this.wS.isJavaLangString()) {
         return this.WR == null ? "null(String)" : Strings.ff("\"%s\"", Formatter.escapeString(this.WR));
      } else if (this.wS.isBoolean()) {
         return Strings.ff("%s", this.UT ? "true" : "false");
      } else {
         String var1;
         if (this.wS.isByte()) {
            String var2 = this.pC(this.E <= 9);
            var1 = Strings.ff(var2, this.E);
         } else if (this.wS.isChar()) {
            var1 = Strings.ff("'%s'", Formatter.escapeCharacter(this.sY));
         } else if (this.wS.isShort()) {
            String var6 = this.pC(this.ys <= 9);
            var1 = Strings.ff(var6, this.ys);
         } else if (this.wS.isInt()) {
            String var7 = this.pC(this.ld >= 0 && this.ld <= 9);
            var1 = Strings.ff(var7, this.ld);
         } else if (this.wS.isLong()) {
            String var8 = this.pC(this.gp <= 9L);
            var1 = Strings.ff(var8, this.gp);
            if (this.gp < -2147483648L || this.gp > 2147483647L) {
               var1 = var1 + "L";
            }
         } else {
            if (!this.wS.isFloat() && !this.wS.isDouble()) {
               throw new RuntimeException();
            }

            var1 = Strings.ff("%.6f", this.wS.isFloat() ? this.oT : this.fI);

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
            if (this.wS.isFloat()) {
               var1 = var1 + "f";
            }
         }

         return Strings.ff("%s(%s)", var1, this.wS);
      }
   }

   @Ser
   public static enum Av {
      pC,
      A,
      kS;
   }
}
