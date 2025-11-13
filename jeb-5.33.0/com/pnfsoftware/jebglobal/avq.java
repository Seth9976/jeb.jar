package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.UnmanglerException;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IManglingEngine;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class avq implements IManglingEngine {
   private static final ILogger pC = GlobalLog.getLogger(avq.class);
   private boolean A;

   @Override
   public IUnmangledData unmangle(String var1) {
      avq.Av var2 = new avq.Av(var1);

      IUnmangledData var3;
      try {
         var3 = this.pC(var2);
      } catch (Exception var4) {
         if (!this.A) {
            this.A = true;
            JebCoreService.notifySilentExceptionToClient(new UnmanglerException(Strings.ff("MSVCPP - on:%s", var1)));
         }

         return null;
      }

      return var3 == null || var3.getFull() == null || var3.isRawData() || !var3.getFull().contains("@") && !var3.getFull().contains("?") ? var3 : null;
   }

   private IUnmangledData pC(avq.Av var1) {
      return this.pC(var1, null);
   }

   private IUnmangledData pC(avq.Av var1, List var2) {
      int var3 = 0;
      if (var1.pC() == '.') {
         var1.kS++;
         ArrayList var10 = new ArrayList();
         avq.Sv var11 = new avq.Sv();
         var1.wS |= 4;
         if (!this.pC(var1, var11, var10, false)) {
            return null;
         } else {
            avt var12 = new avt(Strings.ff("%s", Strings.safe(var11.pC)));
            var1.UT = var12;
            return var1.UT;
         }
      } else if (var1.pC() != '?') {
         return null;
      } else {
         var1.kS++;
         if (var1.pC() == '?' && (var1.A[var1.kS + 1] != '$' || var1.A[var1.kS + 2] == '?')) {
            if (var1.A[var1.kS + 1] == '$') {
               var3 = 6;
               var1.kS += 2;
            }

            var1.kS++;
            Couple var9 = this.wS(var1);
            var3 = var9.getFirst() != 0 ? (Integer)var9.getFirst() : var3;
            String var5 = (String)var9.getSecond();
            var1.kS++;
            switch (var3) {
               case 1:
               case 2:
                  var1.ys.add("--null--");
                  break;
               case 4:
                  avt var6 = this.A(var1);
                  if (var6 == null) {
                     var6 = new avt(var5);
                  }

                  return var6;
               case 6:
                  ArrayList var7 = new ArrayList();
                  String var8 = this.pC(var1, var7, false, '<', '>');
                  if (var8 != null) {
                     var5 = Strings.ff("%s%s", var5, var8);
                  }

                  var1.E.clear();
               case 3:
               case 5:
               default:
                  var1.ys.add(var5);
            }
         } else {
            if (var1.pC() == '$') {
               var1.kS++;
               String var4 = this.ys(var1);
               if (var4 == null) {
                  return null;
               }

               return new avt(var4);
            }

            if (var1.pC() == '?' && var1.A[var1.kS + 1] == '$') {
               var3 = 5;
            }
         }

         switch (var1.pC()) {
            case '$':
               break;
            case '@':
               var1.kS++;
               break;
            default:
               if (!this.pC(var1, var3 == 0 || var3 == 1)) {
                  return null;
               }
         }

         switch (var3) {
            case 0:
            case 4:
            default:
               break;
            case 1:
            case 2:
               if (var1.ys.size() <= 1) {
                  return null;
               }

               if (var3 == 1) {
                  var1.ys.set(0, (String)var1.ys.get(1));
               } else {
                  var1.ys.set(0, Strings.ff("~%s", var1.ys.get(1)));
               }

               var1.wS |= 4;
               break;
            case 3:
               var1.wS &= -5;
               break;
            case 5:
               var1.sY++;
         }

         if (var1.pC() >= '0' && var1.pC() <= '9') {
            if (!this.A(var1, var2)) {
               return null;
            }
         } else {
            if ((var1.pC() < 'A' || var1.pC() > 'Z') && var1.pC() != '$') {
               return null;
            }

            if (!this.pC(var1, var3 == 3, var2)) {
               return null;
            }
         }

         return var1.UT;
      }
   }

   private avt A(avq.Av var1) {
      if (var1.kS() != '@') {
         return null;
      } else if (var1.kS() != '_') {
         return null;
      } else {
         boolean var2;
         if (var1.pC() == '0') {
            var2 = false;
         } else {
            if (var1.pC() != '1') {
               return null;
            }

            var2 = true;
         }

         var1.kS++;
         String var3 = this.UT(var1);
         if (var3 != null && !var3.isEmpty()) {
            int var4 = Integer.parseInt(var3);
            if (var4 >= 0 && var4 >= (var2 ? 2 : 1)) {
               String var5 = this.UT(var1);
               if (var5 != null && !var5.isEmpty()) {
                  String var6 = null;
                  byte[] var7 = new byte[128];
                  if (var2) {
                     int var8 = 0;

                     while (var1.pC() != '@') {
                        Byte var9 = this.kS(var1);
                        if (var9 == null) {
                           break;
                        }

                        Byte var10 = this.kS(var1);
                        if (var10 == null) {
                           break;
                        }

                        var7[var8++] = var9;
                        var7[var8++] = var10;
                     }

                     try {
                        var6 = new String(var7, "UTF16");
                     } catch (UnsupportedEncodingException var12) {
                        return null;
                     }
                  } else {
                     int var14 = 0;

                     while (var14 < 128 && var1.pC() != '@') {
                        Byte var16 = this.kS(var1);
                        if (var16 == null) {
                           break;
                        }

                        var7[var14++] = var16;
                        if (!var1.A()) {
                           return null;
                        }
                     }

                     Charset var17;
                     if (var7.length < 4) {
                        var17 = Charset.forName("UTF-8");
                     } else {
                        if (var14 > 1 && var14 < 128) {
                           var7 = Arrays.copyOf(var7, var14);
                        }

                        var17 = Strings.determinePotentialEncoding(var7, 0, var7.length - 1);
                     }

                     if (var17 != null) {
                        var6 = new String(var7, var17);
                     } else {
                        try {
                           var6 = new String(var7, "UTF-8");
                        } catch (UnsupportedEncodingException var11) {
                        }
                     }
                  }

                  avt var15 = new avt(Strings.rtrim(var6, '\u0000'), true);
                  return var15.getFull().contains("\u0000") ? null : var15;
               } else {
                  return null;
               }
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   private Byte kS(avq.Av var1) {
      char var2 = var1.kS();
      if (var2 != '?') {
         return (byte)var2;
      } else {
         var2 = var1.kS();
         if (var2 == '$') {
            char var10 = var1.kS();
            char var4 = var1.kS();
            if (this.pC(var10) && this.pC(var4)) {
               char var5 = this.A(var10);
               char var6 = this.A(var4);
               return (byte)(var5 << 4 | var6);
            } else {
               return null;
            }
         } else if (Character.isDigit(var2)) {
            byte[] var9 = new byte[]{44, 47, 92, 58, 46, 32, 10, 9, 39, 45};
            return var9[var2 - '0'];
         } else if (var2 >= 'a' && var2 <= 'z') {
            byte[] var8 = new byte[]{
               -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -21, -20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6
            };
            return var8[var2 - 'a'];
         } else if (var2 >= 'A' && var2 <= 'Z') {
            byte[] var3 = new byte[]{
               -63, -62, -61, -60, -59, -58, -57, -56, -55, -54, -53, -52, -51, -50, -49, -48, -47, -46, -45, -44, -43, -42, -41, -40, -39, -38
            };
            return var3[var2 - 'A'];
         } else {
            return null;
         }
      }
   }

   private boolean pC(char var1) {
      return var1 >= 'A' && var1 <= 'P';
   }

   private char A(char var1) {
      return (char)(var1 <= 'J' ? var1 - 'A' : '\n' + var1 - 75);
   }

   private Couple wS(avq.Av var1) {
      byte var2 = 0;
      String var3 = null;
      switch (var1.pC()) {
         case '0':
            var2 = 1;
            break;
         case '1':
            var2 = 2;
            break;
         case '2':
            var3 = "operator new";
            break;
         case '3':
            var3 = "operator delete";
            break;
         case '4':
            var3 = "operator=";
            break;
         case '5':
            var3 = "operator>>";
            break;
         case '6':
            var3 = "operator<<";
            break;
         case '7':
            var3 = "operator!";
            break;
         case '8':
            var3 = "operator==";
            break;
         case '9':
            var3 = "operator!=";
            break;
         case ':':
         case ';':
         case '<':
         case '=':
         case '>':
         case '?':
         case '@':
         case '[':
         case '\\':
         case ']':
         case '^':
         default:
            throw new RuntimeException(Strings.ff("Unknown operator %c", var1.pC()));
         case 'A':
            var3 = "operator[]";
            break;
         case 'B':
            var3 = "operator ";
            var2 = 3;
            break;
         case 'C':
            var3 = "operator->";
            break;
         case 'D':
            var3 = "operator*";
            break;
         case 'E':
            var3 = "operator++";
            break;
         case 'F':
            var3 = "operator--";
            break;
         case 'G':
            var3 = "operator-";
            break;
         case 'H':
            var3 = "operator+";
            break;
         case 'I':
            var3 = "operator&";
            break;
         case 'J':
            var3 = "operator->*";
            break;
         case 'K':
            var3 = "operator/";
            break;
         case 'L':
            var3 = "operator%";
            break;
         case 'M':
            var3 = "operator<";
            break;
         case 'N':
            var3 = "operator<=";
            break;
         case 'O':
            var3 = "operator>";
            break;
         case 'P':
            var3 = "operator>=";
            break;
         case 'Q':
            var3 = "operator,";
            break;
         case 'R':
            var3 = "operator()";
            break;
         case 'S':
            var3 = "operator~";
            break;
         case 'T':
            var3 = "operator^";
            break;
         case 'U':
            var3 = "operator|";
            break;
         case 'V':
            var3 = "operator&&";
            break;
         case 'W':
            var3 = "operator||";
            break;
         case 'X':
            var3 = "operator*=";
            break;
         case 'Y':
            var3 = "operator+=";
            break;
         case 'Z':
            var3 = "operator-=";
            break;
         case '_':
            var1.kS++;
            switch (var1.pC()) {
               case '0':
                  var3 = "operator/=";
                  break;
               case '1':
                  var3 = "operator%=";
                  break;
               case '2':
                  var3 = "operator>>=";
                  break;
               case '3':
                  var3 = "operator<<=";
                  break;
               case '4':
                  var3 = "operator&=";
                  break;
               case '5':
                  var3 = "operator|=";
                  break;
               case '6':
                  var3 = "operator^=";
                  break;
               case '7':
                  var3 = "`vftable'";
                  break;
               case '8':
                  var3 = "`vbtable'";
                  break;
               case '9':
                  var3 = "`vcall'";
                  break;
               case ':':
               case ';':
               case '<':
               case '=':
               case '>':
               case '?':
               case '@':
               case 'Q':
               case 'W':
               case 'Z':
               case '[':
               case '\\':
               case ']':
               case '^':
               default:
                  throw new RuntimeException(Strings.ff("Unknown operator %c", var1.pC()));
               case 'A':
                  var3 = "`typeof'";
                  break;
               case 'B':
                  var3 = "`local static guard'";
                  break;
               case 'C':
                  var3 = "`string'";
                  var2 = 4;
                  break;
               case 'D':
                  var3 = "`vbase destructor'";
                  break;
               case 'E':
                  var3 = "`vector deleting destructor'";
                  break;
               case 'F':
                  var3 = "`default constructor closure'";
                  break;
               case 'G':
                  var3 = "`scalar deleting destructor'";
                  break;
               case 'H':
                  var3 = "`vector constructor iterator'";
                  break;
               case 'I':
                  var3 = "`vector destructor iterator'";
                  break;
               case 'J':
                  var3 = "`vector vbase constructor iterator'";
                  break;
               case 'K':
                  var3 = "`virtual displacement map'";
                  break;
               case 'L':
                  var3 = "`eh vector constructor iterator'";
                  break;
               case 'M':
                  var3 = "`eh vector destructor iterator'";
                  break;
               case 'N':
                  var3 = "`eh vector vbase constructor iterator'";
                  break;
               case 'O':
                  var3 = "`copy constructor closure'";
                  break;
               case 'P':
                  var1.kS++;
                  String var9 = (String)this.wS(var1).getSecond();
                  var3 = Strings.ff("`udt returning'%s", var9);
                  break;
               case 'R':
                  var1.wS |= 4;
                  var1.kS++;
                  switch (var1.pC()) {
                     case '0':
                        avq.Sv var8 = new avq.Sv();
                        ArrayList var10 = new ArrayList();
                        var1.kS++;
                        this.pC(var1, var8, var10, false);
                        var3 = Strings.ff("%s%s `RTTI Type Descriptor'", Strings.safe(var8.pC), Strings.safe(var8.A));
                        var1.kS--;
                        return new Couple(Integer.valueOf(var2), var3);
                     case '1':
                        var1.kS++;
                        String var4 = this.UT(var1);
                        String var5 = this.UT(var1);
                        String var6 = this.UT(var1);
                        String var7 = this.UT(var1);
                        var1.kS--;
                        var3 = Strings.ff(
                           "`RTTI Base Class Descriptor at (%s,%s,%s,%s)'", Strings.safe(var4), Strings.safe(var5), Strings.safe(var6), Strings.safe(var7)
                        );
                        return new Couple(Integer.valueOf(var2), var3);
                     case '2':
                        var3 = "`RTTI Base Class Array'";
                        return new Couple(Integer.valueOf(var2), var3);
                     case '3':
                        var3 = "`RTTI Class Hierarchy Descriptor'";
                        return new Couple(Integer.valueOf(var2), var3);
                     case '4':
                        var3 = "`RTTI Complete Object Locator'";
                        return new Couple(Integer.valueOf(var2), var3);
                     default:
                        throw new RuntimeException(Strings.ff("TBI: RTTI operator: %c", var1.pC()));
                  }
               case 'S':
                  var3 = "`local vftable'";
                  break;
               case 'T':
                  var3 = "`local vftable constructor closure'";
                  break;
               case 'U':
                  var3 = "operator new[]";
                  break;
               case 'V':
                  var3 = "operator delete[]";
                  break;
               case 'X':
                  var3 = "`placement delete closure'";
                  break;
               case 'Y':
                  var3 = "`placement delete[] closure'";
                  break;
               case '_':
                  var3 = "`dynamic atexit destructor for '";
            }
      }

      return new Couple(Integer.valueOf(var2), var3);
   }

   private boolean A(avq.Av var1, List var2) {
      String var3 = null;
      switch (var1.pC()) {
         case '0':
            var3 = "private: ";
            break;
         case '1':
            var3 = "protected: ";
            break;
         case '2':
            var3 = "public: ";
      }

      String var4 = null;
      if (var1.pC() >= '0' && var1.pC() <= '2') {
         var4 = "static ";
      }

      String var5 = this.pC(var1, 0);
      String var6 = null;
      avq.Sv var7 = new avq.Sv();
      char var8 = var1.pC();
      var1.kS++;
      switch (var8) {
         case '0':
         case '1':
         case '2':
         case '3':
         case '4':
         case '5':
            int var14 = var1.ys.size();
            Object var16 = var2 != null ? var2 : new ArrayList();
            if (!this.pC(var1, var7, (List)var16, false)) {
               return false;
            }

            StringBuilder var18 = new StringBuilder();
            StringBuilder var12 = new StringBuilder();
            if (!this.pC(var1, var18, var12)) {
               return false;
            }

            var6 = var18.toString();
            String var13 = var12.toString();
            if (!var6.isEmpty() && !var13.isEmpty()) {
               var6 = Strings.ff("%s %s", var6, var13);
            } else if (var6.isEmpty()) {
               var6 = var13;
            }

            while (var1.ys.size() > var14) {
               var1.ys.remove(var1.ys.size() - 1);
            }
            break;
         case '6':
         case '7':
            var7.pC = null;
            var7.A = null;
            StringBuilder var9 = new StringBuilder();
            StringBuilder var10 = new StringBuilder();
            if (!this.pC(var1, var9, var10)) {
               return false;
            }

            var6 = var9.toString();
            if (var1.pC() != '@') {
               String var11 = this.E(var1);
               if (var11 == null) {
                  return false;
               }

               var7.A = Strings.ff("{for `%s'}", var11);
            }
            break;
         case '8':
         case '9':
            var7.A = null;
            var7.pC = null;
            break;
         default:
            return false;
      }

      String var15 = Strings.ff(
         "%s%s%s%s%s%s%s%s",
         Strings.safe(var3),
         Strings.safe(var4),
         Strings.safe(var7.pC),
         var6 != null && !var6.isEmpty() && var7.pC != null ? " " : "",
         Strings.safe(var6),
         var6 == null && var7.pC == null ? "" : " ",
         Strings.safe(var5),
         Strings.safe(var7.A)
      );
      avt var17 = new avt(var15);
      var1.UT = var17;
      return true;
   }

   private boolean pC(avq.Av var1, boolean var2, List var3) {
      String var5 = null;
      int var6 = -1;
      String var7 = null;
      String var9 = null;
      avq.Sv var10 = new avq.Sv();
      Object var11 = var3 != null ? var3 : new ArrayList();
      String var12 = null;
      boolean var13 = true;
      boolean var14 = true;
      if (var1.pC.substring(var1.kS, var1.kS + 3).equals("$$F") || var1.pC.substring(var1.kS, var1.kS + 3).equals("$$H")) {
         var1.kS += 3;
      }

      char var4 = var1.pC();
      var1.kS++;
      if (var4 == '$') {
         if (var1.pC() >= '0' && var1.pC() <= '5') {
            var6 = (var1.pC() - '0') / 2;
         } else if (var1.pC() == 'R') {
            var6 = (var1.A[var1.kS + 1] - '0') / 2;
         } else if (var1.pC() != 'B') {
            return false;
         }
      } else {
         if (var4 < 'A' || var4 > 'Z') {
            return false;
         }

         var6 = (var4 - 'A') / 8;
      }

      switch (var6) {
         case 0:
            var5 = "private: ";
            break;
         case 1:
            var5 = "protected: ";
            break;
         case 2:
            var5 = "public: ";
      }

      if (var4 == '$' || (var4 - 'A') % 8 == 6 || (var4 - 'A') % 8 == 7) {
         var5 = Strings.ff("[thunk]:%s", var5 != null ? var5 : " ");
      }

      if (var4 == '$' && var1.pC() != 'B') {
         var7 = "virtual ";
      } else if (var4 <= 'X') {
         switch ((var4 - 'A') % 8) {
            case 2:
            case 3:
               var7 = "static ";
               break;
            case 4:
            case 5:
            case 6:
            case 7:
               var7 = "virtual ";
         }
      }

      String var8 = this.pC(var1, 0);
      if (var4 == '$' && var1.pC() == 'B') {
         var1.kS++;
         String var25 = this.UT(var1);
         if (var25 == null || var1.pC() != 'A') {
            return false;
         }

         var1.kS++;
         var8 = Strings.ff("%s{%s,{flat}}' }'", Strings.safe(var8), Strings.safe(var25));
         var13 = false;
         var14 = false;
      } else if (var4 == '$' && var1.pC() == 'R') {
         var1.kS += 2;
         String var24 = this.UT(var1);
         String var28 = this.UT(var1);
         String var17 = this.UT(var1);
         String var18 = this.UT(var1);
         if (var24 == null || var28 == null || var17 == null || var18 == null) {
            return false;
         }

         var8 = Strings.ff(
            "%s`vtordispex{%s,%s,%s,%s}' ", Strings.safe(var8), Strings.safe(var24), Strings.safe(var28), Strings.safe(var17), Strings.safe(var18)
         );
      } else if (var4 == '$') {
         var1.kS++;
         String var15 = this.UT(var1);
         String var16 = this.UT(var1);
         if (var15 == null || var16 == null) {
            return false;
         }

         var8 = Strings.ff("%s`vtordisp{%s,%s}' ", Strings.safe(var8), Strings.safe(var15), Strings.safe(var16));
      } else if ((var4 - 'A') % 8 == 6 || (var4 - 'A') % 8 == 7) {
         var8 = Strings.ff("%s`adjustor{%s}' ", Strings.safe(var8), Strings.safe(this.UT(var1)));
      }

      if (var13 && (var4 == '$' || var4 <= 'X' && (var4 - 'A') % 8 != 2 && (var4 - 'A') % 8 != 3)) {
         StringBuilder var26 = new StringBuilder();
         StringBuilder var29 = new StringBuilder();
         if (!this.pC(var1, var26, var29)) {
            return false;
         }

         if (var26.length() != 0 || var29.length() != 0) {
            var9 = Strings.ff("%s %s", Strings.safe(var26.toString()), Strings.safe(var29.toString()));
         }
      }

      StringBuilder var27 = new StringBuilder();
      StringBuilder var30 = new StringBuilder();
      if (!this.pC(var1.pC(), var27, var30)) {
         return false;
      } else {
         var1.kS++;
         String var31 = var27.toString();
         String var32 = var30.toString();
         if (var14 && var1.pC() == '@') {
            var1.kS++;
         } else if (var14 && !this.pC(var1, var10, (List)var11, false)) {
            return false;
         }

         if (!var14 || (var1.wS & 4) != 0) {
            var10.pC = null;
            var10.A = null;
         }

         if (var2) {
            var8 = Strings.ff("%s%s%s", Strings.safe(var8), Strings.safe(var10.pC), Strings.safe(var10.A));
            var10.pC = null;
            var10.A = null;
         }

         int var19 = var1.ys.size();
         if (var13) {
            var12 = this.pC(var1, (List)var11, true, '(', ')');
            if (var12 == null) {
               return false;
            }
         }

         while (var1.ys.size() > var19) {
            var1.ys.remove(var1.ys.size() - 1);
         }

         List var20 = null;
         if (var12 != null && var12.length() > 2) {
            var20 = Arrays.asList(var12.substring(1, var12.length() - 1).split(","));
         }

         String var21 = null;
         if (var10.A == null) {
            var21 = var10.pC;
         }

         String var22 = Strings.ff(
            "%s%s%s%s%s%s%s%s%s%s%s",
            Strings.safe(var5),
            Strings.safe(var7),
            Strings.safe(var10.pC),
            var10.pC != null && var10.A == null ? " " : "",
            Strings.safe(var31),
            !var31.isEmpty() ? " " : "",
            Strings.safe(var32),
            Strings.safe(var8),
            Strings.safe(var12),
            Strings.safe(var9),
            Strings.safe(var10.A)
         );
         avs var23 = new avs(var8, var21, var20, null, var22);
         var23.A = var31;
         var1.UT = var23;
         return true;
      }
   }

   private boolean pC(avq.Av var1, avq.Sv var2, List var3, boolean var4) {
      boolean var6;
      char var5 = var1.pC();
      var1.kS++;
      var6 = true;
      label589:
      switch (var5) {
         case '$':
            char var28 = var1.pC();
            var1.kS++;
            switch (var28) {
               case '$':
                  if (var1.pC() == 'B') {
                     var1.kS++;
                     int var38 = var1.ys.size();
                     avq.Sv var47 = new avq.Sv();
                     String var55 = null;
                     if (var1.pC() == 'Y') {
                        var1.kS++;
                        String var62 = this.UT(var1);
                        if (var62 == null) {
                           return var2.pC != null;
                        }

                        int var68 = Integer.parseInt(var62);

                        while (var68-- != 0) {
                           var55 = Strings.ff("%s[%s]", Strings.safe(var55), this.UT(var1));
                        }
                     }

                     if (!this.pC(var1, var47, var3, false)) {
                        return var2.pC != null;
                     }

                     if (var55 != null) {
                        var2.pC = Strings.ff("%s %s", Strings.safe(var47.pC), var55);
                     } else {
                        var2.pC = var47.pC;
                     }

                     var2.A = var47.A;

                     while (true) {
                        if (var1.ys.size() <= var38) {
                           break label589;
                        }

                        var1.ys.remove(var1.ys.size() - 1);
                     }
                  } else {
                     if (var1.pC() == 'C' || var1.pC() == 'Q' || var1.pC() == 'R') {
                        String var39 = null;
                        if (var1.pC() == 'Q') {
                           var39 = "&&";
                        } else if (var1.pC() == 'R') {
                           var39 = "&& volatile";
                        }

                        var1.kS++;
                        StringBuilder var48 = new StringBuilder();
                        StringBuilder var56 = new StringBuilder();
                        if (!this.pC(var1, var48, var56)) {
                           return var2.pC != null;
                        }

                        if (var2.pC == null && !this.pC(var1, var2, var3, var4)) {
                           return var2.pC != null;
                        }

                        String var63 = var48.toString();
                        String var69 = var56.toString();
                        var2.pC = Strings.ff(
                           "%s%s%s%s%s%s%s",
                           Strings.safe(var2.pC),
                           !var63.isEmpty() ? " " : "",
                           Strings.safe(var63),
                           var39 != null ? " " : "",
                           Strings.safe(var39),
                           !var69.isEmpty() ? " " : "",
                           Strings.safe(var69)
                        );
                     } else if (var1.pC() == '$') {
                        var1.kS++;
                        if (var1.pC() != 'V') {
                           throw new RuntimeException();
                        }

                        var1.kS++;
                        var2.pC = "";
                     } else if (var1.pC() == 'V') {
                        var1.kS++;
                        var2.pC = "";
                     } else {
                        if (var1.pC() != 'T') {
                           throw new RuntimeException();
                        }

                        var1.kS++;
                        var2.pC = "std::nullptr_t";
                     }
                     break label589;
                  }
               case '0':
                  var2.pC = this.UT(var1);
                  if (var2.pC == null) {
                     return false;
                  }
                  break label589;
               case '1':
                  ArrayList var37 = new ArrayList(var1.ys);
                  int var46 = var1.sY;
                  int var54 = var1.E.size();
                  var1.ys.clear();
                  int var61 = var1.wS;
                  var1.wS = 0;
                  IUnmangledData var67 = this.pC(var1, var3);
                  if (var67 == null) {
                     return false;
                  }

                  var6 = false;
                  var2.pC = Strings.ff("&%s", var67.getFull());
                  var1.sY = var46;

                  while (var1.E.size() > var54) {
                     var1.E.remove(var1.E.size() - 1);
                  }

                  var1.ys = var37;
                  var1.wS = var61;
                  break label589;
               case 'D':
                  String var36 = this.UT(var1);
                  if (var36 == null) {
                     return var2.pC != null;
                  }

                  var2.pC = Strings.ff("`template-parameter%s'", var36);
                  break label589;
               case 'F':
                  String var35 = this.UT(var1);
                  if (var35 == null) {
                     return var2.pC != null;
                  }

                  String var45 = this.UT(var1);
                  if (var45 == null) {
                     return var2.pC != null;
                  }

                  var2.pC = Strings.ff("{%s,%s}", var35, var45);
                  break label589;
               case 'G':
                  String var34 = this.UT(var1);
                  if (var34 == null) {
                     return var2.pC != null;
                  }

                  String var44 = this.UT(var1);
                  if (var44 == null) {
                     return var2.pC != null;
                  }

                  String var53 = this.UT(var1);
                  if (var53 == null) {
                     return var2.pC != null;
                  }

                  var2.pC = Strings.ff("{%s,%s,%s}", var34, var44, var53);
                  break label589;
               case 'Q':
                  String var33 = this.UT(var1);
                  if (var33 == null) {
                     return var2.pC != null;
                  }

                  var2.pC = Strings.ff("`non-type-template-parameter%s'", var33);
                  break label589;
               default:
                  throw new RuntimeException();
            }
         case '%':
         case '&':
         case '\'':
         case '(':
         case ')':
         case '*':
         case '+':
         case ',':
         case '-':
         case '.':
         case '/':
         case ':':
         case ';':
         case '<':
         case '=':
         case '>':
         case '@':
         case 'L':
         case '[':
         case '\\':
         case ']':
         case '^':
         default:
            throw new RuntimeException(Strings.ff("Unknown type %c", var5));
         case '0':
         case '1':
         case '2':
         case '3':
         case '4':
         case '5':
         case '6':
         case '7':
         case '8':
         case '9':
            if (var3 == null) {
               return var2.pC != null;
            }

            var2.pC = (String)var3.get((var5 - '0') * 2);
            var2.A = (String)var3.get((var5 - '0') * 2 + 1);
            var6 = false;
            break;
         case '?':
            if (var4) {
               String var27 = this.UT(var1);
               if (var27 == null) {
                  return var2.pC != null;
               }

               var2.pC = Strings.ff("`template-parameter-%s'", var27);
            } else if (!this.pC(var2, var1, var3, '?', var4)) {
               return var2.pC != null;
            }
            break;
         case 'A':
         case 'B':
            if (!this.pC(var2, var1, var3, var5, var4)) {
               return var2.pC != null;
            }
            break;
         case 'C':
         case 'D':
         case 'E':
         case 'F':
         case 'G':
         case 'H':
         case 'I':
         case 'J':
         case 'K':
         case 'M':
         case 'N':
         case 'O':
         case 'X':
         case 'Z':
            var2.pC = this.wS(var5);
            var6 = false;
            break;
         case 'P':
            if (Character.isDigit(var1.pC())) {
               if (var1.pC() == '8') {
                  var1.kS++;
                  int var25 = var1.ys.size();
                  String var31 = this.E(var1);
                  if (var31 == null) {
                     return var2.pC != null;
                  }

                  StringBuilder var42 = new StringBuilder();
                  StringBuilder var51 = new StringBuilder();
                  if (!this.pC(var1, var42, var51)) {
                     return var2.pC != null;
                  }

                  String var59 = var42.toString();
                  String var65 = var51.toString();
                  if (!var59.isEmpty()) {
                     var59 = Strings.ff("%s %s", var59, Strings.safe(var65));
                  } else if (!var65.isEmpty()) {
                     var59 = Strings.ff(" %s", var65);
                  }

                  StringBuilder var70 = new StringBuilder();
                  StringBuilder var71 = new StringBuilder();
                  if (!this.pC(var1.pC(), var70, var71)) {
                     return var2.pC != null;
                  }

                  var1.kS++;
                  String var72 = var70.toString();
                  avq.Sv var73 = new avq.Sv();
                  if (!this.pC(var1, var73, var3, false)) {
                     return var2.pC != null;
                  }

                  String var74 = this.pC(var1, var3, true, '(', ')');
                  if (var74 == null) {
                     return var2.pC != null;
                  }

                  while (var1.ys.size() > var25) {
                     var1.ys.remove(var1.ys.size() - 1);
                  }

                  var2.pC = Strings.ff("%s%s (%s %s::*", Strings.safe(var73.pC), Strings.safe(var73.A), Strings.safe(var72), Strings.safe(var31));
                  var2.A = Strings.ff(")%s%s", Strings.safe(var74), Strings.safe(var59));
               } else {
                  if (var1.pC() != '6') {
                     return var2.pC != null;
                  }

                  var1.kS++;
                  int var26 = var1.ys.size();
                  StringBuilder var32 = new StringBuilder();
                  StringBuilder var43 = new StringBuilder();
                  if (!this.pC(var1.pC(), var32, var43)) {
                     return var2.pC != null;
                  }

                  var1.kS++;
                  String var52 = var32.toString();
                  avq.Sv var60 = new avq.Sv();
                  if (!this.pC(var1, var60, var3, false)) {
                     return var2.pC != null;
                  }

                  String var66 = this.pC(var1, var3, true, '(', ')');
                  if (var66 == null) {
                     return var2.pC != null;
                  }

                  while (var1.ys.size() > var26) {
                     var1.ys.remove(var1.ys.size() - 1);
                  }

                  var2.pC = Strings.ff("%s%s (%s*", Strings.safe(var60.pC), Strings.safe(var60.A), Strings.safe(var52));
                  var2.A = Strings.ff(")%s", Strings.safe(var66));
               }
            } else if (!this.pC(var2, var1, var3, 'P', var4)) {
               return var2.pC != null;
            }
            break;
         case 'Q':
            if (var1.pC() == '8') {
               var1.kS++;
               int var24 = var1.ys.size();
               String var30 = this.E(var1);
               if (var30 == null) {
                  return var2.pC != null;
               }

               StringBuilder var41 = new StringBuilder();
               StringBuilder var50 = new StringBuilder();
               if (!this.pC(var1, var41, var50)) {
                  return var2.pC != null;
               }

               String var58 = var41.toString();
               String var64 = var50.toString();
               if (!var58.isEmpty()) {
                  var58 = Strings.ff("%s %s", var58, Strings.safe(var64));
               } else if (!var64.isEmpty()) {
                  var58 = Strings.ff(" %s", var64);
               }

               StringBuilder var15 = new StringBuilder();
               StringBuilder var16 = new StringBuilder();
               if (!this.pC(var1.pC(), var15, var16)) {
                  return var2.pC != null;
               }

               var1.kS++;
               String var17 = var15.toString();
               avq.Sv var18 = new avq.Sv();
               if (!this.pC(var1, var18, var3, false)) {
                  return var2.pC != null;
               }

               String var19 = this.pC(var1, var3, true, '(', ')');
               if (var19 == null) {
                  return var2.pC != null;
               }

               while (var1.ys.size() > var24) {
                  var1.ys.remove(var1.ys.size() - 1);
               }

               var2.pC = Strings.ff("%s%s (%s %s::*", Strings.safe(var18.pC), Strings.safe(var18.A), Strings.safe(var17), Strings.safe(var30));
               var2.A = Strings.ff(")%s%s", Strings.safe(var19), Strings.safe(var58));
               break;
            } else if (var1.pC() == '6') {
               var1.kS++;
               int var23 = var1.ys.size();
               StringBuilder var29 = new StringBuilder();
               StringBuilder var40 = new StringBuilder();
               if (!this.pC(var1.pC(), var29, var40)) {
                  return var2.pC != null;
               }

               var1.kS++;
               String var49 = var29.toString();
               avq.Sv var57 = new avq.Sv();
               if (!this.pC(var1, var57, var3, false)) {
                  return var2.pC != null;
               }

               String var14 = this.pC(var1, var3, true, '(', ')');
               if (var14 == null) {
                  return var2.pC != null;
               }

               while (var1.ys.size() > var23) {
                  var1.ys.remove(var1.ys.size() - 1);
               }

               var2.pC = Strings.ff("%s%s (%s*", Strings.safe(var57.pC), Strings.safe(var57.A), Strings.safe(var49));
               var2.A = Strings.ff(")%s", Strings.safe(var14));
               break;
            }
         case 'R':
         case 'S':
            if (!this.pC(var2, var1, var3, var4 ? var5 : 'P', var4)) {
               return var2.pC != null;
            }
            break;
         case 'T':
         case 'U':
         case 'V':
         case 'Y':
            String var20 = this.E(var1);
            if (var20 == null) {
               return var2.pC != null;
            }
            var2.pC = Strings.ff("%s%s", Strings.safe(switch (var5) {
               case 'T' -> "union ";
               case 'U' -> "struct ";
               case 'V' -> "class ";
               default -> throw new RuntimeException();
               case 'Y' -> "cointerface ";
            }), Strings.safe(var20));
            break;
         case 'W':
            if (var1.pC() != '4') {
               return var2.pC != null;
            }

            var1.kS++;
            String var22 = this.E(var1);
            if (var22 == null) {
               return var2.pC != null;
            }

            var2.pC = Strings.ff("enum %s", var22);
            break;
         case '_':
            if (var1.pC() == '$') {
               var1.kS++;
               if (!this.pC(var1, var2, var3, var4)) {
                  return false;
               }

               var2.pC = Strings.ff("__w64 %s", var2.pC);
            } else if (var1.pC() == 'O') {
               int var7 = 1;
               var1.kS++;
               StringBuilder var8 = new StringBuilder();
               StringBuilder var9 = new StringBuilder();
               if (!this.pC(var1, var8, var9)) {
                  return false;
               }

               String var10 = var8.toString();
               String var11 = var9.toString();
               if (!var10.isEmpty() && !var11.isEmpty()) {
                  var10 = Strings.ff("%s %s", var10, var11);
               }

               while (var1.pC.substring(var1.kS, var1.kS + 2).equals("_O")) {
                  var7++;
                  var1.kS += 2;
                  if (!this.pC(var1, var8, var9)) {
                     return false;
                  }
               }

               if (!this.pC(var1, var2, var3, var4)) {
                  return false;
               }

               StringBuilder var12 = new StringBuilder();

               for (int var13 = 0; var13 < var7; var13++) {
                  var12.append("[]");
               }

               var2.pC = Strings.ff("%s%s%s%s", Strings.safe(var2.pC), var10.isEmpty() ? "" : " ", Strings.safe(var10), var10.isEmpty() ? "" : " ");
               var2.A = Strings.ff("%s%s", Strings.safe(var2.A), var12.toString());
            } else {
               var2.pC = this.kS(var1.pC());
               var1.kS++;
            }
      }

      if (var6 && var3 != null && var4) {
         var3.add(var2.pC != null ? var2.pC : "");
         var3.add(var2.A != null ? var2.A : "");
      }

      return var2.pC != null;
   }

   private String kS(char var1) {
      return switch (var1) {
         case 'D' -> "__int8";
         case 'E' -> "unsigned __int8";
         case 'F' -> "__int16";
         case 'G' -> "unsigned __int16";
         case 'H' -> "__int32";
         case 'I' -> "unsigned __int32";
         case 'J' -> "__int64";
         case 'K' -> "unsigned __int64";
         case 'L' -> "__int128";
         case 'M' -> "unsigned __int128";
         case 'N' -> "bool";
         default -> throw new RuntimeException();
         case 'S' -> "char16_t";
         case 'U' -> "char32_t";
         case 'W' -> "wchar_t";
      };
   }

   private String wS(char var1) {
      return switch (var1) {
         case 'C' -> "signed char";
         case 'D' -> "char";
         case 'E' -> "unsigned char";
         case 'F' -> "short";
         case 'G' -> "unsigned short";
         case 'H' -> "int";
         case 'I' -> "unsigned int";
         case 'J' -> "long";
         case 'K' -> "unsigned long";
         default -> throw new RuntimeException();
         case 'M' -> "float";
         case 'N' -> "double";
         case 'O' -> "long double";
         case 'X' -> "void";
         case 'Z' -> "...";
      };
   }

   private boolean pC(char var1, StringBuilder var2, StringBuilder var3) {
      switch (var1) {
         case 'A':
         case 'B':
            var2.append("__cdecl");
            break;
         case 'C':
         case 'D':
            var2.append("__pascal");
            break;
         case 'E':
         case 'F':
            var2.append("__thiscall");
            break;
         case 'G':
         case 'H':
            var2.append("__stdcall");
            break;
         case 'I':
         case 'J':
            var2.append("__fastcall");
         case 'K':
         case 'L':
            break;
         case 'M':
         case 'N':
            var2.append("__clrcall");
            break;
         case 'O':
         case 'P':
            var2.append("__eabi");
            break;
         case 'Q':
            var2.append("__vectorcall");
            break;
         default:
            throw new RuntimeException();
      }

      return true;
   }

   private boolean pC(avq.Av var1, StringBuilder var2, StringBuilder var3) {
      if (var1.pC() == 'E') {
         var3.append("__ptr64");
         var1.kS++;
      }

      if (var1.pC() == 'I') {
         var3.append("__restrict");
         var1.kS++;
      }

      switch (var1.pC()) {
         case '$':
            var1.kS++;
            var1.pC();
            throw new RuntimeException(Strings.ff("Unknown $-type modifier %c", var1.pC()));
         case 'A':
            break;
         case 'B':
            var2.append("const");
            break;
         case 'C':
            var2.append("volatile");
            break;
         case 'D':
            var2.append("const volatile");
            break;
         default:
            throw new RuntimeException(Strings.ff("Unknown modifier %c", var1.pC()));
      }

      var1.kS++;
      return true;
   }

   private boolean pC(avq.Sv var1, avq.Av var2, List var3, char var4, boolean var5) {
      String var6 = "";
      if (var2.pC() == 'E') {
         var6 = " __ptr64";
         var2.kS++;
      }

      if (var2.pC() == 'I') {
         var6 = " __restrict";
         var2.kS++;
      }
      String var7 = switch (var4) {
         case '?' -> "";
         default -> throw new RuntimeException();
         case 'A' -> Strings.ff(" &%s", var6);
         case 'B' -> Strings.ff(" &%s volatile", var6);
         case 'P' -> Strings.ff(" *%s", var6);
         case 'Q' -> Strings.ff(" *%s const", var6);
         case 'R' -> Strings.ff(" *%s volatile", var6);
         case 'S' -> Strings.ff(" *%s const volatile", var6);
      };
      StringBuilder var8 = new StringBuilder();
      StringBuilder var9 = new StringBuilder();
      if (this.pC(var2, var8, var9)) {
         String var10 = var8.toString();
         avq.Sv var11 = new avq.Sv();
         int var12 = var2.ys.size();
         if (var2.pC() == 'Y') {
            var2.kS++;
            String var13 = this.UT(var2);
            if (var13 == null) {
               return false;
            }

            int var14 = Integer.parseInt(var13);
            if (var7.charAt(0) == ' ' && var10.isEmpty()) {
               var7 = var7.substring(1);
            }

            if (!var10.isEmpty()) {
               var7 = Strings.ff(" (%s%s)", Strings.safe(var10), Strings.safe(var7));
               var10 = "";
            } else {
               var7 = Strings.ff(" (%s)", Strings.safe(var7));
            }

            while (var14-- != 0) {
               var7 = Strings.ff("%s[%s]", Strings.safe(var7), this.UT(var2));
            }
         }

         if (!this.pC(var2, var11, var3, false)) {
            return false;
         }

         if (!var10.isEmpty()) {
            var1.pC = Strings.ff("%s %s%s", Strings.safe(var11.pC), Strings.safe(var10), Strings.safe(var7));
         } else {
            if (!var5 && var7.length() > 1 && var7.charAt(0) == ' ' && var7.charAt(1) == '*' && var11.pC.endsWith("*")) {
               var7 = var7.substring(1);
            }

            var1.pC = Strings.ff("%s%s", Strings.safe(var11.pC), Strings.safe(var7));
         }

         var1.A = var11.A;

         while (var2.ys.size() > var12) {
            var2.ys.remove(var2.ys.size() - 1);
         }
      }

      return true;
   }

   private String UT(avq.Av var1) {
      StringBuilder var2 = new StringBuilder();
      if (var1.pC() == '?') {
         var2.append("-");
         var1.kS++;
      }

      if (var1.pC() >= '0' && var1.pC() <= '9') {
         var2.append(Character.getNumericValue(var1.pC()) + 1);
         var1.kS++;
      } else {
         if (var1.pC() < 'A' || var1.pC() > 'P') {
            return null;
         }

         long var3;
         for (var3 = 0L; var1.pC() >= 'A' && var1.pC() <= 'P'; var1.kS++) {
            var3 *= 16L;
            var3 += var1.pC() - 'A';
         }

         if (var1.pC() != '@') {
            return null;
         }

         var2.append(var3);
         var1.kS++;
      }

      return var2.toString();
   }

   private String pC(avq.Av var1, List var2, boolean var3, char var4, char var5) {
      String var6 = null;
      ArrayList var7 = new ArrayList();

      while (var1.kS < var1.A.length) {
         avq.Sv var8 = new avq.Sv();
         if (var1.pC() == '@') {
            var1.kS++;
            break;
         }

         if (!this.pC(var1, var8, var2, true)) {
            return null;
         }

         if (var3 && var8.pC.equals("void")) {
            break;
         }

         String var9 = Strings.ff("%s%s", Strings.safe(var8.pC), Strings.safe(var8.A));
         var7.add(var9);
         if (var8.pC.equals("...")) {
            break;
         }
      }

      if (var3) {
         if (var1.pC() != 'Z') {
            return null;
         }

         var1.kS++;
      }

      if (var7.size() != 0 && (var7.size() != 1 || !((String)var7.get(0)).equals("void"))) {
         for (int var11 = 1; var11 < var7.size(); var11++) {
            var6 = Strings.ff("%s,%s", Strings.safe(var6), Strings.safe(var7.get(var11)));
         }

         String var12 = var6 != null ? var6 : (String)var7.get(0);
         if (var5 == '>' && var12.endsWith(">")) {
            var6 = Strings.ff("%c%s%s %c", var4, Strings.safe(var7.get(0)), Strings.safe(var6), var5);
         } else {
            var6 = Strings.ff("%c%s%s%c", var4, Strings.safe(var7.get(0)), Strings.safe(var6), var5);
         }

         return var6;
      } else {
         return Strings.ff("%cvoid%c", var4, var5);
      }
   }

   private String E(avq.Av var1) {
      int var2 = var1.ys.size();
      String var3 = null;
      if (this.pC(var1, false)) {
         var3 = this.pC(var1, var2);
      }

      while (var1.ys.size() > var2) {
         var1.ys.remove(var1.ys.size() - 1);
      }

      return var3;
   }

   private String pC(avq.Av var1, int var2) {
      StringBuilder var3 = new StringBuilder();

      for (int var4 = var1.ys.size() - 1; var4 >= var2; var4--) {
         var3.append((String)var1.ys.get(var4));
         if (var4 > var2) {
            var3.append("::");
         }
      }

      return var3.toString();
   }

   private boolean pC(avq.Av var1, boolean var2) {
      while (var1.pC() != '@') {
         String var3;
         label38:
         switch (var1.pC()) {
            case '\u0000':
               return false;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
               var1.pC();
               var3 = (String)var1.E.get(var1.sY + var1.pC() - 48);
               var1.kS++;
               break;
            case '?':
               var1.kS++;
               switch (var1.pC()) {
                  case '$':
                     var1.kS++;
                     var3 = this.ys(var1);
                     var1.E.add(var3);
                     break label38;
                  case '?':
                     ArrayList var4 = new ArrayList(var1.ys);
                     int var5 = var1.sY;
                     int var6 = var1.E.size();
                     var1.ys.clear();
                     IUnmangledData var7 = this.pC(var1);
                     if (var7 == null) {
                        return false;
                     }

                     var3 = Strings.ff("`%s'", var7.getFull());
                     var1.sY = var5;

                     while (var1.E.size() > var6) {
                        var1.E.remove(var1.E.size() - 1);
                     }

                     var1.ys = var4;
                     break label38;
                  default:
                     var3 = this.UT(var1);
                     if (var3 == null) {
                        return false;
                     }

                     var3 = Strings.ff("`%s'", var3);
                     break label38;
               }
            default:
               var3 = this.sY(var1);
         }

         if (var3 == null) {
            return false;
         }

         var1.ys.add(var3);
      }

      var1.kS++;
      return true;
   }

   private String sY(avq.Av var1) {
      int var2 = var1.kS;

      while (
         var1.pC() >= 'A' && var1.pC() <= 'Z'
            || var1.pC() >= 'a' && var1.pC() <= 'z'
            || var1.pC() >= '0' && var1.pC() <= '9'
            || var1.pC() == '_'
            || var1.pC() == '$'
      ) {
         var1.kS++;
         if (var1.pC() == '@') {
            var1.E.add(var1.pC.substring(var2, var1.kS));
            var1.kS++;
            return (String)var1.E.get(var1.E.size() - 1);
         }
      }

      return null;
   }

   private String ys(avq.Av var1) {
      int var2 = var1.E.size();
      int var3 = var1.sY;
      int var4 = var1.ys.size();
      var1.sY = var1.E.size();
      String var5 = this.sY(var1);
      if (var5 == null) {
         var1.sY = var3;
         return null;
      } else {
         ArrayList var6 = new ArrayList();
         String var7 = this.pC(var1, var6, false, '<', '>');
         if (var7 == null) {
            return null;
         } else {
            var5 = Strings.ff("%s%s", Strings.safe(var5), Strings.safe(var7));

            while (var1.E.size() > var2) {
               var1.E.remove(var1.E.size() - 1);
            }

            var1.sY = var3;

            while (var1.ys.size() > var4) {
               var1.ys.remove(var1.ys.size() - 1);
            }

            return var5;
         }
      }
   }

   private static class Av {
      String pC;
      char[] A;
      int kS;
      int wS;
      IUnmangledData UT;
      List E;
      int sY = 0;
      List ys;

      Av(String var1) {
         this.pC = var1;
         this.A = this.pC.toCharArray();
         this.kS = 0;
         this.E = new ArrayList();
         this.ys = new ArrayList();
      }

      char pC() {
         return this.A[this.kS];
      }

      boolean A() {
         return this.kS < this.A.length;
      }

      char kS() {
         return this.A[this.kS++];
      }
   }

   private static class Sv {
      String pC = null;
      String A = null;
   }
}
