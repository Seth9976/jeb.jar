package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.units.code.android.IDexReferenceManager;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexPoolType;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexReferenceType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAddress;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotation;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationElement;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationForField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationForMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationsDirectory;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.bjo;
import com.pnfsoftware.jebglobal.bjp;
import com.pnfsoftware.jebglobal.bjs;
import com.pnfsoftware.jebglobal.bjt;
import com.pnfsoftware.jebglobal.bkx;
import com.pnfsoftware.jebglobal.bky;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Ser
public class oM implements IDexReferenceManager {
   @SerId(1)
   @Deprecated
   private HashMap RF;
   @SerId(2)
   @Deprecated
   private HashMap xK;
   @SerId(3)
   @Deprecated
   private HashMap Dw;
   @SerId(4)
   @Deprecated
   private HashMap Uv;
   @Deprecated
   @SerId(value = 5, deprecated = true)
   private boolean oW;
   @SerId(6)
   @Deprecated
   private HashMap gO;
   @SerId(7)
   @Deprecated
   private HashMap nf;
   @SerId(8)
   @Deprecated
   private HashMap gP;
   @SerId(9)
   @Deprecated
   private HashMap za;
   @SerId(10)
   private Map lm;
   @SerId(11)
   private Map zz;
   @SerId(12)
   private Map JY;
   @SerId(13)
   private Map HF;
   @SerId(14)
   bK q;
   @SerId(15)
   private Set LK;
   @SerId(16)
   private Set io;

   @SerCustomInitPostGraph
   private void RF() {
      if (this.lm == null) {
         this.lm = new HashMap(this.gO.size());
         this.gO.forEach((var1, var2) -> {
            LinkedHashSet var3 = new LinkedHashSet(var2.size());
            var2.forEach(var1x -> var3.add(new oM.eo(var1x)));
            this.lm.put(var1, var3);
         });
         this.gO = null;
      }

      if (this.zz == null) {
         this.zz = new HashMap(this.nf.size());
         this.nf.forEach((var1, var2) -> {
            LinkedHashSet var3 = new LinkedHashSet(var2.size());
            var2.forEach(var1x -> var3.add(new oM.eo(var1x)));
            this.zz.put(var1, var3);
         });
         this.nf = null;
      }

      if (this.JY == null) {
         this.JY = new HashMap(this.gP.size());
         this.gP.forEach((var1, var2) -> {
            LinkedHashSet var3 = new LinkedHashSet(var2.size());
            var2.forEach(var1x -> var3.add(new oM.eo(var1x)));
            this.JY.put(var1, var3);
         });
         this.gP = null;
      }

      if (this.HF == null) {
         this.HF = new HashMap(this.za.size());
         this.za.forEach((var1, var2) -> {
            LinkedHashSet var3 = new LinkedHashSet(var2.size());
            var2.forEach(var1x -> var3.add(new oM.eo(var1x)));
            this.HF.put(var1, var3);
         });
         this.za = null;
      }

      if (this.LK == null || this.io == null) {
         this.LK = new HashSet();
         this.io = new HashSet();
         this.q();
      }
   }

   public oM(bK var1) {
      this.q = var1;
      this.lm = new HashMap();
      this.zz = new HashMap();
      this.JY = new HashMap();
      this.HF = new HashMap();
      this.LK = new HashSet();
      this.io = new HashSet();
   }

   public void q(int var1, int var2, DexPoolType var3, int var4) {
      if (var3 == DexPoolType.FIELD) {
         this.q(var1, var2, var4);
      } else if (var3 == DexPoolType.METHOD) {
         this.RF(var1, var2, var4);
      } else {
         if (var3 != DexPoolType.TYPE) {
            throw new RuntimeException();
         }

         this.xK(var1, var2, var4);
      }
   }

   public void q(int var1, int var2, int var3) {
      this.q(DexPoolType.STRING, var1, var2, var3);
   }

   public void RF(int var1, int var2, int var3) {
      this.RF(DexPoolType.STRING, var1, var2, var3);
   }

   public void xK(int var1, int var2, int var3) {
      this.xK(DexPoolType.STRING, var1, var2, var3);
   }

   public void q(int var1, int var2, int var3, int var4) {
      this.q(DexPoolType.STRING, var1, var2, var3, var4);
   }

   @Override
   public boolean addStringReference(String var1, String var2, DexReferenceType var3) {
      bjs var4 = (bjs)this.q.JY().xK(var1);
      if (var4 == null) {
         return false;
      } else {
         int var5 = var4.getIndex();
         if (!(this.q.xK().q(var2) instanceof InstructionCoordinates var7)) {
            return false;
         } else {
            this.q(DexPoolType.STRING, var5, var7.getMethodId(), var7.getOffset(), var3.getId());
            return true;
         }
      }
   }

   public void RF(int var1, int var2, int var3, int var4) {
      this.q(DexPoolType.TYPE, var1, var2, var3, var4);
   }

