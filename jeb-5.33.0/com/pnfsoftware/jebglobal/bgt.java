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

public class bgt implements IJLSTypeAdapter {
   com.pnfsoftware.jeb.corei.parsers.dex.vi pC;
   boolean A;
   boolean kS;

   public bgt(IDexUnit var1) {
      this(var1, true);
   }

   public bgt(IDexUnit var1, boolean var2) {
      this(var1, var2, true);
   }

   public bgt(IDexUnit var1, boolean var2, boolean var3) {
      this.pC = (com.pnfsoftware.jeb.corei.parsers.dex.vi)var1;
      this.A = var2;
      this.kS = var3;
   }

   private bfs pC(String var1) {
      bfy var2 = this.pC.ld().sY(bak.wS(var1));
      return var2 == null ? null : var2.A();
   }

   @Override
   public IJLSType getType(String var1) {
      bfs var2 = this.pC(var1);
      return var2 == null ? null : new bgp(var2.getSignature(this.kS), var2.getAccessFlags());
   }

   @Override
   public String getSupertype(String var1) {
      bfs var2 = this.pC(var1);
      if (var2 == null) {
         return null;
      } else {
         String var3 = var2.getSupertypeSignature(this.kS);
         return this.A ? bak.kS(var3) : var3;
      }
   }

   @Override
   public List getInterfaces(String var1) {
      bfs var2 = this.pC(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();

         for (String var7 : var2.getInterfaceSignatures(this.kS)) {
            var3.add(this.A ? bak.kS(var7) : var7);
         }

         return var3;
      }
   }

   @Override
   public List getParentTypes(String var1) {
      bfs var2 = this.pC(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();
         String var4 = var2.getSupertypeSignature(this.kS);
         var3.add(this.A ? bak.kS(var4) : var4);

         for (String var8 : var2.getInterfaceSignatures(this.kS)) {
            var3.add(this.A ? bak.kS(var8) : var8);
         }

         return var3;
      }
   }

   @Override
   public List getMethods(String var1) {
      bfs var2 = this.pC(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();

         for (bfu var5 : var2.getMethods()) {
            bgo var6 = new bgo(var5.getName(this.kS), var5.UT().generate(this.kS), var5.A().getAccessFlags());
            var3.add(var6);
         }

         return var3;
      }
   }

   @Override
   public IJLSMethod findMethod(String var1, String var2, String var3) {
      bfs var4 = this.pC(var1);
      if (var4 == null) {
         return null;
      } else {
         bfu var5 = var4.pC(this.kS, var2, var3);
         return var5 == null ? null : new bgo(var2, var3, var5.A().getAccessFlags());
      }
   }

   @Override
   public List getFields(String var1) {
      bfs var2 = this.pC(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();

         for (bft var5 : var2.getFields()) {
            bgn var6 = new bgn(var5.getName(this.kS), var5.getFieldTypeSignature(this.kS), var5.A().getAccessFlags());
            var3.add(var6);
         }

         return var3;
      }
   }

   @Override
   public List getTypeAnnotations(String var1) {
      bfs var2 = this.pC(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();

         for (IDexAnnotationItem var5 : var2.ys()) {
            IDexAnnotation var6 = var5.getAnnotation();
            var3.add(this.pC(var6));
         }

         return var3;
      }
   }

   private bgm pC(IDexAnnotation var1) {
      LinkedHashMap var2 = new LinkedHashMap();

      for (IDexAnnotationElement var4 : var1.getElements()) {
         String var5 = var4.getName(this.pC);
         IDexValue var6 = var4.getValue();
         var2.put(var5, this.pC(var6));
      }

      String var7 = this.pC.pC(var1.getTypeIndex(), this.kS);
      return new bgm(var7, var2);
   }

   private bgr pC(IDexValue var1) {
      switch (var1.getType()) {
         case 0:
            return new bgr('B', var1.getByte());
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
            return new bgr('\u0000', null);
         case 2:
            return new bgr('S', var1.getShort());
         case 3:
            return new bgr('C', var1.getChar());
         case 4:
            return new bgr('I', var1.getInt());
         case 6:
            return new bgr('J', var1.getLong());
         case 16:
            return new bgr('F', var1.getFloat());
         case 17:
            return new bgr('D', var1.getDouble());
         case 23:
            return new bgr('s', this.pC.kS(var1.getStringIndex()));
         case 24:
            return new bgr('c', this.pC.pC(var1.getTypeIndex(), false));
         case 27:
            return new bgr('e', this.pC.E(var1.getEnumIndex()).getSignature(false));
         case 28:
            ArrayList var3 = new ArrayList();

            for (IDexValue var5 : var1.getArray()) {
               var3.add(this.pC(var5));
            }

            return new bgr('[', var3.toArray());
         case 29:
            IDexAnnotation var2 = var1.getAnnotation();
            return new bgr('@', this.pC(var2));
         case 30:
            return new bgr('\u0000', null);
         case 31:
            return new bgr('Z', var1.getBoolean());
      }
   }
}
