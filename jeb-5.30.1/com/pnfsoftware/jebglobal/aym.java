package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.mangling.IManglingEngine;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@Deprecated
public class aym implements IManglingEngine {
   private static final ILogger q = GlobalLog.getLogger(aym.class);
   private static final boolean RF = false;
   private static final Map xK = new HashMap();
   private static final Map Dw = new HashMap();
   private static final Map Uv = new HashMap();
   private static final Map oW = new HashMap();
   private static final Map gO = new HashMap();
   private static final Map nf = new HashMap();

   private aym.eo q(aym.CU var1, List var2, List var3) {
      aym.eo var4;
      if (var1.q("?$")) {
         var4 = this.q(var1);
         if (var4.xK.size() == 0) {
            return null;
         }
      } else {
         var4 = new aym.eo();
         StringBuilder var5 = new StringBuilder();
         String var6 = var1.RF();
         if (var6.matches("[0-9]")) {
            var5.append((String)var3.get(Integer.parseInt(var6)));
         } else if (var6.equals("?")) {
            var5.append(var6);
            if (var1.q("_")) {
               var5.append(var1.RF());
            }

            var5.append(var1.RF());
         } else {
            String var7 = var6.concat(var1.xK("@"));
            var5.append(var7);
            if (var3 != null) {
               var3.add(var7);
            }
         }

         var4.RF = var5.toString();
      }

      return var4;
   }

   private aym.eo q(aym.CU var1) {
      aym.eo var2 = new aym.eo();
      if (!var1.q("?$")) {
         return null;
      } else {
         var1.RF(2);
         var2.RF = var1.xK("@");
         if (var2.RF.startsWith("?")) {
            return null;
         } else {
            ArrayList var3 = new ArrayList();
            var3.add(var2.RF);
            String var4 = this.RF(var1, null, null);
            var2.xK.add(var4);

            while (var1.q() && !var1.q("@")) {
               var4 = this.RF(var1, null, var3);
               if (var4 == null) {
                  return null;
               }

               var2.xK.add(var4);
            }

            if (var1.q("@")) {
               var1.RF(1);
            }

            return var2;
         }
      }
   }

   @Override
   public IUnmangledData unmangle(String var1) {
      try {
         aym.nI var2 = new aym.nI();
         aym.CU var3 = new aym.CU(var1);
         Object[] var11 = new Object[]{var3};
         if (var3.q("?") && var3.RF("Z")) {
            if (var3.q("??B")) {
               return null;
            } else {
               var3.RF(1);
               ArrayList var4 = new ArrayList();
               var2.RF = this.q(var3, null, var4);
               if (var2.RF == null) {
                  return null;
               } else {
                  var11 = new Object[]{var2.RF};
                  var11 = new Object[]{var3};

                  while (var3.q() && !var3.q("@")) {
                     aym.eo var5;
                     if (var3.q("?$")) {
                        var5 = this.q(var3);
                     } else {
                        var5 = new aym.eo();
                        var5.RF = var3.xK("@");
                     }

                     if (var5 == null) {
                        return null;
                     }

                     var2.xK.add(var5);
                     var11 = new Object[]{var5};
                     var4.add(var5.toString());
                  }

                  var3.RF(1);
                  var11 = new Object[]{var3};
                  var2.Dw = var3.RF();
                  if (!Uv.containsKey(var2.Dw)) {
                     return null;
                  } else {
                     var11 = new Object[]{var2.Dw};
                     var11 = new Object[]{var3};
                     if (!var2.q() && !var2.RF()) {
                        var2.Uv = var3.RF();
                        if (var2.Uv.equals("E")) {
                           if (!var3.q("A")) {
                              return null;
                           }

                           var3.RF(1);
                        } else if (!oW.containsKey(var2.Uv)) {
                           return null;
                        }

                        var11 = new Object[]{var2.Uv};
                     }

                     var11 = new Object[]{var3};
                     var2.oW = var3.RF();
                     var11 = new Object[]{var2.oW};
                     var11 = new Object[]{var3};
                     if (var3.q("?")) {
                        var2.gO = var3.q(2);
                        var11 = new Object[]{var2.gO};
                     }

                     var11 = new Object[]{var3};
                     if (!var2.RF.RF.startsWith("?0") && !var2.RF.RF.startsWith("?1")) {
                        var2.nf = this.RF(var3, null, null);
                        if (var2.nf == null) {
                           return null;
                        }
                     } else {
                        var2.nf = var3.RF();
                        if (!var2.nf.equals("@")) {
                           return null;
                        }
                     }

                     var11 = new Object[]{var2.nf};
                     var11 = new Object[]{var3};
                     ArrayList var9 = new ArrayList();

                     while (var3.q() && !var3.q("@")) {
                        String var6 = this.RF(var3, var9, var4);
                        if (var6 == null) {
                           return null;
                        }

                        if (var6.length() > 1) {
                           var9.add(var6);
                        }

                        var2.gP.add(var6);
                        var11 = new Object[]{var6};
                        if (var6.equals("X") || var6.equals("Z")) {
                           break;
                        }
                     }

                     ayp var10 = var2.Dw();
                     String var7 = var10.getFull();
                     if (!var7.contains("?") && !var7.contains("@")) {
                        var11 = new Object[]{var7};
                        return var10;
                     } else {
                        var11 = new Object[]{var7};
                        return null;
                     }
                  }
               }
            }
         } else {
            return null;
         }
      } catch (Exception var8) {
         Object[] var10000 = new Object[0];
         return null;
      }
   }

