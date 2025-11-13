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

public class ayn implements IManglingEngine {
   private static final ILogger q = GlobalLog.getLogger(ayn.class);
   private boolean RF;
   private static final int xK = 4;

   @Override
   public IUnmangledData unmangle(String var1) {
      ayn.eo var2 = new ayn.eo(var1);

      IUnmangledData var3;
      try {
         var3 = this.q(var2);
      } catch (Exception var4) {
         if (!this.RF) {
            this.RF = true;
            JebCoreService.notifySilentExceptionToClient(new UnmanglerException(Strings.ff("MSVCPP - on:%s", var1)));
         }

         return null;
      }

      return var3 == null || var3.getFull() == null || var3.isRawData() || !var3.getFull().contains("@") && !var3.getFull().contains("?") ? var3 : null;
   }

   private IUnmangledData q(ayn.eo var1) {
      return this.q(var1, null);
   }

   private IUnmangledData q(ayn.eo var1, List var2) {
      int var3 = 0;
      if (var1.q() == '.') {
         var1.xK++;
         ArrayList var10 = new ArrayList();
         ayn.CU var11 = new ayn.CU();
         var1.Dw |= 4;
         if (!this.q(var1, var11, var10, false)) {
            return null;
         } else {
            ayq var12 = new ayq(Strings.ff("%s", Strings.safe(var11.q)));
            var1.Uv = var12;
            return var1.Uv;
         }
      } else if (var1.q() != '?') {
         return null;
      } else {
         var1.xK++;
         if (var1.q() == '?' && (var1.RF[var1.xK + 1] != '$' || var1.RF[var1.xK + 2] == '?')) {
            if (var1.RF[var1.xK + 1] == '$') {
               var3 = 6;
               var1.xK += 2;
            }

            var1.xK++;
            Couple var9 = this.Dw(var1);
            var3 = var9.getFirst() != 0 ? (Integer)var9.getFirst() : var3;
            String var5 = (String)var9.getSecond();
            var1.xK++;
            switch (var3) {
               case 1:
               case 2:
                  var1.nf.add("--null--");
                  break;
               case 4:
                  ayq var6 = this.RF(var1);
                  if (var6 == null) {
                     var6 = new ayq(var5);
                  }

                  return var6;
               case 6:
                  ArrayList var7 = new ArrayList();
                  String var8 = this.q(var1, var7, false, '<', '>');
                  if (var8 != null) {
                     var5 = Strings.ff("%s%s", var5, var8);
                  }

                  var1.oW.clear();
               case 3:
               case 5:
               default:
                  var1.nf.add(var5);
            }
         } else {
            if (var1.q() == '$') {
               var1.xK++;
               String var4 = this.nf(var1);
               if (var4 == null) {
                  return null;
               }

               return new ayq(var4);
            }

            if (var1.q() == '?' && var1.RF[var1.xK + 1] == '$') {
               var3 = 5;
            }
         }

         switch (var1.q()) {
            case '$':
               break;
            case '@':
               var1.xK++;
               break;
            default:
               if (!this.q(var1, var3 == 0 || var3 == 1)) {
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
               if (var1.nf.size() <= 1) {
                  return null;
               }

               if (var3 == 1) {
                  var1.nf.set(0, (String)var1.nf.get(1));
               } else {
                  var1.nf.set(0, Strings.ff("~%s", var1.nf.get(1)));
               }

               var1.Dw |= 4;
               break;
            case 3:
               var1.Dw &= -5;
               break;
            case 5:
               var1.gO++;
         }

         if (var1.q() >= '0' && var1.q() <= '9') {
            if (!this.RF(var1, var2)) {
               return null;
            }
         } else {
            if ((var1.q() < 'A' || var1.q() > 'Z') && var1.q() != '$') {
               return null;
            }

            if (!this.q(var1, var3 == 3, var2)) {
               return null;
            }
         }

         return var1.Uv;
      }
   }

   private ayq RF(ayn.eo var1) {
      if (var1.xK() != '@') {
         return null;
      } else if (var1.xK() != '_') {
         return null;
      } else {
         boolean var2;
         if (var1.q() == '0') {
            var2 = false;
         } else {
            if (var1.q() != '1') {
               return null;
            }

            var2 = true;
         }

         var1.xK++;
         String var3 = this.Uv(var1);
         if (var3 != null && !var3.isEmpty()) {
            int var4 = Integer.parseInt(var3);
            if (var4 >= 0 && var4 >= (var2 ? 2 : 1)) {
               String var5 = this.Uv(var1);
               if (var5 != null && !var5.isEmpty()) {
                  String var6 = null;
                  byte[] var7 = new byte[128];
                  if (var2) {
                     int var8 = 0;

                     while (var1.q() != '@') {
                        Byte var9 = this.xK(var1);
                        if (var9 == null) {
                           break;
                        }

                        Byte var10 = this.xK(var1);
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

                     while (var14 < 128 && var1.q() != '@') {
                        Byte var16 = this.xK(var1);
                        if (var16 == null) {
                           break;
                        }

                        var7[var14++] = var16;
                        if (!var1.RF()) {
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

                  ayq var15 = new ayq(Strings.rtrim(var6, '\u0000'), true);
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

   private Byte xK(ayn.eo var1) {
      char var2 = var1.xK();
      if (var2 != '?') {
         return (byte)var2;
      } else {
         var2 = var1.xK();
         if (var2 == '$') {
            char var10 = var1.xK();
            char var4 = var1.xK();
            if (this.q(var10) && this.q(var4)) {
               char var5 = this.RF(var10);
               char var6 = this.RF(var4);
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

   private boolean q(char var1) {
      return var1 >= 'A' && var1 <= 'P';
   }

   private char RF(char var1) {
      return (char)(var1 <= 'J' ? var1 - 'A' : '\n' + var1 - 75);
   }

   private Couple Dw(ayn.eo var1) {
      byte var2 = 0;
      String var3 = null;
      switch (var1.q()) {
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
            throw new RuntimeException(Strings.ff("Unknown operator %c", var1.q()));
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
            var1.xK++;
            switch (var1.q()) {
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
                  throw new RuntimeException(Strings.ff("Unknown operator %c", var1.q()));
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
                  var1.xK++;
                  String var9 = (String)this.Dw(var1).getSecond();
                  var3 = Strings.ff("`udt returning'%s", var9);
                  break;
               case 'R':
                  var1.Dw |= 4;
                  var1.xK++;
                  switch (var1.q()) {
                     case '0':
                        ayn.CU var8 = new ayn.CU();
                        ArrayList var10 = new ArrayList();
                        var1.xK++;
                        this.q(var1, var8, var10, false);
                        var3 = Strings.ff("%s%s `RTTI Type Descriptor'", Strings.safe(var8.q), Strings.safe(var8.RF));
                        var1.xK--;
                        return new Couple(Integer.valueOf(var2), var3);
                     case '1':
                        var1.xK++;
                        String var4 = this.Uv(var1);
                        String var5 = this.Uv(var1);
                        String var6 = this.Uv(var1);
                        String var7 = this.Uv(var1);
                        var1.xK--;
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
                        throw new RuntimeException(Strings.ff("TBI: RTTI operator: %c", var1.q()));
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

   private boolean RF(ayn.eo var1, List var2) {
      String var3 = null;
      switch (var1.q()) {
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
      if (var1.q() >= '0' && var1.q() <= '2') {
         var4 = "static ";
      }

      String var5 = this.q(var1, 0);
      String var6 = null;
      ayn.CU var7 = new ayn.CU();
      char var8 = var1.q();
      var1.xK++;
      switch (var8) {
         case '0':
         case '1':
         case '2':
         case '3':
         case '4':
         case '5':
            int var14 = var1.nf.size();
            Object var16 = var2 != null ? var2 : new ArrayList();
            if (!this.q(var1, var7, (List)var16, false)) {
               return false;
            }

            StringBuilder var18 = new StringBuilder();
            StringBuilder var12 = new StringBuilder();
            if (!this.q(var1, var18, var12)) {
               return false;
            }

            var6 = var18.toString();
            String var13 = var12.toString();
            if (!var6.isEmpty() && !var13.isEmpty()) {
               var6 = Strings.ff("%s %s", var6, var13);
            } else if (var6.isEmpty()) {
               var6 = var13;
            }

            while (var1.nf.size() > var14) {
               var1.nf.remove(var1.nf.size() - 1);
            }
            break;
         case '6':
         case '7':
            var7.q = null;
            var7.RF = null;
            StringBuilder var9 = new StringBuilder();
            StringBuilder var10 = new StringBuilder();
            if (!this.q(var1, var9, var10)) {
               return false;
            }

            var6 = var9.toString();
            if (var1.q() != '@') {
               String var11 = this.oW(var1);
               if (var11 == null) {
                  return false;
               }

               var7.RF = Strings.ff("{for `%s'}", var11);
            }
            break;
         case '8':
         case '9':
            var7.RF = null;
            var7.q = null;
            break;
         default:
            return false;
      }

      String var15 = Strings.ff(
         "%s%s%s%s%s%s%s%s",
         Strings.safe(var3),
         Strings.safe(var4),
         Strings.safe(var7.q),
         var6 != null && !var6.isEmpty() && var7.q != null ? " " : "",
         Strings.safe(var6),
         var6 == null && var7.q == null ? "" : " ",
         Strings.safe(var5),
         Strings.safe(var7.RF)
      );
      ayq var17 = new ayq(var15);
      var1.Uv = var17;
      return true;
   }

   private boolean q(ayn.eo var1, boolean var2, List var3) {
      String var5 = null;
      int var6 = -1;
      String var7 = null;
      String var9 = null;
      ayn.CU var10 = new ayn.CU();
      Object var11 = var3 != null ? var3 : new ArrayList();
      String var12 = null;
      boolean var13 = true;
      boolean var14 = true;
      if (var1.q.substring(var1.xK, var1.xK + 3).equals("$$F") || var1.q.substring(var1.xK, var1.xK + 3).equals("$$H")) {
         var1.xK += 3;
      }

      char var4 = var1.q();
      var1.xK++;
      if (var4 == '$') {
         if (var1.q() >= '0' && var1.q() <= '5') {
            var6 = (var1.q() - '0') / 2;
         } else if (var1.q() == 'R') {
            var6 = (var1.RF[var1.xK + 1] - '0') / 2;
         } else if (var1.q() != 'B') {
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

      if (var4 == '$' && var1.q() != 'B') {
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

      String var8 = this.q(var1, 0);
      if (var4 == '$' && var1.q() == 'B') {
         var1.xK++;
         String var25 = this.Uv(var1);
         if (var25 == null || var1.q() != 'A') {
            return false;
         }

         var1.xK++;
         var8 = Strings.ff("%s{%s,{flat}}' }'", Strings.safe(var8), Strings.safe(var25));
         var13 = false;
         var14 = false;
      } else if (var4 == '$' && var1.q() == 'R') {
         var1.xK += 2;
         String var24 = this.Uv(var1);
         String var28 = this.Uv(var1);
         String var17 = this.Uv(var1);
         String var18 = this.Uv(var1);
         if (var24 == null || var28 == null || var17 == null || var18 == null) {
            return false;
         }

         var8 = Strings.ff(
            "%s`vtordispex{%s,%s,%s,%s}' ", Strings.safe(var8), Strings.safe(var24), Strings.safe(var28), Strings.safe(var17), Strings.safe(var18)
         );
      } else if (var4 == '$') {
         var1.xK++;
         String var15 = this.Uv(var1);
         String var16 = this.Uv(var1);
         if (var15 == null || var16 == null) {
            return false;
         }

         var8 = Strings.ff("%s`vtordisp{%s,%s}' ", Strings.safe(var8), Strings.safe(var15), Strings.safe(var16));
      } else if ((var4 - 'A') % 8 == 6 || (var4 - 'A') % 8 == 7) {
         var8 = Strings.ff("%s`adjustor{%s}' ", Strings.safe(var8), Strings.safe(this.Uv(var1)));
      }

      if (var13 && (var4 == '$' || var4 <= 'X' && (var4 - 'A') % 8 != 2 && (var4 - 'A') % 8 != 3)) {
         StringBuilder var26 = new StringBuilder();
         StringBuilder var29 = new StringBuilder();
         if (!this.q(var1, var26, var29)) {
            return false;
         }

         if (var26.length() != 0 || var29.length() != 0) {
            var9 = Strings.ff("%s %s", Strings.safe(var26.toString()), Strings.safe(var29.toString()));
         }
      }

      StringBuilder var27 = new StringBuilder();
      StringBuilder var30 = new StringBuilder();
      if (!this.q(var1.q(), var27, var30)) {
         return false;
      } else {
         var1.xK++;
         String var31 = var27.toString();
         String var32 = var30.toString();
         if (var14 && var1.q() == '@') {
            var1.xK++;
         } else if (var14 && !this.q(var1, var10, (List)var11, false)) {
            return false;
         }

         if (!var14 || (var1.Dw & 4) != 0) {
            var10.q = null;
            var10.RF = null;
         }

         if (var2) {
            var8 = Strings.ff("%s%s%s", Strings.safe(var8), Strings.safe(var10.q), Strings.safe(var10.RF));
            var10.q = null;
            var10.RF = null;
         }

         int var19 = var1.nf.size();
         if (var13) {
            var12 = this.q(var1, (List)var11, true, '(', ')');
            if (var12 == null) {
               return false;
            }
         }

         while (var1.nf.size() > var19) {
            var1.nf.remove(var1.nf.size() - 1);
         }

         List var20 = null;
         if (var12 != null && var12.length() > 2) {
            var20 = Arrays.asList(var12.substring(1, var12.length() - 1).split(","));
         }

         String var21 = null;
         if (var10.RF == null) {
            var21 = var10.q;
         }

         String var22 = Strings.ff(
            "%s%s%s%s%s%s%s%s%s%s%s",
            Strings.safe(var5),
            Strings.safe(var7),
            Strings.safe(var10.q),
            var10.q != null && var10.RF == null ? " " : "",
            Strings.safe(var31),
            !var31.isEmpty() ? " " : "",
            Strings.safe(var32),
            Strings.safe(var8),
            Strings.safe(var12),
            Strings.safe(var9),
            Strings.safe(var10.RF)
         );
         ayp var23 = new ayp(var8, var21, var20, null, var22);
         var23.RF = var31;
         var1.Uv = var23;
         return true;
      }
   }

   private boolean q(ayn.eo var1, ayn.CU var2, List var3, boolean var4) {
      boolean var6;
      char var5 = var1.q();
      var1.xK++;
      var6 = true;
      label589:
      switch (var5) {
         case '$':
            char var28 = var1.q();
            var1.xK++;
            switch (var28) {
               case '$':
                  if (var1.q() == 'B') {
                     var1.xK++;
                     int var38 = var1.nf.size();
                     ayn.CU var47 = new ayn.CU();
                     String var55 = null;
                     if (var1.q() == 'Y') {
                        var1.xK++;
                        String var62 = this.Uv(var1);
                        if (var62 == null) {
                           return var2.q != null;
                        }

                        int var68 = Integer.parseInt(var62);

                        while (var68-- != 0) {
                           var55 = Strings.ff("%s[%s]", Strings.safe(var55), this.Uv(var1));
                        }
                     }

                     if (!this.q(var1, var47, var3, false)) {
                        return var2.q != null;
                     }

                     if (var55 != null) {
                        var2.q = Strings.ff("%s %s", Strings.safe(var47.q), var55);
                     } else {
                        var2.q = var47.q;
                     }

                     var2.RF = var47.RF;

                     while (true) {
                        if (var1.nf.size() <= var38) {
                           break label589;
                        }

                        var1.nf.remove(var1.nf.size() - 1);
                     }
                  } else {
                     if (var1.q() == 'C' || var1.q() == 'Q' || var1.q() == 'R') {
                        String var39 = null;
                        if (var1.q() == 'Q') {
                           var39 = "&&";
                        } else if (var1.q() == 'R') {
                           var39 = "&& volatile";
                        }

                        var1.xK++;
                        StringBuilder var48 = new StringBuilder();
                        StringBuilder var56 = new StringBuilder();
                        if (!this.q(var1, var48, var56)) {
                           return var2.q != null;
                        }

                        if (var2.q == null && !this.q(var1, var2, var3, var4)) {
                           return var2.q != null;
                        }

                        String var63 = var48.toString();
                        String var69 = var56.toString();
                        var2.q = Strings.ff(
                           "%s%s%s%s%s%s%s",
                           Strings.safe(var2.q),
                           !var63.isEmpty() ? " " : "",
                           Strings.safe(var63),
                           var39 != null ? " " : "",
                           Strings.safe(var39),
                           !var69.isEmpty() ? " " : "",
                           Strings.safe(var69)
                        );
                     } else if (var1.q() == '$') {
                        var1.xK++;
                        if (var1.q() != 'V') {
                           throw new RuntimeException();
                        }

                        var1.xK++;
                        var2.q = "";
                     } else if (var1.q() == 'V') {
                        var1.xK++;
                        var2.q = "";
                     } else {
                        if (var1.q() != 'T') {
                           throw new RuntimeException();
                        }

                        var1.xK++;
                        var2.q = "std::nullptr_t";
                     }
                     break label589;
                  }
               case '0':
                  var2.q = this.Uv(var1);
                  if (var2.q == null) {
                     return false;
                  }
                  break label589;
               case '1':
                  ArrayList var37 = new ArrayList(var1.nf);
                  int var46 = var1.gO;
                  int var54 = var1.oW.size();
                  var1.nf.clear();
                  int var61 = var1.Dw;
                  var1.Dw = 0;
                  IUnmangledData var67 = this.q(var1, var3);
                  if (var67 == null) {
                     return false;
                  }

                  var6 = false;
                  var2.q = Strings.ff("&%s", var67.getFull());
                  var1.gO = var46;

                  while (var1.oW.size() > var54) {
                     var1.oW.remove(var1.oW.size() - 1);
                  }

                  var1.nf = var37;
                  var1.Dw = var61;
                  break label589;
               case 'D':
                  String var36 = this.Uv(var1);
                  if (var36 == null) {
                     return var2.q != null;
                  }

                  var2.q = Strings.ff("`template-parameter%s'", var36);
                  break label589;
               case 'F':
                  String var35 = this.Uv(var1);
                  if (var35 == null) {
                     return var2.q != null;
                  }

                  String var45 = this.Uv(var1);
                  if (var45 == null) {
                     return var2.q != null;
                  }

                  var2.q = Strings.ff("{%s,%s}", var35, var45);
                  break label589;
               case 'G':
                  String var34 = this.Uv(var1);
                  if (var34 == null) {
                     return var2.q != null;
                  }

                  String var44 = this.Uv(var1);
                  if (var44 == null) {
                     return var2.q != null;
                  }

                  String var53 = this.Uv(var1);
                  if (var53 == null) {
                     return var2.q != null;
                  }

                  var2.q = Strings.ff("{%s,%s,%s}", var34, var44, var53);
                  break label589;
               case 'Q':
                  String var33 = this.Uv(var1);
                  if (var33 == null) {
                     return var2.q != null;
                  }

                  var2.q = Strings.ff("`non-type-template-parameter%s'", var33);
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
               return var2.q != null;
            }

            var2.q = (String)var3.get((var5 - '0') * 2);
            var2.RF = (String)var3.get((var5 - '0') * 2 + 1);
            var6 = false;
            break;
         case '?':
            if (var4) {
               String var27 = this.Uv(var1);
               if (var27 == null) {
                  return var2.q != null;
               }

               var2.q = Strings.ff("`template-parameter-%s'", var27);
            } else if (!this.q(var2, var1, var3, '?', var4)) {
               return var2.q != null;
            }
            break;
         case 'A':
         case 'B':
            if (!this.q(var2, var1, var3, var5, var4)) {
               return var2.q != null;
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
            var2.q = this.Dw(var5);
            var6 = false;
            break;
         case 'P':
            if (Character.isDigit(var1.q())) {
               if (var1.q() == '8') {
                  var1.xK++;
                  int var25 = var1.nf.size();
                  String var31 = this.oW(var1);
                  if (var31 == null) {
                     return var2.q != null;
                  }

                  StringBuilder var42 = new StringBuilder();
                  StringBuilder var51 = new StringBuilder();
                  if (!this.q(var1, var42, var51)) {
                     return var2.q != null;
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
                  if (!this.q(var1.q(), var70, var71)) {
                     return var2.q != null;
                  }

                  var1.xK++;
                  String var72 = var70.toString();
                  ayn.CU var73 = new ayn.CU();
                  if (!this.q(var1, var73, var3, false)) {
                     return var2.q != null;
                  }

                  String var74 = this.q(var1, var3, true, '(', ')');
                  if (var74 == null) {
                     return var2.q != null;
                  }

                  while (var1.nf.size() > var25) {
                     var1.nf.remove(var1.nf.size() - 1);
                  }

                  var2.q = Strings.ff("%s%s (%s %s::*", Strings.safe(var73.q), Strings.safe(var73.RF), Strings.safe(var72), Strings.safe(var31));
                  var2.RF = Strings.ff(")%s%s", Strings.safe(var74), Strings.safe(var59));
               } else {
                  if (var1.q() != '6') {
                     return var2.q != null;
                  }

                  var1.xK++;
                  int var26 = var1.nf.size();
                  StringBuilder var32 = new StringBuilder();
                  StringBuilder var43 = new StringBuilder();
                  if (!this.q(var1.q(), var32, var43)) {
                     return var2.q != null;
                  }

                  var1.xK++;
                  String var52 = var32.toString();
                  ayn.CU var60 = new ayn.CU();
                  if (!this.q(var1, var60, var3, false)) {
                     return var2.q != null;
                  }

                  String var66 = this.q(var1, var3, true, '(', ')');
                  if (var66 == null) {
                     return var2.q != null;
                  }

                  while (var1.nf.size() > var26) {
                     var1.nf.remove(var1.nf.size() - 1);
                  }

                  var2.q = Strings.ff("%s%s (%s*", Strings.safe(var60.q), Strings.safe(var60.RF), Strings.safe(var52));
                  var2.RF = Strings.ff(")%s", Strings.safe(var66));
               }
            } else if (!this.q(var2, var1, var3, 'P', var4)) {
               return var2.q != null;
            }
            break;
         case 'Q':
            if (var1.q() == '8') {
               var1.xK++;
               int var24 = var1.nf.size();
               String var30 = this.oW(var1);
               if (var30 == null) {
                  return var2.q != null;
               }

               StringBuilder var41 = new StringBuilder();
               StringBuilder var50 = new StringBuilder();
               if (!this.q(var1, var41, var50)) {
                  return var2.q != null;
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
               if (!this.q(var1.q(), var15, var16)) {
                  return var2.q != null;
               }

               var1.xK++;
               String var17 = var15.toString();
               ayn.CU var18 = new ayn.CU();
               if (!this.q(var1, var18, var3, false)) {
                  return var2.q != null;
               }

               String var19 = this.q(var1, var3, true, '(', ')');
               if (var19 == null) {
                  return var2.q != null;
               }

               while (var1.nf.size() > var24) {
                  var1.nf.remove(var1.nf.size() - 1);
               }

               var2.q = Strings.ff("%s%s (%s %s::*", Strings.safe(var18.q), Strings.safe(var18.RF), Strings.safe(var17), Strings.safe(var30));
               var2.RF = Strings.ff(")%s%s", Strings.safe(var19), Strings.safe(var58));
               break;
            } else if (var1.q() == '6') {
               var1.xK++;
               int var23 = var1.nf.size();
               StringBuilder var29 = new StringBuilder();
               StringBuilder var40 = new StringBuilder();
               if (!this.q(var1.q(), var29, var40)) {
                  return var2.q != null;
               }

               var1.xK++;
               String var49 = var29.toString();
               ayn.CU var57 = new ayn.CU();
               if (!this.q(var1, var57, var3, false)) {
                  return var2.q != null;
               }

               String var14 = this.q(var1, var3, true, '(', ')');
               if (var14 == null) {
                  return var2.q != null;
               }

               while (var1.nf.size() > var23) {
                  var1.nf.remove(var1.nf.size() - 1);
               }

               var2.q = Strings.ff("%s%s (%s*", Strings.safe(var57.q), Strings.safe(var57.RF), Strings.safe(var49));
               var2.RF = Strings.ff(")%s", Strings.safe(var14));
               break;
            }
         case 'R':
         case 'S':
            if (!this.q(var2, var1, var3, var4 ? var5 : 'P', var4)) {
               return var2.q != null;
            }
            break;
         case 'T':
         case 'U':
         case 'V':
         case 'Y':
            String var20 = this.oW(var1);
            if (var20 == null) {
               return var2.q != null;
            }
            var2.q = Strings.ff("%s%s", Strings.safe(switch (var5) {
               case 'T' -> "union ";
               case 'U' -> "struct ";
               case 'V' -> "class ";
               default -> throw new RuntimeException();
               case 'Y' -> "cointerface ";
            }), Strings.safe(var20));
            break;
         case 'W':
            if (var1.q() != '4') {
               return var2.q != null;
            }

            var1.xK++;
            String var22 = this.oW(var1);
            if (var22 == null) {
               return var2.q != null;
            }

            var2.q = Strings.ff("enum %s", var22);
            break;
         case '_':
            if (var1.q() == '$') {
               var1.xK++;
               if (!this.q(var1, var2, var3, var4)) {
                  return false;
               }

               var2.q = Strings.ff("__w64 %s", var2.q);
            } else if (var1.q() == 'O') {
               int var7 = 1;
               var1.xK++;
               StringBuilder var8 = new StringBuilder();
               StringBuilder var9 = new StringBuilder();
               if (!this.q(var1, var8, var9)) {
                  return false;
               }

               String var10 = var8.toString();
               String var11 = var9.toString();
               if (!var10.isEmpty() && !var11.isEmpty()) {
                  var10 = Strings.ff("%s %s", var10, var11);
               }

               while (var1.q.substring(var1.xK, var1.xK + 2).equals("_O")) {
                  var7++;
                  var1.xK += 2;
                  if (!this.q(var1, var8, var9)) {
                     return false;
                  }
               }

               if (!this.q(var1, var2, var3, var4)) {
                  return false;
               }

               StringBuilder var12 = new StringBuilder();

               for (int var13 = 0; var13 < var7; var13++) {
                  var12.append("[]");
               }

               var2.q = Strings.ff("%s%s%s%s", Strings.safe(var2.q), var10.isEmpty() ? "" : " ", Strings.safe(var10), var10.isEmpty() ? "" : " ");
               var2.RF = Strings.ff("%s%s", Strings.safe(var2.RF), var12.toString());
            } else {
               var2.q = this.xK(var1.q());
               var1.xK++;
            }
      }

      if (var6 && var3 != null && var4) {
         var3.add(var2.q != null ? var2.q : "");
         var3.add(var2.RF != null ? var2.RF : "");
      }

      return var2.q != null;
   }

   private String xK(char var1) {
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

   private String Dw(char var1) {
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

   private boolean q(String var1) {
      return var1.equals("signed char")
         || var1.equals("char")
         || var1.equals("unsigned char")
         || var1.equals("short")
         || var1.equals("unsigned short")
         || var1.equals("int")
         || var1.equals("unsigned int")
         || var1.equals("long")
         || var1.equals("unsigned long")
         || var1.equals("float")
         || var1.equals("double")
         || var1.equals("long double")
         || var1.equals("void")
         || var1.equals("...");
   }

   private boolean q(char var1, StringBuilder var2, StringBuilder var3) {
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

   private boolean q(ayn.eo var1, StringBuilder var2, StringBuilder var3) {
      if (var1.q() == 'E') {
         var3.append("__ptr64");
         var1.xK++;
      }

      if (var1.q() == 'I') {
         var3.append("__restrict");
         var1.xK++;
      }

      switch (var1.q()) {
         case '$':
            var1.xK++;
            var1.q();
            throw new RuntimeException(Strings.ff("Unknown $-type modifier %c", var1.q()));
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
            throw new RuntimeException(Strings.ff("Unknown modifier %c", var1.q()));
      }

      var1.xK++;
      return true;
   }

   private boolean q(ayn.CU var1, ayn.eo var2, List var3, char var4, boolean var5) {
      String var6 = "";
      if (var2.q() == 'E') {
         var6 = " __ptr64";
         var2.xK++;
      }

      if (var2.q() == 'I') {
         var6 = " __restrict";
         var2.xK++;
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
      if (this.q(var2, var8, var9)) {
         String var10 = var8.toString();
         ayn.CU var11 = new ayn.CU();
         int var12 = var2.nf.size();
         if (var2.q() == 'Y') {
            var2.xK++;
            String var13 = this.Uv(var2);
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
               var7 = Strings.ff("%s[%s]", Strings.safe(var7), this.Uv(var2));
            }
         }

         if (!this.q(var2, var11, var3, false)) {
            return false;
         }

         if (!var10.isEmpty()) {
            var1.q = Strings.ff("%s %s%s", Strings.safe(var11.q), Strings.safe(var10), Strings.safe(var7));
         } else {
            if (!var5 && var7.length() > 1 && var7.charAt(0) == ' ' && var7.charAt(1) == '*' && var11.q.endsWith("*")) {
               var7 = var7.substring(1);
            }

            var1.q = Strings.ff("%s%s", Strings.safe(var11.q), Strings.safe(var7));
         }

         var1.RF = var11.RF;

         while (var2.nf.size() > var12) {
            var2.nf.remove(var2.nf.size() - 1);
         }
      }

      return true;
   }

   private String Uv(ayn.eo var1) {
      StringBuilder var2 = new StringBuilder();
      if (var1.q() == '?') {
         var2.append("-");
         var1.xK++;
      }

      if (var1.q() >= '0' && var1.q() <= '9') {
         var2.append(Character.getNumericValue(var1.q()) + 1);
         var1.xK++;
      } else {
         if (var1.q() < 'A' || var1.q() > 'P') {
            return null;
         }

         long var3;
         for (var3 = 0L; var1.q() >= 'A' && var1.q() <= 'P'; var1.xK++) {
            var3 *= 16L;
            var3 += var1.q() - 'A';
         }

         if (var1.q() != '@') {
            return null;
         }

         var2.append(var3);
         var1.xK++;
      }

      return var2.toString();
   }

   private String q(ayn.eo var1, List var2, boolean var3, char var4, char var5) {
      String var6 = null;
      ArrayList var7 = new ArrayList();

      while (var1.xK < var1.RF.length) {
         ayn.CU var8 = new ayn.CU();
         if (var1.q() == '@') {
            var1.xK++;
            break;
         }

         if (!this.q(var1, var8, var2, true)) {
            return null;
         }

         if (var3 && var8.q.equals("void")) {
            break;
         }

         String var9 = Strings.ff("%s%s", Strings.safe(var8.q), Strings.safe(var8.RF));
         var7.add(var9);
         if (var8.q.equals("...")) {
            break;
         }
      }

      if (var3) {
         if (var1.q() != 'Z') {
            return null;
         }

         var1.xK++;
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

   private String oW(ayn.eo var1) {
      int var2 = var1.nf.size();
      String var3 = null;
      if (this.q(var1, false)) {
         var3 = this.q(var1, var2);
      }

      while (var1.nf.size() > var2) {
         var1.nf.remove(var1.nf.size() - 1);
      }

      return var3;
   }

   private String q(ayn.eo var1, int var2) {
      StringBuilder var3 = new StringBuilder();

      for (int var4 = var1.nf.size() - 1; var4 >= var2; var4--) {
         var3.append((String)var1.nf.get(var4));
         if (var4 > var2) {
            var3.append("::");
         }
      }

      return var3.toString();
   }

   private boolean q(ayn.eo var1, boolean var2) {
      while (var1.q() != '@') {
         String var3;
         label38:
         switch (var1.q()) {
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
               var1.q();
               var3 = (String)var1.oW.get(var1.gO + var1.q() - 48);
               var1.xK++;
               break;
            case '?':
               var1.xK++;
               switch (var1.q()) {
                  case '$':
                     var1.xK++;
                     var3 = this.nf(var1);
                     var1.oW.add(var3);
                     break label38;
                  case '?':
                     ArrayList var4 = new ArrayList(var1.nf);
                     int var5 = var1.gO;
                     int var6 = var1.oW.size();
                     var1.nf.clear();
                     IUnmangledData var7 = this.q(var1);
                     if (var7 == null) {
                        return false;
                     }

                     var3 = Strings.ff("`%s'", var7.getFull());
                     var1.gO = var5;

                     while (var1.oW.size() > var6) {
                        var1.oW.remove(var1.oW.size() - 1);
                     }

                     var1.nf = var4;
                     break label38;
                  default:
                     var3 = this.Uv(var1);
                     if (var3 == null) {
                        return false;
                     }

                     var3 = Strings.ff("`%s'", var3);
                     break label38;
               }
            default:
               var3 = this.gO(var1);
         }

         if (var3 == null) {
            return false;
         }

         var1.nf.add(var3);
      }

      var1.xK++;
      return true;
   }

   private String gO(ayn.eo var1) {
      int var2 = var1.xK;

      while (
         var1.q() >= 'A' && var1.q() <= 'Z' || var1.q() >= 'a' && var1.q() <= 'z' || var1.q() >= '0' && var1.q() <= '9' || var1.q() == '_' || var1.q() == '$'
      ) {
         var1.xK++;
         if (var1.q() == '@') {
            var1.oW.add(var1.q.substring(var2, var1.xK));
            var1.xK++;
            return (String)var1.oW.get(var1.oW.size() - 1);
         }
      }

      return null;
   }

   private String nf(ayn.eo var1) {
      int var2 = var1.oW.size();
      int var3 = var1.gO;
      int var4 = var1.nf.size();
      var1.gO = var1.oW.size();
      String var5 = this.gO(var1);
      if (var5 == null) {
         var1.gO = var3;
         return null;
      } else {
         ArrayList var6 = new ArrayList();
         String var7 = this.q(var1, var6, false, '<', '>');
         if (var7 == null) {
            return null;
         } else {
            var5 = Strings.ff("%s%s", Strings.safe(var5), Strings.safe(var7));

            while (var1.oW.size() > var2) {
               var1.oW.remove(var1.oW.size() - 1);
            }

            var1.gO = var3;

            while (var1.nf.size() > var4) {
               var1.nf.remove(var1.nf.size() - 1);
            }

            return var5;
         }
      }
   }

   private static class CU {
      String q = null;
      String RF = null;
   }

   private static class eo {
      String q;
      char[] RF;
      int xK;
      int Dw;
      IUnmangledData Uv;
      List oW;
      int gO = 0;
      List nf;

      eo(String var1) {
         this.q = var1;
         this.RF = this.q.toCharArray();
         this.xK = 0;
         this.oW = new ArrayList();
         this.nf = new ArrayList();
      }

      char q() {
         return this.RF[this.xK];
      }

      boolean RF() {
         return this.xK < this.RF.length;
      }

      char xK() {
         return this.RF[this.xK++];
      }
   }
}
