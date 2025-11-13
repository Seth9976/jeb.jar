package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.android.JvmFieldSig;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVariable;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractValueNumber;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueBoolean;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueCharacter;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueString;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.CharSequences;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jebglobal.Fx;
import com.pnfsoftware.jebglobal.Ux;
import com.pnfsoftware.jebglobal.ch;
import com.pnfsoftware.jebglobal.hw;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.text.StringEscapeUtils;

public class Vj {
   private CI q;

   public Vj(CI var1) {
      this.q = var1;
   }

   public CI q() {
      return this.q;
   }

   public List q(String var1, kY var2, Xa var3, String[] var4) throws IOException, JebException {
      StringBuilder var5 = new StringBuilder(var1.trim());
      List var6 = this.q(var5, var2, var3, var4);
      if (!Strings.isBlank(var5)) {
         throw new JebException("Malformed argument list. Use simple comas or full parenthesis. Example: 'true, false' ; '\"foo\",'c'' ; '(true, false)'");
      } else {
         return var6;
      }
   }

   public List q(StringBuilder var1, kY var2, Xa var3, String[] var4) throws IOException, JebException {
      ArrayList var5 = new ArrayList();
      int var6 = 0;
      RF(var1);
      if (Strings.isBlank(var1)) {
         return var5;
      } else {
         if (var1.charAt(0) == '(') {
            var1.deleteCharAt(0);
         }

         while (!Strings.isBlank(var1)) {
            RF(var1);
            if (var1.charAt(0) == ')') {
               var1.deleteCharAt(0);
               return var5;
            }

            this.q(var5, var1, var2, var3, var4 != null && var6 < var4.length ? var4[var6] : null);
            if (Strings.isBlank(var1)) {
               break;
            }

            RF(var1);
            if (var1.charAt(0) != ',') {
               if (var1.charAt(0) == ')') {
                  var1.deleteCharAt(0);
                  return var5;
               }

               throw new JebException(Strings.ff("Expected argument separator before %s", var1));
            }

            var1.deleteCharAt(0);
            if (Strings.isBlank(var1)) {
               throw new JebException("Expected argument after final ','");
            }

            var6++;
         }

         return var5;
      }
   }

