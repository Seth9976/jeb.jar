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
import com.pnfsoftware.jebglobal.bft;
import com.pnfsoftware.jebglobal.bfu;
import com.pnfsoftware.jebglobal.bfx;
import com.pnfsoftware.jebglobal.bfy;
import com.pnfsoftware.jebglobal.bhb;
import com.pnfsoftware.jebglobal.bhc;
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
public class bO implements IDexReferenceManager {
   @SerId(1)
   @Deprecated
   private HashMap A;
   @SerId(2)
   @Deprecated
   private HashMap kS;
   @SerId(3)
   @Deprecated
   private HashMap wS;
   @SerId(4)
   @Deprecated
   private HashMap UT;
   @Deprecated
   @SerId(value = 5, deprecated = true)
   private boolean E;
   @SerId(6)
   @Deprecated
   private HashMap sY;
   @SerId(7)
   @Deprecated
   private HashMap ys;
   @SerId(8)
   @Deprecated
   private HashMap ld;
   @SerId(9)
   @Deprecated
   private HashMap gp;
   @SerId(10)
   private Map oT;
   @SerId(11)
   private Map fI;
   @SerId(12)
   private Map WR;
   @SerId(13)
   private Map NS;
   @SerId(14)
   vi pC;
   @SerId(15)
   private Set vP;
   @SerId(16)
   private Set xC;

   @SerCustomInitPostGraph
   private void A() {
      if (this.oT == null) {
         this.oT = new HashMap(this.sY.size());
         this.sY.forEach((var1, var2) -> {
            LinkedHashSet var3 = new LinkedHashSet(var2.size());
            var2.forEach(var1x -> var3.add(new bO.Av(var1x)));
            this.oT.put(var1, var3);
         });
         this.sY = null;
      }

      if (this.fI == null) {
         this.fI = new HashMap(this.ys.size());
         this.ys.forEach((var1, var2) -> {
            LinkedHashSet var3 = new LinkedHashSet(var2.size());
            var2.forEach(var1x -> var3.add(new bO.Av(var1x)));
            this.fI.put(var1, var3);
         });
         this.ys = null;
      }

      if (this.WR == null) {
         this.WR = new HashMap(this.ld.size());
         this.ld.forEach((var1, var2) -> {
            LinkedHashSet var3 = new LinkedHashSet(var2.size());
            var2.forEach(var1x -> var3.add(new bO.Av(var1x)));
            this.WR.put(var1, var3);
         });
         this.ld = null;
      }

      if (this.NS == null) {
         this.NS = new HashMap(this.gp.size());
         this.gp.forEach((var1, var2) -> {
            LinkedHashSet var3 = new LinkedHashSet(var2.size());
            var2.forEach(var1x -> var3.add(new bO.Av(var1x)));
            this.NS.put(var1, var3);
         });
         this.gp = null;
      }

      if (this.vP == null || this.xC == null) {
         this.vP = new HashSet();
         this.xC = new HashSet();
         this.pC();
      }
   }

   public bO(vi var1) {
      this.pC = var1;
      this.oT = new HashMap();
      this.fI = new HashMap();
      this.WR = new HashMap();
      this.NS = new HashMap();
      this.vP = new HashSet();
      this.xC = new HashSet();
   }

   public void pC(int var1, int var2, DexPoolType var3, int var4) {
      if (var3 == DexPoolType.FIELD) {
         this.pC(var1, var2, var4);
      } else if (var3 == DexPoolType.METHOD) {
         this.A(var1, var2, var4);
      } else {
         if (var3 != DexPoolType.TYPE) {
            throw new RuntimeException();
         }

         this.kS(var1, var2, var4);
      }
   }

   public void pC(int var1, int var2, int var3) {
      this.pC(DexPoolType.STRING, var1, var2, var3);
   }

   public void A(int var1, int var2, int var3) {
      this.A(DexPoolType.STRING, var1, var2, var3);
   }

   public void kS(int var1, int var2, int var3) {
      this.kS(DexPoolType.STRING, var1, var2, var3);
   }

   public void pC(int var1, int var2, int var3, int var4) {
      this.pC(DexPoolType.STRING, var1, var2, var3, var4);
   }

   @Override
   public boolean addStringReference(String var1, String var2, DexReferenceType var3) {
      bfx var4 = (bfx)this.pC.sY().kS(var1);
      if (var4 == null) {
         return false;
      } else {
         int var5 = var4.getIndex();
         if (!(this.pC.A().pC(var2) instanceof InstructionCoordinates var7)) {
            return false;
         } else {
            this.pC(DexPoolType.STRING, var5, var7.getMethodId(), var7.getOffset(), var3.getId());
            return true;
         }
      }
   }

   public void A(int var1, int var2, int var3, int var4) {
      this.pC(DexPoolType.TYPE, var1, var2, var3, var4);
   }

