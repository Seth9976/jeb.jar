package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IJLSField;
import com.pnfsoftware.jeb.core.units.code.android.IJLSMethod;
import com.pnfsoftware.jeb.core.units.code.android.IJLSTypeAdapter;
import com.pnfsoftware.jeb.core.units.code.android.JvmFieldSig;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodDescriptor;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuObject;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.util.base.Wrapper;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayDeque;
import java.util.List;

public class bxz implements IDEmuObject {
   private bye RF;
   private bxy xK;
   IDImm q;
   private bxv Dw = new bxv();

   bxz(bye var1, bxy var2) {
      this.RF = var1;
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         this.xK = var2;
         IDexClass var3 = var1.getDex().getClass(var2.q);

         do {
            bxu var4 = this.Dw.q(var3.getSignature(false));

            for (IDexField var6 : var3.getFields()) {
               String var7 = var6.getName(false);
               String var8 = var6.getFieldTypeSignature(false);
               var4.q(var7, var1.nf(var8));
            }

            int var9 = var3.getSuperTypeIndex();
            if (var9 < 0) {
               break;
            }

            var3 = var1.getDex().getType(var9).getImplementingClass();
         } while (var3 != null);
      }
   }

   public String q() {
      return "obj_of_" + this.xK.RF();
   }

   public bxy RF() {
      return this.xK;
   }

   @Override
   public int getId() {
      return this.q.getObjectReferenceId();
   }

   public IDImm q(String var1, IDImm var2) throws DexDecEvaluationException {
      if (this.RF.qa().q()) {
         long var3 = bye.io();
         Wrapper var5 = this.RF.qa().getField(var3, var1, var2);
         if (var5 != null) {
            return (IDImm)var5.get();
         }
      }

      JvmFieldSig var6 = JvmFieldSig.parse(var1);
      return this.Dw.q(var6.csig, var6.fname);
   }

   public boolean q(String var1, IDImm var2, IDImm var3) {
      JvmFieldSig var4 = JvmFieldSig.parse(var1);
      if (!this.Dw.RF(var4.csig)) {
         return false;
      } else {
         boolean var5 = false;
         if (this.RF.qa().q()) {
            long var6 = bye.io();
            IDImm[] var8 = new IDImm[]{var3};
            Boolean var9 = this.RF.qa().setField(var6, var1, var2, var8);
            if (var9 != null) {
               if (var9) {
                  var5 = true;
               } else {
                  var3 = var8[0];
               }
            }
         }

         return var5 ? true : this.Dw.RF(var4.csig, var4.fname, var3);
      }
   }

   public String q(String var1) throws DexDecEvaluationException {
      return this.q(var1, false);
   }

   public String q(String var1, boolean var2) throws DexDecEvaluationException {
      return this.q(var1, var2 ? 1 : 0, null);
   }

   public String q(String var1, String var2) throws DexDecEvaluationException {
      return this.q(var1, 2, var2);
   }

   private String q(String var1, int var2, String var3) throws DexDecEvaluationException {
      if (var2 == 2 && var1.startsWith("Ljava/lang/Object;->")) {
         return null;
      } else {
         JvmMethodSig var4 = JvmMethodSig.parse(var1);
         String var5 = var4.mname;
         List var6 = var4.partypes;
         String var7 = var4.rettype;
         IJLSTypeAdapter var8 = this.RF.getTypeAdapter();
         if (var8.getType(this.xK.q) == null) {
            return null;
         } else if (var8.getType(var4.csig) == null) {
            return null;
         } else {
            boolean var9 = (var8.getType(var4.csig).getAccessFlags() & 512) != 0;
            int var10 = 0;
            ArrayDeque var11 = new ArrayDeque();
            boolean var12;
            if (var2 == 2) {
               JvmMethodSig var13 = JvmMethodSig.parse(var3);
               String var14 = var13.csig;
               var10++;
               var12 = !var9;
               if (var8.getType(var14) == null) {
                  return null;
               }

               if (var12) {
                  String var15 = var8.getSupertype(var14);
                  if (var15 == null) {
                     return null;
                  }

                  var11.add(var15);
               }

               var11.addAll(var8.getInterfaces(var14));
            } else {
               var12 = false;
               var11.add(this.xK.q);
            }

            while (!var11.isEmpty()) {
               String var16 = (String)var11.remove();
               String var17 = this.q(var8, var10, var16, var5, var6, var7);
               if (var17 != null) {
                  return var17;
               }

               if (var2 != 2 || var12) {
                  String var18 = var8.getSupertype(var16);
                  if (var18 != null && !"Ljava/lang/Object;".equals(var18)) {
                     var11.add(var18);
                  }
               }

               List var19 = var8.getInterfaces(var16);
               if (var19 == null) {
                  return null;
               }

               var11.addAll(var19);
               var10++;
            }

            return null;
         }
      }
   }

   private String q(IJLSTypeAdapter var1, int var2, String var3, String var4, List var5, String var6) {
      List var7 = var1.getMethods(var3);
      if (var7 == null) {
         return null;
      } else {
         String var8 = null;

         label53:
         for (IJLSMethod var10 : var7) {
            if ((var10.getAccessFlags() & 8) == 0 && (var10.getAccessFlags() & 1024) == 0 && (var2 <= 0 || (var10.getAccessFlags() & 2) == 0)) {
               String var11 = var10.getName();
               if (!var11.startsWith("<") && var11.equals(var4)) {
                  JvmMethodDescriptor var12 = JvmMethodDescriptor.parse(var10.getDescriptor());
                  if (var12.partypes.size() == var5.size()) {
                     for (int var13 = 0; var13 < var5.size(); var13++) {
                        if (!((String)var5.get(var13)).equals(var12.partypes.get(var13))) {
                           continue label53;
                        }
                     }

                     var8 = var3 + "->" + var10;
                     if (var6.equals(var12.rettype)) {
                        break;
                     }
                  }
               }
            }
         }

         return var8;
      }
   }

   public String RF(String var1) throws DexDecEvaluationException {
      IJLSTypeAdapter var2 = this.RF.getTypeAdapter();
      JvmFieldSig var3 = JvmFieldSig.parse(var1);
      String var4 = var3.csig;
      String var5 = var3.fname;
      String var6 = var3.ftype;

      for (int var7 = 0; var4 != null; var7++) {
         String var8 = this.q(var2, var7, var4, var5, var6);
         if (var8 != null) {
            return var8;
         }

         String var9 = var2.getSupertype(var4);
         if (var9 == null || "Ljava/lang/Object;".equals(var9)) {
            break;
         }

         var4 = var9;
      }

      return null;
   }

   private String q(IJLSTypeAdapter var1, int var2, String var3, String var4, String var5) {
      List var6 = var1.getFields(var3);
      if (var6 == null) {
         return null;
      } else {
         String var7 = null;

         for (IJLSField var9 : var6) {
            if ((var9.getAccessFlags() & 8) == 0 && (var2 <= 0 || (var9.getAccessFlags() & 2) == 0)) {
               String var10 = var9.getName();
               if (var10.equals(var4)) {
                  var7 = var3 + "->" + var9;
                  if (!var5.equals(var9.getDescriptor())) {
                     continue;
                  }
                  break;
               }
            }
         }

         return var7;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("object %s", this.xK);
   }
}
