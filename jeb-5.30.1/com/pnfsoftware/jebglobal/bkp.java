package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.IJLSMethod;
import com.pnfsoftware.jeb.core.units.code.android.IJLSType;
import com.pnfsoftware.jeb.core.units.code.android.IJLSTypeAdapter;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotation;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationElement;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class bkp implements IJLSTypeAdapter {
   com.pnfsoftware.jeb.corei.parsers.dex.bK q;
   boolean RF;
   boolean xK;

   public bkp(IDexUnit var1) {
      this(var1, true);
   }

   public bkp(IDexUnit var1, boolean var2) {
      this(var1, var2, true);
   }

   public bkp(IDexUnit var1, boolean var2, boolean var3) {
      this.q = (com.pnfsoftware.jeb.corei.parsers.dex.bK)var1;
      this.RF = var2;
      this.xK = var3;
   }

   private bjn q(String var1) {
      bjt var2 = this.q.io().gO(bed.Uv(var1));
      return var2 == null ? null : var2.xK();
   }

   @Override
   public IJLSType getType(String var1) {
      bjn var2 = this.q(var1);
      return var2 == null ? null : new bkl(var2.getSignature(this.xK), var2.getAccessFlags());
   }

   @Override
   public String getSupertype(String var1) {
      bjn var2 = this.q(var1);
      if (var2 == null) {
         return null;
      } else {
         String var3 = var2.getSupertypeSignature(this.xK);
         return this.RF ? bed.Dw(var3) : var3;
      }
   }

   @Override
   public List getInterfaces(String var1) {
      bjn var2 = this.q(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();

         for (String var7 : var2.getInterfaceSignatures(this.xK)) {
            var3.add(this.RF ? bed.Dw(var7) : var7);
         }

         return var3;
      }
   }

   @Override
   public List getParentTypes(String var1) {
      bjn var2 = this.q(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();
         String var4 = var2.getSupertypeSignature(this.xK);
         var3.add(this.RF ? bed.Dw(var4) : var4);

         for (String var8 : var2.getInterfaceSignatures(this.xK)) {
            var3.add(this.RF ? bed.Dw(var8) : var8);
         }

         return var3;
      }
   }

   @Override
   public List getMethods(String var1) {
      bjn var2 = this.q(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();

         for (bjp var5 : var2.getMethods()) {
            bkk var6 = new bkk(var5.getName(this.xK), var5.Uv().generate(this.xK), var5.RF().getAccessFlags());
            var3.add(var6);
         }

         return var3;
      }
   }

   @Override
   public IJLSMethod findMethod(String var1, String var2, String var3) {
      bjn var4 = this.q(var1);
      if (var4 == null) {
         return null;
      } else {
         bjp var5 = var4.q(this.xK, var2, var3);
         return var5 == null ? null : new bkk(var2, var3, var5.RF().getAccessFlags());
      }
   }

   @Override
   public List getFields(String var1) {
      bjn var2 = this.q(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();

         for (bjo var5 : var2.getFields()) {
            bki var6 = new bki(var5.getName(this.xK), var5.getFieldTypeSignature(this.xK), var5.RF().getAccessFlags());
            var3.add(var6);
         }

         return var3;
      }
   }

   @Override
   public List getTypeAnnotations(String var1) {
      bjn var2 = this.q(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();

         for (IDexAnnotationItem var5 : var2.za()) {
            IDexAnnotation var6 = var5.getAnnotation();
            var3.add(this.q(var6));
         }

         return var3;
      }
   }

   private bkh q(IDexAnnotation var1) {
      LinkedHashMap var2 = new LinkedHashMap();

      for (IDexAnnotationElement var4 : var1.getElements()) {
         String var5 = var4.getName(this.q);
         IDexValue var6 = var4.getValue();
         var2.put(var5, this.q(var6));
      }

      String var7 = this.q.q(var1.getTypeIndex(), this.xK);
      return new bkh(var7, var2);
   }

   private bkn q(IDexValue var1) {
      switch (var1.getType()) {
         case 0:
            return new bkn('B', var1.getByte());
         case 1:
         case 5:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 25:
         case 26:
         default:
            return new bkn('\u0000', null);
         case 2:
            return new bkn('S', var1.getShort());
         case 3:
            return new bkn('C', var1.getChar());
         case 4:
            return new bkn('I', var1.getInt());
         case 6:
            return new bkn('J', var1.getLong());
         case 16:
            return new bkn('F', var1.getFloat());
         case 17:
            return new bkn('D', var1.getDouble());
         case 23:
            return new bkn('s', this.q.xK(var1.getStringIndex()));
         case 24:
            return new bkn('c', this.q.q(var1.getTypeIndex(), false));
         case 27:
            return new bkn('e', this.q.oW(var1.getEnumIndex()).getSignature(false));
         case 28:
            ArrayList var3 = new ArrayList();

            for (IDexValue var5 : var1.getArray()) {
               var3.add(this.q(var5));
            }

            return new bkn('[', var3.toArray());
         case 29:
            IDexAnnotation var2 = var1.getAnnotation();
            return new bkn('@', this.q(var2));
         case 30:
            return new bkn('\u0000', null);
         case 31:
            return new bkn('Z', var1.getBoolean());
      }
   }
}