   public void xK(int var1, int var2, int var3, int var4) {
      this.q(DexPoolType.FIELD, var1, var2, var3, var4);
   }

   @Override
   public boolean addFieldReference(String var1, String var2, DexReferenceType var3) {
      bjo var4 = this.q.xK(var1);
      if (var4 == null) {
         return false;
      } else {
         int var5 = var4.getIndex();
         if (!(this.q.xK().q(var2) instanceof InstructionCoordinates var7)) {
            return false;
         } else {
            this.q(DexPoolType.FIELD, var5, var7.getMethodId(), var7.getOffset(), var3.getId());
            return true;
         }
      }
   }

   public void Dw(int var1, int var2, int var3, int var4) {
      this.q(DexPoolType.METHOD, var1, var2, var3, var4);
   }

   @Override
   public boolean addMethodReference(String var1, String var2, DexReferenceType var3) {
      bjp var4 = this.q.Dw(var1);
      if (var4 == null) {
         return false;
      } else {
         int var5 = var4.getIndex();
         if (!(this.q.xK().q(var2) instanceof InstructionCoordinates var7)) {
            return false;
         } else {
            this.q(DexPoolType.METHOD, var5, var7.getMethodId(), var7.getOffset(), var3.getId());
            return true;
         }
      }
   }

   private void q(DexPoolType var1, int var2, int var3, int var4, int var5) {
      Assert.a(var3 <= 16777215);
      long var6 = 504403158265495552L | (long)var3 << 32 | var4;
      this.q(var1, var2, var6, var5);
   }

   private void q(DexPoolType var1, int var2, int var3, int var4) {
      Assert.a(var3 <= 16777215);
      long var5 = 360287970189639680L | (long)var3 << 32;
      this.q(var1, var2, var5, var4);
   }

   private void RF(DexPoolType var1, int var2, int var3, int var4) {
      Assert.a(var3 <= 16777215);
      long var5 = 432345564227567616L | (long)var3 << 32;
      this.q(var1, var2, var5, var4);
   }

   private void xK(DexPoolType var1, int var2, int var3, int var4) {
      Assert.a(var3 <= 16777215);
      long var5 = 216172782113783808L | (long)var3 << 32;
      this.q(var1, var2, var5, var4);
   }

   private synchronized void q(DexPoolType var1, int var2, long var3, int var5) {
      Maps.putMulti(switch (var1) {
         case STRING -> this.lm;
         case TYPE -> this.zz;
         case FIELD -> this.JY;
         case METHOD -> this.HF;
         default -> throw new JebRuntimeException("Illegal pool type: " + var1);
      }, var2, new oM.eo(var3, var5), LinkedHashSet::new);
   }

   private void q(int var1, Collection var2) {
      for (oM.eo var4 : var2) {
         Maps.putMulti(this.zz, var1, var4, LinkedHashSet::new);
      }
   }

   public boolean q(int var1) {
      return !this.oW(var1).isEmpty();
   }

   @Override
   public Collection getReferences(DexPoolType var1, int var2) {
      return this.getReferences(var1, var2, 0);
   }

   @Override
   public Collection getReferences(DexPoolType var1, int var2, int var3) {
      Collection var4 = switch (var1) {
         case STRING -> this.oW(var2);
         case TYPE -> this.gO(var2);
         case FIELD -> this.nf(var2);
         case METHOD -> this.gP(var2);
         default -> throw new JebRuntimeException("Unsupported pool type for xrefs: " + var1);
      };
      if (var3 <= 0) {
         var3 = 100000;
      }

      ArrayList var5 = new ArrayList();

      for (oM.eo var7 : var4) {
         long var8 = var7.q;
         int var10 = (int)(var8 >>> 56);
         if (var10 == 0) {
            var8 |= 504403158265495552L;
         }

         var5.add(new iZ(var8, null, var7.RF));
         if (var5.size() >= var3) {
            break;
         }
      }

      if (var5.size() < var3) {
         if (var1 == DexPoolType.METHOD) {
            bky var12 = new bky(this.q);
            bjp var15 = this.q.gO(var2);

            label75:
            for (int var9 : var12.xK(var15, false)) {
               for (oM.eo var11 : this.gP(var9)) {
                  var5.add(new iZ(var11, "Not a direct reference"));
                  if (var5.size() >= var3) {
                     break label75;
                  }
               }
            }
         } else if (var1 == DexPoolType.FIELD) {
            bkx var13 = new bkx(this.q);
            bjo var16 = this.q.oW(var2);

            label63:
            for (int var20 : var13.q(var16, false)) {
               for (oM.eo var23 : this.nf(var20)) {
                  var5.add(new iZ(var23, "Not a direct reference"));
                  if (var5.size() >= var3) {
                     break label63;
                  }
               }
            }
         }
      }

      for (IDexAddress var17 : var5) {
         ((iZ)var17).q(this.q);
      }

      return var5;
   }

