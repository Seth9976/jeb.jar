package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IJLSMethod;
import com.pnfsoftware.jeb.core.units.code.android.IJLSType;
import com.pnfsoftware.jeb.core.units.code.android.IJLSTypeAdapter;
import java.util.ArrayList;
import java.util.List;

public class bkq implements IJLSTypeAdapter {
   List q = new ArrayList();

   public bkq(IJLSTypeAdapter... var1) {
      for (IJLSTypeAdapter var5 : var1) {
         if (var5 != null) {
            this.q.add(var5);
         }
      }
   }

   @Override
   public IJLSType getType(String var1) {
      for (IJLSTypeAdapter var3 : this.q) {
         IJLSType var4 = var3.getType(var1);
         if (var4 != null) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public String getSupertype(String var1) {
      for (IJLSTypeAdapter var3 : this.q) {
         String var4 = var3.getSupertype(var1);
         if (var4 != null) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public List getInterfaces(String var1) {
      for (IJLSTypeAdapter var3 : this.q) {
         List var4 = var3.getInterfaces(var1);
         if (var4 != null) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public List getParentTypes(String var1) {
      for (IJLSTypeAdapter var3 : this.q) {
         List var4 = var3.getParentTypes(var1);
         if (var4 != null) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public List getMethods(String var1) {
      for (IJLSTypeAdapter var3 : this.q) {
         List var4 = var3.getMethods(var1);
         if (var4 != null) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public IJLSMethod findMethod(String var1, String var2, String var3) {
      for (IJLSTypeAdapter var5 : this.q) {
         IJLSMethod var6 = var5.findMethod(var1, var2, var3);
         if (var6 != null) {
            return var6;
         }
      }

      return null;
   }

   @Override
   public List getFields(String var1) {
      for (IJLSTypeAdapter var3 : this.q) {
         List var4 = var3.getFields(var1);
         if (var4 != null) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public List getTypeAnnotations(String var1) {
      for (IJLSTypeAdapter var3 : this.q) {
         List var4 = var3.getTypeAnnotations(var1);
         if (var4 != null) {
            return var4;
         }
      }

      return null;
   }
}