   private void q(List var1, StringBuilder var2, kY var3, Xa var4, String var5) throws IOException, JebException {
      if (!Strings.isBlank(var2)) {
         if (var2.charAt(0) == '(') {
            var2.deleteCharAt(0);
            RF(var2);
            this.q(var1, var2, var3, var4, var5);
            RF(var2);
            if (var2.charAt(0) != ')') {
               throw new JebException("Malformed argument list: expected closed parenthesis");
            } else {
               var2.deleteCharAt(0);
            }
         } else if (var2.charAt(0) == '"') {
            int var22 = CharSequences.indexOf(var2, '"', 1);

            while (var22 != -1) {
               int var26 = var22 - 1;

               int var19;
               for (var19 = 0; var2.charAt(var26) == '\\'; var26--) {
                  var19++;
               }

               if (var19 % 2 != 0) {
                  var22 = CharSequences.indexOf(var2, '"', var22 + 1);
               }

               if (var19 % 2 == 0) {
                  var1.add(new ValueString(StringEscapeUtils.escapeJava(var2.substring(1, var22))));
                  var2.delete(0, var22 + 1);
                  return;
               }
            }

            throw new JebException("Malformed String: not closed");
         } else if (var2.charAt(0) == '\'') {
            if (var2.charAt(1) == '\\' && var2.charAt(3) == '\'') {
               var1.add(new ValueCharacter(StringEscapeUtils.unescapeJava(var2.substring(1, 3)).charAt(0)));
               var2.delete(0, 4);
            } else if (var2.charAt(2) == '\'') {
               var1.add(new ValueCharacter(var2.charAt(1)));
               var2.delete(0, 3);
            } else {
               throw new JebException(Strings.ff("Cannot parse argument(s) '%s'", var2));
            }
         } else if (CharSequences.startsWith(var2, "this") || CharSequences.startsWith(var2, "@")) {
            int var7 = MathUtil.minPositive(CharSequences.indexOfn(var2, ',', '}', ')'));
            if (var7 == -1) {
               var1.add(this.RF(var2.toString(), var3, var4));
               var2.delete(0, var2.length());
            } else {
               var1.add(this.RF(var2.substring(0, var7), var3, var4));
               var2.delete(0, var7);
            }
         } else {
            char var6;
            if ((var6 = var2.charAt(0)) == '-' || var6 == '+' || Character.isDigit(var6)) {
               int var16 = 0;
               if (var6 == '-' || var6 == '+') {
                  var16++;
               }

               boolean var8 = false;
               if (var16 + 2 <= var2.length() && var2.charAt(var16) == '0' && ((var6 = var2.charAt(var16 + 1)) == 'x' || var6 == 'X')) {
                  var8 = true;
                  var16 += 2;
               }

               int var9 = var2.indexOf(" ", var16);
               if (var9 < 0) {
                  var9 = var2.length();
               }

               var6 = var2.charAt(var9 - 1);
               if (var6 == 'h') {
                  if (var8) {
                     throw new JebException("Hex number already prefixed with 0x");
                  }

                  var8 = true;
               }

               for (var23 = false; var16 < var2.length(); var16++) {
                  var6 = var2.charAt(var16);
                  if (!Character.isDigit(var6) && (!var8 || (var6 < 'a' || var6 > 'f') && (var6 < 'A' || var6 > 'F'))) {
                     if (var2.charAt(var16) != '.') {
                        break;
                     }

                     if (var23) {
                        throw new JebException("Double dot in Digit");
                     }

                     var23 = true;
                     if (var8) {
                        throw new JebException("Illegal float number prefixed as hexadecimal");
                     }
                  }
               }

               if (var16 < var2.length() && var2.charAt(var16) == 'h') {
                  var16++;
               }

               int var10 = var16;
               byte var11 = -1;
               if (var16 < var2.length()) {
                  var11 = q(var2.charAt(var16));
               }

               if (var11 == -1) {
                  if (var23) {
                     var11 = 68;
                  } else {
                     var11 = 73;
                  }
               } else {
                  var16++;
               }

               String var12 = var2.substring(0, var10);
               if (var5 == null || !var5.equals("object") && !var5.equals("array")) {
                  var1.add(q(var11, var12, var5));
               } else {
                  var1.add(new HA(this.q, Conversion.stringToLong(var12), 0L, var5));
               }

               var2.delete(0, var16);
            } else if (q(var2, "true")) {
               var1.add(new ValueBoolean(true));
               var2.delete(0, 4);
            } else if (q(var2, "false")) {
               var1.add(new ValueBoolean(false));
               var2.delete(0, 5);
            } else if (var2.charAt(0) == '[') {
               int var18 = CharSequences.indexOf(var2, '{');
               String var21 = var2.substring(0, var18).trim();
               this.RF(var1, var2, var3, var4, var21);
            } else {
               if (CharSequences.startsWith(var2, "new ")) {
                  var2.delete(0, 4);
                  RF(var2);
                  int var17 = CharSequences.indexOf2(var2, '[', '(');
                  if (var17 != -1) {
                     String var20 = var2.substring(0, var17).trim();
                     var2.delete(0, var17);
                     if (var2.charAt(0) == '[') {
                        String var25 = this.q(var2);
                        RF(var2);
                        String var27 = var25 + this.RF(var20);
                        this.RF(var1, var2, var3, var4, var27);
                        return;
                     }

                     List var24 = this.q(var2, var3, var4, null);
                     var1.add(new Vj.CU(var3, var20, var24));
                     return;
                  }
               }

               throw new JebException(Strings.ff("Cannot parse argument(s) '%s'", var2));
            }
         }
      }
   }

   private String q(StringBuilder var1) throws JebException {
      RF(var1);
      String var2 = "";

      boolean var3;
      for (var3 = true; var1.length() > 0; var1.deleteCharAt(0)) {
         char var4 = var1.charAt(0);
         if (var4 == '[') {
            if (!var3) {
               break;
            }

            var3 = false;
         } else if (var4 == ']') {
            if (var3) {
               var3 = false;
               break;
            }

            var3 = true;
            var2 = var2 + "[";
         } else if (!Character.isWhitespace(var4)) {
            break;
         }
      }

      if (!var3) {
         throw new JebException(Strings.ff("Can not determine array depth '%s'", var1));
      } else {
         return var2;
      }
   }

   private void RF(List var1, StringBuilder var2, kY var3, Xa var4, String var5) throws JebException, IOException {
      int var6 = CharSequences.indexOf(var2, '{');
      int var7 = CharSequences.indexOf(var2, '}');
      if (var6 != -1 && var7 != -1 && var7 >= var6) {
         String var8 = null;
         if (var6 != -1) {
            var8 = var5.substring(1);
            if (var8.length() == 1) {
               byte var9 = q(var8.charAt(0));
               if (var9 != -1) {
                  var8 = q(var9);
               }
            }
         }

         ArrayList var10 = new ArrayList();
         var2.delete(0, var6 + 1);

         while (var2.charAt(0) != '}') {
            RF(var2);
            if (var5.charAt(1) == '[' && CharSequences.startsWith(var2, "{")) {
               this.RF(var10, var2, var3, var4, var5.substring(1));
            } else {
               this.q(var10, var2, var3, var4, var8);
            }

            if (var2.charAt(0) == '}') {
               var2.deleteCharAt(0);
               break;
            }

            RF(var2);
            if (var2.charAt(0) != ',') {
               throw new JebException(Strings.ff("Expected argument separator before %s", var2));
            }

            var2.deleteCharAt(0);
            if (Strings.isBlank(var2)) {
               throw new JebException("Expected argument after final ','");
            }
         }

         var1.add(new Vj.eo(var5, var10));
      } else {
         throw new JebException("Malformed table: Table does not allow spaces or objects not in {}");
      }
   }