   private void q(String var1, StringBuilder var2) {
      aym.CU var3 = new aym.CU(var1);
      String var4 = var3.RF();
      if (xK.containsKey(var4)) {
         var2.append((String)xK.get(var4));
      } else if (var4.equals("_")) {
         String var12 = var4.concat(var3.RF());
         if (xK.containsKey(var12)) {
            var2.append((String)xK.get(var12));
         }
      } else {
         if (var4.matches("[0-9]")) {
            var2.append(var1);
         }

         int var5 = 0;
         int var6 = 0;
         boolean var7 = false;
         String var8 = null;

         for (boolean var9 = false; var3.q(); var9 = true) {
            if (var4.equals("P") || var4.equals("A")) {
               if (var4.equals("P")) {
                  var5++;
               } else {
                  var6++;
               }

               var4 = var3.RF();
               var9 = false;
            }

            if (var4.equals("E")) {
               var7 = true;
               var4 = var3.RF();
               var9 = false;
            }

            if (oW.containsKey(var4)) {
               var8 = var4;
               var4 = var3.RF();
               var9 = false;
            }

            if (var4.equals("T") || var4.equals("U") || var4.equals("V") || var4.equals("W") && var3.q("4")) {
               if (var4.equals("T")) {
                  var2.append("union ");
               }

               if (var4.equals("U")) {
                  var2.append("struct ");
               }

               if (var4.equals("V")) {
                  var2.append("class ");
               }

               if (var4.equals("W")) {
                  var2.append("enum ");
                  var3.RF(1);
               }

               String var13 = var3.toString();
               if (var13.contains("@")) {
                  String[] var11 = var13.split("@");
                  Collections.reverse(Arrays.asList(var11));
                  var13 = Strings.join("::", var11, 0, var11.length);
               }

               var2.append(var13);
               var3.xK();
               break;
            }

            if (xK.containsKey(var4)) {
               var2.append((String)xK.get(var4));
               break;
            }

            if (var4.equals("_")) {
               String var10 = var4.concat(var3.RF());
               if (xK.containsKey(var10)) {
                  var2.append((String)xK.get(var10));
               }
               break;
            }

            if (var9) {
               throw new IllegalStateException("Cant print type");
            }
         }

         if (var3.q()) {
            throw new IllegalStateException("Cant print type");
         } else {
            if (var8 != null && !((List)oW.get(var8)).isEmpty()) {
               var2.append(" ");
               var2.append(oW.get(var8));
            }

            while (var5-- != 0) {
               var2.append(" *");
            }

            while (var6-- != 0) {
               var2.append(" &");
            }

            if (var7) {
               var2.append(" __ptr64");
            }
         }
      }
   }