   private Collection oW(int var1) {
      if (this.lm != null) {
         Collection var2 = (Collection)this.lm.get(var1);
         if (var2 != null) {
            return var2;
         }
      }

      return Collections.emptyList();
   }

   private Collection gO(int var1) {
      if (this.zz != null) {
         Collection var2 = (Collection)this.zz.get(var1);
         if (var2 != null) {
            return var2;
         }
      }

      return Collections.emptyList();
   }

   private Collection nf(int var1) {
      if (this.JY != null) {
         Collection var2 = (Collection)this.JY.get(var1);
         if (var2 != null) {
            return var2;
         }
      }

      return Collections.emptyList();
   }

   private Collection gP(int var1) {
      if (this.HF != null) {
         Collection var2 = (Collection)this.HF.get(var1);
         if (var2 != null) {
            return var2;
         }
      }

      return Collections.emptyList();
   }

   public void q() {
      for (int var2 : this.JY.keySet()) {
         if (this.LK.add(var2)) {
            int var3 = this.q.oW(var2).getClassTypeIndex();
            this.q(var3, (Collection)this.JY.get(var2));
         }
      }

      for (int var7 : this.HF.keySet()) {
         if (this.io.add(var7)) {
            String var8 = this.q.gO(var7).Dw().getNonArrayClass();
            bjt var4 = (bjt)this.q.io().xK(var8);
            if (var4 != null) {
               int var5 = var4.getIndex();
               this.q(var5, (Collection)this.HF.get(var7));
            }
         }
      }
   }

   public void q(int var1, IDexAnnotationsDirectory var2) {
      for (IDexAnnotationItem var4 : var2.getClassAnnotations()) {
         if (!var4.isSystemLevelAnnotation()) {
            this.q(var4.getAnnotation(), var1, DexPoolType.TYPE);
         }
      }

      for (IDexAnnotationForMethod var9 : var2.getMethodsAnnotations()) {
         for (IDexAnnotationItem var6 : var9.getAnnotationItems()) {
            if (!var6.isSystemLevelAnnotation()) {
               this.q(var6.getAnnotation(), var9.getMethodIndex(), DexPoolType.METHOD);
            }
         }
      }

      for (IDexAnnotationForField var10 : var2.getFieldsAnnotations()) {
         for (IDexAnnotationItem var12 : var10.getAnnotationItems()) {
            if (!var12.isSystemLevelAnnotation()) {
               this.q(var12.getAnnotation(), var10.getFieldIndex(), DexPoolType.FIELD);
            }
         }
      }
   }

   private void q(IDexAnnotation var1, int var2, DexPoolType var3) {
      for (IDexAnnotationElement var5 : var1.getElements()) {
         this.q(var5.getValue(), var2, var3);
      }
   }

   void q(IDexValue var1, int var2, DexPoolType var3) {
      int var4 = var1.getType();
      if (var4 == 23) {
         int var5 = var1.getStringIndex();
         this.q(var5, var2, var3, iZ.RF);
      } else if (var4 == 28) {
         for (IDexValue var6 : var1.getArray()) {
            this.q(var6, var2, var3);
         }
      } else if (var4 == 29) {
         IDexAnnotation var8 = var1.getAnnotation();
         this.q(var8, var2, var3);
      }
   }

   public List q(DexPoolType var1, int var2) {
      switch (var1) {
         case STRING:
            return this.RF(var2);
         case TYPE:
            return this.xK(var2);
         case FIELD:
            return this.Dw(var2);
         case METHOD:
            return this.Uv(var2);
         default:
            throw new JebRuntimeException("Illegal pool type: " + var1);
      }
   }

   public List RF(int var1) {
      return this.RF == null ? null : (List)this.RF.get(var1);
   }

   public List xK(int var1) {
      return this.xK == null ? null : (List)this.xK.get(var1);
   }

   public List Dw(int var1) {
      return this.Dw == null ? null : (List)this.Dw.get(var1);
   }

   public List Uv(int var1) {
      return this.Uv == null ? null : (List)this.Uv.get(var1);
   }

   @Ser
   public static class eo {
      @SerId(1)
      long q;
      @SerId(2)
      int RF;

      public eo(long var1) {
         this(var1, iZ.q);
      }

      public eo(long var1, int var3) {
         this.q = var1;
         this.RF = var3;
      }

      public long q() {
         return this.q;
      }

      public int RF() {
         return this.RF & 0xFF;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + this.RF;
         return 31 * var1 + (int)(this.q ^ this.q >>> 32);
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            oM.eo var2 = (oM.eo)var1;
            return this.RF != var2.RF ? false : this.q == var2.q;
         }
      }

      @Override
      public String toString() {
         return "loc=" + this.q + ",flags=" + this.RF;
      }
   }
}
