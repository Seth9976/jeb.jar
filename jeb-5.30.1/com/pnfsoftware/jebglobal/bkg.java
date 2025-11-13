package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IJLSField;
import com.pnfsoftware.jeb.core.units.code.android.IJLSMethod;
import com.pnfsoftware.jeb.core.units.code.android.IJLSType;
import com.pnfsoftware.jeb.core.units.code.android.IJLSTypeAdapter;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTypeInfoProvider;
import com.pnfsoftware.jeb.util.collect.CacheMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class bkg implements IDTypeInfoProvider {
   private static final ILogger xK = GlobalLog.getLogger(bkg.class);
   com.pnfsoftware.jeb.corei.parsers.dex.bK q;
   bec RF;
   private bkp Dw;
   private bko Uv;
   private bkq oW;
   private CacheMap gO;
   private CacheMap nf;
   private CacheMap gP;
   private static final String za = "java/lang/Object";
   private static final String[] lm = new String[]{
      "getClass()Ljava/lang/Class;",
      "hashCode()I",
      "equals(Ljava/lang/Object;)Z",
      "toString()Ljava/lang/String;",
      "notify()V",
      "notifyAll()V",
      "wait(J)V",
      "wait(JI)V",
      "wait()V"
   };

   public bkg(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, bec var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
         this.Dw = new bkp(var1);
         this.RF = var2;
         if (this.RF != null) {
            this.Uv = new bko(this.RF);
         }

         this.oW = new bkq(this.Dw, this.Uv);
         this.gO = new CacheMap(1000, 200);
         this.nf = new CacheMap(1000, 200);
         this.gP = new CacheMap(1000, 200);
      }
   }

   public IJLSTypeAdapter q() {
      return this.oW;
   }

   @Override
   public boolean isCompatible(String var1, String var2) {
      int var3;
      for (var3 = 0; var3 < var1.length(); var3++) {
         char var4 = var1.charAt(var3);
         if (var4 != '[') {
            break;
         }
      }

      if (var3 == var1.length()) {
         throw new IllegalArgumentException();
      } else {
         int var7;
         for (var7 = 0; var7 < var2.length(); var7++) {
            char var5 = var2.charAt(var7);
            if (var5 != '[') {
               break;
            }
         }

         if (var7 == var2.length()) {
            throw new IllegalArgumentException();
         } else {
            String var8 = var1.substring(var3);
            String var6 = var2.substring(var7);
            if (var3 == var7) {
               if (var8.length() == 1 || var6.length() == 1) {
                  return var6.equals(var8);
               } else {
                  return var8.startsWith("L") && var6.equals("Ljava/lang/Object;") ? true : this.isChildOf(var8, var6);
               }
            } else {
               return var7 < var3 && var6.equals("Ljava/lang/Object;");
            }
         }
      }
   }

   @Override
   public boolean isChildOf(String var1, String var2) {
      var1 = bed.Dw(var1);
      var2 = bed.Dw(var2);
      String var3 = null;
      if (this.gO != null) {
         var3 = var1 + "," + var2;
         Boolean var4 = (Boolean)this.gO.get(var3);
         if (var4 != null) {
            return var4;
         }
      }

      boolean var7 = this.q(var1, var2);
      if (var3 != null) {
         this.gO.put(var3, var7);
      }

      return var7;
   }

   private boolean q(String var1, String var2) {
      HashSet var3 = new HashSet();
      ArrayDeque var4 = new ArrayDeque();
      var4.add(var1);

      while (!var4.isEmpty()) {
         var1 = (String)var4.remove();
         if (var1.equals(var2)) {
            return true;
         }

         List var5 = this.oW.getParentTypes(var1);
         if (var5 != null) {
            for (String var7 : var5) {
               if (var3.add(var7)) {
                  var4.add(var7);
               }
            }
         }
      }

      return false;
   }

   @Override
   public List findTypesWithSuperMethods(String var1, String var2, String var3, boolean var4) {
      var1 = bed.Dw(var1);
      String var5 = null;
      if (this.nf != null) {
         var5 = var1 + "->" + var2 + var3;
         List var6 = (List)this.nf.get(var5);
         if (var6 != null) {
            return var6;
         }
      }

      List var8 = this.q(var1, var2, var3, var4);
      if (var5 != null) {
         this.nf.put(var5, var8);
      }

      return var8;
   }

   private List q(String var1, String var2, String var3, boolean var4) {
      if (bed.q(var2)) {
         return Collections.emptyList();
      } else {
         IJLSMethod var5 = this.oW.findMethod(var1, var2, var3);
         if (q(var5, var2, var3) != 1) {
            return Collections.emptyList();
         } else {
            List var6 = this.oW.getParentTypes(var1);
            if (var6 == null) {
               return Collections.emptyList();
            } else {
               ArrayList var7 = new ArrayList();
               ArrayDeque var8 = new ArrayDeque();
               var8.addAll(var6);
               HashSet var9 = new HashSet();

               while (!var8.isEmpty()) {
                  String var10 = (String)var8.remove();
                  if (var9.add(var10)) {
                     var5 = this.oW.findMethod(var10, var2, var3);
                     int var11 = q(var5, var2, var3);
                     if (var11 == 1) {
                        var7.add(var10);
                     } else if (var11 == -1) {
                        continue;
                     }

                     if (var11 == 0 || !var4) {
                        List var12 = this.oW.getParentTypes(var10);
                        if (var12 != null) {
                           var8.addAll(var12);
                        }
                     }
                  }
               }

               return var7;
            }
         }
      }
   }

   private static int q(List var0, String var1, String var2) {
      for (IJLSMethod var4 : var0) {
         if (var1.equals(var4.getName()) && var2.equals(var4.getDescriptor())) {
            return (var4.getAccessFlags() & 2) != 0 ? -1 : 1;
         }
      }

      return 0;
   }

   private static int q(IJLSMethod var0, String var1, String var2) {
      if (var0 != null && var1.equals(var0.getName()) && var2.equals(var0.getDescriptor())) {
         return (var0.getAccessFlags() & 2) != 0 ? -1 : 1;
      } else {
         return 0;
      }
   }

   @Override
   public String isFunctionalType(String var1) {
      if (var1 == null) {
         return null;
      } else {
         if (this.gP != null) {
            String var2 = (String)this.gP.get(var1);
            if (var2 != null) {
               return var2;
            }
         }

         String var3 = this.q(var1);
         this.gP.put(var1, var3);
         return var3;
      }
   }

   private String q(String var1) {
      if (!"java/lang/Object".equals(this.oW.getSupertype(var1))) {
         return null;
      } else {
         String[] var2 = new String[1];

         for (String var4 : this.oW.getInterfaces(var1)) {
            if (!this.q(var4, var2)) {
               return null;
            }
         }

         return var2[0];
      }
   }

   private boolean q(String var1, String[] var2) {
      if ("java/lang/Object".equals(var1)) {
         return true;
      } else {
         List var3 = this.oW.getMethods(var1);
         if (var3 == null) {
            return false;
         } else {
            for (IJLSMethod var5 : var3) {
               if ((var5.getAccessFlags() & 8) != 8 && (var5.getAccessFlags() & 1024) != 0 && (var5.getAccessFlags() & 1) != 0) {
                  String var6 = var5.getSignature();
                  if (!Strings.isContainedIn(var6, lm)) {
                     if (var2[0] != null) {
                        return false;
                     }

                     var2[0] = var6;
                  }
               }
            }

            if (!"java/lang/Object".equals(this.oW.getSupertype(var1))) {
               return false;
            } else {
               for (String var8 : this.oW.getInterfaces(var1)) {
                  if (!this.q(var8, var2)) {
                     return false;
                  }
               }

               return true;
            }
         }
      }
   }

   @Override
   public IJLSType findType(String var1) {
      return this.oW.getType(var1);
   }

   @Override
   public IJLSMethod findMethod(String var1, String var2, String var3) {
      return this.findMethod(var1, var2, var3, true, null);
   }

   @Override
   public IJLSMethod findMethod(String var1, String var2, String var3, boolean var4, String[] var5) {
      if (var3.startsWith("(") && var3.endsWith(")")) {
         boolean var6 = var2.equals("<init>") || var2.equals("<clinit>");
         ArrayDeque var7 = new ArrayDeque();
         var7.add(var1);
         HashSet var8 = new HashSet();
         int var9 = 0;

         while (!var7.isEmpty()) {
            var1 = (String)var7.remove();
            if (var8.add(var1)) {
               List var10 = this.oW.getMethods(var1);
               if (var10 == null) {
                  break;
               }

               for (IJLSMethod var12 : var10) {
                  if (var12.getName().equals(var2) && (var4 && var9 <= 0 || (var12.getAccessFlags() & 1) != 0)) {
                     String var13 = var12.getDescriptor();
                     if (var13.startsWith(var3)) {
                        if (var5 != null) {
                           var5[0] = bed.Uv(var1);
                        }

                        return var12;
                     }
                  }
               }

               if (var4 || var6) {
                  break;
               }

               var7.addAll(this.oW.getParentTypes(var1));
               var9++;
            }
         }

         return null;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJLSField findField(String var1, String var2) {
      return this.findField(var1, var2, true, null);
   }

   @Override
   public IJLSField findField(String var1, String var2, boolean var3, String[] var4) {
      ArrayDeque var5 = new ArrayDeque();
      var5.add(var1);
      HashSet var6 = new HashSet();
      int var7 = 0;

      while (!var5.isEmpty()) {
         var1 = (String)var5.remove();
         if (var6.add(var1)) {
            List var8 = this.oW.getFields(var1);
            if (var8 == null) {
               return null;
            }

            for (IJLSField var10 : var8) {
               if (var10.getName().equals(var2) && (var3 && var7 <= 0 || (var10.getAccessFlags() & 1) != 0)) {
                  if (var4 != null) {
                     var4[0] = bed.Uv(var1);
                  }

                  return var10;
               }
            }

            if (var3) {
               break;
            }

            var5.addAll(this.oW.getParentTypes(var1));
            var7++;
         }
      }

      return null;
   }
}