   private String RF(aym.CU var1) {
      String var2 = var1.RF();
      StringBuilder var3 = new StringBuilder();
      if (var2.startsWith("?")) {
         var3.append("-");
         var2 = var1.RF();
      }

      if (var2.matches("[0-9]")) {
         return var3.append(Integer.valueOf(Integer.parseInt(var2) + 1)).toString();
      } else if (var2.compareTo("A") >= 0 && var2.compareTo("P") <= 0) {
         int var4;
         for (var4 = 0; var2.compareTo("A") >= 0 && var2.compareTo("P") <= 0; var2 = var1.RF()) {
            var4 *= 16;
            var4 += var2.charAt(0) - 'A';
         }

         return !var2.equals("@") ? null : var3.append(Long.toString(var4 & 4294967295L)).toString();
      } else {
         return null;
      }
   }

   private String RF(aym.CU var1, List var2, List var3) {
      String var4 = var1.RF();
      if (var4.equals("@")) {
         return null;
      } else if (xK.containsKey(var4)) {
         return var4;
      } else if (var4.equals("_")) {
         String var8 = var4.concat(var1.RF());
         return xK.containsKey(var8) ? var8 : null;
      } else if (var4.matches("[0-9]")) {
         return (String)var2.get(Integer.parseInt(var4));
      } else if (var4.equals("$")) {
         if (var1.q("0")) {
            var1.RF(1);
            return this.RF(var1);
         } else {
            return null;
         }
      } else {
         for (StringBuilder var5 = new StringBuilder(); var1.q(); var4 = var1.RF()) {
            if (var4.equals("P") || var4.equals("A")) {
               var5.append(var4);
               var4 = var1.RF();
               if (var4.matches("[0-9]")) {
                  return null;
               }
            }

            if (var4.equals("E")) {
               var5.append(var4);
               var4 = var1.RF();
            }

            if (oW.containsKey(var4)) {
               var5.append(var4);
               var4 = var1.RF();
            }

            var5.append(var4);
            if (var4.equals("Q")) {
               return null;
            }

            if (var4.equals("T") || var4.equals("U") || var4.equals("V") || var4.equals("W") && var1.q("4")) {
               if (var4.equals("W")) {
                  var5.append(var1.RF());
               }

               for (boolean var9 = true; var1.q() && !var1.q("@"); var9 = false) {
                  if (!var9) {
                     var5.append("@");
                  }

                  aym.eo var10 = this.q(var1, var2, var3);
                  if (var10 == null) {
                     return null;
                  }

                  var5.append(var10.toString());
               }

               if (!var1.q("@")) {
                  return null;
               } else {
                  var1.RF(1);
                  return var5.toString();
               }
            }

            if (xK.containsKey(var4)) {
               return var5.toString();
            }

            if (var4.equals("_")) {
               String var6 = var1.RF();
               String var7 = var4.concat(var6);
               if (xK.containsKey(var7)) {
                  return var5.append(var6).toString();
               }

               return null;
            }
         }

         return null;
      }
   }

   private void q(String var1, Object... var2) {
   }