   private static byte q(char var0) {
      byte var1 = -1;
      switch (var0) {
         case 'B':
         case 'b':
            return 66;
         case 'C':
         case 'E':
         case 'G':
         case 'H':
         case 'K':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'T':
         case 'U':
         case 'V':
         case 'W':
         case 'X':
         case 'Y':
         case 'Z':
         case '[':
         case '\\':
         case ']':
         case '^':
         case '_':
         case '`':
         case 'a':
         case 'c':
         case 'e':
         case 'g':
         case 'h':
         case 'k':
         case 'm':
         case 'n':
         case 'o':
         case 'p':
         case 'q':
         case 'r':
         default:
            return var1;
         case 'D':
         case 'd':
            return 68;
         case 'F':
         case 'f':
            return 70;
         case 'I':
         case 'i':
            return 73;
         case 'J':
         case 'L':
         case 'j':
         case 'l':
            return 74;
         case 'S':
         case 's':
            return 83;
      }
   }

   private static ITypedValue q(byte var0, String var1, String var2) {
      if (var2 == null) {
         var2 = q(var0);
      }

      return AbstractValueNumber.parseNumber(var2, var1);
   }

   private static String q(byte var0) {
      switch (var0) {
         case 66:
            return "byte";
         case 67:
         case 69:
         case 71:
         case 72:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         default:
            return null;
         case 68:
            return "double";
         case 70:
            return "float";
         case 73:
            return "int";
         case 74:
            return "long";
         case 83:
            return "short";
      }
   }

   private String RF(String var1) {
      switch (var1) {
         case "boolean":
            return "Z";
         case "byte":
            return "B";
         case "short":
            return "S";
         case "int":
            return "I";
         case "long":
            return "J";
         case "float":
            return "F";
         case "double":
            return "D";
         case "char":
            return "C";
         default:
            return "L" + var1.replace('.', '/') + ";";
      }
   }

   private IDebuggerVariable q(kY var1, Xa var2) throws IOException, JebException {
      if (!var1.RF()) {
         throw new JebException("Thread is not paused");
      } else if (var2 == null) {
         throw new JebException("Frame is not accessible.");
      } else {
         Ux var3 = (Ux)this.q().oW();
         return new LR("this", var3.HF().q(this.q(), var1.getId(), var2.getId()), 0, this.q(), null);
      }
   }

   public final IDebuggerVariable q(String var1, kY var2, Xa var3) throws IOException, JebException {
      Ux var5 = (Ux)this.q().oW();
      if (var1.startsWith("@")) {
         var1 = var1.substring(1);
         int var14 = MathUtil.minPositive(var1.indexOf(46), var1.indexOf(91));
         if (var14 == -1) {
            return var5.HF().q(this.q(), var1, null);
         } else {
            IDebuggerVariable var13 = var5.HF().q(this.q(), var1.substring(0, var14), null);
            var1 = var1.substring(var14);
            return this.q(var1, var13);
         }
      } else if ("this".equals(var1)) {
         return this.q(var2, var3);
      } else if (var1.startsWith("this.")) {
         var1 = var1.substring(5);
         IDebuggerVariable var4 = this.q(var2, var3);
         return this.q(var1, var4);
      } else if (var1.startsWith("L") && var1.contains("->") && !var1.contains("(") && !var1.contains(")")) {
         JvmFieldSig var6 = JvmFieldSig.parse(var1, true);
         String var7 = var6.csig;
         String var8 = var6.fname;
         return this.q(var7, var8);
      } else {
         return this.q(var1, null);
      }
   }

   public IDebuggerVariable q(String var1, IDebuggerVariable var2) throws JebException {
      Ux var3 = (Ux)this.q().oW();
      return var3.HF().q(this.q(), var1, var2);
   }

   public ITypedValue RF(String var1, kY var2, Xa var3) throws IOException, JebException {
      IDebuggerVariable var4 = this.q(var1, var2, var3);
      if (var4 != null && var4.getTypedValue() != null) {
         return var4.getTypedValue();
      } else {
         throw new JebException(Strings.ff("No value found for object %s", var1));
      }
   }