   public void kS(int var1, int var2, int var3, int var4) {
      this.pC(DexPoolType.FIELD, var1, var2, var3, var4);
   }

   @Override
   public boolean addFieldReference(String var1, String var2, DexReferenceType var3) {
      bft var4 = this.pC.kS(var1);
      if (var4 == null) {
         return false;
      } else {
         int var5 = var4.getIndex();
         if (!(this.pC.A().pC(var2) instanceof InstructionCoordinates var7)) {
            return false;
         } else {
            this.pC(DexPoolType.FIELD, var5, var7.getMethodId(), var7.getOffset(), var3.getId());
            return true;
         }
      }
   }

   public void wS(int var1, int var2, int var3, int var4) {
      this.pC(DexPoolType.METHOD, var1, var2, var3, var4);
   }

   @Override
   public boolean addMethodReference(String var1, String var2, DexReferenceType var3) {
      bfu var4 = this.pC.wS(var1);
      if (var4 == null) {
         return false;
      } else {
         int var5 = var4.getIndex();
         if (!(this.pC.A().pC(var2) instanceof InstructionCoordinates var7)) {
            return false;
         } else {
            this.pC(DexPoolType.METHOD, var5, var7.getMethodId(), var7.getOffset(), var3.getId());
            return true;
         }
      }
   }

   private void pC(DexPoolType var1, int var2, int var3, int var4, int var5) {
      Assert.a(var3 <= 16777215);
      long var6 = 504403158265495552L | (long)var3 << 32 | var4;
      this.pC(var1, var2, var6, var5);
   }

   private void pC(DexPoolType var1, int var2, int var3, int var4) {
      Assert.a(var3 <= 16777215);
      long var5 = 360287970189639680L | (long)var3 << 32;
      this.pC(var1, var2, var5, var4);
   }

   private void A(DexPoolType var1, int var2, int var3, int var4) {
      Assert.a(var3 <= 16777215);
      long var5 = 432345564227567616L | (long)var3 << 32;
      this.pC(var1, var2, var5, var4);
   }