   static {
      xK.put("_J", "__int64");
      xK.put("_K", "unsigned __int64");
      xK.put("_N", "bool");
      xK.put("_T", "long double");
      xK.put("_W", "wchar_t");
      xK.put("_Z", "long double");
      xK.put("C", "signed char");
      xK.put("D", "char");
      xK.put("E", "unsigned char");
      xK.put("F", "short");
      xK.put("G", "unsigned short");
      xK.put("H", "int");
      xK.put("I", "unsigned int");
      xK.put("J", "long");
      xK.put("K", "unsigned long");
      xK.put("M", "float");
      xK.put("N", "double");
      xK.put("O", "long double");
      xK.put("X", "void");
      xK.put("Z", "...");
      Collections.unmodifiableMap(xK);
      Dw.put("?2", "operator new");
      Dw.put("?3", "operator delete");
      Dw.put("?4", "operator=");
      Dw.put("?5", "operator>>");
      Dw.put("?6", "operator<<");
      Dw.put("?7", "operator!");
      Dw.put("?8", "operator==");
      Dw.put("?9", "operator!=");
      Dw.put("?A", "operator[]");
      Dw.put("?C", "operator->");
      Dw.put("?D", "operator*");
      Dw.put("?E", "operator++");
      Dw.put("?F", "operator--");
      Dw.put("?G", "operator-");
      Dw.put("?H", "operator+");
      Dw.put("?I", "operator&");
      Dw.put("?J", "operator->*");
      Dw.put("?K", "operator/");
      Dw.put("?L", "operator%");
      Dw.put("?M", "operator<");
      Dw.put("?N", "operator<=");
      Dw.put("?O", "operator>");
      Dw.put("?P", "operator>=");
      Dw.put("?Q", "operator,");
      Dw.put("?R", "operator()");
      Dw.put("?S", "operator~");
      Dw.put("?T", "operator^");
      Dw.put("?U", "operator|");
      Dw.put("?V", "operator&&");
      Dw.put("?W", "operator||");
      Dw.put("?X", "operator*=");
      Dw.put("?Y", "operator+=");
      Dw.put("?Z", "operator-=");
      Dw.put("?_0", "operator/=");
      Dw.put("?_1", "operator%=");
      Dw.put("?_2", "operator>>=");
      Dw.put("?_3", "operator<<=");
      Dw.put("?_4", "operator&=");
      Dw.put("?_5", "operator|=");
      Dw.put("?_6", "operator^=");
      Dw.put("?_7", "`vftable'");
      Dw.put("?_8", "`vbtable'");
      Dw.put("?_9", "`vcall'");
      Dw.put("?_A", "`typeof'");
      Dw.put("?_B", "`local static guard'");
      Dw.put("?_C", "`string'");
      Dw.put("?_D", "`vbase destructor'");
      Dw.put("?_E", "`vector deleting destructor'");
      Dw.put("?_F", "`default constructor closure'");
      Dw.put("?_G", "`scalar deleting destructor'");
      Dw.put("?_H", "`vector constructor iterator'");
      Dw.put("?_I", "`vector destructor iterator'");
      Dw.put("?_J", "`vector vbase constructor iterator'");
      Dw.put("?_K", "`virtual displacement map'");
      Dw.put("?_L", "`eh vector constructor iterator'");
      Dw.put("?_M", "`eh vector destructor iterator'");
      Dw.put("?_N", "`eh vector vbase constructor iterator'");
      Dw.put("?_O", "`copy constructor closure'");
      Collections.unmodifiableMap(Dw);
      Uv.put("A", "private:");
      Uv.put("B", "private: far");
      Uv.put("C", "private: static");
      Uv.put("D", "private: static far");
      Uv.put("E", "private: virtual");
      Uv.put("F", "private: virtual far");
      Uv.put("I", "protected:");
      Uv.put("J", "protected: far");
      Uv.put("K", "protected: static");
      Uv.put("L", "protected: static far");
      Uv.put("M", "protected: virtual");
      Uv.put("N", "protected: virtual far");
      Uv.put("Q", "public:");
      Uv.put("R", "public: far");
      Uv.put("S", "public: static");
      Uv.put("T", "public: static far");
      Uv.put("U", "public: virtual");
      Uv.put("V", "public: virtual far");
      Uv.put("Y", "");
      Uv.put("Z", "");
      Collections.unmodifiableMap(Uv);
      oW.put("A", null);
      oW.put("B", Arrays.asList("const"));
      oW.put("C", Arrays.asList("volatile"));
      oW.put("D", Arrays.asList("const", "volatile"));
      oW.put("E", Arrays.asList("__ptr64"));
      Collections.unmodifiableMap(oW);
      gO.put("A", "__cdecl");
      gO.put("C", "__pascal");
      gO.put("E", "__thiscall");
      gO.put("G", "__stdcall");
      gO.put("I", "__fastcall");
      Collections.unmodifiableMap(gO);
      nf.put("?A", "");
      nf.put("?B", "const");
      nf.put("?C", "volatile");
      nf.put("?D", "const volatile");
      Collections.unmodifiableMap(nf);
   }

   private static class CU {
      private final String q;
      private int RF = 0;

      public CU(String var1) {
         this.q = var1;
      }

      public boolean q() {
         return this.RF < this.q.length();
      }

      public boolean q(String var1) {
         return this.q.startsWith(var1, this.RF);
      }

      public boolean RF(String var1) {
         return this.q.endsWith(var1);
      }