   public final kY q(String var1) {
      long var2 = var1 == null ? -1L : Conversion.stringToLong(var1, -1L);
      return var2 == -1L ? this.q().nf() : this.q().q(var2);
   }

   public final Xa q(kY var1, String var2) {
      int var3 = var2 == null ? 0 : Conversion.toInt(var2, 0);
      return var1.q(var3);
   }

   private static String xK(String var0) {
      return var0.contains(".") ? "L" + var0.replaceAll("\\.", "/") + ";" : var0;
   }

   private static void RF(StringBuilder var0) {
      while (var0.length() > 0 && var0.charAt(0) <= ' ') {
         var0.deleteCharAt(0);
      }
   }

   private static boolean q(StringBuilder var0, String var1) {
      if (!CharSequences.startsWith(var0, var1)) {
         return false;
      } else if (var0.length() == var1.length()) {
         return true;
      } else {
         char var2 = var0.charAt(var1.length());
         return var2 == ' '
            || var2 == ','
            || var2 == ';'
            || var2 == ')'
            || var2 == ']'
            || var2 == '}'
            || var2 == '\''
            || var2 == '"'
            || var2 == '/'
            || var2 == '>'
            || var2 == '|';
      }
   }

   public IDebuggerVariable q(String var1, String var2) throws IOException, JebException {
      Ux var3 = (Ux)this.q().oW();
      long var4 = var3.JY().Dw(var1);
      if (var4 < 0L) {
         return null;
      } else {
         hw var6 = var3.JY().xK(var4, var2, null);
         if (var6 == null) {
            return null;
         } else {
            ch var7 = var3.zz().q(var4, var6.za);
            ITypedValue var8;
            if (var7.q()) {
               IDebuggerVariable var9 = var3.HF().q(this.q(), var7.RF + "", null);
               var8 = var9.getTypedValue();
            } else {
               var8 = this.q().q(var7, null);
            }

            return new LR(var2, var8, HA.q(var6.HF), this.q, new LR.eo(var4, var6.za));
         }
      }
   }

   public class CU extends HA {
      String q;
      List RF;
      long xK = 0L;
      private kY oW;

      public CU(kY var2, String var3, List var4) {
         super(Vj.this.q(), 0L, 0L, var3);
         this.oW = var2;
         this.q = Vj.xK(var3);
         this.RF = var4;
      }

      @Override
      public long getObjectId() {
         if (this.xK == 0L) {
            Ux var1 = (Ux)Vj.this.q().oW();

            try {
               long var2 = var1.JY().Dw(this.q);
               List var4 = Vj.this.q().RF(this.RF);
               ch var5 = var1.JY().q(this.oW.getId(), var2, var4);
               this.xK = var5 == null ? 0L : var5.RF;
            } catch (IOException var6) {
               this.xK = 0L;
               com.pnfsoftware.jeb.corei.debuggers.android.vm.CU.xK.catching(var6);
               throw new JebRuntimeException("Inner exception while trying to instantiate array");
            } catch (JebException var7) {
               this.xK = 0L;
               throw new JebRuntimeException(var7.getMessage());
            }
         }

         return this.xK;
      }

      public List q() {
         return this.RF;
      }
   }

   public class eo extends eM {
      String q;
      List RF;
      long xK = 0L;

      public eo(String var2, List var3) {
         super(Vj.this.q(), 0L, 0L);
         this.q = Vj.xK(var2);
         this.RF = var3;
      }

      @Override
      public long getObjectId() {
         if (this.xK == 0L) {
            Ux var1 = (Ux)Vj.this.q().oW();

            try {
               long var2 = var1.JY().Dw(this.q);
               this.xK = var1.zz().q(var2, this.RF.size());
               List var4 = Vj.this.q().RF(this.RF);
               String var5 = this.q.substring(1);

               for (ch var7 : var4) {
                  if (!var1.JY().q(var7, var5)) {
                     throw new JebRuntimeException(Strings.ff("Argument %s is not of type %s", var7.RF, var5));
                  }
               }

               var1.zz().q(this.xK, 0, var4);
            } catch (Fx | IOException var8) {
               this.xK = 0L;
               com.pnfsoftware.jeb.corei.debuggers.android.vm.CU.xK.catching(var8);
               throw new JebRuntimeException("Inner exception while trying to instantiate array");
            } catch (JebException var9) {
               this.xK = 0L;
               throw new JebRuntimeException(var9.getMessage());
            }
         }

         return this.xK;
      }

      public List q() {
         return this.RF;
      }
   }
}