   private void kS(DexPoolType var1, int var2, int var3, int var4) {
      Assert.a(var3 <= 16777215);
      long var5 = 216172782113783808L | (long)var3 << 32;
      this.pC(var1, var2, var5, var4);
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private synchronized void pC(DexPoolType var1, int var2, long var3, int var5) {
      Maps.putMulti(switch (var1) {
         case STRING -> this.oT;
         case TYPE -> this.fI;
         case FIELD -> this.WR;
         case METHOD -> this.NS;
         default -> throw new JebRuntimeException("Illegal pool type: " + var1);
      }, var2, new bO.Av(var3, var5), LinkedHashSet::new);
   }

   private void pC(int var1, Collection var2) {
      for (bO.Av var4 : var2) {
         Maps.putMulti(this.fI, var1, var4, LinkedHashSet::new);
      }
   }

   public boolean pC(int var1) {
      return !this.E(var1).isEmpty();
   }

   @Override
   public Collection getReferences(DexPoolType var1, int var2) {
      return this.getReferences(var1, var2, 0);
   }

   @Override
   public Collection getReferences(DexPoolType var1, int var2, int var3) {
      Collection var4 = switch (var1) {
         case STRING -> this.E(var2);
         case TYPE -> this.sY(var2);
         case FIELD -> this.ys(var2);
         case METHOD -> this.ld(var2);
         default -> throw new JebRuntimeException("Unsupported pool type for xrefs: " + var1);
      };
      if (var3 <= 0) {
         var3 = 100000;
      }

      ArrayList var5 = new ArrayList();

      for (bO.Av var7 : var4) {
         long var8 = var7.pC;
         int var10 = (int)(var8 >>> 56);
         if (var10 == 0) {
            var8 |= 504403158265495552L;
         }

         var5.add(new DH(var8, null, var7.A));
         if (var5.size() >= var3) {
            break;
         }
      }

      if (var5.size() < var3) {
         if (var1 == DexPoolType.METHOD) {
            bhc var12 = new bhc(this.pC);
            bfu var15 = this.pC.sY(var2);

            label75:
            for (int var9 : var12.kS(var15, false)) {
               for (bO.Av var11 : this.ld(var9)) {
                  var5.add(new DH(var11, "Not a direct reference"));
                  if (var5.size() >= var3) {
                     break label75;
                  }
               }
            }
         } else if (var1 == DexPoolType.FIELD) {
            bhb var13 = new bhb(this.pC);
            bft var16 = this.pC.E(var2);

            label63:
            for (int var20 : var13.pC(var16, false)) {
               for (bO.Av var23 : this.ys(var20)) {
                  var5.add(new DH(var23, "Not a direct reference"));
                  if (var5.size() >= var3) {
                     break label63;
                  }
               }
            }
         }
      }

      for (IDexAddress var17 : var5) {
         ((DH)var17).pC(this.pC);
      }

      return var5;
   }

   private Collection E(int var1) {
      if (this.oT != null) {
         Collection var2 = (Collection)this.oT.get(var1);
         if (var2 != null) {
            return var2;
         }
      }

      return Collections.emptyList();
   }

   private Collection sY(int var1) {
      if (this.fI != null) {
         Collection var2 = (Collection)this.fI.get(var1);
         if (var2 != null) {
            return var2;
         }
      }

      return Collections.emptyList();
   }

   private Collection ys(int var1) {
      if (this.WR != null) {
         Collection var2 = (Collection)this.WR.get(var1);
         if (var2 != null) {
            return var2;
         }
      }

      return Collections.emptyList();
   }

   private Collection ld(int var1) {
      if (this.NS != null) {
         Collection var2 = (Collection)this.NS.get(var1);
         if (var2 != null) {
            return var2;
         }
      }

      return Collections.emptyList();
   }

   public void pC() {
      for (int var2 : this.WR.keySet()) {
         if (this.vP.add(var2)) {
            int var3 = this.pC.E(var2).getClassTypeIndex();
            this.pC(var3, (Collection)this.WR.get(var2));
         }
      }

      for (int var7 : this.NS.keySet()) {
         if (this.xC.add(var7)) {
            String var8 = this.pC.sY(var7).wS().getNonArrayClass();
            bfy var4 = (bfy)this.pC.ld().kS(var8);
            if (var4 != null) {
               int var5 = var4.getIndex();
               this.pC(var5, (Collection)this.NS.get(var7));
            }
         }
      }
   }

   public void pC(int var1, IDexAnnotationsDirectory var2) {
      for (IDexAnnotationItem var4 : var2.getClassAnnotations()) {
         if (!var4.isSystemLevelAnnotation()) {
            this.pC(var4.getAnnotation(), var1, DexPoolType.TYPE);
         }
      }

      for (IDexAnnotationForMethod var9 : var2.getMethodsAnnotations()) {
         for (IDexAnnotationItem var6 : var9.getAnnotationItems()) {
            if (!var6.isSystemLevelAnnotation()) {
               this.pC(var6.getAnnotation(), var9.getMethodIndex(), DexPoolType.METHOD);
            }
         }
      }

      for (IDexAnnotationForField var10 : var2.getFieldsAnnotations()) {
         for (IDexAnnotationItem var12 : var10.getAnnotationItems()) {
            if (!var12.isSystemLevelAnnotation()) {
               this.pC(var12.getAnnotation(), var10.getFieldIndex(), DexPoolType.FIELD);
            }
         }
      }
   }

   private void pC(IDexAnnotation var1, int var2, DexPoolType var3) {
      for (IDexAnnotationElement var5 : var1.getElements()) {
         this.pC(var5.getValue(), var2, var3);
      }
   }

   void pC(IDexValue var1, int var2, DexPoolType var3) {
      int var4 = var1.getType();
      if (var4 == 23) {
         int var5 = var1.getStringIndex();
         this.pC(var5, var2, var3, DH.A);
      } else if (var4 == 28) {
         for (IDexValue var6 : var1.getArray()) {
            this.pC(var6, var2, var3);
         }
      } else if (var4 == 29) {
         IDexAnnotation var8 = var1.getAnnotation();
         this.pC(var8, var2, var3);
      }
   }

   public List pC(DexPoolType var1, int var2) {
      switch (var1) {
         case STRING:
            return this.A(var2);
         case TYPE:
            return this.kS(var2);
         case FIELD:
            return this.wS(var2);
         case METHOD:
            return this.UT(var2);
         default:
            throw new JebRuntimeException("Illegal pool type: " + var1);
      }
   }

   public List A(int var1) {
      return this.A == null ? null : (List)this.A.get(var1);
   }

   public List kS(int var1) {
      return this.kS == null ? null : (List)this.kS.get(var1);
   }

   public List wS(int var1) {
      return this.wS == null ? null : (List)this.wS.get(var1);
   }

   public List UT(int var1) {
      return this.UT == null ? null : (List)this.UT.get(var1);
   }

   @Ser
   public static class Av {
      @SerId(1)
      long pC;
      @SerId(2)
      int A;

      public Av(long var1) {
         this(var1, DH.pC);
      }

      public Av(long var1, int var3) {
         this.pC = var1;
         this.A = var3;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + this.A;
         return 31 * var1 + (int)(this.pC ^ this.pC >>> 32);
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
            bO.Av var2 = (bO.Av)var1;
            return this.A != var2.A ? false : this.pC == var2.pC;
         }
      }

      @Override
      public String toString() {
         return "loc=" + this.pC + ",flags=" + this.A;
      }
   }
}