      private int Dw(String var1) {
         return this.q.indexOf(var1, this.RF);
      }

      public String RF() {
         if (!this.q()) {
            return null;
         } else {
            String var1 = this.q.substring(this.RF, this.RF + 1);
            this.RF(1);
            return var1;
         }
      }

      public String q(int var1) {
         if (this.RF + var1 > this.q.length() - 1) {
            throw new InvalidParameterException("State overflow");
         } else {
            String var2 = this.q.substring(this.RF, this.RF + var1);
            this.RF(var1);
            return var2;
         }
      }

      public String xK(String var1) {
         int var2 = this.Dw(var1);
         if (var2 == -1) {
            return null;
         } else {
            String var3 = this.q.substring(this.RF, var2);
            this.RF(var3.length() + 1);
            return var3;
         }
      }

      public void RF(int var1) {
         this.RF += var1;
      }

      public void xK() {
         this.RF = this.q.length();
      }

      @Override
      public String toString() {
         return this.q.substring(this.RF);
      }
   }

   private class eo {
      private String RF = null;
      private List xK = new ArrayList();

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         var1.append(this.RF);
         if (!this.xK.isEmpty()) {
            var1.append("<");
            ListIterator var2 = this.xK.listIterator();

            while (var2.hasNext()) {
               String var3 = (String)var2.next();
               aym.this.q(var3, var1);
               if (var2.hasNext()) {
                  var1.append(",");
               }
            }

            if (var1.substring(var1.length() - 1).equals(">")) {
               var1.append(" >");
            } else {
               var1.append(">");
            }
         }

         return var1.toString();
      }
   }

   private class nI {
      private aym.eo RF;
      private List xK = new ArrayList();
      private String Dw;
      private String Uv;
      private String oW;
      private String gO;
      private String nf;
      private List gP = new ArrayList();

      public boolean q() {
         return this.Dw.equals("C") || this.Dw.equals("D") || this.Dw.equals("K") || this.Dw.equals("L") || this.Dw.equals("S") || this.Dw.equals("T");
      }

      public boolean RF() {
         return this.Dw.equals("Y") || this.Dw.equals("Z");
      }

      public String xK() {
         StringBuilder var1 = new StringBuilder();
         ListIterator var2 = this.xK.listIterator(this.xK.size());

         while (var2.hasPrevious()) {
            var1.append(var2.previous());
            if (var2.hasPrevious()) {
               var1.append("::");
            }
         }

         if (this.xK.size() != 0) {
            var1.append("::");
         }

         if (this.RF.RF.startsWith("?0")) {
            var1.append(this.xK.get(0));
         } else if (this.RF.RF.startsWith("?1")) {
            var1.append("~");
            var1.append(this.xK.get(0));
         } else if (this.RF.RF.startsWith("?")) {
            var1.append((String)aym.Dw.get(this.RF.RF));
         } else {
            var1.append(this.RF.toString());
         }

         return var1.toString();
      }

      public ayp Dw() {
         String var1 = null;
         if (!this.nf.equals("@")) {
            StringBuilder var2 = new StringBuilder();
            if (this.gO != null && !((String)aym.nf.get(this.gO)).isEmpty()) {
               var2.append((String)aym.nf.get(this.gO));
               var2.append(" ");
            }

            aym.this.q(this.nf, var2);
            var1 = var2.toString();
         }

         ArrayList var6 = new ArrayList();

         for (String var4 : this.gP) {
            StringBuilder var5 = new StringBuilder();
            aym.this.q(var4, var5);
            var6.add(var5.toString());
         }

         if (var6.isEmpty()) {
            var6 = null;
         }

         List var7 = this.Uv != null && !this.Uv.equals("A") ? (List)aym.oW.get(this.Uv) : null;
         ayp var8 = new ayp(this.xK(), var1, var6, var7, null);
         if (this.Dw != null && !((String)aym.Uv.get(this.Dw)).isEmpty()) {
            var8.q = (String)aym.Uv.get(this.Dw);
         }

         if (this.oW != null && !((String)aym.gO.get(this.oW)).isEmpty()) {
            var8.RF = (String)aym.gO.get(this.oW);
         }

         return var8;
      }
   }
}
